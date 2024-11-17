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
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;
import com.yookue.commonplexus.javaseutil.constant.InetAddressConst;
import com.yookue.commonplexus.javaseutil.constant.RegexVariantConst;


/**
 * Utilities for {@link java.net.InetAddress}
 *
 * @author David Hsing
 * @see "org.apache.http.conn.util.InetAddressWraps"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class InetAddressWraps {
    @Nullable
    public static byte[] getAddressBytes(@Nullable String ipAddress) {
        InetAddress address = getByName(ipAddress);
        return (address == null) ? null : address.getAddress();
    }

    @Nullable
    public static InetAddress[] getAllByName(@Nullable String host) {
        if (StringUtils.isBlank(host)) {
            return null;
        }
        try {
            return InetAddress.getAllByName(host);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static InetAddress getByAddress(@Nullable byte[] ipAddress) {
        return getByAddress(null, ipAddress);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static InetAddress getByAddress(@Nullable String host, @Nullable byte[] ipAddress) {
        if (ArrayUtils.isEmpty(ipAddress)) {
            return null;
        }
        try {
            return InetAddress.getByAddress(host, ipAddress);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static InetAddress getByName(@Nullable String host) {
        if (StringUtils.isBlank(host)) {
            return null;
        }
        try {
            return InetAddress.getByName(host);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static String getLocalIpAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    @Nullable
    public static String getLocalIpAddressQuietly() {
        try {
            return getLocalIpAddress();
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static List<String> getLocalMacAddress() throws SocketException {
        List<String> result = new ArrayList<>();
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface interfacing = interfaces.nextElement();
            if (interfacing.isLoopback() || interfacing.isVirtual() || interfacing.isPointToPoint() || !interfacing.isUp()) {
                continue;
            }
            byte[] address = interfacing.getHardwareAddress();
            if (ArrayUtils.isEmpty(address)) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < address.length; i++) {
                builder.append(String.format("%02X%s", address[i], (i < address.length - 1) ? CharVariantConst.HYPHEN : StringUtils.EMPTY));    // $NON-NLS-1$
            }
            result.add(builder.toString());
        }
        return result.isEmpty() ? null : result.stream().distinct().collect(Collectors.toList());
    }

    @Nullable
    public static List<String> getLocalMacAddressQuietly() {
        try {
            return getLocalMacAddress();
        } catch (Exception ignored) {
        }
        return null;
    }

    public static boolean isLanAddress(@Nullable String ipAddress) {
        return StringUtils.isNotBlank(ipAddress) && Pattern.matches(RegexVariantConst.LAN_ADDRESS_IPV4, ipAddress);
    }

    /**
     * Returns an ip address of LAN from localhost one
     *
     * @param ipAddress the localhost ip address, such as '127.0.0.1'
     *
     * @return an ip address of LAN from localhost one
     */
    public static String toLanAddress(@Nullable String ipAddress) throws UnknownHostException {
        return StringUtils.equalsAny(ipAddress, InetAddressConst.LOCALHOST_IPV4, InetAddressConst.LOCALHOST_IPV6) ? getLocalIpAddress() : ipAddress;
    }

    @Nullable
    public static String toLanAddressQuietly(@Nullable String ipAddress) {
        try {
            return toLanAddress(ipAddress);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Returns a {@link String} that represents an inet4 address from it's {@link Long} representation
     *
     * @param inet4 the address of {@link Long} representation
     *
     * @return a {@link String} that represents an inet4 address from it's {@link Long} representation
     */
    @Nullable
    public static String toInet4ByLong(Long inet4) {
        if (NumberUtilsWraps.isNotPositive(inet4)) {
            return null;
        }
        long octet3 = (inet4 >> 24) % 256;
        long octet2 = (inet4 >> 16) % 256;
        long octet1 = (inet4 >> 8) % 256;
        long octet0 = inet4 % 256;
        return String.format("%d.%d.%d.%d", octet3, octet2, octet1, octet0);    // $NON-NLS-1$
    }

    /**
     * Returns a {@link String} that represents an inet4 address with port from it's {@link Long} representation
     *
     * @param inet4Port the address with port of {@link Long} representation
     *
     * @return a {@link String} that represents an inet4 address with port from it's {@link Long} representation
     */
    @Nullable
    public static String toInet4PortByLong(Long inet4Port) {
        if (NumberUtilsWraps.isNotPositive(inet4Port)) {
            return null;
        }
        long octet4 = (inet4Port >> 40) % 256;
        long octet3 = (inet4Port >> 32) % 256;
        long octet2 = (inet4Port >> 24) % 256;
        long octet1 = (inet4Port >> 16) % 256;
        long octet0 = inet4Port % 65536;
        return String.format("%d.%d.%d.%d:%d", octet4, octet3, octet2, octet1, octet0);    // $NON-NLS-1$
    }

    /**
     * Returns a {@link Long} that represents an inet4 address from it's {@link String} representation
     *
     * @param inet4 the address of {@link String} representation
     *
     * @return a {@link Long} that represents an inet4 address from it's {@link String} representation
     */
    @Nullable
    public static Long toLongByInet4(@Nullable String inet4) throws NumberFormatException {
        if (StringUtils.isBlank(inet4)) {
            return null;
        }
        String[] octets = StringUtils.split(inet4, CharVariantConst.DOT);
        if (ArrayUtils.getLength(octets) == 4 && StringUtils.isNoneBlank(octets)) {
            return (Long.parseLong(octets[0]) << 24) + (Long.parseLong(octets[1]) << 16) + (Long.parseLong(octets[2]) << 8) + Long.parseLong(octets[3]);
        }
        return null;
    }

    /**
     * Returns a {@link Long} that represents an inet4 address with port from it's {@link String} representation
     *
     * @param inet4 the address of {@link String} representation
     * @param port the port number
     *
     * @return a {@link Long} that represents an inet4 address with port from it's {@link String} representation
     */
    @Nullable
    public static Long toLongByInet4Port(@Nullable String inet4, int port) throws NumberFormatException {
        if (StringUtils.isBlank(inet4) || port <= 0) {
            return null;
        }
        String[] octets = StringUtils.split(inet4, CharVariantConst.DOT);
        if (ArrayUtils.getLength(octets) == 4 && StringUtils.isNoneBlank(octets)) {
            return (Long.parseLong(octets[0]) << 40) + (Long.parseLong(octets[1]) << 32) + (Long.parseLong(octets[2]) << 24) + (Long.parseLong(octets[3]) << 16) + port;
        }
        return null;
    }
}
