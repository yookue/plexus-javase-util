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
 *
 * @see java.util.ResourceBundle
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ResourceBundleWraps {
    /**
     * Gets a localized message from the resource bundle with locale lookup support
     *
     * @param baseName the base name of the resource bundle
     * @param locale the locale for which to get the message
     * @param loader the class loader to use for loading the resource bundle
     * @param key the key of the message to retrieve
     *
     * @return the localized message, or {@code null} if not found
     */
    @Nullable
    public static String getLocaleLookupMessage(@Nullable String baseName, @Nullable Locale locale, @Nullable ClassLoader loader, @Nullable String key) {
        return getLocaleLookupMessage(baseName, locale, loader, key, null);
    }

    /**
     * Gets a localized message from the resource bundle with locale lookup support and default value
     *
     * @param baseName the base name of the resource bundle
     * @param locale the locale for which to get the message
     * @param key the key of the message to retrieve
     * @param defaultValue the default value to return if the message is not found
     *
     * @return the localized message, or the default value if not found
     */
    @Nullable
    public static String getLocaleLookupMessage(@Nullable String baseName, @Nullable Locale locale, @Nullable String key, @Nullable String defaultValue) {
        return getLocaleLookupMessage(baseName, locale, null, key, defaultValue);
    }

    /**
     * Gets a localized message from the resource bundle with locale lookup support
     *
     * <p>
     * This method searches through the locale lookup list to find the message in the most appropriate locale.
     * It tries each locale in the lookup list until a non-empty message is found.
     * </p>
     *
     * @param baseName the base name of the resource bundle
     * @param locale the locale for which to get the message
     * @param loader the class loader to use for loading the resource bundle (can be null)
     * @param key the key of the message to retrieve
     * @param defaultValue the default value to return if the message is not found
     *
     * @return the localized message, or the default value if not found or if any required parameter is blank/null
     */
    @Nullable
    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    public static String getLocaleLookupMessage(@Nullable String baseName, @Nullable Locale locale, @Nullable ClassLoader loader, @Nullable String key, @Nullable String defaultValue) {
        if (StringUtils.isAnyBlank(baseName, key) || locale == null) {
            return defaultValue;
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
        return defaultValue;
    }
}
