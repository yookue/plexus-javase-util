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


import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import jakarta.annotation.Nullable;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link org.apache.commons.io.IOUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.io.IOUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class IoUtilsWraps {
    public static void close(@Nullable Closeable closeable) {
        try {
            IOUtils.close(closeable);
        } catch (Exception ignored) {
        }
    }

    public static void close(@Nullable Closeable... closeables) {
        if (ArrayUtils.isEmpty(closeables)) {
            return;
        }
        try {
            IOUtils.close(closeables);
        } catch (Exception ignored) {
        }
    }

    public static void close(@Nullable Closeable closeable, @Nullable IOConsumer<IOException> action) {
        if (closeable == null) {
            return;
        }
        try {
            IOUtils.close(closeable, action);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int copy(@Nullable InputStream input, @Nullable OutputStream output) {
        if (ObjectUtils.anyNull(input, output)) {
            return 0;
        }
        try {
            return IOUtils.copy(input, output);
        } catch (Exception ignored) {
        }
        return 0;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static long copy(@Nullable InputStream input, @Nullable OutputStream output, int bufferSize) {
        if (ObjectUtils.anyNull(input, output) || bufferSize <= 0) {
            return 0L;
        }
        try {
            return IOUtils.copy(input, output, bufferSize);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copy(@Nullable InputStream input, @Nullable Writer output, @Nullable Charset charset) {
        if (ObjectUtils.anyNull(input, output)) {
            return;
        }
        try {
            IOUtils.copy(input, output, charset);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copy(@Nullable InputStream input, @Nullable Writer output, @Nullable String charset) {
        if (ObjectUtils.anyNull(input, output)) {
            return;
        }
        try {
            IOUtils.copy(input, output, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static long copy(@Nullable Reader input, @Nullable Appendable output) {
        if (ObjectUtils.anyNull(input, output)) {
            return 0L;
        }
        try {
            return IOUtils.copy(input, output);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static long copy(@Nullable Reader input, @Nullable Appendable output, @Nullable CharBuffer buffer) {
        if (ObjectUtils.anyNull(input, output, buffer)) {
            return 0L;
        }
        try {
            return IOUtils.copy(input, output, buffer);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copy(@Nullable Reader input, @Nullable OutputStream output, @Nullable Charset charset) {
        if (ObjectUtils.anyNull(input, output)) {
            return;
        }
        try {
            IOUtils.copy(input, output, charset);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copy(@Nullable Reader input, @Nullable OutputStream output, @Nullable String charset) {
        if (ObjectUtils.anyNull(input, output)) {
            return;
        }
        try {
            IOUtils.copy(input, output, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int copy(@Nullable Reader input, @Nullable Writer output) {
        if (ObjectUtils.anyNull(input, output)) {
            return 0;
        }
        try {
            return IOUtils.copy(input, output);
        } catch (Exception ignored) {
        }
        return 0;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static long copy(@Nullable URL input, @Nullable File output) {
        if (ObjectUtils.anyNull(input, output)) {
            return 0L;
        }
        try {
            return IOUtils.copy(input, output);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static long copy(@Nullable URL input, @Nullable OutputStream output) {
        if (ObjectUtils.anyNull(input, output)) {
            return 0L;
        }
        try {
            return IOUtils.copy(input, output);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static long copyLarge(@Nullable InputStream input, @Nullable OutputStream output) {
        if (ObjectUtils.anyNull(input, output)) {
            return 0L;
        }
        try {
            return IOUtils.copyLarge(input, output);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static long copyLarge(@Nullable InputStream input, @Nullable OutputStream output, @Nullable byte[] buffer) {
        if (ObjectUtils.anyNull(input, output, buffer)) {
            return 0L;
        }
        try {
            return IOUtils.copyLarge(input, output, buffer);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    public static long copyLarge(@Nullable InputStream input, @Nullable OutputStream output, long offset, long length) {
        if (ObjectUtils.anyNull(input, output) || offset < 0 || length <= 0) {
            return 0L;
        }
        try {
            return IOUtils.copyLarge(input, output, offset, length);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings("DuplicatedCode")
    public static long copyLarge(@Nullable InputStream input, @Nullable OutputStream output, long offset, long length, @Nullable byte[] buffer) {
        if (ObjectUtils.anyNull(input, output, buffer) || offset < 0 || length <= 0) {
            return 0L;
        }
        try {
            return IOUtils.copyLarge(input, output, offset, length, buffer);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static long copyLarge(@Nullable Reader input, @Nullable Writer output) {
        if (ObjectUtils.anyNull(input, output)) {
            return 0L;
        }
        try {
            return IOUtils.copyLarge(input, output);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static long copyLarge(@Nullable Reader input, @Nullable Writer output, @Nullable char[] buffer) {
        if (ObjectUtils.anyNull(input, output, buffer)) {
            return 0L;
        }
        try {
            return IOUtils.copyLarge(input, output, buffer);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    public static long copyLarge(@Nullable Reader input, @Nullable Writer output, long offset, long length) {
        if (ObjectUtils.anyNull(input, output) || offset < 0 || length <= 0) {
            return 0L;
        }
        try {
            return IOUtils.copyLarge(input, output, offset, length);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings("DuplicatedCode")
    public static long copyLarge(@Nullable Reader input, @Nullable Writer output, long offset, long length, @Nullable char[] buffer) {
        if (ObjectUtils.anyNull(input, output, buffer) || offset < 0 || length <= 0) {
            return 0L;
        }
        try {
            return IOUtils.copyLarge(input, output, offset, length, buffer);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int read(@Nullable InputStream input, @Nullable byte[] buffer) {
        if (ObjectUtils.anyNull(input, buffer)) {
            return 0;
        }
        try {
            return IOUtils.read(input, buffer);
        } catch (Exception ignored) {
        }
        return 0;
    }

    public static int read(@Nullable InputStream input, @Nullable byte[] buffer, int offset, int length) {
        if (ObjectUtils.anyNull(input, buffer) || offset < 0 || length <= 0) {
            return 0;
        }
        try {
            return IOUtils.read(input, buffer, offset, length);
        } catch (Exception ignored) {
        }
        return 0;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int read(@Nullable ReadableByteChannel input, @Nullable ByteBuffer buffer) {
        if (ObjectUtils.anyNull(input, buffer)) {
            return 0;
        }
        try {
            return IOUtils.read(input, buffer);
        } catch (Exception ignored) {
        }
        return 0;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static int read(@Nullable Reader input, @Nullable char[] buffer) {
        if (ObjectUtils.anyNull(input, buffer)) {
            return 0;
        }
        try {
            return IOUtils.read(input, buffer);
        } catch (Exception ignored) {
        }
        return 0;
    }

    public static int read(@Nullable Reader input, @Nullable char[] buffer, int offset, int length) {
        if (ObjectUtils.anyNull(input, buffer) || offset < 0 || length <= 0) {
            return 0;
        }
        try {
            return IOUtils.read(input, buffer, offset, length);
        } catch (Exception ignored) {
        }
        return 0;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void readFully(@Nullable InputStream input, @Nullable byte[] buffer) {
        if (ObjectUtils.anyNull(input, buffer)) {
            return;
        }
        try {
            IOUtils.readFully(input, buffer);
        } catch (Exception ignored) {
        }
    }

    public static void readFully(@Nullable InputStream input, @Nullable byte[] buffer, int offset, int length) {
        if (ObjectUtils.anyNull(input, buffer) || offset < 0 || length <= 0) {
            return;
        }
        try {
            IOUtils.readFully(input, buffer, offset, length);
        } catch (Exception ignored) {
        }
    }

    @Nullable
    public static byte[] readFully(@Nullable InputStream input, int length) {
        if (input == null || length <= 0) {
            return null;
        }
        try {
            return IOUtils.readFully(input, length);
        } catch (Exception ignored) {
        }
        return null;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void readFully(@Nullable ReadableByteChannel input, @Nullable ByteBuffer buffer) {
        if (ObjectUtils.anyNull(input, buffer)) {
            return;
        }
        try {
            IOUtils.readFully(input, buffer);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void readFully(@Nullable Reader input, @Nullable char[] buffer) {
        if (ObjectUtils.anyNull(input, buffer)) {
            return;
        }
        try {
            IOUtils.readFully(input, buffer);
        } catch (Exception ignored) {
        }
    }

    public static void readFully(@Nullable Reader input, @Nullable char[] buffer, int offset, int length) {
        if (ObjectUtils.anyNull(input, buffer) || offset < 0 || length <= 0) {
            return;
        }
        try {
            IOUtils.readFully(input, buffer, offset, length);
        } catch (Exception ignored) {
        }
    }

    @Nullable
    public static List<String> readLines(@Nullable InputStream input, @Nullable Charset charset) {
        if (input == null) {
            return null;
        }
        try {
            return IOUtils.readLines(input, charset);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static List<String> readLines(@Nullable InputStream input, @Nullable String charset) {
        if (input == null) {
            return null;
        }
        try {
            return IOUtils.readLines(input, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static List<String> readLines(@Nullable Reader input) {
        if (input == null) {
            return null;
        }
        try {
            return IOUtils.readLines(input);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static byte[] resourceToByteArray(@Nullable String name) {
        return resourceToByteArray(name, null);
    }

    @Nullable
    public static byte[] resourceToByteArray(@Nullable String name, @Nullable ClassLoader loader) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        try {
            return IOUtils.resourceToByteArray(name, loader);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String resourceToString(@Nullable String name, @Nullable Charset charset) {
        return resourceToString(name, charset, null);
    }

    @Nullable
    public static String resourceToString(@Nullable String name, @Nullable Charset charset, @Nullable ClassLoader loader) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        try {
            return StringUtilsWraps.nullIfEmpty(IOUtils.resourceToString(name, charset, loader));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static URL resourceToUrl(@Nullable String name) {
        return resourceToUrl(name, null);
    }

    @Nullable
    public static URL resourceToUrl(@Nullable String name, @Nullable ClassLoader loader) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        try {
            return IOUtils.resourceToURL(name, loader);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static long skip(@Nullable InputStream input, long toSkip) {
        if (input == null || toSkip < 0) {
            return 0L;
        }
        try {
            return IOUtils.skip(input, toSkip);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    public static long skip(@Nullable ReadableByteChannel input, long toSkip) {
        if (input == null || toSkip < 0) {
            return 0L;
        }
        try {
            return IOUtils.skip(input, toSkip);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    public static long skip(@Nullable Reader input, long toSkip) {
        if (input == null || toSkip < 0) {
            return 0L;
        }
        try {
            return IOUtils.skip(input, toSkip);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    public static void skipFully(@Nullable InputStream input, long toSkip) {
        if (input == null || toSkip < 0) {
            return;
        }
        try {
            IOUtils.skipFully(input, toSkip);
        } catch (Exception ignored) {
        }
    }

    public static void skipFully(@Nullable ReadableByteChannel input, long toSkip) {
        if (input == null || toSkip < 0) {
            return;
        }
        try {
            IOUtils.skipFully(input, toSkip);
        } catch (Exception ignored) {
        }
    }

    public static void skipFully(@Nullable Reader input, long toSkip) {
        if (input == null || toSkip < 0) {
            return;
        }
        try {
            IOUtils.skipFully(input, toSkip);
        } catch (Exception ignored) {
        }
    }

    @Nullable
    public static InputStream toBufferedInputStream(@Nullable InputStream source) {
        if (source == null) {
            return null;
        }
        try {
            return IOUtils.toBufferedInputStream(source);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static InputStream toBufferedInputStream(@Nullable InputStream source, int size) {
        if (source == null || size <= 0) {
            return null;
        }
        try {
            return IOUtils.toBufferedInputStream(source, size);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static byte[] toByteArray(@Nullable InputStream source) {
        if (source == null) {
            return null;
        }
        try {
            return IOUtils.toByteArray(source);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static byte[] toByteArray(@Nullable InputStream source, int size) {
        if (source == null || size <= 0) {
            return null;
        }
        try {
            return IOUtils.toByteArray(source, size);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static byte[] toByteArray(@Nullable InputStream source, long size) {
        if (source == null || size <= 0L) {
            return null;
        }
        try {
            return IOUtils.toByteArray(source, size);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static byte[] toByteArray(@Nullable Reader source, @Nullable Charset charset) {
        if (source == null) {
            return null;
        }
        try {
            return IOUtils.toByteArray(source, charset);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static byte[] toByteArray(@Nullable Reader source, @Nullable String charset) {
        if (source == null) {
            return null;
        }
        try {
            return IOUtils.toByteArray(source, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static byte[] toByteArray(@Nullable URI source) {
        if (source == null) {
            return null;
        }
        try {
            return IOUtils.toByteArray(source);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static byte[] toByteArray(@Nullable URL source) {
        if (source == null) {
            return null;
        }
        try {
            return IOUtils.toByteArray(source);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static byte[] toByteArray(@Nullable URLConnection source) {
        if (source == null) {
            return null;
        }
        try {
            return IOUtils.toByteArray(source);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static char[] toCharArray(@Nullable InputStream source, @Nullable Charset charset) {
        if (source == null) {
            return null;
        }
        try {
            return IOUtils.toCharArray(source, charset);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static char[] toCharArray(@Nullable InputStream source, @Nullable String charset) {
        if (source == null) {
            return null;
        }
        try {
            return IOUtils.toCharArray(source, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static char[] toCharArray(@Nullable Reader source) {
        if (source == null) {
            return null;
        }
        try {
            return IOUtils.toCharArray(source);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toString(@Nullable byte[] source, @Nullable Charset charset) {
        if (ArrayUtils.isEmpty(source)) {
            return null;
        }
        try {
            return StringUtilsWraps.nullIfEmpty(IOUtils.toString(source, CharsetPlainWraps.defaultCharsetName(charset)));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String toString(@Nullable byte[] source, @Nullable String charset) {
        if (ArrayUtils.isEmpty(source)) {
            return null;
        }
        try {
            return StringUtilsWraps.nullIfEmpty(IOUtils.toString(source, charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String toString(@Nullable InputStream source, @Nullable Charset charset) {
        if (source == null) {
            return null;
        }
        try {
            return StringUtilsWraps.nullIfEmpty(IOUtils.toString(source, charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String toString(@Nullable InputStream source, @Nullable String charset) {
        if (source == null) {
            return null;
        }
        try {
            return StringUtilsWraps.nullIfEmpty(IOUtils.toString(source, CharsetPlainWraps.defaultCharsetName(charset)));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String toString(@Nullable Reader source) {
        if (source == null) {
            return null;
        }
        try {
            return StringUtilsWraps.nullIfEmpty(IOUtils.toString(source));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String toString(@Nullable URI source, @Nullable Charset charset) {
        if (source == null) {
            return null;
        }
        try {
            return StringUtilsWraps.nullIfEmpty(IOUtils.toString(source, charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String toString(@Nullable URI source, @Nullable String charset) {
        if (source == null) {
            return null;
        }
        try {
            return StringUtilsWraps.nullIfEmpty(IOUtils.toString(source, CharsetPlainWraps.defaultCharsetName(charset)));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String toString(@Nullable URL source, @Nullable Charset charset) {
        if (source == null) {
            return null;
        }
        try {
            return StringUtilsWraps.nullIfEmpty(IOUtils.toString(source, charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String toString(@Nullable URL source, @Nullable String charset) {
        if (source == null) {
            return null;
        }
        try {
            return StringUtilsWraps.nullIfEmpty(IOUtils.toString(source, CharsetPlainWraps.defaultCharsetName(charset)));
        } catch (Exception ignored) {
        }
        return null;
    }

    public static void write(@Nullable byte[] data, @Nullable OutputStream target) {
        if (ArrayUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target);
        } catch (Exception ignored) {
        }
    }

    public static void write(@Nullable byte[] data, @Nullable Writer target, @Nullable Charset charset) {
        if (ArrayUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target, charset);
        } catch (Exception ignored) {
        }
    }

    public static void write(@Nullable byte[] data, @Nullable Writer target, @Nullable String charset) {
        if (ArrayUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
    }

    public static void write(@Nullable char[] data, @Nullable OutputStream target, @Nullable Charset charset) {
        if (ArrayUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target, charset);
        } catch (Exception ignored) {
        }
    }

    public static void write(@Nullable char[] data, @Nullable OutputStream target, @Nullable String charset) {
        if (ArrayUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
    }

    public static void write(@Nullable char[] data, @Nullable Writer target) {
        if (ArrayUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target);
        } catch (Exception ignored) {
        }
    }

    public static void write(@Nullable CharSequence data, @Nullable OutputStream target, @Nullable Charset charset) {
        if (StringUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target, charset);
        } catch (Exception ignored) {
        }
    }

    public static void write(@Nullable CharSequence data, @Nullable OutputStream target, @Nullable String charset) {
        if (StringUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
    }

    public static void write(@Nullable CharSequence data, @Nullable Writer target) {
        if (StringUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target);
        } catch (Exception ignored) {
        }
    }

    public static void write(@Nullable String data, @Nullable OutputStream target, @Nullable Charset charset) {
        if (StringUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target, charset);
        } catch (Exception ignored) {
        }
    }

    public static void write(@Nullable String data, @Nullable OutputStream target, @Nullable String charset) {
        if (StringUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
    }

    public static void write(@Nullable String data, @Nullable Writer target) {
        if (StringUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.write(data, target);
        } catch (Exception ignored) {
        }
    }

    public static void writeChunked(@Nullable byte[] data, @Nullable OutputStream target) {
        if (ArrayUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.writeChunked(data, target);
        } catch (Exception ignored) {
        }
    }

    public static void writeChunked(@Nullable char[] data, @Nullable Writer target) {
        if (ArrayUtils.isEmpty(data) || target == null) {
            return;
        }
        try {
            IOUtils.writeChunked(data, target);
        } catch (Exception ignored) {
        }
    }

    public static void writeLines(@Nullable Collection<?> lines, @Nullable String lineEnding, @Nullable Writer target) {
        if (CollectionPlainWraps.isEmpty(lines) || target == null) {
            return;
        }
        try {
            IOUtils.writeLines(lines, lineEnding, target);
        } catch (Exception ignored) {
        }
    }

    public static void writeLines(@Nullable Collection<?> lines, @Nullable String lineEnding, @Nullable OutputStream target, @Nullable Charset charset) {
        if (CollectionPlainWraps.isEmpty(lines) || target == null) {
            return;
        }
        try {
            IOUtils.writeLines(lines, lineEnding, target, charset);
        } catch (Exception ignored) {
        }
    }

    public static void writeLines(@Nullable Collection<?> lines, @Nullable String lineEnding, @Nullable OutputStream target, @Nullable String charset) {
        if (CollectionPlainWraps.isEmpty(lines) || target == null) {
            return;
        }
        try {
            IOUtils.writeLines(lines, lineEnding, target, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
    }
}
