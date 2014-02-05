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

import java.io.File;
import java.util.logging.Logger;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * MyMojo Description. @Mojo( name = "sayhi" ) is the minimal required
 * annotation.
 * 
 * @since <since-text>
 */
@Mojo(name = "sayhi", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class MyMojo implements org.apache.maven.plugin.Mojo {
  private Logger LOGGER = Logger.getLogger(getClass().getName());

  @Parameter(required = true)
  protected File outputDirectory;

  public void execute() {
    getLog().info("Hello, world ! => outputDirectory=" + outputDirectory);
    LOGGER.info("Hello, world.");
  }

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
}
