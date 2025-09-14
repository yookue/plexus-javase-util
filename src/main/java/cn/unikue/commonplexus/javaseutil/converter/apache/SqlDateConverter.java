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


import java.sql.Date;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.beanutils2.converters.DateTimeConverter;
import cn.unikue.commonplexus.javaseutil.constant.TemporalFormatCombo;
import cn.unikue.commonplexus.javaseutil.util.JdkDateWraps;
import cn.unikue.commonplexus.javaseutil.util.SqlDateWraps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Converts an object to {@link java.sql.Date}
 *
 * @author David Hsing
 *
 * @see org.apache.commons.beanutils2.Converter
 * @see org.apache.commons.beanutils2.sql.converters.SqlDateConverter
 */
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings("unused")
public class SqlDateConverter extends DateTimeConverter<Date> {
    private String[] formats = TemporalFormatCombo.ALL_DATETIME_DATES;

    public SqlDateConverter(@Nullable Date defaultValue) {
        super(defaultValue);
    }

    @Override
    protected <T> T convertToType(@Nonnull Class<T> type, @Nullable Object value) throws Exception {
        if (value == null) {
            return null;
        }
        if (value instanceof String alias) {
            for (String pattern : formats) {
                java.util.Date result = JdkDateWraps.parseDateQuietly(alias, pattern);
                if (result != null) {
                    return type.cast(SqlDateWraps.ofJdkDate(result));
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
