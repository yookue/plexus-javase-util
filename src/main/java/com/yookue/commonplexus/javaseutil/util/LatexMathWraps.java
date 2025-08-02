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


import java.util.LinkedHashMap;
import java.util.Map;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import net.objecthunter.exp4j.ExpressionBuilder;


/**
 * Utilities for LaTeX math
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class LatexMathWraps {
    private static final Map<String, String> latexMaths = Map.of(
        "\\cdot", "*",    // $NON-NLS-1$ // $NON-NLS-2$
        "\\times", "*",    // $NON-NLS-1$ // $NON-NLS-2$
        "\\div", "/",    // $NON-NLS-1$ // $NON-NLS-2$
        "\\sqrt", "Sqrt",    // $NON-NLS-1$ // $NON-NLS-2$
        "\\pi", "Pi",    // $NON-NLS-1$ // $NON-NLS-2$
        "\\infty", "Infinity",    // $NON-NLS-1$ // $NON-NLS-2$
        "\\int", "Integrate",    // $NON-NLS-1$ // $NON-NLS-2$
        "\\sum", "Sum"    // $NON-NLS-1$ // $NON-NLS-2$
    );

    @Nullable
    public static String parseLatexToMath(@Nullable String latex) {
        return parseLatexToMath(latex, null);
    }

    @Nullable
    public static String parseLatexToMath(@Nullable String latex, @Nullable Map<String, String> keywords) {
        if (StringUtils.isBlank(latex)) {
            return null;
        }
        String result = latex;
        if (MapPlainWraps.isEmpty(keywords)) {
            for (Map.Entry<String, String> entry : latexMaths.entrySet()) {
                result = result.replace(entry.getKey(), entry.getValue());
            }
        } else {
            Map<String, String> alias = new LinkedHashMap<>(latexMaths);
            MapPlainWraps.putAllIfKeyNotBlank(alias, keywords);
            for (Map.Entry<String, String> entry : alias.entrySet()) {
                result = result.replace(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    @Nullable
    public static Double evalLatexExpress(@Nullable String latex) throws IllegalArgumentException {
        return evalLatexExpress(latex, null, null);
    }

    @Nullable
    public static Double evalLatexExpress(@Nullable String latex, @Nullable Map<String, String> variables) throws IllegalArgumentException {
        return evalLatexExpress(latex, variables, null);
    }

    @Nullable
    public static Double evalLatexExpress(@Nullable String latex, @Nullable Map<String, String> variables, @Nullable Map<String, String> keywords) throws IllegalArgumentException {
        if (StringUtils.isBlank(latex)) {
            return null;
        }
        String math = parseLatexToMath(latex, keywords);
        return evalMathExpress(math, variables);
    }

    @Nullable
    public static Double evalLatexExpressQuietly(@Nullable String latex) {
        return evalLatexExpressQuietly(latex, null, null);
    }

    @Nullable
    public static Double evalLatexExpressQuietly(@Nullable String latex, @Nullable Map<String, String> variables) {
        return evalLatexExpressQuietly(latex, variables, null);
    }

    @Nullable
    public static Double evalLatexExpressQuietly(@Nullable String latex, @Nullable Map<String, String> variables, @Nullable Map<String, String> keywords) {
        try {
            return evalLatexExpress(latex, variables, keywords);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Double evalMathExpress(@Nullable String math) throws IllegalArgumentException {
        return evalMathExpress(math, null);
    }

    @Nullable
    public static Double evalMathExpress(@Nullable String math, @Nullable Map<String, String> variables) throws IllegalArgumentException {
        if (StringUtils.isBlank(math)) {
            return null;
        }
        String alias = math;
        if (MapPlainWraps.isNotEmpty(variables)) {
            for (Map.Entry<String, String> entry : variables.entrySet()) {
                if (StringUtils.isBlank(entry.getKey())) {
                    continue;
                }
                alias = alias.replace(entry.getKey(), StringUtils.defaultString(entry.getValue()));
            }
        }
        return new ExpressionBuilder(alias).build().evaluate();
    }

    @Nullable
    public static Double evalMathExpressQuietly(@Nullable String math) {
        return evalMathExpressQuietly(math, null);
    }

    @Nullable
    public static Double evalMathExpressQuietly(@Nullable String math, @Nullable Map<String, String> variables) {
        try {
            return evalMathExpress(math, variables);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static boolean testLatexExpress(@Nullable String latex) {
        return testLatexExpress(latex, null, null);
    }

    public static boolean testLatexExpress(@Nullable String latex, @Nullable Map<String, String> variables) {
        return testLatexExpress(latex, variables, null);
    }

    public static boolean testLatexExpress(@Nullable String latex, @Nullable Map<String, String> variables, @Nullable Map<String, String> keywords) {
        if (StringUtils.isBlank(latex)) {
            return false;
        }
        String math = parseLatexToMath(latex, keywords);
        return testMathExpress(math, variables);
    }

    public static boolean testMathExpress(@Nullable String math) {
        return testMathExpress(math, null);
    }

    public static boolean testMathExpress(@Nullable String math, @Nullable Map<String, String> variables) {
        try {
            return evalMathExpress(math, variables) != null;
        } catch (Exception ignored) {
        }
        return false;
    }
}
