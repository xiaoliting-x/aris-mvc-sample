package com.accenture.aris.sample.business.service;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.aris.sample.business.entity.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:webmvc-config-test.xml")
public class UserServiceTest {
    
    @Autowired
    UserService userService;
    
    @Test
    public void testSearchUserService() {
        String id = "U0001";
        
        UserEntity userEntity = new UserEntity();
        userEntity.setId("U0001");
        
        Assert.assertEquals(userEntity.getId(), userService.searchUserService(id).getResult().getId());
    }
    
    @Test
    public void testSearchUserServiceNull() {
        String id = "U0003";
        
        Assert.assertNull(userService.searchUserService(id).getResult());
    }
    
}
