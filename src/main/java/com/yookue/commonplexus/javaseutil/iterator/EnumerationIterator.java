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

package com.yookue.commonplexus.javaseutil.iterator;


import java.util.Enumeration;
import java.util.Iterator;


/**
 * {@link java.util.Iterator} for {@link java.util.Enumeration}
 *
 * @see org.apache.commons.collections4.iterators.EnumerationIterator
 * @see "org.apache.ibatis.ognl.EnumerationIterator"
 */
@SuppressWarnings("unused")
public record EnumerationIterator<E>(Enumeration<? extends E> enumeration) implements Iterator<E> {
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    public E next() {
        return enumeration.nextElement();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
