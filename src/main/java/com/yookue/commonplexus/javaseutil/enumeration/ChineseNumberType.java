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

package com.yookue.commonplexus.javaseutil.enumeration;


/**
 * Enumerations of Chinese Number types
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public enum ChineseNumberType {
    ZH_CN_FINANCE,
    ZH_CN_GENERAL,
    ZH_TW_FINANCE,
    ZH_TW_GENERAL;

    public boolean isZhCN() {
        return this == ZH_CN_FINANCE || this == ZH_CN_GENERAL;
    }

    public boolean isZhTW() {
        return this == ZH_TW_FINANCE || this == ZH_TW_GENERAL;
    }

    public boolean isFinance() {
        return this == ZH_CN_FINANCE || this == ZH_TW_FINANCE;
    }

    public boolean isGeneral() {
        return this == ZH_CN_GENERAL || this == ZH_TW_GENERAL;
    }

    public boolean isSimplified() {
        return this == ZH_CN_GENERAL;
    }

    public boolean isTraditional() {
        return this == ZH_CN_FINANCE || this == ZH_TW_FINANCE || this == ZH_TW_GENERAL;
    }
}
