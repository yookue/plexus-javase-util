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


import java.util.Arrays;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.SymbolVariantConst;
import com.yookue.commonplexus.javaseutil.enumeration.InetProtocolType;


/**
 * Utilities for networking protocols in session layer
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class InetProtocolWraps {
    public static boolean isHttpPath(@Nullable String path) {
        return isProtocol(path, InetProtocolType.HTTP);
    }

    public static boolean isHttpsPath(@Nullable String path) {
        return isProtocol(path, InetProtocolType.HTTPS);
    }

    public static boolean isFtpPath(@Nullable String path) {
        return isProtocol(path, InetProtocolType.FTP);
    }

    public static boolean isSftpPath(@Nullable String path) {
        return isProtocol(path, InetProtocolType.SFTP);
    }

    public static boolean isProtocol(@Nullable String path, @Nullable InetProtocolType type) {
        return type != null && StringUtils.startsWithIgnoreCase(path, StringUtils.join(type.getValue(), SymbolVariantConst.PROTOCOL_DELIMITER));
    }

    public static boolean startsWithProtocol(@Nullable String path) {
        return StringUtils.isNotBlank(path) && Arrays.stream(InetProtocolType.class.getEnumConstants()).anyMatch(element -> StringUtils.startsWithIgnoreCase(path, StringUtils.join(element.getValue(), SymbolVariantConst.PROTOCOL_DELIMITER)));
    }

    public static String removeProtocolPrefix(@Nullable String text) {
        return StringUtils.substringAfter(text, SymbolVariantConst.PROTOCOL_DELIMITER);
    }
}
