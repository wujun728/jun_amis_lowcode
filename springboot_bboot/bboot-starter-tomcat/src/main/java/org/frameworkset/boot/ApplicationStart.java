package org.frameworkset.boot;


import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardThreadExecutor;
import org.apache.catalina.startup.Constants;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ApplicationStart extends BaseApplicationStart{
	private static Logger log = LoggerFactory.getLogger(ApplicationStart.class);
	private Tomcat tomcat = null;
	private static final String PROP_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";

	private static final String DEFAULT_CHARSET = "UTF-8";

	public ApplicationStart() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getServerType() {
		return "Tomcat";
	}
	private URL getWebappConfigFileFromDirectory(File docBase) {
		URL result = null;
		File webAppContextXml = new File(docBase, Constants.ApplicationWebXml);
		if (webAppContextXml.exists()) {
			try {
				result = webAppContextXml.toURI().toURL();
			} catch (MalformedURLException e) {
				log.info(
						"Unable to determine web application context.xml " + docBase, e);
			}
		}
		return result;
	}

	@Override
	protected void startContainer(ApplicationBootContext applicationBootContext)  throws Exception{


		tomcat = new Tomcat();
//		tomcat.setPort(this.getPort());
		tomcat.setBaseDir(".");
//		tomcat.setBaseDir(applicationBootContext.getDocBase());

		Connector connector = new Connector(PROP_PROTOCOL);
		connector.setPort(getPort());
		connector.setURIEncoding(DEFAULT_CHARSET);

		// 设置一下最大线程数
		this.tomcat.getService().addConnector(connector);
		StandardThreadExecutor executor = new StandardThreadExecutor();
		executor.setMaxThreads(this.getMaxThreads());

		connector.getService().addExecutor(executor);

		this.tomcat.setConnector(connector);

		this.tomcat.setHostname(getHost());
		this.tomcat.getEngine().setBackgroundProcessorDelay(30);

		tomcat.getHost().setAutoDeploy(false);
		tomcat.getHost().setAppBase(".");
		String contextPath = applicationBootContext.getContext();
		StandardContext context = new StandardContext();
		context.setParentClassLoader(Thread.currentThread().getContextClassLoader());
		context.setPath(contextPath);
		context.setDelegate(false);
		context.setDocBase(applicationBootContext.getDocBase());
		context.setAltDDName(applicationBootContext.getDocBase()+"/WEB-INF/web.xml");

		context.setConfigFile(getWebappConfigFileFromDirectory(new File(applicationBootContext.getDocBase())));
		ContextConfig contextConfig = new ContextConfig();

		context.addLifecycleListener(contextConfig );
		context.addLifecycleListener(new Tomcat.DefaultWebXmlListener());
		context.addLifecycleListener(new Tomcat.FixContextListener());

//		context.addLifecycleListener(new StoreMergedWebXmlListener(applicationBootContext));
//		context.setDefaultWebXml(applicationBootContext.getDocBase()+"/WEB-INF/web.xml");
//		context.addWatchedResource(applicationBootContext.getDocBase()+"/WEB-INF/web.xml");
		log.info(applicationBootContext.getDocBase()+"/WEB-INF/web.xml");
//
//		WebResourceRoot resources = new StandardRoot(context);
//		resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
//				applicationBootContext.getDocBase(), "/"));
//		context.setResources(resources);
		tomcat.getHost().addChild(context);




		tomcat.start();

		log.info("configuring app with basedir: " + applicationBootContext.getDocBase());

		applicationBootContext.setServerStatus("started");
	}
	private static class StoreMergedWebXmlListener implements LifecycleListener {
		private ApplicationBootContext applicationBootContext;
		private static final String MERGED_WEB_XML = "org.apache.tomcat.util.scan.MergedWebXml";

		public StoreMergedWebXmlListener(ApplicationBootContext applicationBootContext){
			this.applicationBootContext = applicationBootContext;
		}

		@Override
		public void lifecycleEvent(LifecycleEvent event) {
			if (event.getType().equals(Lifecycle.CONFIGURE_START_EVENT)) {
				onStart((Context) event.getLifecycle());
			}
		}

		private void onStart(Context context) {
			ServletContext servletContext = context.getServletContext();

			if (servletContext.getAttribute(MERGED_WEB_XML) == null) {
				servletContext.setAttribute(MERGED_WEB_XML, getEmptyWebXml());
			}
		}

		private String getEmptyWebXml() {
			InputStream stream = null;
			try {
				try {
					stream = new FileInputStream(new File(applicationBootContext.getDocBase()+"/WEB-INF/web.xml"));
					if (stream == null) {
						throw new IllegalArgumentException("Unable to read "+applicationBootContext.getDocBase()+"/WEB-INF/web.xml");
					}
					StringBuilder out = new StringBuilder();
					InputStreamReader reader = new InputStreamReader(stream, DEFAULT_CHARSET);
					char[] buffer = new char[1024 * 4];
					int bytesRead = -1;
					while ((bytesRead = reader.read(buffer)) != -1) {
						out.append(buffer, 0, bytesRead);
					}
					return out.toString();
				} finally {
					stream.close();
				}
			} catch (IOException ex) {
				throw new IllegalStateException(ex);
			}
		}

	}
	@Override
	protected void afterStartContainer(ApplicationBootContext applicationBootContext) throws Exception{
		Thread tomcatAwaitThread = new Thread("container-1" ) {
			@Override
			public void run() {
				ApplicationStart.this.tomcat.getServer().await();
			}
		};
		tomcatAwaitThread.setContextClassLoader(getClass().getClassLoader());
		tomcatAwaitThread.setDaemon(false);
		tomcatAwaitThread.start();

	}


	public static void main(String[] args) {
		ApplicationStart applicationStart = new ApplicationStart();
		applicationStart.start();
	}

}
