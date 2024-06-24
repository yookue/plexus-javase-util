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


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link java.time.Period}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class TimePeriodWraps {
    @Nullable
    public static Period between(@Nullable Date startDateInclusive, @Nullable Date endDateExclusive) {
        return between(startDateInclusive, endDateExclusive, null);
    }

    @Nullable
    public static Period between(@Nullable Date startDateInclusive, @Nullable Date endDateExclusive, @Nullable ZoneId zoneId) {
        return ObjectUtils.anyNull(startDateInclusive, endDateExclusive) ? null : between(LocalDateWraps.fromUtilDate(startDateInclusive, zoneId), LocalDateWraps.fromUtilDate(endDateExclusive, zoneId));
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Period between(@Nullable LocalDate startDateInclusive, @Nullable LocalDate endDateExclusive) {
        return ObjectUtils.anyNull(startDateInclusive, endDateExclusive) ? null : Period.between(startDateInclusive, endDateExclusive);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Period between(@Nullable LocalDateTime startDateInclusive, @Nullable LocalDateTime endDateExclusive) {
        return ObjectUtils.anyNull(startDateInclusive, endDateExclusive) ? null : between(startDateInclusive.toLocalDate(), endDateExclusive.toLocalDate());
    }
}
