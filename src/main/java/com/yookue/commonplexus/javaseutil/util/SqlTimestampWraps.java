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


import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.yookue.commonplexus.javaseutil.constant.TemporalFormatCombo;


/**
 * Utilities for {@link java.sql.Timestamp}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class SqlTimestampWraps {
    @Nonnull
    public static Timestamp getYesterdayTimestamp() {
        return Timestamp.from(UtilDateWraps.getYesterdayDateTime().toInstant());
    }

    @Nonnull
    public static Timestamp getCurrentTimestamp() {
        return Timestamp.from(UtilDateWraps.getCurrentDateTime().toInstant());
    }

    @Nonnull
    public static Timestamp getTomorrowTimestamp() {
        return Timestamp.from(UtilDateWraps.getTomorrowDateTime().toInstant());
    }

    @Nullable
    public static Timestamp parseTimestamp(@Nullable String timestamp, @Nullable String format) throws ParseException {
        return parseTimestamp(timestamp, format, null, null);
    }

    @Nullable
    public static Timestamp parseTimestamp(@Nullable String timestamp, @Nullable String format, @Nullable TimeZone zone, @Nullable Locale locale) throws ParseException {
        Date result = UtilDateWraps.parseDateTime(timestamp, format, zone, locale);
        return (result == null) ? null : Timestamp.from(result.toInstant());
    }

    @Nullable
    public static Timestamp parseTimestampQuietly(@Nullable String timestamp, @Nullable String format) {
        return parseTimestampQuietly(timestamp, format, null, null);
    }

    @Nullable
    public static Timestamp parseTimestampQuietly(@Nullable String timestamp, @Nullable String format, @Nullable TimeZone zone, @Nullable Locale locale) {
        try {
            return parseTimestamp(timestamp, format, zone, locale);
        } catch (ParseException ignored) {
        }
        return null;
    }

    @Nullable
    public static Timestamp parseTimestampGuessing(@Nullable String timestamp) {
        return parseTimestampWithFormats(timestamp, TemporalFormatCombo.DATETIME_FORMATS);
    }

    @Nullable
    public static Timestamp parseTimestampWithFormats(@Nullable String timestamp, @Nullable String... formats) {
        return parseTimestampWithFormats(timestamp, ArrayUtilsWraps.asList(formats));
    }

    @Nullable
    public static Timestamp parseTimestampWithFormats(@Nullable String timestamp, @Nullable Collection<String> formats) {
        return parseTimestampWithFormats(timestamp, null, null, formats);
    }

    @Nullable
    public static Timestamp parseTimestampWithFormats(@Nullable String timestamp, @Nullable TimeZone zone, @Nullable Locale locale, @Nullable String... formats) {
        return parseTimestampWithFormats(timestamp, zone, locale, ArrayUtilsWraps.asList(formats));
    }

    @Nullable
    public static Timestamp parseTimestampWithFormats(@Nullable String timestamp, @Nullable TimeZone zone, @Nullable Locale locale, @Nullable Collection<String> formats) {
        Date result = UtilDateWraps.parseDateTimeWithFormats(timestamp, zone, locale, formats);
        return (result == null) ? null : Timestamp.from(result.toInstant());
    }

    @Nullable
    public static Timestamp getDayStartTimestamp(@Nullable Timestamp timestamp) {
        return getDayStartTimestamp(timestamp, null, null);
    }

    @Nullable
    public static Timestamp getDayStartTimestamp(@Nullable Timestamp timestamp, @Nullable TimeZone zone) {
        return getDayStartTimestamp(timestamp, zone, null);
    }

    @Nullable
    public static Timestamp getDayStartTimestamp(@Nullable Timestamp timestamp, @Nullable Locale locale) {
        return getDayStartTimestamp(timestamp, null, locale);
    }

    @Nullable
    public static Timestamp getDayStartTimestamp(@Nullable Timestamp timestamp, @Nullable TimeZone zone, @Nullable Locale locale) {
        Date result = UtilDateWraps.getDayStartDate(timestamp, zone, locale);
        return (result == null) ? null : Timestamp.from(result.toInstant());
    }

    @Nullable
    public static Timestamp getDayEndTimestamp(@Nullable Timestamp timestamp) {
        return getDayEndTimestamp(timestamp, null, null);
    }

    @Nullable
    public static Timestamp getDayEndTimestamp(@Nullable Timestamp timestamp, @Nullable TimeZone zone) {
        return getDayEndTimestamp(timestamp, zone, null);
    }

    @Nullable
    public static Timestamp getDayEndTimestamp(@Nullable Timestamp timestamp, @Nullable Locale locale) {
        return getDayEndTimestamp(timestamp, null, locale);
    }

    @Nullable
    public static Timestamp getDayEndTimestamp(@Nullable Timestamp timestamp, @Nullable TimeZone zone, @Nullable Locale locale) {
        Date result = UtilDateWraps.getDayEndDate(timestamp, zone, locale);
        return (result == null) ? null : Timestamp.from(result.toInstant());
    }
}
