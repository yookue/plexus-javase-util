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
 * Enumerations of number order types
 *
 * @author David Hsing
 * @reference "https://zhidao.baidu.com/question/283479244.html"
 */
@AllArgsConstructor
@Getter
@SuppressWarnings({"unused", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public enum NumberOrderType implements ValueEnum<Integer> {
    PRIMARY(1),
    SECONDARY(2),
    TERTIARY(3),
    QUATERNARY(4),
    QUINARY(5),
    SENARY(6),
    SEPTENARY(7),
    OCTONARY(8),
    NONARY(9),
    DENARY(10),
    DUODENARY(12),
    VIGENARY(20);

    private final Integer value;
}
