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

package com.yookue.commonplexus.javaseutil.constant;


import com.yookue.commonplexus.javaseutil.util.ArrayUtilsWraps;


/**
 * Combinations of @Nullable annotations
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public abstract class NullableAnnotationCombo {
    /**
     * @see "%IDEA%/plugins/java/lib/java-impl.jar!com.intellij.codeInsight.annoPackages.AndroidAnnotationSupport"
     */
    public static final String[] ANDROID = new String[]{"android.support.annotation.Nullable", "androidx.annotation.Nullable", "androidx.annotation.RecentlyNullable", "com.android.annotations.Nullable"};    // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$ // $NON-NLS-4$

    /**
     * @see "%IDEA%/plugins/java/lib/java-impl.jar!com.intellij.codeInsight.annoPackages.CheckerFrameworkSupport"
     */
    public static final String[] CHECKER_FRAMEWORK = new String[]{"org.checkerframework.checker.nullness.qual.Nullable", "org.checkerframework.checker.nullness.compatqual.NullableDecl", "org.checkerframework.checker.nullness.compatqual.NullableType"};    // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$

    /**
     * @see "%IDEA%/plugins/java/lib/java-impl.jar!com.intellij.codeInsight.annoPackages.FindBugsAnnotationSupport"
     */
    public static final String[] FIND_BUGS = new String[]{"edu.umd.cs.findbugs.annotations.Nullable"};    // $NON-NLS-1$

    /**
     * @see "%IDEA%/plugins/java/lib/java-impl.jar!com.intellij.codeInsight.annoPackages.JSpecifyAnnotationSupport"
     */
    public static final String[] J_SPECIFY = new String[]{"org.jspecify.nullness.Nullable"};    // $NON-NLS-1$

    /**
     * @see "%IDEA%/plugins/java/lib/java-impl.jar!com.intellij.codeInsight.annoPackages.JetBrainsAnnotationSupport"
     */
    public static final String[] JET_BRAINS = new String[]{"org.jetbrains.annotations.Nullable"};    // $NON-NLS-1$

    /**
     * @see "%IDEA%/plugins/java/lib/java-impl.jar!com.intellij.codeInsight.annoPackages.Jsr305Support"
     */
    public static final String[] JSR305 = new String[]{"javax.annotation.Nullable", "javax.annotation.CheckForNull"};    // $NON-NLS-1$ // $NON-NLS-2$

    /**
     * Union of all annotations
     */
    public static final String[] ALL = ArrayUtilsWraps.unionAll(ANDROID, CHECKER_FRAMEWORK, FIND_BUGS, J_SPECIFY, JET_BRAINS, JSR305);
}
