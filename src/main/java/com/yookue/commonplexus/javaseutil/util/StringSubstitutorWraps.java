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
import java.util.Properties;
import javax.annotation.Nullable;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.lookup.StringLookup;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;


/**
 * Utilities for {@link org.apache.commons.text.StringSubstitutor}
 *
 * @author David Hsing
 * @see org.apache.commons.text.StringSubstitutor
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class StringSubstitutorWraps {
    @Nullable
    public static <V> String replace(@Nullable Object source, @Nullable Map<String, V> mappings, char prefix, char suffix) {
        return replace(source, mappings, prefix, suffix, null);
    }

    @Nullable
    public static <V> String replace(@Nullable Object source, @Nullable Map<String, V> mappings, char prefix, char suffix, @Nullable StringLookup lookup) {
        return replace(source, mappings, CharUtils.toString(prefix), CharUtils.toString(suffix), lookup);
    }

    @Nullable
    public static <V> String replace(@Nullable Object source, @Nullable Map<String, V> mappings, @Nullable CharSequence prefix, @Nullable CharSequence suffix) {
        return replace(source, mappings, prefix, suffix, null);
    }

    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <V> String replace(@Nullable Object source, @Nullable Map<String, V> mappings, @Nullable CharSequence prefix, @Nullable CharSequence suffix, @Nullable StringLookup lookup) {
        if (source == null) {
            return null;
        }
        if (MapPlainWraps.isEmpty(mappings) || StringUtils.isAnyEmpty(prefix, suffix)) {
            return source.toString();
        }
        StringSubstitutor substitutor = new StringSubstitutor(mappings, prefix.toString(), suffix.toString());
        if (lookup != null) {
            substitutor.setVariableResolver(lookup);
        }
        return substitutor.replace(source);
    }

    @Nullable
    public static <V> String replace(@Nullable Object source, @Nullable Properties mappings, char prefix, char suffix) {
        return replace(source, mappings, prefix, suffix, null);
    }

    @Nullable
    public static <V> String replace(@Nullable Object source, @Nullable Properties mappings, char prefix, char suffix, @Nullable StringLookup lookup) {
        return replace(source, mappings, CharUtils.toString(prefix), CharUtils.toString(suffix), lookup);
    }

    @Nullable
    public static <V> String replace(@Nullable Object source, @Nullable Properties mappings, @Nullable CharSequence prefix, @Nullable CharSequence suffix) {
        return replace(source, mappings, prefix, suffix, null);
    }

    @Nullable
    public static <V> String replace(@Nullable Object source, @Nullable Properties mappings, @Nullable CharSequence prefix, @Nullable CharSequence suffix, @Nullable StringLookup lookup) {
        return replace(source, PropertyPlainWraps.toStringStringMap(mappings), prefix, suffix, lookup);
    }

    @Nullable
    public static <V> String replaceAngleBrackets(@Nullable Object source, @Nullable Map<String, V> mappings) {
        return replaceAngleBrackets(source, mappings, null);
    }

    /**
     * Returns the replacing string with angle brackets in format "&lt;variable&gt;"
     *
     * @param source the source text containing the variables to substitute
     * @param mappings the map that contains the variables and values
     * @param lookup the string variable resolver
     *
     * @return the replacing string with angle brackets in format "&lt;variable&gt;"
     */
    @Nullable
    public static <V> String replaceAngleBrackets(@Nullable Object source, @Nullable Map<String, V> mappings, @Nullable StringLookup lookup) {
        return replace(source, mappings, CharVariantConst.ANGLE_BRACKET_LEFT, CharVariantConst.ANGLE_BRACKET_RIGHT, lookup);
    }

    @Nullable
    public static <V> String replaceDollarAngleBrackets(@Nullable Object source, @Nullable Map<String, V> mappings) {
        return replaceDollarAngleBrackets(source, mappings, null);
    }

    /**
     * Returns the replacing string with dollar and angle brackets in format "$&lt;variable&gt;"
     *
     * @param source the source text containing the variables to substitute
     * @param mappings the map that contains the variables and values
     * @param lookup the string variable resolver
     *
     * @return the replacing string with dollar and angle brackets in format "$&lt;variable&gt;"
     */
    @Nullable
    public static <V> String replaceDollarAngleBrackets(@Nullable Object source, @Nullable Map<String, V> mappings, @Nullable StringLookup lookup) {
        return replace(source, mappings, StringUtils.join(CharVariantConst.DOLLAR, CharVariantConst.ANGLE_BRACKET_LEFT), CharUtils.toString(CharVariantConst.ANGLE_BRACKET_RIGHT), lookup);
    }

    @Nullable
    public static <V> String replaceCurlyBrackets(@Nullable Object source, @Nullable Map<String, V> mappings) {
        return replaceCurlyBrackets(source, mappings, null);
    }

    /**
     * Returns the replacing string with curly brackets in format "{variable}"
     *
     * @param source the source text containing the variables to substitute
     * @param mappings the map that contains the variables and values
     * @param lookup the string variable resolver
     *
     * @return the replacing string with curly brackets in format "{variable}"
     */
    @Nullable
    public static <V> String replaceCurlyBrackets(@Nullable Object source, @Nullable Map<String, V> mappings, @Nullable StringLookup lookup) {
        return replace(source, mappings, CharVariantConst.CURLY_BRACKET_LEFT, CharVariantConst.CURLY_BRACKET_RIGHT, lookup);
    }

    @Nullable
    public static <V> String replaceDollarCurlyBrackets(@Nullable Object source, @Nullable Map<String, V> mappings) {
        return replaceDollarCurlyBrackets(source, mappings, null);
    }

    /**
     * Returns the replacing string with dollar and curly brackets in format "${variable}"
     *
     * @param source the source text containing the variables to substitute
     * @param mappings the map that contains the variables and values
     * @param lookup the string variable resolver
     *
     * @return the replacing string with dollar and curly brackets in format "${variable}"
     */
    @Nullable
    public static <V> String replaceDollarCurlyBrackets(@Nullable Object source, @Nullable Map<String, V> mappings, @Nullable StringLookup lookup) {
        return replace(source, mappings, StringSubstitutor.DEFAULT_VAR_START, StringSubstitutor.DEFAULT_VAR_END, lookup);
    }

    @Nullable
    public static <V> String replaceSquareBrackets(@Nullable Object source, @Nullable Map<String, V> mappings) {
        return replaceSquareBrackets(source, mappings, null);
    }

    /**
     * Returns the replacing string with square brackets in format "[variable]"
     *
     * @param source the source text containing the variables to substitute
     * @param mappings the map that contains the variables and values
     * @param lookup the string variable resolver
     *
     * @return the replacing string with square brackets in format "[variable]"
     */
    @Nullable
    public static <V> String replaceSquareBrackets(@Nullable Object source, @Nullable Map<String, V> mappings, @Nullable StringLookup lookup) {
        return replace(source, mappings, CharVariantConst.SQUARE_BRACKET_LEFT, CharVariantConst.SQUARE_BRACKET_RIGHT, lookup);
    }

    @Nullable
    public static <V> String replaceDollarSquareBrackets(@Nullable Object source, @Nullable Map<String, V> mappings) {
        return replaceDollarSquareBrackets(source, mappings, null);
    }

    /**
     * Returns the replacing string with dollar and square brackets in format "$[variable]"
     *
     * @param source the source text containing the variables to substitute
     * @param mappings the map that contains the variables and values
     * @param lookup the string variable resolver
     *
     * @return the replacing string with dollar and square brackets in format "$[variable]"
     */
    @Nullable
    public static <V> String replaceDollarSquareBrackets(@Nullable Object source, @Nullable Map<String, V> mappings, @Nullable StringLookup lookup) {
        return replace(source, mappings, StringUtils.join(CharVariantConst.DOLLAR, CharVariantConst.SQUARE_BRACKET_LEFT), CharUtils.toString(CharVariantConst.SQUARE_BRACKET_RIGHT), lookup);
    }

    @Nullable
    public static <V> String replaceParenthesis(@Nullable Object source, @Nullable Map<String, V> mappings) {
        return replaceParenthesis(source, mappings, null);
    }

    /**
     * Returns the replacing string with parenthesis in format "(variable)"
     *
     * @param source the source text containing the variables to substitute
     * @param mappings the map that contains the variables and values
     * @param lookup the string variable resolver
     *
     * @return the replacing string with parenthesis in format "(variable)"
     */
    @Nullable
    public static <V> String replaceParenthesis(@Nullable Object source, @Nullable Map<String, V> mappings, @Nullable StringLookup lookup) {
        return replace(source, mappings, CharVariantConst.PARENTHESIS_LEFT, CharVariantConst.PARENTHESIS_RIGHT, lookup);
    }

    @Nullable
    public static <V> String replaceDollarParenthesis(@Nullable Object source, @Nullable Map<String, V> mappings) {
        return replaceDollarParenthesis(source, mappings, null);
    }

    /**
     * Returns the replacing string with dollar and parenthesis in format "$(variable)"
     *
     * @param source the source text containing the variables to substitute
     * @param mappings the map that contains the variables and values
     * @param lookup the string variable resolver
     *
     * @return the replacing string with dollar and parenthesis in format "$(variable)"
     */
    @Nullable
    public static <V> String replaceDollarParenthesis(@Nullable Object source, @Nullable Map<String, V> mappings, @Nullable StringLookup lookup) {
        return replace(source, mappings, StringUtils.join(CharVariantConst.DOLLAR, CharVariantConst.PARENTHESIS_LEFT), CharUtils.toString(CharVariantConst.PARENTHESIS_RIGHT), lookup);
    }
}
