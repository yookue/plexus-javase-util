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
 * Constants for http headers
 *
 * @author David Hsing
 * @see "org.springframework.http.HttpHeaders"
 * @see "org.apache.http.HttpHeaders"
 * @see "com.google.common.net.HttpHeaders"
 * @see "io.netty.handler.codec.http.HttpHeaders"
 * @see "org.springframework.web.filter.ForwardedHeaderFilter.ForwardedPrefixExtractor#initForwardedPrefix"
 */
@SuppressWarnings("unused")
public abstract class HttpHeaderConst {
    public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";    // $NON-NLS-1$
    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";    // $NON-NLS-1$
    public static final String XML_HTTP_REQUEST = "XMLHttpRequest";    // $NON-NLS-1$

    public static final String X_AUTH_TOKEN = "X-Auth-Token";    // $NON-NLS-1$
    public static final String X_CONTENT_TYPE_OPTIONS = "X-Content-Type-Options";    // $NON-NLS-1$
    public static final String X_DOWNLOAD_OPTIONS = "X-Download-Options";    // $NON-NLS-1$
    public static final String X_CSRF_TOKEN = "X-CSRF-Token";    // $NON-NLS-1$
    public static final String X_DO_NOT_TRACK = "X-Do-Not-Track";    // $NON-NLS-1$
    public static final String X_FORWARDED_FOR = "X-Forwarded-For";    // $NON-NLS-1$
    public static final String X_FORWARDED_HOST = "X-Forwarded-Host";    // $NON-NLS-1$
    public static final String X_FORWARDED_PORT = "X-Forwarded-Port";    // $NON-NLS-1$
    public static final String X_FORWARDED_PREFIX = "X-Forwarded-Prefix";    // $NON-NLS-1$
    public static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";    // $NON-NLS-1$
    public static final String X_FRAME_OPTIONS = "X-Frame-Options";    // $NON-NLS-1$
    public static final String X_MOZ = "X-Moz";    // $NON-NLS-1$
    public static final String X_POWERED_BY = "X-Powered-By";    // $NON-NLS-1$
    public static final String X_PURPOSE = "X-Purpose";    // $NON-NLS-1$
    public static final String X_REAL_IP = "X-Real-IP";    // $NON-NLS-1$
    public static final String X_REQUEST_ID = "X-Request-ID";    // $NON-NLS-1$
    public static final String X_REQUESTED_WITH = "X-Requested-With";    // $NON-NLS-1$
    public static final String X_USER_IP = "X-User-IP";    // $NON-NLS-1$
    public static final String X_XSS_PROTECTION = "X-XSS-Protection";    // $NON-NLS-1$

    // Log4j MDC
    public static final String X_TRACE_ID = "X-Trace-ID";    // $NON-NLS-1$
}
