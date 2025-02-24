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


import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import com.yookue.commonplexus.javaseutil.constant.TemporalFormatCombo;
import com.yookue.commonplexus.javaseutil.constant.TemporalFormatConst;


/**
 * Utilities for {@link java.util.Date}
 *
 * @author David Hsing
 * @see java.util.concurrent.TimeUnit
 * @see org.apache.commons.lang3.time.DateUtils
 * @see org.apache.commons.lang3.time.DateFormatUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class UtilDateWraps {
    public static boolean afterEpoch(@Nullable Date date) {
        return date != null && date.after(getEpochDate());
    }

    /**
     * Returns the year that represents, from LONG.MIN_YEAR to LONG.MAX_YEAR
     *
     * @param date the source date to check
     *
     * @return the year that represents, from LONG.MIN_YEAR to LONG.MAX_YEAR
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Integer getYear(@Nullable Date date) {
        return (date == null) ? null : LocalDateWraps.ofUtilDate(date).getYear();
    }

    /**
     * Returns the month-of-year that represents, from 1 (January) to 12 (December)
     *
     * @param date the source date to check
     *
     * @return the month-of-year that represents, from 1 (January) to 12 (December)
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Integer getMonth(@Nullable Date date) {
        return (date == null) ? null : LocalDateWraps.ofUtilDate(date).getMonthValue();
    }

    /**
     * Returns the day-of-month that represents, from 1 to 31
     *
     * @param date the source date to check
     *
     * @return the day-of-month that represents, from 1 to 31
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Integer getDayOfMonth(@Nullable Date date) {
        return (date == null) ? null : LocalDateWraps.ofUtilDate(date).getDayOfMonth();
    }

    @Nullable
    public static Integer getDayOfWeak(@Nullable Date date) {
        return CalendarPlainWraps.getCalendarField(date, Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    @Nullable
    public static Integer getDayOfYear(@Nullable Date date) {
        return CalendarPlainWraps.getCalendarField(date, Calendar.DAY_OF_YEAR);
    }

    @Nonnull
    public static Date getEpochDate() {
        return getSpecificDate(1970, 1, 1);
    }

    @Nonnull
    public static Date getSpecificDate(int year, int monthOfYear, int dayOfMonth) {
        return getSpecificDateTime(year, monthOfYear, dayOfMonth, 0, 0, 0, 0);
    }

    @Nonnull
    public static Date getSpecificDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute) {
        return getSpecificDateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, 0);
    }

    /**
     * Returns a {@link java.util.Date} with specified calendar fields
     *
     * @param year the year to represent, from LONG.MIN_YEAR to LONG.MAX_YEAR
     * @param monthOfYear the month-of-year to represent, from 1 (January) to 12 (December)
     * @param dayOfMonth the day-of-month to represent, from 1 to 31
     * @param hourOfDay the hour-of-day to represent, from 0 to 23
     * @param minuteOfHour the minute-of-hour to represent, from 0 to 59
     * @param secondOfMinute the second-of-minute to represent, from 0 to 59
     * @param nanoOfSecond the nano-of-second to represent, from 0 to 999,999,999
     * @return a {@link java.util.Date} with specified calendar fields
     *
     * @throws java.lang.IllegalArgumentException if any calendar fields are invalid
     * @throws java.time.DateTimeException if the value of any field is out of range, or if the day-of-month is invalid for the month-year
     * @see java.util.GregorianCalendar
     */
    @Nonnull
    public static Date getSpecificDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int nanoOfSecond) {
        if (year < Year.MIN_VALUE || year > Year.MAX_VALUE) {
            throw new IllegalArgumentException(AssertMessageWraps.isYear("year"));    // $NON-NLS-1$
        }
        if (monthOfYear < 1 || monthOfYear > 12) {
            throw new IllegalArgumentException(AssertMessageWraps.isMonth("monthOfYear"));    // $NON-NLS-1$
        }
        if (dayOfMonth < 1 || dayOfMonth > 31) {
            throw new IllegalArgumentException(AssertMessageWraps.isDay("dayOfMonth"));    // $NON-NLS-1$
        }
        return LocalDateWraps.toUtilDate(LocalDateTime.of(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, nanoOfSecond));
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Date getYesterdayDate() {
        return getDayStartDate(getYesterdayDateTime());
    }

    @Nonnull
    public static Date getYesterdayDateTime() {
        return DateUtils.addDays(getCurrentDateTime(), -1);
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Date getCurrentDate() {
        return getDayStartDate(getCurrentDateTime());
    }

    @Nonnull
    public static Date getCurrentDateTime() {
        return new Date();
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static Date getTomorrowDate() {
        return getDayStartDate(getTomorrowDateTime());
    }

    @Nonnull
    public static Date getTomorrowDateTime() {
        return DateUtils.addDays(getCurrentDateTime(), 1);
    }

    @Nonnull
    public static Date getMaxDateTime() {
        return getSpecificDateTime(9999, 12, 31, 23, 59, 59, 999);
    }

    @Nonnull
    public static Date getMinDateTime() {
        return getSpecificDateTime(0, 1, 1, 0, 0, 0, 0);
    }

    @Nullable
    public static Integer getMonthDays(int year, int month) {
        return LocalDateWraps.getMonthDays(year, month);
    }

    @Nullable
    public static Integer getMonthDays(@Nullable Date date) {
        return LocalDateWraps.getMonthDays(LocalDateWraps.ofUtilDateTime(date));
    }

    @Nullable
    public static String getMonthDisplayName(@Nullable Date date, @Nullable TextStyle style) {
        return getMonthDisplayName(date, style, Locale.getDefault());
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String getMonthDisplayName(@Nullable Date date, @Nullable TextStyle style, @Nullable Locale locale) {
        return ObjectUtils.anyNull(date, style, locale) ? null : CalendarPlainWraps.getMonthDisplayName(CalendarPlainWraps.getCalendarField(date, Calendar.MONTH, locale) + 1, style, locale);
    }

    @Nullable
    public static Date getMonthFirstDate(int year, int month) {
        return LocalDateWraps.toUtilDate(LocalDateWraps.getMonthFirstDateTime(year, month));
    }

    @Nullable
    public static Date getMonthFirstDate(@Nullable Date date) {
        return (date == null) ? null : LocalDateWraps.toUtilDate(LocalDateWraps.getMonthFirstDateTime(LocalDateWraps.ofUtilDateTime(date)));
    }

    @Nullable
    public static Date getMonthLastDate(int year, int month) {
        return LocalDateWraps.toUtilDate(LocalDateWraps.getMonthLastDateTime(year, month));
    }

    @Nullable
    public static Date getMonthLastDate(@Nullable Date date) {
        return (date == null) ? null : LocalDateWraps.toUtilDate(LocalDateWraps.getMonthLastDateTime(LocalDateWraps.ofUtilDateTime(date)));
    }

    @Nullable
    public static Date getSeasonFirstDate(int year, int month) {
        return LocalDateWraps.toUtilDate(LocalDateWraps.getSeasonFirstDateTime(year, month));
    }

    @Nullable
    public static Date getSeasonFirstDate(@Nullable Date date) {
        return (date == null) ? null : LocalDateWraps.toUtilDate(LocalDateWraps.getSeasonFirstDateTime(LocalDateWraps.ofUtilDateTime(date)));
    }

    @Nullable
    public static Date getSeasonLastDate(int year, int month) {
        return LocalDateWraps.toUtilDate(LocalDateWraps.getSeasonLastDateTime(year, month));
    }

    @Nullable
    public static Date getSeasonLastDate(@Nullable Date date) {
        return (date == null) ? null : LocalDateWraps.toUtilDate(LocalDateWraps.getSeasonLastDateTime(LocalDateWraps.ofUtilDateTime(date)));
    }

    public static String formatCurrentDate() {
        return formatCurrentDateTime(TemporalFormatConst.ISO_YYYYMMDD);
    }

    public static String formatCurrentDateTime() {
        return formatCurrentDateTime(TemporalFormatConst.ISO_YYYYMMDD_HHMMSS);
    }

    public static String formatCurrentDateTime(@Nullable String format) {
        return formatCurrentDateTime(format, null);
    }

    public static String formatCurrentDateTime(@Nullable String format, @Nullable TimeZone zone) {
        return formatCurrentDateTime(format, zone, null);
    }

    public static String formatCurrentDateTime(@Nullable String format, @Nullable TimeZone zone, @Nullable Locale locale) {
        return formatDateTime(getCurrentDateTime(), format, zone, locale);
    }

    public static String formatDate(@Nullable Date date) {
        return formatDateTime(date, TemporalFormatConst.ISO_YYYYMMDD, null, null);
    }

    public static String formatDateTime(@Nullable Date date) {
        return formatDateTime(date, TemporalFormatConst.ISO_YYYYMMDD_HHMMSS, null, null);
    }

    public static String formatDateTime(@Nullable Date date, @Nullable String format) {
        return formatDateTime(date, format, null, null);
    }

    public static String formatDateTime(@Nullable Date date, @Nullable String format, @Nullable TimeZone zone) {
        return formatDateTime(date, format, zone, null);
    }

    @Nullable
    public static String formatDateTime(@Nullable Date date, @Nullable String format, @Nullable TimeZone zone, @Nullable Locale locale) {
        return (date == null || StringUtils.isBlank(format)) ? null : DateFormatUtils.format(date, format, zone, locale);
    }

    public static boolean matchAnyFormats(@Nullable String date, @Nullable String... formats) {
        return matchAnyFormats(date, ArrayUtilsWraps.asList(formats));
    }

    public static boolean matchAnyFormats(@Nullable String date, @Nullable Collection<String> formats) {
        return StringUtils.isNotBlank(date) && CollectionPlainWraps.isNotEmpty(formats) && formats.stream().filter(StringUtils::isNotBlank).anyMatch(format -> matchFormat(date, format));
    }

    public static boolean matchAnyFormats(@Nullable String date, @Nullable TimeZone zone, @Nullable Locale locale, @Nullable String... formats) {
        return matchAnyFormats(date, zone, locale, ArrayUtilsWraps.asList(formats));
    }

    public static boolean matchAnyFormats(@Nullable String date, @Nullable TimeZone zone, @Nullable Locale locale, @Nullable Collection<String> formats) {
        return StringUtils.isNotBlank(date) && CollectionPlainWraps.isNotEmpty(formats) && formats.stream().filter(StringUtils::isNotBlank).anyMatch(format -> matchFormat(date, format, zone, locale));
    }

    public static boolean matchFormat(@Nullable String date, @Nullable String format) {
        if (StringUtils.isAnyBlank(date, format)) {
            return false;
        }
        try {
            parseDateTime(date, format);
            return true;
        } catch (ParseException ignored) {
        }
        return false;
    }

    public static boolean matchFormat(@Nullable String date, @Nullable String format, @Nullable TimeZone zone, @Nullable Locale locale) {
        if (StringUtils.isAnyBlank(date, format)) {
            return false;
        }
        try {
            parseDateTime(date, format, zone, locale);
            return true;
        } catch (ParseException ignored) {
        }
        return false;
    }

    @Nullable
    public static Date parseDateGuessing(@Nullable String date) {
        return parseDateTimeWithFormats(date, TemporalFormatCombo.DATE_FORMATS);
    }

    @Nullable
    public static Date parseDateTime(@Nullable String date, @Nullable String format) throws ParseException {
        return parseDateTime(date, format, null, null);
    }

    @Nullable
    public static Date parseDateTime(@Nullable String date, @Nullable String format, @Nullable TimeZone zone, @Nullable Locale locale) throws ParseException {
        return StringUtils.isAnyBlank(date, format) ? null : FastDateFormat.getInstance(format, zone, locale).parse(date);
    }

    @Nullable
    public static Date parseDateTimeQuietly(@Nullable String date, @Nullable String format) {
        return parseDateTimeQuietly(date, format, null, null);
    }

    @Nullable
    public static Date parseDateTimeQuietly(@Nullable String date, @Nullable String format, @Nullable TimeZone zone, @Nullable Locale locale) {
        try {
            return parseDateTime(date, format, zone, locale);
        } catch (ParseException ignored) {
        }
        return null;
    }

    @Nullable
    public static Date parseDateTimeGuessing(@Nullable String dateTime) {
        return parseDateTimeWithFormats(dateTime, TemporalFormatCombo.DATETIME_FORMATS);
    }

    @Nullable
    public static Date parseDateTimeWithFormats(@Nullable String date, @Nullable String... formats) {
        return parseDateTimeWithFormats(date, ArrayUtilsWraps.asList(formats));
    }

    @Nullable
    public static Date parseDateTimeWithFormats(@Nullable String date, @Nullable Collection<String> formats) {
        return parseDateTimeWithFormats(date, null, null, formats);
    }

    @Nullable
    public static Date parseDateTimeWithFormats(@Nullable String date, @Nullable TimeZone zone, @Nullable Locale locale, @Nullable String... formats) {
        return parseDateTimeWithFormats(date, zone, locale, ArrayUtilsWraps.asList(formats));
    }

    @Nullable
    public static Date parseDateTimeWithFormats(@Nullable String date, @Nullable TimeZone zone, @Nullable Locale locale, @Nullable Collection<String> formats) {
        if (StringUtils.isBlank(date) || CollectionPlainWraps.isEmpty(formats)) {
            return null;
        }
        return formats.stream().map(format -> parseDateTimeQuietly(date, format, zone, locale)).filter(Objects::nonNull).findFirst().orElse(null);
    }

    /**
     * Returns a date that plus amount with the given data field
     *
     * @param date the date, not null
     * @param field the calendar field type to add to
     * @param amount the amount to add, may be negative
     *
     * @return a date that plus amount with the given data field
     *
     * @see org.apache.commons.lang3.time.DateUtils#addYears
     * @see org.apache.commons.lang3.time.DateUtils#addMonths
     * @see org.apache.commons.lang3.time.DateUtils#addDays
     * @see org.apache.commons.lang3.time.DateUtils#addHours
     * @see org.apache.commons.lang3.time.DateUtils#addMinutes
     * @see org.apache.commons.lang3.time.DateUtils#addSeconds
     * @see org.apache.commons.lang3.time.DateUtils#addMilliseconds
     */
    @SuppressWarnings("MagicConstant")
    public static Date plusDateTime(@Nullable Date date, int field, int amount) {
        if (date == null || field < 0 || amount == 0) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    @Nullable
    public static Date plusDuration(@Nullable Date date, @Nullable Duration duration) {
        return (duration == null || duration.isZero()) ? date : plusDateTime(date, Calendar.MILLISECOND, (int) duration.toMillis());
    }

    @Nullable
    public static Date getDayStartDate(@Nullable Date date) {
        return getDayStartDate(date, null, null);
    }

    public static Date getDayStartDate(@Nullable Date date, @Nullable TimeZone zone) {
        return getDayStartDate(date, zone, null);
    }

    public static Date getDayStartDate(@Nullable Date date, @Nullable Locale locale) {
        return getDayStartDate(date, null, locale);
    }

    @Nullable
    public static Date getDayStartDate(@Nullable Date date, @Nullable TimeZone zone, @Nullable Locale locale) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance(ObjectUtils.defaultIfNull(zone, TimeZone.getDefault()), ObjectUtils.defaultIfNull(locale, Locale.getDefault(Locale.Category.FORMAT)));
        calendar.setLenient(false);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDayEndDate(@Nullable Date date) {
        return getDayEndDate(date, null, null);
    }

    public static Date getDayEndDate(@Nullable Date date, @Nullable TimeZone zone) {
        return getDayEndDate(date, zone, null);
    }

    public static Date getDayEndDate(@Nullable Date date, @Nullable Locale locale) {
        return getDayEndDate(date, null, locale);
    }

    @Nullable
    public static Date getDayEndDate(@Nullable Date date, @Nullable TimeZone zone, @Nullable Locale locale) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance(ObjectUtils.defaultIfNull(zone, TimeZone.getDefault()), ObjectUtils.defaultIfNull(locale, Locale.getDefault(Locale.Category.FORMAT)));
        calendar.setLenient(false);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
