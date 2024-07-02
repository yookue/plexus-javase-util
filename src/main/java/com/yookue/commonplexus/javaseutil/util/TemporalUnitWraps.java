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


import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationUtils;


/**
 * Utilities for {@link java.time.temporal.TemporalUnit}
 *
 * @author David Hsing
 * @see java.time.temporal.TemporalUnit
 * @see java.time.temporal.ChronoUnit
 * @see java.util.concurrent.TimeUnit
 * @see "org.springframework.data.redis.core.TimeoutUtils"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "JavadocReference"})
public abstract class TemporalUnitWraps {
    @Nullable
    public static ChronoUnit toChronoUnit(@Nullable CharSequence sequence) {
        if (StringUtils.isBlank(sequence)) {
            return null;
        }
        ChronoUnit[] units = ChronoUnit.class.getEnumConstants();
        return Arrays.stream(units).filter(element -> StringUtils.equalsIgnoreCase(element.name(), sequence)).findFirst().orElse(null);
    }

    /**
     * @see org.apache.commons.lang3.time.DurationUtils#toChronoUnit
     */
    @Nullable
    public static ChronoUnit toChronoUnit(@Nullable TimeUnit unit) {
        try {
            return MethodUtilsWraps.invokeMethodAs(DurationUtils.class, "toChronoUnit", new Object[]{unit}, ChronoUnit.class);    // $NON-NLS-1$
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static TimeUnit toTimeUnit(@Nullable CharSequence sequence) {
        if (StringUtils.isBlank(sequence)) {
            return null;
        }
        TimeUnit[] units = TimeUnit.class.getEnumConstants();
        return Arrays.stream(units).filter(element -> StringUtils.equalsIgnoreCase(element.name(), sequence)).findFirst().orElse(null);
    }

    @Nullable
    public static TimeUnit toTimeUnit(@Nullable ChronoUnit unit) {
        if (unit == null) {
            return null;
        }
        switch (unit) {
            case NANOS:
                return TimeUnit.NANOSECONDS;
            case MICROS:
                return TimeUnit.MICROSECONDS;
            case MILLIS:
                return TimeUnit.MILLISECONDS;
            case SECONDS:
                return TimeUnit.SECONDS;
            case MINUTES:
                return TimeUnit.MINUTES;
            case HOURS:
                return TimeUnit.HOURS;
            case DAYS:
                return TimeUnit.DAYS;
            default:
                return null;
        }
    }
}
