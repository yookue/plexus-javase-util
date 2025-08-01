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


/**
 * Utilities for parsing LaTeX
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class LatexParserWraps {
    private static final Map<String, String> LATEX_MATH_MAP = Map.of(
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
    public static String parseToMath(@Nullable String latex) {
        return parseToMath(latex, null);
    }

    @Nullable
    public static String parseToMath(@Nullable String latex, @Nullable Map<String, String> keywords) {
        if (StringUtils.isBlank(latex)) {
            return null;
        }
        String result = latex;
        if (MapPlainWraps.isEmpty(keywords)) {
            for (Map.Entry<String, String> entry : LATEX_MATH_MAP.entrySet()) {
                result = result.replace(entry.getKey(), entry.getValue());
            }
        } else {
            Map<String, String> alias = new LinkedHashMap<>(LATEX_MATH_MAP);
            MapPlainWraps.putAllIfKeyNotBlank(alias, keywords);
            for (Map.Entry<String, String> entry : alias.entrySet()) {
                result = result.replace(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }
}
