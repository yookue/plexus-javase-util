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


import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.SerializationUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.SerializationUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class SerializationUtilsWraps {
    /**
     * Serializes an object to a byte array
     *
     * @param object the object to serialize, must implement {@link Serializable}
     *
     * @return the serialized byte array, or {@code null} if the object is null or serialization fails
     */
    @Nullable
    public static byte[] serialize(@Nullable Serializable object) {
        if (object == null) {
            return null;
        }
        try {
            return SerializationUtils.serialize(object);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Serializes an object to an output stream
     *
     * @param object the object to serialize, must implement {@link Serializable}
     * @param output the output stream to write the serialized data to
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static void serialize(@Nullable Serializable object, @Nullable OutputStream output) {
        if (ObjectUtils.anyNull(object, output)) {
            return;
        }
        try {
            SerializationUtils.serialize(object, output);
        } catch (Exception ignored) {
        }
    }

    /**
     * Clones an object by serializing and deserializing it
     *
     * @param object the object to clone, must implement {@link Serializable}
     *
     * @return a deep copy of the object, or {@code null} if the object is null or cloning fails
     */
    @Nullable
    public static <T extends Serializable> T clone(@Nullable T object) {
        try {
            return SerializationUtils.clone(object);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Deserializes a byte array to an object
     *
     * @param input the byte array containing the serialized data
     *
     * @return the deserialized object, or {@code null} if the input is null or deserialization fails
     */
    @Nullable
    public static <T> T deserialize(@Nullable byte[] input) {
        if (input == null) {
            return null;
        }
        try {
            return SerializationUtils.deserialize(input);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Deserializes an object from an input stream
     *
     * @param input the input stream containing the serialized data
     *
     * @return the deserialized object, or {@code null} if the input is null or deserialization fails
     */
    @Nullable
    public static <T> T deserialize(@Nullable InputStream input) {
        if (input == null) {
            return null;
        }
        try {
            return SerializationUtils.deserialize(input);
        } catch (Exception ignored) {
        }
        return null;
    }
}
