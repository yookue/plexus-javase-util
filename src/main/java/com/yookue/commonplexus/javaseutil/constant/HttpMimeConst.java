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
 * Constants for http mime types
 *
 * @author David Hsing
 * @see "org.springframework.http.MediaType"
 */
@SuppressWarnings("unused")
public abstract class HttpMimeConst {
    public static final String APPLICATION = "application";    // $NON-NLS-1$
    public static final String ATOM_XML = "atom+xml";    // $NON-NLS-1$
    public static final String CBOR = "cbor";    // $NON-NLS-1$
    public static final String CHARSET_UTF8 = "charset=UTF-8";    // $NON-NLS-1$
    public static final String JAVASCRIPT = "javascript";    // $NON-NLS-1$
    public static final String JSON = "json";    // $NON-NLS-1$
    public static final String OCTET_STREAM = "octet-stream";    // $NON-NLS-1$
    public static final String PDF = "pdf";    // $NON-NLS-1$
    public static final String PROBLEM_JSON = "problem+json";    // $NON-NLS-1$
    public static final String PROBLEM_XML = "problem+xml";    // $NON-NLS-1$
    public static final String RSS_XML = "rss+xml";    // $NON-NLS-1$
    public static final String SOAP_JSON = "soap+json";    // $NON-NLS-1$
    public static final String SOAP_XML = "soap+xml";    // $NON-NLS-1$
    public static final String STREAM_JSON = "stream+json";    // $NON-NLS-1$
    public static final String STAR_JSON = "*+json";    // $NON-NLS-1$
    public static final String STAR_XML = "*+xml";    // $NON-NLS-1$
    public static final String X_NDJSON = "x-ndjson";    // $NON-NLS-1$
    public static final String X_WWW_FORM_URLENCODED = "x-www-form-urlencoded";    // $NON-NLS-1$
    public static final String XHTML_XML = "xhtml+xml";    // $NON-NLS-1$
    public static final String XML = "xml";    // $NON-NLS-1$

    public static final String IMAGE_GIF = "image/gif";    // $NON-NLS-1$
    public static final String IMAGE_JPEG = "image/jpeg";    // $NON-NLS-1$
    public static final String IMAGE_PNG = "image/png";    // $NON-NLS-1$

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";    // $NON-NLS-1$
    public static final String MULTIPART_MIXED = "multipart/mixed";    // $NON-NLS-1$
    public static final String MULTIPART_RELATED = "multipart/related";    // $NON-NLS-1$

    public static final String TEXT_CSS = "text/css";    // $NON-NLS-1$
    public static final String TEXT_HTML = "text/html";    // $NON-NLS-1$
    public static final String TEXT_MARKDOWN = "text/markdown";    // $NON-NLS-1$
    public static final String TEXT_PLAIN = "text/plain";    // $NON-NLS-1$
    public static final String TEXT_XML = "text/xml";    // $NON-NLS-1$

    public static final String APPLICATION_ATOM_XML = APPLICATION + CharVariantConst.SLASH + ATOM_XML;
    public static final String APPLICATION_CBOR = APPLICATION + CharVariantConst.SLASH + CBOR;
    public static final String APPLICATION_JAVASCRIPT = APPLICATION + CharVariantConst.SLASH + JAVASCRIPT;
    public static final String APPLICATION_JSON = APPLICATION + CharVariantConst.SLASH + JSON;
    public static final String APPLICATION_OCTET_STREAM = APPLICATION + CharVariantConst.SLASH + OCTET_STREAM;
    public static final String APPLICATION_PDF = APPLICATION + CharVariantConst.SLASH + PDF;
    public static final String APPLICATION_PROBLEM_JSON = APPLICATION + CharVariantConst.SLASH + PROBLEM_JSON;
    public static final String APPLICATION_PROBLEM_XML = APPLICATION + CharVariantConst.SLASH + PROBLEM_XML;
    public static final String APPLICATION_RSS_XML = APPLICATION + CharVariantConst.SLASH + RSS_XML;
    public static final String APPLICATION_SOAP_JSON = APPLICATION + CharVariantConst.SLASH + SOAP_JSON;
    public static final String APPLICATION_SOAP_XML = APPLICATION + CharVariantConst.SLASH + SOAP_XML;
    public static final String APPLICATION_STREAM_JSON = APPLICATION + CharVariantConst.SLASH + STREAM_JSON;
    public static final String APPLICATION_STAR_JSON = APPLICATION + CharVariantConst.SLASH + STAR_JSON;
    public static final String APPLICATION_STAR_XML = APPLICATION + CharVariantConst.SLASH + STAR_XML;
    public static final String APPLICATION_X_NDJSON = APPLICATION + CharVariantConst.SLASH + X_NDJSON;
    public static final String APPLICATION_X_WWW_FORM_URLENCODED = APPLICATION + CharVariantConst.SLASH + X_WWW_FORM_URLENCODED;
    public static final String APPLICATION_XHTML_XML = APPLICATION + CharVariantConst.SLASH + XHTML_XML;
    public static final String APPLICATION_XML = APPLICATION + CharVariantConst.SLASH + XML;

    public static final String APPLICATION_ATOM_XML_UTF8 = APPLICATION_ATOM_XML + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_JAVASCRIPT_UTF8 = APPLICATION_JAVASCRIPT + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_JSON_UTF8 = APPLICATION_JSON + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_PROBLEM_JSON_UTF8 = APPLICATION_PROBLEM_JSON + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_PROBLEM_XML_UTF8 = APPLICATION_PROBLEM_XML + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_RSS_XML_UTF8 = APPLICATION_RSS_XML + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_SOAP_JSON_UTF8 = APPLICATION_SOAP_JSON + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_SOAP_XML_UTF8 = APPLICATION_SOAP_XML + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_STREAM_JSON_UTF8 = APPLICATION_STREAM_JSON + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_STAR_JSON_UTF8 = APPLICATION_STAR_JSON + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_STAR_XML_UTF8 = APPLICATION_STAR_XML + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_X_NDJSON_UTF8 = APPLICATION_X_NDJSON + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_X_WWW_FORM_URLENCODED_UTF8 = APPLICATION_X_WWW_FORM_URLENCODED + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_XHTML_XML_UTF8 = APPLICATION_XHTML_XML + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String APPLICATION_XML_UTF8 = APPLICATION_XML + CharVariantConst.SEMICOLON + CHARSET_UTF8;

    public static final String TEXT_CSS_UTF8 = TEXT_CSS + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String TEXT_HTML_UTF8 = TEXT_HTML + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String TEXT_MARKDOWN_UTF8 = TEXT_MARKDOWN + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String TEXT_PLAIN_UTF8 = TEXT_PLAIN + CharVariantConst.SEMICOLON + CHARSET_UTF8;
    public static final String TEXT_XML_UTF8 = TEXT_XML + CharVariantConst.SEMICOLON + CHARSET_UTF8;
}
