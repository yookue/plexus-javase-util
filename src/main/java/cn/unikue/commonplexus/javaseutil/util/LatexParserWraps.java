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


import java.util.LinkedHashMap;
import java.util.Map;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for LaTeX parser
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class LatexParserWraps {
    private static final Map<String, String> latexMaths = new LinkedHashMap<>() {{
        put("\\cdot", "*"); // $NON-NLS-1$ // $NON-NLS-2$
        put("\\times", "*"); // $NON-NLS-1$ // $NON-NLS-2$
        put("\\div", "/"); // $NON-NLS-1$ // $NON-NLS-2$
        put("\\pm", "+-"); // $NON-NLS-1$ // $NON-NLS-2$
        put("\\approx", "~"); // $NON-NLS-1$ // $NON-NLS-2$
        put("\\neq", "!="); // $NON-NLS-1$ // $NON-NLS-2$
        put("\\sqrt", "Sqrt");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\pi", "Pi");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\infty", "Infinity");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\int", "Integrate");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\sum", "Sum");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\lfloor", "floor(");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\rfloor", ")");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\lceil", "ceil(");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\rceil", ")");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\lvert", "abs(");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\rvert", ")");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\left(", "(");    // $NON-NLS-1$ // $NON-NLS-2$
        put("\\right)", ")");    // $NON-NLS-1$ // $NON-NLS-2$
    }};

    /**
     * Parses LaTeX math expression to plain math notation
     *
     * @param latex the LaTeX math expression to parse
     *
     * @return the parsed math notation, or {@code null} if the input is blank
     */
    @Nullable
    public static String parseLatexToMath(@Nullable String latex) {
        return parseLatexToMath(latex, null);
    }

    /**
     * Parses LaTeX math expression to plain math notation with custom keywords
     *
     * @param latex the LaTeX math expression to parse
     * @param keywords additional custom keyword mappings to apply (merged with default mappings)
     *
     * @return the parsed math notation, or {@code null} if the input is blank
     */
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
}
