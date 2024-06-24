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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;
import com.yookue.commonplexus.javaseutil.constant.RegexVariantCombo;
import com.yookue.commonplexus.javaseutil.constant.RegexVariantConst;
import com.yookue.commonplexus.javaseutil.constant.SymbolVariantConst;


/**
 * Utilities for {@link org.apache.commons.lang3.RegExUtils}
 *
 * @author David Hsing
 * @reference "https://www3.ntu.edu.sg/home/ehchua/programming/howto/Regexe.html"
 * @reference "https://regexr.com/"
 * @reference "http://www.regexlab.com/zh/regref.htm"
 * @see org.apache.commons.lang3.RegExUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public abstract class RegexUtilsWraps {
    @SafeVarargs
    public static <T extends CharSequence> boolean allAlphabetic(@Nullable T... sequences) {
        return allAlphabetic(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean allAlphabetic(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().allMatch(RegexUtilsWraps::isAlphabetic);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean anyAlphabetic(@Nullable T... sequences) {
        return anyAlphabetic(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean anyAlphabetic(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(RegexUtilsWraps::isAlphabetic);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean allAlphanumeric(@Nullable T... sequences) {
        return allAlphanumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean allAlphanumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().allMatch(RegexUtilsWraps::isAlphanumeric);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean anyAlphanumeric(@Nullable T... sequences) {
        return anyAlphanumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean anyAlphanumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(RegexUtilsWraps::isAlphanumeric);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean allNumeric(@Nullable T... sequences) {
        return allNumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean allNumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().allMatch(RegexUtilsWraps::isNumeric);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean anyNumeric(@Nullable T... sequences) {
        return anyNumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean anyNumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(RegexUtilsWraps::isNumeric);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean allWord(@Nullable T... sequences) {
        return allWord(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean allWord(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().allMatch(RegexUtilsWraps::isWord);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean anyWord(@Nullable T... sequences) {
        return anyWord(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean anyWord(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(RegexUtilsWraps::isWord);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean allWordHyphen(@Nullable T... sequences) {
        return allWordHyphen(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean allWordHyphen(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().allMatch(RegexUtilsWraps::isWordHyphen);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean anyWordHyphen(@Nullable T... sequences) {
        return anyWordHyphen(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean anyWordHyphen(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(RegexUtilsWraps::isWordHyphen);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean allStartsWithAlphabetic(@Nullable T... sequences) {
        return allStartsWithAlphabetic(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean allStartsWithAlphabetic(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().allMatch(RegexUtilsWraps::startsWithAlphabetic);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean anyStartsWithAlphabetic(@Nullable T... sequences) {
        return anyStartsWithAlphabetic(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean anyStartsWithAlphabetic(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(RegexUtilsWraps::startsWithAlphabetic);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean allStartsWithAlphanumeric(@Nullable T... sequences) {
        return allStartsWithAlphanumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean allStartsWithAlphanumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().allMatch(RegexUtilsWraps::startsWithAlphanumeric);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean anyStartsWithAlphanumeric(@Nullable T... sequences) {
        return anyStartsWithAlphanumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean anyStartsWithAlphanumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(RegexUtilsWraps::startsWithAlphanumeric);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean allStartsWithNumeric(@Nullable T... sequences) {
        return allStartsWithNumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean allStartsWithNumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().allMatch(RegexUtilsWraps::startsWithNumeric);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean anyStartsWithNumeric(@Nullable T... sequences) {
        return anyStartsWithNumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean anyStartsWithNumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(RegexUtilsWraps::startsWithNumeric);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean allEndsWithAlphabetic(@Nullable T... sequences) {
        return allEndsWithAlphabetic(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean allEndsWithAlphabetic(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().allMatch(RegexUtilsWraps::endsWithAlphabetic);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean anyEndsWithAlphabetic(@Nullable T... sequences) {
        return anyEndsWithAlphabetic(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean anyEndsWithAlphabetic(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(RegexUtilsWraps::endsWithAlphabetic);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean allEndsWithAlphanumeric(@Nullable T... sequences) {
        return allEndsWithAlphanumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean allEndsWithAlphanumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().allMatch(RegexUtilsWraps::endsWithAlphanumeric);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean anyEndsWithAlphanumeric(@Nullable T... sequences) {
        return anyEndsWithAlphanumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean anyEndsWithAlphanumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(RegexUtilsWraps::endsWithAlphanumeric);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean allEndsWithNumeric(@Nullable T... sequences) {
        return allEndsWithNumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean allEndsWithNumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().allMatch(RegexUtilsWraps::endsWithNumeric);
    }

    @SafeVarargs
    public static <T extends CharSequence> boolean anyEndsWithNumeric(@Nullable T... sequences) {
        return anyEndsWithNumeric(ArrayUtilsWraps.asList(sequences));
    }

    public static <T extends CharSequence> boolean anyEndsWithNumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(RegexUtilsWraps::endsWithNumeric);
    }

    public static boolean matchAllPatterns(@Nullable CharSequence sequence, @Nullable String... regexes) {
        return matchAllPatternsWithFlags(sequence, 0, regexes);
    }

    public static boolean matchAllPatterns(@Nullable CharSequence sequence, @Nullable Collection<String> regexes) {
        return matchAllPatternsWithFlags(sequence, 0, regexes);
    }

    public static boolean matchAllPatternsIgnoreCase(@Nullable CharSequence sequence, @Nullable String... regexes) {
        return matchAllPatternsWithFlags(sequence, Pattern.CASE_INSENSITIVE, regexes);
    }

    public static boolean matchAllPatternsIgnoreCase(@Nullable CharSequence sequence, @Nullable Collection<String> regexes) {
        return matchAllPatternsWithFlags(sequence, Pattern.CASE_INSENSITIVE, regexes);
    }

    public static boolean matchAllPatternsWithFlags(@Nullable CharSequence sequence, int flags, @Nullable String... regexes) {
        return matchAllPatternsWithFlags(sequence, flags, ArrayUtilsWraps.asList(regexes));
    }

    public static boolean matchAllPatternsWithFlags(@Nullable CharSequence sequence, int flags, @Nullable Collection<String> regexes) {
        return StringUtils.isNotEmpty(sequence) && CollectionPlainWraps.isNotEmpty(regexes) && regexes.stream().allMatch(regex -> StringUtils.isNotEmpty(regex) && Pattern.compile(regex, flags).matcher(sequence).matches());
    }

    public static boolean matchAnyPatterns(@Nullable CharSequence sequence, @Nullable String... regexes) {
        return matchAnyPatternsWithFlags(sequence, 0, regexes);
    }

    public static boolean matchAnyPatterns(@Nullable CharSequence sequence, @Nullable Collection<String> regexes) {
        return matchAnyPatternsWithFlags(sequence, 0, regexes);
    }

    public static boolean matchAnyPatternsIgnoreCase(@Nullable CharSequence sequence, @Nullable String... regexes) {
        return matchAnyPatternsWithFlags(sequence, Pattern.CASE_INSENSITIVE, regexes);
    }

    public static boolean matchAnyPatternsIgnoreCase(@Nullable CharSequence sequence, @Nullable Collection<String> regexes) {
        return matchAnyPatternsWithFlags(sequence, Pattern.CASE_INSENSITIVE, regexes);
    }

    public static boolean matchAnyPatternsWithFlags(@Nullable CharSequence sequence, int flags, @Nullable String... regexes) {
        return matchAnyPatternsWithFlags(sequence, flags, ArrayUtilsWraps.asList(regexes));
    }

    public static boolean matchAnyPatternsWithFlags(@Nullable CharSequence sequence, int flags, @Nullable Collection<String> regexes) {
        return StringUtils.isNotEmpty(sequence) && CollectionPlainWraps.isNotEmpty(regexes) && regexes.stream().filter(StringUtils::isNotEmpty).anyMatch(regex -> Pattern.compile(regex, flags).matcher(sequence).matches());
    }

    public static boolean matchAllSequences(@Nullable String regex, @Nullable CharSequence... sequences) {
        return matchAllSequencesWithFlags(regex, 0, sequences);
    }

    public static boolean matchAllSequences(@Nullable String regex, @Nullable Collection<? extends CharSequence> sequences) {
        return matchAllSequencesWithFlags(regex, 0, sequences);
    }

    public static boolean matchAllSequencesIgnoreCase(@Nullable String regex, @Nullable CharSequence... sequences) {
        return matchAllSequencesWithFlags(regex, Pattern.CASE_INSENSITIVE, sequences);
    }

    public static boolean matchAllSequencesIgnoreCase(@Nullable String regex, @Nullable Collection<? extends CharSequence> sequences) {
        return matchAllSequencesWithFlags(regex, Pattern.CASE_INSENSITIVE, sequences);
    }

    public static boolean matchAllSequencesWithFlags(@Nullable String regex, int flags, @Nullable CharSequence... sequences) {
        return matchAllSequencesWithFlags(regex, flags, ArrayUtilsWraps.asList(sequences));
    }

    public static boolean matchAllSequencesWithFlags(@Nullable String regex, int flags, @Nullable Collection<? extends CharSequence> sequences) {
        if (StringUtils.isEmpty(regex) || CollectionPlainWraps.isEmpty(sequences)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex, flags);
        return sequences.stream().allMatch(element -> pattern.matcher(element).matches());
    }

    public static boolean matchAnySequences(@Nullable String regex, @Nullable CharSequence... sequences) {
        return matchAnySequencesWithFlags(regex, 0, sequences);
    }

    public static boolean matchAnySequences(@Nullable String regex, @Nullable Collection<? extends CharSequence> sequences) {
        return matchAnySequencesWithFlags(regex, 0, sequences);
    }

    public static boolean matchAnySequencesIgnoreCase(@Nullable String regex, @Nullable CharSequence... sequences) {
        return matchAnySequencesWithFlags(regex, Pattern.CASE_INSENSITIVE, sequences);
    }

    public static boolean matchAnySequencesIgnoreCase(@Nullable String regex, @Nullable Collection<? extends CharSequence> sequences) {
        return matchAnySequencesWithFlags(regex, Pattern.CASE_INSENSITIVE, sequences);
    }

    public static boolean matchAnySequencesWithFlags(@Nullable String regex, int flags, @Nullable CharSequence... sequences) {
        return matchAnySequencesWithFlags(regex, flags, ArrayUtilsWraps.asList(sequences));
    }

    public static boolean matchAnySequencesWithFlags(@Nullable String regex, int flags, @Nullable Collection<? extends CharSequence> sequences) {
        if (StringUtils.isEmpty(regex) || CollectionPlainWraps.isEmpty(sequences)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex, flags);
        return sequences.stream().anyMatch(element -> pattern.matcher(element).matches());
    }

    @Nullable
    public static Pattern compilePattern(@Nullable String regex) {
        return compilePattern(regex, 0);
    }

    /**
     * Compiles the given regular expression into a pattern with the given flags
     *
     * @param regex the expression to be compiled
     * @param flags match flags, a bit mask that may include
     *
     * @return the given regular expression compiled into a pattern with the given flags
     *
     * @see java.util.regex.Pattern#compile(java.lang.String, int)
     */
    @Nullable
    public static Pattern compilePattern(@Nullable String regex, int flags) {
        if (StringUtils.isBlank(regex)) {
            return null;
        }
        try {
            return Pattern.compile(regex, flags);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static List<String> extractMatched(@Nullable CharSequence sequence, @Nullable String... regexes) {
        return extractMatchedWithFlags(sequence, 0, regexes);
    }

    public static List<String> extractMatched(@Nullable CharSequence sequence, @Nullable Collection<String> regexes) {
        return extractMatchedWithFlags(sequence, 0, regexes);
    }

    public static List<String> extractMatchedIgnoreCase(@Nullable CharSequence sequence, @Nullable String... regexes) {
        return extractMatchedWithFlags(sequence, Pattern.CASE_INSENSITIVE, regexes);
    }

    public static List<String> extractMatchedIgnoreCase(@Nullable CharSequence sequence, Collection<String> regexes) {
        return extractMatchedWithFlags(sequence, Pattern.CASE_INSENSITIVE, regexes);
    }

    public static List<String> extractMatchedWithFlags(@Nullable CharSequence sequence, int flags, @Nullable String... regexes) {
        return extractMatchedWithFlags(sequence, flags, ArrayUtilsWraps.asList(regexes));
    }

    /**
     * Returns a list that containing the result that matched any of the patterns to the input sequence
     *
     * @param sequence the input sequence
     * @param flags pattern match flags, a bit mask such as {@link java.util.regex.Pattern.CASE_INSENSITIVE}
     * @param regexes the regex patterns
     *
     * @return a list that containing the result that matched any of the patterns to the input sequence
     */
    @Nullable
    @SuppressWarnings("JavadocReference")
    public static List<String> extractMatchedWithFlags(@Nullable CharSequence sequence, int flags, @Nullable Collection<String> regexes) {
        if (StringUtils.isEmpty(sequence) || CollectionPlainWraps.isEmpty(regexes)) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for (String regex : regexes) {
            if (StringUtils.isEmpty(regex)) {
                continue;
            }
            Pattern pattern = Pattern.compile(regex, flags);
            Matcher matcher = pattern.matcher(sequence);
            while (matcher.find()) {
                result.add(matcher.group());
            }
        }
        return result.isEmpty() ? null : result;
    }

    @Nullable
    @SafeVarargs
    public static <T extends CharSequence> T firstStartsWithAlphabetic(@Nullable T... sequences) {
        return firstStartsWithAlphabetic(ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static <T extends CharSequence> T firstStartsWithAlphabetic(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) ? sequences.stream().filter(RegexUtilsWraps::startsWithAlphabetic).findFirst().orElse(null) : null;
    }

    @Nullable
    @SafeVarargs
    public static <T extends CharSequence> T firstStartsWithAlphanumeric(@Nullable T... sequences) {
        return firstStartsWithAlphanumeric(ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static <T extends CharSequence> T firstStartsWithAlphanumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) ? sequences.stream().filter(RegexUtilsWraps::startsWithAlphanumeric).findFirst().orElse(null) : null;
    }

    @Nullable
    @SafeVarargs
    public static <T extends CharSequence> T firstStartsWithNumeric(@Nullable T... sequences) {
        return firstStartsWithNumeric(ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static <T extends CharSequence> T firstStartsWithNumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) ? sequences.stream().filter(RegexUtilsWraps::startsWithNumeric).findFirst().orElse(null) : null;
    }

    @Nullable
    @SafeVarargs
    public static <T extends CharSequence> T firstEndsWithAlphabetic(@Nullable T... sequences) {
        return firstEndsWithAlphabetic(ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static <T extends CharSequence> T firstEndsWithAlphabetic(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) ? sequences.stream().filter(RegexUtilsWraps::endsWithAlphabetic).findFirst().orElse(null) : null;
    }

    @Nullable
    @SafeVarargs
    public static <T extends CharSequence> T firstEndsWithAlphanumeric(@Nullable T... sequences) {
        return firstEndsWithAlphanumeric(ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static <T extends CharSequence> T firstEndsWithAlphanumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) ? sequences.stream().filter(RegexUtilsWraps::endsWithAlphanumeric).findFirst().orElse(null) : null;
    }

    @Nullable
    @SafeVarargs
    public static <T extends CharSequence> T firstEndsWithNumeric(@Nullable T... sequences) {
        return firstEndsWithNumeric(ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static <T extends CharSequence> T firstEndsWithNumeric(@Nullable Collection<T> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) ? sequences.stream().filter(RegexUtilsWraps::endsWithNumeric).findFirst().orElse(null) : null;
    }

    public static boolean isEscapeChar(char character) {
        return ArrayUtils.contains(RegexVariantCombo.ESCAPE_CHARS, character);
    }

    public static boolean isCompilable(@Nullable String regex) {
        return compilePattern(regex) != null;
    }

    public static String removeAll(@Nullable String text, @Nullable String... regexes) {
        return removeAll(text, ArrayUtilsWraps.asList(regexes));
    }

    /**
     * @see org.apache.commons.lang3.RegExUtils#removeAll(String, String)
     */
    public static String removeAll(@Nullable String text, @Nullable Collection<String> regexes) {
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(regexes)) {
            return text;
        }
        AtomicReference<String> result = new AtomicReference<>(text);
        regexes.stream().filter(StringUtils::isNotEmpty).forEach(regex -> result.set(RegExUtils.removeAll(result.get(), regex)));
        return result.get();
    }

    public static String removeAllIgnoreCase(@Nullable String text, @Nullable String... regexes) {
        return removeAllIgnoreCase(text, ArrayUtilsWraps.asList(regexes));
    }

    public static String removeAllIgnoreCase(@Nullable String text, @Nullable Collection<String> regexes) {
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(regexes)) {
            return text;
        }
        AtomicReference<String> result = new AtomicReference<>(text);
        regexes.stream().filter(StringUtils::isNotEmpty).forEach(regex -> result.set(RegExUtils.removeAll(result.get(), Pattern.compile(regex, Pattern.CASE_INSENSITIVE))));
        return result.get();
    }

    public static String removeLineSeparator(@Nullable String text) {
        return StringUtils.isEmpty(text) ? text : RegExUtils.removeAll(text, RegexVariantConst.LINE_SEPARATOR);
    }

    public static String removeStart(@Nullable String text, char character) {
        if (StringUtils.isEmpty(text)) {
            return text;
        }
        String regex = StringUtils.join(CharVariantConst.CARET, (isEscapeChar(character) ? SymbolVariantConst.DOUBLE_BACKSLASHES : null), character, CharVariantConst.PLUS);
        return RegExUtils.removeAll(text, regex);
    }

    public static String removeStart(@Nullable String text, @Nullable String... regexes) {
        return removeStart(text, ArrayUtilsWraps.asList(regexes));
    }

    public static String removeStart(@Nullable String text, @Nullable Collection<String> regexes) {
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(regexes)) {
            return text;
        }
        AtomicReference<String> result = new AtomicReference<>(text);
        regexes.stream().filter(StringUtils::isNotEmpty).forEach(regex -> result.set(RegExUtils.removeAll(result.get(), StringUtilsWraps.prependIfMissing(regex, CharVariantConst.CARET))));
        return result.get();
    }

    public static String removeStartIgnoreCase(@Nullable String text, char character) {
        String regex = StringUtils.join(CharVariantConst.CARET, (isEscapeChar(character) ? SymbolVariantConst.DOUBLE_BACKSLASHES : null), character, CharVariantConst.PLUS);
        return removeStartIgnoreCase(text, Collections.singleton(regex));
    }

    public static String removeStartIgnoreCase(@Nullable String text, @Nullable String... regexes) {
        return removeStartIgnoreCase(text, ArrayUtilsWraps.asList(regexes));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String removeStartIgnoreCase(@Nullable String text, @Nullable Collection<String> regexes) {
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(regexes)) {
            return text;
        }
        AtomicReference<String> result = new AtomicReference<>(text);
        regexes.stream().filter(StringUtils::isNotEmpty).forEach(regex -> result.set(RegExUtils.removeAll(result.get(), Pattern.compile(StringUtilsWraps.prependIfMissing(regex, CharVariantConst.CARET), Pattern.CASE_INSENSITIVE))));
        return result.get();
    }

    public static String removeEnd(@Nullable String text, char character) {
        if (StringUtils.isEmpty(text)) {
            return text;
        }
        String regex = StringUtils.join((isEscapeChar(character) ? SymbolVariantConst.DOUBLE_BACKSLASHES : null), character, CharVariantConst.PLUS, CharVariantConst.DOLLAR);
        return RegExUtils.removeAll(text, regex);
    }

    public static String removeEnd(@Nullable String text, @Nullable String... regexes) {
        return removeEnd(text, ArrayUtilsWraps.asList(regexes));
    }

    public static String removeEnd(@Nullable String text, @Nullable Collection<String> regexes) {
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(regexes)) {
            return text;
        }
        AtomicReference<String> result = new AtomicReference<>(text);
        regexes.stream().filter(StringUtils::isNotEmpty).forEach(regex -> result.set(RegExUtils.removeAll(result.get(), StringUtilsWraps.appendIfMissing(regex, CharVariantConst.DOLLAR))));
        return result.get();
    }

    public static String removeEndIgnoreCase(@Nullable String text, char character) {
        String regex = StringUtils.join((isEscapeChar(character) ? SymbolVariantConst.DOUBLE_BACKSLASHES : null), character, CharVariantConst.PLUS, CharVariantConst.DOLLAR);
        return removeEndIgnoreCase(text, Collections.singleton(regex));
    }

    public static String removeEndIgnoreCase(@Nullable String text, @Nullable String... regexes) {
        return removeEndIgnoreCase(text, ArrayUtilsWraps.asList(regexes));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String removeEndIgnoreCase(@Nullable String text, @Nullable Collection<String> regexes) {
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(regexes)) {
            return text;
        }
        AtomicReference<String> result = new AtomicReference<>(text);
        regexes.stream().filter(StringUtils::isNotEmpty).forEach(regex -> result.set(RegExUtils.removeAll(result.get(), Pattern.compile(StringUtilsWraps.appendIfMissing(regex, CharVariantConst.DOLLAR), Pattern.CASE_INSENSITIVE))));
        return result.get();
    }

    public static String replaceAllIgnoreCase(@Nullable String text, @Nullable String regex, char replacement) {
        return replaceAllIgnoreCase(text, regex, CharUtils.toString(replacement));
    }

    /**
     * @see org.apache.commons.lang3.RegExUtils#replaceAll(String, String, String)
     */
    public static String replaceAllIgnoreCase(@Nullable String text, @Nullable String regex, @Nullable String replacement) {
        if (StringUtils.isEmpty(text) || StringUtils.isBlank(regex)) {
            return text;
        }
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return RegExUtils.replaceAll(text, pattern, StringUtils.defaultString(replacement));
    }

    public static String replaceFirstIgnoreCase(@Nullable String text, @Nullable String regex, char replacement) {
        return replaceFirstIgnoreCase(text, regex, CharUtils.toString(replacement));
    }

    /**
     * @see RegExUtils#replaceFirst(String, String, String)
     */
    public static String replaceFirstIgnoreCase(@Nullable String text, @Nullable String regex, @Nullable String replacement) {
        if (StringUtils.isEmpty(text) || StringUtils.isBlank(regex)) {
            return text;
        }
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return RegExUtils.replaceFirst(text, pattern, StringUtils.defaultString(replacement));
    }

    /**
     * @see org.apache.commons.lang3.RandomStringUtils#randomAlphabetic(int)
     */
    public static String reserveAlphabetic(@Nullable String text) {
        return StringUtils.isEmpty(text) ? text : RegExUtils.removeAll(text, "[^A-Za-z]");    // $NON-NLS-1$
    }

    public static String reserveAlphanumeric(@Nullable String text) {
        return StringUtils.isEmpty(text) ? text : RegExUtils.removeAll(text, "[^A-Za-z0-9]");    // $NON-NLS-1$
    }

    public static String reserveWord(@Nullable String text) {
        return StringUtils.isEmpty(text) ? text : RegExUtils.removeAll(text, "\\W");    // $NON-NLS-1$
    }

    public static String reserveWordHyphen(@Nullable String text) {
        return StringUtils.isEmpty(text) ? text : RegExUtils.removeAll(text, "[^A-Za-z0-9_\\-]");    // $NON-NLS-1$
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#getDigits(String)
     */
    public static String reserveNumeric(@Nullable String text) {
        return StringUtils.isEmpty(text) ? text : RegExUtils.removeAll(text, "\\D");    // $NON-NLS-1$
    }

    public static String reserveNumericHyphen(@Nullable String text) {
        return StringUtils.isEmpty(text) ? text : RegExUtils.removeAll(text, "[^0-9\\-]");    // $NON-NLS-1$
    }

    public static boolean isAlphabetic(@Nullable CharSequence sequence) {
        return matches(sequence, "^[A-Za-z]+$");    // $NON-NLS-1$
    }

    public static boolean isAlphanumeric(@Nullable CharSequence sequence) {
        return matches(sequence, "^[A-Za-z\\d]+$");    // $NON-NLS-1$
    }

    public static boolean isNumeric(@Nullable CharSequence sequence) {
        return matches(sequence, "^\\d+$");    // $NON-NLS-1$
    }

    public static boolean isNegateNumeric(@Nullable CharSequence sequence) {
        return matches(sequence, "^-\\d+$");    // $NON-NLS-1$
    }

    public static boolean isPositiveNumeric(@Nullable CharSequence sequence) {
        return matches(sequence, "^\\+?[1-9]+$");    // $NON-NLS-1$
    }

    public static boolean isWord(@Nullable CharSequence sequence) {
        return matches(sequence, "^\\w+$");    // $NON-NLS-1$
    }

    public static boolean isWordHyphen(@Nullable CharSequence sequence) {
        return matches(sequence, "^[\\w\\-]+$");    // $NON-NLS-1$
    }

    public static boolean find(@Nullable CharSequence sequence, @Nullable Pattern pattern) {
        return StringUtils.isNotEmpty(sequence) && pattern != null && pattern.matcher(sequence).find();
    }

    /**
     * Attempts to find the next subsequence of the input sequence that matches the pattern
     *
     * @param sequence the character sequence to be matched
     * @param regex the expression to be compiled
     *
     * @return true if a subsequence of the input sequence matches this matcher's pattern
     *
     * @throws {@link java.util.regex.PatternSyntaxException} if the expression's syntax is invalid
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean find(@Nullable CharSequence sequence, @Nullable String regex) {
        return StringUtils.isNoneEmpty(sequence, regex) && Pattern.compile(regex).matcher(sequence).find();
    }

    public static boolean findAny(@Nullable CharSequence sequence, @Nullable String... regexes) {
        return findAny(sequence, ArrayUtilsWraps.asList(regexes));
    }

    public static boolean findAny(@Nullable CharSequence sequence, @Nullable Collection<String> regexes) {
        return StringUtils.isNotEmpty(sequence) && CollectionPlainWraps.isNotEmpty(regexes) && regexes.stream().filter(StringUtils::isNotEmpty).anyMatch(element -> find(sequence, element));
    }

    /**
     * Attempts to find the next subsequence of the input sequence that matches the pattern, case-insensitive
     *
     * @param sequence the character sequence to be matched
     * @param regex the expression to be compiled
     *
     * @return true if a subsequence of the input sequence matches this matcher's pattern
     *
     * @throws {@link java.util.regex.PatternSyntaxException} if the expression's syntax is invalid
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean findIgnoreCase(@Nullable CharSequence sequence, @Nullable String regex) {
        return StringUtils.isNoneEmpty(sequence, regex) && Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(sequence).find();
    }

    public static boolean findAnyIgnoreCase(@Nullable CharSequence sequence, @Nullable String... regexes) {
        return findAnyIgnoreCase(sequence, ArrayUtilsWraps.asList(regexes));
    }

    public static boolean findAnyIgnoreCase(@Nullable CharSequence sequence, @Nullable Collection<String> regexes) {
        return StringUtils.isNotEmpty(sequence) && CollectionPlainWraps.isNotEmpty(regexes) && regexes.stream().filter(StringUtils::isNotEmpty).anyMatch(element -> findIgnoreCase(sequence, element));
    }

    /**
     * Attempts to find the next subsequence of the input sequence that matches the pattern, with the given flags
     *
     * @param sequence the character sequence to be matched
     * @param regex the expression to be compiled
     *
     * @return true if a subsequence of the input sequence matches this matcher's pattern
     *
     * @throws {@link java.util.regex.PatternSyntaxException} if the expression's syntax is invalid
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean findWithFlags(@Nullable CharSequence sequence, @Nullable String regex, int flags) {
        return StringUtils.isNoneEmpty(sequence, regex) && Pattern.compile(regex, flags).matcher(sequence).find();
    }

    public static boolean findAnyWithFlags(@Nullable CharSequence sequence, int flags, @Nullable String... regexes) {
        return findAnyWithFlags(sequence, flags, ArrayUtilsWraps.asList(regexes));
    }

    public static boolean findAnyWithFlags(@Nullable CharSequence sequence, int flags, @Nullable Collection<String> regexes) {
        return StringUtils.isNotEmpty(sequence) && CollectionPlainWraps.isNotEmpty(regexes) && regexes.stream().filter(StringUtils::isNotEmpty).anyMatch(element -> findWithFlags(sequence, element, flags));
    }

    public static boolean matches(@Nullable CharSequence sequence, @Nullable Pattern pattern) {
        return StringUtils.isNotEmpty(sequence) && pattern != null && pattern.matcher(sequence).matches();
    }

    /**
     * Compiles the given regular expression and attempts to match the given input against it
     *
     * @param sequence the character sequence to be matched
     * @param regex the expression to be compiled
     *
     * @return true if the input sequence matches this matcher's pattern
     *
     * @throws {@link java.util.regex.PatternSyntaxException} if the expression's syntax is invalid
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean matches(@Nullable CharSequence sequence, @Nullable String regex) {
        return StringUtils.isNoneEmpty(sequence, regex) && Pattern.matches(regex, sequence);
    }

    /**
     * Compiles the given regular expression and attempts to match the given input against it, case-insensitive
     *
     * @param sequence the character sequence to be matched
     * @param regex the expression to be compiled
     *
     * @return true if the input sequence matches this matcher's pattern
     *
     * @throws {@link java.util.regex.PatternSyntaxException} if the expression's syntax is invalid
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean matchesIgnoreCase(@Nullable CharSequence sequence, @Nullable String regex) {
        return StringUtils.isNoneEmpty(sequence, regex) && Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(sequence).matches();
    }

    /**
     * Compiles the given regular expression and attempts to match the given input against it, with the given flags
     *
     * @param sequence the character sequence to be matched
     * @param regex the expression to be compiled
     *
     * @return true if the input sequence matches this matcher's pattern
     *
     * @throws {@link java.util.regex.PatternSyntaxException} if the expression's syntax is invalid
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean matchesWithFlags(@Nullable CharSequence sequence, @Nullable String regex, int flags) {
        return StringUtils.isNoneEmpty(sequence, regex) && Pattern.compile(regex, flags).matcher(sequence).matches();
    }

    public static boolean startsWithAlphabetic(@Nullable CharSequence sequence) {
        return matches(sequence, "^[A-Za-z]+");    // $NON-NLS-1$
    }

    public static boolean startsWithAlphanumeric(@Nullable CharSequence sequence) {
        return matches(sequence, "^[A-Za-z\\d]+");    // $NON-NLS-1$
    }

    public static boolean startsWithNumeric(@Nullable CharSequence sequence) {
        return matches(sequence, "^\\d+");    // $NON-NLS-1$
    }

    public static boolean startsWithWord(@Nullable CharSequence sequence) {
        return matches(sequence, "^\\w+");    // $NON-NLS-1$
    }

    public static boolean startsWithWordHyphen(@Nullable CharSequence sequence) {
        return matches(sequence, "^[\\w\\-]+");    // $NON-NLS-1$
    }

    public static boolean endsWithAlphabetic(@Nullable CharSequence sequence) {
        return StringUtils.isNotBlank(sequence) && Pattern.matches("[A-Za-z]+$", sequence);    // $NON-NLS-1$
    }

    public static boolean endsWithAlphanumeric(@Nullable CharSequence sequence) {
        return StringUtils.isNotBlank(sequence) && Pattern.matches("[A-Za-z\\d]+$", sequence);    // $NON-NLS-1$
    }

    public static boolean endsWithNumeric(@Nullable CharSequence sequence) {
        return StringUtils.isNotBlank(sequence) && Pattern.matches("\\d+$", sequence);    // $NON-NLS-1$
    }

    public static boolean endsWithWord(@Nullable CharSequence sequence) {
        return matches(sequence, "\\w+$");    // $NON-NLS-1$
    }

    public static boolean endsWithWordHyphen(@Nullable CharSequence sequence) {
        return matches(sequence, "[\\w\\-]+$");    // $NON-NLS-1$
    }
}
