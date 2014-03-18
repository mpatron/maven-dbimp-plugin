package org.apache.maven.plugin.dbimp;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.jobjects.dbimp.Importation;

/**
 * MyMojo Description. @Mojo( name = "sayhi" ) is the minimal required
 * annotation.
 * @author Mickaël Patron 
 * @since <since-text>
 */
@Mojo(name = "sayhi", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class MyMojo implements org.apache.maven.plugin.Mojo {
  private Logger LOGGER = Logger.getLogger(getClass().getName());

  private Log log = null;

  public Log getLog() {
    if (log == null) {
      log = new SystemStreamLog();
    }
    return log;
  }

  public void setLog(Log log) {
    this.log = log;
  }


  @Parameter(required = true)
  protected String password;
  
  @Parameter(required = true)
  protected String username;
  
  @Parameter(required = true)
  protected String url;
  
  @Parameter(required = true)
  protected String fileTxt;

  @Parameter(required = true)
  protected String fileXml;

  @Parameter(required = false)
  protected String report;

  @Parameter(required = false, defaultValue="ISO-8859-1")
  protected String encodage;
  
  @Parameter(required = true)
  protected String schemaName;
  
  public void execute() {
	  getLog().info("Hello, world !");
	  
	  StringBuffer sb = new StringBuffer ();
	  sb.append("username="+username+System.lineSeparator());
	  sb.append("password="+password+System.lineSeparator());
	  sb.append("url="+url+System.lineSeparator());
	  sb.append("fileTxt="+fileTxt+System.lineSeparator());
	  sb.append("fileXml="+fileXml+System.lineSeparator());
	  sb.append("report="+report+System.lineSeparator());
	  sb.append("encodage="+encodage+System.lineSeparator());
	  sb.append("schemaName="+schemaName+System.lineSeparator());
	  
	  
	  getLog().info(sb.toString());
	  try {
		  String[] args = new String[]{"-U", username, 
				  						"-P", password,
				  						"-u", url,
				  						"-f", fileTxt,
				  						"-x", fileXml,
				  						"-r", report,
				  						"--schema", schemaName}; 
		  Importation.run(args);
	  } catch (Throwable e) {
		  LOGGER.log(Level.SEVERE, "Error in Importation starting.", e);
	  }
	  	  
    LOGGER.info("Hello, world.");
  }

}
