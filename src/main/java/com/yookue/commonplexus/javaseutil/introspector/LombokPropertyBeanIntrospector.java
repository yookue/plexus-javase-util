/*
 * Copyright 2016 Yookue Ltd. All rights reserved.
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

package com.yookue.commonplexus.javaseutil.introspector;


import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;
import jakarta.annotation.Nonnull;
import org.apache.commons.beanutils2.BeanIntrospector;
import org.apache.commons.beanutils2.IntrospectionContext;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * Allows "org.apache.commons.beanutils.BeanUtils#copyProperties" to copy properties across beans whose
 * properties have been made <b>fluent</b> through <a href="https://projectlombok.org/">Lombok</a>
 * {@link lombok.experimental.Accessors}, {@link lombok.Setter} and {@link lombok.Getter} annotations
 * <p>
 * <pre><code>
 *     PropertyUtils.addBeanIntrospector(new FluentPropertyBeanIntrospector());
 *     PropertyUtils.addBeanIntrospector(new LombokPropertyBeanIntrospector());
 * </code></pre>
 *
 * @author izilotti
 * @reference "https://stackoverflow.com/questions/22743765/beanutils-not-works-for-chain-setter"
 */
@Slf4j
@SuppressWarnings({"unused", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public class LombokPropertyBeanIntrospector implements BeanIntrospector {
    /**
     * Performs introspection
     * This method scans the current class's methods for property write and read methods which have been
     * Created by the <a href="https://projectlombok.org/">Lombok</a> annotations
     *
     * @param context the introspection context
     */
    @Override
    public void introspect(@Nonnull IntrospectionContext context) {
        getLombokMethods(context).forEach((property, methods) -> {
            if (ArrayUtils.getLength(methods) < 2) {
                return;
            }
            PropertyDescriptor descriptor = context.getPropertyDescriptor(property);
            if (descriptor == null) {
                return;
            }
            try {
                descriptor = new PropertyDescriptor(property, methods[1], methods[0]);
                context.addPropertyDescriptor(descriptor);
            } catch (IntrospectionException ex) {
                if (log.isWarnEnabled()) {
                    log.warn(String.format("Error creating PropertyDescriptor, ignored property '%s'", property), ex);
                }
            }
        });
    }

    @Nonnull
    private Map<String, Method[]> getLombokMethods(@Nonnull IntrospectionContext context) {
        Map<String, Method[]> propertyMethods = new HashMap<>();    // property name, write, read
        Stream.of(context.getTargetClass().getMethods()).filter(this::isNotJavaBeanMethod).forEach(method -> {
            String property = getPropertyName(method);
            if (method.getReturnType().isAssignableFrom(context.getTargetClass()) && method.getParameterCount() == 1) {
                if (log.isDebugEnabled()) {
                    log.debug("Found mutator '{}' with parameter '{}'", method.getName(), method.getParameters()[0].getName());
                }
                addWriteMethod(propertyMethods, property, method);
            } else if (method.getReturnType() != Void.TYPE && method.getParameterCount() == 0) {
                if (log.isDebugEnabled()) {
                    log.debug("Found accessor '{}' with no parameter", method.getName());
                }
                addReadMethod(propertyMethods, property, method);
            }
        });
        return propertyMethods;
    }

    private void addReadMethod(@Nonnull Map<String, Method[]> propertyMethods, String propertyName, @Nonnull Method readMethod) {
        if (StringUtils.isBlank(propertyName)) {
            return;
        }
        if (!propertyMethods.containsKey(propertyName)) {
            Method[] writeRead = new Method[2];
            propertyMethods.put(propertyName, writeRead);
        }
        propertyMethods.get(propertyName)[1] = readMethod;
    }

    private void addWriteMethod(@Nonnull Map<String, Method[]> propertyMethods, @Nonnull String propertyName, @Nonnull Method writeMethod) {
        if (StringUtils.isBlank(propertyName)) {
            return;
        }
        if (!propertyMethods.containsKey(propertyName)) {
            Method[] writeRead = new Method[2];
            propertyMethods.put(propertyName, writeRead);
        }
        propertyMethods.get(propertyName)[0] = writeMethod;
    }

    private String getPropertyName(@Nonnull Method method) {
        String methodName = method.getName();
        return StringUtils.isNotEmpty(methodName) ? Introspector.decapitalize(methodName) : methodName.toLowerCase(Locale.ENGLISH);
    }

    private boolean isNotJavaBeanMethod(@Nonnull Method method) {
        return !isGetterMethod(method) || isSetterMethod(method);
    }

    private boolean isGetterMethod(@Nonnull Method method) {
        if (!Modifier.isPublic(method.getModifiers()) || method.getParameterTypes().length != 0) {
            return false;
        }
        if (method.getName().matches("^get[A-Z].*") && method.getReturnType() != Void.TYPE) {    //$NON-NLS-1$
            return true;
        }
        return method.getName().matches("^is[A-Z].*") && method.getReturnType() == Boolean.TYPE;    //$NON-NLS-1$
    }

    private boolean isSetterMethod(@Nonnull Method method) {
        return Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 1 && method.getName().matches("^set[A-Z].*");
    }
}
