/*
 * Copyright (c) 2025 Yookue Ltd. All rights reserved.
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

package com.yookue.commonplexus.javaseutil.converter.apache;


import java.time.LocalTime;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.beanutils2.converters.AbstractConverter;
import com.yookue.commonplexus.javaseutil.constant.TemporalFormatCombo;
import com.yookue.commonplexus.javaseutil.util.LocalDateWraps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Converts an object to {@link java.time.LocalTime}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.beanutils2.Converter
 * @see org.apache.commons.beanutils2.converters.LocalTimeConverter
 */
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings("unused")
public class LocalTimeConverter extends AbstractConverter<LocalTime> {
    private String[] formats = TemporalFormatCombo.TIME_FORMATS;

    public LocalTimeConverter(@Nullable LocalTime defaultValue) {
        super(defaultValue);
    }

    @Override
    protected <T> T convertToType(@Nonnull Class<T> type, @Nullable Object value) throws Exception {
        if (value == null) {
            return null;
        }
        if (value instanceof String alias) {
            for (String pattern : formats) {
                T result = type.cast(LocalDateWraps.parseTimeQuietly(alias, pattern));
                if (result != null) {
                    return result;
                }
            }
        }
        if (LocalTime.class.equals(type)) {
            return type.cast(LocalTime.parse(String.valueOf(value)));
        }
        throw super.conversionException(type, value);
    }

    @Override
    protected Class<LocalTime> getDefaultType() {
        return LocalTime.class;
    }
}
