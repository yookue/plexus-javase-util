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


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Stream;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link java.nio.file.Files}
 *
 * @author David Hsing
 * @see java.nio.file.Files
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class FilePlainWraps {
    public static boolean isHidden(@Nullable Path path) {
        if (path == null) {
            return false;
        }
        try {
            return Files.isHidden(path);
        } catch (Exception ignored) {
        }
        return false;
    }

    public static long size(@Nullable Path path) {
        if (path == null) {
            return 0L;
        }
        try {
            return Files.size(path);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @Nullable
    public static Stream<String> lines(@Nullable Path path) {
        if (path == null) {
            return null;
        }
        try {
            return Files.lines(path);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Stream<String> lines(@Nullable Path path, @Nullable Charset charset) {
        if (path == null) {
            return null;
        }
        try {
            return Files.lines(path, CharsetPlainWraps.defaultCharset(charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Stream<Path> list(@Nullable Path path) {
        if (path == null) {
            return null;
        }
        try {
            return Files.list(path);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Stream<Path> find(@Nullable Path path, int maxDepth, @Nullable BiPredicate<Path, BasicFileAttributes> filter, @Nullable FileVisitOption[] options) {
        if (path == null || filter == null) {
            return null;
        }
        try {
            return Files.find(path, maxDepth, filter, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path write(@Nullable Path path, @Nullable Iterable<? extends CharSequence> lines, @Nullable Charset charset, @Nullable OpenOption[] options) {
        if (path == null || lines == null) {
            return null;
        }
        try {
            return Files.write(path, lines, CharsetPlainWraps.defaultCharset(charset), options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path write(@Nullable Path path, @Nullable byte[] bytes, @Nullable OpenOption[] options) {
        if (path == null || bytes == null) {
            return null;
        }
        try {
            return Files.write(path, bytes, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path write(@Nullable Path path, @Nullable Iterable<? extends CharSequence> lines, @Nullable OpenOption[] options) {
        if (path == null || lines == null) {
            return null;
        }
        try {
            return Files.write(path, lines, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static void delete(@Nullable Path path) {
        if (path == null) {
            return;
        }
        try {
            Files.delete(path);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings("DataFlowIssue")
    public static long copy(@Nullable InputStream inputStream, @Nullable Path path, @Nullable CopyOption[] options) {
        if (inputStream == null || path == null) {
            return 0L;
        }
        try {
            return Files.copy(inputStream, path, options);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path copy(@Nullable Path source, @Nullable Path target, @Nullable CopyOption[] options) {
        if (source == null || target == null) {
            return null;
        }
        try {
            return Files.copy(source, target, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static long copy(@Nullable Path path, @Nullable OutputStream outputStream) {
        if (path == null || outputStream == null) {
            return 0L;
        }
        try {
            return Files.copy(path, outputStream);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @Nullable
    public static byte[] readAllBytes(@Nullable Path path) {
        if (path == null) {
            return null;
        }
        try {
            return Files.readAllBytes(path);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Stream<Path> walk(@Nullable Path path, int maxDepth, @Nullable FileVisitOption[] options) {
        if (path == null) {
            return null;
        }
        try {
            return Files.walk(path, maxDepth, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Stream<Path> walk(@Nullable Path path, @Nullable FileVisitOption[] options) {
        if (path == null) {
            return null;
        }
        try {
            return Files.walk(path, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @SuppressWarnings("DataFlowIssue")
    public static boolean exists(@Nullable Path path, @Nullable LinkOption[] options) {
        if (path == null) {
            return false;
        }
        try {
            return Files.exists(path, options);
        } catch (Exception ignored) {
        }
        return false;
    }

    public static long mismatch(@Nullable Path path1, @Nullable Path path2) {
        if (path1 == null || path2 == null) {
            return 0L;
        }
        try {
            return Files.mismatch(path1, path2);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    @SuppressWarnings("DataFlowIssue")
    public static boolean isDirectory(@Nullable Path path, @Nullable LinkOption[] options) {
        if (path == null) {
            return false;
        }
        try {
            return Files.isDirectory(path, options);
        } catch (Exception ignored) {
        }
        return false;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static FileTime getLastModifiedTime(@Nullable Path path, @Nullable LinkOption[] options) {
        if (path == null) {
            return null;
        }
        try {
            return Files.getLastModifiedTime(path, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path createDirectory(@Nullable Path path, @Nullable FileAttribute<?>[] attrs) {
        if (path == null) {
            return null;
        }
        try {
            return Files.createDirectory(path, attrs);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Path setLastModifiedTime(@Nullable Path path, @Nullable FileTime time) {
        if (path == null || time == null) {
            return null;
        }
        try {
            return Files.setLastModifiedTime(path, time);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path createTempFile(@Nullable Path path, @Nullable String prefix, @Nullable String suffix, @Nullable FileAttribute<?>[] attrs) {
        if (path == null) {
            return null;
        }
        try {
            return Files.createTempFile(path, prefix, suffix, attrs);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path createTempFile(@Nullable String prefix, @Nullable String suffix, @Nullable FileAttribute<?>[] attrs) {
        try {
            return Files.createTempFile(prefix, suffix, attrs);
        } catch (Exception ignored) {
        }
        return null;
    }

    @SuppressWarnings("DataFlowIssue")
    public static boolean isRegularFile(@Nullable Path path, @Nullable LinkOption[] options) {
        if (path == null) {
            return false;
        }
        try {
            return Files.isRegularFile(path, options);
        } catch (Exception ignored) {
        }
        return false;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static InputStream newInputStream(@Nullable Path path, @Nullable OpenOption[] options) {
        if (path == null) {
            return null;
        }
        try {
            return Files.newInputStream(path, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static OutputStream newOutputStream(@Nullable Path path, @Nullable OpenOption[] options) {
        if (path == null) {
            return null;
        }
        try {
            return Files.newOutputStream(path, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static SeekableByteChannel newByteChannel(@Nullable Path path, @Nullable OpenOption[] options) {
        if (path == null) {
            return null;
        }
        try {
            return Files.newByteChannel(path, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static SeekableByteChannel newByteChannel(@Nullable Path path, @Nullable Set<? extends OpenOption> options, @Nullable FileAttribute<?>[] attrs) {
        if (path == null) {
            return null;
        }
        try {
            return Files.newByteChannel(path, options, attrs);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static DirectoryStream<Path> newDirectoryStream(@Nullable Path path) {
        if (path == null) {
            return null;
        }
        try {
            return Files.newDirectoryStream(path);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static DirectoryStream<Path> newDirectoryStream(@Nullable Path path, @Nullable DirectoryStream.Filter<? super Path> filter) {
        if (path == null || filter == null) {
            return null;
        }
        try {
            return Files.newDirectoryStream(path, filter);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static DirectoryStream<Path> newDirectoryStream(@Nullable Path path, @Nullable String glob) {
        if (path == null || glob == null) {
            return null;
        }
        try {
            return Files.newDirectoryStream(path, glob);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path createTempDirectory(@Nullable Path path, @Nullable String prefix, @Nullable FileAttribute<?>[] attrs) {
        if (path == null) {
            return null;
        }
        try {
            return Files.createTempDirectory(path, prefix, attrs);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path createTempDirectory(@Nullable String prefix, @Nullable FileAttribute<?>[] attrs) {
        try {
            return Files.createTempDirectory(prefix, attrs);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path createSymbolicLink(@Nullable Path link, @Nullable Path target, @Nullable FileAttribute<?>[] attrs) {
        if (link == null || target == null) {
            return null;
        }
        try {
            return Files.createSymbolicLink(link, target, attrs);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Path createLink(@Nullable Path link, @Nullable Path exist) {
        if (link == null || exist == null) {
            return null;
        }
        try {
            return Files.createLink(link, exist);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static boolean deleteIfExists(@Nullable Path path) {
        if (path == null) {
            return false;
        }
        try {
            return Files.deleteIfExists(path);
        } catch (Exception ignored) {
        }
        return false;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path move(@Nullable Path source, @Nullable Path target, @Nullable CopyOption[] options) {
        if (source == null || target == null) {
            return null;
        }
        try {
            return Files.move(source, target, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Path readSymbolicLink(@Nullable Path path) {
        if (path == null) {
            return null;
        }
        try {
            return Files.readSymbolicLink(path);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static FileStore getFileStore(@Nullable Path path) {
        if (path == null) {
            return null;
        }
        try {
            return Files.getFileStore(path);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static boolean isSameFile(@Nullable Path path1, @Nullable Path path2) {
        if (path1 == null || path2 == null) {
            return false;
        }
        try {
            return Files.isSameFile(path1, path2);
        } catch (Exception ignored) {
        }
        return false;
    }

    @Nullable
    public static String probeContentType(@Nullable Path path) {
        if (path == null) {
            return null;
        }
        try {
            return Files.probeContentType(path);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static <V extends FileAttributeView> V getFileAttributeView(@Nullable Path path, @Nullable Class<V> type, @Nullable LinkOption[] options) {
        if (path == null || type == null) {
            return null;
        }
        try {
            return Files.getFileAttributeView(path, type, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static <A extends BasicFileAttributes> A readAttributes(@Nullable Path path, @Nullable Class<A> type, @Nullable LinkOption[] options) {
        if (path == null || type == null) {
            return null;
        }
        try {
            return Files.readAttributes(path, type, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Map<String, Object> readAttributes(@Nullable Path path, @Nullable String attrs, @Nullable LinkOption[] options) {
        if (path == null) {
            return null;
        }
        try {
            return Files.readAttributes(path, attrs, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path setAttribute(@Nullable Path path, @Nullable String attr, @Nullable Object value, @Nullable LinkOption[] options) {
        if (path == null || attr == null) {
            return null;
        }
        try {
            return Files.setAttribute(path, attr, value, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static boolean isSymbolicLink(@Nullable Path path) {
        if (path == null) {
            return false;
        }
        try {
            return Files.isSymbolicLink(path);
        } catch (Exception ignored) {
        }
        return false;
    }

    @Nullable
    public static Path walkFileTree(@Nullable Path path, @Nullable FileVisitor<? super Path> visitor) {
        if (path == null || visitor == null) {
            return null;
        }
        try {
            return Files.walkFileTree(path, visitor);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path walkFileTree(@Nullable Path path, @Nullable Set<FileVisitOption> options, int maxDepth, @Nullable FileVisitor<? super Path> visitor) {
        if (path == null || visitor == null) {
            return null;
        }
        try {
            return Files.walkFileTree(path, options, maxDepth, visitor);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static BufferedReader newBufferedReader(@Nullable Path path) {
        if (path == null) {
            return null;
        }
        try {
            return Files.newBufferedReader(path);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static BufferedReader newBufferedReader(@Nullable Path path, @Nullable Charset charset) {
        if (path == null) {
            return null;
        }
        try {
            return Files.newBufferedReader(path, CharsetPlainWraps.defaultCharset(charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static BufferedWriter newBufferedWriter(@Nullable Path path, @Nullable OpenOption[] options) {
        if (path == null) {
            return null;
        }
        try {
            return Files.newBufferedWriter(path, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static BufferedWriter newBufferedWriter(@Nullable Path path, @Nullable Charset charset, @Nullable OpenOption[] options) {
        if (path == null) {
            return null;
        }
        try {
            return Files.newBufferedWriter(path, CharsetPlainWraps.defaultCharset(charset), options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String readString(@Nullable Path path, @Nullable Charset charset) {
        if (path == null) {
            return null;
        }
        try {
            return Files.readString(path, CharsetPlainWraps.defaultCharset(charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String readString(@Nullable Path path) {
        if (path == null) {
            return null;
        }
        try {
            return Files.readString(path);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static List<String> readAllLines(@Nullable Path path) {
        if (path == null) {
            return null;
        }
        try {
            return Files.readAllLines(path);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static List<String> readAllLines(@Nullable Path path, @Nullable Charset charset) {
        if (path == null) {
            return null;
        }
        try {
            return Files.readAllLines(path, CharsetPlainWraps.defaultCharset(charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path writeString(@Nullable Path path, @Nullable CharSequence sequence, @Nullable OpenOption[] options) {
        if (path == null || sequence == null) {
            return null;
        }
        try {
            return Files.writeString(path, sequence, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path writeString(@Nullable Path path, @Nullable CharSequence sequence, @Nullable Charset charset, @Nullable OpenOption[] options) {
        if (path == null || sequence == null) {
            return null;
        }
        try {
            return Files.writeString(path, sequence, CharsetPlainWraps.defaultCharset(charset), options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path createFile(@Nullable Path path, @Nullable FileAttribute<?>[] attrs) {
        if (path == null) {
            return null;
        }
        try {
            return Files.createFile(path, attrs);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Path createDirectories(@Nullable Path path, @Nullable FileAttribute<?>[] attrs) {
        if (path == null) {
            return null;
        }
        try {
            return Files.createDirectories(path, attrs);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Object getAttribute(@Nullable Path path, @Nullable String attr, @Nullable LinkOption[] options) {
        if (path == null || StringUtils.isBlank(attr)) {
            return null;
        }
        try {
            return Files.getAttribute(path, attr, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static Set<PosixFilePermission> getPosixFilePermissions(@Nullable Path path, @Nullable LinkOption[] options) {
        if (path == null) {
            return null;
        }
        try {
            return Files.getPosixFilePermissions(path, options);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Path setPosixFilePermissions(@Nullable Path path, @Nullable Set<PosixFilePermission> permissions) {
        if (path == null || permissions == null) {
            return null;
        }
        try {
            return Files.setPosixFilePermissions(path, permissions);
        } catch (Exception ignored) {
        }
        return null;
    }

    @SuppressWarnings("DataFlowIssue")
    public static boolean notExists(@Nullable Path path, @Nullable LinkOption[] options) {
        if (path == null) {
            return true;
        }
        try {
            return Files.notExists(path, options);
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean isReadable(@Nullable Path path) {
        if (path == null) {
            return false;
        }
        try {
            return Files.isReadable(path);
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean isWritable(@Nullable Path path) {
        if (path == null) {
            return false;
        }
        try {
            return Files.isWritable(path);
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean isExecutable(@Nullable Path path) {
        if (path == null) {
            return false;
        }
        try {
            return Files.isExecutable(path);
        } catch (Exception ignored) {
        }
        return false;
    }
}
