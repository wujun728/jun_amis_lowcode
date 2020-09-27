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

public interface ApplicationBootContext {
	public final int __FAILED = -1;
	public final int __STOPPED = 0;
	public final int __STARTING = 1;
	public final int __STARTED = 2;
	public final int __STOPPING = 3;

	public String getContext();


	public int getPort() ;


	public File getAppdir() ;
	public String getServerStatus() ;
	public void setServerStatus(String serverStatus) ;

	public String getDocBase() ;

	public void setDocBase(String docBase);
	public String getHost();

	public void setHost(String host);
}
