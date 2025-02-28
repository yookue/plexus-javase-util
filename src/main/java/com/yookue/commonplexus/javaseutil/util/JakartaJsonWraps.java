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
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import jakarta.annotation.Nullable;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonString;
import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link jakarta.json.JsonStructure}
 *
 * @author David Hsing
 * @see jakarta.json.JsonStructure
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class JakartaJsonWraps {
    @Nullable
    public static JsonStructure ofJson(@Nullable String content) {
        return StringUtils.isBlank(content) ? null : ofJson(new StringReader(content));
    }

    @Nullable
    public static JsonStructure ofJson(@Nullable File file) {
        return ofJson(file, null);
    }

    @Nullable
    public static JsonStructure ofJson(@Nullable File file, @Nullable Charset charset) {
        try {
            return (file == null) ? null : ofJson(new FileReader(file, ObjectUtils.defaultIfNull(charset, StandardCharsets.UTF_8)));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonStructure ofJson(@Nullable InputStream stream) {
        return ofJson(stream, null);
    }

    @Nullable
    public static JsonStructure ofJson(@Nullable InputStream stream, @Nullable Charset charset) {
        try {
            return (stream == null) ? null : ofJson(new InputStreamReader(stream, ObjectUtils.defaultIfNull(charset, StandardCharsets.UTF_8)));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonStructure ofJson(@Nullable Reader reader) {
        if (reader == null) {
            return null;
        }
        try (JsonReader jsonReader = Json.createReader(reader)) {
            return jsonReader.read();
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static <T> T ofJsonAs(@Nullable String content, @Nullable Class<T> expectedType) {
        return ofJsonAs(content, expectedType, null);
    }

    @Nullable
    public static <T> T ofJsonAs(@Nullable String content, @Nullable Class<T> expectedType, @Nullable JsonbConfig config) {
        if (StringUtils.isBlank(content) || expectedType == null) {
            return null;
        }
        try (Jsonb jsonb = JsonbBuilder.create(config)) {
            return jsonb.fromJson(content, expectedType);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static <T> T ofJsonAs(@Nullable File file, @Nullable Class<T> expectedType) {
        return ofJsonAs(file, expectedType, null, null);
    }

    @Nullable
    public static <T> T ofJsonAs(@Nullable File file, @Nullable Class<T> expectedType, @Nullable Charset charset) {
        return ofJsonAs(file, expectedType, null, charset);
    }

    @Nullable
    public static <T> T ofJsonAs(@Nullable File file, @Nullable Class<T> expectedType, @Nullable JsonbConfig config, @Nullable Charset charset) {
        if (ObjectUtils.anyNull(file, expectedType)) {
            return null;
        }
        String content = FileUtilsWraps.readFileToString(file, ObjectUtils.defaultIfNull(charset, StandardCharsets.UTF_8));
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try (Jsonb jsonb = JsonbBuilder.create(config)) {
            return jsonb.fromJson(content, expectedType);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static <T> T ofJsonAs(@Nullable InputStream stream, @Nullable Class<T> expectedType) {
        return ofJsonAs(stream, expectedType, null);
    }

    @Nullable
    public static <T> T ofJsonAs(@Nullable InputStream stream, @Nullable Class<T> expectedType, @Nullable JsonbConfig config) {
        if (ObjectUtils.anyNull(stream, expectedType)) {
            return null;
        }
        try (Jsonb jsonb = JsonbBuilder.create(config)) {
            return jsonb.fromJson(stream, expectedType);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static <T> T ofJsonAs(@Nullable Reader reader, @Nullable Class<T> expectedType) {
        return ofJsonAs(reader, expectedType, null);
    }

    @Nullable
    public static <T> T ofJsonAs(@Nullable Reader reader, @Nullable Class<T> expectedType, @Nullable JsonbConfig config) {
        if (ObjectUtils.anyNull(reader, expectedType)) {
            return null;
        }
        try (Jsonb jsonb = JsonbBuilder.create(config)) {
            return jsonb.fromJson(reader, expectedType);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String toJson(@Nullable Object source) {
        return toJson(source, null);
    }

    @Nullable
    public static String toJson(@Nullable Object source, @Nullable JsonbConfig config) {
        if (source == null) {
            return null;
        }
        try (Jsonb jsonb = JsonbBuilder.create(config)) {
            return jsonb.toJson(source);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonStructure readStructure(@Nullable JsonReader reader) {
        if (reader == null) {
            return null;
        }
        try {
            return reader.read();
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonArray readArray(@Nullable JsonReader reader) {
        if (reader == null) {
            return null;
        }
        try {
            return reader.readArray();
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonObject readObject(@Nullable JsonReader reader) {
        if (reader == null) {
            return null;
        }
        try {
            return reader.readObject();
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonValue readValue(@Nullable JsonReader reader) {
        if (reader == null) {
            return null;
        }
        try {
            return reader.readValue();
        } catch (Exception ignored) {
        }
        return null;
    }

    public static Boolean getBoolean(@Nullable JsonArray instance, int index) {
        return getBoolean(instance, index, null);
    }

    public static Boolean getBoolean(@Nullable JsonArray instance, int index, Boolean defaultValue) {
        if (instance == null || index < 0) {
            return null;
        }
        try {
            return instance.getBoolean(index, defaultValue);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static Boolean getBoolean(@Nullable JsonObject instance, @Nullable String name) {
        return getBoolean(instance, name, null);
    }

    public static Boolean getBoolean(@Nullable JsonObject instance, @Nullable String name, Boolean defaultValue) {
        if (instance == null || StringUtils.isBlank(name)) {
            return null;
        }
        try {
            return instance.getBoolean(name, defaultValue);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static Integer getInteger(@Nullable JsonArray instance, int index) {
        return getInteger(instance, index, null);
    }

    public static Integer getInteger(@Nullable JsonArray instance, int index, Integer defaultValue) {
        if (instance == null || index < 0) {
            return null;
        }
        try {
            return instance.getInt(index, defaultValue);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static Integer getInteger(@Nullable JsonObject instance, @Nullable String name) {
        return getInteger(instance, name, null);
    }

    public static Integer getInteger(@Nullable JsonObject instance, @Nullable String name, Integer defaultValue) {
        if (instance == null || StringUtils.isBlank(name)) {
            return null;
        }
        try {
            return instance.getInt(name, defaultValue);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String getString(@Nullable JsonArray instance, int index) {
        return getString(instance, index, null);
    }

    @Nullable
    public static String getString(@Nullable JsonArray instance, int index, @Nullable String defaultValue) {
        if (instance == null || index < 0) {
            return null;
        }
        try {
            return instance.getString(index, defaultValue);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static String getString(@Nullable JsonObject instance, @Nullable String name) {
        return getString(instance, name, null);
    }

    @Nullable
    public static String getString(@Nullable JsonObject instance, @Nullable String name, @Nullable String defaultValue) {
        if (instance == null || StringUtils.isBlank(name)) {
            return null;
        }
        try {
            return instance.getString(name, defaultValue);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonArray getJsonArray(@Nullable JsonArray instance, int index) {
        if (instance == null || index < 0) {
            return null;
        }
        try {
            return instance.getJsonArray(index);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonArray getJsonArray(@Nullable JsonObject instance, @Nullable String name) {
        if (instance == null || StringUtils.isBlank(name)) {
            return null;
        }
        try {
            return instance.getJsonArray(name);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonNumber getJsonNumber(@Nullable JsonArray instance, int index) {
        if (instance == null || index < 0) {
            return null;
        }
        try {
            return instance.getJsonNumber(index);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonNumber getJsonNumber(@Nullable JsonObject instance, @Nullable String name) {
        if (instance == null || StringUtils.isBlank(name)) {
            return null;
        }
        try {
            return instance.getJsonNumber(name);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonObject getJsonObject(@Nullable JsonArray instance, int index) {
        if (instance == null || index < 0) {
            return null;
        }
        try {
            return instance.getJsonObject(index);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonObject getJsonObject(@Nullable JsonObject instance, @Nullable String name) {
        if (instance == null || StringUtils.isBlank(name)) {
            return null;
        }
        try {
            return instance.getJsonObject(name);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonString getJsonString(@Nullable JsonArray instance, int index) {
        if (instance == null || index < 0) {
            return null;
        }
        try {
            return instance.getJsonString(index);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonString getJsonString(@Nullable JsonObject instance, @Nullable String name) {
        if (instance == null || StringUtils.isBlank(name)) {
            return null;
        }
        try {
            return instance.getJsonString(name);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static JsonValue getJsonValue(@Nullable JsonStructure structure, @Nullable String name) {
        return (structure == null || StringUtils.isBlank(name)) ? null : structure.getValue(name);
    }

    @Nullable
    public static List<JsonValue> getValues(@Nullable JsonArray instance) {
        return (instance == null) ? null : instance.getValuesAs(JsonValue.class);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static <T extends JsonValue> List<T> getValuesAs(@Nullable JsonArray instance, @Nullable Class<T> clazz) {
        return ObjectUtils.anyNull(instance, clazz) ? null : instance.getValuesAs(clazz);
    }

    @Nullable
    @SuppressWarnings("DataFlowIssue")
    public static <T, K extends JsonValue> List<T> getValuesAs(@Nullable JsonArray instance, @Nullable Function<K, T> func) {
        return ObjectUtils.anyNull(instance, func) ? null : instance.getValuesAs(func);
    }

    @Nullable
    public static List<String> getValuesAsString(@Nullable JsonArray instance) {
        return (instance == null) ? null : instance.getValuesAs(JsonString::getString);
    }

    @Nullable
    public static List<Integer> getValuesAsInteger(@Nullable JsonArray instance) {
        return (instance == null) ? null : instance.getValuesAs(JsonNumber::intValue);
    }

    public static Boolean isNull(@Nullable JsonArray instance, int index) {
        if (instance == null || index < 0) {
            return null;
        }
        try {
            return instance.isNull(index);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static Boolean isNull(@Nullable JsonObject instance, @Nullable String name) {
        if (instance == null || StringUtils.isBlank(name)) {
            return null;
        }
        try {
            return instance.isNull(name);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static boolean isArrayType(@Nullable JsonValue value) {
        return value != null && value.getValueType() == JsonValue.ValueType.ARRAY;
    }

    public static boolean isObjectType(@Nullable JsonValue value) {
        return value != null && value.getValueType() == JsonValue.ValueType.OBJECT;
    }

    public static boolean isNullType(@Nullable JsonValue value) {
        return value != null && value.getValueType() == JsonValue.ValueType.NULL;
    }
}
