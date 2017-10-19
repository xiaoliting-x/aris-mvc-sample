package com.accenture.aris.inventory.business.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.aris.core.support.ServiceResult;
import com.accenture.aris.core.support.message.Messages;
import com.accenture.aris.core.support.pagination.Pagination;
import com.accenture.aris.core.support.pagination.PaginationUtils;
import com.accenture.aris.inventory.business.entity.StockInfoEntity;
import com.accenture.aris.inventory.business.repository.StockRepository;
import com.accenture.aris.inventory.business.service.StockService;

@Service
public class StockServiceImpl implements StockService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4775138227367404038L;

	@Autowired
	private Messages messages;
	
	@Autowired
	private StockRepository StockRepository;
	
	@Override
	public ServiceResult<Void> searchStockInfoService(StockInfoEntity entity,
			int page) {
		// TODO Auto-generated method stub
		if(entity == null){
			entity = new StockInfoEntity();
		}
//		设置分页处理里使用的值。
//		recordCount = 符合查询条件的记录总数
//		disp = 1页显示的数据个数
//		offset = 显示记录的先头偏移量
		int recordCount = StockRepository.countByStockInfoEntity(entity);
		int disp = 5;
		int offset = (page - 1)*disp;
		
		List<StockInfoEntity> stocks = StockRepository.selectByStockInfoEntity(entity,new RowBounds(offset,disp));
		List<Pagination>pages = PaginationUtils.pagination(recordCount, page, disp);
		
		ServiceResult<Void> result = new ServiceResult<Void>();
		result.setAttribute("stocks", stocks);
		result.setAttribute("pages", pages);
		
		return result;
	}

	@Override
	public ServiceResult<StockInfoEntity> readStockInfoService(int id) {
		// TODO Auto-generated method stub
		StockInfoEntity details=StockRepository.selectByIdDetailedInfo(id);
		ServiceResult<StockInfoEntity> result=new ServiceResult<StockInfoEntity>();
		result.setAttribute("details", details);
		return result;
	}

}
