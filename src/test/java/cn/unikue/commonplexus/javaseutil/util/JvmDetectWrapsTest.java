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


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;


/**
 * Tests for {@link cn.unikue.commonplexus.javaseutil.util.JvmDetectWraps}
 *
 * @author David Hsing
 */
@Slf4j
class JvmDetectWrapsTest {
    @Test
    void getJvmStatus() {
        String status = JvmDetectWraps.getJvmStatus();
        log.info("{}:\n{}", StackTraceWraps.getExecutingMethodName(), status);
        Assertions.assertTrue(StringUtils.isNotBlank(status));
        Assertions.assertTrue(status.contains("=== JVM Status ==="));
        Assertions.assertTrue(status.contains("Heap Memory:"));
        Assertions.assertTrue(status.contains("Non-Heap Memory:"));
        Assertions.assertTrue(status.contains("Threads:"));
        Assertions.assertTrue(status.contains("GC:"));
        Assertions.assertTrue(status.contains("Classes:"));
    }

    @Test
    void getJvmRuntimeInfo() {
        String runtimeInfo = JvmDetectWraps.getJvmRuntimeInfo();
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), runtimeInfo);
        Assertions.assertTrue(StringUtils.isNotBlank(runtimeInfo));
        Assertions.assertTrue(runtimeInfo.contains("JVM:"));
        Assertions.assertTrue(runtimeInfo.contains("name="));
        Assertions.assertTrue(runtimeInfo.contains("version="));
        Assertions.assertTrue(runtimeInfo.contains("vendor="));
    }

    @Test
    void getJvmHeapMemoryInfo() {
        String heapInfo = JvmDetectWraps.getJvmHeapMemoryInfo();
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), heapInfo);
        Assertions.assertTrue(StringUtils.isNotBlank(heapInfo));
        Assertions.assertTrue(heapInfo.contains("Heap Memory:"));
        Assertions.assertTrue(heapInfo.contains("used="));
        Assertions.assertTrue(heapInfo.contains("committed="));
        Assertions.assertTrue(heapInfo.contains("max="));
    }

    @Test
    void getJvmThreadInfo() {
        String threadInfo = JvmDetectWraps.getJvmThreadInfo();
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), threadInfo);
        Assertions.assertTrue(StringUtils.isNotBlank(threadInfo));
        Assertions.assertTrue(threadInfo.contains("Threads:"));
        Assertions.assertTrue(threadInfo.contains("live="));
        Assertions.assertTrue(threadInfo.contains("daemon="));
        Assertions.assertTrue(threadInfo.contains("peak="));
    }

    @Test
    void getJvmGcInfo() {
        String gcInfo = JvmDetectWraps.getJvmGcInfo();
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), gcInfo);
        Assertions.assertTrue(StringUtils.isNotBlank(gcInfo));
        Assertions.assertTrue(gcInfo.contains("GC:"));
        Assertions.assertTrue(gcInfo.contains("collectors="));
        Assertions.assertTrue(gcInfo.contains("totalCollections="));
    }

    @Test
    void getJvmClassLoadingInfo() {
        String classInfo = JvmDetectWraps.getJvmClassLoadingInfo();
        log.info("{}: {}", StackTraceWraps.getExecutingMethodName(), classInfo);
        Assertions.assertTrue(StringUtils.isNotBlank(classInfo));
        Assertions.assertTrue(classInfo.contains("Classes:"));
        Assertions.assertTrue(classInfo.contains("loaded="));
        Assertions.assertTrue(classInfo.contains("totalLoaded="));
        Assertions.assertTrue(classInfo.contains("unloaded="));
    }

    @Test
    void getJvmMemoryValues() {
        long totalMemory = JvmDetectWraps.getJvmTotalMemory();
        long freeMemory = JvmDetectWraps.getJvmFreeMemory();
        long usedMemory = JvmDetectWraps.getJvmUsedMemory();
        long maxMemory = JvmDetectWraps.getJvmMaxMemory();
        log.info("{}: total={}, free={}, used={}, max={}", 
                StackTraceWraps.getExecutingMethodName(), 
                FilePlainWraps.sizeToHumanReadable(totalMemory),
                FilePlainWraps.sizeToHumanReadable(freeMemory),
                FilePlainWraps.sizeToHumanReadable(usedMemory),
                FilePlainWraps.sizeToHumanReadable(maxMemory));
        Assertions.assertTrue(totalMemory > 0);
        Assertions.assertTrue(freeMemory > 0);
        Assertions.assertTrue(usedMemory > 0);
        Assertions.assertTrue(maxMemory > 0);
        Assertions.assertEquals(totalMemory, usedMemory + freeMemory);
        Assertions.assertTrue(maxMemory >= totalMemory);
    }

    @Test
    void getJvmBasicInfo() {
        String jvmName = JvmDetectWraps.getJvmName();
        String jvmVersion = JvmDetectWraps.getJvmVersion();
        String jvmVendor = JvmDetectWraps.getJvmVendor();
        String javaVersion = JvmDetectWraps.getJavaVersion();
        log.info("{}: name={}, version={}, vendor={}, javaVersion={}", StackTraceWraps.getExecutingMethodName(), jvmName, jvmVersion, jvmVendor, javaVersion);
        Assertions.assertTrue(StringUtils.isNotBlank(jvmName));
        Assertions.assertTrue(StringUtils.isNotBlank(jvmVersion));
        Assertions.assertTrue(StringUtils.isNotBlank(jvmVendor));
        Assertions.assertTrue(StringUtils.isNotBlank(javaVersion));
    }

    @Test
    void getJvmUptime() {
        long uptime = JvmDetectWraps.getJvmUptime();
        long startTime = JvmDetectWraps.getJvmStartTime();
        log.info("{}: uptime={}ms, startTime={}", StackTraceWraps.getExecutingMethodName(), uptime, startTime);
        Assertions.assertTrue(uptime >= 0);
        Assertions.assertTrue(startTime > 0);
    }
}
