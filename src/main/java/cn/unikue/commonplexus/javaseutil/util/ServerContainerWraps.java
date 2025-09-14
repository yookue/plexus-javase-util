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


import java.lang.management.ManagementFactory;
import java.util.Set;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import cn.unikue.commonplexus.javaseutil.enumeration.ServerContainerType;


/**
 * Utilities for server container
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ServerContainerWraps {
    @Nonnull
    public static ServerContainerType detectContainerType() {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        try {
            if (CollectionPlainWraps.isNotEmpty(server.queryNames(new ObjectName("Catalina:*"), null))) {
                return ServerContainerType.TOMCAT;
            }
            if (CollectionPlainWraps.isNotEmpty(server.queryNames(new ObjectName("org.eclipse.jetty:*"), null))) {
                return ServerContainerType.JETTY;
            }
            if (CollectionPlainWraps.isNotEmpty(server.queryNames(new ObjectName("org.wildfly.extension.undertow:*"), null))) {
                return ServerContainerType.UNDERTOW;
            }
        } catch (Exception ignored) {
        }
        return ServerContainerType.UNKNOWN;
    }

    @Nullable
    public static Set<ObjectName> getTomcatObjectNames() {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            return server.queryNames(new ObjectName("Catalina:type=Connector,*"), null);    // $NON-NLS-1$
        } catch (Exception ignored) {
        }
        return null;
    }

    public static boolean isTomcatPresent() {
        return CollectionPlainWraps.isEmpty(getTomcatObjectNames());
    }

    public static boolean isTomcatSslEnabled() {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> connectors = server.queryNames(new ObjectName("Catalina:type=Connector,*"), null);    // $NON-NLS-1$
            if (CollectionPlainWraps.isEmpty(connectors)) {
                return false;
            }
            for (ObjectName connector : connectors) {
                Boolean secure = ObjectUtilsWraps.castAsBoolean(server.getAttribute(connector, "secure"));    // $NON-NLS-1$
                Boolean SSLEnabled = ObjectUtilsWraps.castAsBoolean(server.getAttribute(connector, "SSLEnabled"));    // $NON-NLS-1$
                if (BooleanUtilsWraps.anyTrue(secure, SSLEnabled)) {
                    return true;
                }
            }
        } catch (Exception ignored) {
        }
        return false;
    }
}
