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

package com.yookue.commonplexus.javaseutil.identity;


import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;


/**
 * Nano id generator by {@link java.util.UUID}
 *
 * @author David Hsing
 * @see "org.springframework.util.JdkIdGenerator"
 */
@SuppressWarnings("unused")
public abstract class NanoIdGenerator {
    private static final String ALPHABETIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";    // $NON-NLS-1$
    private static final String NUMERIC = "0123456789";    // $NON-NLS-1$

    private static final char[] POPULAR_CHARS = "346789ABCDEFGHJKMNPQRTWXYabcdefghjkmnpqrtwxy".toCharArray();    // $NON-NLS-1$
    private static final char[] ALPHABETIC_UPPERS = ALPHABETIC.toCharArray();
    private static final char[] ALPHABETIC_LOWERS = ALPHABETIC.toLowerCase().toCharArray();
    private static final char[] ALPHABETIC_MIXINS = (ALPHABETIC + ALPHABETIC.toLowerCase()).toCharArray();
    private static final char[] NUMERIC_CHARS = NUMERIC.toCharArray();
    private static final char[] ALPHANUMERIC_UPPERS = (ALPHABETIC + NUMERIC).toCharArray();
    private static final char[] ALPHANUMERIC_LOWERS = (ALPHABETIC.toLowerCase() + NUMERIC).toCharArray();
    private static final char[] ALPHANUMERIC_MIXINS = (ALPHABETIC + ALPHABETIC.toLowerCase() + NUMERIC).toCharArray();

    @Nonnull
    public static String getPopularId() {
        return getPopularId(-1);
    }

    @Nonnull
    public static String getPopularId(int size) {
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, POPULAR_CHARS, (size > 0) ? size : NanoIdUtils.DEFAULT_SIZE);
    }

    @Nonnull
    public static String getAlphabeticId() {
        return getAlphabeticId(-1);
    }

    @Nonnull
    public static String getAlphabeticId(int size) {
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, ALPHABETIC_MIXINS, (size > 0) ? size : NanoIdUtils.DEFAULT_SIZE);
    }

    @Nonnull
    public static String getAlphabeticLowerId() {
        return getAlphabeticLowerId(-1);
    }

    @Nonnull
    public static String getAlphabeticLowerId(int size) {
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, ALPHABETIC_LOWERS, (size > 0) ? size : NanoIdUtils.DEFAULT_SIZE);
    }

    @Nonnull
    public static String getAlphabeticUpperId() {
        return getAlphabeticUpperId(-1);
    }

    @Nonnull
    public static String getAlphabeticUpperId(int size) {
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, ALPHABETIC_UPPERS, (size > 0) ? size : NanoIdUtils.DEFAULT_SIZE);
    }

    @Nonnull
    public static String getAlphanumericId() {
        return getAlphanumericId(-1);
    }

    @Nonnull
    public static String getAlphanumericId(int size) {
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, ALPHANUMERIC_MIXINS, (size > 0) ? size : NanoIdUtils.DEFAULT_SIZE);
    }

    @Nonnull
    public static String getAlphanumericLowerId() {
        return getAlphanumericLowerId(-1);
    }

    @Nonnull
    public static String getAlphanumericLowerId(int size) {
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, ALPHANUMERIC_LOWERS, (size > 0) ? size : NanoIdUtils.DEFAULT_SIZE);
    }

    @Nonnull
    public static String getAlphanumericUpperId() {
        return getAlphanumericUpperId(-1);
    }

    @Nonnull
    public static String getAlphanumericUpperId(int size) {
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, ALPHANUMERIC_UPPERS, (size > 0) ? size : NanoIdUtils.DEFAULT_SIZE);
    }

    @Nonnull
    public static String getNumericId() {
        return getNumericId(-1);
    }

    @Nonnull
    public static String getNumericId(int size) {
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, NUMERIC_CHARS, (size > 0) ? size : NanoIdUtils.DEFAULT_SIZE);
    }

    @Nonnull
    public static String getRandomId() {
        return NanoIdUtils.randomNanoId();
    }

    @Nonnull
    public static String getRandomId(int size) {
        return getRandomId(size, null);
    }

    @Nonnull
    public static String getRandomId(int size, @Nullable char[] alphabets) {
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, ((alphabets == null || alphabets.length == 0) ? NanoIdUtils.DEFAULT_ALPHABET : alphabets), (size > 0) ? size : NanoIdUtils.DEFAULT_SIZE);
    }
}
