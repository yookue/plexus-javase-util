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

package com.yookue.commonplexus.javaseutil.structure;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.SymbolVariantConst;
import com.yookue.commonplexus.javaseutil.util.ArrayUtilsWraps;
import com.yookue.commonplexus.javaseutil.util.CharSequenceWraps;
import com.yookue.commonplexus.javaseutil.util.CollectionPlainWraps;
import com.yookue.commonplexus.javaseutil.util.ListPlainWraps;
import com.yookue.commonplexus.javaseutil.util.StringUtilsWraps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * Structure with a string list
 *
 * @author David Hsing
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings({"unused", "WeakerAccess", "UnusedReturnValue"})
public class PureTextStruct implements Serializable {
    private String orderPrefix = SymbolVariantConst.ORDER_SQUARES_SPACE;

    private final List<String> texts = new ArrayList<>();

    public PureTextStruct(@Nullable String... texts) {
        addText(texts);
    }

    public PureTextStruct(@Nullable Collection<String> texts) {
        addText(texts);
    }

    public PureTextStruct addText(@Nullable String... texts) {
        return addText(ArrayUtilsWraps.asList(texts));
    }

    public PureTextStruct addText(@Nullable Collection<String> texts) {
        texts = CollectionPlainWraps.newArrayListIfNull(texts);
        CollectionPlainWraps.addAllIfNotEmpty(this.texts, texts);
        return this;
    }

    public PureTextStruct addTextAt(int index, @Nullable String... texts) {
        return addTextAt(index, ArrayUtilsWraps.asList(texts));
    }

    public PureTextStruct addTextAt(int index, @Nullable Collection<String> texts) {
        texts = CollectionPlainWraps.newArrayListIfNull(texts);
        ListPlainWraps.addAllIfNotEmpty(this.texts, index, texts);
        return this;
    }

    public PureTextStruct clearTexts() {
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
        return StringUtils.join(texts, delimiter);
    }

    public String getCompositeTextOrdering() {
        return getCompositeTextOrdering(null);
    }

    public String getCompositeTextOrdering(char delimiter) {
        return getCompositeTextOrdering(CharUtils.toString(delimiter));
    }

    public String getCompositeTextOrdering(@Nullable String delimiter) {
        if (CollectionPlainWraps.isEmpty(texts)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(CollectionPlainWraps.forEachIndexingTailing(texts, (index, text) -> {
            StringUtilsWraps.ifNotBlank(orderPrefix, element -> builder.append(String.format(element, index + 1)));
            builder.append(text).append(StringUtils.defaultString(delimiter));
        }));
        return CharSequenceWraps.toStringIgnoreEmpty(builder);
    }

    public boolean isTextsEmpty() {
        return CollectionPlainWraps.isEmpty(texts);
    }

    public PureTextStruct removeText(int index) {
        if (CollectionPlainWraps.isIndexBound(texts, index)) {
            texts.remove(index);
        }
        return this;
    }
}
