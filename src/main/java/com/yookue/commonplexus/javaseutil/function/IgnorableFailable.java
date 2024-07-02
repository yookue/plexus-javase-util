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

package com.yookue.commonplexus.javaseutil.function;


import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.function.Failable;
import org.apache.commons.lang3.function.FailableBiConsumer;
import org.apache.commons.lang3.function.FailableBiFunction;
import org.apache.commons.lang3.function.FailableBiPredicate;
import org.apache.commons.lang3.function.FailableBooleanSupplier;
import org.apache.commons.lang3.function.FailableCallable;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableDoubleSupplier;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.commons.lang3.function.FailableIntSupplier;
import org.apache.commons.lang3.function.FailableLongSupplier;
import org.apache.commons.lang3.function.FailablePredicate;
import org.apache.commons.lang3.function.FailableRunnable;
import org.apache.commons.lang3.function.FailableShortSupplier;
import org.apache.commons.lang3.function.FailableSupplier;
import org.apache.commons.lang3.math.NumberUtils;


/**
 * Attempts to address the fact that lambdas are supposed not to throw exceptions, at least not checked exceptions
 *
 * @author David Hsing
 * @reference "https://blog.csdn.net/Revivedsun/article/details/79906165/"
 * @see org.apache.commons.lang3.function.Failable
 * @see org.apache.commons.lang3.exception.ExceptionUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public abstract class IgnorableFailable {
    @Nonnull
    public static <T, U> BiConsumer<T, U> asBiConsumer(@Nonnull FailableBiConsumer<T, U, ?> consumer) {
        return asBiConsumer(consumer, null);
    }

    @Nonnull
    public static <T, U, E extends Throwable> BiConsumer<T, U> asBiConsumer(@Nonnull FailableBiConsumer<T, U, ?> consumer, @Nullable Class<E> throwable) {
        return (t, u) -> {
            try {
                consumer.accept(t, u);
            } catch (Throwable ex) {
                judgeThrowable(ex, throwable);
            }
        };
    }

    @Nonnull
    public static <T, U, R> BiFunction<T, U, R> asBiFunction(@Nonnull FailableBiFunction<T, U, R, ?> function) {
        return asBiFunction(function, null);
    }

    @Nonnull
    public static <T, U, R, E extends Throwable> BiFunction<T, U, R> asBiFunction(@Nonnull FailableBiFunction<T, U, R, ?> function, @Nullable Class<E> throwable) {
        return (t, u) -> {
            try {
                return function.apply(t, u);
            } catch (Throwable ex) {
                judgeThrowable(ex, throwable);
            }
            return null;
        };
    }

    @Nonnull
    public static <T, U> BiPredicate<T, U> asBiPredicate(@Nonnull FailableBiPredicate<T, U, ?> predicate) {
        return asBiPredicate(predicate, null);
    }

    @Nonnull
    public static <T, U, E extends Throwable> BiPredicate<T, U> asBiPredicate(@Nonnull FailableBiPredicate<T, U, ?> predicate, @Nullable Class<E> throwable) {
        return (t, u) -> {
            try {
                return predicate.test(t, u);
            } catch (Throwable ex) {
                judgeThrowable(ex, throwable);
            }
            return false;
        };
    }

    @Nonnull
    public static <V> Callable<V> asCallable(@Nonnull FailableCallable<V, ?> callable) {
        return asCallable(callable, null);
    }

    @Nonnull
    public static <V, E extends Throwable> Callable<V> asCallable(@Nonnull FailableCallable<V, ?> callable, @Nullable Class<E> throwable) {
        return () -> {
            try {
                return callable.call();
            } catch (Throwable ex) {
                judgeThrowable(ex, throwable);
            }
            return null;
        };
    }

    @Nonnull
    public static <T> Consumer<T> asConsumer(@Nonnull FailableConsumer<T, ?> consumer) {
        return asConsumer(consumer, null);
    }

    @Nonnull
    public static <T, E extends Throwable> Consumer<T> asConsumer(@Nonnull FailableConsumer<T, ?> consumer, @Nullable Class<E> throwable) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Throwable ex) {
                judgeThrowable(ex, throwable);
            }
        };
    }

    @Nonnull
    public static <T, R> Function<T, R> asFunction(@Nonnull FailableFunction<T, R, ?> function) {
        return asFunction(function, null);
    }

    @Nonnull
    public static <T, R, E extends Throwable> Function<T, R> asFunction(@Nonnull FailableFunction<T, R, ?> function, @Nullable Class<E> throwable) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Throwable ex) {
                judgeThrowable(ex, throwable);
            }
            return null;
        };
    }

    @Nonnull
    public static <T> Predicate<T> asPredicate(@Nonnull FailablePredicate<T, ?> predicate) {
        return asPredicate(predicate, null);
    }

    @Nonnull
    public static <T, E extends Throwable> Predicate<T> asPredicate(@Nonnull FailablePredicate<T, ?> predicate, @Nullable Class<E> throwable) {
        return t -> {
            try {
                return predicate.test(t);
            } catch (Throwable ex) {
                judgeThrowable(ex, throwable);
            }
            return false;
        };
    }

    @Nonnull
    public static Runnable asRunnable(@Nonnull FailableRunnable<?> runnable) {
        return asRunnable(runnable, null);
    }

    @Nonnull
    public static <E extends Throwable> Runnable asRunnable(@Nonnull FailableRunnable<?> runnable, @Nullable Class<E> throwable) {
        return () -> {
            try {
                runnable.run();
            } catch (Throwable ex) {
                judgeThrowable(ex, throwable);
            }
        };
    }

    @Nonnull
    public static <T> Supplier<T> asSupplier(@Nonnull FailableSupplier<T, ?> supplier) {
        return asSupplier(supplier, null);
    }

    @Nonnull
    public static <T, E extends Throwable> Supplier<T> asSupplier(@Nonnull FailableSupplier<T, ?> supplier, @Nullable Class<E> throwable) {
        return () -> {
            try {
                return supplier.get();
            } catch (Throwable ex) {
                judgeThrowable(ex, throwable);
            }
            return null;
        };
    }

    @Nullable
    public static <V> V call(@Nonnull FailableCallable<V, ?> callable) {
        return call(callable, null);
    }

    @Nullable
    public static <V, E extends Throwable> V call(@Nonnull FailableCallable<V, ?> callable, @Nullable Class<E> throwable) {
        try {
            return Failable.call(callable);
        } catch (Throwable ex) {
            judgeThrowable(ex, throwable);
        }
        return null;
    }

    @Nullable
    public static <T> T get(@Nonnull FailableSupplier<T, ?> supplier) {
        return get(supplier, null);
    }

    @Nullable
    public static <T, E extends Throwable> T get(@Nonnull FailableSupplier<T, ?> supplier, @Nullable Class<E> throwable) {
        try {
            return Failable.get(supplier);
        } catch (Throwable ex) {
            judgeThrowable(ex, throwable);
        }
        return null;
    }

    public static boolean getAsBoolean(@Nonnull FailableBooleanSupplier<?> supplier) {
        return getAsBoolean(supplier, null);
    }

    public static <E extends Throwable> boolean getAsBoolean(@Nonnull FailableBooleanSupplier<?> supplier, @Nullable Class<E> throwable) {
        try {
            return Failable.getAsBoolean(supplier);
        } catch (Throwable ex) {
            judgeThrowable(ex, throwable);
        }
        return false;
    }

    public static double getAsDouble(@Nonnull FailableDoubleSupplier<?> supplier) {
        return getAsDouble(supplier, null);
    }

    public static <E extends Throwable> double getAsDouble(@Nonnull FailableDoubleSupplier<?> supplier, @Nullable Class<E> throwable) {
        try {
            return Failable.getAsDouble(supplier);
        } catch (Throwable ex) {
            judgeThrowable(ex, throwable);
        }
        return NumberUtils.DOUBLE_ZERO;
    }

    public static int getAsInt(@Nonnull FailableIntSupplier<?> supplier) {
        return getAsInt(supplier, null);
    }

    public static <E extends Throwable> int getAsInt(@Nonnull FailableIntSupplier<?> supplier, @Nullable Class<E> throwable) {
        try {
            return Failable.getAsInt(supplier);
        } catch (Throwable ex) {
            judgeThrowable(ex, throwable);
        }
        return NumberUtils.INTEGER_ZERO;
    }

    public static long getAsLong(@Nonnull FailableLongSupplier<?> supplier) {
        return getAsLong(supplier, null);
    }

    public static <E extends Throwable> long getAsLong(@Nonnull FailableLongSupplier<?> supplier, @Nullable Class<E> throwable) {
        try {
            return Failable.getAsLong(supplier);
        } catch (Throwable ex) {
            judgeThrowable(ex, throwable);
        }
        return NumberUtils.LONG_ZERO;
    }

    public static short getAsShort(@Nonnull FailableShortSupplier<?> supplier) {
        return getAsShort(supplier, null);
    }

    public static <E extends Throwable> short getAsShort(@Nonnull FailableShortSupplier<?> supplier, @Nullable Class<E> throwable) {
        try {
            return Failable.getAsShort(supplier);
        } catch (Throwable ex) {
            judgeThrowable(ex, throwable);
        }
        return NumberUtils.SHORT_ZERO;
    }

    public static void run(@Nonnull FailableRunnable<?> runnable) {
        run(runnable, null);
    }

    public static <E extends Throwable> void run(@Nonnull FailableRunnable<?> runnable, @Nullable Class<E> throwable) {
        try {
            Failable.run(runnable);
        } catch (Throwable ex) {
            judgeThrowable(ex, throwable);
        }
    }

    public static <T, U> boolean test(@Nonnull FailableBiPredicate<T, U, ?> predicate, @Nullable T t, @Nullable U u) {
        return test(predicate, t, u, null);
    }

    public static <T, U, E extends Throwable> boolean test(@Nonnull FailableBiPredicate<T, U, ?> predicate, @Nullable T t, @Nullable U u, @Nullable Class<E> throwable) {
        try {
            return Failable.test(predicate, t, u);
        } catch (Throwable ex) {
            judgeThrowable(ex, throwable);
        }
        return false;
    }

    public static <T> boolean test(@Nonnull FailablePredicate<T, ?> predicate, @Nullable T t) {
        return test(predicate, t, null);
    }

    public static <T, E extends Throwable> boolean test(@Nonnull FailablePredicate<T, ?> predicate, @Nullable T t, @Nullable Class<E> throwable) {
        try {
            return Failable.test(predicate, t);
        } catch (Throwable ex) {
            judgeThrowable(ex, throwable);
        }
        return false;
    }

    protected static void judgeThrowable(@Nonnull Throwable ex, @Nullable Class<? extends Throwable> clazz) {
        if (clazz == null || clazz == Throwable.class || clazz == Exception.class) {
            return;
        }
        try {
            clazz.cast(ex);
        } catch (ClassCastException ignored) {
            Failable.rethrow(ex);
        }
    }
}
