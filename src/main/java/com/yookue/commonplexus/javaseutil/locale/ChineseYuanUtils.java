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
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;
import com.yookue.commonplexus.javaseutil.constant.ChineseVariantConst;
import com.yookue.commonplexus.javaseutil.constant.RegexVariantConst;
import com.yookue.commonplexus.javaseutil.enumeration.ChineseNumberType;
import com.yookue.commonplexus.javaseutil.util.StringUtilsWraps;


/**
 * Utilities for Chinese Yuan
 *
 * @author David Hsing
 * @reference "https://www.cnblogs.com/weisenz/archive/2012/09/05/2672176.html"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public abstract class ChineseYuanUtils {
    @Nonnull
    public static String toYuanString(double amount) {
        return toYuanString(amount, 2);
    }

    @Nonnull
    public static String toYuanString(double amount, int fraction) {
        return toYuanString(amount, fraction, null);
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toYuanString(double amount, int fraction, @Nullable ChineseNumberType type) {
        return toYuanString(BigDecimal.valueOf(amount), fraction, type);
    }

    /**
     * @warning Since the float loss accuracy, this method is not guaranteed, use double/BigDecimal instead
     */
    @Nonnull
    public static String toYuanString(float amount) {
        return toYuanString(amount, 2);
    }

    /**
     * @warning Since the float loss accuracy, this method is not guaranteed, use double/BigDecimal instead
     */
    @Nonnull
    public static String toYuanString(float amount, int fraction) {
        return toYuanString(amount, fraction, null);
    }

    /**
     * @warning Since the float loss accuracy, this method is not guaranteed, use double/BigDecimal instead
     */
    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toYuanString(float amount, int fraction, @Nullable ChineseNumberType type) {
        return toYuanString(BigDecimal.valueOf(amount), fraction, type);
    }

    @Nonnull
    public static String toYuanString(int amount) {
        return toYuanString(amount, null);
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toYuanString(int amount, @Nullable ChineseNumberType type) {
        return toYuanString(new BigDecimal(amount), 0, type);
    }

    @Nonnull
    public static String toYuanString(long amount) {
        return toYuanString(amount, null);
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toYuanString(long amount, @Nullable ChineseNumberType type) {
        return toYuanString(BigDecimal.valueOf(amount), 0, type);
    }

    @Nonnull
    public static String toYuanString(short amount) {
        return toYuanString(amount, null);
    }

    @Nonnull
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toYuanString(short amount, @Nullable ChineseNumberType type) {
        return toYuanString(new BigDecimal(amount), 0, type);
    }

    @Nullable
    public static String toYuanString(@Nullable Double amount) {
        return toYuanString(amount, 2);
    }

    @Nullable
    public static String toYuanString(@Nullable Double amount, int fraction) {
        return toYuanString(amount, fraction, null);
    }

    @Nullable
    public static String toYuanString(@Nullable Double amount, int fraction, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toYuanString(new BigDecimal(amount), fraction, type);
    }

    /**
     * @warning Since the Float loss accuracy, this method is not guaranteed, use Double/BigDecimal instead
     */
    @Nullable
    public static String toYuanString(@Nullable Float amount) {
        return toYuanString(amount, 2);
    }

    /**
     * @warning Since the Float loss accuracy, this method is not guaranteed, use Double/BigDecimal instead
     */
    @Nullable
    public static String toYuanString(@Nullable Float amount, int fraction) {
        return toYuanString(amount, fraction, null);
    }

    /**
     * @warning Since the Float loss accuracy, this method is not guaranteed, use Double/BigDecimal instead
     */
    @Nullable
    public static String toYuanString(@Nullable Float amount, int fraction, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toYuanString(new BigDecimal(amount), fraction, type);
    }

    @Nullable
    public static String toYuanString(@Nullable Integer amount) {
        return toYuanString(amount, null);
    }

    @Nullable
    public static String toYuanString(@Nullable Integer amount, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toYuanString(new BigDecimal(amount), 0, null);
    }

    @Nullable
    public static String toYuanString(@Nullable Long amount) {
        return toYuanString(amount, null);
    }

    @Nullable
    public static String toYuanString(@Nullable Long amount, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toYuanString(new BigDecimal(amount), 0, type);
    }

    @Nullable
    public static String toYuanString(@Nullable Short amount) {
        return toYuanString(amount, null);
    }

    @Nullable
    public static String toYuanString(@Nullable Short amount, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toYuanString(new BigDecimal(amount), 0, type);
    }

    @Nullable
    public static String toYuanString(@Nullable BigDecimal amount) {
        return toYuanString(amount, 2);
    }

    @Nullable
    public static String toYuanString(@Nullable BigDecimal amount, int fraction) {
        return toYuanString(amount, fraction, null);
    }

    @Nullable
    @SuppressWarnings("DuplicatedCode")
    public static String toYuanString(@Nullable BigDecimal amount, int fraction, @Nullable ChineseNumberType type) {
        if (amount == null) {
            return null;
        }
        if (fraction < 0 || fraction > 4) {
            throw new IllegalArgumentException("fraction can not be less than 0 or greater than 4");    // $NON-NLS-1$
        }
        ChineseNumberType numberType = ObjectUtils.defaultIfNull(type, ChineseNumberType.ZH_CN_GENERAL);
        String plainAmount = amount.toPlainString();
        boolean negative = StringUtilsWraps.startsWith(plainAmount, CharVariantConst.HYPHEN);
        String integerText = negative ? StringUtilsWraps.substringBetween(plainAmount, CharVariantConst.HYPHEN, CharVariantConst.DOT) : StringUtils.substringBefore(plainAmount, CharVariantConst.DOT);
        String decimalText = (fraction == 0) ? null : RegExUtils.removeAll(StringUtils.substring(StringUtils.substringAfter(plainAmount, CharVariantConst.DOT), 0, fraction), "0+$");    // $NON-NLS-1$
        // Integer part
        StringBuilder integerBuilder = new StringBuilder();
        if (negative) {
            integerBuilder.append(numberType.isZhCN() ? ChineseVariantConst.NEGATIVE_CN : ChineseVariantConst.NEGATIVE_TW);
        }
        integerBuilder.append(ChineseNumberUtils.toNumberString(integerText, 0, numberType));
        integerBuilder.append(numberType.isZhCN() ? ChineseVariantConst.YUAN_CN : ChineseVariantConst.YUAN_TW);
        if (StringUtils.isBlank(decimalText)) {
            integerBuilder.append(ChineseVariantConst.NEATNESS);
            return integerBuilder.toString();
        }
        // Decimal part
        StringBuilder decimalBuilder = new StringBuilder();
        StringUtilsWraps.forEachCharsIndexing(decimalText, (index, element) -> {
            decimalBuilder.append(numberType.isSimplified() ? ChineseVariantConst.NUMBER_CAPITALS_CN[element - '0'] : ChineseVariantConst.NUMBER_CAPITALS_TW[element - '0']);
            decimalBuilder.append(numberType.isSimplified() ? ChineseVariantConst.NEGATIVE_UNITS_CN[index] : ChineseVariantConst.NEGATIVE_UNITS_TW[index]);
        });
        String zeroRegex1 = ChineseVariantConst.ZERO + CharVariantConst.SQUARE_BRACKET_LEFT + StringUtils.join(numberType.isSimplified() ? ChineseVariantConst.NEGATIVE_UNITS_CN : ChineseVariantConst.NEGATIVE_UNITS_TW) + CharVariantConst.SQUARE_BRACKET_RIGHT;
        String zeroRegex2 = ChineseVariantConst.ZERO + RegexVariantConst.MULTIPLE_SUFFIX;
        String decimalPart = RegExUtils.replaceAll(RegExUtils.replaceAll(decimalBuilder.toString(), zeroRegex1, ChineseVariantConst.ZERO), zeroRegex2, ChineseVariantConst.ZERO);
        return StringUtils.join(integerBuilder, decimalPart);
    }

    @Nullable
    public static String toYuanString(@Nullable BigInteger amount) {
        return toYuanString(amount, null);
    }

    @Nullable
    public static String toYuanString(@Nullable BigInteger amount, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toYuanString(new BigDecimal(amount), 0, type);
    }

    @Nullable
    public static String toYuanString(@Nullable Byte amount) {
        return toYuanString(amount, null);
    }

    @Nullable
    public static String toYuanString(@Nullable Byte amount, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toYuanString(new BigDecimal(amount), 0, type);
    }

    @Nullable
    public static String toYuanString(@Nullable Number amount) {
        return toYuanString(amount, 2);
    }

    @Nullable
    public static String toYuanString(@Nullable Number amount, int fraction) {
        return toYuanString(amount, fraction, null);
    }

    @Nullable
    public static String toYuanString(@Nullable Number amount, int fraction, @Nullable ChineseNumberType type) {
        return (amount == null) ? null : toYuanString(BigDecimal.valueOf(amount.doubleValue()), fraction, type);
    }

    @Nullable
    public static String toYuanString(@Nullable String amount) {
        return toYuanString(amount, 2);
    }

    @Nullable
    public static String toYuanString(@Nullable String amount, int fraction) {
        return toYuanString(amount, fraction, null);
    }

    @Nullable
    public static String toYuanString(@Nullable String amount, int fraction, @Nullable ChineseNumberType type) {
        if (StringUtils.isBlank(amount)) {
            return null;
        }
        String value = StringUtils.remove(amount, CharVariantConst.COMMA);
        if (StringUtils.isBlank(value) || !NumberUtils.isCreatable(value)) {
            return null;
        }
        return toYuanString(NumberUtils.createBigDecimal(value), fraction, type);
    }
}
