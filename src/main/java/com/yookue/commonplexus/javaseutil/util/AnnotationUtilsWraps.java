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


import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.AnnotationUtils}
 *
 * @author David Hsing
 * @see java.lang.reflect.AnnotatedElement
 * @see org.apache.commons.lang3.AnnotationUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class AnnotationUtilsWraps {
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean isPresent(@Nullable AnnotatedElement element, @Nullable Class<? extends Annotation> annotation) {
        return ObjectUtils.allNotNull(element, annotation) && element.isAnnotationPresent(annotation);
    }

    public static boolean isAbsent(@Nullable AnnotatedElement element, @Nullable Class<? extends Annotation> annotation) {
        return !isPresent(element, annotation);
    }

    @SafeVarargs
    public static boolean allPresent(@Nullable AnnotatedElement element, @Nullable Class<? extends Annotation>... annotations) {
        return allPresent(element, ArrayUtilsWraps.asList(annotations));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allPresent(@Nullable AnnotatedElement element, @Nullable Collection<Class<? extends Annotation>> annotations) {
        return ObjectUtils.allNotNull(element, annotations) && CollectionPlainWraps.isNotEmpty(annotations) && annotations.stream().allMatch(annotation -> isPresent(element, annotation));
    }

    @SafeVarargs
    public static boolean allAbsent(@Nullable AnnotatedElement element, @Nullable Class<? extends Annotation>... annotations) {
        return allAbsent(element, ArrayUtilsWraps.asList(annotations));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allAbsent(@Nullable AnnotatedElement element, @Nullable Collection<Class<? extends Annotation>> annotations) {
        return ObjectUtils.anyNull(element, annotations) || CollectionPlainWraps.isEmpty(annotations) || annotations.stream().allMatch(annotation -> isAbsent(element, annotation));
    }

    @SafeVarargs
    public static boolean anyPresent(@Nullable AnnotatedElement element, @Nullable Class<? extends Annotation>... annotations) {
        return anyPresent(element, ArrayUtilsWraps.asList(annotations));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyPresent(@Nullable AnnotatedElement element, @Nullable Collection<Class<? extends Annotation>> annotations) {
        return ObjectUtils.allNotNull(element, annotations) && CollectionPlainWraps.isNotEmpty(annotations) && annotations.stream().anyMatch(annotation -> isPresent(element, annotation));
    }

    @SafeVarargs
    public static boolean anyAbsent(@Nullable AnnotatedElement element, @Nullable Class<? extends Annotation>... annotations) {
        return anyAbsent(element, ArrayUtilsWraps.asList(annotations));
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyAbsent(@Nullable AnnotatedElement element, @Nullable Collection<Class<? extends Annotation>> annotations) {
        return ObjectUtils.anyNull(element, annotations) || CollectionPlainWraps.isEmpty(annotations) || annotations.stream().anyMatch(annotation -> isAbsent(element, annotation));
    }
}
