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


import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationUtils;
import cn.unikue.commonplexus.javaseutil.constant.RangeVariantConst;


/**
 * Utilities for {@link java.time.Duration}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.time.DurationUtils
 * @see org.apache.commons.lang3.time.DurationFormatUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class DurationUtilsWraps {
    /**
     * Gets the first positive duration from the given durations
     *
     * @param durations the durations to search through
     *
     * @return the first positive duration, or {@code null} if none found or input is null
     */
    @Nullable
    public static Duration firstPositive(@Nullable Duration... durations) {
        return firstPositive(ArrayUtilsWraps.asList(durations));
    }

    /**
     * Gets the first positive duration from the collection of durations
     *
     * @param durations the collection of durations to search through
     *
     * @return the first positive duration, or {@code null} if none found or collection is empty
     */
    @Nullable
    public static Duration firstPositive(@Nullable Collection<Duration> durations) {
        return CollectionPlainWraps.isEmpty(durations) ? null : durations.stream().filter(DurationUtilsWraps::isPositive).findFirst().orElse(null);
    }

    /**
     * Gets the first negative duration from the given durations
     *
     * @param durations the durations to search through
     *
     * @return the first negative duration, or {@code null} if none found or input is null
     */
    @Nullable
    public static Duration firstNegative(@Nullable Duration... durations) {
        return firstNegative(ArrayUtilsWraps.asList(durations));
    }

    /**
     * Gets the first negative duration from the collection of durations
     *
     * @param durations the collection of durations to search through
     *
     * @return the first negative duration, or {@code null} if none found or collection is empty
     */
    @Nullable
    public static Duration firstNegative(@Nullable Collection<Duration> durations) {
        return CollectionPlainWraps.isEmpty(durations) ? null : durations.stream().filter(DurationUtilsWraps::isNegative).findFirst().orElse(null);
    }

    /**
     * Executes the action if the duration is positive
     *
     * @param duration the duration to check
     * @param action the action to execute with the duration if it is positive
     */
    public static void ifPositive(@Nullable Duration duration, @Nullable Consumer<Duration> action) {
        if (isPositive(duration) && action != null) {
            action.accept(duration);
        }
    }

    /**
     * Executes the action if the duration is negative
     *
     * @param duration the duration to check
     * @param action the action to execute with the duration if it is negative
     */
    public static void ifNegative(@Nullable Duration duration, @Nullable Consumer<Duration> action) {
        if (isNegative(duration) && action != null) {
            action.accept(duration);
        }
    }

    /**
     * Executes the action if the duration is not positive (zero or negative)
     *
     * @param duration the duration to check
     * @param action the action to execute with the duration if it is not positive
     */
    public static void ifNotPositive(@Nullable Duration duration, @Nullable Consumer<Duration> action) {
        if (isNotPositive(duration) && action != null) {
            action.accept(duration);
        }
    }

    /**
     * Executes the action if the duration is not negative (positive or zero)
     *
     * @param duration the duration to check
     * @param action the action to execute with the duration if it is not negative
     */
    public static void ifNotNegative(@Nullable Duration duration, @Nullable Consumer<Duration> action) {
        if (isNotNegative(duration) && action != null) {
            action.accept(duration);
        }
    }

    /**
     * Checks if the given text can be parsed as a duration
     *
     * @param duration the text to check
     *
     * @return {@code true} if the text can be parsed as a valid duration, {@code false} otherwise
     */
    public static boolean isDurationParsable(@Nullable CharSequence duration) {
        return parseDuration(duration) != null;
    }

    /**
     * Checks if the duration is positive (greater than zero)
     *
     * @param duration the duration to check
     *
     * @return {@code true} if the duration is positive, {@code false} otherwise
     *
     * @see org.apache.commons.lang3.time.DurationUtils#isPositive
     */
    public static boolean isPositive(@Nullable Duration duration) {
        return duration != null && !duration.isZero() && !duration.isNegative();
    }

    /**
     * Checks if the duration is positive or zero (not negative)
     *
     * @param duration the duration to check
     *
     * @return {@code true} if the duration is positive or zero, {@code false} otherwise
     */
    public static boolean isPositiveOrZero(@Nullable Duration duration) {
        return duration != null && !duration.isNegative();
    }

    /**
     * Checks if the duration is not positive (zero or negative)
     *
     * @param duration the duration to check
     *
     * @return {@code true} if the duration is not positive, {@code false} otherwise
     */
    public static boolean isNotPositive(@Nullable Duration duration) {
        return !isPositiveOrZero(duration);
    }

    /**
     * Checks if the duration is negative (less than zero)
     *
     * @param duration the duration to check
     *
     * @return {@code true} if the duration is negative, {@code false} otherwise
     */
    public static boolean isNegative(@Nullable Duration duration) {
        return duration != null && duration.isNegative();
    }

    /**
     * Checks if the duration is negative or zero (not positive)
     *
     * @param duration the duration to check
     *
     * @return {@code true} if the duration is negative or zero, {@code false} otherwise
     */
    public static boolean isNegativeOrZero(@Nullable Duration duration) {
        return duration != null && (duration.isNegative() || duration.isZero());
    }

    /**
     * Checks if the duration is not negative (positive or zero)
     *
     * @param duration the duration to check
     *
     * @return {@code true} if the duration is not negative, {@code false} otherwise
     */
    public static boolean isNotNegative(@Nullable Duration duration) {
        return !isNegativeOrZero(duration);
    }

    /**
     * Checks if the duration is zero
     *
     * @param duration the duration to check
     *
     * @return {@code true} if the duration is zero, {@code false} otherwise
     */
    public static boolean isZero(@Nullable Duration duration) {
        return duration != null && duration.isZero();
    }

    /**
     * Parses the given text as a duration
     *
     * @param duration the text to parse (in ISO-8601 duration format)
     *
     * @return the parsed duration, or {@code null} if the text is blank or cannot be parsed
     */
    @Nullable
    public static Duration parseDuration(@Nullable CharSequence duration) {
        if (StringUtils.isBlank(duration)) {
            return null;
        }
        try {
            return Duration.parse(duration);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Creates a duration from the amount and chrono unit
     *
     * @param amount the amount of the duration
     * @param unit the chrono unit as text (e.g., "DAYS", "HOURS")
     *
     * @return the created duration, or {@code null} if either parameter is null
     */
    @Nullable
    public static Duration ofChronoUnit(@Nullable Long amount, @Nullable CharSequence unit) {
        return ofChronoUnit(amount, TemporalUtilsWraps.toChronoUnit(unit));
    }

    /**
     * Creates a duration from the amount and chrono unit
     *
     * @param amount the amount of the duration
     * @param unit the chrono unit
     *
     * @return the created duration, or {@code null} if either parameter is null
     */
    @Nullable
    public static Duration ofChronoUnit(@Nullable Long amount, @Nullable ChronoUnit unit) {
        return ObjectUtils.anyNull(amount, unit) ? null : Duration.of(amount, unit);
    }

    /**
     * Creates a duration from the amount and time unit
     *
     * @param amount the amount of the duration
     * @param unit the time unit as text (e.g., "MILLISECONDS", "SECONDS")
     *
     * @return the created duration, or {@code null} if either parameter is null
     */
    @Nullable
    public static Duration ofTimeUnit(@Nullable Long amount, @Nullable CharSequence unit) {
        return ofTimeUnit(amount, TemporalUtilsWraps.toTimeUnit(unit));
    }

    /**
     * Creates a duration from the amount and time unit
     *
     * @param amount the amount of the duration
     * @param unit the time unit
     *
     * @return the created duration, or {@code null} if either parameter is null
     */
    @Nullable
    public static Duration ofTimeUnit(@Nullable Long amount, @Nullable TimeUnit unit) {
        return ObjectUtils.anyNull(amount, unit) ? null : DurationUtils.toDuration(amount, unit);
    }

    /**
     * Converts the duration to milliseconds as an Integer
     *
     * @param duration the duration to convert
     *
     * @return the duration in milliseconds as an Integer, or {@code null} if the duration is null
     */
    @Nullable
    public static Integer toMillisInteger(@Nullable Duration duration) {
        return (duration == null) ? null : DurationUtils.toMillisInt(duration);
    }

    /**
     * Converts the duration to seconds as an Integer
     *
     * @param duration the duration to convert
     *
     * @return the duration in seconds as an Integer, or {@code null} if the duration is null
     */
    @Nullable
    public static Integer toSecondsInteger(@Nullable Duration duration) {
        return (duration == null) ? null : RangeVariantConst.LONG_INTEGER.fit(duration.getSeconds()).intValue();
    }
}
