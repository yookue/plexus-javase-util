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


import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.function.IgnorableFailable;


/**
 * Utilities for {@link org.apache.commons.io.FileUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.io.FileUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class FileUtilsWraps {
    public static void cleanDirectory(@Nullable File directory) {
        if (directory == null) {
            return;
        }
        try {
            FileUtils.cleanDirectory(directory);
        } catch (Exception ignored) {
        }
    }

    public static void copyDirectory(@Nullable File srcDir, @Nullable File destDir) {
        copyDirectory(srcDir, destDir, null, true);
    }

    public static void copyDirectory(@Nullable File srcDir, @Nullable File destDir, boolean preserveFileDate) {
        copyDirectory(srcDir, destDir, null, preserveFileDate);
    }

    public static void copyDirectory(@Nullable File srcDir, @Nullable File destDir, @Nullable FileFilter fileFilter) {
        copyDirectory(srcDir, destDir, fileFilter, true);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyDirectory(@Nullable File srcDir, @Nullable File destDir, @Nullable FileFilter fileFilter, boolean preserveFileDate) {
        if (ObjectUtils.anyNull(srcDir, destDir)) {
            return;
        }
        try {
            FileUtils.copyDirectory(srcDir, destDir, fileFilter, preserveFileDate);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyDirectory(@Nullable File srcDir, @Nullable File destDir, @Nullable FileFilter fileFilter, boolean preserveFileDate, @Nullable CopyOption... copyOptions) {
        if (ObjectUtils.anyNull(srcDir, destDir, copyOptions)) {
            return;
        }
        try {
            FileUtils.copyDirectory(srcDir, destDir, fileFilter, preserveFileDate, copyOptions);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyDirectoryToDirectory(@Nullable File srcDir, @Nullable File destDir) {
        if (ObjectUtils.anyNull(srcDir, destDir)) {
            return;
        }
        try {
            FileUtils.copyDirectoryToDirectory(srcDir, destDir);
        } catch (Exception ignored) {
        }
    }

    public static void copyFile(@Nullable File srcFile, @Nullable File destFile) {
        copyFile(srcFile, destFile, true);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyFile(@Nullable File srcFile, @Nullable File destFile, boolean preserveFileDate) {
        if (ObjectUtils.anyNull(srcFile, destFile)) {
            return;
        }
        try {
            FileUtils.copyFile(srcFile, destFile, preserveFileDate);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyFile(@Nullable File srcFile, @Nullable File destFile, boolean preserveFileDate, @Nullable CopyOption... copyOptions) {
        if (ObjectUtils.anyNull(srcFile, destFile, copyOptions)) {
            return;
        }
        try {
            FileUtils.copyFile(srcFile, destFile, preserveFileDate, copyOptions);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyFile(@Nullable File srcFile, @Nullable File destFile, @Nullable CopyOption... copyOptions) {
        if (ObjectUtils.anyNull(srcFile, destFile, copyOptions)) {
            return;
        }
        try {
            FileUtils.copyFile(srcFile, destFile, copyOptions);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static long copyFile(@Nullable File src, @Nullable OutputStream dest) {
        if (ObjectUtils.anyNull(src, dest)) {
            return 0L;
        }
        try {
            return FileUtils.copyFile(src, dest);
        } catch (Exception ignored) {
        }
        return 0L;
    }

    public static void copyFileToDirectory(@Nullable File srcFile, @Nullable File destDir) {
        copyFileToDirectory(srcFile, destDir, true);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyFileToDirectory(@Nullable File srcFile, @Nullable File destDir, boolean preserveFileDate) {
        if (ObjectUtils.anyNull(srcFile, destDir)) {
            return;
        }
        try {
            FileUtils.copyFileToDirectory(srcFile, destDir, preserveFileDate);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyInputStreamToFile(@Nullable InputStream src, @Nullable File dest) {
        if (ObjectUtils.anyNull(src, dest)) {
            return;
        }
        try {
            FileUtils.copyInputStreamToFile(src, dest);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyToDirectory(@Nullable File srcFile, @Nullable File destDir) {
        if (ObjectUtils.anyNull(srcFile, destDir)) {
            return;
        }
        try {
            FileUtils.copyToDirectory(srcFile, destDir);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyToDirectory(@Nullable Iterable<File> srcFiles, @Nullable File destDir) {
        if (ObjectUtils.anyNull(srcFiles, destDir)) {
            return;
        }
        try {
            FileUtils.copyToDirectory(srcFiles, destDir);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyToFile(@Nullable InputStream src, @Nullable File dest) {
        if (ObjectUtils.anyNull(src, dest)) {
            return;
        }
        try {
            FileUtils.copyToFile(src, dest);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyUrlToFile(@Nullable URL src, @Nullable File dest) {
        if (ObjectUtils.anyNull(src, dest)) {
            return;
        }
        try {
            FileUtils.copyURLToFile(src, dest);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void copyUrlToFile(@Nullable URL src, @Nullable File dest, int connectionTimeoutMillis, int readTimeoutMillis) {
        if (ObjectUtils.anyNull(src, dest)) {
            return;
        }
        try {
            FileUtils.copyURLToFile(src, dest, connectionTimeoutMillis, readTimeoutMillis);
        } catch (Exception ignored) {
        }
    }

    @Nullable
    public static File createParentDirectories(@Nullable File file) {
        if (file == null) {
            return null;
        }
        try {
            return FileUtils.createParentDirectories(file);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static void deleteDirectoryQuietly(@Nullable File directory) {
        if (directory == null) {
            return;
        }
        try {
            FileUtils.deleteDirectory(directory);
        } catch (Exception ignored) {
        }
    }

    public static boolean exists(@Nullable File file) {
        return file != null && file.exists();
    }

    public static boolean exists(@Nullable String path) {
        return StringUtils.isNotBlank(path) && new File(path).exists();
    }

    public static void forceDeleteQuietly(@Nullable File file) {
        if (file == null) {
            return;
        }
        try {
            FileUtils.forceDelete(file);
        } catch (Exception ignored) {
        }
    }

    public static void forceDeleteOnExitQuietly(@Nullable File file) {
        if (file == null) {
            return;
        }
        try {
            FileUtils.forceDeleteOnExit(file);
        } catch (Exception ignored) {
        }
    }

    public static void forceMkdirQuietly(@Nullable File directory) {
        if (directory == null) {
            return;
        }
        try {
            FileUtils.forceMkdir(directory);
        } catch (Exception ignored) {
        }
    }

    public static void forceMkdirParent(@Nullable File file) {
        if (file == null) {
            return;
        }
        try {
            FileUtils.forceMkdirParent(file);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void moveDirectory(@Nullable File srcDir, @Nullable File destDir) {
        if (ObjectUtils.anyNull(srcDir, destDir)) {
            return;
        }
        try {
            FileUtils.moveDirectory(srcDir, destDir);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void moveDirectoryToDirectory(@Nullable File srcDir, @Nullable File destDir, boolean createDestDir) {
        if (ObjectUtils.anyNull(srcDir, destDir)) {
            return;
        }
        try {
            FileUtils.moveDirectoryToDirectory(srcDir, destDir, createDestDir);
        } catch (Exception ignored) {
        }
    }

    public static void moveFile(@Nullable File srcFile, @Nullable File destFile) {
        moveFile(srcFile, destFile, StandardCopyOption.COPY_ATTRIBUTES);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void moveFile(@Nullable File srcFile, @Nullable File destFile, @Nullable CopyOption... copyOptions) {
        if (ObjectUtils.anyNull(srcFile, destFile, copyOptions)) {
            return;
        }
        try {
            FileUtils.moveFile(srcFile, destFile, copyOptions);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void moveFileToDirectory(@Nullable File srcFile, @Nullable File destDir, boolean createDestDir) {
        if (ObjectUtils.anyNull(srcFile, destDir)) {
            return;
        }
        try {
            FileUtils.moveFileToDirectory(srcFile, destDir, createDestDir);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void moveToDirectory(@Nullable File srcDir, @Nullable File destDir, boolean createDestDir) {
        if (ObjectUtils.anyNull(srcDir, destDir)) {
            return;
        }
        try {
            FileUtils.moveToDirectory(srcDir, destDir, createDestDir);
        } catch (Exception ignored) {
        }
    }

    @Nullable
    public static FileInputStream openInputStream(@Nullable File file) {
        if (file == null) {
            return null;
        }
        try {
            return FileUtils.openInputStream(file);
        } catch (Exception ignored) {
        } return null;
    }

    @Nullable
    public static FileOutputStream openOutputStream(@Nullable File file) {
        return openOutputStream(file, false);
    }

    @Nullable
    public static FileOutputStream openOutputStream(@Nullable File file, boolean append) {
        if (file == null) {
            return null;
        }
        try {
            return FileUtils.openOutputStream(file, append);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static byte[] readFileToByteArray(@Nullable File file) {
        if (file == null) {
            return null;
        }
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String readFileToString(@Nullable File file, @Nullable Charset charset) {
        if (file == null) {
            return null;
        }
        try {
            return FileUtils.readFileToString(file, charset);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String readFileToString(@Nullable File file, @Nullable String charset) {
        if (file == null) {
            return null;
        }
        try {
            return FileUtils.readFileToString(file, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static List<String> readLines(@Nullable File file, @Nullable Charset charset) {
        if (file == null) {
            return null;
        }
        try {
            return FileUtils.readLines(file, CharsetPlainWraps.defaultCharset(charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static List<String> readLines(@Nullable File file, @Nullable String charset) {
        if (file == null) {
            return null;
        }
        try {
            return FileUtils.readLines(file, CharsetPlainWraps.defaultCharsetName(charset));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nonnull
    public static Stream<File> streamFiles(@Nullable File directory, boolean recursive, @Nullable String... extensions) {
        if (directory == null || ArrayUtils.isEmpty(extensions)) {
            return Stream.empty();
        }
        try {
            return FileUtils.streamFiles(directory, recursive, extensions);
        } catch (Exception ignored) {
        }
        return Stream.empty();
    }

    public static void writeCharSequence(@Nullable File file, @Nullable CharSequence data, @Nullable Charset charset) {
        writeCharSequence(file, data, charset, false);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void writeCharSequence(@Nullable File file, @Nullable CharSequence data, @Nullable Charset charset, boolean append) {
        if (ObjectUtils.anyNull(file, data)) {
            return;
        }
        try {
            FileUtils.write(file, data, charset, append);
        } catch (Exception ignored) {
        }
    }

    public static void writeCharSequence(@Nullable File file, @Nullable CharSequence data, @Nullable String charset) {
        writeCharSequence(file, data, charset, false);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void writeCharSequence(@Nullable File file, @Nullable CharSequence data, @Nullable String charset, boolean append) {
        if (ObjectUtils.anyNull(file, data)) {
            return;
        }
        try {
            FileUtils.write(file, data, CharsetPlainWraps.defaultCharsetName(charset), append);
        } catch (Exception ignored) {
        }
    }

    public static void writeByteArray(@Nullable File file, @Nullable byte[] data) {
        writeByteArray(file, data, false);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void writeByteArray(@Nullable File file, @Nullable byte[] data, boolean append) {
        if (file == null || ArrayUtils.isEmpty(data)) {
            return;
        }
        try {
            FileUtils.writeByteArrayToFile(file, data, append);
        } catch (Exception ignored) {
        }
    }

    public static void writeByteArray(@Nullable File file, @Nullable byte[] data, int offset, int length) {
        writeByteArray(file, data, 0, ArrayUtils.getLength(data), false);
    }

    public static void writeByteArray(@Nullable File file, @Nullable byte[] data, int offset, int length, boolean append) {
        if (file == null || ArrayUtils.isEmpty(data) || offset < 0 || length <= 0) {
            return;
        }
        try {
            FileUtils.writeByteArrayToFile(file, data, offset, length, append);
        } catch (Exception ignored) {
        }
    }

    public static void writeLines(@Nullable File file, @Nullable Collection<?> lines) {
        writeLines(file, lines, null, false);
    }

    public static void writeLines(@Nullable File file, @Nullable Collection<?> lines, boolean append) {
        writeLines(file, lines, null, append);
    }

    public static void writeLines(@Nullable File file, @Nullable Collection<?> lines, @Nullable String lineEnding) {
        writeLines(file, lines, lineEnding, false);
    }

    public static void writeLines(@Nullable File file, @Nullable Collection<?> lines, @Nullable String lineEnding, boolean append) {
        if (file == null || CollectionPlainWraps.isEmpty(lines)) {
            return;
        }
        try {
            FileUtils.writeLines(file, lines, lineEnding, append);
        } catch (Exception ignored) {
        }
    }

    public static void writeLines(@Nullable File file, @Nullable String charset, @Nullable Collection<?> lines) {
        writeLines(file, charset, lines, null, false);
    }

    public static void writeLines(@Nullable File file, @Nullable String charset, @Nullable Collection<?> lines, boolean append) {
        writeLines(file, charset, lines, null, append);
    }

    public static void writeLines(@Nullable File file, @Nullable String charset, @Nullable Collection<?> lines, String lineEnding) {
        writeLines(file, charset, lines, lineEnding, false);
    }

    public static void writeLines(@Nullable File file, @Nullable String charset, @Nullable Collection<?> lines, String lineEnding, boolean append) {
        if (file == null || CollectionPlainWraps.isEmpty(lines)) {
            return;
        }
        try {
            FileUtils.writeLines(file, CharsetPlainWraps.defaultCharsetName(charset), lines, lineEnding, append);
        } catch (Exception ignored) {
        }
    }

    public static void writeString(@Nullable File file, @Nullable String data, @Nullable Charset charset) {
        writeString(file, data, charset, false);
    }

    public static void writeString(@Nullable File file, @Nullable String data, @Nullable Charset charset, boolean append) {
        if (file == null || StringUtils.isEmpty(data)) {
            return;
        }
        try {
            FileUtils.writeStringToFile(file, data, charset, append);
        } catch (Exception ignored) {
        }
    }

    public static void writeString(@Nullable File file, @Nullable String data, @Nullable String charset) {
        writeString(file, data, charset, false);
    }

    public static void writeString(@Nullable File file, @Nullable String data, @Nullable String charset, boolean append) {
        if (file == null || StringUtils.isEmpty(data)) {
            return;
        }
        try {
            FileUtils.writeStringToFile(file, data, CharsetPlainWraps.defaultCharsetName(charset), append);
        } catch (Exception ignored) {
        }
    }

    public static boolean directoryContainsAll(@Nullable File directory, @Nullable File... children) {
        return directoryContainsAll(directory, ArrayUtilsWraps.asList(children));
    }

    public static boolean directoryContainsAll(@Nullable File directory, @Nullable Collection<File> children) {
        if (directory == null || CollectionPlainWraps.isEmpty(children)) {
            return false;
        }
        return children.stream().allMatch(IgnorableFailable.asPredicate(child -> FileUtils.directoryContains(directory, child)));
    }

    public static boolean directoryContainsAny(@Nullable File directory, @Nullable File... children) {
        return directoryContainsAny(directory, ArrayUtilsWraps.asList(children));
    }

    public static boolean directoryContainsAny(@Nullable File directory, @Nullable Collection<File> children) {
        return directory != null && CollectionPlainWraps.isNotEmpty(children) && children.stream().anyMatch(IgnorableFailable.asPredicate(child -> FileUtils.directoryContains(directory, child)));
    }

    /**
     * Force creating parents with ability to delete {@code dest} file
     *
     * @param dest the destination file or folder
     * @param deleteIfExists when {@code dest} file exists, if true means this will delete it first; while false means not to delete
     *
     * @see FileUtils#forceMkdirParent
     */
    public static void forceMkdirParentDeletable(@Nullable File dest, boolean deleteIfExists) throws IOException {
        if (dest == null) {
            return;
        }
        if (!dest.exists()) {
            FileUtils.forceMkdirParent(dest);
            return;
        }
        if (deleteIfExists) {
            BasicFileAttributes attributes = Files.readAttributes(dest.toPath(), BasicFileAttributes.class);
            if (attributes.isRegularFile()) {
                FileUtils.delete(dest);
            } else if (attributes.isDirectory()) {
                FileUtils.deleteDirectory(dest);
            }
            return;
        }
        throw new FileAlreadyExistsException(String.format("File '%s' already exists", dest.getAbsolutePath()));
    }

    public static void forceMkdirParentDeletableQuietly(@Nullable File dest, boolean deleteIfExists) {
        try {
            forceMkdirParentDeletable(dest, deleteIfExists);
        } catch (Exception ignored) {
        }
    }

    @Nullable
    public static FileImageInputStream openImageInputStream(@Nullable File file) throws IOException {
        return (file == null) ? null : new FileImageInputStream(file);
    }

    /**
     * @see org.apache.commons.io.FileUtils#openOutputStream(java.io.File, boolean)
     */
    @Nullable
    public static FileImageOutputStream openImageOutputStream(@Nullable File file) throws IOException {
        if (file == null) {
            return null;
        }
        if (file.exists()) {
            if (!file.isFile()) {
                throw new IllegalStateException("The destination must be a file");    // $NON-NLS-1$
            }
            if (!file.canWrite()) {
                throw new IllegalStateException("The destination must be writeable");    // $NON-NLS-1$
            }
        } else {
            FileUtils.createParentDirectories(file);
        }
        return new FileImageOutputStream(file);
    }
}
