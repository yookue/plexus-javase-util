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


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import cn.unikue.commonplexus.javaseutil.constant.LocaleAreaCombo;


/**
 * Utilities for {@link java.util.Locale}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.LocaleUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class LocalePlainWraps {
    /**
     * Checks if two locales have the same language
     *
     * @param target the target locale to check
     * @param comparison the comparison locale to check against
     *
     * @return {@code true} if both locales have the same language, or both are null; {@code false} otherwise
     */
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

    /**
     * Checks if the locale has the specified language
     *
     * @param locale the locale to check
     * @param language the language to compare with
     *
     * @return {@code true} if the locale has the specified language, or both are null/blank; {@code false} otherwise
     */
    public static boolean equalsLanguage(@Nullable Locale locale, @Nullable String language) {
        if (ObjectUtils.allNull(locale, language)) {
            return true;
        }
        if (locale == null || StringUtils.isBlank(language)) {
            return false;
        }
        return StringUtils.equals(locale.getLanguage(), language);
    }

    /**
     * Checks if two locales have the same language tag
     *
     * @param target the target locale to check
     * @param comparison the comparison locale to check against
     *
     * @return {@code true} if both locales have the same language tag, or both are null; {@code false} otherwise
     */
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

    /**
     * Checks if the locale has the specified language tag
     *
     * @param locale the locale to check
     * @param languageTag the language tag to compare with
     *
     * @return {@code true} if the locale has the specified language tag, or both are null/blank; {@code false} otherwise
     */
    public static boolean equalsLanguageTag(@Nullable Locale locale, @Nullable String languageTag) {
        if (ObjectUtils.allNull(locale, languageTag)) {
            return true;
        }
        if (locale == null || StringUtils.isBlank(languageTag)) {
            return false;
        }
        return StringUtils.equals(locale.toLanguageTag(), languageTag);
    }

    /**
     * Checks if the target locale's language equals any of the comparison locales' languages
     *
     * @param target the target locale to check
     * @param comparisons the comparison locales to check against
     *
     * @return {@code true} if the target locale's language matches any of the comparison locales, {@code false} otherwise
     */
    public static boolean equalsAnyLanguages(@Nullable Locale target, @Nullable Locale... comparisons) {
        return equalsAnyLanguages(target, ArrayUtilsWraps.asList(comparisons));
    }

    /**
     * Checks if the locale's language equals any of the specified languages
     *
     * @param locale the locale to check
     * @param languages the languages to compare with
     *
     * @return {@code true} if the locale's language matches any of the specified languages, {@code false} otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean equalsAnyLanguages(@Nullable Locale locale, @Nullable String... languages) {
        return locale != null && ArrayUtils.isNotEmpty(languages) && Arrays.stream(languages).filter(Objects::nonNull).anyMatch(item -> equalsLanguage(locale, item));
    }

    /**
     * Checks if the target locale's language equals any of the comparison locales' languages in the collection
     *
     * @param target the target locale to check
     * @param comparisons the collection of comparison locales to check against
     *
     * @return {@code true} if the target locale's language matches any of the comparison locales, {@code false} otherwise
     */
    public static boolean equalsAnyLanguages(@Nullable Locale target, @Nullable Collection<Locale> comparisons) {
        return target != null && CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().filter(Objects::nonNull).anyMatch(item -> equalsLanguage(target, item));
    }

    /**
     * Checks if the target locale's language tag equals any of the comparison locales' language tags
     *
     * @param target the target locale to check
     * @param comparisons the comparison locales to check against
     *
     * @return {@code true} if the target locale's language tag matches any of the comparison locales, {@code false} otherwise
     */
    public static boolean equalsAnyLanguageTags(@Nullable Locale target, @Nullable Locale... comparisons) {
        return equalsAnyLanguageTags(target, ArrayUtilsWraps.asList(comparisons));
    }

    /**
     * Checks if the locale's language tag equals any of the specified language tags
     *
     * @param locale the locale to check
     * @param languageTags the language tags to compare with
     *
     * @return {@code true} if the locale's language tag matches any of the specified language tags, {@code false} otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean equalsAnyLanguageTags(@Nullable Locale locale, @Nullable String... languageTags) {
        return locale != null && ArrayUtils.isNotEmpty(languageTags) && Arrays.stream(languageTags).filter(Objects::nonNull).anyMatch(item -> equalsLanguageTag(locale, item));
    }

    /**
     * Checks if the target locale's language tag equals any of the comparison locales' language tags in the collection
     *
     * @param target the target locale to check
     * @param comparisons the collection of comparison locales to check against
     *
     * @return {@code true} if the target locale's language tag matches any of the comparison locales, {@code false} otherwise
     */
    public static boolean equalsAnyLanguageTags(@Nullable Locale target, @Nullable Collection<Locale> comparisons) {
        return target != null && CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().filter(Objects::nonNull).anyMatch(item -> equalsLanguageTag(target, item));
    }

    /**
     * Checks if the language range string is parsable
     *
     * @param ranges the language range string to check
     *
     * @return {@code true} if the language range string can be parsed, {@code false} otherwise
     */
    public static boolean isLanguageRangeParsable(@Nullable String ranges) {
        return parseLanguageRangeQuietly(ranges) != null;
    }

    /**
     * Checks if the locale is a Chinese language locale
     *
     * @param target the locale to check
     *
     * @return {@code true} if the locale is a Chinese language locale, {@code false} otherwise
     */
    public static boolean isChineseLanguage(@Nullable Locale target) {
        return equalsAnyLanguages(target, LocaleAreaCombo.CHINESE_LOCALES);
    }

    /**
     * Checks if the locale is a Western language locale
     *
     * @param target the locale to check
     *
     * @return {@code true} if the locale is a Western language locale, {@code false} otherwise
     */
    public static boolean isWesternLanguage(@Nullable Locale target) {
        return equalsAnyLanguages(target, LocaleAreaCombo.WESTERN_LOCALES);
    }

    /**
     * Parses the language range string quietly without throwing exceptions
     *
     * @param ranges the language range string to parse
     *
     * @return a list of language ranges, or {@code null} if the string is blank or cannot be parsed
     */
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
     * Returns an IETF BCP47 language tag representing the locale
     *
     * <p>Examples: <ul>
     * <li><tt>en-US</tt></li>
     * <li><tt>zh-CN</tt></li>
     *
     * @param locale The source locale to convert
     *
     * @return an IETF BCP47 language tag representing the locale
     */
    @Nullable
    public static String toLanguageTag(@Nullable Locale locale) {
        return (locale == null) ? null : locale.toLanguageTag();
    }

    /**
     * Return an IETF BCP47 language tag representing the locale, sheared some formats
     *
     * @param locale The source locale to convert
     *
     * @return an IETF BCP47 language tag representing the locale, sheared some formats
     */
    @Nullable
    public static String toLanguageTagSheared(@Nullable Locale locale) {
        String result = toLanguageTag(locale);
        if (StringUtils.isBlank(result)) {
            return null;
        }
        return switch (result) {
            case "en-CN", "en-TW" -> "en-US";    // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$
            case "zh-US" -> "zh-CN";    // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$
            default -> result;
        };
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
     * @param locale The source locale to convert
     *
     * @return a string representation of the Locale, for debugging
     */
    @Nullable
    public static String toString(@Nullable Locale locale) {
        return (locale == null) ? null : locale.toString();
    }
}
