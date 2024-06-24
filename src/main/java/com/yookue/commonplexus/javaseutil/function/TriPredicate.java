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


import java.util.Objects;


/**
 * Represents an operation that accepts three input arguments and return boolean result
 *
 * @param <T> the type of the first argument to the operation
 * @param <U> the type of the second argument to the operation
 * @param <V> the type of the third argument to the operation
 *
 * @author David Hsing
 * @see java.util.function.BiPredicate
 * @see org.apache.commons.lang3.function.TriFunction
 */
@FunctionalInterface
@SuppressWarnings("unused")
public interface TriPredicate<T, U, V> {
    /**
     * Evaluate this predicate on the given arguments
     *
     * @param t the first input argument
     * @param u the second input argument
     * @param v the third input argument
     */
    boolean test(T t, U u, V v);

    /**
     * Returns a composed predicate that represent a short-circuiting logical AND of this predicate and the {@code other} predicate
     * When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other} predicate is not evaluated
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller
     * If evaluation of this predicate throws an exception, the {@code other} predicate will not be evaluated
     *
     * @param other a predicate that will be logically-ANDed with this predicate
     *
     * @return a composed predicate that represent the short-circuiting logical AND of this predicate and the {@code other} predicate
     *
     * @throws NullPointerException if the {@code other} predicate is null
     */
    default TriPredicate<T, U, V> and(TriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (T t, U u, V v) -> test(t, u, v) && other.test(t, u, v);
    }

    /**
     * Returns a predicate that represent the logical negation of this predicate
     *
     * @return a predicate that represent the logical negation of this predicate
     */
    default TriPredicate<T, U, V> negate() {
        return (T t, U u, V v) -> !test(t, u, v);
    }

    /**
     * Returns a composed predicate that represent a short-circuiting logical OR of this predicate and the {@code other} predicate
     * When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other} predicate is not evaluated
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller
     * If evaluation of this predicate throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this predicate
     *
     * @return a composed predicate that represent the short-circuiting logical OR of this predicate and the {@code other} predicate
     *
     * @throws NullPointerException if the {@code other} predicate is null
     */
    default TriPredicate<T, U, V> or(TriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (T t, U u, V v) -> test(t, u, v) || other.test(t, u, v);
    }
}
