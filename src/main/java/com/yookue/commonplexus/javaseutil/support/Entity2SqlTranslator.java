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

package com.yookue.commonplexus.javaseutil.support;


import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;
import com.yookue.commonplexus.javaseutil.constant.TemporalFormatConst;
import com.yookue.commonplexus.javaseutil.util.AnnotationUtilsWraps;
import com.yookue.commonplexus.javaseutil.util.FieldUtilsWraps;
import com.yookue.commonplexus.javaseutil.util.MapPlainWraps;
import com.yookue.commonplexus.javaseutil.util.StringUtilsWraps;
import com.yookue.commonplexus.javaseutil.util.UtilDateWraps;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


/**
 * Beans2SQL
 *
 * @author chkrr00k
 * @author David Hsing
 * @reference "https://github.com/chkrr00k/Beans2SQL"
 */
@Getter
@Setter
@SuppressWarnings({"unused", "WeakerAccess", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public class Entity2SqlTranslator {
    private String dateFormat = TemporalFormatConst.ISO_YYYYMMDD;
    private String timeFormat = TemporalFormatConst.ISO_HHMMSS;
    private String dateTimeFormat = TemporalFormatConst.ISO_YYYYMMDD_HHMMSS;

    public String toInsertSql(@Nullable Object entity, @Nullable String schema, @Nullable String table) {
        SchemaTableColumns tableColumns = buildColumnStruct(entity, schema, table);
        if (tableColumns == null || MapPlainWraps.isEmpty(tableColumns.getColumnValues())) {
            return null;
        }
        Validate.isTrue(StringUtils.isNotBlank(tableColumns.getTableName()));
        StringBuilder builder = new StringBuilder("insert into ");    // $NON-NLS-1$
        if (StringUtils.isNotBlank(tableColumns.getSchemaName())) {
            builder.append(String.format("`%s`.", tableColumns.getSchemaName()));    // $NON-NLS-1$
        }
        builder.append(String.format("`%s` ", tableColumns.getTableName()));    // $NON-NLS-1$
        String columns = StringUtilsWraps.joinWith(tableColumns.getColumnValues().keySet(), ", ", "`", "`");    // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$
        String values = StringUtilsWraps.joinWith(tableColumns.getColumnValues().values(), ", ", "'", "'");    // $NON-NLS-1$ // $NON-NLS-2$ // $NON-NLS-3$
        builder.append(String.format("(%s) values (%s);", columns, values));    // $NON-NLS-1$
        return builder.toString();
    }

    public String toDeleteSql(@Nullable Object entity, @Nullable String schema, @Nullable String table) {
        SchemaTableColumns tableColumns = buildColumnStruct(entity, schema, table);
        if (tableColumns == null || MapPlainWraps.isEmpty(tableColumns.getColumnValues())) {
            return null;
        }
        Validate.isTrue(StringUtils.isNotBlank(tableColumns.getTableName()));
        StringBuilder builder = new StringBuilder("delete from ");    // $NON-NLS-1$
        if (StringUtils.isNotBlank(tableColumns.getSchemaName())) {
            builder.append(String.format("`%s`.", tableColumns.getSchemaName()));    // $NON-NLS-1$
        }
        builder.append(String.format("`%s` where 1 = 1", tableColumns.getTableName()));    // $NON-NLS-1$
        tableColumns.getColumnValues().forEach((key, value) -> {
            if (value != null) {
                builder.append(String.format(" and `%s`='%s'", key, value));    // $NON-NLS-1$
            } else {
                builder.append(String.format(" and `%s` is null", key));    // $NON-NLS-1$
            }
        });
        builder.append(CharVariantConst.SEMICOLON);
        return builder.toString();
    }

    @SuppressWarnings({"DataFlowIssue", "RedundantSuppression"})
    protected SchemaTableColumns buildColumnStruct(@Nullable Object entity, @Nullable String schema, @Nullable String table) {
        if (entity == null) {
            return null;
        }
        SchemaTableColumns result = new SchemaTableColumns(schema, table);
        if (StringUtils.isBlank(result.getSchemaName()) && entity.getClass().isAnnotationPresent(Table.class)) {
            result.setSchemaName(entity.getClass().getAnnotation(Table.class).schema());
        }
        if (StringUtils.isBlank(result.getTableName()) && entity.getClass().isAnnotationPresent(Table.class)) {
            result.setTableName(entity.getClass().getAnnotation(Table.class).name());
        }
        if (StringUtils.isBlank(result.getTableName())) {
            result.setTableName(ClassUtils.getShortClassName(entity.getClass()));
        }
        List<Field> fields = FieldUtils.getAllFieldsList(entity.getClass());
        for (Field field : fields) {
            if (FieldUtilsWraps.isStatic(field) || FieldUtilsWraps.isFinal(field) || AnnotationUtilsWraps.isPresent(field, Transient.class)) {
                continue;
            }
            String columnName = null;
            if (field.isAnnotationPresent(Column.class)) {
                columnName = field.getAnnotation(Column.class).name();
            }
            if (StringUtils.isBlank(columnName)) {
                columnName = field.getName();
            }
            Object columnValue = FieldUtilsWraps.readField(field, entity, true);
            if (ObjectUtils.isEmpty(columnValue)) {
                continue;
            }
            Class<?> fieldType = field.getType();
            if (fieldType.equals(java.util.Date.class) || fieldType.equals(java.sql.Date.class)) {
                if (field.isAnnotationPresent(Temporal.class)) {
                    TemporalType temporalType = field.getAnnotation(Temporal.class).value();
                    switch (temporalType) {
                        case DATE:
                            result.getColumnValues().put(columnName, UtilDateWraps.formatDateTime((java.util.Date) columnValue, dateFormat));
                            break;
                        case TIME:
                            result.getColumnValues().put(columnName, UtilDateWraps.formatDateTime((java.util.Date) columnValue, timeFormat));
                            break;
                        case TIMESTAMP:
                            result.getColumnValues().put(columnName, UtilDateWraps.formatDateTime((java.util.Date) columnValue, dateTimeFormat));
                            break;
                        default:
                            break;
                    }
                } else {
                    result.getColumnValues().put(columnName, UtilDateWraps.formatDateTime((java.util.Date) columnValue, dateFormat));
                }
            } else if (fieldType.equals(java.sql.Timestamp.class)) {
                result.getColumnValues().put(columnName, UtilDateWraps.formatDateTime((java.util.Date) columnValue, dateTimeFormat));
            } else if (fieldType.equals(Boolean.class)) {
                result.getColumnValues().put(columnName, BooleanUtils.toInteger((Boolean) columnValue));
            }
            if (!result.getColumnValues().containsKey(columnName)) {
                result.getColumnValues().put(columnName, columnValue.toString());
            }
        }
        return result;
    }

    /**
     * Structure for Beans2SQL
     */
    @Accessors(chain = true)
    @NoArgsConstructor
    @Data
    protected static class SchemaTableColumns {
        private String schemaName;
        private String tableName;
        private Map<String, Object> columnValues = new LinkedHashMap<>();

        public SchemaTableColumns(@Nullable String schemaName, @Nullable String tableName) {
            this.schemaName = schemaName;
            this.tableName = tableName;
        }
    }
}
