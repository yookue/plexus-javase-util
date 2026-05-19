/*
 * Copyright (c) 2016 Unikue Ltd. All rights reserved.
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

package cn.unikue.commonplexus.javaseutil.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.commons.lang3.SystemUtils;


/**
 * Utilities for detecting docker
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public abstract class DockerDetectionWraps {
    private static final String DOCKER_ENV_FILE = "/.dockerenv";    // $NON-NLS-1$
    private static final String DOCKER_CGROUP_FILE = "/proc/1/cgroup";    // $NON-NLS-1$
    private static final String DOCKER_ENV = "docker";    // $NON-NLS-1$
    private static final String CONTAINER_ENV = "containerd";    // $NON-NLS-1$
    private static final String PODMAN_ENV = "container";    // $NON-NLS-1$

    private static final String DOCKER_HOST = "DOCKER_HOST";    // $NON-NLS-1$
    private static final String DOCKER_CONTAINER = "DOCKER_CONTAINER";    // $NON-NLS-1$
    private static final String CONTAINER = "CONTAINER";    // $NON-NLS-1$

    /**
     * Check if current process is running inside a Docker container
     *
     * @return true if running in Docker, false otherwise
     */
    public static boolean isUnderDocker() {
        return checkDockerEnvFile() || checkCgroupFile()  || checkContainerEnvVars();
    }

    /**
     * Retrieve Docker host from environment
     *
     * @return Docker host or null if not available
     */
    public static String getDockerHost() {
        return SystemUtils.getEnvironmentVariable(DOCKER_HOST, null);
    }

    /**
     * Check for /.dockerenv file (most reliable method)
     */
    private static boolean checkDockerEnvFile() {
        return new File(DOCKER_ENV_FILE).exists();
    }

    /**
     * Check /proc/1/cgroup for docker/containerd keywords
     */
    private static boolean checkCgroupFile() {
        File cgroupFile = new File(DOCKER_CGROUP_FILE);
        if (!cgroupFile.exists() || !cgroupFile.canRead()) {
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(cgroupFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (StringUtilsWraps.containsAnyIgnoreCase(line, DOCKER_ENV, CONTAINER_ENV, PODMAN_ENV)) {
                    return true;
                }
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * Check for Docker-specific environment variables
     */
    private static boolean checkContainerEnvVars() {
        String dockerContainer = SystemUtils.getEnvironmentVariable(DOCKER_CONTAINER, null);
        String container = SystemUtils.getEnvironmentVariable(CONTAINER, null);
        return StringUtilsWraps.anyNotBlank(dockerContainer, container);
    }
}
