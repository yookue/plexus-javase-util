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


import java.util.List;
import java.util.Objects;
import jakarta.annotation.Nullable;
import org.apache.commons.beanutils2.BeanIntrospector;
import org.apache.commons.beanutils2.BeanUtilsBean;
import org.apache.commons.beanutils2.FluentPropertyBeanIntrospector;
import org.apache.commons.beanutils2.PropertyUtils;
import org.apache.commons.beanutils2.PropertyUtilsBean;


/**
 * Utilities for {@link org.apache.commons.beanutils2.BeanIntrospector}
 *
 * @author David Hsing
 * @see org.apache.commons.beanutils2.BeanIntrospector
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class BeanIntrospectorWraps {
    public static boolean containsPropertyIntrospector(@Nullable Class<? extends BeanIntrospector> clazz) {
        if (clazz == null) {
            return false;
        }
        PropertyUtilsBean bean = BeanUtilsBean.getInstance().getPropertyUtils();
        if (bean == null) {
            return false;
        }
        List<?> introspectors = FieldUtilsWraps.readDeclaredFieldAs(bean, "introspectors", true, List.class);    // $NON-NLS-1$
        return CollectionPlainWraps.isNotEmpty(introspectors) && introspectors.stream().filter(Objects::nonNull).anyMatch(element -> ClassUtilsWraps.isAssignableValue(clazz, element));
    }

    public static void initPropertyIntrospectors() {
        if (!containsPropertyIntrospector(FluentPropertyBeanIntrospector.class)) {
            PropertyUtils.addBeanIntrospector(new FluentPropertyBeanIntrospector());
        }
    }
}
