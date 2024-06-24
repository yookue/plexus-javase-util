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


import com.yookue.commonplexus.javaseutil.util.ArrayUtilsWraps;


/**
 * Combinations of temporal formats
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public abstract class TemporalFormatCombo {
    public static final String[] DATE_FORMATS = new String[]{TemporalFormatConst.ISO_YYYYMMDD, TemporalFormatConst.EUR_YYYYMMDD, TemporalFormatConst.EUR_MMDDYYYY, TemporalFormatConst.NON_YYYYMMDD};
    public static final String[] DATETIME_FORMATS = new String[]{TemporalFormatConst.ISO_YYYYMMDD_HHMMSS, TemporalFormatConst.EUR_YYYYMMDD_HHMMSS, TemporalFormatConst.EUR_MMDDYYYY_HHMMSS, TemporalFormatConst.NON_YYYYMMDD_HHMMSS};
    public static final String[] TIME_FORMATS = new String[]{TemporalFormatConst.ISO_HHMMSS, TemporalFormatConst.NON_HHMMSS};

    public static final String[] ALL_DATETIME_DATES = ArrayUtilsWraps.unionAll(DATETIME_FORMATS, DATE_FORMATS);
}
