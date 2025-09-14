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
    @Nonnull
    public static String getSlashYear() {
        return StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatCurrentDate(TemporalFormatConst.RAW_YYYY));
    }

    @Nonnull
    public static String getSlashYearMonth() {
        return getSlashYearMonth(true);
    }

    @Nonnull
    public static String getSlashYearMonth(boolean withYear) {
        LocalDate date = LocalDateWraps.getCurrentDate();
        String year = !withYear ? null : StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatDate(date, TemporalFormatConst.RAW_YYYY));
        String yearMonth = StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatDate(date, TemporalFormatConst.RAW_YYYYMM));
        return StringUtils.join(year, yearMonth);
    }

    @Nonnull
    public static String getSlashYearMonthDay() {
        return getSlashYearMonthDay(true, true);
    }

    @Nonnull
    public static String getSlashYearMonthDay(boolean withYear, boolean withYearMonth) {
        LocalDate date = LocalDateWraps.getCurrentDate();
        String year = !withYear ? null : StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatDate(date, TemporalFormatConst.RAW_YYYY));
        String yearMonth = !withYearMonth ? null : StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatDate(date, TemporalFormatConst.RAW_YYYYMM));
        String yearMonthDay = StringUtils.join(CharVariantConst.SLASH, LocalDateWraps.formatDate(date, TemporalFormatConst.RAW_YYYYMMDD));
        return StringUtils.join(year, yearMonth, yearMonthDay);
    }
}
