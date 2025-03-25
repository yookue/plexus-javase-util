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
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;
import com.yookue.commonplexus.javaseutil.constant.RegexVariantConst;


/**
 * Utilities for Base64
 *
 * @author David Hsing
 * @see org.apache.commons.codec.binary.Base64
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class Base64UtilsWraps {
    public static String decodeToString(@Nullable String text) {
        return decodeToString(text, StandardCharsets.UTF_8);
    }

    public static String decodeToString(@Nullable String text, @Nullable Charset charset) {
        return StringUtils.isEmpty(text) ? text : StringUtils.toEncodedString(Base64.decodeBase64(text), charset);
    }

    public static String encodeToString(@Nullable String text) {
        return encodeToString(text, StandardCharsets.UTF_8);
    }

    public static String encodeToString(@Nullable String text, @Nullable Charset charset) {
        return StringUtils.isEmpty(text) ? text : Base64.encodeBase64String(text.getBytes(Charsets.toCharset(charset)));
    }

    @Nullable
    public static byte[] encodeToBytes(@Nullable String text) {
        return encodeToBytes(text, StandardCharsets.UTF_8);
    }

    @Nullable
    public static byte[] encodeToBytes(@Nullable String text, @Nullable Charset charset) {
        return StringUtils.isEmpty(text) ? null : Base64.encodeBase64(text.getBytes(Charsets.toCharset(charset)));
    }

    /**
     * @reference "http://snv.iteye.com/blog/1968740"
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

    public static boolean saveImage(@Nullable String base64, @Nullable File file) throws IOException {
        return saveImage(base64, file, false, null, null);
    }

    public static boolean saveImage(@Nullable String base64, @Nullable File file, boolean append) throws IOException {
        return saveImage(base64, file, append, null, null);
    }

    public static boolean saveImage(@Nullable String base64, @Nullable File file, boolean append, @Nullable Integer width, @Nullable Integer height) throws IOException {
        if (StringUtils.isBlank(base64) || file == null) {
            return false;
        }
        FileUtilsWraps.forceMkdirParentDeletable(file, !append);
        return saveImage(base64, FileUtilsWraps.openImageOutputStream(file), width, height);
    }

    public static boolean saveImageQuietly(@Nullable String base64, @Nullable File file) {
        return saveImageQuietly(base64, file, false, null, null);
    }

    public static boolean saveImageQuietly(@Nullable String base64, @Nullable File file, boolean append) {
        return saveImageQuietly(base64, file, append, null, null);
    }

    public static boolean saveImageQuietly(@Nullable String base64, @Nullable File file, boolean append, @Nullable Integer width, @Nullable Integer height) {
        try {
            return saveImage(base64, file, append, width, height);
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean saveImage(@Nullable String base64, @Nullable ImageOutputStream stream) throws IOException {
        return saveImage(base64, stream, null, null);
    }

    public static boolean saveImageQuietly(@Nullable String base64, @Nullable ImageOutputStream stream) {
        return saveImageQuietly(base64, stream, null, null);
    }

    public static boolean saveImageQuietly(@Nullable String base64, @Nullable ImageOutputStream stream, @Nullable Integer width, @Nullable Integer height) {
        try {
            return saveImage(base64, stream, width, height);
        } catch (Exception ignored) {
        }
        return false;
    }
}
