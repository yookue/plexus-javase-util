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


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.LocaleAreaCombo;


/**
 * Utilities for {@link java.util.Locale}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.LocaleUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class LocalePlainWraps {
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean equalsLanguage(@Nullable Locale target, @Nullable Locale comparison) {
        if (ObjectUtils.allNull(target, comparison)) {
            return true;
        }
        if (ObjectUtils.anyNull(target, comparison)) {
            return false;
        }
        return StringUtils.equals(target.getLanguage(), comparison.getLanguage());
    }

    public static boolean equalsLanguage(@Nullable Locale locale, @Nullable String language) {
        if (ObjectUtils.allNull(locale, language)) {
            return true;
        }
        if (locale == null || StringUtils.isBlank(language)) {
            return false;
        }
        return StringUtils.equals(locale.getLanguage(), language);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean equalsLanguageTag(@Nullable Locale target, @Nullable Locale comparison) {
        if (ObjectUtils.allNull(target, comparison)) {
            return true;
        }
        if (ObjectUtils.anyNull(target, comparison)) {
            return false;
        }
        return StringUtils.equals(target.toLanguageTag(), comparison.toLanguageTag());
    }

    public static boolean equalsLanguageTag(@Nullable Locale locale, @Nullable String languageTag) {
        if (ObjectUtils.allNull(locale, languageTag)) {
            return true;
        }
        if (locale == null || StringUtils.isBlank(languageTag)) {
            return false;
        }
        return StringUtils.equals(locale.toLanguageTag(), languageTag);
    }

    public static boolean equalsAnyLanguages(@Nullable Locale target, @Nullable Locale... comparisons) {
        return equalsAnyLanguages(target, ArrayUtilsWraps.asList(comparisons));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean equalsAnyLanguages(@Nullable Locale locale, @Nullable String... languages) {
        return locale != null && ArrayUtils.isNotEmpty(languages) && Arrays.stream(languages).filter(Objects::nonNull).anyMatch(element -> equalsLanguage(locale, element));
    }

    public static boolean equalsAnyLanguages(@Nullable Locale target, @Nullable Collection<Locale> comparisons) {
        return target != null && CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().filter(Objects::nonNull).anyMatch(element -> equalsLanguage(target, element));
    }

    public static boolean equalsAnyLanguageTags(@Nullable Locale target, @Nullable Locale... comparisons) {
        return equalsAnyLanguageTags(target, ArrayUtilsWraps.asList(comparisons));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean equalsAnyLanguageTags(@Nullable Locale locale, @Nullable String... languageTags) {
        return locale != null && ArrayUtils.isNotEmpty(languageTags) && Arrays.stream(languageTags).filter(Objects::nonNull).anyMatch(element -> equalsLanguageTag(locale, element));
    }

    public static boolean equalsAnyLanguageTags(@Nullable Locale target, @Nullable Collection<Locale> comparisons) {
        return target != null && CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().filter(Objects::nonNull).anyMatch(element -> equalsLanguageTag(target, element));
    }

    public static boolean isLanguageRangeParsable(@Nullable String ranges) {
        return parseLanguageRangeQuietly(ranges) != null;
    }

    public static boolean isChineseLanguage(@Nullable Locale target) {
        return equalsAnyLanguages(target, LocaleAreaCombo.CHINESE_LOCALES);
    }

    public static boolean isWesternLanguage(@Nullable Locale target) {
        return equalsAnyLanguages(target, LocaleAreaCombo.WESTERN_LOCALES);
    }

    @Nullable
    public static List<Locale.LanguageRange> parseLanguageRangeQuietly(@Nullable String ranges) {
        if (StringUtils.isBlank(ranges)) {
            return null;
        }
        try {
            return Locale.LanguageRange.parse(ranges);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Returns a well-formed IETF BCP 47 language tag representing the locale
     *
     * <p>Examples: <ul>
     * <li><tt>en-US</tt></li>
     * <li><tt>zh-CN</tt></li>
     *
     * @param locale the source locale to convert
     *
     * @return a BCP47 language tag representing the locale
     */
    @Nullable
    public static String toLanguageTag(@Nullable Locale locale) {
        return (locale == null) ? null : locale.toLanguageTag();
    }

    /**
     * Returns a string representation of the <code>Locale</code> object, consisting of language, country, variant, script, and extensions as below:
     * <blockquote>
     * language + "_" + country + "_" + (variant + "_#" | "#") + script + "-" + extensions
     * </blockquote>
     * <p>
     * This behavior is designed to support debugging and to be compatible with previous uses of <code>toString</code>
     * <p>
     * To represent a Locale as a String for interchange purposes, use {@link #toLanguageTag}
     *
     * <p>Examples: <ul>
     * <li><tt>en_US_WIN</tt></li>
     * <li><tt>zh_CN_#Hans</tt></li>
     *
     * @param locale the source locale to convert
     *
     * @return a string representation of the Locale, for debugging
     */
    @Nullable
    public static String toString(@Nullable Locale locale) {
        return (locale == null) ? null : locale.toString();
    }
}
