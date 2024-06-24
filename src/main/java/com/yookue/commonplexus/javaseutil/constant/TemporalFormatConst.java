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
 * Constants for temporal formats
 *
 * @author David Hsing
 * @see java.time.format.DateTimeFormatter
 * @see java.time.temporal.TemporalUnit
 * @see "com.fasterxml.jackson.datatype.joda.cfg.FormatConfig"
 * @see "com.fasterxml.jackson.databind.util.StdDateFormat"
 * @see "org.joda.time.format.ISODateTimeFormat"
 */
@SuppressWarnings("unused")
public abstract class TemporalFormatConst {
    public static final String GMT_JAVASCRIPT = "EEE MMM dd yyyy HH:mm:ss 'GMT'Z";    // $NON-NLS-1$

    public static final String NON_YYYY = "yyyy";    // $NON-NLS-1$
    public static final String NON_YYYYMM = "yyyyMM";    // $NON-NLS-1$
    public static final String NON_YYYYMMDD = "yyyyMMdd";    // $NON-NLS-1$
    public static final String NON_HHMMSS = "HHmmss";    // $NON-NLS-1$
    public static final String NON_HHMMSSS = "HHmmssSSS";    // $NON-NLS-1$
    public static final String NON_YYYYMMDD_HHMM = "yyyyMMddHHmm";    // $NON-NLS-1$
    public static final String NON_YYYYMMDD_HHMMSS = "yyyyMMddHHmmss";    // $NON-NLS-1$
    public static final String NON_YYYYMMDD_HHMMSSS = "yyyyMMddHHmmssSSS";    // $NON-NLS-1$

    public static final String ISO_YYYYMMDD = "yyyy-MM-dd";    // $NON-NLS-1$
    public static final String ISO_YYYYMMDD_HHMM = "yyyy-MM-dd HH:mm";    // $NON-NLS-1$
    public static final String ISO_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";    // $NON-NLS-1$
    public static final String ISO_YYYYMMDD_HHMMSSS = "yyyy-MM-dd HH:mm:ss.SSS";    // $NON-NLS-1$
    public static final String ISO_HHMMSS = "HH:mm:ss";    // $NON-NLS-1$
    public static final String ISO_HHMMSSS = "HH:mm:ss.SSS";    // $NON-NLS-1$

    public static final String EUR_YYYYMMDD = "yyyy/MM/dd";    // $NON-NLS-1$
    public static final String EUR_YYYYMMDD_HHMM = "yyyy/MM/dd HH:mm";    // $NON-NLS-1$
    public static final String EUR_YYYYMMDD_HHMMSS = "yyyy/MM/dd HH:mm:ss";    // $NON-NLS-1$
    public static final String EUR_YYYYMMDD_HHMMSSS = "yyyy/MM/dd HH:mm:ss.SSS";    // $NON-NLS-1$
    public static final String EUR_MMDDYYYY = "MM/dd/yyyy";    // $NON-NLS-1$
    public static final String EUR_MMDDYYYY_HHMM = "MM/dd/yyyy HH:mm";    // $NON-NLS-1$
    public static final String EUR_MMDDYYYY_HHMMSS = "MM/dd/yyyy HH:mm:ss";    // $NON-NLS-1$
    public static final String EUR_MMDDYYYY_HHMMSSS = "MM/dd/yyyy HH:mm:ss.SSS";    // $NON-NLS-1$
}
