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


import java.util.Collection;
import javax.annotation.Nullable;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link org.apache.commons.io.FilenameUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.io.FilenameUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class FilenameUtilsWraps {
    public static boolean isExtensionEmpty(@Nullable String fileName) {
        return StringUtils.isEmpty(FilenameUtils.getExtension(fileName));
    }

    public static boolean isExtensionIgnoreCase(@Nullable String fileName, @Nullable String... extensions) {
        return isExtensionIgnoreCase(fileName, ArrayUtilsWraps.asList(extensions));
    }

    /**
     * Returns {@code true} if the file name is one of those case-insensitive extensions
     *
     * @param fileName the file name to query, null returns false
     * @param extensions the extensions to check for, null checks for no extension
     *
     * @return {@code true} if the file name is one of those case-insensitive extensions
     *
     * @see org.apache.commons.io.FilenameUtils#isExtension(String, String...)
     */
    public static boolean isExtensionIgnoreCase(@Nullable String fileName, @Nullable Collection<String> extensions) {
        if (StringUtils.isBlank(fileName)) {
            return false;
        }
        String extension = FilenameUtils.getExtension(fileName);
        return (StringUtils.isEmpty(extension) && CollectionPlainWraps.isEmpty(extensions)) || StringUtilsWraps.equalsAnyIgnoreCase(extension, extensions);
    }
}
