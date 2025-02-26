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


import java.util.Collection;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * Structure with a boolean, a generic object, and a string list
 *
 * @param <T> the type of the object inside
 *
 * @author David Hsing
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public class BooleanDataStruct<T> extends DataTextStruct<T> {
    private boolean success = false;

    public BooleanDataStruct(boolean success, @Nullable T data, @Nullable String... texts) {
        this.success = success;
        super.setData(data);
        super.addText(texts);
    }

    public BooleanDataStruct(boolean success, @Nullable T data, @Nullable Collection<String> texts) {
        this.success = success;
        super.setData(data);
        super.addText(texts);
    }

    @Nonnull
    public static BooleanDataStruct<?> ofSuccess() {
        return new BooleanDataStruct<>(true);
    }

    @Nonnull
    public static <T> BooleanDataStruct<T> ofSuccess(@Nullable T data) {
        return new BooleanDataStruct<>(true, data);
    }

    @Nonnull
    public static <T> BooleanDataStruct<T> ofSuccess(@Nullable T data, @Nullable String... texts) {
        return new BooleanDataStruct<>(true, data, texts);
    }

    @Nonnull
    public static <T> BooleanDataStruct<T> ofSuccess(@Nullable T data, @Nullable Collection<String> texts) {
        return new BooleanDataStruct<>(true, data, texts);
    }

    @Nonnull
    public static BooleanDataStruct<?> ofFailure() {
        return new BooleanDataStruct<>(false);
    }

    @Nonnull
    public static <T> BooleanDataStruct<T> ofFailure(@Nullable T data) {
        return new BooleanDataStruct<>(false);
    }

    @Nonnull
    public static <T> BooleanDataStruct<T> ofFailure(@Nullable T data, @Nullable String... texts) {
        return new BooleanDataStruct<>(false, data, texts);
    }

    @Nonnull
    public static <T> BooleanDataStruct<T> ofFailure(@Nullable T data, @Nullable Collection<String> texts) {
        return new BooleanDataStruct<>(false, data, texts);
    }
}
