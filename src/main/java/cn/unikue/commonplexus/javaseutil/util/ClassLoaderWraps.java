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
import java.util.Vector;
import jakarta.annotation.Nullable;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassLoaderUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.ClassLoaderUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.ClassLoaderUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ClassLoaderWraps {
    /**
     * Returns the names of the loaded libraries within the classloader
     *
     * @param loader The source classloader
     * @param extension Whether include the extension or not
     *
     * @return the names of the loaded libraries within the classloader
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String[] getLoadedLibraryNames(@Nullable ClassLoader loader, boolean extension) {
        String[] pathnames = getLoadedLibraryPathnames(loader);
        if (ArrayUtils.isEmpty(pathnames)) {
            return null;
        }
        return Arrays.stream(pathnames).map(item -> extension ? FilenameUtils.getName(item) : FilenameUtils.getBaseName(item)).toArray(String[]::new);
    }

    /**
     * Returns the paths of the loaded libraries within the classloader
     *
     * @param loader The source classloader
     * @param separator Whether include the end separator or not
     *
     * @return the paths of the loaded libraries within the classloader
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String[] getLoadedLibraryPaths(@Nullable ClassLoader loader, boolean separator) {
        String[] pathnames = getLoadedLibraryPathnames(loader);
        if (ArrayUtils.isEmpty(pathnames)) {
            return null;
        }
        return Arrays.stream(pathnames).map(item -> separator ? FilenameUtils.getPath(item) : FilenameUtils.getPathNoEndSeparator(item)).distinct().toArray(String[]::new);
    }

    /**
     * Returns the pathnames of the loaded libraries within the classloader
     *
     * @param loader The source classloader
     *
     * @return the pathnames of the loaded libraries within the classloader
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static String[] getLoadedLibraryPathnames(@Nullable ClassLoader loader) {
        if (loader == null) {
            return null;
        }
        Vector<String> result = (Vector<String>) FieldUtilsWraps.readStaticField(loader.getClass(), "loadedLibraryNames", true);    // $NON-NLS-1$
        return CollectionPlainWraps.isEmpty(result) ? null : result.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Get the context ClassLoader for the current thread.
     *
     * @return the context ClassLoader for the current thread, or null if not set
     */
    @Nullable
    public static ClassLoader getTreadContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * Convert ClassLoader to String representation.
     *
     * @param loader the ClassLoader to convert
     * @return the String representation, or null if loader is null
     */
    @Nullable
    public static String toString(@Nullable ClassLoader loader) {
        return (loader == null) ? null : ClassLoaderUtils.toString(loader);
    }
}
