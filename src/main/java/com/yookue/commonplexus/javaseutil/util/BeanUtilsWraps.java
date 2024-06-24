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

package com.yookue.commonplexus.javaseutil.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import javax.annotation.Nullable;
import org.apache.commons.beanutils2.BeanMap;
import org.apache.commons.beanutils2.BeanUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.annotation.BeanCopyIgnore;
import com.yookue.commonplexus.javaseutil.annotation.ViewSubmitIgnore;
import com.yookue.commonplexus.javaseutil.constant.JavaKeywordConst;
import com.yookue.commonplexus.javaseutil.exception.BeanInvocationException;
import com.yookue.commonplexus.javaseutil.structure.BooleanDataStruct;


/**
 * Utilities for {@link org.apache.commons.beanutils2.BeanUtils}
 *
 * @author David Hsing
 * @reference "https://gitbox.apache.org/repos/asf?p=commons-beanutils.git;a=tree"
 * @see org.apache.commons.beanutils2.BeanUtils
 * @see org.apache.commons.beanutils2.BeanUtilsBean
 * @see org.apache.commons.beanutils2.BeanMap
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public abstract class BeanUtilsWraps {
    static {
        BeanIntrospectorWraps.initPropertyIntrospectors();
    }

    public static void copyProperties(@Nullable Object target, @Nullable Object source) throws BeanInvocationException {
        if (ObjectUtils.anyNull(target, source)) {
            return;
        }
        mapToBean(target, new BeanMap(source));
    }

    public static void copyProperties(@Nullable Object target, @Nullable Object source, BiPredicate<String, Object> filter) throws BeanInvocationException {
        if (ObjectUtils.anyNull(target, source)) {
            return;
        }
        mapToBean(target, new BeanMap(source), filter);
    }

    public static void copyProperties(@Nullable Object target, @Nullable Object source, BiFunction<String, Object, BooleanDataStruct<Object>> action) throws BeanInvocationException {
        if (ObjectUtils.anyNull(target, source)) {
            return;
        }
        mapToBean(target, new BeanMap(source), action);
    }

    public static void copyPropertiesExclusive(@Nullable Object target, @Nullable Object source, @Nullable String... fields) throws BeanInvocationException {
        copyPropertiesExclusive(target, source, ArrayUtilsWraps.asList(fields), false);
    }

    public static void copyPropertiesExclusive(@Nullable Object target, @Nullable Object source, @Nullable Collection<String> fields) throws BeanInvocationException {
        copyPropertiesExclusive(target, source, fields, false);
    }

    public static void copyPropertiesExclusive(@Nullable Object target, @Nullable Object source, @Nullable Collection<String> fields, boolean allowEmptyValue) throws BeanInvocationException {
        if (ObjectUtils.anyNull(target, source)) {
            return;
        }
        mapToBeanExclusive(target, new BeanMap(source), fields, allowEmptyValue);
    }

    public static void copyPropertiesInclusive(@Nullable Object target, @Nullable Object source, @Nullable String... fields) throws BeanInvocationException {
        copyPropertiesInclusive(target, source, ArrayUtilsWraps.asList(fields), false);
    }

    public static void copyPropertiesInclusive(@Nullable Object target, @Nullable Object source, @Nullable Collection<String> fields) throws BeanInvocationException {
        copyPropertiesInclusive(target, source, fields, false);
    }

    public static void copyPropertiesInclusive(@Nullable Object target, @Nullable Object source, @Nullable Collection<String> fields, boolean allowEmptyValue) throws BeanInvocationException {
        if (ObjectUtils.anyNull(target, source) || CollectionPlainWraps.isEmpty(fields)) {
            return;
        }
        mapToBeanInclusive(target, new BeanMap(source), fields, allowEmptyValue);
    }

    public static void copyPropertiesQuietly(@Nullable Object target, @Nullable Object source) {
        try {
            copyProperties(target, source);
        } catch (Exception ignored) {
        }
    }

    public static void copyPropertiesQuietly(@Nullable Object target, @Nullable Object source, BiPredicate<String, Object> filter) {
        try {
            copyProperties(target, source, filter);
        } catch (Exception ignored) {
        }
    }

    public static void copyPropertiesQuietly(@Nullable Object target, @Nullable Object source, BiFunction<String, Object, BooleanDataStruct<Object>> action) {
        try {
            copyProperties(target, source, action);
        } catch (Exception ignored) {
        }
    }

    public static void copyPropertiesExclusiveQuietly(@Nullable Object target, @Nullable Object source, @Nullable String... fields) {
        try {
            copyPropertiesExclusive(target, source, fields);
        } catch (Exception ignored) {
        }
    }

    public static void copyPropertiesExclusiveQuietly(@Nullable Object target, @Nullable Object source, @Nullable Collection<String> fields) {
        try {
            copyPropertiesExclusive(target, source, fields);
        } catch (Exception ignored) {
        }
    }

    public static void copyPropertiesExclusiveQuietly(@Nullable Object target, @Nullable Object source, @Nullable Collection<String> fields, boolean allowEmptyValue) {
        try {
            copyPropertiesExclusive(target, source, fields, allowEmptyValue);
        } catch (Exception ignored) {
        }
    }

    public static void copyPropertiesInclusiveQuietly(@Nullable Object target, @Nullable Object source, @Nullable String... fields) {
        try {
            copyPropertiesInclusive(target, source, fields);
        } catch (Exception ignored) {
        }
    }

    public static void copyPropertiesInclusiveQuietly(@Nullable Object target, @Nullable Object source, @Nullable Collection<String> fields) {
        try {
            copyPropertiesInclusive(target, source, fields);
        } catch (Exception ignored) {
        }
    }

    public static void copyPropertiesInclusiveQuietly(@Nullable Object target, @Nullable Object source, @Nullable Collection<String> fields, boolean allowEmptyValue) {
        try {
            copyPropertiesInclusive(target, source, fields, allowEmptyValue);
        } catch (Exception ignored) {
        }
    }

    public static Map<String, ?> beanToMap(@Nullable Object bean) {
        return beanToMapExclusive(bean, (Collection<String>) null);
    }

    @Nullable
    public static Map<String, ?> beanToMap(@Nullable Object bean, @Nullable String keyPrefix, @Nullable String keySuffix) {
        return beanToMap(bean, keyPrefix, keySuffix, true, true);
    }

    @Nullable
    public static Map<String, ?> beanToMap(@Nullable Object bean, @Nullable String keyPrefix, @Nullable String keySuffix, boolean allowClassKey, boolean allowEmptyValue) {
        return PropertyPlainWraps.toStringObjectMap(beanToProperties(bean, keyPrefix, keySuffix, allowClassKey, allowEmptyValue));
    }

    @Nullable
    public static Map<String, ?> beanToMap(@Nullable Object bean, @Nullable Map<String, String> keyMapping, @Nullable Map<Object, Object> valueMapping) {
        return beanToMap(bean, keyMapping, valueMapping, null);
    }

    @Nullable
    public static Map<String, ?> beanToMap(@Nullable Object bean, @Nullable BiPredicate<String, Object> filer) {
        return beanToMap(bean, null, null, filer);
    }

    @Nullable
    public static Map<String, ?> beanToMap(@Nullable Object bean, @Nullable Map<String, String> keyMapping, @Nullable Map<Object, Object> valueMapping, @Nullable BiPredicate<String, Object> filer) {
        return PropertyPlainWraps.toStringObjectMap(beanToProperties(bean, keyMapping, valueMapping, filer));
    }

    @Nullable
    public static Map<String, ?> beanToMapExclusive(@Nullable Object bean, @Nullable String... fields) {
        return beanToMapExclusive(bean, ArrayUtilsWraps.asList(fields), true, true);
    }

    @Nullable
    public static Map<String, ?> beanToMapExclusive(@Nullable Object bean, @Nullable Collection<String> fields) {
        return beanToMapExclusive(bean, fields, true, true);
    }

    @Nullable
    public static Map<String, ?> beanToMapExclusive(@Nullable Object bean, @Nullable Collection<String> fields, boolean allowClassKey, boolean allowEmptyValue) {
        return PropertyPlainWraps.toStringObjectMap(beanToPropertiesExclusive(bean, fields, allowClassKey, allowEmptyValue));
    }

    @Nullable
    public static Map<String, ?> beanToMapInclusive(@Nullable Object bean, @Nullable String... fields) {
        return beanToMapInclusive(bean, ArrayUtilsWraps.asList(fields), true, true);
    }

    @Nullable
    public static Map<String, ?> beanToMapInclusive(@Nullable Object bean, @Nullable Collection<String> fields) {
        return beanToMapInclusive(bean, fields, true, true);
    }

    @Nullable
    public static Map<String, ?> beanToMapInclusive(@Nullable Object bean, @Nullable Collection<String> fields, boolean allowClassKey, boolean allowEmptyValue) {
        return PropertyPlainWraps.toStringObjectMap(beanToPropertiesInclusive(bean, fields, allowClassKey, allowEmptyValue));
    }

    @Nullable
    public static Properties beanToProperties(@Nullable Object bean) {
        return beanToPropertiesExclusive(bean, (Collection<String>) null);
    }

    @Nullable
    public static Properties beanToProperties(@Nullable Object bean, @Nullable Map<String, String> keyMapping, @Nullable Map<Object, Object> valueMapping) {
        return beanToProperties(bean, keyMapping, valueMapping, null);
    }

    @Nullable
    public static Properties beanToProperties(@Nullable Object bean, @Nullable BiPredicate<String, Object> filer) {
        return beanToProperties(bean, null, null, filer);
    }

    @Nullable
    public static Properties beanToProperties(@Nullable Object bean, @Nullable Map<String, String> keyMapping, @Nullable Map<Object, Object> valueMapping, @Nullable BiPredicate<String, Object> filer) {
        if (bean == null) {
            return null;
        }
        BeanMap map = new BeanMap(bean);
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        Properties properties = new Properties();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (filer == null || filer.test(entry.getKey(), entry.getValue())) {
                properties.put(MapUtils.getString(keyMapping, entry.getKey(), entry.getKey()), MapUtils.getObject(valueMapping, entry.getValue(), entry.getValue()));
            }
        }
        return MapUtils.isEmpty(properties) ? null : properties;
    }

    @Nullable
    public static Properties beanToProperties(@Nullable Object bean, @Nullable CharSequence keyPrefix, @Nullable CharSequence keySuffix) {
        return beanToProperties(bean, keyPrefix, keySuffix, true, true);
    }

    @Nullable
    public static Properties beanToProperties(@Nullable Object bean, @Nullable CharSequence keyPrefix, @Nullable CharSequence keySuffix, boolean allowClassKey, boolean allowEmptyValue) {
        if (bean == null) {
            return null;
        }
        BeanMap map = new BeanMap(bean);
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        Properties properties = new Properties();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey() == null || (!allowEmptyValue && ObjectUtils.isEmpty(entry.getValue()))) {
                continue;
            }
            if (StringUtils.equals(entry.getKey(), JavaKeywordConst.CLASS)) {
                if (allowClassKey) {
                    properties.put(entry.getKey(), entry.getValue());
                }
                continue;
            }
            properties.put(StringUtils.join(keyPrefix, entry.getKey(), keySuffix), entry.getValue());
        }
        return MapUtils.isEmpty(properties) ? null : properties;
    }

    @Nullable
    public static Properties beanToPropertiesExclusive(@Nullable Object bean, @Nullable String... fields) {
        return beanToPropertiesExclusive(bean, ArrayUtilsWraps.asList(fields), true, true);
    }

    @Nullable
    public static Properties beanToPropertiesExclusive(@Nullable Object bean, @Nullable Collection<String> fields) {
        return beanToPropertiesExclusive(bean, fields, true, true);
    }

    @Nullable
    public static Properties beanToPropertiesExclusive(@Nullable Object bean, @Nullable Collection<String> fields, boolean allowClassKey, boolean allowEmptyValue) {
        if (bean == null) {
            return null;
        }
        BeanMap map = new BeanMap(bean);
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        Properties properties = new Properties();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey() == null || (!allowClassKey && StringUtils.equals(entry.getKey(), JavaKeywordConst.CLASS)) || (!allowEmptyValue && ObjectUtils.isEmpty(entry.getValue()))) {
                continue;
            }
            if (!StringUtilsWraps.equalsAny(entry.getKey(), fields)) {
                properties.put(entry.getKey(), entry.getValue());
            }
        }
        return MapUtils.isEmpty(properties) ? null : properties;
    }

    @Nullable
    public static Properties beanToPropertiesInclusive(@Nullable Object bean, @Nullable String... fields) {
        return beanToPropertiesInclusive(bean, ArrayUtilsWraps.asList(fields), true, true);
    }

    @Nullable
    public static Properties beanToPropertiesInclusive(@Nullable Object bean, @Nullable Collection<String> fields) {
        return beanToPropertiesInclusive(bean, fields, true, true);
    }

    @Nullable
    public static Properties beanToPropertiesInclusive(@Nullable Object bean, @Nullable Collection<String> fields, boolean allowClassKey, boolean allowEmptyValue) {
        if (bean == null || CollectionPlainWraps.isEmpty(fields)) {
            return null;
        }
        BeanMap map = new BeanMap(bean);
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        Properties properties = new Properties();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey() == null || (!allowClassKey && StringUtils.equals(entry.getKey(), JavaKeywordConst.CLASS)) || (!allowEmptyValue && ObjectUtils.isEmpty(entry.getValue()))) {
                continue;
            }
            if (StringUtilsWraps.equalsAny(entry.getKey(), fields)) {
                properties.put(entry.getKey(), entry.getValue());
            }
        }
        return MapUtils.isEmpty(properties) ? null : properties;
    }

    public static void mapToBean(@Nullable Object bean, @Nullable Map<String, ?> map) throws BeanInvocationException {
        if (bean == null || MapUtils.isEmpty(map)) {
            return;
        }
        List<String> excludes = new ArrayList<>();
        FieldUtilsWraps.doWithNestedFields(bean.getClass(), field -> excludes.add(field.getName()), field -> AnnotationUtilsWraps.anyPresent(field, BeanCopyIgnore.class, ViewSubmitIgnore.class));
        mapToBeanExclusive(bean, map, excludes);
    }

    public static void mapToBean(@Nullable Object bean, @Nullable Map<String, ?> map, BiPredicate<String, Object> filter) throws BeanInvocationException {
        if (ObjectUtils.anyNull(bean, filter) || MapUtils.isEmpty(map)) {
            return;
        }
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            boolean result = filter.test(entry.getKey(), entry.getValue());
            if (result) {
                setProperty(bean, entry.getKey(), entry.getValue());
            }
        }
    }

    public static void mapToBean(@Nullable Object bean, @Nullable Map<String, ?> map, BiFunction<String, Object, BooleanDataStruct<Object>> action) throws BeanInvocationException {
        if (ObjectUtils.anyNull(bean, action) || MapUtils.isEmpty(map)) {
            return;
        }
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            BooleanDataStruct<Object> result = action.apply(entry.getKey(), entry.getValue());
            if (result != null && result.isSuccess()) {
                setProperty(bean, entry.getKey(), result.getData());
            }
        }
    }

    public static void mapToBeanExclusive(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable String... fields) throws BeanInvocationException {
        mapToBeanExclusive(bean, map, ArrayUtilsWraps.asList(fields), false);
    }

    public static void mapToBeanExclusive(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable Collection<String> fields) throws BeanInvocationException {
        mapToBeanExclusive(bean, map, fields, false);
    }

    /**
     * @reference "http://blog.csdn.net/q358543781/article/details/50176953"
     */
    @SuppressWarnings({"JavadocDeclaration", "JavadocLinkAsPlainText"})
    public static void mapToBeanExclusive(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable Collection<String> fields, boolean allowEmptyValue) throws BeanInvocationException {
        if (bean == null || MapUtils.isEmpty(map)) {
            return;
        }
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            if (StringUtils.isBlank(entry.getKey()) || StringUtils.equals(entry.getKey(), JavaKeywordConst.CLASS) || (!allowEmptyValue && ObjectUtils.isEmpty(entry.getValue()))) {
                continue;
            }
            if (!StringUtilsWraps.equalsAny(entry.getKey(), fields)) {
                setProperty(bean, entry.getKey(), entry.getValue());
            }
        }
    }

    public static void mapToBeanInclusive(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable String... fields) throws BeanInvocationException {
        mapToBeanInclusive(bean, map, ArrayUtilsWraps.asList(fields), false);
    }

    public static void mapToBeanInclusive(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable Collection<String> fields) throws BeanInvocationException {
        mapToBeanInclusive(bean, map, fields, false);
    }

    /**
     * @reference "http://blog.csdn.net/q358543781/article/details/50176953"
     */
    @SuppressWarnings({"JavadocDeclaration", "JavadocLinkAsPlainText"})
    public static void mapToBeanInclusive(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable Collection<String> fields, boolean allowEmptyValue) throws BeanInvocationException {
        if (bean == null || MapUtils.isEmpty(map) || CollectionPlainWraps.isEmpty(fields)) {
            return;
        }
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            if (StringUtils.isBlank(entry.getKey()) || StringUtils.equals(entry.getKey(), JavaKeywordConst.CLASS) || (!allowEmptyValue && ObjectUtils.isEmpty(entry.getValue()))) {
                continue;
            }
            if (StringUtilsWraps.equalsAny(entry.getKey(), fields)) {
                setProperty(bean, entry.getKey(), entry.getValue());
            }
        }
    }

    public static void mapToBeanQuietly(@Nullable Object bean, @Nullable Map<String, ?> map) {
        try {
            mapToBean(bean, map);
        } catch (Exception ignored) {
        }
    }

    public static void mapToBeanQuietly(@Nullable Object bean, @Nullable Map<String, ?> map, BiPredicate<String, Object> filter) {
        try {
            mapToBean(bean, map, filter);
        } catch (Exception ignored) {
        }
    }

    public static void mapToBeanQuietly(@Nullable Object bean, @Nullable Map<String, ?> map, BiFunction<String, Object, BooleanDataStruct<Object>> action) {
        try {
            mapToBean(bean, map, action);
        } catch (Exception ignored) {
        }
    }

    public static void mapToBeanExclusiveQuietly(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable String... fields) {
        try {
            mapToBeanExclusive(bean, map, fields);
        } catch (Exception ignored) {
        }
    }

    public static void mapToBeanExclusiveQuietly(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable Collection<String> fields) {
        try {
            mapToBeanExclusive(bean, map, fields);
        } catch (Exception ignored) {
        }
    }

    public static void mapToBeanExclusiveQuietly(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable Collection<String> fields, boolean allowEmptyValue) {
        try {
            mapToBeanExclusive(bean, map, fields, allowEmptyValue);
        } catch (Exception ignored) {
        }
    }

    public static void mapToBeanInclusiveQuietly(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable String... fields) {
        try {
            mapToBeanInclusive(bean, map, fields);
        } catch (Exception ignored) {
        }
    }

    public static void mapToBeanInclusiveQuietly(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable Collection<String> fields) {
        try {
            mapToBeanInclusive(bean, map, fields);
        } catch (Exception ignored) {
        }
    }

    public static void mapToBeanInclusiveQuietly(@Nullable Object bean, @Nullable Map<String, ?> map, @Nullable Collection<String> fields, boolean allowEmptyValue) {
        try {
            mapToBeanInclusive(bean, map, fields, allowEmptyValue);
        } catch (Exception ignored) {
        }
    }

    /**
     * Sets the property naming {@code property} of the given {@code bean} object, with value {@code value}
     *
     * @reference "https://stackoverflow.com/questions/22743765/beanutils-not-works-for-chain-setter"
     * @reference "https://blog.csdn.net/u014074757/article/details/106273150/"
     * @see org.apache.commons.beanutils2.BeanUtils#setProperty
     * @see "org.springframework.beans.BeanUtils#copyProperties"
     */
    public static boolean setProperty(@Nullable Object bean, @Nullable String property, @Nullable Object value) throws BeanInvocationException {
        if (bean == null || StringUtils.isBlank(property)) {
            return false;
        }
        try {
            BeanUtils.setProperty(bean, property, value);
            return true;
        } catch (Exception ex) {
            throw new BeanInvocationException(String.format("Could not set property '%s' value of bean '%s'", property, bean.getClass().getName()));
        }
    }

    public static boolean setPropertyQuietly(@Nullable Object bean, @Nullable String property, @Nullable Object value) {
        try {
            return setProperty(bean, property, value);
        } catch (Exception ignored) {
        }
        return false;
    }
}
