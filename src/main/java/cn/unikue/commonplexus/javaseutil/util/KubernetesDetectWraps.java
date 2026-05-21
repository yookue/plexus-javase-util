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


import java.io.File;
import org.apache.commons.lang3.SystemUtils;


/**
 * Utilities for detecting Kubernetes environment
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public abstract class KubernetesDetectWraps {
    private static final String KUBERNETES_SERVICE_HOST = "KUBERNETES_SERVICE_HOST";    // $NON-NLS-1$
    private static final String KUBERNETES_SERVICE_PORT = "KUBERNETES_SERVICE_PORT";    // $NON-NLS-1$
    private static final String KUBERNETES_PORT = "KUBERNETES_PORT";    // $NON-NLS-1$
    private static final String SERVICE_ACCOUNT_PATH = "/var/run/secrets/kubernetes.io/serviceaccount";    // $NON-NLS-1$
    private static final String SERVICE_ACCOUNT_TOKEN = SERVICE_ACCOUNT_PATH + "/token";    // $NON-NLS-1$
    private static final String SERVICE_ACCOUNT_NAMESPACE = SERVICE_ACCOUNT_PATH + "/namespace";    // $NON-NLS-1$

    /**
     * Check if current process is running inside Kubernetes cluster
     *
     * @return true if running in Kubernetes, false otherwise
     */
    public static boolean isInKubernetes() {
        return checkContainerEnvVars() || checkServiceAccount();
    }

    /**
     * Get Kubernetes service host from environment
     *
     * @return Kubernetes service host or null if not available
     */
    public static String getKubernetesHost() {
        return SystemUtils.getEnvironmentVariable(KUBERNETES_SERVICE_HOST, null);
    }

    /**
     * Get Kubernetes service port from environment
     *
     * @return Kubernetes service port or null if not available
     */
    public static String getKubernetesPort() {
        return SystemUtils.getEnvironmentVariable(KUBERNETES_SERVICE_PORT, null);
    }

    /**
     * Check for Kubernetes-specific environment variables
     */
    private static boolean checkContainerEnvVars() {
        String serviceHost = SystemUtils.getEnvironmentVariable(KUBERNETES_SERVICE_HOST, null);
        String servicePort = SystemUtils.getEnvironmentVariable(KUBERNETES_SERVICE_PORT, null);
        String kubernetesPort = SystemUtils.getEnvironmentVariable(KUBERNETES_PORT, null);
        return StringUtilsWraps.anyNotBlank(serviceHost, servicePort, kubernetesPort);
    }

    /**
     * Check for Kubernetes service account files
     */
    private static boolean checkServiceAccount() {
        File serviceAccountDir = new File(SERVICE_ACCOUNT_PATH);
        if (!serviceAccountDir.exists() || !serviceAccountDir.isDirectory()) {
            return false;
        }
        File tokenFile = new File(SERVICE_ACCOUNT_TOKEN);
        File namespaceFile = new File(SERVICE_ACCOUNT_NAMESPACE);
        return tokenFile.exists() || namespaceFile.exists();
    }
}
