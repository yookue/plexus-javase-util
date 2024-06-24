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


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;
import com.yookue.commonplexus.javaseutil.constant.ChineseVariantConst;
import com.yookue.commonplexus.javaseutil.constant.RegexVariantConst;
import com.yookue.commonplexus.javaseutil.enumeration.ChineseNumberType;
import com.yookue.commonplexus.javaseutil.util.MapPlainWraps;
import com.yookue.commonplexus.javaseutil.util.StringUtilsWraps;


/**
 * Utilities for Chinese number
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "JavadocDeclaration"})
public abstract class ChineseNumberUtils {
    @Nonnull
    public static String toNumberString(double amount) {
        return toNumberString(amount, 2);
    }

    @Nonnull
    public static String toNumberString(double amount, int fraction) {
        return toNumberString(amount, fraction, null);
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toNumberString(double amount, int fraction, @Nullable ChineseNumberType type) {
        return toNumberString(BigDecimal.valueOf(amount), fraction, type);
    }

    /**
     * @warning Since the float loss accuracy, this method is not guaranteed, use double/BigDecimal instead
     */
    @Nonnull
    public static String toNumberString(float amount) {
        return toNumberString(amount, 2);
    }

    /**
     * @warning Since the float loss accuracy, this method is not guaranteed, use double/BigDecimal instead
     */
    @Nonnull
    public static String toNumberString(float amount, int fraction) {
        return toNumberString(amount, fraction, null);
    }

    /**
     * @warning Since the float loss accuracy, this method is not guaranteed, use double/BigDecimal instead
     */
    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toNumberString(float amount, int fraction, @Nullable ChineseNumberType type) {
        return toNumberString(BigDecimal.valueOf(amount), fraction, type);
    }

    @Nonnull
    public static String toNumberString(int amount) {
        return toNumberString(amount, null);
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toNumberString(int amount, @Nullable ChineseNumberType type) {
        return toNumberString(new BigDecimal(amount), 0, type);
    }

    @Nonnull
    public static String toNumberString(long amount) {
        return toNumberString(amount, null);
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toNumberString(long amount, @Nullable ChineseNumberType type) {
        return toNumberString(BigDecimal.valueOf(amount), 0, type);
    }

    @Nonnull
    public static String toNumberString(short amount) {
        return toNumberString(amount, null);
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toNumberString(short amount, @Nullable ChineseNumberType type) {
        return toNumberString(new BigDecimal(amount), 0, type);
    }

    @Nullable
    public static String toNumberString(@Nullable Double amount) {
        return toNumberString(amount, 2);
    }

    @Nullable
    public static String toNumberString(@Nullable Double amount, int fraction) {
        return toNumberString(amount, fraction, null);
    }

    @Nullable
    public static String toNumberString(@Nullable Double amount, int fraction, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toNumberString(new BigDecimal(amount), fraction, type);
    }

    /**
     * @warning Since the Float loss accuracy, this method is not guaranteed, use Double/BigDecimal instead
     */
    @Nullable
    public static String toNumberString(@Nullable Float amount) {
        return toNumberString(amount, 2);
    }

    /**
     * @warning Since the Float loss accuracy, this method is not guaranteed, use Double/BigDecimal instead
     */
    @Nullable
    public static String toNumberString(@Nullable Float amount, int fraction) {
        return toNumberString(amount, fraction, null);
    }

    /**
     * @warning Since the Float loss accuracy, this method is not guaranteed, use Double/BigDecimal instead
     */
    @Nullable
    public static String toNumberString(@Nullable Float amount, int fraction, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toNumberString(new BigDecimal(amount), fraction, type);
    }

    @Nullable
    public static String toNumberString(@Nullable Integer amount) {
        return toNumberString(amount, null);
    }

    @Nullable
    public static String toNumberString(@Nullable Integer amount, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toNumberString(new BigDecimal(amount), 0, type);
    }

    @Nullable
    public static String toNumberString(@Nullable Long amount) {
        return toNumberString(amount, null);
    }

    @Nullable
    public static String toNumberString(@Nullable Long amount, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toNumberString(new BigDecimal(amount), 0, type);
    }

    @Nullable
    public static String toNumberString(@Nullable Short amount) {
        return toNumberString(amount, null);
    }

    @Nullable
    public static String toNumberString(@Nullable Short amount, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toNumberString(new BigDecimal(amount), 0, type);
    }

    @Nullable
    public static String toNumberString(@Nullable BigDecimal amount) {
        return toNumberString(amount, 2);
    }

    @Nullable
    public static String toNumberString(@Nullable BigDecimal amount, int fraction) {
        return toNumberString(amount, fraction, null);
    }

    @Nullable
    @SuppressWarnings("DuplicatedCode")
    public static String toNumberString(@Nullable BigDecimal amount, int fraction, @Nullable ChineseNumberType type) {
        if (amount == null) {
            return null;
        }
        ChineseNumberType numberType = ObjectUtils.defaultIfNull(type, ChineseNumberType.ZH_CN_GENERAL);
        String plainAmount = amount.toPlainString();
        boolean negative = StringUtilsWraps.startsWith(plainAmount, CharVariantConst.MINUS);
        String integerText = negative ? StringUtilsWraps.substringBetween(plainAmount, CharVariantConst.MINUS, CharVariantConst.DOT) : StringUtils.substringBefore(plainAmount, CharVariantConst.DOT);
        String decimalText = (fraction == 0) ? null : RegExUtils.removeAll(StringUtils.substring(StringUtils.substringAfter(plainAmount, CharVariantConst.DOT), 0, fraction), "0+$");    // $NON-NLS-1$
        // Integer part
        StringBuilder integerBuilder = new StringBuilder();
        Map<Integer, Integer> remainderUnits = MapPlainWraps.newHashMapWithin(0, 2, 2, 0, 3, 1);
        StringUtilsWraps.forEachCharsIndexing(integerText, (index, element) -> {
            int section = (StringUtils.length(integerText) - index) / 4, remainder = (StringUtils.length(integerText) - index) % 4;
            if (!(element == '0' && remainder == 1) && (numberType.isFinance() || !(element == '1' && remainder == 2))) {
                integerBuilder.append(numberType.isSimplified() ? ChineseVariantConst.NUMBER_CAPITALS_CN[element - '0'] : ChineseVariantConst.NUMBER_CAPITALS_TW[element - '0']);
            }
            if (MapPlainWraps.containsKey(remainderUnits, remainder)) {
                int sectionValue = MapUtils.getIntValue(remainderUnits, remainder);
                integerBuilder.append(numberType.isSimplified() ? ChineseVariantConst.NUMBER_SECTIONS_CN[sectionValue] : ChineseVariantConst.NUMBER_SECTIONS_TW[sectionValue]);
            }
            if (section > 0 && remainder == 1) {
                integerBuilder.append(numberType.isZhCN() ? ChineseVariantConst.POSITIVE_UNITS_CN[section - 1] : ChineseVariantConst.POSITIVE_UNITS_TW[section - 1]);
            }
        });
        if (negative) {
            integerBuilder.insert(0, numberType.isZhCN() ? ChineseVariantConst.NEGATIVE_CN : ChineseVariantConst.NEGATIVE_TW);
        }
        String zeroRegex1 = ChineseVariantConst.ZERO + CharVariantConst.SQUARE_BRACKET_LEFT + StringUtils.join(numberType.isSimplified() ? ChineseVariantConst.NUMBER_SECTIONS_CN : ChineseVariantConst.NUMBER_SECTIONS_TW) + CharVariantConst.SQUARE_BRACKET_RIGHT;
        String zeroRegex2 = ChineseVariantConst.ZERO + RegexVariantConst.MULTIPLE_SUFFIX;
        String integerPart = RegExUtils.replaceAll(RegExUtils.replaceAll(integerBuilder.toString(), zeroRegex1, ChineseVariantConst.ZERO), zeroRegex2, ChineseVariantConst.ZERO);
        // Decimal part
        StringBuilder decimalBuilder = new StringBuilder();
        StringUtilsWraps.forEachChars(decimalText, element -> decimalBuilder.append(numberType.isSimplified() ? ChineseVariantConst.NUMBER_CAPITALS_CN[element - '0'] : ChineseVariantConst.NUMBER_CAPITALS_TW[element - '0']));
        return StringUtilsWraps.appendIfSuffixNotBlank(integerPart, decimalBuilder, (numberType.isZhCN() ? ChineseVariantConst.DOT_CN : ChineseVariantConst.DOT_TW));
    }

    @Nullable
    public static String toNumberString(@Nullable BigInteger amount) {
        return toNumberString(amount, null);
    }

    @Nullable
    public static String toNumberString(@Nullable BigInteger amount, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toNumberString(new BigDecimal(amount), 0, type);
    }

    @Nullable
    public static String toNumberString(@Nullable Byte amount) {
        return toNumberString(amount, null);
    }

    @Nullable
    public static String toNumberString(@Nullable Byte amount, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toNumberString(new BigDecimal(amount), 0, type);
    }

    @Nullable
    public static String toNumberString(@Nullable Number amount) {
        return toNumberString(amount, null);
    }

    @Nullable
    public static String toNumberString(@Nullable Number amount, @Nullable ChineseNumberType type) {
        return toNumberString(amount, 2, type);
    }

    @Nullable
    public static String toNumberString(@Nullable Number amount, int fraction, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toNumberString(BigDecimal.valueOf(amount.doubleValue()), fraction, type);
    }

    @Nullable
    public static String toNumberString(@Nullable String amount) {
        return toNumberString(amount, 2);
    }

    @Nullable
    public static String toNumberString(@Nullable String amount, int fraction) {
        return toNumberString(amount, fraction, null);
    }

    @Nullable
    public static String toNumberString(@Nullable String amount, int fraction, @Nullable ChineseNumberType type) {
        if (StringUtils.isBlank(amount)) {
            return null;
        }
        String value = StringUtils.remove(amount, CharVariantConst.COMMA);
        if (StringUtils.isBlank(value) || !NumberUtils.isCreatable(value)) {
            return null;
        }
        return toNumberString(NumberUtils.createBigDecimal(value), fraction, type);
    }
}
