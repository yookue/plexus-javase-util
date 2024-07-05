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

package com.yookue.commonplexus.javaseutil.constant;


/**
 * Constants for logging messages
 *
 * @author David Hsing
 * @see jakarta.annotation.PostConstruct
 * @see jakarta.annotation.PreDestroy
 * @see "org.springframework.core.log.LogMessage"
 * @see "org.springframework.beans.factory.InitializingBean"
 * @see "org.springframework.beans.factory.DisposableBean"
 */
@SuppressWarnings("unused")
public abstract class LogMessageConst {
    public static final String CONSTRUCTING = "Constructing instance";    // $NON-NLS-1$
    public static final String POST_CONSTRUCTING = "Post constructing instance";    // $NON-NLS-1$
    public static final String AFTER_PROPERTIES_SETTING = "After properties setting within instance";    // $NON-NLS-1$
    public static final String INITIALIZING = "Initializing instance";    // $NON-NLS-1$
    public static final String PRE_DESTROYING = "Pre destroying instance";    // $NON-NLS-1$
    public static final String DESTROYING = "Destroying instance";    // $NON-NLS-1$
    public static final String EXCEPTION_OCCURRED = "Exception occurred during execution";    // $NON-NLS-1$
}
