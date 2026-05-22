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
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import cn.unikue.commonplexus.javaseutil.constant.CharVariantConst;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;


/**
 * Utilities for detecting hardware information via OSHI, with container-awareness.
 * <p>
 * When running inside a Docker or Kubernetes container, host-mapped files are
 * checked first before falling back to native OSHI detection.
 * Ensure the following host paths are volume-mapped into the container:
 * <ul>
 *   <li>{@code /proc/cpuinfo} → {@code /host/proc/cpuinfo}</li>
 *   <li>{@code /sys/class/dmi/id/board_serial} → {@code /host/sys/class/dmi/id/board_serial}</li>
 *   <li>{@code /sys/class/dmi/id/product_serial} → {@code /host/sys/class/dmi/id/product_serial}</li>
 *   <li>{@code /sys/class/net} → {@code /host/sys/class/net}</li>
 *   <li>{@code /sys/block} → {@code /host/sys/block}</li>
 * </ul>
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class OshiDetectWraps {
    private static final String HOST_PROC_CPUINFO = "/host/proc/cpuinfo";    // $NON-NLS-1$
    private static final String HOST_SYS_BOARD_SERIAL = "/host/sys/class/dmi/id/board_serial";    // $NON-NLS-1$
    private static final String HOST_SYS_PRODUCT_SERIAL = "/host/sys/class/dmi/id/product_serial";    // $NON-NLS-1$
    private static final String HOST_SYS_NET_DIR = "/host/sys/class/net";    // $NON-NLS-1$
    private static final String HOST_SYS_BLOCK_DIR = "/host/sys/block";    // $NON-NLS-1$
    private static final String LOOPBACK_IFACE = "lo";    // $NON-NLS-1$
    private static final String CPUINFO_SERIAL_PREFIX = "Serial";    // $NON-NLS-1$

    private static final SystemInfo SYSTEM_INFO = new SystemInfo();

    /**
     * Check if current process is running inside a container (Docker or Kubernetes)
     */
    private static boolean isInContainer() {
        return DockerDetectWraps.isInDocker() || KubernetesDetectWraps.isInKubernetes();
    }

    // ========================================================================
    // CPU Serial Numbers
    // ========================================================================

    /**
     * Retrieve CPU serial numbers.
     * <p>
     * When running in a container, the host {@code /proc/cpuinfo} is attempted first;
     * otherwise OSHI native detection is used.
     *
     * @return list of CPU serial numbers (never {@code null}, may be empty)
     */
    @Nonnull
    public static List<String> getCpuSerials() {
        if (isInContainer()) {
            List<String> hostSerials = detectCpuSerialsFromHost();
            if (CollectionPlainWraps.isNotEmpty(hostSerials)) {
                return hostSerials;
            }
        }
        return detectCpuSerialsFromNative();
    }

    /**
     * Parse CPU serials from host-mapped {@code /proc/cpuinfo}
     */
    @Nonnull
    private static List<String> detectCpuSerialsFromHost() {
        File cpuinfoFile = new File(HOST_PROC_CPUINFO);
        if (!cpuinfoFile.exists() || !cpuinfoFile.canRead()) {
            return Collections.emptyList();
        }
        List<String> serials = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cpuinfoFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(CPUINFO_SERIAL_PREFIX)) {
                    String serial = StringUtils.substringAfter(line, CharVariantConst.COLON);
                    if (StringUtils.isNotBlank(serial)) {
                        serials.add(serial);
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return serials;
    }

    /**
     * Detect CPU serial via OSHI native call
     */
    @Nonnull
    private static List<String> detectCpuSerialsFromNative() {
        try {
            HardwareAbstractionLayer hal = SYSTEM_INFO.getHardware();
            CentralProcessor processor = hal.getProcessor();
            String processorId = processor.getProcessorIdentifier().getProcessorID();
            if (StringUtils.isNotBlank(processorId)) {
                List<String> result = new ArrayList<>();
                result.add(processorId);
                return result;
            }
        } catch (Exception ignored) {
        }
        return Collections.emptyList();
    }

    // ========================================================================
    // Baseboard Serial Numbers
    // ========================================================================

    /**
     * Retrieve baseboard serial numbers.
     * <p>
     * When running in a container, host DMI files are attempted first;
     * otherwise OSHI native detection is used.
     *
     * @return list of baseboard serial numbers (never {@code null}, may be empty)
     */
    @Nonnull
    public static List<String> getBaseboardSerials() {
        if (isInContainer()) {
            List<String> serials = detectBaseboardSerialsFromHost();
            if (CollectionPlainWraps.isNotEmpty(serials)) {
                return serials;
            }
        }
        return detectBaseboardSerialsFromNative();
    }

    /**
     * Read baseboard serials from host-mapped DMI files
     */
    @Nonnull
    private static List<String> detectBaseboardSerialsFromHost() {
        List<String> serials = new ArrayList<>();
        String board = StringUtils.trim(FileUtilsWraps.readFileToString(new File(HOST_SYS_BOARD_SERIAL), StandardCharsets.UTF_8));
        if (StringUtils.isNotBlank(board)) {
            serials.add(board);
        }
        String product = StringUtils.trim(FileUtilsWraps.readFileToString(new File(HOST_SYS_PRODUCT_SERIAL), StandardCharsets.UTF_8));
        if (StringUtils.isNotBlank(product)) {
            serials.add(product);
        }
        return serials;
    }

    /**
     * Detect baseboard serials via OSHI native call
     */
    @Nonnull
    private static List<String> detectBaseboardSerialsFromNative() {
        try {
            HardwareAbstractionLayer hal = SYSTEM_INFO.getHardware();
            String serial = hal.getComputerSystem().getBaseboard().getSerialNumber();
            if (StringUtils.isNotBlank(serial)) {
                return CollectionPlainWraps.newArrayListWithin(serial);
            }
        } catch (Exception ignored) {
        }
        return Collections.emptyList();
    }

    // ========================================================================
    // MAC Addresses
    // ========================================================================

    /**
     * Retrieve MAC addresses of all available network interfaces.
     * <p>
     * When running in a container, host {@code /sys/class/net} is attempted first;
     * otherwise OSHI native detection is used.
     * Loopback interfaces are excluded.
     *
     * @return list of MAC addresses (never {@code null}, may be empty)
     */
    @Nonnull
    public static List<String> getMacAddresses() {
        if (isInContainer()) {
            List<String> hostMacs = detectMacAddressesFromHost();
            if (CollectionPlainWraps.isNotEmpty(hostMacs)) {
                return hostMacs;
            }
        }
        return detectMacAddressesFromNative();
    }

    /**
     * Read MAC addresses from host-mapped {@code /sys/class/net}
     */
    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "DuplicatedCode"})
    private static List<String> detectMacAddressesFromHost() {
        File netDir = new File(HOST_SYS_NET_DIR);
        if (!netDir.exists() || !netDir.isDirectory()) {
            return Collections.emptyList();
        }
        File[] interfaceDirs = netDir.listFiles(File::isDirectory);
        if (ArrayUtils.isEmpty(interfaceDirs)) {
            return Collections.emptyList();
        }
        List<String> macAddresses = new ArrayList<>();
        for (File ifaceDir : interfaceDirs) {
            String ifaceName = ifaceDir.getName();
            if (LOOPBACK_IFACE.equals(ifaceName)) {
                continue;
            }
            File addressFile = new File(ifaceDir, "address");    // $NON-NLS-1$
            String content = StringUtils.trim(FileUtilsWraps.readFileToString(addressFile, StandardCharsets.UTF_8));
            if (StringUtils.isNotBlank(content)) {
                macAddresses.add(content);
            }
        }
        return macAddresses;
    }

    /**
     * Detect MAC addresses via OSHI native call
     */
    @Nonnull
    private static List<String> detectMacAddressesFromNative() {
        try {
            HardwareAbstractionLayer hal = SYSTEM_INFO.getHardware();
            List<NetworkIF> networkIFs = hal.getNetworkIFs();
            if (CollectionPlainWraps.isEmpty(networkIFs)) {
                return Collections.emptyList();
            }
            List<String> macAddresses = new ArrayList<>();
            for (NetworkIF networkIF : networkIFs) {
                String mac = networkIF.getMacaddr();
                if (StringUtils.isNotBlank(mac)) {
                    macAddresses.add(mac);
                }
            }
            return macAddresses;
        } catch (Exception ignored) {
        }
        return Collections.emptyList();
    }

    // ========================================================================
    // Disk Serial Numbers
    // ========================================================================

    /**
     * Retrieve disk serial numbers.
     * <p>
     * When running in a container, host {@code /sys/block} is attempted first;
     * otherwise OSHI native detection is used.
     * Virtual device types ({@code loop}, {@code ram}, {@code dm-}) are excluded.
     *
     * @return list of disk serial numbers (never {@code null}, may be empty)
     */
    @Nonnull
    public static List<String> getDiskSerials() {
        if (isInContainer()) {
            List<String> hostSerials = detectDiskSerialsFromHost();
            if (CollectionPlainWraps.isNotEmpty(hostSerials)) {
                return hostSerials;
            }
        }
        List<String> nativeSerials = detectDiskSerialsFromNative();
        if (CollectionPlainWraps.isNotEmpty(nativeSerials)) {
            return nativeSerials;
        }
        // macOS fallback: OSHI requires root for NVMe serials,
        // system_profiler works without elevated privileges.
        if (SystemUtils.IS_OS_MAC) {
            return detectDiskSerialsOnMac();
        }
        return Collections.emptyList();
    }

    /**
     * Read disk serials from host-mapped {@code /sys/block/<dev>/device/serial}
     */
    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "DuplicatedCode"})
    private static List<String> detectDiskSerialsFromHost() {
        File blockDir = new File(HOST_SYS_BLOCK_DIR);
        if (!blockDir.exists() || !blockDir.isDirectory()) {
            return Collections.emptyList();
        }
        File[] deviceDirs = blockDir.listFiles(File::isDirectory);
        if (ArrayUtils.isEmpty(deviceDirs)) {
            return Collections.emptyList();
        }
        List<String> serials = new ArrayList<>();
        for (File devDir : deviceDirs) {
            String devName = devDir.getName();
            if (StringUtils.startsWithAny(devName, "loop", "ram", "dm-")) {    // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$
                continue;
            }
            File serialFile = new File(devDir, "device/serial");    // $NON-NLS-1$
            String content = StringUtils.trim(FileUtilsWraps.readFileToString(serialFile, StandardCharsets.UTF_8));
            if (StringUtils.isNotBlank(content)) {
                serials.add(content);
            }
        }
        return serials;
    }

    /**
     * Detect disk serials via OSHI native call
     */
    @Nonnull
    private static List<String> detectDiskSerialsFromNative() {
        try {
            HardwareAbstractionLayer hal = SYSTEM_INFO.getHardware();
            List<HWDiskStore> diskStores = hal.getDiskStores();
            if (CollectionPlainWraps.isEmpty(diskStores)) {
                return Collections.emptyList();
            }
            List<String> serials = new ArrayList<>();
            for (HWDiskStore disk : diskStores) {
                String serial = disk.getSerial();
                if (StringUtils.isNotBlank(serial)) {
                    serials.add(serial);
                }
            }
            return serials;
        } catch (Exception ignored) {
        }
        return Collections.emptyList();
    }

    /**
     * macOS-specific disk serial detection via {@code system_profiler SPNVMeDataType}.
     * Used as fallback when OSHI native fails (OSHI needs root for NVMe serials on macOS).
     */
    @Nonnull
    private static List<String> detectDiskSerialsOnMac() {
        try {
            Process process = new ProcessBuilder("system_profiler", "SPNVMeDataType").redirectErrorStream(true).start();    // $NON-NLS-1$ // $NON-NLS-2$
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                List<String> serials = new ArrayList<>();
                String prefix = "Serial Number:";    // $NON-NLS-1$
                String line;
                while ((line = reader.readLine()) != null) {
                    String trimmed = line.trim();
                    if (StringUtils.startsWithIgnoreCase(trimmed, prefix)) {
                        String serial = StringUtilsWraps.substringAfterIgnoreCase(trimmed, prefix);
                        if (StringUtils.isNotBlank(serial)) {
                            serials.add(serial);
                        }
                    }
                }
                process.waitFor();
                return serials;
            }
        } catch (Exception ignored) {
        }
        return Collections.emptyList();
    }
}
