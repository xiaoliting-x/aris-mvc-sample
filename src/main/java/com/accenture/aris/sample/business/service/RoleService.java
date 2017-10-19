package com.accenture.aris.sample.business.service;

import java.io.Serializable;
import java.util.List;

import com.accenture.aris.core.support.ServiceResult;
import com.accenture.aris.sample.business.entity.RoleEntity;

public interface RoleService extends Serializable {

    public ServiceResult<List<RoleEntity>> selectRoleByEntity(RoleEntity roleEntity);
    
    public ServiceResult<List<RoleEntity>> selectRoleByEntity();
    
    public ServiceResult<RoleEntity> selectByPrimaryKey(String id);
    
    public String getRoleName(String id);
}
