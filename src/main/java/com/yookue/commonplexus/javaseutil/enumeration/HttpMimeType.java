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

package com.yookue.commonplexus.javaseutil.enumeration;


import com.yookue.commonplexus.javaseutil.constant.HttpMimeConst;
import com.yookue.commonplexus.javaseutil.support.ValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Enumerations of http mime types
 *
 * @author David Hsing
 * @reference "http://www.iana.org/assignments/media-types/media-types.xhtml"
 * @see "org.springframework.http.MediaType"
 * @see "org.springframework.http.MediaTypeFactory"
 * @see "org.springframework.util.MimeTypeUtils"
 * @see "org.springframework.boot.actuate.endpoint.http.ActuatorMediaType"
 */
@AllArgsConstructor
@Getter
@SuppressWarnings({"unused", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public enum HttpMimeType implements ValueEnum<String> {
    APPLICATION_FORM_URLENCODED_UTF8(HttpMimeConst.APPLICATION_X_WWW_FORM_URLENCODED_UTF8),
    APPLICATION_JAVASCRIPT(HttpMimeConst.APPLICATION_JAVASCRIPT),
    APPLICATION_JAVASCRIPT_UTF8(HttpMimeConst.APPLICATION_JAVASCRIPT_UTF8),
    APPLICATION_SOAP_JSON(HttpMimeConst.APPLICATION_SOAP_JSON),
    APPLICATION_SOAP_JSON_UTF8(HttpMimeConst.APPLICATION_SOAP_JSON_UTF8),
    APPLICATION_SOAP_XML(HttpMimeConst.APPLICATION_SOAP_XML),
    APPLICATION_SOAP_XML_UTF8(HttpMimeConst.APPLICATION_SOAP_XML_UTF8),
    APPLICATION_STAR_JSON(HttpMimeConst.APPLICATION_STAR_JSON),
    APPLICATION_STAR_JSON_UTF8(HttpMimeConst.APPLICATION_STAR_JSON_UTF8),
    APPLICATION_STAR_XML(HttpMimeConst.APPLICATION_STAR_XML),
    APPLICATION_STAR_XML_UTF8(HttpMimeConst.APPLICATION_STAR_XML_UTF8),
    APPLICATION_XHTML_XML_UTF8(HttpMimeConst.APPLICATION_XHTML_XML_UTF8),
    APPLICATION_XML_UTF8(HttpMimeConst.APPLICATION_XML_UTF8),
    TEXT_HTML_UTF8(HttpMimeConst.TEXT_HTML_UTF8),
    TEXT_MARKDOWN_UTF8(HttpMimeConst.TEXT_MARKDOWN_UTF8),
    TEXT_PLAIN_UTF8(HttpMimeConst.TEXT_PLAIN_UTF8),
    TEXT_XML_UTF8(HttpMimeConst.TEXT_XML_UTF8),
    STAR_JSON(HttpMimeConst.STAR_JSON), STAR_XML(HttpMimeConst.STAR_XML);

    private final String value;
}
