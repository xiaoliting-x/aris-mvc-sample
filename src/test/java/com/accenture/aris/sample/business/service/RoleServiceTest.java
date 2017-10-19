package com.accenture.aris.sample.business.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.aris.core.support.database.DatabaseOperator;
import com.accenture.aris.core.support.utils.AssertUtils;
import com.accenture.aris.sample.business.entity.RoleEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:webmvc-config-test.xml")
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private DatabaseOperator databaseOperator;
    
    @Before
    public void init() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        // Oracleの場合には、スキーマ名を指定する必要がある
        //DatabaseOperator.execute(DatabaseOperation.CLEAN_INSERT, "data/testSelectRoleByEntity_01_Database.xls", new OracleDataTypeFactory(), "TEST");

        // MySQLの場合には、以下のようにスキーマ名を指定しない
        databaseOperator.execute(DatabaseOperation.CLEAN_INSERT, "data/testSelectRoleByEntity_01_Database.xls", new MySqlDataTypeFactory());

        // Sybaseの場合には、以下のようにスキーマ名とDataTypeFactoryを指定しない
        //DatabaseOperator.execute(DatabaseOperation.CLEAN_INSERT, "data/testSelectRoleByEntity_01_Database.xls");
    }
    
    @After
    public void destroy() throws DataSetException, IOException, SQLException, DatabaseUnitException {
        // Oracleの場合には、スキーマ名を指定する必要がある
        //DatabaseOperator.execute(DatabaseOperation.DELETE, "data/testSelectRoleByEntity_01_Database.xls",  new OracleDataTypeFactory(), "TEST");

        // MySQLの場合には、以下のようにスキーマ名を指定しない
        databaseOperator.execute(DatabaseOperation.DELETE, "data/testSelectRoleByEntity_01_Database.xls", new MySqlDataTypeFactory());

        // Sybaseの場合には、以下のようにスキーマ名とDataTypeFactoryを指定しない
        //DatabaseOperator.execute(DatabaseOperation.DELETE, "data/testSelectRoleByEntity_01_Database.xls");
    }
    
    @Test
    public void testSelectRoleByEntity() throws Exception {
        Map<String, List<?>> map = AssertUtils.getData("data/testSelectRoleByEntity_01_Entity.xml", "data/testSelectRoleByEntity_01_Entity.xls");
        List<RoleEntity> expect = (List<RoleEntity>)map.get("roleEntities");
        List<RoleEntity> actual = roleService.selectRoleByEntity().getResult();
        AssertUtils.assertEquals(expect, actual);
    }
}
