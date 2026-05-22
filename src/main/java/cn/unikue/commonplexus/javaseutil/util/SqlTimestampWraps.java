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


import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import cn.unikue.commonplexus.javaseutil.constant.TemporalFormatCombo;


/**
 * Utilities for {@link java.sql.Timestamp}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class SqlTimestampWraps {
    /**
     * Returns a Timestamp for yesterday at the current time
     *
     * @return yesterday's timestamp
     */
    @Nonnull
    public static Timestamp getYesterdayTimestamp() {
        return Timestamp.from(JdkDateWraps.getYesterdayDateTime().toInstant());
    }

    /**
     * Returns a Timestamp for the current date and time
     *
     * @return the current timestamp
     */
    @Nonnull
    public static Timestamp getCurrentTimestamp() {
        return Timestamp.from(JdkDateWraps.getCurrentDateTime().toInstant());
    }

    /**
     * Returns a Timestamp for tomorrow at the current time
     *
     * @return tomorrow's timestamp
     */
    @Nonnull
    public static Timestamp getTomorrowTimestamp() {
        return Timestamp.from(JdkDateWraps.getTomorrowDateTime().toInstant());
    }

    /**
     * Parses a string to Timestamp using the specified format
     *
     * @param timestamp The timestamp string to parse
     * @param format The date/time format pattern
     *
     * @return the parsed Timestamp, or null if parsing fails
     * @throws ParseException if the string cannot be parsed
     */
    @Nullable
    public static Timestamp parseTimestamp(@Nullable String timestamp, @Nullable String format) throws ParseException {
        return parseTimestamp(timestamp, format, null, null);
    }

    /**
     * Parses a string to Timestamp using the specified format, timezone and locale
     *
     * @param timestamp The timestamp string to parse
     * @param format The date/time format pattern
     * @param zone The timezone to use for parsing
     * @param locale The locale to use for parsing
     *
     * @return the parsed Timestamp, or null if parsing fails
     * @throws ParseException if the string cannot be parsed
     */
    @Nullable
    public static Timestamp parseTimestamp(@Nullable String timestamp, @Nullable String format, @Nullable TimeZone zone, @Nullable Locale locale) throws ParseException {
        Date result = JdkDateWraps.parseDate(timestamp, format, zone, locale);
        return (result == null) ? null : Timestamp.from(result.toInstant());
    }

    /**
     * Parses a string to Timestamp quietly without throwing exceptions
     *
     * @param timestamp The timestamp string to parse
     * @param format The date/time format pattern
     *
     * @return the parsed Timestamp, or null if parsing fails
     */
    @Nullable
    public static Timestamp parseTimestampQuietly(@Nullable String timestamp, @Nullable String format) {
        return parseTimestampQuietly(timestamp, format, null, null);
    }

    /**
     * Parses a string to Timestamp quietly with timezone and locale support
     *
     * @param timestamp The timestamp string to parse
     * @param format The date/time format pattern
     * @param zone The timezone to use for parsing
     * @param locale The locale to use for parsing
     *
     * @return the parsed Timestamp, or null if parsing fails
     */
    @Nullable
    public static Timestamp parseTimestampQuietly(@Nullable String timestamp, @Nullable String format, @Nullable TimeZone zone, @Nullable Locale locale) {
        try {
            return parseTimestamp(timestamp, format, zone, locale);
        } catch (ParseException ignored) {
        }
        return null;
    }

    /**
     * Parses a string to Timestamp by guessing the format from common datetime patterns
     *
     * @param timestamp The timestamp string to parse
     *
     * @return the parsed Timestamp, or null if no format matches
     */
    @Nullable
    public static Timestamp parseTimestampGuessing(@Nullable String timestamp) {
        return parseTimestampWithFormats(timestamp, TemporalFormatCombo.DATETIME_FORMATS);
    }

    /**
     * Parses a string to Timestamp trying multiple formats
     *
     * @param timestamp The timestamp string to parse
     * @param formats The array of format patterns to try
     *
     * @return the parsed Timestamp, or null if no format matches
     */
    @Nullable
    public static Timestamp parseTimestampWithFormats(@Nullable String timestamp, @Nullable String... formats) {
        return parseTimestampWithFormats(timestamp, ArrayUtilsWraps.asList(formats));
    }

    /**
     * Parses a string to Timestamp trying multiple formats from a collection
     *
     * @param timestamp The timestamp string to parse
     * @param formats The collection of format patterns to try
     *
     * @return the parsed Timestamp, or null if no format matches
     */
    @Nullable
    public static Timestamp parseTimestampWithFormats(@Nullable String timestamp, @Nullable Collection<String> formats) {
        return parseTimestampWithFormats(timestamp, null, null, formats);
    }

    /**
     * Parses a string to Timestamp trying multiple formats with timezone and locale
     *
     * @param timestamp The timestamp string to parse
     * @param zone The timezone to use for parsing
     * @param locale The locale to use for parsing
     * @param formats The array of format patterns to try
     *
     * @return the parsed Timestamp, or null if no format matches
     */
    @Nullable
    public static Timestamp parseTimestampWithFormats(@Nullable String timestamp, @Nullable TimeZone zone, @Nullable Locale locale, @Nullable String... formats) {
        return parseTimestampWithFormats(timestamp, zone, locale, ArrayUtilsWraps.asList(formats));
    }

    /**
     * Parses a string to Timestamp trying multiple formats from collection with timezone and locale
     *
     * @param timestamp The timestamp string to parse
     * @param zone The timezone to use for parsing
     * @param locale The locale to use for parsing
     * @param formats The collection of format patterns to try
     *
     * @return the parsed Timestamp, or null if no format matches
     */
    @Nullable
    public static Timestamp parseTimestampWithFormats(@Nullable String timestamp, @Nullable TimeZone zone, @Nullable Locale locale, @Nullable Collection<String> formats) {
        Date result = JdkDateWraps.parseDateFormats(timestamp, zone, locale, formats);
        return (result == null) ? null : Timestamp.from(result.toInstant());
    }

    /**
     * Returns the start of day (00:00:00.000) for the given timestamp
     *
     * @param timestamp The timestamp to get day start from
     *
     * @return the day start timestamp, or null if input is null
     */
    @Nullable
    public static Timestamp getDayStartTimestamp(@Nullable Timestamp timestamp) {
        return getDayStartTimestamp(timestamp, null, null);
    }

    /**
     * Returns the start of day with timezone support
     *
     * @param timestamp The timestamp to get day start from
     * @param zone The timezone to use
     *
     * @return the day start timestamp, or null if input is null
     */
    @Nullable
    public static Timestamp getDayStartTimestamp(@Nullable Timestamp timestamp, @Nullable TimeZone zone) {
        return getDayStartTimestamp(timestamp, zone, null);
    }

    /**
     * Returns the start of day with locale support
     *
     * @param timestamp The timestamp to get day start from
     * @param locale The locale to use
     *
     * @return the day start timestamp, or null if input is null
     */
    @Nullable
    public static Timestamp getDayStartTimestamp(@Nullable Timestamp timestamp, @Nullable Locale locale) {
        return getDayStartTimestamp(timestamp, null, locale);
    }

    /**
     * Returns the start of day with timezone and locale support
     *
     * @param timestamp The timestamp to get day start from
     * @param zone The timezone to use
     * @param locale The locale to use
     *
     * @return the day start timestamp, or null if input is null
     */
    @Nullable
    public static Timestamp getDayStartTimestamp(@Nullable Timestamp timestamp, @Nullable TimeZone zone, @Nullable Locale locale) {
        Date result = JdkDateWraps.getDayStartDate(timestamp, zone, locale);
        return (result == null) ? null : Timestamp.from(result.toInstant());
    }

    /**
     * Returns the end of day (23:59:59.999) for the given timestamp
     *
     * @param timestamp The timestamp to get day end from
     *
     * @return the day end timestamp, or null if input is null
     */
    @Nullable
    public static Timestamp getDayEndTimestamp(@Nullable Timestamp timestamp) {
        return getDayEndTimestamp(timestamp, null, null);
    }

    /**
     * Returns the end of day with timezone support
     *
     * @param timestamp The timestamp to get day end from
     * @param zone The timezone to use
     *
     * @return the day end timestamp, or null if input is null
     */
    @Nullable
    public static Timestamp getDayEndTimestamp(@Nullable Timestamp timestamp, @Nullable TimeZone zone) {
        return getDayEndTimestamp(timestamp, zone, null);
    }

    /**
     * Returns the end of day with locale support
     *
     * @param timestamp The timestamp to get day end from
     * @param locale The locale to use
     *
     * @return the day end timestamp, or null if input is null
     */
    @Nullable
    public static Timestamp getDayEndTimestamp(@Nullable Timestamp timestamp, @Nullable Locale locale) {
        return getDayEndTimestamp(timestamp, null, locale);
    }

    /**
     * Returns the end of day with timezone and locale support
     *
     * @param timestamp The timestamp to get day end from
     * @param zone The timezone to use
     * @param locale The locale to use
     *
     * @return the day end timestamp, or null if input is null
     */
    @Nullable
    public static Timestamp getDayEndTimestamp(@Nullable Timestamp timestamp, @Nullable TimeZone zone, @Nullable Locale locale) {
        Date result = JdkDateWraps.getDayEndDate(timestamp, zone, locale);
        return (result == null) ? null : Timestamp.from(result.toInstant());
    }
}
