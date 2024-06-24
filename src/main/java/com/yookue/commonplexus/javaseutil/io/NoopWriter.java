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

package com.yookue.commonplexus.javaseutil.io;


import java.io.Writer;
import javax.annotation.Nullable;


/**
 * {@link java.io.Writer} with no operations
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public class NoopWriter extends Writer {
    @Override
    public Writer append(char character) {
        return this;
    }

    @Override
    public Writer append(@Nullable CharSequence sequence) {
        return this;
    }

    @Override
    public Writer append(@Nullable CharSequence sequence, int start, int end) {
        return this;
    }

    @Override
    public void write(int character) {
    }

    @Override
    public void write(@Nullable char[] buffer) {
    }

    @Override
    public void write(@Nullable char[] buffer, int offset, int length) {
    }

    @Override
    public void write(@Nullable String text) {
    }

    @Override
    public void write(@Nullable String text, int offset, int length) {
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }
}
