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


import com.yookue.commonplexus.javaseutil.support.ValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Enumerations of Microsoft Office mime types
 *
 * @author David Hsing
 * @reference "http://filext.com/faq/office_mime_types.php"
 */
@AllArgsConstructor
@Getter
@SuppressWarnings({"unused", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public enum OfficeMimeType implements ValueEnum<String> {
    WORD_DOC("application/msword"),    // $NON-NLS-1$
    WORD_DOT("application/msword"),    // $NON-NLS-1$
    WORD_DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),    // $NON-NLS-1$
    WORD_DOTX("application/vnd.openxmlformats-officedocument.wordprocessingml.template"),    // $NON-NLS-1$
    WORD_DOCM("application/vnd.ms-word.document.macroEnabled.12"),    // $NON-NLS-1$
    WORD_DOTM("application/vnd.ms-word.template.macroEnabled.12"),    // $NON-NLS-1$
    EXCEL_XLS("application/vnd.ms-excel"),    // $NON-NLS-1$
    EXCEL_XLT("application/vnd.ms-excel"),    // $NON-NLS-1$
    EXCEL_XLA("application/vnd.ms-excel"),    // $NON-NLS-1$
    EXCEL_XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),    // $NON-NLS-1$
    EXCEL_XLTX("application/vnd.openxmlformats-officedocument.spreadsheetml.template"),    // $NON-NLS-1$
    EXCEL_XLSM("application/vnd.ms-excel.sheet.macroEnabled.12"),    // $NON-NLS-1$
    EXCEL_XLTM("application/vnd.ms-excel.template.macroEnabled.12"),    // $NON-NLS-1$
    EXCEL_XLAM("application/vnd.ms-excel.addin.macroEnabled.12"),    // $NON-NLS-1$
    EXCEL_XLSB("application/vnd.ms-excel.sheet.binary.macroEnabled.12"),    // $NON-NLS-1$
    POWERPOINT_PPT("application/vnd.ms-powerpoint"),    // $NON-NLS-1$
    POWERPOINT_POT("application/vnd.ms-powerpoint"),    // $NON-NLS-1$
    POWERPOINT_PPS("application/vnd.ms-powerpoint"),    // $NON-NLS-1$
    POWERPOINT_PPA("application/vnd.ms-powerpoint"),    // $NON-NLS-1$
    POWERPOINT_PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation"),    // $NON-NLS-1$
    POWERPOINT_POTX("application/vnd.openxmlformats-officedocument.presentationml.template"),    // $NON-NLS-1$
    POWERPOINT_PPSX("application/vnd.openxmlformats-officedocument.presentationml.slideshow"),    // $NON-NLS-1$
    POWERPOINT_PPAM("application/vnd.ms-powerpoint.addin.macroEnabled.12"),    // $NON-NLS-1$
    POWERPOINT_PPTM("application/vnd.ms-powerpoint.presentation.macroEnabled.12"),    // $NON-NLS-1$
    POWERPOINT_POTM("application/vnd.ms-powerpoint.template.macroEnabled.12"),    // $NON-NLS-1$
    POWERPOINT_PPSM("application/vnd.ms-powerpoint.slideshow.macroEnabled.12");    // $NON-NLS-1$

    private final String value;
}
