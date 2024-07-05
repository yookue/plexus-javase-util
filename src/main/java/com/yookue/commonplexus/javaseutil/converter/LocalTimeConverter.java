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


import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.beanutils2.ConversionException;
import org.apache.commons.beanutils2.converters.AbstractConverter;
import org.apache.commons.lang3.ClassUtils;
import com.yookue.commonplexus.javaseutil.constant.TemporalFormatCombo;
import com.yookue.commonplexus.javaseutil.util.CollectionPlainWraps;
import com.yookue.commonplexus.javaseutil.util.LocalDateWraps;
import lombok.Getter;
import lombok.Setter;


/**
 * Converts an object to {@link java.time.LocalTime}
 *
 * @author David Hsing
 * @see org.apache.commons.beanutils2.Converter
 * @see org.apache.commons.beanutils2.converters.LocalDateTimeConverter
 */
@Getter
@Setter
@SuppressWarnings("unused")
public class LocalTimeConverter extends AbstractConverter<LocalTime> {
    private List<String> patterns = Arrays.asList(TemporalFormatCombo.TIME_FORMATS);

    @Override
    protected <T> T convertToType(@Nonnull Class<T> type, @Nullable Object value) throws ConversionException {
        if (!ClassUtils.isAssignable(type, getDefaultType())) {
            throw super.conversionException(type, value);
        }
        if (!(value instanceof String instance) || CollectionPlainWraps.isEmpty(patterns)) {
            return null;
        }
        for (String pattern : patterns) {
            if (LocalDateWraps.matchTimeFormat(instance, pattern)) {
                return type.cast(LocalDateWraps.parseTime(instance, pattern));
            }
        }
        return null;
    }

    @Override
    protected Class<LocalTime> getDefaultType() {
        return LocalTime.class;
    }
}
