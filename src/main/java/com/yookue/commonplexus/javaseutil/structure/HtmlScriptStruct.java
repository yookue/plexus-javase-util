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

package com.yookue.commonplexus.javaseutil.structure;


import java.util.List;
import jakarta.annotation.Nullable;
import javax.swing.text.html.HTML;


/**
 * Structure for HTML "&lt;script&gt;" node
 *
 * @author David Hsing
 * @see "org.springframework.web.util.JavaScriptUtils"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public class HtmlScriptStruct extends HtmlTagStruct {
    public static final String DEFAULT_TYPE = "text/javascript";    // $NON-NLS-1$

    public HtmlScriptStruct() {
        super.setTag(HTML.Tag.SCRIPT);
        super.addAttribute(HTML.Attribute.TYPE, DEFAULT_TYPE);
    }

    public HtmlScriptStruct(@Nullable List<String> contents) {
        this();
        super.setSubNodes(contents);
    }

    public HtmlScriptStruct(@Nullable String src, @Nullable String lowsrc) {
        this();
        super.addAttribute(HTML.Attribute.SRC, src);
        super.addAttribute(HTML.Attribute.LOWSRC, lowsrc);
    }
}
