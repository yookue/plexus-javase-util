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

package com.yookue.commonplexus.javaseutil.constant;


import java.util.Locale;


/**
 * Combinations of locale areas
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public abstract class LocaleAreaCombo {
    public static final Locale[] CHINESE_LOCALES = new Locale[]{Locale.CHINESE, Locale.SIMPLIFIED_CHINESE, Locale.TRADITIONAL_CHINESE};
    public static final Locale[] WESTERN_LOCALES = new Locale[]{Locale.ENGLISH, Locale.FRENCH, Locale.GERMAN, Locale.ITALIAN};
}
