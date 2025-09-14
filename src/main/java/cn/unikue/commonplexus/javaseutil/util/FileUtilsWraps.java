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


import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import cn.unikue.commonplexus.javaseutil.function.IgnorableFailable;


/**
 * Utilities for {@link org.apache.commons.io.FileUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.io.FileUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class FileUtilsWraps {
    public static boolean cleanDirectory(@Nullable File directory) {
        if (directory == null) {
            return false;
        }
        try {
            FileUtils.cleanDirectory(directory);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean copyDirectory(@Nullable File srcDir, @Nullable File destDir) {
        return copyDirectory(srcDir, destDir, null, true);
    }

    public static boolean copyDirectory(@Nullable File srcDir, @Nullable File destDir, boolean preserveFileDate) {
        return copyDirectory(srcDir, destDir, null, preserveFileDate);
    }

    public static boolean copyDirectory(@Nullable File srcDir, @Nullable File destDir, @Nullable FileFilter fileFilter) {
        return copyDirectory(srcDir, destDir, fileFilter, true);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyDirectory(@Nullable File srcDir, @Nullable File destDir, @Nullable FileFilter fileFilter, boolean preserveFileDate) {
        if (ObjectUtils.anyNull(srcDir, destDir)) {
            return false;
        }
        try {
            FileUtils.copyDirectory(srcDir, destDir, fileFilter, preserveFileDate);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyDirectory(@Nullable File srcDir, @Nullable File destDir, @Nullable FileFilter fileFilter, boolean preserveFileDate, @Nullable CopyOption... copyOptions) {
        if (ObjectUtils.anyNull(srcDir, destDir, copyOptions)) {
            return false;
        }
        try {
            FileUtils.copyDirectory(srcDir, destDir, fileFilter, preserveFileDate, copyOptions);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyDirectoryToDirectory(@Nullable File srcDir, @Nullable File destDir) {
        if (ObjectUtils.anyNull(srcDir, destDir)) {
            return false;
        }
        try {
            FileUtils.copyDirectoryToDirectory(srcDir, destDir);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean copyFile(@Nullable File srcFile, @Nullable File destFile) {
        return copyFile(srcFile, destFile, true);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyFile(@Nullable File srcFile, @Nullable File destFile, boolean preserveFileDate) {
        if (ObjectUtils.anyNull(srcFile, destFile)) {
            return false;
        }
        try {
            FileUtils.copyFile(srcFile, destFile, preserveFileDate);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyFile(@Nullable File srcFile, @Nullable File destFile, boolean preserveFileDate, @Nullable CopyOption... copyOptions) {
        if (ObjectUtils.anyNull(srcFile, destFile, copyOptions)) {
            return false;
        }
        try {
            FileUtils.copyFile(srcFile, destFile, preserveFileDate, copyOptions);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyFile(@Nullable File srcFile, @Nullable File destFile, @Nullable CopyOption... copyOptions) {
        if (ObjectUtils.anyNull(srcFile, destFile, copyOptions)) {
            return false;
        }
        try {
            FileUtils.copyFile(srcFile, destFile, copyOptions);
            return true;
        } catch (Exception ignored) {
        }
        return false;
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

    public static boolean copyFileToDirectory(@Nullable File srcFile, @Nullable File destDir) {
        return copyFileToDirectory(srcFile, destDir, true);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyFileToDirectory(@Nullable File srcFile, @Nullable File destDir, boolean preserveFileDate) {
        if (ObjectUtils.anyNull(srcFile, destDir)) {
            return false;
        }
        try {
            FileUtils.copyFileToDirectory(srcFile, destDir, preserveFileDate);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyInputStreamToFile(@Nullable InputStream src, @Nullable File dest) {
        if (ObjectUtils.anyNull(src, dest)) {
            return false;
        }
        try {
            FileUtils.copyInputStreamToFile(src, dest);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyToDirectory(@Nullable File srcFile, @Nullable File destDir) {
        if (ObjectUtils.anyNull(srcFile, destDir)) {
            return false;
        }
        try {
            FileUtils.copyToDirectory(srcFile, destDir);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyToDirectory(@Nullable Iterable<File> srcFiles, @Nullable File destDir) {
        if (ObjectUtils.anyNull(srcFiles, destDir)) {
            return false;
        }
        try {
            FileUtils.copyToDirectory(srcFiles, destDir);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyToFile(@Nullable InputStream src, @Nullable File dest) {
        if (ObjectUtils.anyNull(src, dest)) {
            return false;
        }
        try {
            FileUtils.copyToFile(src, dest);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyUrlToFile(@Nullable URL src, @Nullable File dest) {
        if (ObjectUtils.anyNull(src, dest)) {
            return false;
        }
        try {
            FileUtils.copyURLToFile(src, dest);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean copyUrlToFile(@Nullable URL src, @Nullable File dest, int connectionTimeoutMillis, int readTimeoutMillis) {
        if (ObjectUtils.anyNull(src, dest)) {
            return false;
        }
        try {
            FileUtils.copyURLToFile(src, dest, connectionTimeoutMillis, readTimeoutMillis);
            return true;
        } catch (Exception ignored) {
        }
        return false;
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

    public static boolean deleteDirectory(@Nullable File directory) {
        if (directory == null) {
            return false;
        }
        try {
            FileUtils.deleteDirectory(directory);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean exists(@Nullable File file) {
        return file != null && file.exists();
    }

    public static boolean exists(@Nullable String path) {
        return StringUtils.isNotBlank(path) && new File(path).exists();
    }

    public static boolean forceDelete(@Nullable File file) {
        if (file == null) {
            return false;
        }
        try {
            FileUtils.forceDelete(file);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean forceDeleteOnExit(@Nullable File file) {
        if (file == null) {
            return false;
        }
        try {
            FileUtils.forceDeleteOnExit(file);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean forceMkdir(@Nullable File directory) {
        if (directory == null) {
            return false;
        }
        try {
            FileUtils.forceMkdir(directory);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean forceMkdirParent(@Nullable File file) {
        if (file == null) {
            return false;
        }
        try {
            FileUtils.forceMkdirParent(file);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean moveDirectory(@Nullable File srcDir, @Nullable File destDir) {
        if (ObjectUtils.anyNull(srcDir, destDir)) {
            return false;
        }
        try {
            FileUtils.moveDirectory(srcDir, destDir);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean moveDirectoryToDirectory(@Nullable File srcDir, @Nullable File destDir, boolean createDestDir) {
        if (ObjectUtils.anyNull(srcDir, destDir)) {
            return false;
        }
        try {
            FileUtils.moveDirectoryToDirectory(srcDir, destDir, createDestDir);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean moveFile(@Nullable File srcFile, @Nullable File destFile) {
        return moveFile(srcFile, destFile, StandardCopyOption.COPY_ATTRIBUTES);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean moveFile(@Nullable File srcFile, @Nullable File destFile, @Nullable CopyOption... copyOptions) {
        if (ObjectUtils.anyNull(srcFile, destFile, copyOptions)) {
            return false;
        }
        try {
            FileUtils.moveFile(srcFile, destFile, copyOptions);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean moveFileToDirectory(@Nullable File srcFile, @Nullable File destDir, boolean createDestDir) {
        if (ObjectUtils.anyNull(srcFile, destDir)) {
            return false;
        }
        try {
            FileUtils.moveFileToDirectory(srcFile, destDir, createDestDir);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean moveToDirectory(@Nullable File srcDir, @Nullable File destDir, boolean createDestDir) {
        if (ObjectUtils.anyNull(srcDir, destDir)) {
            return false;
        }
        try {
            FileUtils.moveToDirectory(srcDir, destDir, createDestDir);
            return true;
        } catch (Exception ignored) {
        }
        return false;
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

    public static boolean writeCharSequence(@Nullable File file, @Nullable CharSequence data, @Nullable Charset charset) {
        return writeCharSequence(file, data, charset, false);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean writeCharSequence(@Nullable File file, @Nullable CharSequence data, @Nullable Charset charset, boolean append) {
        if (ObjectUtils.anyNull(file, data)) {
            return false;
        }
        try {
            FileUtils.write(file, data, charset, append);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean writeCharSequence(@Nullable File file, @Nullable CharSequence data, @Nullable String charset) {
        return writeCharSequence(file, data, charset, false);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean writeCharSequence(@Nullable File file, @Nullable CharSequence data, @Nullable String charset, boolean append) {
        if (ObjectUtils.anyNull(file, data)) {
            return false;
        }
        try {
            FileUtils.write(file, data, CharsetPlainWraps.defaultCharsetName(charset), append);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean writeByteArray(@Nullable File file, @Nullable byte[] data) {
        return writeByteArray(file, data, false);
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean writeByteArray(@Nullable File file, @Nullable byte[] data, boolean append) {
        if (file == null || ArrayUtils.isEmpty(data)) {
            return false;
        }
        try {
            FileUtils.writeByteArrayToFile(file, data, append);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean writeByteArray(@Nullable File file, @Nullable byte[] data, int offset, int length) {
        return writeByteArray(file, data, 0, ArrayUtils.getLength(data), false);
    }

    public static boolean writeByteArray(@Nullable File file, @Nullable byte[] data, int offset, int length, boolean append) {
        if (file == null || ArrayUtils.isEmpty(data) || offset < 0 || length <= 0) {
            return false;
        }
        try {
            FileUtils.writeByteArrayToFile(file, data, offset, length, append);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean writeLines(@Nullable File file, @Nullable Collection<?> lines) {
        return writeLines(file, lines, null, false);
    }

    public static boolean writeLines(@Nullable File file, @Nullable Collection<?> lines, boolean append) {
        return writeLines(file, lines, null, append);
    }

    public static boolean writeLines(@Nullable File file, @Nullable Collection<?> lines, @Nullable String lineEnding) {
        return writeLines(file, lines, lineEnding, false);
    }

    public static boolean writeLines(@Nullable File file, @Nullable Collection<?> lines, @Nullable String lineEnding, boolean append) {
        if (file == null || CollectionPlainWraps.isEmpty(lines)) {
            return false;
        }
        try {
            FileUtils.writeLines(file, lines, lineEnding, append);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean writeLines(@Nullable File file, @Nullable String charset, @Nullable Collection<?> lines) {
        return writeLines(file, charset, lines, null, false);
    }

    public static boolean writeLines(@Nullable File file, @Nullable String charset, @Nullable Collection<?> lines, boolean append) {
        return writeLines(file, charset, lines, null, append);
    }

    public static boolean writeLines(@Nullable File file, @Nullable String charset, @Nullable Collection<?> lines, String lineEnding) {
        return writeLines(file, charset, lines, lineEnding, false);
    }

    public static boolean writeLines(@Nullable File file, @Nullable String charset, @Nullable Collection<?> lines, String lineEnding, boolean append) {
        if (file == null || CollectionPlainWraps.isEmpty(lines)) {
            return false;
        }
        try {
            FileUtils.writeLines(file, CharsetPlainWraps.defaultCharsetName(charset), lines, lineEnding, append);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean writeStream(@Nullable File file, @Nullable InputStream inputStream) {
        if (ObjectUtils.anyNull(file, inputStream) || !file.isFile() || !file.canWrite()) {
            return false;
        }
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] bufferRead = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(bufferRead)) != -1) {
                outputStream.write(bufferRead, 0, bytesRead);
            }
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean writeString(@Nullable File file, @Nullable String data, @Nullable Charset charset) {
        return writeString(file, data, charset, false);
    }

    public static boolean writeString(@Nullable File file, @Nullable String data, @Nullable Charset charset, boolean append) {
        if (file == null || StringUtils.isEmpty(data)) {
            return false;
        }
        try {
            FileUtils.writeStringToFile(file, data, charset, append);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean writeString(@Nullable File file, @Nullable String data, @Nullable String charset) {
        return writeString(file, data, charset, false);
    }

    public static boolean writeString(@Nullable File file, @Nullable String data, @Nullable String charset, boolean append) {
        if (file == null || StringUtils.isEmpty(data)) {
            return false;
        }
        try {
            FileUtils.writeStringToFile(file, data, CharsetPlainWraps.defaultCharsetName(charset), append);
            return true;
        } catch (Exception ignored) {
        }
        return false;
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
     * @param dest The destination file or folder
     * @param deleteIfExists When {@code dest} file exists, if true means this will delete it first; while false means not to delete
     *
     * @see org.apache.commons.io.FileUtils#forceMkdirParent
     */
    public static boolean forceMkdirParent(@Nullable File dest, boolean deleteIfExists) {
        if (dest == null) {
            return false;
        }
        try {
            if (!dest.exists()) {
                FileUtils.forceMkdirParent(dest);
            } else {
                if (deleteIfExists) {
                    if (dest.isFile()) {
                        FileUtils.delete(dest);
                    } else if (dest.isDirectory()) {
                        FileUtils.deleteDirectory(dest);
                    }
                }
            }
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @Nullable
    public static FileImageInputStream openImageInputStream(@Nullable File file) {
        try {
            return (file == null || file.isDirectory()) ? null : new FileImageInputStream(file);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * @see org.apache.commons.io.FileUtils#openOutputStream(java.io.File, boolean)
     */
    @Nullable
    public static FileImageOutputStream openImageOutputStream(@Nullable File file, boolean deleteIfExists) {
        if (file == null || file.isDirectory()) {
            return null;
        }
        try {
            if (file.exists()) {
                if (deleteIfExists) {
                    FileUtils.delete(file);
                }
            } else {
                FileUtils.createParentDirectories(file);
            }
            return new FileImageOutputStream(file);
        } catch (Exception ignored) {
        }
        return null;
    }
}
