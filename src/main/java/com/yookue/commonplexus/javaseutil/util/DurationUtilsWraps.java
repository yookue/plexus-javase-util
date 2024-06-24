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


import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationUtils;
import com.yookue.commonplexus.javaseutil.constant.RangeVariantConst;


/**
 * Utilities for {@link java.time.Duration}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.time.DurationUtils
 * @see org.apache.commons.lang3.time.DurationFormatUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class DurationUtilsWraps {
    @Nullable
    public static Duration firstPositive(@Nullable Duration... durations) {
        return firstPositive(ArrayUtilsWraps.asList(durations));
    }

    @Nullable
    public static Duration firstPositive(@Nullable Collection<Duration> durations) {
        return CollectionPlainWraps.isEmpty(durations) ? null : durations.stream().filter(DurationUtilsWraps::isPositive).findFirst().orElse(null);
    }

    @Nullable
    public static Duration firstNegative(@Nullable Duration... durations) {
        return firstNegative(ArrayUtilsWraps.asList(durations));
    }

    @Nullable
    public static Duration firstNegative(@Nullable Collection<Duration> durations) {
        return CollectionPlainWraps.isEmpty(durations) ? null : durations.stream().filter(DurationUtilsWraps::isNegative).findFirst().orElse(null);
    }

    public static void ifPositive(@Nullable Duration duration, @Nullable Consumer<Duration> action) {
        if (isPositive(duration) && action != null) {
            action.accept(duration);
        }
    }

    public static void ifNegative(@Nullable Duration duration, @Nullable Consumer<Duration> action) {
        if (isNegative(duration) && action != null) {
            action.accept(duration);
        }
    }

    public static void ifNotPositive(@Nullable Duration duration, @Nullable Consumer<Duration> action) {
        if (isNotPositive(duration) && action != null) {
            action.accept(duration);
        }
    }

    public static void ifNotNegative(@Nullable Duration duration, @Nullable Consumer<Duration> action) {
        if (isNotNegative(duration) && action != null) {
            action.accept(duration);
        }
    }

    public static boolean isDurationParsable(@Nullable CharSequence duration) {
        return parseDuration(duration) != null;
    }

    /**
     * @see org.apache.commons.lang3.time.DurationUtils#isPositive
     */
    public static boolean isPositive(@Nullable Duration duration) {
        return duration != null && !duration.isZero() && !duration.isNegative();
    }

    public static boolean isPositiveOrZero(@Nullable Duration duration) {
        return duration != null && !duration.isNegative();
    }

    public static boolean isNotPositive(@Nullable Duration duration) {
        return !isPositiveOrZero(duration);
    }

    public static boolean isNegative(@Nullable Duration duration) {
        return duration != null && duration.isNegative();
    }

    public static boolean isNegativeOrZero(@Nullable Duration duration) {
        return duration != null && (duration.isNegative() || duration.isZero());
    }

    public static boolean isNotNegative(@Nullable Duration duration) {
        return !isNegativeOrZero(duration);
    }

    public static boolean isZero(@Nullable Duration duration) {
        return duration != null && duration.isZero();
    }

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

    @Nullable
    public static Duration ofChronoUnit(@Nullable Long amount, @Nullable CharSequence unit) {
        return ofChronoUnit(amount, TemporalUnitWraps.toChronoUnit(unit));
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Duration ofChronoUnit(@Nullable Long amount, @Nullable ChronoUnit unit) {
        return ObjectUtils.anyNull(amount, unit) ? null : Duration.of(amount, unit);
    }

    @Nullable
    public static Duration ofTimeUnit(@Nullable Long amount, @Nullable CharSequence unit) {
        return ofTimeUnit(amount, TemporalUnitWraps.toTimeUnit(unit));
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Duration ofTimeUnit(@Nullable Long amount, @Nullable TimeUnit unit) {
        return ObjectUtils.anyNull(amount, unit) ? null : DurationUtils.toDuration(amount, unit);
    }

    @Nullable
    public static Integer toMillisInteger(@Nullable Duration duration) {
        return (duration == null) ? null : DurationUtils.toMillisInt(duration);
    }

    @Nullable
    public static Integer toSecondsInteger(@Nullable Duration duration) {
        return (duration == null) ? null : RangeVariantConst.LONG_INTEGER.fit(duration.getSeconds()).intValue();
    }
}
