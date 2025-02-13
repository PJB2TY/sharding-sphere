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

package org.apache.shardingsphere.transaction.util;

import org.apache.shardingsphere.distsql.statement.rdl.resource.unit.type.RegisterStorageUnitStatement;
import org.apache.shardingsphere.sql.parser.statement.core.segment.generic.table.SimpleTableSegment;
import org.apache.shardingsphere.sql.parser.statement.core.statement.dml.SelectStatement;
import org.apache.shardingsphere.sql.parser.statement.mysql.ddl.MySQLCreateTableStatement;
import org.apache.shardingsphere.sql.parser.statement.mysql.dml.MySQLInsertStatement;
import org.apache.shardingsphere.sql.parser.statement.mysql.dml.MySQLSelectStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class AutoCommitUtilsTest {
    
    @Test
    void assertNeedOpenTransactionForSelectStatement() {
        SelectStatement selectStatement = new MySQLSelectStatement();
        assertFalse(AutoCommitUtils.needOpenTransaction(selectStatement));
        selectStatement.setFrom(mock(SimpleTableSegment.class));
        assertTrue(AutoCommitUtils.needOpenTransaction(selectStatement));
    }
    
    @Test
    void assertNeedOpenTransactionForDDLOrDMLStatement() {
        MySQLCreateTableStatement sqlStatement = new MySQLCreateTableStatement();
        sqlStatement.setIfNotExists(true);
        assertTrue(AutoCommitUtils.needOpenTransaction(sqlStatement));
        assertTrue(AutoCommitUtils.needOpenTransaction(new MySQLInsertStatement()));
    }
    
    @Test
    void assertNeedOpenTransactionForOtherStatement() {
        assertFalse(AutoCommitUtils.needOpenTransaction(mock(RegisterStorageUnitStatement.class)));
    }
}
