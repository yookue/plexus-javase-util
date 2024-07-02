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


import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link java.util.Calendar}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.time.CalendarUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class CalendarPlainWraps {
    @Nullable
    public static Integer getCalendarField(@Nullable Date date, int field) {
        return getCalendarField(date, field, null, null);
    }

    @Nullable
    public static Integer getCalendarField(@Nullable Date date, int field, @Nullable TimeZone zone) {
        return getCalendarField(date, field, zone, null);
    }

    public static Integer getCalendarField(@Nullable Date date, int field, @Nullable Locale locale) {
        return getCalendarField(date, field, null, null);
    }

    /**
     * Returns the value of the given calendar field
     * <p>
     * In lenient mode, all calendar fields are normalized
     * In non-lenient mode, all calendar fields are validated and this method throws an exception if any calendar fields have out-of-range values
     *
     * @param date the date to check
     * @param field the given calendar field
     *
     * @return the value of the given calendar field
     */
    @Nullable
    public static Integer getCalendarField(@Nullable Date date, int field, @Nullable TimeZone zone, @Nullable Locale locale) {
        if (date == null || field < 0) {
            return null;
        }
        Calendar calendar = Calendar.getInstance(ObjectUtils.defaultIfNull(zone, TimeZone.getDefault()), ObjectUtils.defaultIfNull(locale, Locale.getDefault(Locale.Category.FORMAT)));
        calendar.setLenient(false);
        calendar.setTime(date);
        return calendar.get(field);
    }

    @Nullable
    public static String getMonthDisplayName(int month, @Nullable TextStyle style) {
        return getMonthDisplayName(month, style, Locale.getDefault());
    }

    @Nullable
    public static String getMonthDisplayName(int month, @Nullable TextStyle style, @Nullable Locale locale) {
        return (month < 1 || month > 12 || ObjectUtils.anyNull(style, locale)) ? null : getMonthDisplayName(Month.of(month), style, locale);
    }

    @Nullable
    public static String getMonthDisplayName(@Nullable Month month, @Nullable TextStyle style) {
        return getMonthDisplayName(month, style, Locale.getDefault());
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String getMonthDisplayName(@Nullable Month month, @Nullable TextStyle style, @Nullable Locale locale) {
        return ObjectUtils.anyNull(month, style, locale) ? null : month.getDisplayName(style, locale);
    }
}
