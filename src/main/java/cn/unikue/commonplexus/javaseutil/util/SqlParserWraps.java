/*
 * Copyright (c) 2016 Unikue Ltd. All rights reserved.
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

package cn.unikue.commonplexus.javaseutil.util;


import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;
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
    /**
     * Checks whether the SQL statement from InputStream is parsable
     *
     * @param input The input stream containing SQL statement
     *
     * @return {@code true} if the SQL is parsable, {@code false} otherwise
     */
    public static boolean isStatementParsable(@Nullable InputStream input) {
        return parseStatement(input) != null;
    }

    /**
     * Checks whether the SQL statement from InputStream with encoding is parsable
     *
     * @param input The input stream containing SQL statement
     * @param encoding The charset encoding to use
     *
     * @return {@code true} if the SQL is parsable, {@code false} otherwise
     */
    public static boolean isStatementParsable(@Nullable InputStream input, @Nullable Charset encoding) {
        return parseStatement(input, encoding) != null;
    }

    /**
     * Checks whether the SQL statement from InputStream with encoding name is parsable
     *
     * @param input The input stream containing SQL statement
     * @param encoding The encoding name to use
     *
     * @return {@code true} if the SQL is parsable, {@code false} otherwise
     */
    public static boolean isStatementParsable(@Nullable InputStream input, @Nullable String encoding) {
        return parseStatement(input, encoding) != null;
    }

    /**
     * Checks whether the SQL statement from Reader is parsable
     *
     * @param reader The reader containing SQL statement
     *
     * @return {@code true} if the SQL is parsable, {@code false} otherwise
     */
    public static boolean isStatementParsable(@Nullable Reader reader) {
        return parseStatement(reader) != null;
    }

    /**
     * Checks whether the SQL string is parsable
     *
     * @param sql The SQL statement string
     *
     * @return {@code true} if the SQL is parsable, {@code false} otherwise
     */
    public static boolean isStatementParsable(@Nullable String sql) {
        return parseStatement(sql) != null;
    }

    /**
     * Checks whether the SQL string is parsable with parser customization
     *
     * @param sql The SQL statement string
     * @param action The consumer to customize the parser
     *
     * @return {@code true} if the SQL is parsable, {@code false} otherwise
     */
    public static boolean isStatementParsable(@Nullable String sql, @Nullable Consumer<CCJSqlParser> action) {
        return parseStatement(sql, action) != null;
    }

    /**
     * Parses SQL statement from InputStream
     *
     * @param input The input stream containing SQL statement
     *
     * @return the parsed Statement object, or null if parsing fails
     */
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

    /**
     * Parses SQL statement from InputStream with specified encoding
     *
     * @param input The input stream containing SQL statement
     * @param encoding The charset encoding to use
     *
     * @return the parsed Statement object, or null if parsing fails
     */
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

    /**
     * Parses SQL statement from InputStream with encoding name
     *
     * @param input The input stream containing SQL statement
     * @param encoding The encoding name to use (defaults to UTF-8 if null)
     *
     * @return the parsed Statement object, or null if parsing fails
     */
    @Nullable
    public static Statement parseStatement(@Nullable InputStream input, @Nullable String encoding) {
        if (input == null) {
            return null;
        }
        try {
            return CCJSqlParserUtil.parse(input, Objects.toString(encoding, StandardCharsets.UTF_8.name()));
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Parses SQL statement from Reader
     *
     * @param reader The reader containing SQL statement
     *
     * @return the parsed Statement object, or null if parsing fails
     */
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

    /**
     * Parses SQL statement from string
     *
     * @param sql The SQL statement string
     *
     * @return the parsed Statement object, or null if parsing fails
     */
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

    /**
     * Parses SQL statement from string with parser customization
     *
     * @param sql The SQL statement string
     * @param action The consumer to customize the parser before parsing
     *
     * @return the parsed Statement object, or null if parsing fails
     */
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
