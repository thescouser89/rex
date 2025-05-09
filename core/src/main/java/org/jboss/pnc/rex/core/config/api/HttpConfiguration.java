/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2021-2024 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.rex.core.config.api;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import org.jboss.pnc.rex.core.config.StatusCodeRetryPolicy;
import org.jboss.pnc.rex.core.config.RequestRetryPolicy;

import java.time.Duration;

/**
 * Configuration for internal HTTP client requests from Rex.
 */
@ConfigMapping(prefix = "scheduler.options.http-configuration") //CDI
public interface HttpConfiguration {

    /**
     * Timeout in millis for HTTP client in which the request times out.
     *
     * Value of 0 means NO timeout.
     *
     * @return duration until request times out
     */
    @WithDefault("5m") // 5 minutes
    Duration idleTimeout();

    /**
     * Configures HTTP client to follow 3xx redirects.
     *
     * @return boolean
     */
    @WithDefault("true")
    boolean followRedirects();

    /**
     * Configuration of fault tolerance in case of Unreachable Host, Timeouts, DNS, TLS....
     *
     * If the fault tolerance does not result in a successful request, usually a fallback is triggered.
     *
     * @return FT configuration for unexpected failures
     */
    RequestRetryPolicy requestRetryPolicy();

    /**
     * Configuration of fault tolerance in case when http error response is received.
     */
    StatusCodeRetryPolicy statusCodeRetryPolicy();

}
