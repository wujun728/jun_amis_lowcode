package org.frameworkset.boot;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationStart extends BaseApplicationStart{
	private static Logger log = LoggerFactory.getLogger(ApplicationStart.class);
	private Server server;
	public ApplicationStart() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getServerType() {
		return "Jetty";
	}

	@Override
	protected void startContainer(ApplicationBootContext applicationBootContext)  throws Exception{
		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setIdleTimeout(getThreadPoolIdleTimeout());
		threadPool.setMinThreads(getMinThreads());
		threadPool.setMaxThreads(getMaxThreads());
		Server server = new Server(threadPool);
		ServerConnector connector = new ServerConnector(server);
		connector.setHost(applicationBootContext.getHost());
		connector.setPort(applicationBootContext.getPort());
		connector.setIdleTimeout(getIdleTimeout());
		server.setConnectors(new Connector[]{connector});

		// 关联一个已经存在的上下文
		WebAppContext context = new WebAppContext();
		// 设置描述符位置
		//context.setDescriptor("./"+webroot+"/WEB-INF/web.xml");
		// 设置Web内容上下文路径

		context.setResourceBase(applicationBootContext.getDocBase());
		// 设置上下文路径
		context.setContextPath(applicationBootContext.getContext());
		context.setParentLoaderPriority(true);
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		log.info("WebAppContext:"+context.toString());

		contexts.setHandlers(new Handler[] { context });

		// This webapp will use jsps and jstl. We need to enable the
		// AnnotationConfiguration in order to correctly
		// set up the jsp container
		Configuration.ClassList classlist = Configuration.ClassList
				.setServerDefault( server );
		classlist.addBefore(
				"org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration" );

		// Set the ContainerIncludeJarPattern so that jetty examines these
		// container-path jars for tlds, web-fragments etc.
		// If you omit the jar that contains the jstl .tlds, the jsp engine will
		// scan for them instead.
		context.setAttribute(
				"org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$" );



		// Configure a LoginService.
		// Since this example is for our test webapp, we need to setup a
		// LoginService so this shows how to create a very simple hashmap based
		// one. The name of the LoginService needs to correspond to what is
		// configured in the webapp's web.xml and since it has a lifecycle of
		// its own we register it as a bean with the Jetty server object so it
		// can be started and stopped according to the lifecycle of the server
		// itself.
//			HashLoginService loginService = new HashLoginService();
//			loginService.setName( "Test Realm" );
//			loginService.setConfig( "src/test/resources/realm.properties" );
//			server.addBean( loginService );

		server.setHandler(contexts);


		// 启动
		server.start();
		applicationBootContext.setServerStatus(server.getState());
		this.server = server;
	}

	@Override
	protected void afterStartContainer(ApplicationBootContext applicationBootContext) throws Exception{
		server.join();
	}


	public static void main(String[] args) {
		ApplicationStart applicationStart = new ApplicationStart();
		applicationStart.start();
	}






}
