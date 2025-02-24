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
import java.time.LocalTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.TemporalFormatCombo;
import com.yookue.commonplexus.javaseutil.constant.TemporalFormatConst;


/**
 * Utilities for {@link java.time.LocalDate}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class LocalDateWraps {
    public static boolean afterEpoch(@Nullable LocalDate date) {
        return date != null && date.isAfter(getEpochDate());
    }

    public static boolean afterEpoch(@Nullable LocalDateTime dateTime) {
        return dateTime != null && dateTime.isAfter(getEpochDateTime());
    }

    public static boolean beforeEpoch(@Nullable LocalDate date) {
        return date != null && date.isBefore(getEpochDate());
    }

    public static boolean beforeEpoch(@Nullable LocalDateTime dateTime) {
        return dateTime != null && dateTime.isBefore(getEpochDateTime());
    }

    @Nonnull
    public static LocalDate getYesterdayDate() {
        return LocalDate.now().plusDays(-1L);
    }

    @Nonnull
    public static LocalDateTime getYesterdayDateTime() {
        return LocalDateTime.now().plusDays(-1L);
    }

    @Nonnull
    public static LocalTime getYesterdayTime() {
        return LocalTime.now().plusHours(-24L);
    }

    @Nonnull
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @Nonnull
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    @Nonnull
    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    @Nonnull
    public static LocalDate getTomorrowDate() {
        return LocalDate.now().plusDays(1L);
    }

    @Nonnull
    public static LocalDateTime getTomorrowDateTime() {
        return LocalDateTime.now().plusDays(1L);
    }

    @Nonnull
    public static LocalTime getTomorrowTime() {
        return LocalTime.now().plusHours(24L);
    }

    @Nonnull
    public static LocalDate getEpochDate() {
        return LocalDate.of(1970, 1, 1);
    }

    @Nonnull
    public static LocalDateTime getEpochDateTime() {
        return LocalDateTime.of(1970, 1, 1, 0, 0);
    }

    @Nonnull
    public static LocalDate getMaxDate() {
        return LocalDate.of(9999, 12, 31);
    }

    @Nonnull
    public static LocalDate getMinDate() {
        return LocalDate.of(0, 1, 1);
    }

    @Nonnull
    public static LocalDateTime getMaxDateTime() {
        return LocalDateTime.of(9999, 12, 31, 23, 59, 59, 999);
    }

    @Nonnull
    public static LocalDateTime getMinDateTime() {
        return LocalDateTime.of(0, 1, 1, 0, 0);
    }

    @Nullable
    public static Integer getMonthDays(int year, int month) {
        return getMonthDays(getMonthFirstDate(year, month));
    }

    @Nullable
    public static Integer getMonthDays(@Nullable LocalDate date) {
        return (date == null) ? null : date.lengthOfMonth();
    }

    @Nullable
    public static Integer getMonthDays(@Nullable LocalDateTime dateTime) {
        return (dateTime == null) ? null : dateTime.toLocalDate().lengthOfMonth();
    }

    @Nullable
    public static String getMonthDisplayName(@Nullable LocalDate date, @Nullable TextStyle style) {
        return getMonthDisplayName(date, style, Locale.getDefault());
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String getMonthDisplayName(@Nullable LocalDate date, @Nullable TextStyle style, @Nullable Locale locale) {
        return ObjectUtils.anyNull(date, style, locale) ? null : date.getMonth().getDisplayName(style, locale);
    }

    @Nullable
    public static String getMonthDisplayName(@Nullable LocalDateTime dateTime, @Nullable TextStyle style) {
        return getMonthDisplayName(dateTime, style, Locale.getDefault());
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String getMonthDisplayName(@Nullable LocalDateTime dateTime, @Nullable TextStyle style, @Nullable Locale locale) {
        return ObjectUtils.anyNull(dateTime, style, locale) ? null : dateTime.getMonth().getDisplayName(style, locale);
    }

    @Nullable
    public static LocalDate getMonthFirstDate(int year, int month) {
        return (year < Year.MIN_VALUE || year > Year.MAX_VALUE || month < 1 || month > 12) ? null : LocalDate.of(year, month, 1);
    }

    @Nullable
    public static LocalDate getMonthFirstDate(@Nullable LocalDate date) {
        return (date == null) ? null : date.with(TemporalAdjusters.firstDayOfMonth());
    }

    @Nullable
    public static LocalDateTime getMonthFirstDateTime(int year, int month) {
        return (year < Year.MIN_VALUE || year > Year.MAX_VALUE || month < 1 || month > 12) ? null : LocalDateTime.of(year, month, 1, 0, 0);
    }

    @Nullable
    public static LocalDateTime getMonthFirstDateTime(@Nullable LocalDateTime dateTime) {
        return (dateTime == null) ? null : dateTime.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    @Nullable
    public static LocalDate getMonthLastDate(int year, int month) {
        return (year < Year.MIN_VALUE || year > Year.MAX_VALUE || month < 1 || month > 12) ? null : getMonthLastDate(LocalDate.of(year, month, 1));
    }

    @Nullable
    public static LocalDate getMonthLastDate(@Nullable LocalDate date) {
        return (date == null) ? null : date.with(TemporalAdjusters.lastDayOfMonth());
    }

    @Nullable
    public static LocalDateTime getMonthLastDateTime(int year, int month) {
        return (year < Year.MIN_VALUE || year > Year.MAX_VALUE || month < 1 || month > 12) ? null : getMonthLastDateTime(LocalDateTime.of(year, month, 1, 23, 59, 59, 999999999));
    }

    @Nullable
    public static LocalDateTime getMonthLastDateTime(@Nullable LocalDateTime dateTime) {
        return (dateTime == null) ? null : dateTime.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    @Nullable
    @SuppressWarnings("DuplicatedCode")
    public static LocalDate getSeasonFirstDate(int year, int month) {
        if (year < Year.MIN_VALUE || year > Year.MAX_VALUE || month < 1 || month > 12) {
            return null;
        }
        int adjust = 3 * (month % 3 == 0 ? ((month - 1) / 3) : (month / 3)) + 1;
        return LocalDate.of(year, adjust, 1);
    }

    @Nullable
    public static LocalDate getSeasonFirstDate(@Nullable LocalDate date) {
        if (date == null) {
            return null;
        }
        int adjust = 3 * (date.getMonthValue() % 3 == 0 ? ((date.getMonthValue() - 1) / 3) : (date.getMonthValue() / 3)) + 1;
        return date.withDayOfMonth(1).withMonth(adjust).with(TemporalAdjusters.firstDayOfMonth());
    }

    @Nullable
    @SuppressWarnings("DuplicatedCode")
    public static LocalDateTime getSeasonFirstDateTime(int year, int month) {
        if (year < Year.MIN_VALUE || year > Year.MAX_VALUE || month < 1 || month > 12) {
            return null;
        }
        int adjust = 3 * (month % 3 == 0 ? ((month - 1) / 3) : (month / 3)) + 1;
        return LocalDateTime.of(year, adjust, 1, 0, 0);
    }

    @Nullable
    public static LocalDateTime getSeasonFirstDateTime(@Nullable LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        int adjust = 3 * (dateTime.getMonthValue() % 3 == 0 ? ((dateTime.getMonthValue() - 1) / 3) : (dateTime.getMonthValue() / 3)) + 1;
        return dateTime.withDayOfMonth(1).withMonth(adjust).with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    @Nullable
    public static LocalDate getSeasonLastDate(int year, int month) {
        if (year < Year.MIN_VALUE || year > Year.MAX_VALUE || month < 1 || month > 12) {
            return null;
        }
        int adjust = 3 * (month / 3) + month % 3;
        return getSeasonLastDate(LocalDate.of(year, adjust, 1));
    }

    @Nullable
    public static LocalDate getSeasonLastDate(@Nullable LocalDate date) {
        if (date == null) {
            return null;
        }
        int adjust = 3 * (date.getMonthValue() / 3) + date.getMonthValue() % 3;
        return date.withDayOfMonth(1).withMonth(adjust).with(TemporalAdjusters.lastDayOfMonth());
    }

    @Nullable
    public static LocalDateTime getSeasonLastDateTime(int year, int month) {
        if (year < Year.MIN_VALUE || year > Year.MAX_VALUE || month < 1 || month > 12) {
            return null;
        }
        int adjust = 3 * (month / 3) + month % 3;
        return getSeasonLastDateTime(LocalDateTime.of(year, adjust, 1, 23, 59, 59, 999999999));
    }

    @Nullable
    public static LocalDateTime getSeasonLastDateTime(@Nullable LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        int adjust = 3 * (dateTime.getMonthValue() / 3) + dateTime.getMonthValue() % 3;
        return dateTime.withDayOfMonth(1).withMonth(adjust).with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    public static String formatCurrentDate() {
        return formatCurrentDate(TemporalFormatConst.ISO_YYYYMMDD);
    }

    @Nullable
    public static String formatCurrentDate(@Nullable String format) {
        return formatDate(getCurrentDate(), format);
    }

    public static String formatCurrentDateTime() {
        return formatCurrentDateTime(TemporalFormatConst.ISO_YYYYMMDD_HHMMSS);
    }

    public static String formatCurrentDateTime(@Nullable String format) {
        return formatDateTime(getCurrentDateTime(), format);
    }

    public static String formatCurrentTime() {
        return formatCurrentTime(TemporalFormatConst.ISO_HHMMSS);
    }

    public static String formatCurrentTime(@Nullable String format) {
        return formatTime(getCurrentTime(), format);
    }

    @Nullable
    public static String formatDate(LocalDate date) {
        return formatDate(date, TemporalFormatConst.ISO_YYYYMMDD);
    }

    @Nullable
    public static String formatDate(@Nullable LocalDate date, @Nullable String format) {
        return (date == null || StringUtils.isBlank(format)) ? null : date.format(DateTimeFormatter.ofPattern(format));
    }

    public static String formatDateTime(@Nullable LocalDateTime dateTime) {
        return formatDateTime(dateTime, TemporalFormatConst.ISO_YYYYMMDD_HHMMSS);
    }

    @Nullable
    public static String formatDateTime(@Nullable LocalDateTime dateTime, @Nullable String format) {
        return (dateTime == null || StringUtils.isBlank(format)) ? null : dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String formatTime(@Nullable LocalTime time) {
        return formatTime(time, TemporalFormatConst.ISO_HHMMSS);
    }

    @Nullable
    public static String formatTime(@Nullable LocalTime time, @Nullable String format) {
        return (time == null || StringUtils.isBlank(format)) ? null : time.format(DateTimeFormatter.ofPattern(format));
    }

    public static boolean matchDateFormat(@Nullable String date, @Nullable String format) {
        if (StringUtils.isAnyBlank(date, format)) {
            return false;
        }
        try {
            parseDate(date, format);
            return true;
        } catch (DateTimeParseException ignored) {
        }
        return false;
    }

    public static boolean matchDateTimeFormat(@Nullable String dateTime, @Nullable String format) {
        if (StringUtils.isAnyBlank(dateTime, format)) {
            return false;
        }
        try {
            parseDateTime(dateTime, format);
            return true;
        } catch (DateTimeParseException ignored) {
        }
        return false;
    }

    public static boolean matchTimeFormat(@Nullable String time, @Nullable String format) {
        if (StringUtils.isAnyBlank(time, format)) {
            return false;
        }
        try {
            parseTime(time, format);
            return true;
        } catch (DateTimeParseException ignored) {
        }
        return false;
    }

    public static boolean matchAnyDateFormats(@Nullable String date, @Nullable String... formats) {
        return matchAnyDateFormats(date, ArrayUtilsWraps.asList(formats));
    }

    public static boolean matchAnyDateFormats(@Nullable String date, @Nullable Collection<String> formats) {
        return StringUtils.isNotBlank(date) && CollectionPlainWraps.isNotEmpty(formats) && formats.stream().filter(StringUtils::isNotBlank).anyMatch(format -> matchDateFormat(date, format));
    }

    public static boolean matchAnyDateTimeFormats(@Nullable String dateTime, @Nullable String... formats) {
        return matchAnyDateTimeFormats(dateTime, ArrayUtilsWraps.asList(formats));
    }

    public static boolean matchAnyDateTimeFormats(@Nullable String dateTime, @Nullable Collection<String> formats) {
        return StringUtils.isNotBlank(dateTime) && CollectionPlainWraps.isNotEmpty(formats) && formats.stream().filter(StringUtils::isNotBlank).anyMatch(format -> matchDateTimeFormat(dateTime, format));
    }

    public static boolean matchAnyTimeFormats(@Nullable String time, @Nullable String... formats) {
        return matchAnyTimeFormats(time, ArrayUtilsWraps.asList(formats));
    }

    public static boolean matchAnyTimeFormats(@Nullable String time, @Nullable Collection<String> formats) {
        return StringUtils.isNotBlank(time) && CollectionPlainWraps.isNotEmpty(formats) && formats.stream().filter(StringUtils::isNotBlank).anyMatch(format -> matchTimeFormat(time, format));
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static LocalDate parseDate(@Nullable String date, @Nullable String format) throws DateTimeParseException {
        return StringUtils.isAnyBlank(date, format) ? null : LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    @Nullable
    public static LocalDate parseDateQuietly(@Nullable String date, @Nullable String format) {
        try {
            return parseDate(date, format);
        } catch (DateTimeParseException ignored) {
        }
        return null;
    }

    @Nullable
    public static LocalDate parseDateGuessing(@Nullable String date) {
        return parseDateWithFormats(date, TemporalFormatCombo.DATE_FORMATS);
    }

    @Nullable
    public static LocalDate parseDateWithFormats(@Nullable String date, @Nullable String... formats) {
        return parseDateWithFormats(date, ArrayUtilsWraps.asList(formats));
    }

    @Nullable
    public static LocalDate parseDateWithFormats(@Nullable String date, @Nullable Collection<String> formats) {
        if (StringUtils.isBlank(date) || CollectionPlainWraps.isEmpty(formats)) {
            return null;
        }
        return formats.stream().map(format -> parseDateQuietly(date, format)).filter(Objects::nonNull).findFirst().orElse(null);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static LocalDateTime parseDateTime(@Nullable String dateTime, @Nullable String format) throws DateTimeParseException {
        return StringUtils.isAnyBlank(dateTime, format) ? null : LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
    }

    @Nullable
    public static LocalDateTime parseDateTimeQuietly(@Nullable String dateTime, @Nullable String format) {
        try {
            return parseDateTime(dateTime, format);
        } catch (DateTimeParseException ignored) {
        }
        return null;
    }

    @Nullable
    public static LocalDateTime parseDateTimeGuessing(@Nullable String dateTime) {
        return parseDateTimeWithFormats(dateTime, TemporalFormatCombo.DATETIME_FORMATS);
    }

    @Nullable
    public static LocalDateTime parseDateTimeWithFormats(@Nullable String dateTime, @Nullable String... formats) {
        return parseDateTimeWithFormats(dateTime, ArrayUtilsWraps.asList(formats));
    }

    @Nullable
    public static LocalDateTime parseDateTimeWithFormats(@Nullable String dateTime, @Nullable Collection<String> formats) {
        if (StringUtils.isBlank(dateTime) || CollectionPlainWraps.isEmpty(formats)) {
            return null;
        }
        return formats.stream().map(format -> parseDateTimeQuietly(dateTime, format)).filter(Objects::nonNull).findFirst().orElse(null);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static LocalTime parseTime(@Nullable String time, @Nullable String format) throws DateTimeParseException {
        return StringUtils.isAnyBlank(time, format) ? null : LocalTime.parse(time, DateTimeFormatter.ofPattern(format));
    }

    @Nullable
    public static LocalTime parseTimeQuietly(@Nullable String time, @Nullable String format) {
        try {
            return parseTime(time, format);
        } catch (DateTimeParseException ignored) {
        }
        return null;
    }

    @Nullable
    public static LocalTime parseTimeGuessing(@Nullable String time) {
        return parseTimeWithFormats(time, TemporalFormatCombo.TIME_FORMATS);
    }

    @Nullable
    public static LocalTime parseTimeWithFormats(@Nullable String time, @Nullable String... formats) {
        return parseTimeWithFormats(time, ArrayUtilsWraps.asList(formats));
    }

    @Nullable
    public static LocalTime parseTimeWithFormats(@Nullable String time, @Nullable Collection<String> formats) {
        if (StringUtils.isBlank(time) || CollectionPlainWraps.isEmpty(formats)) {
            return null;
        }
        return formats.stream().map(format -> parseTimeQuietly(time, format)).filter(Objects::nonNull).findFirst().orElse(null);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalDate plusTemporal(@Nullable LocalDate source, @Nullable TemporalAmount amount) {
        return ObjectUtils.anyNull(source, amount) ? source : source.plus(amount);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalDate plusTemporal(@Nullable LocalDate source, @Nullable Long amount, @Nullable TemporalUnit unit) {
        return ObjectUtils.anyNull(source, amount, unit) ? source : source.plus(amount, unit);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalDateTime plusTemporal(@Nullable LocalDateTime source, @Nullable TemporalAmount amount) {
        return ObjectUtils.anyNull(source, amount) ? source : source.plus(amount);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalDateTime plusTemporal(@Nullable LocalDateTime source, @Nullable Long amount, @Nullable TemporalUnit unit) {
        return ObjectUtils.anyNull(source, amount, unit) ? source : source.plus(amount, unit);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalTime plusTemporal(@Nullable LocalTime source, @Nullable TemporalAmount amount) {
        return ObjectUtils.anyNull(source, amount) ? source : source.plus(amount);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalTime plusTemporal(@Nullable LocalTime source, @Nullable Long amount, @Nullable TemporalUnit unit) {
        return ObjectUtils.anyNull(source, amount, unit) ? source : source.plus(amount, unit);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalDate minusTemporal(@Nullable LocalDate source, @Nullable TemporalAmount amount) {
        return ObjectUtils.anyNull(source, amount) ? source : source.minus(amount);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalDate minusTemporal(@Nullable LocalDate source, @Nullable Long amount, @Nullable TemporalUnit unit) {
        return ObjectUtils.anyNull(source, amount, unit) ? source : source.minus(amount, unit);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalDateTime minusTemporal(@Nullable LocalDateTime source, @Nullable TemporalAmount amount) {
        return ObjectUtils.anyNull(source, amount) ? source : source.minus(amount);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalDateTime minusTemporal(@Nullable LocalDateTime source, @Nullable Long amount, @Nullable TemporalUnit unit) {
        return ObjectUtils.anyNull(source, amount, unit) ? source : source.minus(amount, unit);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalTime minusTemporal(@Nullable LocalTime source, @Nullable TemporalAmount amount) {
        return ObjectUtils.anyNull(source, amount) ? source : source.minus(amount);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static LocalTime minusTemporal(@Nullable LocalTime source, @Nullable Long amount, @Nullable TemporalUnit unit) {
        return ObjectUtils.anyNull(source, amount, unit) ? source : source.minus(amount, unit);
    }

    @Nullable
    public static LocalDateTime ofEpochMillis(long milliseconds) {
        return ofEpochMillis(milliseconds, null);
    }

    /**
     * Returns a {@link java.time.LocalDateTime} from milliseconds of the epoch
     *
     * @param milliseconds milliseconds since the standard base time known as "the epoch" namely January 1, 1970, 00:00:00 GMT
     * @param zoneId the time-zone, which may be an offset
     *
     * @return a {@link java.time.LocalDateTime} from milliseconds of the epoch
     */
    @Nullable
    public static LocalDateTime ofEpochMillis(long milliseconds, @Nullable ZoneId zoneId) {
        return (milliseconds <= 0L) ? null : ofUtilDateTime(new Date(milliseconds), zoneId);
    }

    @Nullable
    public static LocalDate ofUtilDate(@Nullable Date date) {
        return ofUtilDate(date, null);
    }

    @Nullable
    public static LocalDate ofUtilDate(@Nullable Date date, @Nullable ZoneId zoneId) {
        LocalDateTime dateTime = ofUtilDateTime(date, zoneId);
        return (dateTime == null) ? null : dateTime.toLocalDate();
    }

    @Nullable
    public static LocalDateTime ofUtilDateTime(@Nullable Date date) {
        return ofUtilDateTime(date, null);
    }

    @Nullable
    public static LocalDateTime ofUtilDateTime(@Nullable Date date, @Nullable ZoneId zoneId) {
        return (date == null) ? null : LocalDateTime.ofInstant(date.toInstant(), ObjectUtils.defaultIfNull(zoneId, ZoneId.systemDefault()));
    }

    @Nullable
    public static LocalTime ofUtilTime(@Nullable Date date) {
        return ofUtilTime(date, null);
    }

    @Nullable
    public static LocalTime ofUtilTime(@Nullable Date date, @Nullable ZoneId zoneId) {
        LocalDateTime dateTime = ofUtilDateTime(date, zoneId);
        return (dateTime == null) ? null : dateTime.toLocalTime();
    }

    @Nullable
    public static Date toUtilDate(@Nullable LocalDate date) {
        return toUtilDate(date, null);
    }

    @Nullable
    public static Date toUtilDate(@Nullable LocalDate date, @Nullable ZoneId zoneId) {
        return (date == null) ? null : Date.from(date.atStartOfDay().atZone(ObjectUtils.defaultIfNull(zoneId, ZoneId.systemDefault())).toInstant());
    }

    @Nullable
    public static Date toUtilDate(@Nullable LocalDateTime dateTime) {
        return toUtilDate(dateTime, null);
    }

    @Nullable
    public static Date toUtilDate(@Nullable LocalDateTime dateTime, @Nullable ZoneId zoneId) {
        return (dateTime == null) ? null : Date.from(dateTime.atZone(ObjectUtils.defaultIfNull(zoneId, ZoneId.systemDefault())).toInstant());
    }

    @Nullable
    public static Date toUtilDate(@Nullable LocalTime time) {
        return (time == null) ? null : toUtilDate(LocalDateTime.of(LocalDate.now(), time));
    }
}
