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


import org.apache.commons.lang3.StringUtils;


/**
 * Constants for Chinese variants
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public abstract class ChineseVariantConst {
    public static final String ANNO_DOMINI = "公元";    // $NON-NLS-1$
    public static final String BEFORE_CHRIST = ANNO_DOMINI + "前";    // $NON-NLS-1$
    public static final String CHRISTIAN_ERA = ANNO_DOMINI + "元年";    // $NON-NLS-1$
    public static final String DOT_CN = "点";    // $NON-NLS-1$
    public static final String DOT_TW = "點";    // $NON-NLS-1$
    public static final String NEATNESS = "整";    // $NON-NLS-1$
    public static final String NEGATIVE_CN = "负";    // $NON-NLS-1$
    public static final String NEGATIVE_TW = "負";    // $NON-NLS-1$
    public static final String YUAN_CN = "元";    // $NON-NLS-1$
    public static final String YUAN_TW = "圆";    // $NON-NLS-1$
    public static final String ZERO = "零";    // $NON-NLS-1$

    public static final String[] DATE_UNITS = StringUtils.split("年 月 日");    // $NON-NLS-1$
    public static final String[] NEGATIVE_UNITS_CN = StringUtils.split("角 分 厘 毫 丝 忽 微 纤 沙 尘 埃 渺 莫 模糊 逡巡 须臾 瞬息 弹指 刹那 六德 空虚 清静");    // $NON-NLS-1$
    public static final String[] NEGATIVE_UNITS_TW = StringUtils.split("角 分 厘 毫 絲 忽 微 纖 沙 塵 埃 渺 莫 模糊 逡巡 須臾 瞬息 彈指 刹那 六德 空虛 清靜");    // $NON-NLS-1$
    public static final String[] NUMBER_CAPITALS_CN = StringUtils.split("零 一 二 三 四 五 六 七 八 九");    // $NON-NLS-1$
    public static final String[] NUMBER_CAPITALS_TW = StringUtils.split("零 壹 贰 叁 肆 伍 陆 柒 捌 玖");    // $NON-NLS-1$
    public static final String[] NUMBER_SECTIONS_CN = StringUtils.split("十 百 千");    // $NON-NLS-1$
    public static final String[] NUMBER_SECTIONS_TW = StringUtils.split("拾 佰 仟");    // $NON-NLS-1$
    public static final String[] POSITIVE_UNITS_CN = StringUtils.split("万 亿 兆 京 垓 杼 穰 沟 涧 正 载 极 恒河沙 阿僧祗 那由它 不可思议 无量 大数");    // $NON-NLS-1$
    public static final String[] POSITIVE_UNITS_TW = StringUtils.split("萬 億 兆 京 垓 杼 穰 溝 澗 正 載 極 恒河沙 阿僧祗 那由它 不可思議 無量 大數");    // $NON-NLS-1$
    public static final String[] TIME_UNITS_CN = StringUtils.split("时 分 秒 毫秒 微秒 纳秒 飞秒");    // $NON-NLS-1$
    public static final String[] TIME_UNITS_TW = StringUtils.split("時 分 秒 毫秒 微秒 納秒 飛秒");    // $NON-NLS-1$
}
