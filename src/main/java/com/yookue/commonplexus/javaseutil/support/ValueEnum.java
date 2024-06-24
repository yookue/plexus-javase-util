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

package com.yookue.commonplexus.javaseutil.support;


import com.yookue.commonplexus.javaseutil.util.ObjectUtilsWraps;


/**
 * Generic {@link java.lang.Enum} type with a {@code value} property
 *
 * @author David Hsing
 * @reference "https://www.jianshu.com/p/9a99b00ebe9d"
 */
@SuppressWarnings({"unused", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public interface ValueEnum<V> {
    /**
     * Returns the value of the enum
     *
     * @return the value of the enum
     */
    V getValue();

    /**
     * Returns the value of the enum, represented as string
     *
     * @return the value of the enum, represented as string
     */
    default String getValueAsString() {
        return ObjectUtilsWraps.toString(getValue());
    }
}
