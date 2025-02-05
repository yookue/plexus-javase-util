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


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.Fraction;
import org.apache.commons.lang3.math.NumberUtils;
import com.yookue.commonplexus.javaseutil.constant.SymbolVariantConst;
import com.yookue.commonplexus.javaseutil.exception.UnsupportedClassException;


/**
 * Utilities for {@link org.apache.commons.lang3.math.NumberUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.math.NumberUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class NumberUtilsWraps {
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Number & Comparable<? extends Number>> boolean allNegative(@Nullable T... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(NumberUtilsWraps::isNegative);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Number & Comparable<? extends Number>> boolean allNotNegative(@Nullable T... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).noneMatch(NumberUtilsWraps::isNegative);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Number & Comparable<? extends Number>> boolean anyNegative(@Nullable T... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(NumberUtilsWraps::isNegative);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Number & Comparable<? extends Number>> boolean anyNotNegative(@Nullable T... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(NumberUtilsWraps::isNotNegative);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Number & Comparable<? extends Number>> boolean allPositive(@Nullable T... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(NumberUtilsWraps::isPositive);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Number & Comparable<? extends Number>> boolean allNotPositive(@Nullable T... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).noneMatch(NumberUtilsWraps::isPositive);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Number & Comparable<? extends Number>> boolean anyPositive(@Nullable T... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(NumberUtilsWraps::isPositive);
    }

    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Number & Comparable<? extends Number>> boolean anyNotPositive(@Nullable T... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(NumberUtilsWraps::isNotPositive);
    }

    /**
     * @reference "https://cloud.tencent.com/developer/ask/44072"
     * @see org.apache.commons.lang3.Range
     */
    @SuppressWarnings({"JavadocDeclaration", "JavadocLinkAsPlainText"})
    public static <T extends Number & Comparable<? super T>> boolean between(@Nullable T value, @Nullable T startValue, @Nullable T endValue, boolean includeStart, boolean includeEnd) {
        if (value == null) {
            return (startValue == null && includeStart) || (endValue == null && includeEnd);
        }
        int compareStart = ObjectUtils.compare(value, startValue), compareEnd = ObjectUtils.compare(value, endValue);
        return (includeStart ? compareStart >= 0 : compareStart > 0) && (includeEnd ? compareEnd <= 0 : compareEnd < 0);
    }

    @Nullable
    @SafeVarargs
    public static <T extends Number & Comparable<? super T>> T firstBetween(@Nullable T startValue, @Nullable T endValue, @Nullable T... values) {
        return firstBetween(startValue, endValue, false, false, values);
    }

    @Nullable
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Number & Comparable<? super T>> T firstBetween(@Nullable T startValue, @Nullable T endValue, boolean includeStart, boolean includeEnd, @Nullable T... values) {
        if (ArrayUtils.isEmpty(values)) {
            return null;
        }
        return Arrays.stream(values).filter(value -> NumberUtilsWraps.between(value, startValue, endValue, includeStart, includeEnd)).findFirst().orElse(null);
    }

    @Nullable
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Number & Comparable<? extends Number>> T firstNegative(@Nullable T... values) {
        if (ArrayUtils.isEmpty(values)) {
            return null;
        }
        return Arrays.stream(values).filter(NumberUtilsWraps::isNegative).findFirst().orElse(null);
    }

    @Nullable
    @SafeVarargs
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static <T extends Number & Comparable<? extends Number>> T firstPositive(@Nullable T... values) {
        if (ArrayUtils.isEmpty(values)) {
            return null;
        }
        return Arrays.stream(values).filter(NumberUtilsWraps::isPositive).findFirst().orElse(null);
    }

    public static <T extends Number & Comparable<? extends Number>> void ifNegative(@Nullable T value, @Nullable Consumer<? super T> action) {
        ifNegativeOrElse(value, action, null);
    }

    public static <T extends Number & Comparable<? extends Number>> void ifNegative(@Nullable T value, @Nullable Runnable action) {
        ifNegativeOrElse(value, action, null);
    }

    public static <T extends Number & Comparable<? extends Number>> void ifNegativeOrElse(@Nullable T value, @Nullable Consumer<? super T> presentAction, @Nullable Consumer<? super T> absentAction) {
        if (isNegative(value)) {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifNegativeOrElse(@Nullable T value, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isNegative(value)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifNotNegative(@Nullable T value, @Nullable Consumer<? super T> action) {
        if (isNotNegative(value) && action != null) {
            action.accept(value);
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifNotNegative(@Nullable T value, @Nullable Runnable action) {
        if (isNotNegative(value) && action != null) {
            action.run();
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifPositive(@Nullable T value, @Nullable Consumer<? super T> action) {
        ifPositiveOrElse(value, action, null);
    }

    public static <T extends Number & Comparable<? extends Number>> void ifPositive(@Nullable T value, @Nullable Runnable action) {
        ifPositiveOrElse(value, action, null);
    }

    public static <T extends Number & Comparable<? extends Number>> void ifPositiveOrElse(@Nullable T value, @Nullable Consumer<? super T> presentAction, @Nullable Consumer<? super T> absentAction) {
        if (isPositive(value)) {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifPositiveOrElse(@Nullable T value, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isPositive(value)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifNotPositive(@Nullable T value, @Nullable Consumer<? super T> action) {
        if (isNotPositive(value) && action != null) {
            action.accept(value);
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifNotPositive(@Nullable T value, @Nullable Runnable action) {
        if (isNotPositive(value) && action != null) {
            action.run();
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifZero(@Nullable T value, @Nullable Consumer<? super T> action) {
        ifZeroOrElse(value, action, null);
    }

    public static <T extends Number & Comparable<? extends Number>> void ifZero(@Nullable T value, @Nullable Runnable action) {
        ifZeroOrElse(value, action, null);
    }

    public static <T extends Number & Comparable<? extends Number>> void ifZeroOrElse(@Nullable T value, @Nullable Consumer<? super T> presentAction, @Nullable Consumer<? super T> absentAction) {
        if (isZero(value)) {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifZeroOrElse(@Nullable T value, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isZero(value)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifZeroOrNegative(@Nullable T value, @Nullable Consumer<? super T> action) {
        if (isZeroOrNegative(value) && action != null) {
            action.accept(value);
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifZeroOrNegative(@Nullable T value, @Nullable Runnable action) {
        if (isZeroOrNegative(value) && action != null) {
            action.run();
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifZeroOrPositive(@Nullable T value, @Nullable Consumer<? super T> action) {
        if (isZeroOrPositive(value) && action != null) {
            action.accept(value);
        }
    }

    public static <T extends Number & Comparable<? extends Number>> void ifZeroOrPositive(@Nullable T value, @Nullable Runnable action) {
        if (isZeroOrPositive(value) && action != null) {
            action.run();
        }
    }

    /**
     * @see org.apache.commons.lang3.builder.CompareToBuilder
     */
    public static <T extends Number & Comparable<? extends Number>> boolean isNegative(@Nullable T value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Integer alias) {
            return ObjectUtils.compare(alias, NumberUtils.INTEGER_ZERO) < 0;
        } else if (value instanceof Long alias) {
            return ObjectUtils.compare(alias, NumberUtils.LONG_ZERO) < 0;
        } else if (value instanceof Short alias) {
            return ObjectUtils.compare(alias, NumberUtils.SHORT_ZERO) < 0;
        } else if (value instanceof Byte alias) {
            return ObjectUtils.compare(alias, NumberUtils.BYTE_ZERO) < 0;
        } else if (value instanceof Double alias) {
            return ObjectUtils.compare(alias, NumberUtils.DOUBLE_ZERO) < 0;
        } else if (value instanceof Float alias) {
            return ObjectUtils.compare(alias, NumberUtils.FLOAT_ZERO) < 0;
        } else if (value instanceof Fraction alias) {
            return ObjectUtils.compare(alias.floatValue(), NumberUtils.FLOAT_ZERO) < 0;
        } else if (value instanceof BigDecimal alias) {
            return ObjectUtils.compare(alias, BigDecimal.ZERO) < 0;
        } else if (value instanceof BigInteger alias) {
            return ObjectUtils.compare(alias, BigInteger.ZERO) < 0;
        }
        return value.longValue() < 0L;
    }

    public static <T extends Number & Comparable<? extends Number>> boolean isNotNegative(@Nullable T value) {
        return !isNegative(value);
    }

    /**
     * @see org.apache.commons.lang3.builder.CompareToBuilder
     */
    public static <T extends Number & Comparable<? extends Number>> boolean isPositive(@Nullable T value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Integer alias) {
            return ObjectUtils.compare(alias, NumberUtils.INTEGER_ZERO) > 0;
        } else if (value instanceof Long alias) {
            return ObjectUtils.compare(alias, NumberUtils.LONG_ZERO) > 0;
        } else if (value instanceof Short alias) {
            return ObjectUtils.compare(alias, NumberUtils.SHORT_ZERO) > 0;
        } else if (value instanceof Byte alias) {
            return ObjectUtils.compare(alias, NumberUtils.BYTE_ZERO) > 0;
        } else if (value instanceof Double alias) {
            return ObjectUtils.compare(alias, NumberUtils.DOUBLE_ZERO) > 0;
        } else if (value instanceof Float alias) {
            return ObjectUtils.compare(alias, NumberUtils.FLOAT_ZERO) > 0;
        } else if (value instanceof Fraction alias) {
            return ObjectUtils.compare(alias.floatValue(), NumberUtils.FLOAT_ZERO) > 0;
        } else if (value instanceof BigDecimal alias) {
            return ObjectUtils.compare(alias, BigDecimal.ZERO) > 0;
        } else if (value instanceof BigInteger alias) {
            return ObjectUtils.compare(alias, BigInteger.ZERO) > 0;
        }
        return value.longValue() > 0L;
    }

    public static <T extends Number & Comparable<? extends Number>> boolean isNotPositive(@Nullable T value) {
        return !isPositive(value);
    }

    public static <T extends Number & Comparable<? extends Number>> boolean isZero(@Nullable T value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Integer alias) {
            return ObjectUtils.compare(alias, NumberUtils.INTEGER_ZERO) == 0;
        } else if (value instanceof Long alias) {
            return ObjectUtils.compare(alias, NumberUtils.LONG_ZERO) == 0;
        } else if (value instanceof Short alias) {
            return ObjectUtils.compare(alias, NumberUtils.SHORT_ZERO) == 0;
        } else if (value instanceof Byte alias) {
            return ObjectUtils.compare(alias, NumberUtils.BYTE_ZERO) == 0;
        } else if (value instanceof Double alias) {
            return ObjectUtils.compare(alias, NumberUtils.DOUBLE_ZERO) == 0;
        } else if (value instanceof Float alias) {
            return ObjectUtils.compare(alias, NumberUtils.FLOAT_ZERO) == 0;
        } else if (value instanceof Fraction alias) {
            return ObjectUtils.compare(alias.floatValue(), NumberUtils.FLOAT_ZERO) == 0;
        } else if (value instanceof BigDecimal alias) {
            return ObjectUtils.compare(alias, BigDecimal.ZERO) == 0;
        } else if (value instanceof BigInteger alias) {
            return ObjectUtils.compare(alias, BigInteger.ZERO) == 0;
        }
        return false;
    }

    public static <T extends Number & Comparable<? extends Number>> boolean isZeroOrNull(@Nullable T value) {
        return value == null || isZero(value);
    }

    public static <T extends Number & Comparable<? extends Number>> boolean isZeroOrNegative(@Nullable T value) {
        return isZero(value) || isNegative(value);
    }

    public static <T extends Number & Comparable<? extends Number>> boolean isZeroOrPositive(@Nullable T value) {
        return isZero(value) || isPositive(value);
    }

    @Nullable
    public static Float multiplyByHundred(@Nullable Float value) {
        return multiplyByHundred(value, null);
    }

    @Nullable
    public static Float multiplyByHundred(@Nullable Float value, @Nullable Float defaultValue) {
        return multiplyByHundred(value, NumberUtils.INTEGER_TWO, null, defaultValue);
    }

    @Nullable
    public static Float multiplyByHundred(@Nullable Float value, int scale, @Nullable RoundingMode mode) {
        return multiplyByHundred(value, scale, mode, null);
    }

    @Nullable
    public static Float multiplyByHundred(@Nullable Float value, int scale, @Nullable RoundingMode mode, @Nullable Float defaultValue) {
        return (value == null) ? defaultValue : Float.valueOf(NumberUtils.toScaledBigDecimal(value * 100f, scale, mode).floatValue());
    }

    @Nullable
    public static Double multiplyByHundred(@Nullable Double value) {
        return multiplyByHundred(value, null);
    }

    @Nullable
    public static Double multiplyByHundred(@Nullable Double value, @Nullable Double defaultValue) {
        return multiplyByHundred(value, NumberUtils.INTEGER_TWO, null, defaultValue);
    }

    @Nullable
    public static Double multiplyByHundred(@Nullable Double value, int scale, @Nullable RoundingMode mode) {
        return multiplyByHundred(value, scale, mode, null);
    }

    @Nullable
    public static Double multiplyByHundred(@Nullable Double value, int scale, @Nullable RoundingMode mode, @Nullable Double defaultValue) {
        return (value == null) ? defaultValue : Double.valueOf(NumberUtils.toScaledBigDecimal(value * 100d, scale, mode).doubleValue());
    }

    @Nullable
    public static BigDecimal multiplyByHundred(@Nullable BigDecimal value) {
        return multiplyByHundred(value, null);
    }

    @Nullable
    public static BigDecimal multiplyByHundred(@Nullable BigDecimal value, @Nullable BigDecimal defaultValue) {
        return multiplyByHundred(value, NumberUtils.INTEGER_TWO, null, defaultValue);
    }

    @Nullable
    public static BigDecimal multiplyByHundred(@Nullable BigDecimal value, int scale, @Nullable RoundingMode mode) {
        return multiplyByHundred(value, scale, mode, null);
    }

    @Nullable
    public static BigDecimal multiplyByHundred(@Nullable BigDecimal value, int scale, @Nullable RoundingMode mode, @Nullable BigDecimal defaultValue) {
        return (value == null) ? defaultValue : NumberUtils.toScaledBigDecimal(value.multiply(new BigDecimal(100)), scale, mode);
    }

    public static <T extends Number & Comparable<? extends Number>> T parseAs(@Nullable String value, @Nullable Class<T> expectedType) throws NumberFormatException, ClassCastException {
        return parseAs(value, expectedType, null);
    }

    public static <T extends Number & Comparable<? extends Number>> T parseAs(@Nullable String value, @Nullable Class<T> expectedType, @Nullable T defaultValue) throws NumberFormatException, ClassCastException {
        if (StringUtils.isBlank(value) || expectedType == null) {
            return defaultValue;
        }
        if (expectedType == BigDecimal.class) {
            return expectedType.cast(NumberUtils.createBigDecimal(value));
        } else if (expectedType == BigInteger.class) {
            return expectedType.cast(NumberUtils.createBigInteger(value));
        } else if (expectedType == Byte.class) {
            return expectedType.cast(Byte.valueOf(value));
        } else if (expectedType == Double.class) {
            return expectedType.cast(Double.valueOf(value));
        } else if (expectedType == Float.class) {
            return expectedType.cast(Float.valueOf(value));
        } else if (expectedType == Integer.class) {
            return expectedType.cast(NumberUtils.createInteger(value));
        } else if (expectedType == Long.class) {
            return expectedType.cast(NumberUtils.createLong(value));
        } else if (expectedType == Short.class) {
            return expectedType.cast(Short.valueOf(value));
        }
        throw new UnsupportedClassException("Unsupported expected type: " + expectedType.getName());
    }

    @Nullable
    public static <T extends Number & Comparable<? extends Number>> T parseAsQuietly(@Nullable String value, @Nullable Class<T> expectedType) {
        return parseAsQuietly(value, expectedType, null);
    }

    @Nullable
    public static <T extends Number & Comparable<? extends Number>> T parseAsQuietly(@Nullable String value, @Nullable Class<T> expectedType, @Nullable T defaultValue) {
        try {
            return parseAs(value, expectedType, defaultValue);
        } catch (Exception ignored) {
        }
        return defaultValue;
    }

    @Nullable
    public static BigDecimal toBigDecimal(@Nullable String value) {
        return toBigDecimal(value, null);
    }

    @Nullable
    public static BigDecimal toBigDecimal(@Nullable String value, @Nullable BigDecimal defaultValue) {
        return parseAsQuietly(value, BigDecimal.class, defaultValue);
    }

    @Nullable
    public static BigInteger toBigInteger(@Nullable String value) {
        return toBigInteger(value, null);
    }

    @Nullable
    public static BigInteger toBigInteger(@Nullable String value, @Nullable BigInteger defaultValue) {
        return parseAsQuietly(value, BigInteger.class, defaultValue);
    }

    @Nullable
    public static Byte toByteObject(@Nullable String value) {
        return toByteObject(value, null);
    }

    @Nullable
    public static Byte toByteObject(@Nullable String value, @Nullable Byte defaultValue) {
        return parseAsQuietly(value, Byte.class, defaultValue);
    }

    @Nullable
    public static Double toDoubleObject(@Nullable String value) {
        return toDoubleObject(value, null);
    }

    @Nullable
    public static Double toDoubleObject(@Nullable String value, @Nullable Double defaultValue) {
        return parseAsQuietly(value, Double.class, defaultValue);
    }

    @Nullable
    public static Float toFloatObject(@Nullable String value) {
        return toFloatObject(value, null);
    }

    @Nullable
    public static Float toFloatObject(@Nullable String value, @Nullable Float defaultValue) {
        return parseAsQuietly(value, Float.class, defaultValue);
    }

    @Nullable
    public static Integer toIntegerObject(@Nullable String value) {
        return toIntegerObject(value, null);
    }

    @Nullable
    public static Integer toIntegerObject(@Nullable String value, @Nullable Integer defaultValue) {
        return parseAsQuietly(value, Integer.class, defaultValue);
    }

    @Nullable
    public static Long toLongObject(@Nullable String value) {
        return toLongObject(value, null);
    }

    @Nullable
    public static Long toLongObject(@Nullable String value, @Nullable Long defaultValue) {
        return parseAsQuietly(value, Long.class, defaultValue);
    }

    @Nullable
    public static Short toShortObject(@Nullable String value) {
        return toShortObject(value, null);
    }

    @Nullable
    public static Short toShortObject(@Nullable String value, @Nullable Short defaultValue) {
        return parseAsQuietly(value, Short.class, defaultValue);
    }

    @Nullable
    public static <T extends Number> String toHexPrefixedString(@Nullable T value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer || value instanceof Byte || value instanceof Short) {
            return StringUtils.join(SymbolVariantConst.HEX_PREFIX, Integer.toHexString(value.intValue()));
        } else if (value instanceof Double alias) {
            return StringUtils.join(SymbolVariantConst.HEX_PREFIX, Double.toHexString(alias));
        }
        return StringUtils.join(SymbolVariantConst.HEX_PREFIX, Long.toHexString(value.longValue()));
    }

    public static long unboxToPrimitive(@Nullable Long value) {
        return unboxToPrimitive(value, NumberUtils.LONG_ZERO);
    }

    public static long unboxToPrimitive(@Nullable Long value, long defaultValue) {
        return (value == null) ? defaultValue : value;
    }

    public static int unboxToPrimitive(@Nullable Integer value) {
        return unboxToPrimitive(value, NumberUtils.INTEGER_ZERO);
    }

    public static int unboxToPrimitive(@Nullable Integer value, int defaultValue) {
        return (value == null) ? defaultValue : value;
    }

    public static short unboxToPrimitive(@Nullable Short value) {
        return unboxToPrimitive(value, NumberUtils.SHORT_ZERO);
    }

    public static short unboxToPrimitive(@Nullable Short value, short defaultValue) {
        return (value == null) ? defaultValue : value;
    }

    public static byte unboxToPrimitive(@Nullable Byte value) {
        return unboxToPrimitive(value, NumberUtils.BYTE_ZERO);
    }

    public static byte unboxToPrimitive(@Nullable Byte value, byte defaultValue) {
        return (value == null) ? defaultValue : value;
    }

    public static double unboxToPrimitive(@Nullable Double value) {
        return unboxToPrimitive(value, NumberUtils.DOUBLE_ZERO);
    }

    public static double unboxToPrimitive(@Nullable Double value, double defaultValue) {
        return (value == null) ? defaultValue : value;
    }

    public static float unboxToPrimitive(@Nullable Float value) {
        return unboxToPrimitive(value, NumberUtils.FLOAT_ZERO);
    }

    public static float unboxToPrimitive(@Nullable Float value, float defaultValue) {
        return (value == null) ? defaultValue : value;
    }
}
