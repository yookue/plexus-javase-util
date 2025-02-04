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


/**
 * Constants for string variants
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public abstract class SymbolVariantConst {
    public static final String COLON_SPACE = ": ";    // $NON-NLS-1$
    public static final String COMMA_SPACE = ", ";    // $NON-NLS-1$
    public static final String CURLY_BRACKETS = "{}";    // $NON-NLS-1$
    public static final String DOLLAR_CURLY_BRACKET = "${";    // $NON-NLS-1$
    public static final String DOUBLE_BACKSLASHES = "\\";    // $NON-NLS-1$
    public static final String DOUBLE_DOLLAR = "$$";    // $NON-NLS-1$
    public static final String DOUBLE_DOTS = "..";    // $NON-NLS-1$
    public static final String DOUBLE_SLASHES = "//";    // $NON-NLS-1$
    public static final String HEX_PREFIX = "0x";    // $NON-NLS-1$
    public static final String JAVASCRIPT_COLON = "javascript:";    // $NON-NLS-1$
    public static final String JAVASCRIPT_VOID = JAVASCRIPT_COLON + "void(0);";    // $NON-NLS-1$
    public static final String ORDER_ANGLES = "<%d>";    // $NON-NLS-1$
    public static final String ORDER_ANGLES_SPACE = ORDER_ANGLES + CharVariantConst.SPACE;
    public static final String ORDER_COLON = "%d";    // $NON-NLS-1$
    public static final String ORDER_COLON_SPACE = ORDER_COLON + CharVariantConst.SPACE;
    public static final String ORDER_DOT = "%d.";    // $NON-NLS-1$
    public static final String ORDER_DOT_SPACE = ORDER_DOT + CharVariantConst.SPACE;
    public static final String ORDER_PARENTHESES = "(%d)";    // $NON-NLS-1$
    public static final String ORDER_PARENTHESES_SPACE = ORDER_PARENTHESES + CharVariantConst.SPACE;
    public static final String ORDER_SQUARES = "[%d]";    // $NON-NLS-1$
    public static final String ORDER_SQUARES_SPACE = ORDER_SQUARES + CharVariantConst.SPACE;
    public static final String PARENTHESES = "()";    // $NON-NLS-1$
    public static final String PROTOCOL_DELIMITER = "://";    // $NON-NLS-1$
    public static final String SEMICOLON_SPACE = "; ";    // $NON-NLS-1$
    public static final String SQUARE_BRACKETS = "[]";    // $NON-NLS-1$
}
