package org.frameworkset.boot;/*
 *  Copyright 2008 biaoping.yin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.io.File;

public class DefaultApplicationBootContext implements ApplicationBootContext {
	private String context;
	private int port;
	private File appdir;
	private String docBase;
	private String serverStatus ;
	private String host;
	public String getContext() {
		return context;
	}

	public String getDocBase() {
		return docBase;
	}
	public String getHost(){
		return host;
	}

	public void setHost(String host){
		this.host = host;
	}
	public void setDocBase(String docBase) {
		this.docBase = docBase;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public File getAppdir() {
		return appdir;
	}

	public void setAppdir(File appdir) {
		this.appdir = appdir;
	}
	public String getServerStatus() {
		return serverStatus;
	}
	public void setServerStatus(String serverStatus) {
		this.serverStatus = serverStatus;
	}
}
