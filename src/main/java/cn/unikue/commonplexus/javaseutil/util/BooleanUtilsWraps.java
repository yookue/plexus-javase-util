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


import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link org.apache.commons.lang3.BooleanUtils}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.lang3.BooleanUtils
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class BooleanUtilsWraps {
    private static final String ONE = "1";    // $NON-NLS-1$
    private static final String ZERO = "0";    // $NON-NLS-1$
    private static final String LOWER_T = "t";    // $NON-NLS-1$
    private static final String LOWER_F = "f";    // $NON-NLS-1$
    private static final String LOWER_Y = "y";    // $NON-NLS-1$
    private static final String LOWER_N = "n";    // $NON-NLS-1$
    private static final String UPPER_T = "T";    // $NON-NLS-1$
    private static final String UPPER_F = "F";    // $NON-NLS-1$
    private static final String UPPER_Y = "Y";    // $NON-NLS-1$
    private static final String UPPER_N = "N";    // $NON-NLS-1$

    /**
     * Check if all Boolean values are true.
     *
     * @param values the Boolean values to check
     * @return true if all values are true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allTrue(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtils::isTrue);
    }

    /**
     * Check if all String representations are true.
     *
     * @param values the String values to check
     * @return true if all values represent true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allTrue(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isTrue);
    }

    /**
     * Check if all Integer representations are true (1 = true).
     *
     * @param values the Integer values to check
     * @return true if all values represent true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allTrue(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isTrue);
    }

    /**
     * Check if all Object representations are true.
     *
     * @param values the Object values to check
     * @return true if all values represent true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allTrue(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isTrue);
    }

    /**
     * Check if all Boolean values are not true (false or null).
     *
     * @param values the Boolean values to check
     * @return true if all values are not true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotTrue(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtils::isNotTrue);
    }

    /**
     * Check if all String representations are not true.
     *
     * @param values the String values to check
     * @return true if all values do not represent true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotTrue(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotTrue);
    }

    /**
     * Check if all Integer representations are not true.
     *
     * @param values the Integer values to check
     * @return true if all values do not represent true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotTrue(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotTrue);
    }

    /**
     * Check if all Object representations are not true.
     *
     * @param values the Object values to check
     * @return true if all values do not represent true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotTrue(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotTrue);
    }

    /**
     * Check if all Boolean values are false.
     *
     * @param values the Boolean values to check
     * @return true if all values are false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allFalse(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtils::isFalse);
    }

    /**
     * Check if all String representations are false.
     *
     * @param values the String values to check
     * @return true if all values represent false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allFalse(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isFalse);
    }

    /**
     * Check if all Integer representations are false (0 = false).
     *
     * @param values the Integer values to check
     * @return true if all values represent false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allFalse(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isFalse);
    }

    /**
     * Check if all Object representations are false.
     *
     * @param values the Object values to check
     * @return true if all values represent false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allFalse(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isFalse);
    }

    /**
     * Check if all Boolean values are not false (true or null).
     *
     * @param values the Boolean values to check
     * @return true if all values are not false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotFalse(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtils::isNotFalse);
    }

    /**
     * Check if all String representations are not false.
     *
     * @param values the String values to check
     * @return true if all values do not represent false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotFalse(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotFalse);
    }

    /**
     * Check if all Integer representations are not false.
     *
     * @param values the Integer values to check
     * @return true if all values do not represent false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotFalse(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotFalse);
    }

    /**
     * Check if all Object representations are not false.
     *
     * @param values the Object values to check
     * @return true if all values do not represent false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean allNotFalse(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).allMatch(BooleanUtilsWraps::isNotFalse);
    }

    /**
     * Check if any Boolean value is true.
     *
     * @param values the Boolean values to check
     * @return true if any value is true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyTrue(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtils::isTrue);
    }

    /**
     * Check if any String representation is true.
     *
     * @param values the String values to check
     * @return true if any value represents true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyTrue(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isTrue);
    }

    /**
     * Check if any Integer representation is true.
     *
     * @param values the Integer values to check
     * @return true if any value represents true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyTrue(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isTrue);
    }

    /**
     * Check if any Object representation is true.
     *
     * @param values the Object values to check
     * @return true if any value represents true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyTrue(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isTrue);
    }

    /**
     * Check if any Boolean value is not true.
     *
     * @param values the Boolean values to check
     * @return true if any value is not true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotTrue(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtils::isNotTrue);
    }

    /**
     * Check if any String representation is not true.
     *
     * @param values the String values to check
     * @return true if any value does not represent true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotTrue(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotTrue);
    }

    /**
     * Check if any Integer representation is not true.
     *
     * @param values the Integer values to check
     * @return true if any value does not represent true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotTrue(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotTrue);
    }

    /**
     * Check if any Object representation is not true.
     *
     * @param values the Object values to check
     * @return true if any value does not represent true, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotTrue(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotTrue);
    }

    /**
     * Check if any Boolean value is false.
     *
     * @param values the Boolean values to check
     * @return true if any value is false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyFalse(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtils::isFalse);
    }

    /**
     * Check if any String representation is false.
     *
     * @param values the String values to check
     * @return true if any value represents false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyFalse(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isFalse);
    }

    /**
     * Check if any Integer representation is false.
     *
     * @param values the Integer values to check
     * @return true if any value represents false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyFalse(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isFalse);
    }

    /**
     * Check if any Object representation is false.
     *
     * @param values the Object values to check
     * @return true if any value represents false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyFalse(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isFalse);
    }

    /**
     * Check if any Boolean value is not false.
     *
     * @param values the Boolean values to check
     * @return true if any value is not false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotFalse(@Nullable Boolean... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtils::isNotFalse);
    }

    /**
     * Check if any String representation is not false.
     *
     * @param values the String values to check
     * @return true if any value does not represent false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotFalse(@Nullable String... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotFalse);
    }

    /**
     * Check if any Integer representation is not false.
     *
     * @param values the Integer values to check
     * @return true if any value does not represent false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotFalse(@Nullable Integer... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotFalse);
    }

    /**
     * Check if any Object representation is not false.
     *
     * @param values the Object values to check
     * @return true if any value does not represent false, false otherwise
     */
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static boolean anyNotFalse(@Nullable Object... values) {
        return ArrayUtils.isNotEmpty(values) && Arrays.stream(values).anyMatch(BooleanUtilsWraps::isNotFalse);
    }

    /**
     * Execute action if value is true (Boolean).
     *
     * @param value the Boolean value to check
     * @param action the action to execute if true
     */
    public static void ifTrue(@Nullable Boolean value, @Nullable Consumer<Boolean> action) {
        if (BooleanUtils.isTrue(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value is true (Boolean).
     *
     * @param value the Boolean value to check
     * @param action the runnable to execute if true
     */
    public static void ifTrue(@Nullable Boolean value, @Nullable Runnable action) {
        if (BooleanUtils.isTrue(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value represents true (String).
     *
     * @param value the String value to check
     * @param action the action to execute if true
     */
    public static void ifTrue(@Nullable String value, @Nullable Consumer<String> action) {
        if (isTrue(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value represents true (String).
     *
     * @param value the String value to check
     * @param action the runnable to execute if true
     */
    public static void ifTrue(@Nullable String value, @Nullable Runnable action) {
        if (isTrue(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value represents true (Integer).
     *
     * @param value the Integer value to check
     * @param action the action to execute if true
     */
    public static void ifTrue(@Nullable Integer value, @Nullable Consumer<Integer> action) {
        if (isTrue(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value represents true (Integer).
     *
     * @param value the Integer value to check
     * @param action the runnable to execute if true
     */
    public static void ifTrue(@Nullable Integer value, @Nullable Runnable action) {
        if (isTrue(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value represents true (Object).
     *
     * @param value the Object value to check
     * @param action the action to execute if true
     */
    public static void ifTrue(@Nullable Object value, @Nullable Consumer<Object> action) {
        if (isTrue(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value represents true (Object).
     *
     * @param value the Object value to check
     * @param action the runnable to execute if true
     */
    public static void ifTrue(@Nullable Object value, @Nullable Runnable action) {
        if (isTrue(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute presentAction if value is true, otherwise execute absentAction (Boolean with Consumer).
     *
     * @param value the Boolean value to check
     * @param presentAction the action to execute if true
     * @param absentAction the action to execute if false
     */
    public static void ifTrueOrElse(@Nullable Boolean value, @Nullable Consumer<Boolean> presentAction, @Nullable Consumer<Boolean> absentAction) {
        if (BooleanUtils.isTrue(value)) {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        }
    }

    /**
     * Execute presentAction if value is true, otherwise execute absentAction (Boolean with Runnable).
     *
     * @param value the Boolean value to check
     * @param presentAction the runnable to execute if true
     * @param absentAction the runnable to execute if false
     */
    public static void ifTrueOrElse(@Nullable Boolean value, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (BooleanUtils.isTrue(value)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    /**
     * Execute presentAction if value represents true, otherwise execute absentAction (String with Consumer).
     *
     * @param value the String value to check
     * @param presentAction the action to execute if true
     * @param absentAction the action to execute if false
     */
    public static void ifTrueOrElse(@Nullable String value, @Nullable Consumer<String> presentAction, @Nullable Consumer<String> absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        }
    }

    /**
     * Execute presentAction if value represents true, otherwise execute absentAction (String with Runnable).
     *
     * @param value the String value to check
     * @param presentAction the runnable to execute if true
     * @param absentAction the runnable to execute if false
     */
    public static void ifTrueOrElse(@Nullable String value, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    /**
     * Execute presentAction if value represents true, otherwise execute absentAction (Integer with Consumer).
     *
     * @param value the Integer value to check
     * @param presentAction the action to execute if true
     * @param absentAction the action to execute if false
     */
    public static void ifTrueOrElse(@Nullable Integer value, @Nullable Consumer<Integer> presentAction, @Nullable Consumer<Integer> absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        }
    }

    /**
     * Execute presentAction if value represents true, otherwise execute absentAction (Integer with Runnable).
     *
     * @param value the Integer value to check
     * @param presentAction the runnable to execute if true
     * @param absentAction the runnable to execute if false
     */
    public static void ifTrueOrElse(@Nullable Integer value, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    /**
     * Execute presentAction if value represents true, otherwise execute absentAction (Object with Consumer).
     *
     * @param value the Object value to check
     * @param presentAction the action to execute if true
     * @param absentAction the action to execute if false
     */
    public static void ifTrueOrElse(@Nullable Object value, @Nullable Consumer<Object> presentAction, @Nullable Consumer<Object> absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        } else {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        }
    }

    /**
     * Execute presentAction if value represents true, otherwise execute absentAction (Object with Runnable).
     *
     * @param value the Object value to check
     * @param presentAction the runnable to execute if true
     * @param absentAction the runnable to execute if false
     */
    public static void ifTrueOrElse(@Nullable Object value, @Nullable Runnable presentAction, @Nullable Runnable absentAction) {
        if (isTrue(value)) {
            if (presentAction != null) {
                presentAction.run();
            }
        } else {
            if (absentAction != null) {
                absentAction.run();
            }
        }
    }

    /**
     * Execute action if value is false (Boolean with Consumer).
     *
     * @param value the Boolean value to check
     * @param action the action to execute if false
     */
    public static void ifFalse(@Nullable Boolean value, @Nullable Consumer<Boolean> action) {
        if (BooleanUtils.isFalse(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value is false (Boolean with Runnable).
     *
     * @param value the Boolean value to check
     * @param action the runnable to execute if false
     */
    public static void ifFalse(@Nullable Boolean value, @Nullable Runnable action) {
        if (BooleanUtils.isFalse(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value represents false (String with Consumer).
     *
     * @param value the String value to check
     * @param action the action to execute if false
     */
    public static void ifFalse(@Nullable String value, @Nullable Consumer<String> action) {
        if (isFalse(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value represents false (String with Runnable).
     *
     * @param value the String value to check
     * @param action the runnable to execute if false
     */
    public static void ifFalse(@Nullable String value, @Nullable Runnable action) {
        if (isFalse(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value represents false (Integer with Consumer).
     *
     * @param value the Integer value to check
     * @param action the action to execute if false
     */
    public static void ifFalse(@Nullable Integer value, @Nullable Consumer<Integer> action) {
        if (isFalse(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value represents false (Integer with Runnable).
     *
     * @param value the Integer value to check
     * @param action the runnable to execute if false
     */
    public static void ifFalse(@Nullable Integer value, @Nullable Runnable action) {
        if (isFalse(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value represents false (Object with Consumer).
     *
     * @param value the Object value to check
     * @param action the action to execute if false
     */
    public static void ifFalse(@Nullable Object value, @Nullable Consumer<Object> action) {
        if (isFalse(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value represents false (Object with Runnable).
     *
     * @param value the Object value to check
     * @param action the runnable to execute if false
     */
    public static void ifFalse(@Nullable Object value, @Nullable Runnable action) {
        if (isFalse(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute absentAction if value is false, otherwise execute presentAction (Boolean with Consumer).
     *
     * @param value the Boolean value to check
     * @param absentAction the action to execute if false
     * @param presentAction the action to execute if true
     */
    public static void ifFalseOrElse(@Nullable Boolean value, @Nullable Consumer<Boolean> absentAction, @Nullable Consumer<Boolean> presentAction) {
        if (BooleanUtils.isFalse(value)) {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        }
    }

    /**
     * Execute absentAction if value is false, otherwise execute presentAction (Boolean with Runnable).
     *
     * @param value the Boolean value to check
     * @param absentAction the runnable to execute if false
     * @param presentAction the runnable to execute if true
     */
    public static void ifFalseOrElse(@Nullable Boolean value, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (BooleanUtils.isFalse(value)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    /**
     * Execute absentAction if value represents false, otherwise execute presentAction (String with Consumer).
     *
     * @param value the String value to check
     * @param absentAction the action to execute if false
     * @param presentAction the action to execute if true
     */
    public static void ifFalseOrElse(@Nullable String value, @Nullable Consumer<String> absentAction, @Nullable Consumer<String> presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        }
    }

    /**
     * Execute absentAction if value represents false, otherwise execute presentAction (String with Runnable).
     *
     * @param value the String value to check
     * @param absentAction the runnable to execute if false
     * @param presentAction the runnable to execute if true
     */
    public static void ifFalseOrElse(@Nullable String value, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    /**
     * Execute absentAction if value represents false, otherwise execute presentAction (Integer with Consumer).
     *
     * @param value the Integer value to check
     * @param absentAction the action to execute if false
     * @param presentAction the action to execute if true
     */
    public static void ifFalseOrElse(@Nullable Integer value, @Nullable Consumer<Integer> absentAction, @Nullable Consumer<Integer> presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        }
    }

    /**
     * Execute absentAction if value represents false, otherwise execute presentAction (Integer with Runnable).
     *
     * @param value the Integer value to check
     * @param absentAction the runnable to execute if false
     * @param presentAction the runnable to execute if true
     */
    public static void ifFalseOrElse(@Nullable Integer value, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    /**
     * Execute absentAction if value represents false, otherwise execute presentAction (Object with Consumer).
     *
     * @param value the Object value to check
     * @param absentAction the action to execute if false
     * @param presentAction the action to execute if true
     */
    public static void ifFalseOrElse(@Nullable Object value, @Nullable Consumer<Object> absentAction, @Nullable Consumer<Object> presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.accept(value);
            }
        } else {
            if (presentAction != null) {
                presentAction.accept(value);
            }
        }
    }

    /**
     * Execute absentAction if value represents false, otherwise execute presentAction (Object with Runnable).
     *
     * @param value the Object value to check
     * @param absentAction the runnable to execute if false
     * @param presentAction the runnable to execute if true
     */
    public static void ifFalseOrElse(@Nullable Object value, @Nullable Runnable absentAction, @Nullable Runnable presentAction) {
        if (isFalse(value)) {
            if (absentAction != null) {
                absentAction.run();
            }
        } else {
            if (presentAction != null) {
                presentAction.run();
            }
        }
    }

    /**
     * Execute action if value is not true (false or null) - Boolean with Consumer.
     *
     * @param value the Boolean value to check
     * @param action the action to execute if not true
     */
    public static void ifNotTrue(@Nullable Boolean value, @Nullable Consumer<Boolean> action) {
        if (BooleanUtils.isNotTrue(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value is not true (false or null) - Boolean with Runnable.
     *
     * @param value the Boolean value to check
     * @param action the runnable to execute if not true
     */
    public static void ifNotTrue(@Nullable Boolean value, @Nullable Runnable action) {
        if (BooleanUtils.isNotTrue(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value does not represent true - String with Consumer.
     *
     * @param value the String value to check
     * @param action the action to execute if not true
     */
    public static void ifNotTrue(@Nullable String value, @Nullable Consumer<String> action) {
        if (isNotTrue(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value does not represent true - String with Runnable.
     *
     * @param value the String value to check
     * @param action the runnable to execute if not true
     */
    public static void ifNotTrue(@Nullable String value, @Nullable Runnable action) {
        if (isNotTrue(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value does not represent true - Integer with Consumer.
     *
     * @param value the Integer value to check
     * @param action the action to execute if not true
     */
    public static void ifNotTrue(@Nullable Integer value, @Nullable Consumer<Integer> action) {
        if (isNotTrue(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value does not represent true - Integer with Runnable.
     *
     * @param value the Integer value to check
     * @param action the runnable to execute if not true
     */
    public static void ifNotTrue(@Nullable Integer value, @Nullable Runnable action) {
        if (isNotTrue(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value does not represent true - Object with Consumer.
     *
     * @param value the Object value to check
     * @param action the action to execute if not true
     */
    public static void ifNotTrue(@Nullable Object value, @Nullable Consumer<Object> action) {
        if (isNotTrue(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value does not represent true - Object with Runnable.
     *
     * @param value the Object value to check
     * @param action the runnable to execute if not true
     */
    public static void ifNotTrue(@Nullable Object value, @Nullable Runnable action) {
        if (isNotTrue(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value is not false (true or null) - Boolean with Consumer.
     *
     * @param value the Boolean value to check
     * @param action the action to execute if not false
     */
    public static void ifNotFalse(@Nullable Boolean value, @Nullable Consumer<Boolean> action) {
        if (BooleanUtils.isNotFalse(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value is not false (true or null) - Boolean with Runnable.
     *
     * @param value the Boolean value to check
     * @param action the runnable to execute if not false
     */
    public static void ifNotFalse(@Nullable Boolean value, @Nullable Runnable action) {
        if (BooleanUtils.isNotFalse(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value does not represent false - String with Consumer.
     *
     * @param value the String value to check
     * @param action the action to execute if not false
     */
    public static void ifNotFalse(@Nullable String value, @Nullable Consumer<String> action) {
        if (isNotFalse(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value does not represent false - String with Runnable.
     *
     * @param value the String value to check
     * @param action the runnable to execute if not false
     */
    public static void ifNotFalse(@Nullable String value, @Nullable Runnable action) {
        if (isNotFalse(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value does not represent false - Integer with Consumer.
     *
     * @param value the Integer value to check
     * @param action the action to execute if not false
     */
    public static void ifNotFalse(@Nullable Integer value, @Nullable Consumer<Integer> action) {
        if (isNotFalse(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value does not represent false - Integer with Runnable.
     *
     * @param value the Integer value to check
     * @param action the runnable to execute if not false
     */
    public static void ifNotFalse(@Nullable Integer value, @Nullable Runnable action) {
        if (isNotFalse(value) && action != null) {
            action.run();
        }
    }

    /**
     * Execute action if value does not represent false - Object with Consumer.
     *
     * @param value the Object value to check
     * @param action the action to execute if not false
     */
    public static void ifNotFalse(@Nullable Object value, @Nullable Consumer<Object> action) {
        if (isNotFalse(value) && action != null) {
            action.accept(value);
        }
    }

    /**
     * Execute runnable if value does not represent false - Object with Runnable.
     *
     * @param value the Object value to check
     * @param action the runnable to execute if not false
     */
    public static void ifNotFalse(@Nullable Object value, @Nullable Runnable action) {
        if (isNotFalse(value) && action != null) {
            action.run();
        }
    }

    /**
     * Check if value represents true (String).
     *
     * @param value the String value to check
     * @return true if value represents true, false otherwise
     */
    public static boolean isTrue(@Nullable String value) {
        return BooleanUtils.isTrue(BooleanUtils.toBooleanObject(value));
    }

    /**
     * Check if value represents true (Integer).
     *
     * @param value the Integer value to check
     * @return true if value represents true, false otherwise
     */
    public static boolean isTrue(@Nullable Integer value) {
        return BooleanUtils.isTrue(BooleanUtils.toBooleanObject(value));
    }

    /**
     * Check if value represents true (Object).
     *
     * @param value the Object value to check
     * @return true if value represents true, false otherwise
     */
    public static boolean isTrue(@Nullable Object value) {
        if (value == null) {
            return false;
        } else if (value instanceof Boolean alias) {
            return BooleanUtils.isTrue(alias);
        } else if (value instanceof String alias) {
            return isTrue(alias);
        } else if (value instanceof Integer alias) {
            return isTrue(alias);
        }
        return isTrue(Objects.toString(value));
    }

    /**
     * Check if value represents false (String).
     *
     * @param value the String value to check
     * @return true if value represents false, false otherwise
     */
    public static boolean isFalse(@Nullable String value) {
        return BooleanUtils.isFalse(BooleanUtils.toBooleanObject(value));
    }

    /**
     * Check if value represents false (Integer).
     *
     * @param value the Integer value to check
     * @return true if value represents false, false otherwise
     */
    public static boolean isFalse(@Nullable Integer value) {
        return BooleanUtils.isFalse(BooleanUtils.toBooleanObject(value));
    }

    /**
     * Check if value represents false (Object).
     *
     * @param value the Object value to check
     * @return true if value represents false, false otherwise
     */
    public static boolean isFalse(@Nullable Object value) {
        if (value == null) {
            return false;
        } else if (value instanceof Boolean alias) {
            return BooleanUtils.isFalse(alias);
        } else if (value instanceof String alias) {
            return isFalse(alias);
        } else if (value instanceof Integer alias) {
            return isFalse(alias);
        }
        return isFalse(Objects.toString(value));
    }

    /**
     * Check if value does not represent true (String).
     *
     * @param value the String value to check
     * @return true if value does not represent true, false otherwise
     */
    public static boolean isNotTrue(@Nullable String value) {
        return BooleanUtils.isNotTrue(BooleanUtils.toBooleanObject(value));
    }

    /**
     * Check if value does not represent true (Integer).
     *
     * @param value the Integer value to check
     * @return true if value does not represent true, false otherwise
     */
    public static boolean isNotTrue(@Nullable Integer value) {
        return BooleanUtils.isNotTrue(BooleanUtils.toBooleanObject(value));
    }

    /**
     * Check if value does not represent true (Object).
     *
     * @param value the Object value to check
     * @return true if value does not represent true, false otherwise
     */
    public static boolean isNotTrue(@Nullable Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof Boolean alias) {
            return BooleanUtils.isNotTrue(alias);
        } else if (value instanceof String alias) {
            return isNotTrue(alias);
        } else if (value instanceof Integer alias) {
            return isNotTrue(alias);
        }
        return isNotTrue(Objects.toString(value));
    }

    /**
     * Check if value does not represent false (String).
     *
     * @param value the String value to check
     * @return true if value does not represent false, false otherwise
     */
    public static boolean isNotFalse(@Nullable String value) {
        return BooleanUtils.isNotFalse(BooleanUtils.toBooleanObject(value));
    }

    /**
     * Check if value does not represent false (Integer).
     *
     * @param value the Integer value to check
     * @return true if value does not represent false, false otherwise
     */
    public static boolean isNotFalse(@Nullable Integer value) {
        return BooleanUtils.isNotFalse(BooleanUtils.toBooleanObject(value));
    }

    /**
     * Check if value does not represent false (Object).
     *
     * @param value the Object value to check
     * @return true if value does not represent false, false otherwise
     */
    public static boolean isNotFalse(@Nullable Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof Boolean alias) {
            return BooleanUtils.isNotFalse(alias);
        } else if (value instanceof String alias) {
            return isNotFalse(alias);
        } else if (value instanceof Integer alias) {
            return isNotFalse(alias);
        }
        return isNotFalse(Objects.toString(value));
    }

    /**
     * Convert String to Boolean object with specified truthy and falsy values.
     *
     * @param value the String to convert
     * @param truthy the String that represents true
     * @param falsy the String that represents false
     * @return the Boolean object, or null if conversion fails
     */
    public static Boolean toBooleanObject(@Nullable String value, @Nullable String truthy, @Nullable String falsy) {
        return toBooleanObject(value, truthy, falsy, null);
    }

    /**
     * Convert String to Boolean object with specified truthy, falsy and nil values.
     *
     * @param value the String to convert
     * @param truthy the String that represents true
     * @param falsy the String that represents false
     * @param nil the String that represents null
     * @return the Boolean object, or null if conversion fails
     */
    @Nullable
    public static Boolean toBooleanObject(@Nullable String value, @Nullable String truthy, @Nullable String falsy, @Nullable String nil) {
        try {
            return BooleanUtils.toBooleanObject(value, truthy, falsy, nil);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Convert String to Boolean object with case-insensitive matching.
     *
     * @param value the String to convert
     * @param truthy the String that represents true
     * @param falsy the String that represents false
     * @return the Boolean object, or null if conversion fails
     */
    public static Boolean toBooleanObjectIgnoreCase(@Nullable String value, @Nullable String truthy, @Nullable String falsy) {
        return toBooleanObjectIgnoreCase(value, truthy, falsy, null);
    }

    /**
     * Returns a Boolean object that converted from the {@code value} String
     *
     * @param value The String to check
     * @param truthy The String to match for true
     * @param falsy The String to match for false
     * @param nil The String to match for null
     *
     * @return a Boolean object that converted from the {@code value} String
     *
     * @throws java.lang.IllegalArgumentException if the String doesn't match
     * @see org.apache.commons.lang3.BooleanUtils#toBooleanObject(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public static Boolean toBooleanObjectIgnoreCase(@Nullable String value, @Nullable String truthy, @Nullable String falsy, @Nullable String nil) {
        if (value == null) {
            if (truthy == null) {
                return Boolean.TRUE;
            }
            if (falsy == null) {
                return Boolean.FALSE;
            }
            if (nil == null) {
                return null;
            }
        } else if (value.equalsIgnoreCase(truthy)) {
            return Boolean.TRUE;
        } else if (value.equalsIgnoreCase(falsy)) {
            return Boolean.FALSE;
        } else if (value.equalsIgnoreCase(nil)) {
            return null;
        }
        throw new IllegalArgumentException("The String did not match any specified value, even case insensitive");    // $NON-NLS-1$
    }

    /**
     * Convert String to Boolean object with case-insensitive matching (quiet version).
     *
     * @param value the String to convert
     * @param truthy the String that represents true
     * @param falsy the String that represents false
     * @return the Boolean object, or null if conversion fails
     */
    @Nullable
    public static Boolean toBooleanObjectIgnoreCaseQuietly(@Nullable String value, @Nullable String truthy, @Nullable String falsy) {
        return toBooleanObjectIgnoreCaseQuietly(value, truthy, falsy, null);
    }

    /**
     * Convert String to Boolean object with case-insensitive matching and nil value (quiet version).
     *
     * @param value the String to convert
     * @param truthy the String that represents true
     * @param falsy the String that represents false
     * @param nil the String that represents null
     * @return the Boolean object, or null if conversion fails
     */
    @Nullable
    public static Boolean toBooleanObjectIgnoreCaseQuietly(@Nullable String value, @Nullable String truthy, @Nullable String falsy, @Nullable String nil) {
        try {
            return toBooleanObjectIgnoreCase(value, truthy, falsy, nil);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Convert boolean value to String representation using specified characters.
     *
     * @param value the boolean value to convert
     * @param truthy the character for true
     * @param falsy the character for false
     * @return the String representation
     */
    public static String toString(boolean value, char truthy, char falsy) {
        return BooleanUtils.toString(value, CharUtils.toString(truthy), CharUtils.toString(falsy));
    }

    /**
     * Convert Boolean value to String representation using specified characters.
     *
     * @param value the Boolean value to convert
     * @param truthy the character for true
     * @param falsy the character for false
     * @return the String representation, or null if value is null
     */
    public static String toString(@Nullable Boolean value, char truthy, char falsy) {
        return toString(value, truthy, falsy, null);
    }

    /**
     * Convert Boolean value to String representation using specified characters with nil value.
     *
     * @param value the Boolean value to convert
     * @param truthy the character for true
     * @param falsy the character for false
     * @param nil the character for null
     * @return the String representation
     */
    public static String toString(@Nullable Boolean value, char truthy, char falsy, char nil) {
        return BooleanUtils.toString(value, CharUtils.toString(truthy), CharUtils.toString(falsy), CharUtils.toString(nil));
    }

    /**
     * Convert Boolean value to String representation using specified characters and nil String.
     *
     * @param value the Boolean value to convert
     * @param truthy the character for true
     * @param falsy the character for false
     * @param nil the String for null
     * @return the String representation
     */
    public static String toString(@Nullable Boolean value, char truthy, char falsy, String nil) {
        return BooleanUtils.toString(value, CharUtils.toString(truthy), CharUtils.toString(falsy), nil);
    }

    /**
     * Convert boolean value to "1" or "0".
     *
     * @param value the boolean value to convert
     * @return "1" if true, "0" if false
     */
    public static String toString10(boolean value) {
        return BooleanUtils.toString(value, ONE, ZERO);
    }

    /**
     * Convert Boolean value to "1", "0", or null.
     *
     * @param value the Boolean value to convert
     * @return "1" if true, "0" if false, null if null
     */
    public static String toString10(@Nullable Boolean value) {
        return BooleanUtils.toString(value, ONE, ZERO, null);
    }

    /**
     * Convert boolean value to "on" or "off".
     *
     * @param value the boolean value to convert
     * @param uppercase whether to use uppercase
     * @return "ON" or "OFF" if uppercase, "on" or "off" otherwise
     */
    public static String toStringOnOff(boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringOnOff(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    /**
     * Convert Boolean value to "on", "off", or null.
     *
     * @param value the Boolean value to convert
     * @param uppercase whether to use uppercase
     * @return "ON", "OFF", or null if null; uppercase based on parameter
     */
    public static String toStringOnOff(@Nullable Boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringOnOff(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    /**
     * Convert boolean value to "true" or "false".
     *
     * @param value the boolean value to convert
     * @param uppercase whether to use uppercase
     * @return "TRUE" or "FALSE" if uppercase, "true" or "false" otherwise
     */
    public static String toStringTrueFalse(boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringTrueFalse(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    /**
     * Convert Boolean value to "true", "false", or null.
     *
     * @param value the Boolean value to convert
     * @param uppercase whether to use uppercase
     * @return "TRUE", "FALSE", or null if null; uppercase based on parameter
     */
    public static String toStringTrueFalse(@Nullable Boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringTrueFalse(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    /**
     * Convert boolean value to "yes" or "no".
     *
     * @param value the boolean value to convert
     * @param uppercase whether to use uppercase
     * @return "YES" or "NO" if uppercase, "yes" or "no" otherwise
     */
    public static String toStringYesNo(boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringYesNo(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    /**
     * Convert Boolean value to "yes", "no", or null.
     *
     * @param value the Boolean value to convert
     * @param uppercase whether to use uppercase
     * @return "YES", "NO", or null if null; uppercase based on parameter
     */
    public static String toStringYesNo(@Nullable Boolean value, boolean uppercase) {
        String result = BooleanUtils.toStringYesNo(value);
        return uppercase ? StringUtils.upperCase(result) : result;
    }

    /**
     * Convert boolean value to "t" or "f".
     *
     * @param value the boolean value to convert
     * @return "t" if true, "f" if false
     */
    public static String toStringTF(boolean value) {
        return toStringTF(value, false);
    }

    /**
     * Convert boolean value to "T" or "F" (or lowercase).
     *
     * @param value the boolean value to convert
     * @param uppercase whether to use uppercase
     * @return "T" or "F" if uppercase, "t" or "f" otherwise
     */
    public static String toStringTF(boolean value, boolean uppercase) {
        return BooleanUtils.toString(value, (uppercase ? UPPER_T : LOWER_T), (uppercase ? UPPER_F : LOWER_F));
    }

    /**
     * Convert Boolean value to "t", "f", or null.
     *
     * @param value the Boolean value to convert
     * @return "t" if true, "f" if false, null if null
     */
    public static String toStringTF(@Nullable Boolean value) {
        return toStringTF(value, false);
    }

    /**
     * Convert Boolean value to "T", "F", or null (or lowercase).
     *
     * @param value the Boolean value to convert
     * @param uppercase whether to use uppercase
     * @return "T", "F", or null if null; uppercase based on parameter
     */
    public static String toStringTF(@Nullable Boolean value, boolean uppercase) {
        return BooleanUtils.toString(value, (uppercase ? UPPER_T : LOWER_T), (uppercase ? UPPER_F : LOWER_F), null);
    }

    /**
     * Convert boolean value to "y" or "n".
     *
     * @param value the boolean value to convert
     * @return "y" if true, "n" if false
     */
    public static String toStringYN(boolean value) {
        return toStringYN(value, false);
    }

    /**
     * Convert boolean value to "Y" or "N" (or lowercase).
     *
     * @param value the boolean value to convert
     * @param uppercase whether to use uppercase
     * @return "Y" or "N" if uppercase, "y" or "n" otherwise
     */
    public static String toStringYN(boolean value, boolean uppercase) {
        return BooleanUtils.toString(value, (uppercase ? UPPER_Y : LOWER_Y), (uppercase ? UPPER_N : LOWER_N));
    }

    /**
     * Convert Boolean value to "y", "n", or null.
     *
     * @param value the Boolean value to convert
     * @return "y" if true, "n" if false, null if null
     */
    public static String toStringYN(@Nullable Boolean value) {
        return toStringYN(value, false);
    }

    /**
     * Convert Boolean value to "Y", "N", or null (or lowercase).
     *
     * @param value the Boolean value to convert
     * @param uppercase whether to use uppercase
     * @return "Y", "N", or null if null; uppercase based on parameter
     */
    public static String toStringYN(@Nullable Boolean value, boolean uppercase) {
        return BooleanUtils.toString(value, (uppercase ? UPPER_Y : LOWER_Y), (uppercase ? UPPER_N : LOWER_N), null);
    }
}
