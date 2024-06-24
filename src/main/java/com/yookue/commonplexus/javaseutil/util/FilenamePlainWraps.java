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
import javax.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;


/**
 * Utilities for file names
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class FilenamePlainWraps {
    public static String appendSlash(@Nullable CharSequence sequence) {
        return StringUtilsWraps.appendIfMissing(sequence, CharVariantConst.SLASH);
    }

    public static void appendSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::appendSlash).forEach(collection::add);
    }

    public static void appendSlashIgnoreEmpty(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.isEmpty(element) ? element : FilenamePlainWraps.appendSlash(element)).forEach(collection::add);
    }

    public static void appendSlashIgnoreBlank(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.isBlank(element) ? element : FilenamePlainWraps.appendSlash(element)).forEach(collection::add);
    }

    public static String appendBackSlash(@Nullable CharSequence sequence) {
        return StringUtilsWraps.appendIfMissing(sequence, CharVariantConst.BACKSLASH);
    }

    public static void appendBackSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::appendBackSlash).forEach(collection::add);
    }

    public static void appendBackSlashIgnoreEmpty(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.isEmpty(element) ? element : FilenamePlainWraps.appendBackSlash(element)).forEach(collection::add);
    }

    public static void appendBackSlashIgnoreBlank(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.isBlank(element) ? element : FilenamePlainWraps.appendBackSlash(element)).forEach(collection::add);
    }

    public static String prependSlash(@Nullable CharSequence sequence) {
        return StringUtilsWraps.prependIfMissing(sequence, CharVariantConst.SLASH);
    }

    public static void prependSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::prependSlash).forEach(collection::add);
    }

    public static void prependSlashIgnoreEmpty(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.isEmpty(element) ? element : FilenamePlainWraps.prependSlash(element)).forEach(collection::add);
    }

    public static void prependSlashIgnoreBlank(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.isBlank(element) ? element : FilenamePlainWraps.prependSlash(element)).forEach(collection::add);
    }

    public static String prependBackSlash(@Nullable CharSequence sequence) {
        return StringUtilsWraps.prependIfMissing(sequence, CharVariantConst.BACKSLASH);
    }

    public static void prependBackSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::prependBackSlash).forEach(collection::add);
    }

    public static void prependBackSlashIgnoreEmpty(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.isEmpty(element) ? element : FilenamePlainWraps.prependBackSlash(element)).forEach(collection::add);
    }

    public static void prependBackSlashIgnoreBlank(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(element -> StringUtils.isBlank(element) ? element : FilenamePlainWraps.prependBackSlash(element)).forEach(collection::add);
    }

    public static String removeStartSlash(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "^/+");    // $NON-NLS-1$
    }

    public static void removeStartSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeStartSlash).forEach(collection::add);
    }

    public static String removeStartBackSlash(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "^\\\\+");    // $NON-NLS-1$
    }

    public static void removeStartBackSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeStartBackSlash).forEach(collection::add);
    }

    /**
     * Returns a string that removed starting slash (/) and backslash (\\)
     *
     * @param sequence the sequence to be processed
     *
     * @return a string that removed starting slash (/) and backslash (\\)
     */
    public static String removeStartSlashes(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "^[/\\\\]+");    // $NON-NLS-1$
    }

    public static void removeStartSlashes(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeStartSlashes).forEach(collection::add);
    }

    public static String removeEndSlash(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "/+$");    // $NON-NLS-1$
    }

    public static void removeEndSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeEndSlash).forEach(collection::add);
    }

    public static String removeEndBackSlash(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "\\\\+$");    // $NON-NLS-1$
    }

    public static void removeEndBackSlash(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeEndBackSlash).forEach(collection::add);
    }

    /**
     * Returns a string that removed ending slash (/) and backslash (\\)
     *
     * @param sequence the sequence to be processed
     *
     * @return a string that removed ending slash (/) and backslash (\\)
     */
    public static String removeEndSlashes(@Nullable CharSequence sequence) {
        return RegExUtils.removeAll(CharSequenceWraps.toStringIgnoreNull(sequence), "[/\\\\]+$");    // $NON-NLS-1$
    }

    public static void removeEndSlashes(@Nullable Collection<String> collection) {
        if (CollectionPlainWraps.isEmpty(collection)) {
            return;
        }
        String[] array = collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        collection.clear();
        Arrays.stream(array).map(FilenamePlainWraps::removeEndSlashes).forEach(collection::add);
    }
}
