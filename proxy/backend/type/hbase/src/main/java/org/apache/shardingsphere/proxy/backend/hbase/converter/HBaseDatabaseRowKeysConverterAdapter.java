/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.proxy.backend.hbase.converter;

import org.apache.shardingsphere.sql.parser.sql.common.segment.dml.expr.ExpressionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.dml.expr.InExpression;
import org.apache.shardingsphere.sql.parser.sql.common.segment.dml.expr.ListExpression;
import org.apache.shardingsphere.sql.parser.sql.common.segment.dml.expr.simple.LiteralExpressionSegment;
import java.util.List;
import java.util.stream.Collectors;

/**
 * HBase database row keys converter adapter.
 */
public class HBaseDatabaseRowKeysConverterAdapter {
    
    protected List<String> getRowKeysFromWhereSegmentByIn(final InExpression expression) {
        return ((ListExpression) expression.getRight()).getItems().stream().map(this::getValueByExpressionSegment).collect(Collectors.toList());
    }
    
    protected String getValueByExpressionSegment(final ExpressionSegment item) {
        return String.valueOf(((LiteralExpressionSegment) item).getLiterals());
    }
}
