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


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link java.time.Period}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class TimePeriodWraps {
    /**
     * Calculates the period between two dates
     *
     * @param startDateInclusive The start date (inclusive), may be null
     * @param endDateExclusive The end date (exclusive), may be null
     *
     * @return the period between the two dates, or null if either date is null
     */
    @Nullable
    public static Period between(@Nullable Date startDateInclusive, @Nullable Date endDateExclusive) {
        return between(startDateInclusive, endDateExclusive, null);
    }

    /**
     * Calculates the period between two dates with a specific timezone
     *
     * @param startDateInclusive The start date (inclusive), may be null
     * @param endDateExclusive The end date (exclusive), may be null
     * @param zoneId The timezone to use for conversion, may be null
     *
     * @return the period between the two dates, or null if either date is null
     */
    @Nullable
    public static Period between(@Nullable Date startDateInclusive, @Nullable Date endDateExclusive, @Nullable ZoneId zoneId) {
        return ObjectUtils.anyNull(startDateInclusive, endDateExclusive) ? null : between(LocalDateWraps.ofJdkDate(startDateInclusive, zoneId), LocalDateWraps.ofJdkDate(endDateExclusive, zoneId));
    }

    /**
     * Calculates the period between two LocalDate objects
     *
     * @param startDateInclusive The start date (inclusive), may be null
     * @param endDateExclusive The end date (exclusive), may be null
     *
     * @return the period between the two dates, or null if either date is null
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Period between(@Nullable LocalDate startDateInclusive, @Nullable LocalDate endDateExclusive) {
        return ObjectUtils.anyNull(startDateInclusive, endDateExclusive) ? null : Period.between(startDateInclusive, endDateExclusive);
    }

    /**
     * Calculates the period between two LocalDateTime objects by converting them to LocalDate
     *
     * @param startDateInclusive The start date-time (inclusive), may be null
     * @param endDateExclusive The end date-time (exclusive), may be null
     *
     * @return the period between the two dates, or null if either date-time is null
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Period between(@Nullable LocalDateTime startDateInclusive, @Nullable LocalDateTime endDateExclusive) {
        return ObjectUtils.anyNull(startDateInclusive, endDateExclusive) ? null : between(startDateInclusive.toLocalDate(), endDateExclusive.toLocalDate());
    }
}
