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

package com.yookue.commonplexus.javaseutil.locale;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**
 * Utilities for Chinese pinyin
 *
 * @author David Hsing
 * @reference "https://github.com/belerweb/pinyin4j"
 * @reference "https://github.com/stuxuhai/jpinyin"
 * @reference "http://yjck.iteye.com/blog/816107"
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public abstract class ChinesePinyinUtils {
    @Nullable
    public static String getFirstSyllables(@Nullable String text) throws BadHanyuPinyinOutputFormatCombination {
        return getFirstSyllables(text, true, null);
    }

    @Nullable
    public static String getFirstSyllables(@Nullable String text, boolean uppercase) throws BadHanyuPinyinOutputFormatCombination {
        return getFirstSyllables(text, uppercase, null);
    }

    @Nullable
    public static String getFirstSyllables(@Nullable String text, boolean uppercase, @Nullable PinyinCallback callback) throws BadHanyuPinyinOutputFormatCombination {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(uppercase ? HanyuPinyinCaseType.UPPERCASE : HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        return getExtraSyllables(text, format, (pinyin, origin, polyphony, index) -> {
            if (callback != null) {
                return StringUtils.substring(callback.process(pinyin, origin, polyphony, index), 0, 1);
            }
            if (!polyphony || index == 0) {
                return StringUtils.substring(pinyin, 0, 1);
            }
            return null;
        });
    }

    @Nullable
    public static String getFirstSyllablesQuietly(@Nullable String text) {
        return getFirstSyllablesQuietly(text, true, null);
    }

    @Nullable
    public static String getFirstSyllablesQuietly(@Nullable String text, boolean uppercase) {
        return getFirstSyllablesQuietly(text, uppercase, null);
    }

    @Nullable
    public static String getFirstSyllablesQuietly(@Nullable String text, boolean uppercase, @Nullable PinyinCallback callback) {
        try {
            return getFirstSyllables(text, uppercase, callback);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String getFullSyllables(@Nullable String text) throws BadHanyuPinyinOutputFormatCombination {
        return getFullSyllables(text, null, null);
    }

    @Nullable
    public static String getFullSyllables(@Nullable String text, @Nullable HanyuPinyinOutputFormat format) throws BadHanyuPinyinOutputFormatCombination {
        return getFullSyllables(text, format, null);
    }

    @Nullable
    public static String getFullSyllables(@Nullable String text, @Nullable HanyuPinyinOutputFormat format, @Nullable PinyinCallback callback) throws BadHanyuPinyinOutputFormatCombination {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        boolean nullFormat = (format == null);
        return getExtraSyllables(text, format, (pinyin, origin, polyphony, index) -> {
            if (callback != null) {
                return callback.process(pinyin, origin, polyphony, index);
            }
            if (!polyphony || index == 0) {
                return nullFormat ? WordUtils.capitalizeFully(pinyin) : pinyin;
            }
            return null;
        });
    }

    @Nullable
    public static String getFullSyllablesQuietly(@Nullable String text) {
        return getFullSyllablesQuietly(text, null, null);
    }

    @Nullable
    public static String getFullSyllablesQuietly(@Nullable String text, HanyuPinyinOutputFormat format) {
        return getFullSyllablesQuietly(text, format, null);
    }

    @Nullable
    public static String getFullSyllablesQuietly(@Nullable String text, HanyuPinyinOutputFormat format, @Nullable PinyinCallback callback) {
        try {
            return getFullSyllables(text, format, callback);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String getExtraSyllables(@Nullable String text, @Nullable HanyuPinyinOutputFormat format, @Nullable PinyinCallback callback) throws BadHanyuPinyinOutputFormatCombination {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        if (format == null) {
            format = new HanyuPinyinOutputFormat();
            format.setVCharType(HanyuPinyinVCharType.WITH_V);
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        }
        StringBuilder result = new StringBuilder();
        char[] inputChars = text.toCharArray();
        for (char inputChar : inputChars) {
            if (inputChar <= 128) {
                result.append(inputChar);
                continue;
            }
            String[] sections = PinyinHelper.toHanyuPinyinStringArray(inputChar, format);
            if (ArrayUtils.isEmpty(sections)) {
                continue;
            }
            if (callback == null) {
                String section = ArrayUtils.get(sections, 0);
                if (StringUtils.isNotEmpty(section)) {
                    result.append(section);
                }
                continue;
            }
            int length = ArrayUtils.getLength(sections);
            for (int i = 0; i < length; i++) {
                String section = callback.process(sections[i], inputChar, length > 1, i);
                if (StringUtils.isNotEmpty(section)) {
                    result.append(section);
                }
            }
        }
        return (result.length() <= 0) ? null : result.toString();
    }

    @Nullable
    public static String getExtraSyllablesQuietly(@Nullable String text, HanyuPinyinOutputFormat format, @Nullable PinyinCallback callback) {
        try {
            return getExtraSyllables(text, format, callback);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Callback for pinyin
     */
    @FunctionalInterface
    public interface PinyinCallback {
        /**
         * Returns processed pinyin spells, for customization
         *
         * @param pinyin the pinyin result of {@code origin} char
         * @param origin the source char of inputs
         * @param polyphony indicates that {@code origin} char is polyphony
         * @param index index of polyphony
         *
         * @return processed pinyin spells, for customization
         */
        @Nullable
        String process(@Nonnull String pinyin, char origin, boolean polyphony, int index);
    }
}
