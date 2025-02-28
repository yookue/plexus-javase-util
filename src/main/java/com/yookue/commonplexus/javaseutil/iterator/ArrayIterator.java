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


import java.util.Iterator;
import java.util.NoSuchElementException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * {@link java.util.Iterator} for array
 *
 * @see "com.fasterxml.jackson.databind.util.ArrayIterator"
 */
@RequiredArgsConstructor
@Getter
@SuppressWarnings("unused")
public class ArrayIterator<E> implements Iterator<E> {
    private final E[] array;
    private int index = 0;

    public boolean hasNext() {
        return index < array.length;
    }

    public E next() {
        if (index < 0 || index >= array.length) {
            throw new NoSuchElementException("Array contains no more elements");    // $NON-NLS-1$
        }
        return array[index++];
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
