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
 * Structure with a boolean, and a string list
 *
 * @author David Hsing
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public class BooleanTextStruct extends PureTextStruct {
    private boolean success = false;

    public BooleanTextStruct(boolean success, @Nullable String... texts) {
        this.success = success;
        super.addText(texts);
    }

    public BooleanTextStruct(boolean success, @Nullable Collection<String> texts) {
        this.success = success;
        super.addText(texts);
    }

    @Nonnull
    public static BooleanTextStruct ofSuccess() {
        return new BooleanTextStruct(true);
    }

    @Nonnull
    public static BooleanTextStruct ofSuccess(@Nullable String... texts) {
        return new BooleanTextStruct(true, texts);
    }

    @Nonnull
    public static BooleanTextStruct ofSuccess(@Nullable Collection<String> texts) {
        return new BooleanTextStruct(true, texts);
    }

    @Nonnull
    public static BooleanTextStruct ofFailure() {
        return new BooleanTextStruct(false);
    }

    @Nonnull
    public static BooleanTextStruct ofFailure(@Nullable String... texts) {
        return new BooleanTextStruct(false, texts);
    }

    @Nonnull
    public static BooleanTextStruct ofFailure(@Nullable Collection<String> texts) {
        return new BooleanTextStruct(false, texts);
    }
}
