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


import javax.annotation.Nonnull;
import javax.swing.text.html.HTML.Tag;
import com.yookue.commonplexus.javaseutil.structure.HtmlTagStruct;


/**
 * Utilities for HTML
 *
 * @author David Hsing
 * @see org.apache.commons.text.StringEscapeUtils
 * @see "org.springframework.web.util.HtmlUtils"
 * @see "com.sun.tools.doclets.formats.html.markup.HtmlTree"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class HtmlEscapeWraps {
    @Nonnull
    public static String getBrAsString() {
        return new HtmlTagStruct(Tag.BR).toString();
    }

    @Nonnull
    public static String getHrAsString() {
        return new HtmlTagStruct(Tag.HR).toString();
    }
}
