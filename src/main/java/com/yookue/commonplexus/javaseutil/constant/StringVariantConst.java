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


/**
 * Constants for string variants
 *
 * @author David Hsing
 * @see org.apache.commons.lang3.StringUtils
 * @see "org.springframework.beans.PropertyAccessor"
 * @see "org.springframework.util.StringUtils"
 * @see "org.springframework.util.ClassUtils"
 * @see "org.springframework.util.ResourceUtils"
 * @see "org.springframework.util.SystemPropertyUtils"
 */
@SuppressWarnings("unused")
public abstract class StringVariantConst {
    public static final String EMPTY = "";    // $NON-NLS-1$
    public static final String SPACE = " ";    // $NON-NLS-1$

    public static final String ATTACHMENT = "attachment";    // $NON-NLS-1$
    public static final String AUTH = "auth";    // $NON-NLS-1$
    public static final String CAPTCHA = "captcha";    // $NON-NLS-1$
    public static final String CLASSES = "classes";    // $NON-NLS-1$
    public static final String CLASSPATH = "classpath";    // $NON-NLS-1$
    // @see "org.springframework.core.io.ResourceLoader#CLASSPATH_URL_PREFIX"
    // @see "org.springframework.util.ResourceUtils#CLASSPATH_URL_PREFIX"
    public static final String CLASSPATH_COLON = CLASSPATH + CharVariantConst.COLON;
    // @see "org.springframework.core.io.support.ResourcePatternResolver#CLASSPATH_ALL_URL_PREFIX"
    public static final String CLASSPATH_STAR_COLON = CLASSPATH + "*:";    // $NON-NLS-1$
    public static final String CURRENT = "current";    // $NON-NLS-1$
    public static final String DEFAULT = "default";    // $NON-NLS-1$
    public static final String ENABLED = "enabled";    // $NON-NLS-1$
    // @see "org.springframework.util.ResourceUtils#URL_PROTOCOL_FILE"
    public static final String FILE = "file";    // $NON-NLS-1$
    public static final String FILENAME = "filename";    // $NON-NLS-1$
    public static final String INSTANCE = "instance";    // $NON-NLS-1$
    // @see "org.springframework.mock.web.MockHttpSession#SESSION_COOKIE_NAME"
    public static final String JSESSION = "JSESSION";    // $NON-NLS-1$
    public static final String JSESSIONID = "JSESSIONID";    // $NON-NLS-1$
    public static final String LANG = "lang";    // $NON-NLS-1$
    public static final String LANG_TAG = "langTag";    // $NON-NLS-1$
    public static final String LATEST = "latest";    // $NON-NLS-1$
    public static final String LOCALE = "locale";    // $NON-NLS-1$
    public static final String LOGGING = "logging";    // $NON-NLS-1$
    public static final String MESSAGE = "message";    // $NON-NLS-1$
    public static final String META_INF = "META-INF";    // $NON-NLS-1$
    public static final String NAME = "name";    // $NON-NLS-1$
    public static final String NONE = "none";    // $NON-NLS-1$
    public static final String NULL = "null";    // $NON-NLS-1$
    public static final String PROPERTY = "property";    // $NON-NLS-1$
    public static final String PROPERTIES = "properties";    // $NON-NLS-1$
    public static final String RANDOM = "random";    // $NON-NLS-1$
    public static final String REFERRER = "referrer";    // $NON-NLS-1$
    public static final String REMEMBER_ME = "remember-me";    // $NON-NLS-1$
    public static final String SERIAL_VERSION_UID = "serialVersionUID";    // $NON-NLS-1$
    public static final String SESSION = "SESSION";    // $NON-NLS-1$
    public static final String SYSTEM = "system";    // $NON-NLS-1$
    public static final String TEST_CLASSES = "test_classes";    // $NON-NLS-1$
    public static final String THEME = "theme";    // $NON-NLS-1$
    public static final String TRACE_ID = "traceId";    // $NON-NLS-1$
    public static final String UNDEFINED = "undefined";    // $NON-NLS-1$
    public static final String UNKNOWN = "unknown";    // $NON-NLS-1$
    public static final String URI = "URI";    // $NON-NLS-1$
    public static final String URL = "URL";    // $NON-NLS-1$
    public static final String VALUE = "value";    // $NON-NLS-1$
    public static final String WEB_INF = "WEB-INF";    // $NON-NLS-1$
}
