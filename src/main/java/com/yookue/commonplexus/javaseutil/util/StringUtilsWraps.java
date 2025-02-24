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


import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;
import com.yookue.commonplexus.javaseutil.constant.SymbolVariantConst;


/**
 * Utilities for {@link org.apache.commons.lang3.StringUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.StringUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class StringUtilsWraps {
    public static boolean allEmpty(@Nullable CharSequence... sequences) {
        return allEmpty(ArrayUtilsWraps.asList(sequences));
    }

    public static boolean allEmpty(@Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isEmpty(sequences) || sequences.stream().allMatch(StringUtils::isEmpty);
    }

    public static boolean allNotEmpty(@Nullable CharSequence... sequences) {
        return allNotEmpty(ArrayUtilsWraps.asList(sequences));
    }

    public static boolean allNotEmpty(@Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().noneMatch(StringUtils::isEmpty);
    }

    public static boolean allBlank(@Nullable CharSequence... sequences) {
        return allBlank(ArrayUtilsWraps.asList(sequences));
    }

    public static boolean allBlank(@Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isEmpty(sequences) || sequences.stream().allMatch(StringUtils::isBlank);
    }

    public static boolean allNotBlank(@Nullable CharSequence... sequences) {
        return allNotBlank(ArrayUtilsWraps.asList(sequences));
    }

    public static boolean allNotBlank(@Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().noneMatch(StringUtils::isBlank);
    }

    public static boolean anyEmpty(@Nullable CharSequence... sequences) {
        return anyEmpty(ArrayUtilsWraps.asList(sequences));
    }

    public static boolean anyEmpty(@Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isEmpty(sequences) || sequences.stream().anyMatch(StringUtils::isEmpty);
    }

    public static boolean anyNotEmpty(@Nullable CharSequence... sequences) {
        return anyNotEmpty(ArrayUtilsWraps.asList(sequences));
    }

    public static boolean anyNotEmpty(@Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(StringUtils::isNotEmpty);
    }

    public static boolean anyBlank(@Nullable CharSequence... sequences) {
        return anyBlank(ArrayUtilsWraps.asList(sequences));
    }

    public static boolean anyBlank(@Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isEmpty(sequences) || sequences.stream().anyMatch(StringUtils::isBlank);
    }

    public static boolean anyNotBlank(@Nullable CharSequence... sequences) {
        return anyNotBlank(ArrayUtilsWraps.asList(sequences));
    }

    public static boolean anyNotBlank(@Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isNotEmpty(sequences) && sequences.stream().anyMatch(StringUtils::isNotBlank);
    }

    /**
     * Returns a string that appends the suffix to the end of the sequence if the sequence does not already end with the suffix
     *
     * @param sequence the source char sequence to be appended
     * @param append the suffix to append to the end of the string
     *
     * @return a string that appends the suffix to the end of the string if the string does not already end with the suffix
     */
    public static String appendIfMissing(@Nullable CharSequence sequence, char append) {
        return appendIfMissing(sequence, CharUtils.toString(append));
    }

    /**
     * Returns a string that appends the suffix to the end of the sequence if the sequence does not already end with the suffix
     *
     * @param sequence the source char sequence to be appended
     * @param append the suffix to append to the end of the string
     *
     * @return a string that appends the suffix to the end of the string if the string does not already end with the suffix
     */
    public static String appendIfMissing(@Nullable CharSequence sequence, @Nullable CharSequence append) {
        return StringUtils.appendIfMissing(CharSequenceWraps.toStringIgnoreNull(sequence), append, append);
    }

    public static void appendIfMissing(@Nullable Collection<String> collection, char append, char... suffixes) {
        appendIfMissing(collection, CharUtils.toString(append), CharUtilsWraps.toStringArray(suffixes));
    }

    public static void appendIfMissing(@Nullable Collection<String> collection, @Nullable CharSequence append) {
        appendIfMissing(collection, append, ArrayUtilsWraps.singletonArray(append));
    }

    public static void appendIfMissing(@Nullable Collection<String> collection, char append, @Nullable CharSequence... suffixes) {
        appendIfMissing(collection, CharUtils.toString(append), suffixes);
    }

    public static void appendIfMissing(@Nullable Collection<String> collection, @Nullable CharSequence append, char... suffixes) {
        appendIfMissing(collection, append, CharUtilsWraps.toStringArray(suffixes));
    }

    /**
     * Append the suffix to the end of the string if the string does not already end with any of the suffixes
     *
     * @param collection the collection to get the input from
     * @param append the suffix to append to the end of the string
     * @param suffixes additional suffixes that are valid terminators
     *
     * @throws java.lang.UnsupportedOperationException if the <tt>clear</tt> operation is not supported by this collection
     * @see "org.springframework.util.ObjectUtils#addObjectToArray"
     */
    public static void appendIfMissing(@Nullable Collection<String> collection, @Nullable CharSequence append, @Nullable CharSequence... suffixes) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.appendIfMissing(element, append, suffixes)).forEach(collection::add);
    }

    /**
     * Returns a string that appends the suffix to the end of the string if the string does not already end with the suffix, case-insensitive
     *
     * @param sequence the source char sequence to be appended
     * @param append the suffix to append to the end of the string
     *
     * @return a string that appends the suffix to the end of the string if the string does not already end with the suffix
     */
    public static String appendIfMissingIgnoreCase(@Nullable CharSequence sequence, char append) {
        return appendIfMissingIgnoreCase(sequence, CharUtils.toString(append));
    }

    /**
     * Returns a string that appends the suffix to the end of the string if the string does not already end with the suffix, case-insensitive
     *
     * @param sequence the source char sequence to be appended
     * @param append the suffix to append to the end of the string
     *
     * @return a string that appends the suffix to the end of the string if the string does not already end with the suffix
     */
    public static String appendIfMissingIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence append) {
        return StringUtils.appendIfMissingIgnoreCase(CharSequenceWraps.toStringIgnoreNull(sequence), append, append);
    }

    public static void appendIfMissingIgnoreCase(@Nullable Collection<String> collection, @Nullable CharSequence append) {
        appendIfMissingIgnoreCase(collection, append, ArrayUtilsWraps.singletonArray(append));
    }

    public static void appendIfMissingIgnoreCase(@Nullable Collection<String> collection, char append, char... suffixes) {
        appendIfMissingIgnoreCase(collection, CharUtils.toString(append), CharUtilsWraps.toStringArray(suffixes));
    }

    public static void appendIfMissingIgnoreCase(@Nullable Collection<String> collection, char append, @Nullable CharSequence... suffixes) {
        appendIfMissingIgnoreCase(collection, CharUtils.toString(append), suffixes);
    }

    public static void appendIfMissingIgnoreCase(@Nullable Collection<String> collection, @Nullable CharSequence append, char... suffixes) {
        appendIfMissingIgnoreCase(collection, append, CharUtilsWraps.toStringArray(suffixes));
    }

    /**
     * Append the suffix to the end of the string if the string does not already end with any of the suffixes, case-insensitive
     *
     * @param collection the collection to get the input from
     * @param append the suffix to append to the end of the string
     * @param suffixes additional suffixes that are valid terminators
     *
     * @throws java.lang.UnsupportedOperationException if the <tt>clear</tt> operation is not supported by this collection
     * @see org.apache.commons.lang3.StringUtils#appendIfMissingIgnoreCase
     */
    public static void appendIfMissingIgnoreCase(@Nullable Collection<String> collection, @Nullable CharSequence append, @Nullable CharSequence... suffixes) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.appendIfMissingIgnoreCase(element, append, suffixes)).forEach(collection::add);
    }

    public static String appendIfSequenceNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence append) {
        return appendIfSequenceNotEmpty(sequence, append, null);
    }

    public static String appendIfSequenceNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence append, char delimiter) {
        return appendIfSequenceNotEmpty(sequence, append, CharUtils.toString(delimiter));
    }

    public static String appendIfSequenceNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence append, @Nullable CharSequence delimiter) {
        return StringUtils.isEmpty(sequence) ? CharSequenceWraps.toStringIgnoreNull(sequence) : StringUtils.join(sequence, delimiter, append);
    }

    public static String appendIfSequenceNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence append) {
        return appendIfSequenceNotBlank(sequence, append, null);
    }

    public static String appendIfSequenceNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence append, char delimiter) {
        return appendIfSequenceNotBlank(sequence, append, CharUtils.toString(delimiter));
    }

    public static String appendIfSequenceNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence append, @Nullable CharSequence delimiter) {
        return StringUtils.isBlank(sequence) ? CharSequenceWraps.toStringIgnoreNull(sequence) : StringUtils.join(sequence, delimiter, append);
    }

    public static String appendIfSuffixNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence append) {
        return appendIfSuffixNotEmpty(sequence, append, null);
    }

    public static String appendIfSuffixNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence append, char delimiter) {
        return appendIfSuffixNotEmpty(sequence, append, CharUtils.toString(delimiter));
    }

    public static String appendIfSuffixNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence append, @Nullable CharSequence delimiter) {
        return StringUtils.isEmpty(append) ? CharSequenceWraps.toStringIgnoreNull(sequence) : StringUtils.join(sequence, delimiter, append);
    }

    public static String appendIfSuffixNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence append) {
        return appendIfSuffixNotBlank(sequence, append, null);
    }

    public static String appendIfSuffixNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence append, char delimiter) {
        return appendIfSuffixNotBlank(sequence, append, CharUtils.toString(delimiter));
    }

    public static String appendIfSuffixNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence append, @Nullable CharSequence delimiter) {
        return StringUtils.isBlank(append) ? CharSequenceWraps.toStringIgnoreNull(sequence) : StringUtils.join(sequence, delimiter, append);
    }

    public static String capitalizeFirstLowerTail(@Nullable CharSequence sequence) {
        return StringUtils.capitalize(StringUtils.lowerCase(CharSequenceWraps.toStringIgnoreNull(sequence)));
    }

    public static boolean containsAny(@Nullable CharSequence sequence, @Nullable CharSequence... comparisons) {
        return containsAny(sequence, ArrayUtilsWraps.asList(comparisons));
    }

    public static boolean containsAny(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> comparisons) {
        return StringUtils.isNotEmpty(sequence) && CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(element -> StringUtils.contains(sequence, element));
    }

    public static boolean containsAnyIgnoreCase(@Nullable CharSequence sequence, char... comparisons) {
        return StringUtils.containsAnyIgnoreCase(sequence, CharUtilsWraps.toStringArray(comparisons));
    }

    public static boolean containsAnyIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence... comparisons) {
        return containsAnyIgnoreCase(sequence, ArrayUtilsWraps.asList(comparisons));
    }

    public static boolean containsAnyIgnoreCase(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> comparisons) {
        return StringUtils.isNotEmpty(sequence) && CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(element -> StringUtils.containsIgnoreCase(sequence, element));
    }

    public static <T extends CharSequence> T defaultIfEquals(@Nullable T sequence, @Nullable CharSequence comparison, @Nullable T defaultValue) {
        return StringUtils.equals(sequence, comparison) ? defaultValue : sequence;
    }

    public static <T extends CharSequence> T defaultIfEqualsIgnoreCase(@Nullable T sequence, @Nullable CharSequence comparison, @Nullable T defaultValue) {
        return StringUtils.equalsIgnoreCase(sequence, comparison) ? defaultValue : sequence;
    }

    public static String defaultString(@Nullable CharSequence sequence) {
        return defaultString(sequence, StringUtils.EMPTY);
    }

    public static String defaultString(@Nullable CharSequence sequence, @Nullable String defaultValue) {
        return (sequence == null) ? defaultValue : sequence.toString();
    }

    public static String defaultStringIfBlank(@Nullable CharSequence sequence, @Nullable String defaultValue) {
        return StringUtils.isBlank(sequence) ? defaultValue : sequence.toString();
    }

    public static String defaultStringIfEmpty(@Nullable CharSequence sequence, @Nullable String defaultValue) {
        return StringUtils.isEmpty(sequence) ? defaultValue : sequence.toString();
    }

    public static String[] distinctIgnoreNull(@Nullable String... texts) {
        return distinctIgnoreNull(ArrayUtilsWraps.asList(texts));
    }

    @Nullable
    public static String[] distinctIgnoreNull(@Nullable Collection<String> texts) {
        Set<String> result = distinctIgnoreNullToSet(texts);
        return CollectionPlainWraps.isEmpty(result) ? null : result.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static List<String> distinctIgnoreNullToList(@Nullable String... texts) {
        return distinctIgnoreNullToList(ArrayUtilsWraps.asList(texts));
    }

    @Nullable
    public static List<String> distinctIgnoreNullToList(@Nullable Collection<String> texts) {
        return CollectionPlainWraps.isEmpty(texts) ? null : texts.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
    }

    public static Set<String> distinctIgnoreNullToSet(@Nullable String... texts) {
        return distinctIgnoreNullToSet(ArrayUtilsWraps.asList(texts));
    }

    @Nullable
    public static Set<String> distinctIgnoreNullToSet(@Nullable Collection<String> texts) {
        return CollectionPlainWraps.isEmpty(texts) ? null : texts.stream().filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public static String[] distinctIgnoreEmpty(@Nullable String... texts) {
        return distinctIgnoreEmpty(ArrayUtilsWraps.asList(texts));
    }

    @Nullable
    public static String[] distinctIgnoreEmpty(@Nullable Collection<String> texts) {
        Set<String> result = distinctIgnoreEmptyToSet(texts);
        return CollectionPlainWraps.isEmpty(result) ? null : result.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static List<String> distinctIgnoreEmptyToList(@Nullable String... texts) {
        return distinctIgnoreEmptyToList(ArrayUtilsWraps.asList(texts));
    }

    @Nullable
    public static List<String> distinctIgnoreEmptyToList(@Nullable Collection<String> texts) {
        return CollectionPlainWraps.isEmpty(texts) ? null : texts.stream().filter(StringUtils::isNotEmpty).distinct().collect(Collectors.toList());
    }

    public static Set<String> distinctIgnoreEmptyToSet(@Nullable String... texts) {
        return distinctIgnoreEmptyToSet(ArrayUtilsWraps.asList(texts));
    }

    @Nullable
    public static Set<String> distinctIgnoreEmptyToSet(@Nullable Collection<String> texts) {
        return CollectionPlainWraps.isEmpty(texts) ? null : texts.stream().filter(StringUtils::isNotEmpty).collect(Collectors.toSet());
    }

    public static String[] distinctIgnoreBlank(@Nullable String... texts) {
        return distinctIgnoreBlank(ArrayUtilsWraps.asList(texts));
    }

    @Nullable
    public static String[] distinctIgnoreBlank(@Nullable Collection<String> texts) {
        Set<String> result = distinctIgnoreBlankToSet(texts);
        return CollectionPlainWraps.isEmpty(result) ? null : result.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static List<String> distinctIgnoreBlankToList(@Nullable String... texts) {
        return distinctIgnoreBlankToList(ArrayUtilsWraps.asList(texts));
    }

    @Nullable
    public static List<String> distinctIgnoreBlankToList(@Nullable Collection<String> texts) {
        return CollectionPlainWraps.isEmpty(texts) ? null : texts.stream().filter(StringUtils::isNotBlank).distinct().collect(Collectors.toList());
    }

    public static Set<String> distinctIgnoreBlankToSet(@Nullable String... texts) {
        return distinctIgnoreBlankToSet(ArrayUtilsWraps.asList(texts));
    }

    @Nullable
    public static Set<String> distinctIgnoreBlankToSet(@Nullable Collection<String> texts) {
        return CollectionPlainWraps.isEmpty(texts) ? null : texts.stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
    }

    public static boolean equalsAny(@Nullable CharSequence sequence, @Nullable CharSequence... comparisons) {
        return equalsAny(sequence, ArrayUtilsWraps.asList(comparisons));
    }

    public static boolean equalsAny(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> comparisons) {
        return sequence != null && CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(element -> StringUtils.equals(sequence, element));
    }

    public static boolean equalsAnyIgnoreCase(@Nullable CharSequence sequence, char... comparisons) {
        return StringUtils.equalsAnyIgnoreCase(sequence, CharUtilsWraps.toStringArray(comparisons));
    }

    public static boolean equalsAnyIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence... comparisons) {
        return StringUtils.equalsAnyIgnoreCase(sequence, comparisons);
    }

    public static boolean equalsAnyIgnoreCase(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> comparisons) {
        return sequence != null && CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(element -> StringUtils.equalsIgnoreCase(sequence, element));
    }

    public static String encodeToString(@Nullable String text, @Nonnull Charset fromCharset, @Nonnull Charset toCharset) {
        return StringUtils.isEmpty(text) ? text : StringUtils.toEncodedString(text.getBytes(fromCharset), toCharset);
    }

    public static String encodeToString(@Nullable String text, @Nonnull CharSequence fromCharset, @Nonnull CharSequence toCharset) {
        if (StringUtils.isEmpty(text) || CharsetPlainWraps.anyNotSupported(fromCharset, toCharset)) {
            return text;
        }
        try {
            return StringUtils.toEncodedString(text.getBytes(fromCharset.toString()), Charset.forName(toCharset.toString()));
        } catch (UnsupportedEncodingException ignored) {
        }
        return null;
    }

    /**
     * @see org.apache.commons.io.IOUtils#toString(byte[], java.lang.String)
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String encodeToString(@Nullable byte[] bytes, @Nullable CharSequence charset) {
        if (ArrayUtils.isEmpty(bytes) || CharsetPlainWraps.isNotSupported(charset)) {
            return null;
        }
        return StringUtils.toEncodedString(bytes, Charset.forName(charset.toString()));
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String encodeToStringWithAscii(@Nullable byte[] bytes) {
        return ArrayUtils.isEmpty(bytes) ? null : StringUtils.toEncodedString(bytes, StandardCharsets.US_ASCII);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String encodeToStringWithUtf8(@Nullable byte[] bytes) {
        return ArrayUtils.isEmpty(bytes) ? null : StringUtils.toEncodedString(bytes, StandardCharsets.UTF_8);
    }

    public static boolean endsWith(@Nullable CharSequence sequence, char comparison) {
        return StringUtils.endsWith(sequence, CharUtils.toString(comparison));
    }

    public static boolean endsWithAny(@Nullable CharSequence sequence, char... comparisons) {
        return StringUtils.endsWithAny(sequence, CharUtilsWraps.toStringArray(comparisons));
    }

    public static boolean endsWithAnyIgnoreCase(@Nullable CharSequence sequence, char... comparisons) {
        return endsWithAnyIgnoreCase(sequence, CharUtilsWraps.toStringArray(comparisons));
    }

    public static boolean endsWithAnyIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence... comparisons) {
        return endsWithAnyIgnoreCase(sequence, ArrayUtilsWraps.asList(comparisons));
    }

    /**
     * Check if a CharSequence ends with any of the provided case-insensitive suffixes
     *
     * @param sequence the CharSequence to check
     * @param comparisons the case-insensitive CharSequence suffixes, may be empty or contain {@code null}
     *
     * @see org.apache.commons.lang3.StringUtils#endsWithAny
     */
    public static boolean endsWithAnyIgnoreCase(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> comparisons) {
        return StringUtils.isNotEmpty(sequence) && CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(element -> StringUtils.endsWithIgnoreCase(sequence, element));
    }

    @Nullable
    public static <T extends CharSequence> T firstNonBlank(@Nullable Collection<T> texts) {
        return CollectionPlainWraps.isEmpty(texts) ? null : texts.stream().filter(StringUtils::isNotBlank).findFirst().orElse(null);
    }

    @Nullable
    public static <T extends CharSequence> T firstNonEmpty(@Nullable Collection<T> texts) {
        return CollectionPlainWraps.isEmpty(texts) ? null : texts.stream().filter(StringUtils::isNotEmpty).findFirst().orElse(null);
    }

    public static <T extends CharSequence> void forEachChars(@Nullable T sequence, @Nullable Consumer<Character> action) {
        forEachChars(sequence, action, null);
    }

    public static <T extends CharSequence> void forEachChars(@Nullable T sequence, @Nullable Consumer<Character> action, @Nullable Predicate<Character> filter) {
        if (StringUtils.isNotEmpty(sequence)) {
            ArrayUtilsWraps.forEach(sequence.toString().toCharArray(), action, filter);
        }
    }

    public static <T extends CharSequence> void forEachCharsBreakable(@Nullable T sequence, @Nullable Function<Character, Boolean> action) {
        forEachCharsBreakable(sequence, action, null);
    }

    public static <T extends CharSequence> void forEachCharsBreakable(@Nullable T sequence, @Nullable Function<Character, Boolean> action, @Nullable Predicate<Character> filter) {
        if (StringUtils.isNotEmpty(sequence)) {
            ArrayUtilsWraps.forEachBreakable(sequence.toString().toCharArray(), action, filter);
        }
    }

    public static <T extends CharSequence> void forEachCharsIndexing(@Nullable T sequence, @Nullable BiConsumer<Integer, Character> action) {
        forEachCharsIndexing(sequence, action, null);
    }

    public static <T extends CharSequence> void forEachCharsIndexing(@Nullable T sequence, @Nullable BiConsumer<Integer, Character> action, @Nullable BiPredicate<Integer, Character> filter) {
        if (StringUtils.isNotEmpty(sequence)) {
            ArrayUtilsWraps.forEachIndexing(sequence.toString().toCharArray(), action, filter);
        }
    }

    public static <T extends CharSequence> void ifBlank(@Nullable T sequence, @Nullable Consumer<? super T> action) {
        ifBlankOrElse(sequence, action, null);
    }

    public static <T extends CharSequence> void ifBlank(@Nullable T sequence, @Nullable Runnable action) {
        ifBlankOrElse(sequence, action, null);
    }

    public static <T extends CharSequence> void ifBlankOrElse(@Nullable T sequence, @Nullable Consumer<? super T> absentAction, @Nullable Consumer<? super T> presentAction) {
        if (StringUtils.isBlank(sequence)) {
            if (absentAction != null) {
                absentAction.accept(sequence);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(sequence);
            }
        }
    }

    public static <T extends CharSequence> void ifBlankOrElse(@Nullable T sequence, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (StringUtils.isBlank(sequence)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    public static <T extends CharSequence> void ifEmpty(@Nullable T sequence, @Nullable Consumer<? super T> action) {
        ifEmptyOrElse(sequence, action, null);
    }

    public static <T extends CharSequence> void ifEmpty(@Nullable T sequence, @Nullable Runnable action) {
        ifEmptyOrElse(sequence, action, null);
    }

    public static <T extends CharSequence> void ifEmptyOrElse(@Nullable T sequence, @Nullable Consumer<? super T> absentAction, @Nullable Consumer<? super T> presentAction) {
        if (StringUtils.isEmpty(sequence)) {
            if (absentAction != null) {
                absentAction.accept(sequence);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(sequence);
            }
        }
    }

    public static <T extends CharSequence> void ifEmptyOrElse(@Nullable T sequence, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (StringUtils.isEmpty(sequence)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    public static <T extends CharSequence> void ifNotBlank(@Nullable T sequence, @Nullable Consumer<? super T> action) {
        ifNotBlank(sequence, action, null);
    }

    public static <T extends CharSequence> void ifNotBlank(@Nullable T sequence, @Nullable Consumer<? super T> action, @Nullable Predicate<? super T> filter) {
        if (StringUtils.isNotBlank(sequence) && action != null && (filter == null || filter.test(sequence))) {
            action.accept(sequence);
        }
    }

    public static <T extends CharSequence> void ifNotBlank(@Nullable T sequence, @Nullable Runnable action) {
        if (StringUtils.isNotBlank(sequence) && action != null) {
            action.run();
        }
    }

    public static <T extends CharSequence> void ifNotEmpty(@Nullable T sequence, @Nullable Consumer<? super T> action) {
        ifNotEmpty(sequence, action, null);
    }

    public static <T extends CharSequence> void ifNotEmpty(@Nullable T sequence, @Nullable Consumer<? super T> action, @Nullable Predicate<? super T> filter) {
        if (StringUtils.isNotEmpty(sequence) && action != null && (filter == null || filter.test(sequence))) {
            action.accept(sequence);
        }
    }

    public static <T extends CharSequence> void ifNotEmpty(@Nullable T sequence, @Nullable Runnable action) {
        if (StringUtils.isNotEmpty(sequence) && action != null) {
            action.run();
        }
    }

    public static int indexOfAny(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> searches) {
        if (StringUtils.isEmpty(sequence) || CollectionPlainWraps.isEmpty(searches)) {
            return -1;
        }
        return StringUtils.indexOfAny(sequence, searches.toArray(ArrayUtilsWraps.EMPTY_CHAR_SEQUENCE_ARRAY));
    }

    public static boolean isDistinctIgnoreNull(@Nullable String... texts) {
        return isDistinctIgnoreNull(ArrayUtilsWraps.asList(texts));
    }

    public static boolean isDistinctIgnoreNull(@Nullable Collection<String> texts) {
        if (CollectionPlainWraps.isEmpty(texts)) {
            return false;
        }
        List<String> list = texts.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionPlainWraps.isEmpty(list)) {
            return false;
        }
        Set<String> set = new LinkedHashSet<>(list);
        return CollectionPlainWraps.isSameSize(list, set);
    }

    public static boolean isDistinctIgnoreEmpty(@Nullable String... texts) {
        return isDistinctIgnoreEmpty(ArrayUtilsWraps.asList(texts));
    }

    public static boolean isDistinctIgnoreEmpty(@Nullable Collection<String> texts) {
        if (CollectionPlainWraps.isEmpty(texts)) {
            return false;
        }
        List<String> list = texts.stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        if (CollectionPlainWraps.isEmpty(list)) {
            return false;
        }
        Set<String> set = new LinkedHashSet<>(list);
        return CollectionPlainWraps.isSameSize(list, set);
    }

    public static boolean isDistinctIgnoreBlank(@Nullable String... texts) {
        return isDistinctIgnoreBlank(ArrayUtilsWraps.asList(texts));
    }

    public static boolean isDistinctIgnoreBlank(@Nullable Collection<String> texts) {
        if (CollectionPlainWraps.isEmpty(texts)) {
            return false;
        }
        List<String> list = texts.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (CollectionPlainWraps.isEmpty(list)) {
            return false;
        }
        Set<String> set = new LinkedHashSet<>(list);
        return CollectionPlainWraps.isSameSize(list, set);
    }

    public static boolean isWrappedWith(@Nullable CharSequence sequence, char delimiter) {
        return isWrappedWith(sequence, CharUtils.toString(delimiter));
    }

    public static boolean isWrappedWith(@Nullable CharSequence sequence, @Nullable CharSequence delimiter) {
        return StringUtils.isNoneEmpty(sequence, delimiter) && StringUtils.length(sequence) >= 2 * StringUtils.length(delimiter) && StringUtils.startsWith(sequence, delimiter) && StringUtils.endsWith(sequence, delimiter);
    }

    public static boolean isWrappedWithIgnoreCase(@Nullable CharSequence sequence, char delimiter) {
        return isWrappedWithIgnoreCase(sequence, CharUtils.toString(delimiter));
    }

    public static boolean isWrappedWithIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence delimiter) {
        return StringUtils.isNoneEmpty(sequence, delimiter) && StringUtils.length(sequence) >= 2 * StringUtils.length(delimiter) && StringUtils.startsWithIgnoreCase(sequence, delimiter) && StringUtils.endsWithIgnoreCase(sequence, delimiter);
    }

    public static <T> String joinWith(@Nullable T[] objects, char delimiter) {
        return joinWith(ArrayUtilsWraps.asList(objects), delimiter);
    }

    public static String joinWith(@Nullable Collection<?> collection, char delimiter) {
        return joinWith(collection, CharUtils.toString(delimiter), null, null);
    }

    public static <T> String joinWith(@Nullable T[] objects, char delimiter, char prefix, char suffix) {
        return joinWith(ArrayUtilsWraps.asList(objects), delimiter, prefix, suffix);
    }

    public static String joinWith(@Nullable Collection<?> collection, char delimiter, char prefix, char suffix) {
        return joinWith(collection, CharUtils.toString(delimiter), CharUtils.toString(prefix), CharUtils.toString(suffix));
    }

    public static <T> String joinWith(@Nullable T[] objects, @Nullable CharSequence delimiter) {
        return joinWith(ArrayUtilsWraps.asList(objects), delimiter);
    }

    public static String joinWith(@Nullable Collection<?> collection, @Nullable CharSequence delimiter) {
        return joinWith(collection, delimiter, null, null);
    }

    public static <T> String joinWith(@Nullable T[] objects, @Nullable CharSequence delimiter, char prefix, char suffix) {
        return joinWith(ArrayUtilsWraps.asList(objects), delimiter, prefix, suffix);
    }

    public static String joinWith(@Nullable Collection<?> collection, @Nullable CharSequence delimiter, char prefix, char suffix) {
        return joinWith(collection, delimiter, CharUtils.toString(prefix), CharUtils.toString(suffix));
    }

    public static <T> String joinWith(@Nullable T[] objects, @Nullable CharSequence delimiter, @Nullable CharSequence prefix, @Nullable CharSequence suffix) {
        return joinWith(ArrayUtilsWraps.asList(objects), delimiter, prefix, suffix);
    }

    /**
     * Returns the joined elements into a String containing the provided elements, with delimiter, prefix and suffix
     *
     * <pre>
     *     StringUtilsWraps.joinWith(Arrays.asList(new String[]{"a", "b", "c"}), null) = "abc"
     *     StringUtilsWraps.joinWith(Arrays.asList(new String[]{"a", "b", "c"}), '*') = "a*b*c"
     *     StringUtilsWraps.joinWith(Arrays.asList(new String[]{"a", "b", "c"}), '*', '[', ']') = "[a]*[b]*[c]"
     * </pre>
     *
     * @param collection the varargs providing the values to join together
     * @param delimiter the separator character to use, null treated as empty string
     * @param prefix the prefix for each element in the collection, null treated as empty string
     * @param suffix the suffix for each element in the collection, null treated as empty string
     *
     * @return the joined elements into a String containing the provided elements, with delimiter, prefix and suffix
     *
     * @see org.apache.commons.lang3.StringUtils#joinWith
     */
    @Nullable
    public static <T> String joinWith(@Nullable Collection<T> collection, @Nullable CharSequence delimiter, @Nullable CharSequence prefix, @Nullable CharSequence suffix) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        int index = 0, size = collection.size();
        for (T element : collection) {
            builder.append(defaultString(prefix)).append(ObjectUtilsWraps.toString(element, StringUtils.EMPTY)).append(defaultString(suffix));
            if (index < size - 1) {
                builder.append(defaultString(delimiter));
            }
            index++;
        }
        return builder.toString();
    }

    @SafeVarargs
    public static <T> String joinWithColon(@Nullable T... objects) {
        return joinWithColon(ArrayUtilsWraps.asList(objects));
    }

    public static String joinWithColon(@Nullable Collection<?> collection) {
        return joinWith(collection, CharVariantConst.COLON);
    }

    @SafeVarargs
    public static <T> String joinWithComma(@Nullable T... objects) {
        return joinWithComma(ArrayUtilsWraps.asList(objects));
    }

    public static String joinWithComma(@Nullable Collection<?> collection) {
        return joinWith(collection, CharVariantConst.COMMA);
    }

    @SafeVarargs
    public static <T> String joinWithCommaSpace(@Nullable T... objects) {
        return joinWithCommaSpace(ArrayUtilsWraps.asList(objects));
    }

    public static String joinWithCommaSpace(@Nullable Collection<?> collection) {
        return joinWith(collection, SymbolVariantConst.COMMA_SPACE);
    }

    @SafeVarargs
    public static <T> String joinWithSemicolon(@Nullable T... objects) {
        return joinWithSemicolon(ArrayUtilsWraps.asList(objects));
    }

    public static String joinWithSemicolon(@Nullable Collection<?> collection) {
        return joinWith(collection, CharVariantConst.SEMICOLON);
    }

    @SafeVarargs
    public static <T> String joinWithSemicolonSpace(@Nullable T... objects) {
        return joinWithSemicolonSpace(ArrayUtilsWraps.asList(objects));
    }

    public static String joinWithSemicolonSpace(@Nullable Collection<?> collection) {
        return joinWith(collection, SymbolVariantConst.SEMICOLON_SPACE);
    }

    @SafeVarargs
    public static <T> String joinWithVertical(@Nullable T... objects) {
        return joinWithVertical(ArrayUtilsWraps.asList(objects));
    }

    public static String joinWithVertical(@Nullable Collection<?> collection) {
        return joinWith(collection, CharVariantConst.VERTICAL);
    }

    public static String joinWithIgnoreBlank(char delimiter, @Nullable CharSequence... sequences) {
        return joinWithIgnoreBlank(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    public static String joinWithIgnoreBlank(char delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        return joinWithIgnoreBlank(CharUtils.toString(delimiter), sequences);
    }

    public static String joinWithIgnoreBlank(@Nullable CharSequence delimiter, @Nullable CharSequence... sequences) {
        return joinWithIgnoreBlank(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static String joinWithIgnoreBlank(@Nullable CharSequence delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isEmpty(sequences) ? null : joinWith(removeBlankSequence(sequences), delimiter);
    }

    public static String joinWithIgnoreEmpty(char delimiter, @Nullable CharSequence... sequences) {
        return joinWithIgnoreEmpty(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    public static String joinWithIgnoreEmpty(char delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        return joinWithIgnoreEmpty(CharUtils.toString(delimiter), sequences);
    }

    public static String joinWithIgnoreEmpty(@Nullable CharSequence delimiter, @Nullable CharSequence... sequences) {
        return joinWithIgnoreEmpty(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static String joinWithIgnoreEmpty(@Nullable CharSequence delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isEmpty(sequences) ? null : joinWith(removeEmptySequences(sequences), delimiter);
    }

    public static String joinOnce(char delimiter, @Nullable CharSequence... sequences) {
        return joinOnce(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    public static String joinOnce(char delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        return joinOnce(CharUtils.toString(delimiter), sequences);
    }

    public static String joinOnce(@Nullable CharSequence delimiter, @Nullable CharSequence... sequences) {
        return joinOnce(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    /**
     * Returns the joined elements into a single String containing the provided elements, with one single delimiter
     *
     * <pre>
     *     StringUtilsWraps.joinWithOnce(null, "a", "b", "c") = "abc"
     *     StringUtilsWraps.joinWithOnce('', "a", "b", "c") = "abc"
     *     StringUtilsWraps.joinWithOnce(':', "a", "b", "c") = "a:b:c"
     *     StringUtilsWraps.joinWithOnce(':', "a:", "b", "c") = "a:b:c"
     *     StringUtilsWraps.joinWithOnce(':', "a", ":b", "c") = "a:b:c"
     *     StringUtilsWraps.joinWithOnce(':', "a", "b", ":c:") = "a:b:c:"
     * </pre>
     *
     * @param delimiter the separator character to use, null treated as empty string
     * @param sequences the varargs providing the values to join together
     *
     * @return the joined elements into a single String containing the provided elements, with one single delimiter
     *
     * @see org.apache.commons.lang3.StringUtils#joinWith
     */
    @Nullable
    public static String joinOnce(@Nullable CharSequence delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        if (CollectionPlainWraps.isEmpty(sequences)) {
            return null;
        }
        if (StringUtils.isEmpty(delimiter)) {
            return sequences.stream().map(StringUtilsWraps::defaultString).collect(Collectors.joining());
        }
        StringJoiner joiner = new StringJoiner(delimiter);
        int index = 0, size = sequences.size();
        for (CharSequence sequence : sequences) {
            if (index == 0) {
                joiner.add(defaultString(size == 1 ? sequence : removeEnd(sequence, delimiter)));
            } else {
                joiner.add(defaultString(removeStart(sequence, delimiter)));
            }
            index++;
        }
        return joiner.toString();
    }

    public static String joinOnceIgnoreCase(char delimiter, @Nullable CharSequence... sequences) {
        return joinOnceIgnoreCase(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    public static String joinOnceIgnoreCase(char delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        return joinOnceIgnoreCase(CharUtils.toString(delimiter), sequences);
    }

    public static String joinOnceIgnoreCase(@Nullable CharSequence delimiter, @Nullable CharSequence... sequences) {
        return joinOnceIgnoreCase(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static String joinOnceIgnoreCase(@Nullable CharSequence delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        if (CollectionPlainWraps.isEmpty(sequences)) {
            return null;
        }
        if (StringUtils.isEmpty(delimiter)) {
            return sequences.stream().map(StringUtilsWraps::defaultString).collect(Collectors.joining());
        }
        StringJoiner joiner = new StringJoiner(delimiter);
        int index = 0, size = sequences.size();
        for (CharSequence sequence : sequences) {
            if (index == 0) {
                joiner.add(defaultString(size == 1 ? sequence : removeEndIgnoreCase(sequence, delimiter)));
            } else {
                joiner.add(defaultString(removeStartIgnoreCase(sequence, delimiter)));
            }
            index++;
        }
        return joiner.toString();
    }

    public static String joinOnceIgnoreBlank(char delimiter, @Nullable CharSequence... sequences) {
        return joinOnceIgnoreBlank(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    public static String joinOnceIgnoreBlank(char delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        return joinOnceIgnoreBlank(CharUtils.toString(delimiter), sequences);
    }

    public static String joinOnceIgnoreBlank(@Nullable CharSequence delimiter, @Nullable CharSequence... sequences) {
        return joinOnceIgnoreBlank(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static String joinOnceIgnoreBlank(@Nullable CharSequence delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        if (CollectionPlainWraps.isEmpty(sequences)) {
            return null;
        }
        if (StringUtils.isEmpty(delimiter)) {
            return sequences.stream().map(StringUtilsWraps::defaultString).collect(Collectors.joining());
        }
        StringJoiner joiner = new StringJoiner(delimiter);
        int index = 0, size = sequences.size();
        for (CharSequence sequence : sequences) {
            String alias;
            if (index == 0) {
                alias = defaultString(size == 1 ? sequence : removeEnd(sequence, delimiter));
            } else {
                alias = defaultString(removeStart(sequence, delimiter));
            }
            if (StringUtils.isNotBlank(alias)) {
                joiner.add(alias);
            }
            index++;
        }
        return joiner.toString();
    }

    public static String joinOnceIgnoreEmpty(char delimiter, @Nullable CharSequence... sequences) {
        return joinOnceIgnoreEmpty(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    public static String joinOnceIgnoreEmpty(char delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        return joinOnceIgnoreEmpty(CharUtils.toString(delimiter), sequences);
    }

    public static String joinOnceIgnoreEmpty(@Nullable CharSequence delimiter, @Nullable CharSequence... sequences) {
        return joinOnceIgnoreEmpty(delimiter, ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static String joinOnceIgnoreEmpty(@Nullable CharSequence delimiter, @Nullable Collection<? extends CharSequence> sequences) {
        if (CollectionPlainWraps.isEmpty(sequences)) {
            return null;
        }
        if (StringUtils.isEmpty(delimiter)) {
            return sequences.stream().map(StringUtilsWraps::defaultString).collect(Collectors.joining());
        }
        StringJoiner joiner = new StringJoiner(delimiter);
        int index = 0, size = sequences.size();
        for (CharSequence sequence : sequences) {
            String alias;
            if (index == 0) {
                alias = defaultString(size == 1 ? sequence : removeEnd(sequence, delimiter));
            } else {
                alias = defaultString(removeStart(sequence, delimiter));
            }
            if (StringUtils.isNotEmpty(alias)) {
                joiner.add(alias);
            }
            index++;
        }
        return joiner.toString();
    }

    public static <T> String joinRoughly(@Nullable T[] objects) {
        return joinRoughly(ArrayUtilsWraps.asList());
    }

    public static String joinRoughly(@Nullable Collection<?> collection) {
        return joinWith(collection, StringUtils.EMPTY);
    }

    public static String lastSubstringBetween(@Nullable String text, char tag) {
        return lastSubstringBetween(text, tag, tag);
    }

    public static String lastSubstringBetween(@Nullable String text, @Nullable CharSequence tag) {
        return lastSubstringBetween(text, tag, tag);
    }

    public static String lastSubstringBetween(@Nullable String text, char open, char close) {
        return lastSubstringBetween(text, CharUtils.toString(open), CharUtils.toString(close));
    }

    /**
     * Returns a String that is nested in between two Strings, only the last match is returned
     *
     * @param text a String containing the substring
     * @param open the String before the substring
     * @param close the String after the substring
     *
     * @return a String that is nested in between two Strings, only the last match is returned
     *
     * @see org.apache.commons.lang3.StringUtils#substringBetween(String, String, String)
     */
    @Nullable
    public static String lastSubstringBetween(@Nullable String text, @Nullable CharSequence open, @Nullable CharSequence close) {
        if (StringUtils.isAnyEmpty(text, open, close)) {
            return null;
        }
        int start = StringUtils.lastIndexOf(text, open), length = StringUtils.length(open);
        int end = StringUtils.lastIndexOf(text, close, start + length);
        return (start != -1 && end != -1) ? StringUtils.substring(text, start + length, end) : null;
    }

    @Nullable
    public static <T extends CharSequence> T nullIfBlank(@Nullable T sequence) {
        return StringUtils.isBlank(sequence) ? null : sequence;
    }

    @Nullable
    public static <T extends CharSequence> T nullIfEmpty(@Nullable T sequence) {
        return StringUtils.isEmpty(sequence) ? null : sequence;
    }

    @Nullable
    public static CharSequence nullIfEquals(@Nullable CharSequence sequence, @Nullable CharSequence comparison) {
        return StringUtils.equals(sequence, comparison) ? null : sequence;
    }

    @Nullable
    public static CharSequence nullIfEqualsIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence comparison) {
        return StringUtils.equalsIgnoreCase(sequence, comparison) ? null : sequence;
    }

    /**
     * Returns a string that prepends the prefix to the start of the sequence if the sequence does not already start with any of the prefixes
     *
     * @param sequence the source char sequence to be prepended
     * @param prepend the prefix to prepend to the start of the string
     *
     * @return a string that prepends the prefix to the start of the string if the string does not already start with any of the prefixes
     */
    public static String prependIfMissing(@Nullable CharSequence sequence, char prepend) {
        return prependIfMissing(sequence, CharUtils.toString(prepend));
    }

    /**
     * Returns a string that prepends the prefix to the start of the sequence if the sequence does not already start with any of the prefixes
     *
     * @param sequence the source char sequence to be prepended
     * @param prepend the prefix to prepend to the start of the string
     *
     * @return a string that prepends the prefix to the start of the string if the string does not already start with any of the prefixes
     */
    public static String prependIfMissing(@Nullable CharSequence sequence, @Nullable CharSequence prepend) {
        return StringUtils.prependIfMissing(CharSequenceWraps.toStringIgnoreNull(sequence), prepend, prepend);
    }

    public static void prependIfMissing(@Nullable Collection<String> collection, @Nullable CharSequence prepend) {
        prependIfMissing(collection, prepend, ArrayUtilsWraps.singletonArray(prepend));
    }

    public static void prependIfMissing(@Nullable Collection<String> collection, char prepend, char... prefixes) {
        prependIfMissing(collection, CharUtils.toString(prepend), CharUtilsWraps.toStringArray(prefixes));
    }

    public static void prependIfMissing(@Nullable Collection<String> collection, char prepend, @Nullable CharSequence... prefixes) {
        prependIfMissing(collection, CharUtils.toString(prepend), prefixes);
    }

    public static void prependIfMissing(@Nullable Collection<String> collection, @Nullable CharSequence prepend, char... prefixes) {
        prependIfMissing(collection, prepend, CharUtilsWraps.toStringArray(prefixes));
    }

    /**
     * Prepend the prefix to the start of the string if the string does not already start with any of the suffixes
     *
     * @param collection the collection to get the input from
     * @param prepend the prefix to append to the start of the string
     * @param prefixes additional prefixes that are valid terminators
     *
     * @throws java.lang.UnsupportedOperationException if the <tt>clear</tt> operation is not supported by this collection
     * @see "org.springframework.util.ObjectUtils#addObjectToArray"
     */
    public static void prependIfMissing(@Nullable Collection<String> collection, @Nullable CharSequence prepend, @Nullable CharSequence... prefixes) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.prependIfMissing(element, prepend, prefixes)).forEach(collection::add);
    }

    /**
     * Returns a string that prepends the prefix to the start of the sequence if the sequence does not already start with any of the prefixes, case-insensitive
     *
     * @param sequence the source char sequence to be prepended
     * @param prepend the prefix to prepend to the start of the string
     *
     * @return a string that prepends the prefix to the start of the string if the string does not already start with any of the prefixes
     */
    public static String prependIfMissingIgnoreCase(@Nullable CharSequence sequence, char prepend) {
        return prependIfMissingIgnoreCase(sequence, CharUtils.toString(prepend));
    }

    /**
     * Returns a string that prepends the prefix to the start of the sequence if the sequence does not already start with any of the prefixes, case-insensitive
     *
     * @param sequence the source char sequence to be prepended
     * @param prepend the prefix to prepend to the start of the string
     *
     * @return a string that prepends the prefix to the start of the string if the string does not already start with any of the prefixes
     */
    public static String prependIfMissingIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence prepend) {
        return StringUtils.prependIfMissingIgnoreCase(CharSequenceWraps.toStringIgnoreNull(sequence), prepend, prepend);
    }

    public static void prependIfMissingIgnoreCase(@Nullable Collection<String> collection, @Nullable CharSequence prepend) {
        prependIfMissingIgnoreCase(collection, prepend, ArrayUtilsWraps.singletonArray(prepend));
    }

    public static void prependIfMissingIgnoreCase(@Nullable Collection<String> collection, char prepend, char... prefixes) {
        prependIfMissingIgnoreCase(collection, CharUtils.toString(prepend), CharUtilsWraps.toStringArray(prefixes));
    }

    public static void prependIfMissingIgnoreCase(@Nullable Collection<String> collection, char prepend, @Nullable CharSequence... prefixes) {
        prependIfMissingIgnoreCase(collection, CharUtils.toString(prepend), prefixes);
    }

    public static void prependIfMissingIgnoreCase(@Nullable Collection<String> collection, @Nullable CharSequence prefix, char... prefixes) {
        prependIfMissingIgnoreCase(collection, prefix, CharUtilsWraps.toStringArray(prefixes));
    }

    /**
     * Prepend the prefix to the start of the string if the string does not already start with any of the suffixes, case-insensitive
     *
     * @param collection the collection to get the input from
     * @param prepend the prefix to append to the start of the string
     * @param prefixes additional prefixes that are valid terminators
     *
     * @throws java.lang.UnsupportedOperationException if the <tt>clear</tt> operation is not supported by this collection
     * @see "org.springframework.util.ObjectUtils#addObjectToArray"
     */
    public static void prependIfMissingIgnoreCase(@Nullable Collection<String> collection, @Nullable CharSequence prepend, @Nullable CharSequence... prefixes) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.prependIfMissingIgnoreCase(element, prepend, prefixes)).forEach(collection::add);
    }

    public static String prependIfSequenceNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence prepend) {
        return prependIfSequenceNotEmpty(sequence, prepend, null);
    }

    public static String prependIfSequenceNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence prepend, char delimiter) {
        return prependIfSequenceNotEmpty(sequence, prepend, CharUtils.toString(delimiter));
    }

    public static String prependIfSequenceNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence prepend, @Nullable CharSequence delimiter) {
        return StringUtils.isEmpty(sequence) ? CharSequenceWraps.toStringIgnoreNull(sequence) : StringUtils.join(prepend, delimiter, sequence);
    }

    public static String prependIfSequenceNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence prepend) {
        return prependIfSequenceNotBlank(sequence, prepend, null);
    }

    public static String prependIfSequenceNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence prepend, char delimiter) {
        return prependIfSequenceNotBlank(sequence, prepend, CharUtils.toString(delimiter));
    }

    public static String prependIfSequenceNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence prepend, @Nullable CharSequence delimiter) {
        return StringUtils.isBlank(sequence) ? CharSequenceWraps.toStringIgnoreNull(sequence) : StringUtils.join(prepend, delimiter, sequence);
    }

    public static String prependIfPrefixNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence prepend) {
        return prependIfPrefixNotEmpty(sequence, prepend, null);
    }

    public static String prependIfPrefixNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence prepend, char delimiter) {
        return prependIfPrefixNotEmpty(sequence, prepend, CharUtils.toString(delimiter));
    }

    public static String prependIfPrefixNotEmpty(@Nullable CharSequence sequence, @Nullable CharSequence prepend, @Nullable CharSequence delimiter) {
        return StringUtils.isEmpty(prepend) ? CharSequenceWraps.toStringIgnoreNull(sequence) : StringUtils.join(prepend, delimiter, sequence);
    }

    public static String prependIfPrefixNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence prepend) {
        return prependIfPrefixNotBlank(sequence, prepend, null);
    }

    public static String prependIfPrefixNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence prepend, char delimiter) {
        return prependIfPrefixNotBlank(sequence, prepend, CharUtils.toString(delimiter));
    }

    public static String prependIfPrefixNotBlank(@Nullable CharSequence sequence, @Nullable CharSequence prepend, @Nullable CharSequence delimiter) {
        return StringUtils.isBlank(prepend) ? CharSequenceWraps.toStringIgnoreNull(sequence) : StringUtils.join(prepend, delimiter, sequence);
    }

    public static String quoteSingle(@Nullable CharSequence sequence) {
        return quoteSingle(sequence, false);
    }

    public static String quoteSingle(@Nullable CharSequence sequence, boolean emptyAsNull) {
        return wrapWith(sequence, CharVariantConst.SINGLE_QUOTE, emptyAsNull);
    }

    public static String quoteDouble(@Nullable CharSequence sequence) {
        return quoteDouble(sequence, false);
    }

    public static String quoteDouble(@Nullable CharSequence sequence, boolean emptyAsNull) {
        return wrapWith(sequence, CharVariantConst.DOUBLE_QUOTE, emptyAsNull);
    }

    public static String removeAll(@Nullable CharSequence sequence, char... removes) {
        return removeAll(sequence, CharUtilsWraps.toStringArray(removes));
    }

    public static String removeAll(@Nullable CharSequence sequence, @Nullable CharSequence... removes) {
        return removeAll(sequence, ArrayUtilsWraps.asList(removes));
    }

    /**
     * Returns a String that removed some substrings from within the source string
     *
     * @param sequence the source char sequence to search
     * @param removes some Strings to search for and remove
     *
     * @return a String that removed some substrings from within the source string
     *
     * @see org.apache.commons.lang3.StringUtils#remove
     */
    public static String removeAll(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> removes) {
        String text = CharSequenceWraps.toStringIgnoreNull(sequence);
        if (StringUtils.isEmpty(text) || removes == null || removes.isEmpty()) {
            return text;
        }
        for (CharSequence remove : removes) {
            text = StringUtils.remove(text, CharSequenceWraps.toStringIgnoreNull(remove));
        }
        return text;
    }

    public static CharSequence[] removeBlankSequence(@Nullable CharSequence... sequences) {
        return removeBlankSequence(ArrayUtilsWraps.asList(sequences));
    }

    /**
     * Returns char sequences which every element is not blank
     *
     * @param sequences the source of char sequences
     *
     * @return char sequences which every element is not blank
     */
    @Nullable
    public static CharSequence[] removeBlankSequence(@Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isEmpty(sequences) ? null : sequences.stream().filter(StringUtils::isNotBlank).toArray(CharSequence[]::new);
    }

    public static CharSequence[] removeEmptySequences(@Nullable CharSequence... sequences) {
        return removeEmptySequences(ArrayUtilsWraps.asList(sequences));
    }

    /**
     * Returns char sequences which every element is not empty
     *
     * @param sequences the source of char sequences
     *
     * @return char sequences which every element is not empty
     */
    @Nullable
    public static CharSequence[] removeEmptySequences(@Nullable Collection<? extends CharSequence> sequences) {
        return CollectionPlainWraps.isEmpty(sequences) ? null : sequences.stream().filter(StringUtils::isNotEmpty).toArray(CharSequence[]::new);
    }

    public static String[] removeBlankString(@Nullable String... texts) {
        return removeBlankString(ArrayUtilsWraps.asList(texts));
    }

    /**
     * Returns strings which every element is not blank
     *
     * @param texts the source of strings
     *
     * @return strings which every element is not blank
     */
    @Nullable
    public static String[] removeBlankString(@Nullable Collection<String> texts) {
        return CollectionPlainWraps.isEmpty(texts) ? null : texts.stream().filter(StringUtils::isNotBlank).toArray(String[]::new);
    }

    public static String[] removeEmptyStrings(@Nullable String... texts) {
        return removeEmptyStrings(ArrayUtilsWraps.asList(texts));
    }

    /**
     * Returns strings which every element is not empty
     *
     * @param texts the source of strings
     *
     * @return strings which every element is not empty
     */
    @Nullable
    public static String[] removeEmptyStrings(@Nullable Collection<String> texts) {
        return CollectionPlainWraps.isEmpty(texts) ? null : texts.stream().filter(StringUtils::isNotEmpty).toArray(String[]::new);
    }

    public static String removeEnd(@Nullable CharSequence sequence, char remove) {
        return removeEnd(sequence, CharUtils.toString(remove));
    }

    public static String removeEnd(@Nullable CharSequence sequence, @Nullable CharSequence remove) {
        return StringUtils.removeEnd(CharSequenceWraps.toStringIgnoreNull(sequence), CharSequenceWraps.toStringIgnoreNull(remove));
    }

    public static String removeEnd(@Nullable CharSequence sequence, char... removes) {
        return removeEnd(sequence, CharUtilsWraps.toStringArray(removes));
    }

    public static String removeEnd(@Nullable CharSequence sequence, @Nullable CharSequence... removes) {
        return removeEnd(sequence, ArrayUtilsWraps.asList(removes));
    }

    /**
     * Returns a string that removed some substrings only if at end of the source string
     *
     * @param sequence the source char sequence to search
     * @param removes some Strings to search for and remove
     *
     * @return a string that removed some substrings only if at end of the source string
     *
     * @see org.apache.commons.lang3.StringUtils#removeEnd
     */
    public static String removeEnd(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> removes) {
        String text = CharSequenceWraps.toStringIgnoreNull(sequence);
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(removes)) {
            return text;
        }
        for (CharSequence remove : removes) {
            text = StringUtils.removeEnd(text, CharSequenceWraps.toStringIgnoreNull(remove));
        }
        return text;
    }

    public static void removeEnd(@Nullable Collection<String> collection, char remove) {
        removeEnd(collection, CharUtils.toString(remove));
    }

    public static void removeEnd(@Nullable Collection<String> collection, char... removes) {
        removeEnd(collection, CharUtilsWraps.toStringArray(removes));
    }

    public static void removeEnd(@Nullable Collection<String> collection, @Nullable CharSequence... removes) {
        removeEnd(collection, ArrayUtilsWraps.asList(removes));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void removeEnd(@Nullable Collection<String> collection, @Nullable Collection<? extends CharSequence> removes) {
        if (CollectionPlainWraps.anyEmpty(collection, removes)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> removeEnd(element, removes)).forEach(collection::add);
    }

    public static String removeEndIgnoreCase(@Nullable CharSequence sequence, char remove) {
        return removeEndIgnoreCase(sequence, CharUtils.toString(remove));
    }

    public static String removeEndIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence remove) {
        return StringUtils.removeEnd(CharSequenceWraps.toStringIgnoreNull(sequence), CharSequenceWraps.toStringIgnoreNull(remove));
    }

    public static String removeEndIgnoreCase(@Nullable CharSequence sequence, char... removes) {
        return removeEndIgnoreCase(sequence, CharUtilsWraps.toStringArray(removes));
    }

    public static String removeEndIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence... removes) {
        return removeEndIgnoreCase(sequence, ArrayUtilsWraps.asList(removes));
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#removeEndIgnoreCase
     */
    public static String removeEndIgnoreCase(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> removes) {
        String text = CharSequenceWraps.toStringIgnoreNull(sequence);
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(removes)) {
            return text;
        }
        for (CharSequence remove : removes) {
            text = StringUtils.removeEndIgnoreCase(text, CharSequenceWraps.toStringIgnoreNull(remove));
        }
        return text;
    }

    public static void removeEndIgnoreCase(@Nullable Collection<String> collection, char remove) {
        removeEndIgnoreCase(collection, CharUtils.toString(remove));
    }

    public static void removeEndIgnoreCase(@Nullable Collection<String> collection, char... removes) {
        removeEndIgnoreCase(collection, CharUtilsWraps.toStringArray((removes)));
    }

    public static void removeEndIgnoreCase(@Nullable Collection<String> collection, @Nullable CharSequence... removes) {
        removeEndIgnoreCase(collection, ArrayUtilsWraps.asList(removes));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void removeEndIgnoreCase(@Nullable Collection<String> collection, @Nullable Collection<? extends CharSequence> removes) {
        if (CollectionPlainWraps.anyEmpty(collection, removes)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> removeEndIgnoreCase(element, removes)).forEach(collection::add);
    }

    public static String removeComma(@Nullable CharSequence sequence) {
        return StringUtils.remove(CharSequenceWraps.toStringIgnoreNull(sequence), CharVariantConst.COMMA);
    }

    public static String removeSpace(@Nullable CharSequence sequence) {
        return StringUtils.remove(CharSequenceWraps.toStringIgnoreNull(sequence), CharVariantConst.SPACE);
    }

    public static String removeStart(@Nullable CharSequence sequence, char remove) {
        return removeStart(sequence, CharUtils.toString(remove));
    }

    public static String removeStart(@Nullable CharSequence sequence, CharSequence remove) {
        return StringUtils.removeStart(CharSequenceWraps.toStringIgnoreNull(sequence), CharSequenceWraps.toStringIgnoreNull(remove));
    }

    public static String removeStart(@Nullable CharSequence sequence, char... removes) {
        return removeStart(sequence, CharUtilsWraps.toStringArray(removes));
    }

    public static String removeStart(@Nullable CharSequence sequence, @Nullable CharSequence... removes) {
        return removeStart(sequence, ArrayUtilsWraps.asList(removes));
    }

    /**
     * Returns a string that removed some substrings only if at start of the source string
     *
     * @param sequence the source char sequence to search
     * @param removes some Strings to search for and remove
     *
     * @return a string that removed some substrings only if at start of the source string
     *
     * @see org.apache.commons.lang3.StringUtils#removeStart
     */
    public static String removeStart(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> removes) {
        String text = CharSequenceWraps.toStringIgnoreNull(sequence);
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(removes)) {
            return text;
        }
        for (CharSequence remove : removes) {
            text = StringUtils.removeStart(text, CharSequenceWraps.toStringIgnoreNull(remove));
        }
        return text;
    }

    public static void removeStart(@Nullable Collection<String> collection, char remove) {
        removeStart(collection, CharUtils.toString(remove));
    }

    public static void removeStart(@Nullable Collection<String> collection, char... removes) {
        removeStart(collection, CharUtilsWraps.toStringArray(removes));
    }

    public static void removeStart(@Nullable Collection<String> collection, @Nullable CharSequence... removes) {
        removeStart(collection, ArrayUtilsWraps.asList(removes));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void removeStart(@Nullable Collection<String> collection, @Nullable Collection<? extends CharSequence> removes) {
        if (CollectionPlainWraps.anyEmpty(collection, removes)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> removeStart(element, removes)).forEach(collection::add);
    }

    public static String removeStartIgnoreCase(@Nullable CharSequence sequence, char remove) {
        return StringUtils.removeStartIgnoreCase(CharSequenceWraps.toStringIgnoreNull(sequence), CharUtils.toString(remove));
    }

    public static String removeStartIgnoreCase(@Nullable CharSequence sequence, @Nullable char... removes) {
        return removeStartIgnoreCase(sequence, CharUtilsWraps.toStringArray(removes));
    }

    public static String removeStartIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence... removes) {
        return removeStartIgnoreCase(sequence, ArrayUtilsWraps.asList(removes));
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#removeStartIgnoreCase
     */
    public static String removeStartIgnoreCase(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> removes) {
        String text = CharSequenceWraps.toStringIgnoreNull(sequence);
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(removes)) {
            return text;
        }
        for (CharSequence remove : removes) {
            text = StringUtils.removeStartIgnoreCase(text, CharSequenceWraps.toStringIgnoreNull(remove));
        }
        return text;
    }

    public static void removeStartIgnoreCase(@Nullable Collection<String> collection, char remove) {
        removeStartIgnoreCase(collection, CharUtils.toString(remove));
    }

    public static void removeStartIgnoreCase(@Nullable Collection<String> collection, char... removes) {
        removeStartIgnoreCase(collection, CharUtilsWraps.toStringArray(removes));
    }

    public static void removeStartIgnoreCase(@Nullable Collection<String> collection, @Nullable CharSequence... removes) {
        removeStartIgnoreCase(collection, ArrayUtilsWraps.asList(removes));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void removeStartIgnoreCase(@Nullable Collection<String> collection, @Nullable Collection<? extends CharSequence> removes) {
        if (CollectionPlainWraps.anyEmpty(collection, removes)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> removeStartIgnoreCase(element, removes)).forEach(collection::add);
    }

    public static String replace(@Nullable String text, char search, char replacement) {
        return replace(text, search, replacement, -1);
    }

    public static String replace(@Nullable String text, char search, char replacement, int max) {
        return StringUtils.isEmpty(text) ? text : StringUtils.replace(text, CharUtils.toString(search), CharUtils.toString(replacement), max);
    }

    public static String replace(@Nullable String text, @Nullable CharSequence search, @Nullable CharSequence replacement) {
        return replace(text, replacement, replacement, -1);
    }

    public static String replace(@Nullable String text, @Nullable CharSequence search, @Nullable CharSequence replacement, int max) {
        return StringUtils.isAnyEmpty(text, search) ? text : StringUtils.replace(text, CharSequenceWraps.toStringIgnoreNull(search), CharSequenceWraps.toStringIgnoreNull(replacement), max);
    }

    public static String replaceIgnoreCase(@Nullable String text, char search, char replacement) {
        return replaceIgnoreCase(text, search, replacement, -1);
    }

    public static String replaceIgnoreCase(@Nullable String text, char search, char replacement, int max) {
        return StringUtils.isEmpty(text) ? text : StringUtils.replaceIgnoreCase(text, CharUtils.toString(search), CharUtils.toString(replacement), max);
    }

    public static String replaceIgnoreCase(@Nullable String text, @Nullable CharSequence search, @Nullable CharSequence replacement) {
        return replaceIgnoreCase(text, replacement, replacement, -1);
    }

    public static String replaceIgnoreCase(@Nullable String text, @Nullable CharSequence search, @Nullable CharSequence replacement, int max) {
        return StringUtils.isAnyEmpty(text, search) ? text : StringUtils.replaceIgnoreCase(text, CharSequenceWraps.toStringIgnoreNull(search), CharSequenceWraps.toStringIgnoreNull(replacement), max);
    }

    public static String replaceAll(@Nullable String text, char replacement, char... searches) {
        if (StringUtils.isEmpty(text) || ArrayUtils.isEmpty(searches)) {
            return text;
        }
        String result = text;
        for (char search : searches) {
            result = replace(result, search, replacement);
        }
        return result;
    }

    public static String replaceAll(@Nullable String text, @Nullable CharSequence replacement, @Nullable CharSequence... searches) {
        return replaceAll(text, replacement, ArrayUtilsWraps.asList(searches));
    }

    public static String replaceAll(@Nullable String text, @Nullable CharSequence replacement, @Nullable Collection<? extends CharSequence> searches) {
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(searches)) {
            return text;
        }
        String result = text;
        for (CharSequence search : searches) {
            result = replace(result, search, replacement);
        }
        return result;
    }

    public static <K extends CharSequence, V extends CharSequence> String replaceAll(@Nullable String text, @Nullable Map<K, V> replacements) {
        if (StringUtils.isEmpty(text) || MapPlainWraps.isEmpty(replacements)) {
            return text;
        }
        String result = text;
        for (Map.Entry<K, V> entry : replacements.entrySet()) {
            result = replace(result, entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static String replaceAllIgnoreCase(@Nullable String text, char replacement, char... searches) {
        if (StringUtils.isEmpty(text) || ArrayUtils.isEmpty(searches)) {
            return text;
        }
        String result = text;
        for (char search : searches) {
            result = replaceIgnoreCase(result, search, replacement);
        }
        return result;
    }

    public static String replaceAllIgnoreCase(@Nullable String text, @Nullable CharSequence replacement, @Nullable CharSequence... searches) {
        return replaceAllIgnoreCase(text, replacement, ArrayUtilsWraps.asList(searches));
    }

    public static String replaceAllIgnoreCase(@Nullable String text, @Nullable CharSequence replacement, @Nullable Collection<? extends CharSequence> searches) {
        if (StringUtils.isEmpty(text) || CollectionPlainWraps.isEmpty(searches)) {
            return text;
        }
        String result = text;
        for (CharSequence search : searches) {
            result = replaceIgnoreCase(result, search, replacement);
        }
        return result;
    }

    public static <K extends CharSequence, V extends CharSequence> String replaceAllIgnoreCase(@Nullable String text, @Nullable Map<K, V> replacements) {
        if (StringUtils.isEmpty(text) || MapPlainWraps.isEmpty(replacements)) {
            return text;
        }
        String result = text;
        for (Map.Entry<K, V> entry : replacements.entrySet()) {
            result = replaceIgnoreCase(result, entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <T extends CharSequence> void reverseForEachChars(@Nullable T sequence, @Nullable Consumer<Character> action) {
        reverseForEachChars(sequence, action, null);
    }

    public static <T extends CharSequence> void reverseForEachChars(@Nullable T sequence, @Nullable Consumer<Character> action, @Nullable Predicate<Character> filter) {
        if (StringUtils.isNotEmpty(sequence)) {
            ArrayUtilsWraps.reverseForEach(sequence.toString().toCharArray(), action, filter);
        }
    }

    public static <T extends CharSequence> void reverseForEachCharsBreakable(@Nullable T sequence, @Nullable Function<Character, Boolean> action) {
        reverseForEachCharsBreakable(sequence, action, null);
    }

    public static <T extends CharSequence> void reverseForEachCharsBreakable(@Nullable T sequence, @Nullable Function<Character, Boolean> action, @Nullable Predicate<Character> filter) {
        if (StringUtils.isNotEmpty(sequence)) {
            ArrayUtilsWraps.reverseForEachBreakable(sequence.toString().toCharArray(), action, filter);
        }
    }

    public static <T extends CharSequence> void reverseForEachCharsIndexing(@Nullable T sequence, @Nullable BiConsumer<Integer, Character> action) {
        reverseForEachCharsIndexing(sequence, action, null);
    }

    public static <T extends CharSequence> void reverseForEachCharsIndexing(@Nullable T sequence, @Nullable BiConsumer<Integer, Character> action, @Nullable BiPredicate<Integer, Character> filter) {
        if (StringUtils.isNotEmpty(sequence)) {
            ArrayUtilsWraps.reverseForEachIndexing(sequence.toString().toCharArray(), action, filter);
        }
    }

    public static String[] splitBy(@Nullable String text, char delimiter) {
        return splitBy(text, delimiter, false, -1);
    }

    public static String[] splitBy(@Nullable String text, char delimiter, boolean trim) {
        return splitBy(text, delimiter, trim, -1);
    }

    public static String[] splitBy(@Nullable String text, char delimiter, boolean trim, int max) {
        return splitBy(text, CharUtils.toString(delimiter), trim, max);
    }

    public static String[] splitBy(@Nullable String text, @Nullable CharSequence delimiter) {
        return splitBy(text, delimiter, false, -1);
    }

    public static String[] splitBy(@Nullable String text, @Nullable CharSequence delimiter, boolean trim) {
        return splitBy(text, delimiter, trim, -1);
    }

    /**
     * Returns a split {@code String} array at the first occurrence of the delimiter
     *
     * @param text the string to split (potentially {@code null} or empty)
     * @param delimiter the separate string, {@code null} means use whitespace
     * @param trim indicates whether trim the split elements or not
     * @param max the maximum number of elements to include in the array. A zero or negative value implies no limit
     *
     * @return a split {@code String} array at the first occurrence of the delimiter
     */
    @Nullable
    public static String[] splitBy(@Nullable String text, @Nullable CharSequence delimiter, boolean trim, int max) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        String[] result = StringUtils.split(text, CharSequenceWraps.toStringIgnoreNull(delimiter), max);
        if (ArrayUtils.isEmpty(result)) {
            return null;
        }
        return trim ? trimStringArray(result) : result;
    }

    public static List<String> splitByToList(@Nullable String text, char delimiter) {
        return splitByToList(text, delimiter, false, -1);
    }

    public static List<String> splitByToList(@Nullable String text, char delimiter, boolean trim) {
        return splitByToList(text, delimiter, trim, -1);
    }

    public static List<String> splitByToList(@Nullable String text, char delimiter, boolean trim, int max) {
        return splitByToList(text, CharUtils.toString(delimiter), trim, max);
    }

    public static List<String> splitByToList(@Nullable String text, @Nullable CharSequence delimiter) {
        return splitByToList(text, delimiter, false, -1);
    }

    public static List<String> splitByToList(@Nullable String text, @Nullable CharSequence delimiter, boolean trim) {
        return splitByToList(text, delimiter, trim, -1);
    }

    public static List<String> splitByToList(@Nullable String text, @Nullable CharSequence delimiter, boolean trim, int max) {
        return ArrayUtilsWraps.asList(true, splitBy(text, delimiter, trim, max));
    }

    public static Set<String> splitByToSet(@Nullable String text, char delimiter) {
        return splitByToSet(text, delimiter, false, -1);
    }

    public static Set<String> splitByToSet(@Nullable String text, char delimiter, boolean trim) {
        return splitByToSet(text, delimiter, trim, -1);
    }

    public static Set<String> splitByToSet(@Nullable String text, char delimiter, boolean trim, int max) {
        return splitByToSet(text, CharUtils.toString(delimiter), trim, max);
    }

    public static Set<String> splitByToSet(@Nullable String text, @Nullable CharSequence delimiter) {
        return splitByToSet(text, delimiter, false, -1);
    }

    public static Set<String> splitByToSet(@Nullable String text, @Nullable CharSequence delimiter, boolean trim) {
        return splitByToSet(text, delimiter, trim, -1);
    }

    public static Set<String> splitByToSet(@Nullable String text, @Nullable CharSequence delimiter, boolean trim, int max) {
        return ArrayUtilsWraps.asSet(splitBy(text, delimiter, trim, max));
    }

    public static String[] splitByComma(@Nullable String text) {
        return splitByComma(text, false, -1);
    }

    public static String[] splitByComma(@Nullable String text, boolean trim) {
        return splitByComma(text, trim, -1);
    }

    public static String[] splitByComma(@Nullable String text, boolean trim, int max) {
        return splitBy(text, CharVariantConst.COMMA, trim, max);
    }

    public static List<String> splitByCommaToList(@Nullable String text) {
        return splitByCommaToList(text, false);
    }

    public static List<String> splitByCommaToList(@Nullable String text, boolean trim) {
        return splitByCommaToList(text, trim, -1);
    }

    public static List<String> splitByCommaToList(@Nullable String text, boolean trim, int max) {
        return ArrayUtilsWraps.asList(true, splitByComma(text, trim, max));
    }

    public static Set<String> splitByCommaToSet(@Nullable String text) {
        return splitByCommaToSet(text, false);
    }

    public static Set<String> splitByCommaToSet(@Nullable String text, boolean trim) {
        return splitByCommaToSet(text, trim, -1);
    }

    public static Set<String> splitByCommaToSet(@Nullable String text, boolean trim, int max) {
        return ArrayUtilsWraps.asSet(splitByComma(text, trim, max));
    }

    public static boolean startsWith(@Nullable CharSequence sequence, char comparison) {
        return StringUtils.startsWith(sequence, CharUtils.toString(comparison));
    }

    public static boolean startsWithIgnoreCase(@Nullable CharSequence sequence, char comparison) {
        return StringUtils.startsWithIgnoreCase(sequence, CharUtils.toString(comparison));
    }

    public static boolean startsWithAnyIgnoreCase(@Nullable CharSequence sequence, char... comparisons) {
        return startsWithAnyIgnoreCase(sequence, CharUtilsWraps.toStringArray(comparisons));
    }

    public static boolean startsWithAnyIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence... comparisons) {
        return startsWithAnyIgnoreCase(sequence, ArrayUtilsWraps.asList(comparisons));
    }

    /**
     * Check if a CharSequence starts with any of the provided case-insensitive prefixes
     *
     * @param sequence the CharSequence to check
     * @param comparisons the case-insensitive CharSequence prefixes
     *
     * @see org.apache.commons.lang3.StringUtils#startsWithAny
     */
    public static boolean startsWithAnyIgnoreCase(@Nullable CharSequence sequence, @Nullable Collection<? extends CharSequence> comparisons) {
        return StringUtils.isNotEmpty(sequence) && CollectionPlainWraps.isNotEmpty(comparisons) && comparisons.stream().anyMatch(element -> StringUtils.startsWithIgnoreCase(sequence, element));
    }

    public static String substringAfter(@Nullable String text, char separator, int length) {
        return substringAfter(text, CharUtils.toString(separator), length);
    }

    /**
     * Returns the substring after the first occurrence of a separator, with specified length, the separator is not returned
     *
     * <pre>
     *     StringUtilsWraps.substringAfter("foo.bar.bar", ".", 3) = "bar"
     *     StringUtilsWraps.substringAfter("foo.foobar.bar", ".", 3) = "foo"
     *     StringUtilsWraps.substringAfter("foo.foobar.bar", "*", 3) = null
     * </pre>
     *
     * @param text the String to get a substring from
     * @param separator the String to search for
     * @param length the max length of substring
     *
     * @return the substring after the first occurrence of a separator, with specified length, the separator is not returned
     *
     * @see org.apache.commons.lang3.StringUtils#substringAfter(String, String)
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String substringAfter(@Nullable String text, @Nullable String separator, int length) {
        if (StringUtils.isAnyEmpty(text, separator) || length <= 0) {
            return null;
        }
        int index = text.indexOf(separator);
        if (index == -1 || index == text.length() - separator.length()) {
            return null;
        }
        return text.substring(index + separator.length(), index + separator.length() + length);
    }

    public static String substringAfterIncluding(@Nullable String text, char separator) {
        return substringAfterIncluding(text, CharUtils.toString(separator));
    }

    /**
     * Returns the substring after the first occurrence of a separator, the separator is returned
     *
     * <pre>
     *     StringUtilsWraps.substringAfterIncluding("foo.bar.bar", "bar") = "bar.bar"
     * </pre>
     *
     * @param text the String to get a substring from
     * @param separator the String to search for
     *
     * @return the substring after the first occurrence of a separator, the separator is returned
     *
     * @see org.apache.commons.lang3.StringUtils#substringAfter(String, String)
     */
    @Nullable
    public static String substringAfterIncluding(@Nullable String text, @Nullable String separator) {
        if (StringUtils.isAnyEmpty(text, separator) || !StringUtils.contains(text, separator)) {
            return null;
        }
        return StringUtils.join(separator, StringUtils.substringAfter(text, separator));
    }

    public static String substringAfterLast(@Nullable String text, char separator, int length) {
        return substringAfterLast(text, CharUtils.toString(separator), length);
    }

    public static String substringAfterLastIncluding(@Nullable String text, char separator) {
        return substringAfterLastIncluding(text, CharUtils.toString(separator));
    }

    /**
     * Returns the substring after the last occurrence of a separator, the separator is returned
     *
     * <pre>
     *     StringUtilsWraps.substringAfterIncluding("foo.bar.bar", "bar") = "bar"
     * </pre>
     *
     * @param text the String to get a substring from
     * @param separator the String to search for
     *
     * @return the substring after the last occurrence of a separator, the separator is returned
     *
     * @see org.apache.commons.lang3.StringUtils#substringAfter(String, String)
     */
    @Nullable
    public static String substringAfterLastIncluding(@Nullable String text, @Nullable String separator) {
        if (StringUtils.isAnyEmpty(text, separator) || !StringUtils.contains(text, separator)) {
            return null;
        }
        return StringUtils.join(separator, StringUtils.substringAfterLast(text, separator));
    }

    /**
     * Returns the substring after the last occurrence of a separator, with specified length, the separator is not returned
     *
     * <pre>
     *     StringUtilsWraps.substringAfterLast("foo.bar.bar", ".", 3) = "bar"
     *     StringUtilsWraps.substringAfterLast("foo.bar.foobar", ".", 3) = "foo"
     *     StringUtilsWraps.substringAfterLast("foo.bar.foobar", "*", 3) = null
     * </pre>
     *
     * @param text the String to get a substring from
     * @param separator the String to search for
     * @param length the max length of substring
     *
     * @return the substring after the last occurrence of a separator, with specified length, the separator is not returned
     *
     * @see org.apache.commons.lang3.StringUtils#substringAfterLast(String, String)
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String substringAfterLast(@Nullable String text, @Nullable String separator, int length) {
        if (StringUtils.isAnyEmpty(text, separator) || length <= 0) {
            return null;
        }
        int index = text.lastIndexOf(separator);
        if (index == -1 || index == text.length() - separator.length()) {
            return null;
        }
        return text.substring(index + separator.length(), index + separator.length() + length);
    }

    public static String substringBefore(@Nullable String text, char separator, int length) {
        return substringBefore(text, CharUtils.toString(separator), length);
    }

    /**
     * Returns the substring before the first occurrence of a separator, with specified length, the separator is not returned
     *
     * <pre>
     *     StringUtilsWraps.substringBefore("foo.foobar.bar", ".", 3) = "foo"
     *     StringUtilsWraps.substringBefore("foobar.foo.bar", ".", 3) = "bar"
     *     StringUtilsWraps.substringBefore("foo.foobar.bar", "*", 3) = null
     * </pre>
     *
     * @param text the String to get a substring from
     * @param separator the String to search for
     * @param length the max length of substring
     *
     * @return the substring before the first occurrence of a separator, with specified length, the separator is not returned
     *
     * @see org.apache.commons.lang3.StringUtils#substringBefore(String, String)
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String substringBefore(@Nullable String text, @Nullable String separator, int length) {
        if (StringUtils.isAnyEmpty(text, separator) || length <= 0) {
            return null;
        }
        int index = text.indexOf(separator);
        if (index == -1) {
            return null;
        }
        return text.substring(index - length, index);
    }

    public static String substringBeforeIncluding(@Nullable String text, char separator) {
        return substringBeforeIncluding(text, CharUtils.toString(separator));
    }

    /**
     * Returns the substring before the first occurrence of a separator, the separator is returned
     *
     * <pre>
     *     StringUtilsWraps.substringBeforeIncluding("foo.bar.bar", "bar") = "foo.bar"
     * </pre>
     *
     * @param text the String to get a substring from
     * @param separator the String to search for
     *
     * @return the substring before the first occurrence of a separator, the separator is returned
     *
     * @see org.apache.commons.lang3.StringUtils#substringBefore(String, String)
     */
    @Nullable
    public static String substringBeforeIncluding(@Nullable String text, @Nullable String separator) {
        if (StringUtils.isAnyEmpty(text, separator) || !StringUtils.contains(text, separator)) {
            return null;
        }
        return StringUtils.join(StringUtils.substringBefore(text, separator), separator);
    }

    public static String substringBeforeLast(@Nullable String text, char separator) {
        return StringUtils.substringBeforeLast(text, CharUtils.toString(separator));
    }

    public static String substringBeforeLast(@Nullable String text, char separator, int length) {
        return substringBeforeLast(text, CharUtils.toString(separator), length);
    }

    /**
     * Returns the substring before the last occurrence of a separator, with specified length, the separator is not returned
     *
     * <pre>
     *     StringUtilsWraps.substringBeforeLast("foo.bar", ".", 3) = "foo"
     *     StringUtilsWraps.substringBeforeLast("foobar.bar", ".", 3) = "bar"
     *     StringUtilsWraps.substringBeforeLast("foobar.bar", "*", 3) = null
     * </pre>
     *
     * @param text the String to get a substring from
     * @param separator the String to search for
     * @param length the max length of substring
     *
     * @return the substring before the last occurrence of a separator, with specified length, the separator is not returned
     *
     * @see org.apache.commons.lang3.StringUtils#substringBeforeLast(String, String)
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String substringBeforeLast(@Nullable String text, @Nullable String separator, int length) {
        if (StringUtils.isAnyEmpty(text, separator) || length <= 0) {
            return null;
        }
        int index = text.lastIndexOf(separator);
        if (index == -1) {
            return null;
        }
        return text.substring(index - length, index);
    }

    public static String substringBeforeLastIncluding(@Nullable String text, char separator) {
        return substringBeforeLastIncluding(text, CharUtils.toString(separator));
    }

    /**
     * Returns the substring before the last occurrence of a separator, the separator is returned
     *
     * <pre>
     *     StringUtilsWraps.substringBeforeIncluding("foo.bar.bar", "bar") = "foo.bar.bar"
     * </pre>
     *
     * @param text the String to get a substring from
     * @param separator the String to search for
     *
     * @return the substring before the last occurrence of a separator, the separator is returned
     *
     * @see org.apache.commons.lang3.StringUtils#substringBefore(String, String)
     */
    @Nullable
    public static String substringBeforeLastIncluding(@Nullable String text, @Nullable String separator) {
        if (StringUtils.isAnyEmpty(text, separator) || !StringUtils.contains(text, separator)) {
            return null;
        }
        return StringUtils.join(StringUtils.substringBeforeLast(text, separator), separator);
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#substringBetween(java.lang.String, java.lang.String)
     */
    @Nullable
    public static String substringBetween(@Nullable String text, char separator) {
        return StringUtils.isEmpty(text) ? null : StringUtils.substringBetween(text, CharUtils.toString(separator));
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#substringBetween(java.lang.String, java.lang.String, java.lang.String)
     */
    @Nullable
    public static String substringBetween(@Nullable String text, char open, char close) {
        return StringUtils.isEmpty(text) ? null : StringUtils.substringBetween(text, CharUtils.toString(open), CharUtils.toString(close));
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#substringsBetween(java.lang.String, java.lang.String, java.lang.String)
     */
    @Nullable
    public static String[] substringsBetween(@Nullable String text, char open, char close) {
        return StringUtils.isEmpty(text) ? null : StringUtils.substringsBetween(text, CharUtils.toString(open), CharUtils.toString(close));
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#toRootLowerCase
     */
    @Nullable
    public static String toLowerCase(@Nullable CharSequence sequence) {
        return (sequence == null) ? null : sequence.toString().toLowerCase();
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#toRootUpperCase
     */
    @Nullable
    public static String toUpperCase(@Nullable CharSequence sequence) {
        return (sequence == null) ? null : sequence.toString().toUpperCase();
    }

    public static CharSequence[] trimCharSequence(@Nullable CharSequence... sequences) {
        return trimCharSequence(false, sequences);
    }

    public static CharSequence[] trimCharSequence(@Nullable Collection<? extends CharSequence> sequences) {
        return trimCharSequence(false, sequences);
    }

    public static CharSequence[] trimCharSequence(boolean emptyAsNull, @Nullable CharSequence... sequences) {
        return trimCharSequence(emptyAsNull, ArrayUtilsWraps.asList(sequences));
    }

    @Nullable
    public static CharSequence[] trimCharSequence(boolean emptyAsNull, @Nullable Collection<? extends CharSequence> sequences) {
        if (CollectionPlainWraps.isEmpty(sequences)) {
            return emptyAsNull ? null : CollectionPlainWraps.toElementArray(sequences, CharSequence.class);
        }
        return sequences.stream().map(CharSequenceWraps::toStringIgnoreNull).map(element -> emptyAsNull ? StringUtils.trimToNull(element) : StringUtils.trim(element)).toArray(CharSequence[]::new);
    }

    public static String[] trimStringArray(@Nullable String... texts) {
        return trimStringArray(false, texts);
    }

    public static String[] trimStringArray(@Nullable Collection<String> texts) {
        return trimStringArray(false, texts);
    }

    public static String[] trimStringArray(boolean emptyAsNull, @Nullable String... texts) {
        return trimStringArray(emptyAsNull, ArrayUtilsWraps.asList(texts));
    }

    /**
     * @see "org.springframework.util.StringUtils#trimArrayElements"
     */
    @Nullable
    public static String[] trimStringArray(boolean emptyAsNull, @Nullable Collection<String> texts) {
        if (CollectionPlainWraps.isEmpty(texts)) {
            return emptyAsNull ? null : CollectionPlainWraps.toElementArray(texts, String.class);
        }
        return texts.stream().map(element -> emptyAsNull ? StringUtils.trimToNull(element) : StringUtils.trim(element)).toArray(String[]::new);
    }

    public static void trimStringCollection(@Nullable Collection<String> texts) {
        trimStringCollection(false, texts);
    }

    public static void trimStringCollection(boolean emptyAsNull, @Nullable Collection<String> texts) {
        if (CollectionPlainWraps.isEmpty(texts)) {
            return;
        }
        String[] elements = texts.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        texts.clear();
        Arrays.stream(elements).map(element -> emptyAsNull ? StringUtils.trimToNull(element) : StringUtils.trim(element)).forEach(texts::add);
    }

    public static String unquote(@Nullable CharSequence sequence) {
        return RegExUtils.replaceAll(CharSequenceWraps.toStringIgnoreNull(sequence), "^['\"`]|['\"`]$", StringUtils.EMPTY);    // $NON-NLS-1$
    }

    public static String unwrapWith(@Nullable CharSequence sequence, char delimiter) {
        return removeEnd(removeStart(sequence, delimiter), delimiter);
    }

    public static String unwrapWith(@Nullable CharSequence sequence, @Nullable CharSequence delimiter) {
        return removeEnd(removeStart(sequence, delimiter), delimiter);
    }

    public static String unwrapWith(@Nullable CharSequence sequence, char... delimiters) {
        return removeEnd(removeStart(sequence, delimiters), delimiters);
    }

    public static String unwrapWith(@Nullable CharSequence sequence, @Nullable CharSequence... delimiters) {
        return removeEnd(removeStart(sequence, delimiters), delimiters);
    }

    public static void unwrapWith(@Nullable Collection<String> collection, char... delimiters) {
        removeStart(collection, delimiters);
        removeEnd(collection, delimiters);
    }

    public static void unwrapWith(@Nullable Collection<String> collection, @Nullable CharSequence... delimiters) {
        removeStart(collection, delimiters);
        removeEnd(collection, delimiters);
    }

    public static String unwrapWithIgnoreCase(@Nullable CharSequence sequence, char... delimiters) {
        return removeStartIgnoreCase(removeEndIgnoreCase(sequence, delimiters), delimiters);
    }

    public static String unwrapWithIgnoreCase(@Nullable CharSequence sequence, @Nullable CharSequence... delimiters) {
        return removeStartIgnoreCase(removeEndIgnoreCase(sequence, delimiters), delimiters);
    }

    public static void unwrapWithIgnoreCase(@Nullable Collection<String> collection, char... delimiters) {
        removeStartIgnoreCase(collection, delimiters);
        removeEndIgnoreCase(collection, delimiters);
    }

    public static void unwrapWithIgnoreCase(@Nullable Collection<String> collection, @Nullable CharSequence... delimiters) {
        removeStartIgnoreCase(collection, delimiters);
        removeEndIgnoreCase(collection, delimiters);
    }

    public static String unwrapWithParentheses(@Nullable CharSequence sequence) {
        return RegExUtils.replaceAll(CharSequenceWraps.toStringIgnoreNull(sequence), "^[\\(]|[\\)]$", StringUtils.EMPTY);    // $NON-NLS-1$
    }

    public static String unwrapWithSquareBrackets(@Nullable CharSequence sequence) {
        return RegExUtils.replaceAll(CharSequenceWraps.toStringIgnoreNull(sequence), "^[\\[]|[\\]]$", StringUtils.EMPTY);    // $NON-NLS-1$
    }

    public static String unwrapWithCurlyBrackets(@Nullable CharSequence sequence) {
        return RegExUtils.replaceAll(CharSequenceWraps.toStringIgnoreNull(sequence), "^[\\{]|[\\}]$", StringUtils.EMPTY);    // $NON-NLS-1$
    }

    public static String wrapWith(@Nullable CharSequence sequence, char delimiter) {
        return wrapWith(sequence, delimiter, false);
    }

    public static String wrapWith(@Nullable CharSequence sequence, char delimiter, boolean emptyAsNull) {
        return wrapWith(sequence, CharUtils.toString(delimiter), emptyAsNull);
    }

    public static String wrapWith(@Nullable CharSequence sequence, CharSequence delimiter) {
        return wrapWith(sequence, delimiter, false);
    }

    @Nullable
    public static String wrapWith(@Nullable CharSequence sequence, CharSequence delimiter, boolean emptyAsNull) {
        return (emptyAsNull && StringUtils.isEmpty(sequence)) ? null : StringUtils.join(delimiter, sequence, delimiter);
    }

    public static String wrapWithParentheses(@Nullable CharSequence sequence) {
        return wrapWithParentheses(sequence, false);
    }

    /**
     * Returns a string that wraps the {@code sequence} with parentheses ("()")
     *
     * @param sequence the source to be wrapped
     * @param emptyAsNull when {@code sequence} is empty, true means this will return {@code null}, otherwise this will return an empty parentheses
     *
     * @return a string that wraps the {@code sequence} with parentheses ("()")
     */
    @Nullable
    public static String wrapWithParentheses(@Nullable CharSequence sequence, boolean emptyAsNull) {
        return (emptyAsNull && StringUtils.isEmpty(sequence)) ? null : StringUtils.join(CharVariantConst.PARENTHESIS_LEFT, sequence, CharVariantConst.PARENTHESIS_RIGHT);
    }

    public static String wrapWithSquareBrackets(@Nullable CharSequence sequence) {
        return wrapWithSquareBrackets(sequence, false);
    }

    /**
     * Returns a string that wraps the {@code sequence} with square brackets ("[]")
     *
     * @param sequence the source to be wrapped
     * @param emptyAsNull when {@code sequence} is empty, true means this will return {@code null}, otherwise this will return an empty parentheses
     *
     * @return a string that wraps the {@code sequence} with square brackets ("[]")
     */
    @Nullable
    public static String wrapWithSquareBrackets(@Nullable CharSequence sequence, boolean emptyAsNull) {
        return (emptyAsNull && StringUtils.isEmpty(sequence)) ? null : StringUtils.join(CharVariantConst.SQUARE_BRACKET_LEFT, sequence, CharVariantConst.SQUARE_BRACKET_RIGHT);
    }

    public static String wrapWithCurlyBrackets(@Nullable CharSequence sequence) {
        return wrapWithCurlyBrackets(sequence, false);
    }

    /**
     * Returns a string that wraps the {@code sequence} with curly brackets ("{}")
     *
     * @param sequence the source to be wrapped
     * @param emptyAsNull when {@code sequence} is empty, true means this will return {@code null}, otherwise this will return an empty parentheses
     *
     * @return a string that wraps the {@code sequence} with curly brackets ("{}")
     */
    @Nullable
    public static String wrapWithCurlyBrackets(@Nullable CharSequence sequence, boolean emptyAsNull) {
        return (emptyAsNull && StringUtils.isEmpty(sequence)) ? null : StringUtils.join(CharVariantConst.CURLY_BRACKET_LEFT, sequence, CharVariantConst.CURLY_BRACKET_RIGHT);
    }

    public static boolean isWrappedWithParentheses(@Nullable CharSequence sequence) {
        return startsWith(sequence, CharVariantConst.PARENTHESIS_LEFT) && endsWith(sequence, CharVariantConst.PARENTHESIS_RIGHT);
    }

    public static boolean isWrappedWithSquareBrackets(@Nullable CharSequence sequence) {
        return startsWith(sequence, CharVariantConst.SQUARE_BRACKET_LEFT) && endsWith(sequence, CharVariantConst.SQUARE_BRACKET_RIGHT);
    }

    public static boolean isWrappedWithCurlyBrackets(@Nullable CharSequence sequence) {
        return startsWith(sequence, CharVariantConst.CURLY_BRACKET_LEFT) && endsWith(sequence, CharVariantConst.CURLY_BRACKET_RIGHT);
    }
}
