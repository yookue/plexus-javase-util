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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.swing.text.html.HTML;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;
import com.yookue.commonplexus.javaseutil.util.ArrayUtilsWraps;
import com.yookue.commonplexus.javaseutil.util.CollectionPlainWraps;
import com.yookue.commonplexus.javaseutil.util.ListPlainWraps;
import com.yookue.commonplexus.javaseutil.util.MapPlainWraps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


/**
 * Structure for {@link javax.swing.text.html.HTML.Tag}
 *
 * @author David Hsing
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public class HtmlTagStruct implements Serializable {
    private HTML.Tag tag;
    private String name;
    private String id;
    private String placeholder;
    private Map<HTML.Attribute, String> attributes = new LinkedHashMap<>();
    private Boolean disabled;
    private Boolean multiple;
    private Boolean readonly;
    private List<String> subNodes = new ArrayList<>();
    private Map<String, String> data = new LinkedHashMap<>();

    public HtmlTagStruct(@Nullable HTML.Tag tag) {
        this.tag = tag;
    }

    public HtmlTagStruct(@Nullable HTML.Tag tag, @Nullable String name, @Nullable String id) {
        this.tag = tag;
        this.name = name;
        this.id = id;
    }

    public HtmlTagStruct(@Nullable HTML.Tag tag, @Nullable Map<HTML.Attribute, String> attributes) {
        this.tag = tag;
        this.attributes = attributes;
    }

    public HtmlTagStruct(@Nullable HTML.Tag tag, @Nullable String name, @Nullable String id, @Nullable Map<HTML.Attribute, String> attributes) {
        this(tag, name, id);
        this.attributes = attributes;
    }

    public HtmlTagStruct addAttribute(@Nullable HTML.Attribute key, @Nullable String value) {
        attributes = MapPlainWraps.newLinkedHashMapIfNull(attributes);
        MapPlainWraps.putIfKeyNotNull(attributes, key, value);
        return this;
    }

    public HtmlTagStruct addData(@Nullable String key, @Nullable String value) {
        data = MapPlainWraps.newLinkedHashMapIfNull(data);
        MapPlainWraps.putIfKeyNotBlank(data, key, value);
        return this;
    }

    public HtmlTagStruct appendSubNode(@Nullable String... lines) {
        return appendSubNode(ArrayUtilsWraps.asList(lines));
    }

    public HtmlTagStruct appendSubNode(@Nullable Collection<String> lines) {
        subNodes = CollectionPlainWraps.newArrayListIfNull(subNodes);
        CollectionPlainWraps.addAll(subNodes, lines);
        return this;
    }

    public HtmlTagStruct prependSubNode(@Nullable String... lines) {
        return prependSubNode(ArrayUtilsWraps.asList(lines));
    }

    public HtmlTagStruct prependSubNode(@Nullable Collection<String> lines) {
        subNodes = CollectionPlainWraps.newArrayListIfNull(subNodes);
        ListPlainWraps.addAll(subNodes, 0, lines);
        return this;
    }

    public HtmlTagStruct clearAttributes() {
        if (attributes != null) {
            attributes.clear();
        }
        return this;
    }

    public HtmlTagStruct clearSubNodes() {
        if (subNodes != null) {
            subNodes.clear();
        }
        return this;
    }

    public HtmlTagStruct clearData() {
        if (data != null) {
            data.clear();
        }
        return this;
    }

    public boolean isSubNodesEmpty() {
        return CollectionPlainWraps.isEmpty(subNodes);
    }

    public boolean isDataEmpty() {
        return MapPlainWraps.isEmpty(data);
    }

    public String removeAttribute(@Nullable HTML.Attribute key) {
        return (key == null) ? null : attributes.remove(key);
    }

    public String removeData(@Nullable String key) {
        return StringUtils.isBlank(key) ? null : data.remove(key);
    }

    @Override
    public String toString() {
        return toString(CharVariantConst.DOUBLE_QUOTE);
    }

    @Nullable
    public String toString(char delimiter) {
        if (tag == null) {
            return null;
        }
        // prepare attributes
        Map<String, String> textAttrs = new LinkedHashMap<>();
        textAttrs.put("name", name);    // $NON-NLS-1$
        textAttrs.put("id", id);    // $NON-NLS-1$
        textAttrs.put("placeholder", placeholder);    // $NON-NLS-1$
        Map<String, Boolean> boolAttrs = new LinkedHashMap<>();
        boolAttrs.put("multiple", multiple);    // $NON-NLS-1$
        boolAttrs.put("disabled", disabled);    // $NON-NLS-1$
        boolAttrs.put("readonly", readonly);    // $NON-NLS-1$
        // build node string
        StringBuilder builder = new StringBuilder();
        builder.append(CharVariantConst.ANGLE_BRACKET_LEFT).append(tag);
        MapPlainWraps.forEach(textAttrs, (key, value) -> builder.append(String.format(" %s=%c%s%c", key, delimiter, value, delimiter)), (key, value) -> StringUtils.isNotEmpty(value));    // $NON-NLS-1$
        MapPlainWraps.forEach(attributes, (key, value) -> builder.append(String.format(" %s=%c%s%c", key.toString(), delimiter, StringUtils.defaultString(value), delimiter)), ObjectUtils::allNotNull);    // $NON-NLS-1$
        MapPlainWraps.forEach(data, (key, value) -> {
            if (value == null) {
                builder.append(String.format(" data-%s", key));    // $NON-NLS-1$
            } else {
                builder.append(String.format(" data-%s=%c%s%c", key, delimiter, StringUtils.defaultString(value), delimiter));    // $NON-NLS-1$
            }
        }, (key, value) -> StringUtils.isNotBlank(key));    // $NON-NLS-1$
        MapPlainWraps.forEach(boolAttrs, (key, value) -> builder.append(CharVariantConst.SPACE).append(key), (key, value) -> BooleanUtils.isTrue(value));
        if (tag.isBlock()) {
            builder.append(CharVariantConst.ANGLE_BRACKET_RIGHT);
            CollectionPlainWraps.forEach(subNodes, builder::append, Objects::nonNull);
            builder.append(CharVariantConst.ANGLE_BRACKET_LEFT).append(CharVariantConst.SLASH).append(tag).append(CharVariantConst.ANGLE_BRACKET_RIGHT);
        } else {
            builder.append(CharVariantConst.SLASH).append(CharVariantConst.ANGLE_BRACKET_RIGHT);
        }
        builder.append(CharVariantConst.ANGLE_BRACKET_RIGHT);
        return builder.toString();
    }
}
