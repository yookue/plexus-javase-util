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

package com.yookue.commonplexus.javaseutil.converter;


import java.sql.Date;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.beanutils2.converters.DateTimeConverter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import com.yookue.commonplexus.javaseutil.constant.TemporalFormatCombo;
import com.yookue.commonplexus.javaseutil.util.SqlDateWraps;
import com.yookue.commonplexus.javaseutil.util.UtilDateWraps;


/**
 * Converts an object to {@link java.sql.Date}
 *
 * @author David Hsing
 * @see org.apache.commons.beanutils2.Converter
 */
@SuppressWarnings("unused")
public class SqlDateConverter extends DateTimeConverter<Date> {
    public SqlDateConverter() {
        super.setPatterns(TemporalFormatCombo.ALL_DATETIME_DATES);
    }

    public SqlDateConverter(@Nullable Date defaultValue) {
        super(defaultValue);
        super.setPatterns(TemporalFormatCombo.ALL_DATETIME_DATES);
    }

    @Override
    protected <T> T convertToType(@Nonnull Class<T> type, @Nullable Object value) throws Exception {
        if (!ClassUtils.isAssignable(type, getDefaultType())) {
            throw super.conversionException(type, value);
        }
        if (!(value instanceof String) || ArrayUtils.isEmpty(super.getPatterns())) {
            return null;
        }
        String dateTime = (String) value;
        for (String pattern : super.getPatterns()) {
            if (UtilDateWraps.matchFormat(dateTime, pattern)) {
                return type.cast(SqlDateWraps.castFromUtilDate(UtilDateWraps.parseDateTime(dateTime, pattern)));
            }
        }
        return null;
    }

    @Override
    protected Class<Date> getDefaultType() {
        return Date.class;
    }
}
