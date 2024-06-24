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


import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;


/**
 * Utilities for {@link net.sf.jsqlparser.parser.CCJSqlParserUtil}
 *
 * @author David Hsing
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class SqlParserWraps {
    public static boolean isStatementParsable(@Nullable InputStream input) {
        return parseStatement(input) != null;
    }

    public static boolean isStatementParsable(@Nullable InputStream input, @Nullable Charset encoding) {
        return parseStatement(input, encoding) != null;
    }

    public static boolean isStatementParsable(@Nullable InputStream input, @Nullable String encoding) {
        return parseStatement(input, encoding) != null;
    }

    public static boolean isStatementParsable(@Nullable Reader reader) {
        return parseStatement(reader) != null;
    }

    public static boolean isStatementParsable(@Nullable String sql) {
        return parseStatement(sql) != null;
    }

    public static boolean isStatementParsable(@Nullable String sql, @Nullable Consumer<CCJSqlParser> action) {
        return parseStatement(sql, action) != null;
    }

    @Nullable
    public static Statement parseStatement(@Nullable InputStream input) {
        if (input == null) {
            return null;
        }
        try {
            return CCJSqlParserUtil.parse(input);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Statement parseStatement(@Nullable InputStream input, @Nullable Charset encoding) {
        if (input == null || encoding == null) {
            return null;
        }
        try {
            return CCJSqlParserUtil.parse(input, encoding.name());
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Statement parseStatement(@Nullable InputStream input, @Nullable String encoding) {
        if (input == null) {
            return null;
        }
        try {
            return CCJSqlParserUtil.parse(input, StringUtils.defaultString(encoding, StandardCharsets.UTF_8.name()));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Statement parseStatement(@Nullable Reader reader) {
        if (reader == null) {
            return null;
        }
        try {
            return CCJSqlParserUtil.parse(reader);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Statement parseStatement(@Nullable String sql) {
        if (StringUtils.isBlank(sql)) {
            return null;
        }
        try {
            return CCJSqlParserUtil.parse(sql);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Nullable
    public static Statement parseStatement(@Nullable String sql, @Nullable Consumer<CCJSqlParser> action) {
        if (StringUtils.isBlank(sql)) {
            return null;
        }
        try {
            return CCJSqlParserUtil.parse(sql, action);
        } catch (Exception ignored) {
        }
        return null;
    }
}
