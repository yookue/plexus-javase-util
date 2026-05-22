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


import java.util.Collection;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link java.lang.Package}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class PackagePlainWraps {
    /**
     * Checks whether a package with the given name exists
     *
     * @param name The package name to check
     *
     * @return {@code true} if the package exists, {@code false} otherwise
     */
    public static boolean existsPackage(@Nullable CharSequence name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        ClassLoader loader = ClassLoaderWraps.getTreadContextClassLoader();
        if (loader == null) {
            return false;
        }
        return StringUtils.isNotBlank(name) && loader.getDefinedPackage(CharSequenceWraps.toStringIgnoreNull(name)) != null;
    }

    /**
     * Checks whether all given packages exist
     *
     * @param packages The package names to check
     *
     * @return {@code true} if all packages exist, {@code false} otherwise
     */
    public static boolean existsAllPackages(@Nullable CharSequence... packages) {
        return existsAllPackages(ArrayUtilsWraps.asList(packages));
    }

    /**
     * Checks whether all packages in the collection exist
     *
     * @param packages The collection of package names to check
     *
     * @return {@code true} if all packages exist and collection is not empty, {@code false} otherwise
     */
    public static boolean existsAllPackages(@Nullable Collection<? extends CharSequence> packages) {
        return CollectionPlainWraps.isNotEmpty(packages) && packages.stream().allMatch(PackagePlainWraps::existsPackage);
    }

    /**
     * Checks whether any of the given packages exists
     *
     * @param packages The package names to check
     *
     * @return {@code true} if any package exists, {@code false} otherwise
     */
    public static boolean existsAnyPackages(@Nullable CharSequence... packages) {
        return existsAnyPackages(ArrayUtilsWraps.asList(packages));
    }

    /**
     * Checks whether any package in the collection exists
     *
     * @param packages The collection of package names to check
     *
     * @return {@code true} if any package exists and collection is not empty, {@code false} otherwise
     */
    public static boolean existsAnyPackages(@Nullable Collection<? extends CharSequence> packages) {
        return CollectionPlainWraps.isNotEmpty(packages) && packages.stream().anyMatch(PackagePlainWraps::existsPackage);
    }
}
