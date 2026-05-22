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


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import jakarta.annotation.Nullable;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import cn.unikue.commonplexus.javaseutil.constant.CharVariantConst;
import cn.unikue.commonplexus.javaseutil.constant.RegexVariantConst;


/**
 * Utilities for Base64
 *
 * @author David Hsing
 *
 * @see org.apache.commons.codec.binary.Base64
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class Base64UtilsWraps {
    /**
     * Decode Base64 encoded string to UTF-8 string.
     *
     * @param text the Base64 encoded string
     * @return decoded string, or null if input is empty
     */
    public static String decodeToString(@Nullable String text) {
        return decodeToString(text, StandardCharsets.UTF_8);
    }

    /**
     * Decode Base64 encoded string to string with specified charset.
     *
     * @param text the Base64 encoded string
     * @param charset the charset to use for decoding
     * @return decoded string, or null if input is empty
     */
    public static String decodeToString(@Nullable String text, @Nullable Charset charset) {
        return StringUtils.isEmpty(text) ? text : StringUtils.toEncodedString(Base64.decodeBase64(text), charset);
    }

    /**
     * Encode string to Base64 using UTF-8 charset.
     *
     * @param text the string to encode
     * @return Base64 encoded string, or null if input is empty
     */
    public static String encodeToString(@Nullable String text) {
        return encodeToString(text, StandardCharsets.UTF_8);
    }

    /**
     * Encode string to Base64 using specified charset.
     *
     * @param text the string to encode
     * @param charset the charset to use for encoding
     * @return Base64 encoded string, or null if input is empty
     */
    public static String encodeToString(@Nullable String text, @Nullable Charset charset) {
        return StringUtils.isEmpty(text) ? text : Base64.encodeBase64String(text.getBytes(Charsets.toCharset(charset)));
    }

    /**
     * Encode string to Base64 bytes using UTF-8 charset.
     *
     * @param text the string to encode
     * @return Base64 encoded bytes, or null if input is empty
     */
    @Nullable
    public static byte[] encodeToBytes(@Nullable String text) {
        return encodeToBytes(text, StandardCharsets.UTF_8);
    }

    /**
     * Encode string to Base64 bytes using specified charset.
     *
     * @param text the string to encode
     * @param charset the charset to use for encoding
     * @return Base64 encoded bytes, or null if input is empty
     */
    @Nullable
    public static byte[] encodeToBytes(@Nullable String text, @Nullable Charset charset) {
        return StringUtils.isEmpty(text) ? null : Base64.encodeBase64(text.getBytes(Charsets.toCharset(charset)));
    }

    /**
     * Save Base64 encoded image to output stream with optional resizing.
     * Supports data URI format (e.g., "data:image/png;base64,...").
     *
     * @param base64 the Base64 encoded image data (with data URI prefix)
     * @param stream the output stream to write the image
     * @param width the target width, or null to keep original size
     * @param height the target height, or null to keep original size
     * @return true if image was saved successfully, false otherwise
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression", "JavadocDeclaration", "JavadocLinkAsPlainText"})
    public static boolean saveImage(@Nullable String base64, @Nullable ImageOutputStream stream, @Nullable Integer width, @Nullable Integer height) throws IOException {
        if (StringUtils.isBlank(base64) || stream == null || NumberUtilsWraps.anyNotPositive(width, height)) {
            return false;
        }
        Pattern pattern = Pattern.compile(RegexVariantConst.BASE64_IMAGE, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(base64);
        if (!matcher.matches() || matcher.end() >= base64.length()) {
            return false;
        }
        String formatName = StringUtils.substring(base64, base64.indexOf(CharVariantConst.SLASH, matcher.start()) + 1, base64.indexOf(CharVariantConst.SEMICOLON, matcher.start()));
        String imageData = StringUtils.substring(base64, matcher.end());
        byte[] imageBytes = Base64.decodeBase64(imageData);
        BufferedImage originImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
        BufferedImage targetImage = new BufferedImage(width, height, originImage.getType());
        if (NumberUtilsWraps.allPositive(width, height)) {
            targetImage.getGraphics().drawImage(originImage, 0, 0, width, height, null);
        } else {
            targetImage.getGraphics().drawImage(originImage, 0, 0, null);
        }
        ImageIO.write(targetImage, formatName, stream);
        return true;
    }

    /**
     * Save Base64 encoded image to file without resizing.
     *
     * @param base64 the Base64 encoded image data
     * @param file the target file
     * @return true if image was saved successfully, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public static boolean saveImage(@Nullable String base64, @Nullable File file) throws IOException {
        return saveImage(base64, file, false, null, null);
    }

    /**
     * Save Base64 encoded image to file without resizing.
     *
     * @param base64 the Base64 encoded image data
     * @param file the target file
     * @param append whether to append to existing file
     * @return true if image was saved successfully, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public static boolean saveImage(@Nullable String base64, @Nullable File file, boolean append) throws IOException {
        return saveImage(base64, file, append, null, null);
    }

    /**
     * Save Base64 encoded image to file with optional resizing.
     *
     * @param base64 the Base64 encoded image data
     * @param file the target file
     * @param append whether to append to existing file
     * @param width the target width, or null to keep original size
     * @param height the target height, or null to keep original size
     * @return true if image was saved successfully, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public static boolean saveImage(@Nullable String base64, @Nullable File file, boolean append, @Nullable Integer width, @Nullable Integer height) throws IOException {
        if (StringUtils.isBlank(base64) || file == null) {
            return false;
        }
        FileUtilsWraps.forceMkdirParent(file, !append);
        return saveImage(base64, FileUtilsWraps.openImageOutputStream(file, true), width, height);
    }

    /**
     * Save Base64 encoded image to file quietly (suppresses exceptions).
     *
     * @param base64 the Base64 encoded image data
     * @param file the target file
     * @return true if image was saved successfully, false otherwise
     */
    public static boolean saveImageQuietly(@Nullable String base64, @Nullable File file) {
        return saveImageQuietly(base64, file, false, null, null);
    }

    /**
     * Save Base64 encoded image to file quietly (suppresses exceptions).
     *
     * @param base64 the Base64 encoded image data
     * @param file the target file
     * @param append whether to append to existing file
     * @return true if image was saved successfully, false otherwise
     */
    public static boolean saveImageQuietly(@Nullable String base64, @Nullable File file, boolean append) {
        return saveImageQuietly(base64, file, append, null, null);
    }

    /**
     * Save Base64 encoded image to file quietly with optional resizing (suppresses exceptions).
     *
     * @param base64 the Base64 encoded image data
     * @param file the target file
     * @param append whether to append to existing file
     * @param width the target width, or null to keep original size
     * @param height the target height, or null to keep original size
     * @return true if image was saved successfully, false otherwise
     */
    public static boolean saveImageQuietly(@Nullable String base64, @Nullable File file, boolean append, @Nullable Integer width, @Nullable Integer height) {
        try {
            return saveImage(base64, file, append, width, height);
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * Save Base64 encoded image to output stream without resizing.
     *
     * @param base64 the Base64 encoded image data
     * @param stream the output stream to write the image
     * @return true if image was saved successfully, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public static boolean saveImage(@Nullable String base64, @Nullable ImageOutputStream stream) throws IOException {
        return saveImage(base64, stream, null, null);
    }

    /**
     * Save Base64 encoded image to output stream quietly (suppresses exceptions).
     *
     * @param base64 the Base64 encoded image data
     * @param stream the output stream to write the image
     * @return true if image was saved successfully, false otherwise
     */
    public static boolean saveImageQuietly(@Nullable String base64, @Nullable ImageOutputStream stream) {
        return saveImageQuietly(base64, stream, null, null);
    }

    /**
     * Save Base64 encoded image to output stream quietly with optional resizing (suppresses exceptions).
     *
     * @param base64 the Base64 encoded image data
     * @param stream the output stream to write the image
     * @param width the target width, or null to keep original size
     * @param height the target height, or null to keep original size
     * @return true if image was saved successfully, false otherwise
     */
    public static boolean saveImageQuietly(@Nullable String base64, @Nullable ImageOutputStream stream, @Nullable Integer width, @Nullable Integer height) {
        try {
            return saveImage(base64, stream, width, height);
        } catch (Exception ignored) {
        }
        return false;
    }
}
