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


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.beanutils2.ConversionException;
import org.apache.commons.beanutils2.converters.AbstractConverter;
import org.apache.commons.lang3.ClassUtils;
import com.yookue.commonplexus.javaseutil.constant.TemporalFormatCombo;
import com.yookue.commonplexus.javaseutil.util.CollectionPlainWraps;
import com.yookue.commonplexus.javaseutil.util.LocalDateWraps;
import lombok.Getter;
import lombok.Setter;


/**
 * Converters an object to {@link java.time.LocalDate}
 *
 * @author David Hsing
 * @see org.apache.commons.beanutils2.Converter
 * @see org.apache.commons.beanutils2.converters.LocalDateConverter
 */
@Getter
@Setter
@SuppressWarnings("unused")
public class LocalDateConverter extends AbstractConverter<LocalDate> {
    private List<String> patterns = Arrays.asList(TemporalFormatCombo.DATE_FORMATS);

    @Override
    protected <T> T convertToType(@Nonnull Class<T> type, @Nullable Object value) throws ConversionException {
        if (!ClassUtils.isAssignable(type, getDefaultType())) {
            throw super.conversionException(type, value);
        }
        if (!(value instanceof String) || CollectionPlainWraps.isEmpty(patterns)) {
            return null;
        }
        String date = (String) value;
        for (String pattern : patterns) {
            if (LocalDateWraps.matchDateFormat(date, pattern)) {
                return type.cast(LocalDateWraps.parseDate(date, pattern));
            }
        }
        return null;
    }

    @Override
    protected Class<LocalDate> getDefaultType() {
        return LocalDate.class;
    }
}
