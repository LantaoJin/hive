/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hive.upgrade.acid;

import org.apache.commons.cli.CommandLine;
import org.apache.hadoop.hive.metastore.TableType;

/**
 * This class's instance holds the option values were passed by the user via the command line.
 */
public class RunOptions {

  public static RunOptions fromCommandLine(CommandLine commandLine) {
    String tableTypeText = commandLine.getOptionValue("tableType");
    return new RunOptions(
      commandLine.getOptionValue("location", "."),
      commandLine.hasOption("execute"),
      commandLine.getOptionValue("dbRegex", ".*"),
      commandLine.getOptionValue("tableRegex", ".*"),
      tableTypeText == null ? null : TableType.valueOf(tableTypeText)
    );
  }

  private final String outputDir;
  private final boolean execute;
  private final String dbRegex;
  private final String tableRegex;
  private final TableType tableType;

  public RunOptions(String outputDir, boolean execute, String dbRegex, String tableRegex, TableType tableType) {
    this.outputDir = outputDir;
    this.execute = execute;
    this.dbRegex = dbRegex;
    this.tableRegex = tableRegex;
    this.tableType = tableType;
  }

  public String getOutputDir() {
    return outputDir;
  }

  public boolean isExecute() {
    return execute;
  }

  public String getDbRegex() {
    return dbRegex;
  }

  public String getTableRegex() {
    return tableRegex;
  }

  public TableType getTableType() {
    return tableType;
  }

  @Override
  public String toString() {
    return "RunOptions{" +
            "outputDir='" + outputDir + '\'' +
            ", execute=" + execute +
            ", dbRegex='" + dbRegex + '\'' +
            ", tableRegex='" + tableRegex + '\'' +
            ", tableType=" + tableType +
            '}';
  }
}
