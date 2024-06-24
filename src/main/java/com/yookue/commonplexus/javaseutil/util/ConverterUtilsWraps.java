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


import java.lang.reflect.Array;
import javax.annotation.Nullable;
import org.apache.commons.beanutils2.BeanUtilsBean;
import org.apache.commons.beanutils2.ConvertUtils;
import org.apache.commons.beanutils2.Converter;
import org.apache.commons.beanutils2.converters.ArrayConverter;
import org.apache.commons.lang3.ObjectUtils;


/**
 * Utilities for {@link org.apache.commons.beanutils2.ConvertUtils}
 *
 * @author David Hsing
 * @see org.apache.commons.beanutils2.ConvertUtils
 * @see org.apache.commons.beanutils2.ConvertUtilsBean
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class ConverterUtilsWraps {
    /**
     * @see org.apache.commons.beanutils2.ConvertUtilsBean#registerArrayConverter
     */
    @SuppressWarnings({"unchecked", "DataFlowIssue", "RedundantSuppression", "JavadocReference"})
    public static <T> void registerArrayConverter(@Nullable Class<T> clazz, @Nullable Converter<T> converter) {
        if (ObjectUtils.allNotNull(clazz, converter)) {
            Class<T[]> arrayType = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
            Converter<T[]> arrayConverter = new ArrayConverter<>(arrayType, converter, 0);
            ConvertUtils.register(arrayConverter, arrayType);
        }
    }

    public static <T> void registerClassConverter(@Nullable Class<T> clazz, @Nullable Converter<T> converter) {
        if (ObjectUtils.allNotNull(clazz, converter)) {
            ConvertUtils.register(converter, clazz);
            registerArrayConverter(clazz, converter);
        }
    }

    /**
     * @see org.apache.commons.beanutils2.ConvertUtilsBean#register(boolean, boolean, int)
     */
    public static void registerNullConverter() {
        BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
    }
}
