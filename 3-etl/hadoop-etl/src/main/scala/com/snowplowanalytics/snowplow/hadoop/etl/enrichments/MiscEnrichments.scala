/*
 * Copyright (c) 2012-2013 SnowPlow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package com.snowplowanalytics.snowplow.hadoop.etl
package enrichments

// Scalaz
import scalaz._
import Scalaz._

// Get our project settings
import generated.ProjectSettings

/**
 * Miscellaneous enrichments which don't fit into
 * one of the other modules.
 */
object MiscEnrichments {
  
  /**
   * The version of this ETL
   */
  val etlVersion = "hadoop-%s" format ProjectSettings.version

  /**
   * Validate the specified
   * platform.
   *
   * @param platform The code
   *        for the platform
   *        generating this
   *        event.
   * @return a Scalaz
   *         Validation[String, String].
   */
  val extractPlatform: (String) => Validation[String, String] = platform => {
    platform match {
      case "web" => "web".success
      case "iot" => "iot".success // Internet of Things (e.g. Arduino tracker)
      case p => "[%s] is not a support tracking platform".format(p).fail
    }
  }
}