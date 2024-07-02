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
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link java.lang.Package}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class PackagePlainWraps {
    public static boolean existsPackage(@Nullable CharSequence name) {
        return StringUtils.isNotBlank(name) && Package.getPackage(CharSequenceWraps.toStringIgnoreNull(name)) != null;
    }

    public static boolean existsAllPackages(@Nullable CharSequence... packages) {
        return existsAllPackages(ArrayUtilsWraps.asList(packages));
    }

    public static boolean existsAllPackages(@Nullable Collection<? extends CharSequence> packages) {
        return CollectionPlainWraps.isNotEmpty(packages) && packages.stream().allMatch(PackagePlainWraps::existsPackage);
    }

    public static boolean existsAnyPackages(@Nullable CharSequence... packages) {
        return existsAnyPackages(ArrayUtilsWraps.asList(packages));
    }

    public static boolean existsAnyPackages(@Nullable Collection<? extends CharSequence> packages) {
        return CollectionPlainWraps.isNotEmpty(packages) && packages.stream().anyMatch(PackagePlainWraps::existsPackage);
    }
}
