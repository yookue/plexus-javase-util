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

package com.yookue.commonplexus.javaseutil.enumeration;


import com.yookue.commonplexus.javaseutil.support.ValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Enumerations of jsoup whitelist types {@link org.jsoup.safety.Safelist}
 *
 * @author David Hsing
 */
@AllArgsConstructor
@Getter
@SuppressWarnings("unused")
public enum JsoupWhitelistType implements ValueEnum<String> {
    /**
     * Allows pure text only
     */
    NONE("none"),    // $NON-NLS-1$

    /**
     * @see org.jsoup.safety.Safelist#simpleText
     */
    SIMPLE_TEXT("simpleText"),    // $NON-NLS-1$

    /**
     * @see org.jsoup.safety.Safelist#basic
     */
    BASIC("basic"),    // $NON-NLS-1$

    /**
     * Allows {@code BASIC} with "img"
     *
     * @see org.jsoup.safety.Safelist#basicWithImages
     */
    BASIC_WITH_IMAGE("basicWithImage"),    // $NON-NLS-1$

    /**
     * @see org.jsoup.safety.Safelist#relaxed
     */
    RELAXED("relaxed"),    // $NON-NLS-1$

    /**
     * Allows {@code RELAXED} with "object"
     */
    RELAXED_WITH_FLASH("relaxedWithFlash"),    // $NON-NLS-1$

    /**
     * Allows {@code RELAXED} with HTML5 tags
     *
     * @see com.yookue.commonplexus.javaseutil.util.JsoupParserWraps#html5Safelist
     */
    RELAXED_WITH_HTML5("relaxedWithHtml5");    // $NON-NLS-1$

    private final String value;
}
