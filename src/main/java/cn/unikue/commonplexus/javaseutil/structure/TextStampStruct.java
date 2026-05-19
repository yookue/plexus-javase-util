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

package cn.unikue.commonplexus.javaseutil.structure;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import cn.unikue.commonplexus.javaseutil.constant.LogMessageConst;
import cn.unikue.commonplexus.javaseutil.constant.SymbolVariantConst;
import cn.unikue.commonplexus.javaseutil.util.ArrayUtilsWraps;
import cn.unikue.commonplexus.javaseutil.util.CharSequenceWraps;
import cn.unikue.commonplexus.javaseutil.util.CollectionPlainWraps;
import cn.unikue.commonplexus.javaseutil.util.ListPlainWraps;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Structure with a string list and a timestamp
 *
 * @author David Hsing
 */
@Accessors(chain = true)
@NoArgsConstructor
@Data
@Slf4j
@SuppressWarnings({"unused", "WeakerAccess", "UnusedReturnValue"})
public class TextStampStruct implements Serializable {
    private final List<String> texts = new ArrayList<>();
    private LocalDateTime timestamp = LocalDateTime.now();

    public TextStampStruct(@Nullable String... texts) {
        addText(texts);
    }

    public TextStampStruct(@Nullable Collection<String> texts) {
        addText(texts);
    }

    public TextStampStruct addText(@Nullable String... texts) {
        return addText(ArrayUtilsWraps.asList(texts));
    }

    public TextStampStruct addText(@Nullable Collection<String> texts) {
        texts = CollectionPlainWraps.newArrayListIfNull(texts);
        CollectionPlainWraps.addAllIfNotEmpty(this.texts, texts);
        return this;
    }

    public TextStampStruct addTextAt(int index, @Nullable String... texts) {
        return addTextAt(index, ArrayUtilsWraps.asList(texts));
    }

    public TextStampStruct addTextAt(int index, @Nullable Collection<String> texts) {
        texts = CollectionPlainWraps.newArrayListIfNull(texts);
        ListPlainWraps.addAllIfNotEmpty(this.texts, index, texts);
        return this;
    }

    public TextStampStruct clearTexts() {
        texts.clear();
        return this;
    }

    public String getText(int index) {
        return (CollectionPlainWraps.isEmpty(texts) || index < 0 || index >= texts.size()) ? null : texts.get(index);
    }

    public String getFirstText() {
        return getText(0);
    }

    public String getLastText() {
        return ListPlainWraps.getLast(texts);
    }

    public String getCompositeText() {
        return getCompositeText(null);
    }

    public String getCompositeText(char delimiter) {
        return getCompositeText(CharUtils.toString(delimiter));
    }

    public String getCompositeText(@Nullable String delimiter) {
        return StringUtils.join(texts, StringUtils.defaultString(delimiter));
    }

    public String getCompositeTextOrdering() {
        return getCompositeTextOrdering(null, SymbolVariantConst.ORDER_SQUARES_SPACE);
    }

    public String getCompositeTextOrdering(char delimiter) {
        return getCompositeTextOrdering(delimiter, SymbolVariantConst.ORDER_SQUARES_SPACE);
    }

    public String getCompositeTextOrdering(char delimiter, @Nullable String order) {
        return getCompositeTextOrdering(CharUtils.toString(delimiter), order);
    }

    public String getCompositeTextOrdering(@Nullable String delimiter) {
        return getCompositeTextOrdering(delimiter, SymbolVariantConst.ORDER_SQUARES_SPACE);
    }

    public String getCompositeTextOrdering(@Nullable String delimiter, @Nullable String order) {
        if (CollectionPlainWraps.isEmpty(texts)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(CollectionPlainWraps.forEachIndexingTailing(texts, (index, text) -> {
            if (StringUtils.isNotEmpty(order)) {
                try {
                    builder.append(String.format(order, index + 1));
                } catch (Exception ex) {
                    if (log.isWarnEnabled()) {
                        log.warn(LogMessageConst.EXCEPTION_OCCURRED_REASON, ex.getMessage());
                    }
                }
            }
            builder.append(text).append(StringUtils.defaultString(delimiter));
        }));
        return CharSequenceWraps.toStringIgnoreEmpty(builder);
    }

    public boolean isTextsEmpty() {
        return CollectionPlainWraps.isEmpty(texts);
    }

    public TextStampStruct removeText(int index) {
        if (CollectionPlainWraps.isIndexBound(texts, index)) {
            texts.remove(index);
        }
        return this;
    }
}
