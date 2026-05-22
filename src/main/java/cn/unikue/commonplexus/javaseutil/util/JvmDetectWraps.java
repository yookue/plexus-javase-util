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


import java.lang.management.ClassLoadingMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;
import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;


/**
 * Utilities for detecting JVM runtime information
 * <p>
 * This class provides methods to retrieve various JVM runtime metrics including:
 * <ul>
 *   <li>Memory usage (heap and non-heap)</li>
 *   <li>Thread statistics</li>
 *   <li>Garbage collection information</li>
 *   <li>Class loading statistics</li>
 *   <li>JVM version and vendor details</li>
 *   <li>Uptime and start time</li>
 * </ul>
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted"})
public abstract class JvmDetectWraps {
    private static final Runtime RUNTIME = Runtime.getRuntime();
    private static final MemoryMXBean MEMORY_MX_BEAN = ManagementFactory.getMemoryMXBean();
    private static final ThreadMXBean THREAD_MX_BEAN = ManagementFactory.getThreadMXBean();
    private static final ClassLoadingMXBean CLASS_LOADING_MX_BEAN = ManagementFactory.getClassLoadingMXBean();
    private static final RuntimeMXBean RUNTIME_MX_BEAN = ManagementFactory.getRuntimeMXBean();
    private static final List<GarbageCollectorMXBean> GC_MX_BEANS = ManagementFactory.getGarbageCollectorMXBeans();

    // ========================================================================
    // JVM Memory Information
    // ========================================================================

    /**
     * Retrieve the total amount of memory (in bytes) currently allocated to the JVM heap.
     * This is the committed memory from the operating system, which includes both used and free memory within the allocated heap.
     * <p>
     * Note: This value represents the heap size that JVM has requested from OS, not the actual memory being used by objects.
     * It is equivalent to {@link MemoryUsage#getCommitted()} from {@link #getJvmHeapMemoryUsage()}.
     *
     * @return total heap memory in bytes (committed memory)
     * @see #getJvmFreeMemory()
     * @see #getJvmUsedMemory()
     * @see #getJvmHeapMemoryUsage()
     */
    public static long getJvmTotalMemory() {
        return RUNTIME.totalMemory();
    }

    /**
     * Retrieve the amount of free memory (in bytes) available in the JVM heap.
     * This is the unused portion within the currently allocated heap (totalMemory).
     * <p>
     * Note: Free memory = totalMemory - usedMemory. The JVM may request more memory from OS when needed.
     *
     * @return free heap memory in bytes
     * @see #getJvmTotalMemory()
     * @see #getJvmUsedMemory()
     */
    public static long getJvmFreeMemory() {
        return RUNTIME.freeMemory();
    }

    /**
     * Retrieve the maximum amount of memory (in bytes) that the JVM will attempt to use for the heap.
     * This is the upper limit of heap size, configured via -Xmx parameter.
     * Returns {@code -1} if there is no inherent limit (unlikely in modern JVMs).
     * <p>
     * Note: The JVM heap can grow from totalMemory up to maxMemory as needed.
     *
     * @return maximum heap memory in bytes, or {@code -1} if undefined
     * @see #getJvmTotalMemory()
     */
    public static long getJvmMaxMemory() {
        return RUNTIME.maxMemory();
    }

    /**
     * Retrieve the amount of used memory (in bytes) in the JVM heap.
     * Calculated as: totalMemory - freeMemory
     * <p>
     * Note: This represents the approximate memory consumed within the committed heap.
     * For more precise object memory usage, consider using {@link #getJvmHeapMemoryUsage()} which provides
     * detailed memory metrics from the MemoryMXBean.
     *
     * @return used heap memory in bytes
     * @see #getJvmTotalMemory()
     * @see #getJvmFreeMemory()
     * @see #getJvmHeapMemoryUsage()
     */
    public static long getJvmUsedMemory() {
        return getJvmTotalMemory() - getJvmFreeMemory();
    }

    /**
     * Retrieve the heap memory usage details from MemoryMXBean.
     * This provides comprehensive heap memory metrics including:
     * <ul>
     *   <li>init: Initial memory requested from OS at JVM startup</li>
     *   <li>used: Current memory used by objects (more accurate than Runtime.usedMemory)</li>
     *   <li>committed: Memory guaranteed to be available (equivalent to Runtime.totalMemory)</li>
     *   <li>max: Maximum memory that can be used (equivalent to Runtime.maxMemory)</li>
     * </ul>
     * <p>
     * Note: The 'used' value from this method may differ from {@link #getJvmUsedMemory()} because
     * it uses a different measurement approach based on actual object allocation vs. heap calculation.
     *
     * @return memory usage object containing init, used, committed, and max values
     * @see java.lang.management.MemoryUsage
     */
    @Nonnull
    public static MemoryUsage getJvmHeapMemoryUsage() {
        return MEMORY_MX_BEAN.getHeapMemoryUsage();
    }

    /**
     * Retrieve the non-heap memory usage details from MemoryMXBean.
     * Non-heap memory includes:
     * <ul>
     *   <li>Metaspace (class metadata, replaced PermGen in Java 8+)</li>
     *   <li>Code cache (JIT compiled code)</li>
     *   <li>Compressed class space</li>
     *   <li>Thread stacks and native buffers</li>
     * </ul>
     * <p>
     * Note: Non-heap memory is not subject to garbage collection in the same way as heap memory.
     *
     * @return memory usage object containing init, used, committed, and max values
     * @see java.lang.management.MemoryUsage
     */
    @Nonnull
    public static MemoryUsage getJvmNonHeapMemoryUsage() {
        return MEMORY_MX_BEAN.getNonHeapMemoryUsage();
    }

    /**
     * Retrieve the heap memory usage as a human-readable string.
     *
     * @return formatted string showing used/total/max memory
     */
    @Nonnull
    public static String getJvmHeapMemoryInfo() {
        MemoryUsage usage = getJvmHeapMemoryUsage();
        String used = FilePlainWraps.sizeToHumanReadable(usage.getUsed());
        String committed = FilePlainWraps.sizeToHumanReadable(usage.getCommitted());
        String max = FilePlainWraps.sizeToHumanReadable(usage.getMax());
        return String.format("Heap Memory: used=%s, committed=%s, max=%s", used, committed, max);    // $NON-NLS-1$
    }

    /**
     * Retrieve the non-heap memory usage as a human-readable string.
     *
     * @return formatted string showing used/committed/max memory
     */
    @Nonnull
    public static String getJvmNonHeapMemoryInfo() {
        MemoryUsage usage = getJvmNonHeapMemoryUsage();
        String used = FilePlainWraps.sizeToHumanReadable(usage.getUsed());
        String committed = FilePlainWraps.sizeToHumanReadable(usage.getCommitted());
        String max = FilePlainWraps.sizeToHumanReadable(usage.getMax());
        return String.format("Non-Heap Memory: used=%s, committed=%s, max=%s", used, committed, max);
    }

    // ========================================================================
    // JVM Thread Information
    // ========================================================================

    /**
     * Retrieve the current number of live threads including both daemon and non-daemon threads.
     *
     * @return current thread count
     */
    public static int getJvmThreadCount() {
        return THREAD_MX_BEAN.getThreadCount();
    }

    /**
     * Retrieve the peak number of live threads since the JVM started.
     *
     * @return peak thread count
     */
    public static int getJvmPeakThreadCount() {
        return THREAD_MX_BEAN.getPeakThreadCount();
    }

    /**
     * Retrieve the current number of daemon threads.
     *
     * @return daemon thread count
     */
    public static int getJvmDaemonThreadCount() {
        return THREAD_MX_BEAN.getDaemonThreadCount();
    }

    /**
     * Retrieve the total number of threads created and started since the JVM started.
     *
     * @return total thread count
     */
    public static long getJvmTotalStartedThreadCount() {
        return THREAD_MX_BEAN.getTotalStartedThreadCount();
    }

    /**
     * Retrieve thread information as a human-readable string.
     *
     * @return formatted string showing thread statistics
     */
    @Nonnull
    public static String getJvmThreadInfo() {
        long liveCount = getJvmThreadCount();
        long daemonCount = getJvmDaemonThreadCount();
        long peakCount = getJvmPeakThreadCount();
        long totalStartedCount = getJvmTotalStartedThreadCount();
        return String.format("Threads: live=%d, daemon=%d, peak=%d, totalStarted=%d", liveCount, daemonCount, peakCount, totalStartedCount);
    }

    // ========================================================================
    // JVM Garbage Collection Information
    // ========================================================================

    /**
     * Retrieve the total number of garbage collections that have occurred.
     *
     * @return total GC count across all collectors
     */
    public static long getJvmTotalGcCount() {
        long totalCount = 0;
        for (GarbageCollectorMXBean gcBean : GC_MX_BEANS) {
            totalCount += gcBean.getCollectionCount();
        }
        return totalCount;
    }

    /**
     * Retrieve the total time (in milliseconds) spent in garbage collection.
     *
     * @return total GC time in milliseconds
     */
    public static long getJvmTotalGcTime() {
        long totalTime = 0;
        for (GarbageCollectorMXBean gcBean : GC_MX_BEANS) {
            totalTime += gcBean.getCollectionTime();
        }
        return totalTime;
    }

    /**
     * Retrieve the number of garbage collectors available.
     *
     * @return GC collector count
     */
    public static int getJvmGcCollectorCount() {
        return GC_MX_BEANS.size();
    }

    /**
     * Retrieve garbage collection information as a human-readable string.
     *
     * @return formatted string showing GC statistics
     */
    @Nonnull
    public static String getJvmGcInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("GC: collectors=%d, totalCollections=%d, totalTime=%dms", getJvmGcCollectorCount(), getJvmTotalGcCount(), getJvmTotalGcTime()));
        for (GarbageCollectorMXBean gcBean : GC_MX_BEANS) {
            sb.append(String.format(", %s(count=%d, time=%dms)", gcBean.getName(), gcBean.getCollectionCount(), gcBean.getCollectionTime()));
        }
        return sb.toString();
    }

    // ========================================================================
    // JVM Class Loading Information
    // ========================================================================

    /**
     * Retrieve the current number of loaded classes.
     *
     * @return loaded class count
     */
    public static int getJvmLoadedClassCount() {
        return CLASS_LOADING_MX_BEAN.getLoadedClassCount();
    }

    /**
     * Retrieve the total number of classes loaded since the JVM started.
     *
     * @return total loaded class count
     */
    public static long getJvmTotalLoadedClassCount() {
        return CLASS_LOADING_MX_BEAN.getTotalLoadedClassCount();
    }

    /**
     * Retrieve the total number of classes unloaded since the JVM started.
     *
     * @return total unloaded class count
     */
    public static long getJvmUnloadedClassCount() {
        return CLASS_LOADING_MX_BEAN.getUnloadedClassCount();
    }

    /**
     * Retrieve class loading information as a human-readable string.
     *
     * @return formatted string showing class loading statistics
     */
    @Nonnull
    public static String getJvmClassLoadingInfo() {
        return String.format("Classes: loaded=%d, totalLoaded=%d, unloaded=%d", getJvmLoadedClassCount(), getJvmTotalLoadedClassCount(), getJvmUnloadedClassCount());    // $NON-NLS-1$
    }

    // ========================================================================
    // JVM Runtime Information
    // ========================================================================

    /**
     * Retrieve the JVM name.
     *
     * @return JVM name (e.g., "Java HotSpot(TM) 64-Bit Server VM")
     */
    @Nonnull
    public static String getJvmName() {
        return RUNTIME_MX_BEAN.getVmName();
    }

    /**
     * Retrieve the JVM version.
     *
     * @return JVM version (e.g., "17.0.1+12-LTS")
     */
    @Nonnull
    public static String getJvmVersion() {
        return RUNTIME_MX_BEAN.getVmVersion();
    }

    /**
     * Retrieve the JVM vendor.
     *
     * @return JVM vendor (e.g., "Oracle Corporation")
     */
    @Nonnull
    public static String getJvmVendor() {
        return RUNTIME_MX_BEAN.getVmVendor();
    }

    /**
     * Retrieve the JVM specification version.
     *
     * @return JVM spec version (e.g., "17")
     */
    @Nonnull
    public static String getJvmSpecVersion() {
        return RUNTIME_MX_BEAN.getSpecVersion();
    }

    /**
     * Retrieve the Java runtime version.
     *
     * @return Java version (e.g., "17.0.1")
     */
    @Nonnull
    public static String getJavaVersion() {
        return SystemUtils.JAVA_VERSION;
    }

    /**
     * Retrieve the Java runtime specification version.
     *
     * @return Java spec version (e.g., "17")
     */
    @Nonnull
    public static String getJavaSpecVersion() {
        return SystemUtils.JAVA_SPECIFICATION_VERSION;
    }

    /**
     * Retrieve the JVM start time (in milliseconds since epoch).
     *
     * @return start time in milliseconds
     */
    public static long getJvmStartTime() {
        return RUNTIME_MX_BEAN.getStartTime();
    }

    /**
     * Retrieve the JVM uptime (in milliseconds).
     *
     * @return uptime in milliseconds
     */
    public static long getJvmUptime() {
        return RUNTIME_MX_BEAN.getUptime();
    }

    /**
     * Retrieve the input arguments passed to the JVM.
     *
     * @return list of JVM arguments
     */
    @Nonnull
    public static List<String> getJvmInputArguments() {
        return RUNTIME_MX_BEAN.getInputArguments();
    }

    /**
     * Retrieve the class path used by the JVM.
     *
     * @return class path string
     */
    @Nonnull
    public static String getJvmClassPath() {
        return RUNTIME_MX_BEAN.getClassPath();
    }

    /**
     * Retrieve the library path used by the JVM.
     *
     * @return library path string
     */
    @Nonnull
    public static String getJvmLibraryPath() {
        return RUNTIME_MX_BEAN.getLibraryPath();
    }

    /**
     * Retrieve comprehensive JVM information as a human-readable string.
     *
     * @return formatted string showing JVM details
     */
    @Nonnull
    public static String getJvmRuntimeInfo() {
        return String.format("JVM: name=%s, version=%s, vendor=%s, specVersion=%s, uptime=%dms", getJvmName(), getJvmVersion(), getJvmVendor(), getJvmSpecVersion(), getJvmUptime());    // $NON-NLS-1$
    }

    // ========================================================================
    // Comprehensive JVM Status
    // ========================================================================

    /**
     * Retrieve comprehensive JVM status information including memory, threads, GC, and class loading.
     *
     * @return formatted multi-line string with complete JVM status
     */
    @Nonnull
    public static String getJvmStatus() {
        String separator = System.lineSeparator();
        return StringUtils.joinWith(separator, "=== JVM Status ===", getJvmRuntimeInfo(), getJvmHeapMemoryInfo(), getJvmNonHeapMemoryInfo(), getJvmThreadInfo(), getJvmGcInfo(), getJvmClassLoadingInfo());    // $NON-NLS-1$
    }

    /**
     * Check if the current JVM is running in debug mode.
     *
     * @return true if debug mode is enabled, false otherwise
     */
    public static boolean isJvmDebugMode() {
        List<String> inputArgs = getJvmInputArguments();
        return inputArgs.stream().anyMatch(arg -> StringUtils.containsAnyIgnoreCase(arg, "-agentlib:jdwp", "-Xdebug"));    // $NON-NLS-1$ // $NON-NLS-2$
    }
}
