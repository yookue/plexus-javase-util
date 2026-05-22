/*
 * Copyright (c) 2025 Unikue Ltd. All rights reserved.
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
import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;
import cn.unikue.commonplexus.javaseutil.constant.CharVariantConst;
import cn.unikue.commonplexus.javaseutil.constant.TemporalFormatConst;


/**
 * Utilities for temporal plain
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class TemporalPlainWraps {
    /**
     * Returns the current year with slash prefix (e.g., "/2025")
     *
     * @return the current year string with slash prefix
     */
    @Nonnull
    public static String getSlashYear() {
        return StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatCurrentDate(TemporalFormatConst.RAW_YYYY));
    }

    /**
     * Returns the current year and month with slash prefixes (e.g., "/2025/202512")
     *
     * @return the current year-month string with slash prefixes
     */
    @Nonnull
    public static String getSlashYearMonth() {
        return getSlashYearMonth(true);
    }

    /**
     * Returns the current year and/or month with slash prefixes based on flags
     *
     * @param withYear {@code true} to include year prefix, {@code false} to omit it
     *
     * @return the formatted year-month string with slash prefixes (e.g., "/2025/202512" or "/202512")
     */
    @Nonnull
    public static String getSlashYearMonth(boolean withYear) {
        LocalDate date = LocalDateWraps.getCurrentDate();
        String year = !withYear ? null : StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatDate(date, TemporalFormatConst.RAW_YYYY));
        String yearMonth = StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatDate(date, TemporalFormatConst.RAW_YYYYMM));
        return StringUtils.join(year, yearMonth);
    }

    /**
     * Returns the current year, month and day with slash prefixes (e.g., "/2025/202512/20251225")
     *
     * @return the current year-month-day string with slash prefixes
     */
    @Nonnull
    public static String getSlashYearMonthDay() {
        return getSlashYearMonthDay(true, true);
    }

    /**
     * Returns the current year, month and/or day with slash prefixes based on flags
     *
     * @param withYear {@code true} to include year prefix, {@code false} to omit it
     * @param withYearMonth {@code true} to include year-month prefix, {@code false} to omit it
     *
     * @return the formatted date string with slash prefixes (e.g., "/2025/202512/20251225", "/202512/20251225", or "/20251225")
     */
    @Nonnull
    public static String getSlashYearMonthDay(boolean withYear, boolean withYearMonth) {
        LocalDate date = LocalDateWraps.getCurrentDate();
        String year = !withYear ? null : StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatDate(date, TemporalFormatConst.RAW_YYYY));
        String yearMonth = !withYearMonth ? null : StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatDate(date, TemporalFormatConst.RAW_YYYYMM));
        String yearMonthDay = StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatDate(date, TemporalFormatConst.RAW_YYYYMMDD));
        return StringUtils.join(year, yearMonth, yearMonthDay);
    }
}
