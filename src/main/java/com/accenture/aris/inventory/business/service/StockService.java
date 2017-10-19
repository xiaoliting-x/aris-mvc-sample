package com.accenture.aris.inventory.business.service;

import java.io.Serializable;

import com.accenture.aris.core.support.ServiceResult;
import com.accenture.aris.inventory.business.entity.StockInfoEntity;

public interface StockService extends Serializable{
	public ServiceResult<Void> searchStockInfoService(StockInfoEntity entity,int page);

	public ServiceResult<StockInfoEntity>readStockInfoService(int id);
}
