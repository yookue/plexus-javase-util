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
 * Constants for regular expressions
 *
 * @author David Hsing
 * @see "org.apache.http.conn.util.InetAddressWraps"
 */
@SuppressWarnings("unused")
public abstract class RegexVariantConst {
    public static final String APPLICATION_JSON = "^application\\/\\w*[+-]?(nd)?json\\b";    // $NON-NLS-1$
    public static final String APPLICATION_XML = "^application\\/\\w*[+-]?xml\\b";    // $NON-NLS-1$

    public static final String ANGLE_BRACKETS = "<[^>]+>";    // $NON-NLS-1$
    public static final String ARRAY_INDEX = "\\[\\d*\\]";    // $NON-NLS-1$
    public static final String BASE64_IMAGE = "data:image/\\w+;base64,";    // $NON-NLS-1$
    public static final String CURLY_BRACKETS = "\\{\\}";    // $NON-NLS-1$
    public static final String LINE_SEPARATOR = "\\r?\\n";    // $NON-NLS-1$
    public static final String LANGUAGE_TAG = "[a-zA-Z]{2,3}([_\\-]{1}[a-zA-Z]{2,3}){0,1}([_\\-]{1}[a-zA-Z]{2,4}){0,1}";    // $NON-NLS-1$
    public static final String MULTIPLE_SUFFIX = "{2,}";    // $NON-NLS-1$
    public static final String MULTIPLE_SLASHES = "/" + MULTIPLE_SUFFIX;    // $NON-NLS-1$
    public static final String MULTIPLE_BACKSLASHES = "\\" + MULTIPLE_SUFFIX;    // $NON-NLS-1$
    public static final String MULTIPLE_SPACES = "\\s" + MULTIPLE_SUFFIX;    // $NON-NLS-1$
    public static final String SINGLE_BACKSLASH = "\\\\";    // $NON-NLS-1$

    public static final String LAN_ADDRESS_IPV4 = "(127\\.0\\.0\\.1)|(localhost)|(10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|(172\\.((1[6-9])|(2\\d)|(3[01]))\\.\\d{1,3}\\.\\d{1,3})|(192\\.168\\.\\d{1,3}\\.\\d{1,3})";    // $NON-NLS-1$
}
