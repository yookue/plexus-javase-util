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
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import cn.unikue.commonplexus.javaseutil.constant.CharVariantConst;


/**
 * Utilities for file names
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class FilenamePlainWraps {
    /**
     * Appends a slash (/) to the sequence if it doesn't already end with one
     *
     * @param sequence the sequence to process
     *
     * @return the sequence with a trailing slash, or null if the input is null
     */
    public static String appendSlash(@Nullable CharSequence sequence) {
        return StringUtilsWraps.appendIfMissing(sequence, CharVariantConst.SLASH);
    }

    /**
     * Appends a slash (/) to each string in the collection if it doesn't already end with one
     *
     * @param collection the collection of strings to process
     */
    public static void appendSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::appendSlash).forEach(collection::add);
    }

    /**
     * Appends a slash (/) to each non-empty string in the collection if it doesn't already end with one
     *
     * @param collection the collection of strings to process
     */
    public static void appendSlashIgnoreEmpty(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(item -> StringUtils.isEmpty(item) ? item : FilenamePlainWraps.appendSlash(item)).forEach(collection::add);
    }

    /**
     * Appends a slash (/) to each non-blank string in the collection if it doesn't already end with one
     *
     * @param collection the collection of strings to process
     */
    public static void appendSlashIgnoreBlank(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(item -> StringUtils.isBlank(item) ? item : FilenamePlainWraps.appendSlash(item)).forEach(collection::add);
    }

    /**
     * Appends a backslash (\\) to the sequence if it doesn't already end with one
     *
     * @param sequence the sequence to process
     *
     * @return the sequence with a trailing backslash, or null if the input is null
     */
    public static String appendBackslash(@Nullable CharSequence sequence) {
        return StringUtilsWraps.appendIfMissing(sequence, CharVariantConst.BACKSLASH);
    }

    /**
     * Appends a backslash (\\) to each string in the collection if it doesn't already end with one
     *
     * @param collection the collection of strings to process
     */
    public static void appendBackslash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::appendBackslash).forEach(collection::add);
    }

    /**
     * Appends a backslash (\\) to each non-empty string in the collection if it doesn't already end with one
     *
     * @param collection the collection of strings to process
     */
    public static void appendBackslashIgnoreEmpty(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(item -> StringUtils.isEmpty(item) ? item : FilenamePlainWraps.appendBackslash(item)).forEach(collection::add);
    }

    /**
     * Appends a backslash (\\) to each non-blank string in the collection if it doesn't already end with one
     *
     * @param collection the collection of strings to process
     */
    public static void appendBackslashIgnoreBlank(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(item -> StringUtils.isBlank(item) ? item : FilenamePlainWraps.appendBackslash(item)).forEach(collection::add);
    }

    /**
     * Prepends a slash (/) to the sequence if it doesn't already start with one
     *
     * @param sequence the sequence to process
     *
     * @return the sequence with a leading slash, or null if the input is null
     */
    public static String prependSlash(@Nullable CharSequence sequence) {
        return StringUtilsWraps.prependIfMissing(sequence, CharVariantConst.SLASH);
    }

    /**
     * Prepends a slash (/) to each string in the collection if it doesn't already start with one
     *
     * @param collection the collection of strings to process
     */
    public static void prependSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::prependSlash).forEach(collection::add);
    }

    /**
     * Prepends a slash (/) to each non-empty string in the collection if it doesn't already start with one
     *
     * @param collection the collection of strings to process
     */
    public static void prependSlashIgnoreEmpty(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(item -> StringUtils.isEmpty(item) ? item : FilenamePlainWraps.prependSlash(item)).forEach(collection::add);
    }

    /**
     * Prepends a slash (/) to each non-blank string in the collection if it doesn't already start with one
     *
     * @param collection the collection of strings to process
     */
    public static void prependSlashIgnoreBlank(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(item -> StringUtils.isBlank(item) ? item : FilenamePlainWraps.prependSlash(item)).forEach(collection::add);
    }

    /**
     * Prepends a backslash (\\) to the sequence if it doesn't already start with one
     *
     * @param sequence the sequence to process
     *
     * @return the sequence with a leading backslash, or null if the input is null
     */
    public static String prependBackslash(@Nullable CharSequence sequence) {
        return StringUtilsWraps.prependIfMissing(sequence, CharVariantConst.BACKSLASH);
    }

    /**
     * Prepends a backslash (\\) to each string in the collection if it doesn't already start with one
     *
     * @param collection the collection of strings to process
     */
    public static void prependBackslash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::prependBackslash).forEach(collection::add);
    }

    /**
     * Prepends a backslash (\\) to each non-empty string in the collection if it doesn't already start with one
     *
     * @param collection the collection of strings to process
     */
    public static void prependBackslashIgnoreEmpty(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(item -> StringUtils.isEmpty(item) ? item : FilenamePlainWraps.prependBackslash(item)).forEach(collection::add);
    }

    /**
     * Prepends a backslash (\\) to each non-blank string in the collection if it doesn't already start with one
     *
     * @param collection the collection of strings to process
     */
    public static void prependBackslashIgnoreBlank(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(item -> StringUtils.isBlank(item) ? item : FilenamePlainWraps.prependBackslash(item)).forEach(collection::add);
    }

    /**
     * Removes all leading slashes (/) from the sequence
     *
     * @param sequence the sequence to process
     *
     * @return the sequence with leading slashes removed, or null if the input is null
     */
    public static String removeStartSlash(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "^/+");    // $NON-NLS-1$
    }

    /**
     * Removes all leading slashes (/) from each string in the collection
     *
     * @param collection the collection of strings to process
     */
    public static void removeStartSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeStartSlash).forEach(collection::add);
    }

    /**
     * Removes all leading backslashes (\\) from the sequence
     *
     * @param sequence the sequence to process
     *
     * @return the sequence with leading backslashes removed, or null if the input is null
     */
    public static String removeStartBackslash(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "^\\\\+");    // $NON-NLS-1$
    }

    /**
     * Removes all leading backslashes (\\) from each string in the collection
     *
     * @param collection the collection of strings to process
     */
    public static void removeStartBackslash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeStartBackslash).forEach(collection::add);
    }

    /**
     * Returns a string that removed starting slash (/) and backslash (\\)
     *
     * @param sequence The sequence to be processed
     *
     * @return a string that removed starting slash (/) and backslash (\\)
     */
    public static String removeStartSlashes(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "^[/\\\\]+");    // $NON-NLS-1$
    }

    /**
     * Removes all leading slashes (/) and backslashes (\\) from each string in the collection
     *
     * @param collection the collection of strings to process
     */
    public static void removeStartSlashes(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeStartSlashes).forEach(collection::add);
    }

    /**
     * Removes all trailing slashes (/) from the sequence
     *
     * @param sequence the sequence to process
     *
     * @return the sequence with trailing slashes removed, or null if the input is null
     */
    public static String removeEndSlash(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "/+$");    // $NON-NLS-1$
    }

    /**
     * Removes all trailing slashes (/) from each string in the collection
     *
     * @param collection the collection of strings to process
     */
    public static void removeEndSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeEndSlash).forEach(collection::add);
    }

    /**
     * Removes all trailing backslashes (\\) from the sequence
     *
     * @param sequence the sequence to process
     *
     * @return the sequence with trailing backslashes removed, or null if the input is null
     */
    public static String removeEndBackslash(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "\\\\+$");    // $NON-NLS-1$
    }

    /**
     * Removes all trailing backslashes (\\) from each string in the collection
     *
     * @param collection the collection of strings to process
     */
    public static void removeEndBackslash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeEndBackslash).forEach(collection::add);
    }

    /**
     * Returns a string that removed ending slash (/) and backslash (\\)
     *
     * @param sequence The sequence to be processed
     *
     * @return a string that removed ending slash (/) and backslash (\\)
     */
    public static String removeEndSlashes(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "[/\\\\]+$");    // $NON-NLS-1$
    }

    /**
     * Removes all trailing slashes (/) and backslashes (\\) from each string in the collection
     *
     * @param collection the collection of strings to process
     */
    public static void removeEndSlashes(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeEndSlashes).forEach(collection::add);
    }

    /**
     * Removes the leading dot (.) from the file extension if present
     *
     * @param extension the file extension to process
     *
     * @return the extension without the leading dot, or null if the input is null
     */
    public static String removeExtensionDot(@Nullable String extension) {
        return RegexUtilsWraps.removeStart(extension, CharVariantConst.DOT);
    }
}
