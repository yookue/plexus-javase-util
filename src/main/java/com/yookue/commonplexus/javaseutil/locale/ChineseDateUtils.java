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

package com.yookue.commonplexus.javaseutil.locale;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.ChineseVariantConst;
import com.yookue.commonplexus.javaseutil.enumeration.ChineseNumberType;
import com.yookue.commonplexus.javaseutil.util.StringUtilsWraps;
import com.yookue.commonplexus.javaseutil.util.UtilDateWraps;


/**
 * Utilities for Chinese date
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ChineseDateUtils {
    @Nullable
    public static String toDateString(@Nullable Date date) {
        return toDateString(date, false);
    }

    @Nullable
    public static String toDateString(@Nullable Date date, boolean prefixIfAd) {
        return toDateString(date, prefixIfAd, null);
    }

    /**
     * Returns the Chinese date string with the given {@link java.util.Date}
     *
     * @param date the source {@link java.util.Date}
     * @param prefixIfAd indicates to prepend the AD prefix when the year is positive or not
     * @param type the Chinese number type enumeration
     *
     * @return the Chinese date string with the given {@link java.util.Date}
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toDateString(@Nullable Date date, boolean prefixIfAd, @Nullable ChineseNumberType type) {
        return (date == null) ? null : toDateString(UtilDateWraps.getYear(date), UtilDateWraps.getMonth(date), UtilDateWraps.getDayOfMonth(date), prefixIfAd, type);
    }

    @Nullable
    public static String toDateString(@Nullable LocalDate date) {
        return toDateString(date, false);
    }

    @Nullable
    public static String toDateString(@Nullable LocalDate date, boolean prefixIfAd) {
        return toDateString(date, prefixIfAd, null);
    }

    /**
     * Returns the Chinese date string with the given {@link java.time.LocalDate}
     *
     * @param date the source {@link java.time.LocalDate}
     * @param prefixIfAd indicates to prepend the AD prefix when the year is positive or not
     * @param type the Chinese number type enumeration
     *
     * @return the Chinese date string with the given {@link java.time.LocalDate}
     */
    @Nullable
    public static String toDateString(@Nullable LocalDate date, boolean prefixIfAd, @Nullable ChineseNumberType type) {
        return (date == null) ? null : toDateString(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), prefixIfAd, type);
    }

    @Nullable
    public static String toDateString(@Nullable LocalDateTime date) {
        return toDateString(date, false);
    }

    @Nullable
    public static String toDateString(@Nullable LocalDateTime date, boolean prefixIfAd) {
        return toDateString(date, prefixIfAd, null);
    }

    /**
     * Returns the Chinese date string with the given {@link java.time.LocalDateTime}
     *
     * @param dateTime the source {@link java.time.LocalDateTime}
     * @param prefixIfAd indicates to prepend the AD prefix when the year is positive or not
     * @param type the Chinese number type enumeration
     *
     * @return the Chinese date string with the given {@link java.time.LocalDateTime}
     */
    @Nullable
    public static String toDateString(@Nullable LocalDateTime dateTime, boolean prefixIfAd, @Nullable ChineseNumberType type) {
        return (dateTime == null) ? null : toDateString(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth(), prefixIfAd, type);
    }

    /**
     * Returns a Chinese string that represents the specified date
     *
     * @param prefixIfAd indicates to prepend the AD prefix when the year is positive or not
     * @param type the Chinese number type enumeration
     *
     * @return a Chinese string that represents the specified date
     *
     * @reference "https://wenku.baidu.com/view/b48efd8382eb6294dd88d0d233d4b14e85243ebc.html"
     */
    @Nonnull
    @SuppressWarnings({"DuplicatedCode", "JavadocDeclaration", "JavadocLinkAsPlainText"})
    private static String toDateString(int year, int monthOfYear, int dayOfMonth, boolean prefixIfAd, @Nullable ChineseNumberType type) {
        if (monthOfYear < 1 || monthOfYear > 12 || dayOfMonth < 1 || dayOfMonth > 31) {
            throw new IllegalArgumentException();
        }
        ChineseNumberType numberType = ObjectUtils.defaultIfNull(type, ChineseNumberType.ZH_CN_GENERAL);
        // Calculate year
        StringBuilder yearBuilder = new StringBuilder();
        if (year < 0) {
            yearBuilder.append(ChineseVariantConst.BEFORE_CHRIST);
            String yearText = StringUtils.substring(String.valueOf(year), 1);
            yearBuilder.append(ChineseNumberUtils.toNumberString(yearText, 0, type));
            yearBuilder.append(ChineseVariantConst.DATE_UNITS[0]);
        } else if (year == 0) {
            yearBuilder.append(ChineseVariantConst.CHRISTIAN_ERA);
        } else {
            if (prefixIfAd) {
                yearBuilder.append(ChineseVariantConst.ANNO_DOMINI);
            }
            String yearText = String.valueOf(year);
            StringUtilsWraps.forEachChars(yearText, element -> yearBuilder.append(numberType.isSimplified() ? ChineseVariantConst.NUMBER_CAPITALS_CN[element - '0'] : ChineseVariantConst.NUMBER_CAPITALS_TW[element - '0']));
            yearBuilder.append(ChineseVariantConst.DATE_UNITS[0]);
        }
        // Calculate month
        StringBuilder monthBuilder = new StringBuilder();
        String monthText = String.valueOf(monthOfYear);
        StringUtilsWraps.forEachCharsIndexing(monthText, (index, element) -> {
            if (element > '0' && (numberType.isFinance() || (index == 0 && (monthOfYear < 9 || (monthOfYear > 9 && element > '1'))) || index == 1)) {
                monthBuilder.append(numberType.isSimplified() ? ChineseVariantConst.NUMBER_CAPITALS_CN[element - '0'] : ChineseVariantConst.NUMBER_CAPITALS_TW[element - '0']);
            }
            if (index == 0 && monthOfYear > 9) {
                monthBuilder.append(numberType.isSimplified() ? ChineseVariantConst.NUMBER_SECTIONS_CN[0] : ChineseVariantConst.NUMBER_SECTIONS_TW[0]);
            }
        });
        if (numberType.isFinance() && (monthOfYear == 1 || monthOfYear == 2 || monthOfYear == 10)) {
            monthBuilder.insert(0, ChineseVariantConst.ZERO);
        }
        monthBuilder.append(ChineseVariantConst.DATE_UNITS[1]);
        // Calculate day
        StringBuilder dayBuilder = new StringBuilder();
        String dayText = String.valueOf(dayOfMonth);
        StringUtilsWraps.forEachCharsIndexing(dayText, (index, element) -> {
            if (element > '0' && (numberType.isFinance() || (index == 0 && (dayOfMonth < 9 || (dayOfMonth > 9 && element > '1'))) || index == 1)) {
                dayBuilder.append(numberType.isSimplified() ? ChineseVariantConst.NUMBER_CAPITALS_CN[element - '0'] : ChineseVariantConst.NUMBER_CAPITALS_TW[element - '0']);
            }
            if (index == 0 && dayOfMonth > 9) {
                dayBuilder.append(numberType.isSimplified() ? ChineseVariantConst.NUMBER_SECTIONS_CN[0] : ChineseVariantConst.NUMBER_SECTIONS_TW[0]);
            }
        });
        if (numberType.isFinance() && (dayOfMonth < 21 || dayOfMonth == 30)) {
            dayBuilder.insert(0, ChineseVariantConst.ZERO);
        }
        dayBuilder.append(ChineseVariantConst.DATE_UNITS[2]);
        return StringUtils.join(yearBuilder, monthBuilder, dayBuilder);
    }
}
