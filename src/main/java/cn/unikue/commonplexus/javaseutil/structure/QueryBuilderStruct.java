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

package cn.unikue.commonplexus.javaseutil.structure;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.Accessors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Structure for React Query Builder
 *
 * @author David Hsing
 *
 * @reference "https://react-querybuilder.js.org"
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue", "JavadocDeclaration", "JavadocLinkAsPlainText"})
public class QueryBuilderStruct implements Serializable {
    private String combinator;
    private String id;
    private String field;
    private String operator;
    private String value;
    private String valueSource;
    private Boolean not;
    private List<QueryBuilderStruct> rules = new ArrayList<>();
}
