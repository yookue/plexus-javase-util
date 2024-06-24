/*
 * Copyright (c) 2016 Yookue Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yookue.commonplexus.javaseutil.util;


import org.apache.commons.lang3.StringUtils;
import lombok.Getter;


/**
 * Utilities for detecting docker
 *
 * @author David Hsing
 * @see "com.baidu.fsg.uid.utils.DockerUtils"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class DockerDetectionWraps {
    private static final String JPAAS_HOST = "JPAAS_HOST";
    private static final String JPAAS_HTTP_PORT = "JPAAS_HTTP_PORT";
    private static final String JPAAS_HOST_PORT = "JPAAS_HOST_PORT_8080";

    @Getter
    private static String dockerHost;

    @Getter
    private static String dockerPort;

    @Getter
    private static boolean underDocker;

    static {
        detectEnvironment();
    }

    /**
     * Retrieve host & port from environment
     */
    private static void detectEnvironment() {
        dockerHost = SystemUtilsWraps.getVariable(JPAAS_HOST);
        dockerPort = SystemUtilsWraps.getVariable(JPAAS_HTTP_PORT);
        if (StringUtils.isBlank(dockerPort)) {
            dockerPort = SystemUtilsWraps.getVariable(JPAAS_HOST_PORT);
        }
        underDocker = StringUtils.isNoneBlank(dockerHost, dockerPort);
    }
}
