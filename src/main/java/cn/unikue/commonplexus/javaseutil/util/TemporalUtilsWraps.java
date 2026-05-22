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


import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationUtils;


/**
 * Utilities for temporal utilities
 *
 * @author David Hsing
 *
 * @see java.time.temporal.TemporalUnit
 * @see java.time.temporal.ChronoUnit
 * @see java.util.concurrent.TimeUnit
 * @see "org.springframework.data.redis.core.TimeoutUtils"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "JavadocReference"})
public abstract class TemporalUtilsWraps {
    /**
     * Checks whether the given sequence can be converted to a ChronoUnit
     *
     * @param sequence The sequence to check, may be null or blank
     *
     * @return {@code true} if the sequence can be converted to a ChronoUnit, {@code false} otherwise
     */
    public static boolean isChronoUnit(@Nullable CharSequence sequence) {
        return toChronoUnit(sequence) != null;
    }

    /**
     * Checks whether the given sequence can be converted to a TimeUnit
     *
     * @param sequence The sequence to check, may be null or blank
     *
     * @return {@code true} if the sequence can be converted to a TimeUnit, {@code false} otherwise
     */
    public static boolean isTimeUnit(@Nullable CharSequence sequence) {
        return toTimeUnit(sequence) != null;
    }

    /**
     * Converts a CharSequence to a ChronoUnit enum value (case-insensitive)
     *
     * @param sequence The sequence to convert, may be null or blank
     *
     * @return the corresponding ChronoUnit, or null if sequence is blank or not matched
     */
    @Nullable
    public static ChronoUnit toChronoUnit(@Nullable CharSequence sequence) {
        if (StringUtils.isBlank(sequence)) {
            return null;
        }
        ChronoUnit[] units = ChronoUnit.class.getEnumConstants();
        return Arrays.stream(units).filter(item -> StringUtils.equalsIgnoreCase(item.name(), sequence)).findFirst().orElse(null);
    }

    /**
     * Converts a TimeUnit to a ChronoUnit using reflection
     *
     * @param unit The TimeUnit to convert, may be null
     *
     * @return the corresponding ChronoUnit, or null if conversion fails
     */
    @Nullable
    public static ChronoUnit toChronoUnit(@Nullable TimeUnit unit) {
        try {
            return MethodUtilsWraps.invokeMethodAs(DurationUtils.class, "toChronoUnit", new Object[]{unit}, ChronoUnit.class);    // $NON-NLS-1$
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Converts a CharSequence to a TimeUnit enum value (case-insensitive)
     *
     * @param sequence The sequence to convert, may be null or blank
     *
     * @return the corresponding TimeUnit, or null if sequence is blank or not matched
     */
    @Nullable
    public static TimeUnit toTimeUnit(@Nullable CharSequence sequence) {
        if (StringUtils.isBlank(sequence)) {
            return null;
        }
        TimeUnit[] units = TimeUnit.class.getEnumConstants();
        return Arrays.stream(units).filter(item -> StringUtils.equalsIgnoreCase(item.name(), sequence)).findFirst().orElse(null);
    }

    /**
     * Converts a ChronoUnit to a TimeUnit
     *
     * @param unit The ChronoUnit to convert, may be null
     *
     * @return the corresponding TimeUnit, or null if the ChronoUnit cannot be converted
     */
    @Nullable
    public static TimeUnit toTimeUnit(@Nullable ChronoUnit unit) {
        if (unit == null) {
            return null;
        }
        return switch (unit) {
            case NANOS -> TimeUnit.NANOSECONDS;
            case MICROS -> TimeUnit.MICROSECONDS;
            case MILLIS -> TimeUnit.MILLISECONDS;
            case SECONDS -> TimeUnit.SECONDS;
            case MINUTES -> TimeUnit.MINUTES;
            case HOURS -> TimeUnit.HOURS;
            case DAYS -> TimeUnit.DAYS;
            default -> null;
        };
    }
}
