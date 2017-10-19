package com.accenture.aris.sample.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.aris.core.support.ServiceResult;
import com.accenture.aris.sample.business.entity.RoleEntity;
import com.accenture.aris.sample.business.repository.RoleRepository;
import com.accenture.aris.sample.business.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private RoleRepository RoleRepository;

    @Override
    public ServiceResult<RoleEntity> selectByPrimaryKey(String id) {
        return new ServiceResult<RoleEntity>(RoleRepository.selectByPrimaryKey(id));
    }

    @Override
    public ServiceResult<List<RoleEntity>> selectRoleByEntity() {
        RoleEntity roleEntity = new RoleEntity();
        return new ServiceResult<List<RoleEntity>>(RoleRepository.selectByEntity(roleEntity));
    }

    @Override
    public ServiceResult<List<RoleEntity>> selectRoleByEntity(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return new ServiceResult<List<RoleEntity>>();
        }
        return new ServiceResult<List<RoleEntity>>(RoleRepository.selectByEntity(roleEntity));
    }

    @Override
    public String getRoleName(String id) {
        String roleData = selectByPrimaryKey(id).getResult().getName();
        return roleData;
    }
}
