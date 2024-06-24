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


import java.util.Calendar;
import com.yookue.commonplexus.javaseutil.support.ValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Enumerations of calendar field types
 *
 * @author David Hsing
 */
@AllArgsConstructor
@Getter
@SuppressWarnings("unused")
public enum CalendarFieldType implements ValueEnum<Integer> {
    YEAR(Calendar.YEAR),
    MONTH_OF_YEAR(Calendar.MONTH),
    DAY_OF_MONTH(Calendar.DAY_OF_MONTH),
    HOUR_OF_DAY(Calendar.HOUR_OF_DAY),
    MINUTE_OF_HOUR(Calendar.MINUTE),
    SECOND_OF_MINUTE(Calendar.SECOND),
    MILLIS_OF_SECOND(Calendar.MILLISECOND);

    private final Integer value;
}
