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


import java.util.Arrays;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import cn.unikue.commonplexus.javaseutil.constant.SymbolVariantConst;
import cn.unikue.commonplexus.javaseutil.enumeration.InetProtocolType;


/**
 * Utilities for networking protocols in session layer
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class InetProtocolWraps {
    /**
     * Checks if the path uses HTTP protocol
     *
     * @param path the path to check
     *
     * @return {@code true} if the path starts with http://, {@code false} otherwise
     */
    public static boolean isHttpPath(@Nullable String path) {
        return isProtocol(path, InetProtocolType.HTTP);
    }

    /**
     * Checks if the path uses HTTPS protocol
     *
     * @param path the path to check
     *
     * @return {@code true} if the path starts with https://, {@code false} otherwise
     */
    public static boolean isHttpsPath(@Nullable String path) {
        return isProtocol(path, InetProtocolType.HTTPS);
    }

    /**
     * Checks if the path uses FTP protocol
     *
     * @param path the path to check
     *
     * @return {@code true} if the path starts with ftp://, {@code false} otherwise
     */
    public static boolean isFtpPath(@Nullable String path) {
        return isProtocol(path, InetProtocolType.FTP);
    }

    /**
     * Checks if the path uses SFTP protocol
     *
     * @param path the path to check
     *
     * @return {@code true} if the path starts with sftp://, {@code false} otherwise
     */
    public static boolean isSftpPath(@Nullable String path) {
        return isProtocol(path, InetProtocolType.SFTP);
    }

    /**
     * Checks if the path uses the specified protocol
     *
     * @param path the path to check
     * @param type the protocol type to check for
     *
     * @return {@code true} if the path starts with the specified protocol, {@code false} otherwise
     */
    public static boolean isProtocol(@Nullable String path, @Nullable InetProtocolType type) {
        return type != null && StringUtils.startsWithIgnoreCase(path, StringUtils.join(type.getValue(), SymbolVariantConst.PROTOCOL_DELIMITER));
    }

    /**
     * Checks if the path starts with any known protocol prefix
     *
     * @param path the path to check
     *
     * @return {@code true} if the path starts with a protocol prefix (e.g., http://, https://, ftp://, etc.), {@code false} otherwise
     */
    public static boolean startsWithProtocol(@Nullable String path) {
        return StringUtils.isNotBlank(path) && Arrays.stream(InetProtocolType.class.getEnumConstants()).anyMatch(item -> StringUtils.startsWithIgnoreCase(path, StringUtils.join(item.getValue(), SymbolVariantConst.PROTOCOL_DELIMITER)));
    }

    /**
     * Removes the protocol prefix from the text
     *
     * @param text the text to process
     *
     * @return the text without the protocol prefix, or the original text if no protocol prefix is found
     */
    public static String removeProtocolPrefix(@Nullable String text) {
        return StringUtils.substringAfter(text, SymbolVariantConst.PROTOCOL_DELIMITER);
    }
}
