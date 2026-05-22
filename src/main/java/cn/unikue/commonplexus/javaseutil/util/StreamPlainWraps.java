/*
 * Copyright (c) 2016 Unikue Ltd. All rights reserved.
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

package cn.unikue.commonplexus.javaseutil.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import cn.unikue.commonplexus.javaseutil.iterator.ArrayIterator;
import cn.unikue.commonplexus.javaseutil.iterator.EnumerationIterator;


/**
 * Utilities for {@link java.util.stream.Stream}
 *
 * @author David Hsing
 *
 * @see java.util.stream.Stream
 * @see java.util.stream.StreamSupport
 * @see org.apache.commons.lang3.stream.Streams
 * @see "org.apache.commons.io.input.CountingInputStream"
 * @see "org.apache.commons.io.output.CountingOutputStream"
 * @see "org.springframework.util.StreamUtils"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class StreamPlainWraps {
    /**
     * Returns an estimate of the number of bytes that can be read (or skipped over) from this stream without blocking by the next invocation for this stream
     *
     * @param input The {@link java.io.InputStream} object to check
     *
     * @return an estimate of the number of bytes that can be read (or skipped over) from this stream without blocking by the next invocation for this stream
     */
    public int available(@Nullable InputStream input) throws IOException {
        return (input == null) ? 0 : input.available();
    }

    /**
     * Returns an estimate of the number of bytes that can be read from this stream, or 0 if an error occurs
     *
     * @param input The {@link java.io.InputStream} object to check
     *
     * @return an estimate of the number of bytes available, or 0 if input is null or an error occurs
     */
    public int availableQuietly(@Nullable InputStream input) {
        try {
            return available(input);
        } catch (Exception ignored) {
        }
        return 0;
    }

    /**
     * Returns a sequential stream from the given array
     *
     * @param array The source array
     *
     * @return a sequential stream, or an empty stream if array is null or empty
     */
    @Nonnull
    public static <E> Stream<E> stream(@Nullable E[] array) {
        return stream(array, false);
    }

    /**
     * Returns a stream object that created from an array
     *
     * @param array The source array
     * @param parallel If {@code true} then the returned stream is a parallel stream; if false the returned stream is a sequential stream
     *
     * @return a stream object that created from an array
     */
    @Nonnull
    public static <E> Stream<E> stream(@Nullable E[] array, boolean parallel) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : StreamSupport.stream(Spliterators.spliteratorUnknownSize(new ArrayIterator<>(array), Spliterator.ORDERED), parallel);
    }

    /**
     * Returns a sequential stream from the given enumeration
     *
     * @param enumeration The source enumeration
     *
     * @return a sequential stream, or an empty stream if enumeration is null
     */
    @Nonnull
    public static <E> Stream<E> stream(@Nullable Enumeration<E> enumeration) {
        return stream(enumeration, false);
    }

    /**
     * Returns a stream object that created from an enumeration
     *
     * @param enumeration The source enumeration
     * @param parallel If {@code true} then the returned stream is a parallel stream; if false the returned stream is a sequential stream
     *
     * @return a stream object that created from an enumeration
     *
     * @reference "http://www.hackerav.com/?post=11225"
     */
    @Nonnull
    @SuppressWarnings({"JavadocDeclaration", "JavadocLinkAsPlainText"})
    public static <E> Stream<E> stream(@Nullable Enumeration<E> enumeration, boolean parallel) {
        return (enumeration == null) ? Stream.empty() : StreamSupport.stream(Spliterators.spliteratorUnknownSize(new EnumerationIterator<>(enumeration), Spliterator.ORDERED), parallel);
    }
}
