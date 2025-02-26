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


import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import jakarta.annotation.Nullable;
import com.yookue.commonplexus.javaseutil.enumeration.InetProxyType;


/**
 * Utilities for {@link java.net.Proxy}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class InetProxyWraps {
    @Nullable
    public static Proxy ofHttpProxy(@Nullable String host, int port) {
        return ofProxy(host, port, InetProxyType.HTTP);
    }

    @Nullable
    public static Proxy ofSocksProxy(@Nullable String host, int port) {
        return ofProxy(host, port, InetProxyType.SOCKS);
    }

    @Nullable
    public static Proxy ofProxy(@Nullable String host, int port, @Nullable InetProxyType type) {
        if (type == null) {
            return null;
        }
        InetSocketAddress address = InetSocketWraps.newInetSocketAddress(host, port);
        if (address == null) {
            return null;
        }
        return switch (type) {
            case HTTP -> new Proxy(Proxy.Type.HTTP, address);
            case SOCKS -> new Proxy(Proxy.Type.SOCKS, address);
        };
    }

    @Nullable
    public static ProxySelector ofProxySelector(@Nullable String host, int port) {
        InetSocketAddress address = InetSocketWraps.newInetSocketAddress(host, port);
        return (address == null) ? null : ProxySelector.of(address);
    }
}
