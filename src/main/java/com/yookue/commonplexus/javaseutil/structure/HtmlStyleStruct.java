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
import javax.swing.text.html.HTML;
import jakarta.annotation.Nullable;


/**
 * Structure for HTML "&lt;style&gt;" node
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public class HtmlStyleStruct extends HtmlTagStruct {
    public static final String DEFAULT_TYPE = "text/css";    // $NON-NLS-1$

    public HtmlStyleStruct() {
        super.setTag(HTML.Tag.STYLE);
        super.addAttribute(HTML.Attribute.TYPE, DEFAULT_TYPE);
    }

    public HtmlStyleStruct(@Nullable List<String> contents) {
        this();
        super.setSubNodes(contents);
    }
}
