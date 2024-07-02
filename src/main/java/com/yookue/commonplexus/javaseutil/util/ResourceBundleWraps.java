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


import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Utilities for {@link java.util.ResourceBundle}
 *
 * @author David Hsing
 * @see java.util.ResourceBundle
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ResourceBundleWraps {
    @Nullable
    public static String getLocaleLookupMessage(@Nullable String baseName, @Nullable Locale locale, @Nullable ClassLoader loader, @Nullable String key) {
        return getLocaleLookupMessage(baseName, locale, loader, key, null);
    }

    @Nullable
    public static String getLocaleLookupMessage(@Nullable String baseName, @Nullable Locale locale, @Nullable String key, @Nullable String defaults) {
        return getLocaleLookupMessage(baseName, locale, null, key, defaults);
    }

    /**
     * Returns the message from taglet/Messages properties file
     *
     * @return the title from taglet/Messages properties file
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String getLocaleLookupMessage(@Nullable String baseName, @Nullable Locale locale, @Nullable ClassLoader loader, @Nullable String key, @Nullable String defaults) {
        if (StringUtils.isAnyBlank(baseName, key) || locale == null) {
            return defaults;
        }
        List<Locale> lookups = LocaleUtils.localeLookupList(locale);
        for (Locale lookup : lookups) {
            try {
                ResourceBundle bundle = (loader == null) ? ResourceBundle.getBundle(baseName, lookup) : ResourceBundle.getBundle(baseName, lookup, loader);
                String result = bundle.getString(key);
                if (StringUtils.isNotEmpty(result)) {
                    return result;
                }
            } catch (Exception ignored) {
            }
        }
        return defaults;
    }
}
