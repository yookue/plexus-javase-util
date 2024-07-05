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


import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import com.yookue.commonplexus.javaseutil.constant.AssertMessageConst;
import com.yookue.commonplexus.javaseutil.enumeration.JsoupWhitelistType;


/**
 * Utilities for {@link org.jsoup.Jsoup}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class JsoupParserWraps {
    public static String cleanWith(@Nullable String text, @Nullable JsoupWhitelistType type) {
        return (StringUtils.isBlank(text) || type == null) ? text : Jsoup.clean(text, getSafelist(type));
    }

    public static Safelist getSafelist(@Nullable JsoupWhitelistType type) {
        if (type == null) {
            return null;
        }
        return switch (type) {
            case SIMPLE_TEXT -> Safelist.simpleText();
            case BASIC -> Safelist.basic();
            case BASIC_WITH_IMAGE -> Safelist.basicWithImages();
            case RELAXED -> Safelist.relaxed();
            case RELAXED_WITH_FLASH -> relaxedFlashSafelist();
            case RELAXED_WITH_HTML5 -> relaxedHtml5Safelist();
            default -> Safelist.none();
        };
    }

    /**
     * Returns a safe list with flash for cleaning
     *
     * @return a safe list with flash for cleaning
     *
     * @reference "http://elf8848.iteye.com/blog/1872433"
     * @see org.jsoup.Jsoup#clean(java.lang.String, org.jsoup.safety.Safelist)
     * @see org.jsoup.safety.Safelist#relaxed
     */
    @Nonnull
    @SuppressWarnings({"JavadocDeclaration", "JavadocLinkAsPlainText"})
    public static Safelist flashSafelist() {
        Safelist result = Safelist.none();
        result.addTags("object", "param", "embed");    // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$
        result.addAttributes(":all", "class", "style", "name", "id", "type", "value", "width", "height");   // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$ // $NON-NLS-4$ // $NON-NLS-5$ // $NON-NLS-6$ // $NON-NLS-7$ // $NON-NLS-8$ // $NON-NLS-9$
        result.addAttributes("a", "target");    // $NON-NLS-1$ // $NON-NLS-2$
        result.addAttributes("embed", "src", "quality", "allowFullScreen", "allowScriptAccess", "flashvars", "pluginspage");   // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$ // $NON-NLS-4$ // $NON-NLS-5$ // $NON-NLS-6$ // $NON-NLS-7$
        result.addAttributes("object", "classid", "codebase");    // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$
        return result;
    }

    /**
     * Returns a safe list with HTML5 for cleaning
     *
     * @return a safe list with HTML5 for cleaning
     */
    @Nonnull
    public static Safelist html5Safelist() {
        Safelist result = Safelist.none();
        result.addTags("header", "nav", "main", "article", "section", "aside", "footer");   // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$ // $NON-NLS-4$ // $NON-NLS-5$ // $NON-NLS-6$ // $NON-NLS-7$
        result.addTags("details", "figure", "progress", "meter", "mark", "time");   // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$ // $NON-NLS-4$ // $NON-NLS-5$ // $NON-NLS-6$
        return result;
    }

    /**
     * Returns a relaxed safe list with flash for cleaning
     *
     * @return a relaxed safe list with flash for cleaning
     */
    @Nonnull
    public static Safelist relaxedFlashSafelist() {
        return mergeSafelist(Safelist.relaxed(), flashSafelist());
    }

    /**
     * Returns a relaxed safe list with HTML5 for cleaning
     *
     * @return a relaxed safe list with HTML5 for cleaning
     */
    @Nonnull
    public static Safelist relaxedHtml5Safelist() {
        return mergeSafelist(Safelist.relaxed(), html5Safelist());
    }

    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Safelist mergeSafelist(@Nullable Safelist target, @Nullable Safelist source) {
        if (source == null) {
            return target;
        }
        if (target == null) {
            return new Safelist(source);
        }
        Safelist result = new Safelist(target);
        Set<Object> targetTagNames = FieldUtilsWraps.readDeclaredFieldAs(target, "tagNames", true, Set.class);    // $NON-NLS-1$
        Validate.notNull(targetTagNames, AssertMessageConst.NOT_NULL);
        Map<Object, Set<?>> targetAttributes = FieldUtilsWraps.readDeclaredFieldAs(target, "attributes", true, Map.class);    // $NON-NLS-1$
        Validate.notNull(targetAttributes, AssertMessageConst.NOT_NULL);
        Map<Object, Map<?, ?>> targetEnforcedAttributes = FieldUtilsWraps.readDeclaredFieldAs(target, "enforcedAttributes", true, Map.class);    // $NON-NLS-1$
        Validate.notNull(targetEnforcedAttributes, AssertMessageConst.NOT_NULL);
        Map<Object, Map<?, ?>> targetProtocols = FieldUtilsWraps.readDeclaredFieldAs(target, "protocols", true, Map.class);    // $NON-NLS-1$
        Validate.notNull(targetProtocols, AssertMessageConst.NOT_NULL);
        Set<Object> sourceTagNames = FieldUtilsWraps.readDeclaredFieldAs(source, "tagNames", true, Set.class);    // $NON-NLS-1$
        Validate.notNull(sourceTagNames, AssertMessageConst.NOT_NULL);
        Map<Object, Set<?>> sourceAttributes = FieldUtilsWraps.readDeclaredFieldAs(source, "attributes", true, Map.class);    // $NON-NLS-1$
        Validate.notNull(sourceAttributes, AssertMessageConst.NOT_NULL);
        Map<Object, Map<?, ?>> sourceEnforcedAttributes = FieldUtilsWraps.readDeclaredFieldAs(source, "enforcedAttributes", true, Map.class);    // $NON-NLS-1$
        Validate.notNull(sourceEnforcedAttributes, AssertMessageConst.NOT_NULL);
        Map<Object, Map<?, ?>> sourceProtocols = FieldUtilsWraps.readDeclaredFieldAs(source, "protocols", true, Map.class);    // $NON-NLS-1$
        Validate.notNull(sourceProtocols, AssertMessageConst.NOT_NULL);
        targetTagNames.addAll(sourceTagNames);
        targetAttributes.putAll(sourceAttributes);
        targetEnforcedAttributes.putAll(sourceEnforcedAttributes);
        targetProtocols.putAll(sourceProtocols);
        FieldUtilsWraps.writeField(result, "tagNames", targetTagNames, true);    // $NON-NLS-1$
        FieldUtilsWraps.writeField(result, "attributes", targetAttributes, true);    // $NON-NLS-1$
        FieldUtilsWraps.writeField(result, "enforcedAttributes", targetEnforcedAttributes, true);    // $NON-NLS-1$
        FieldUtilsWraps.writeField(result, "protocols", targetProtocols, true);    // $NON-NLS-1$
        return result;
    }
}
