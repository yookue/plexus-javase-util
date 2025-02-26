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


import java.net.InetAddress;
import java.net.InetSocketAddress;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link java.net.Socket}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class InetSocketWraps {
    @Nullable
    public static InetSocketAddress ofSocketAddress(@Nullable String host, int port) {
        if (StringUtils.isBlank(host) || port <= 0) {
            return null;
        }
        try {
            return new InetSocketAddress(InetAddress.getByName(host), port);
        } catch (Exception ignored) {
        }
        return null;
    }
}
