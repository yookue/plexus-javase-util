/*
 * Copyright (c) 2025 Unikue Ltd. All rights reserved.
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

package cn.unikue.commonplexus.javaseutil.converter.apache;


import java.util.Date;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.beanutils2.converters.DateTimeConverter;
import cn.unikue.commonplexus.javaseutil.constant.TemporalFormatCombo;
import cn.unikue.commonplexus.javaseutil.util.JdkDateWraps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Converts an object to {@link java.util.Date}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.beanutils2.Converter
 * @see org.apache.commons.beanutils2.converters.DateConverter
 */
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings("unused")
public class JdkDateConverter extends DateTimeConverter<Date> {
    private String[] formats = TemporalFormatCombo.ALL_DATETIME_DATES;

    public JdkDateConverter(@Nullable Date defaultValue) {
        super(defaultValue);
    }

    @Override
    protected <T> T convertToType(@Nonnull Class<T> type, @Nullable Object value) throws Exception {
        if (value == null) {
            return null;
        }
        if (value instanceof String alias) {
            for (String pattern : formats) {
                T result = type.cast(JdkDateWraps.parseDateQuietly(alias, pattern));
                if (result != null) {
                    return result;
                }
            }
        }
        return super.convertToType(type, value);
    }

    @Override
    protected Class<Date> getDefaultType() {
        return Date.class;
    }
}
