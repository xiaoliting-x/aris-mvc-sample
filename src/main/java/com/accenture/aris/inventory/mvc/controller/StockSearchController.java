package com.accenture.aris.inventory.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.accenture.aris.core.support.ServiceResult;
import com.accenture.aris.core.support.codeloader.CodeLoader;
import com.accenture.aris.core.support.codeloader.StaticCodeLoader;
import com.accenture.aris.core.support.message.Messages;
import com.accenture.aris.core.support.pagination.Pagination;
import com.accenture.aris.inventory.business.service.StockService;
import com.accenture.aris.inventory.mvc.form.StockSearchForm;
import com.accenture.aris.inventory.business.entity.StockInfoEntity;

;

@Controller
@RequestMapping(value = "/stock")
@SessionAttributes(value = "stockSearchForm")
public class StockSearchController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StockSearchController.class);

	@Autowired
	private Messages messages;
	@Autowired
	private StockService stockService;
	@Autowired
	private StaticCodeLoader staticCodeLoader;
	@Autowired
	private CodeLoader codeLoader;

	@RequestMapping(value = "")
	public String stockAction(StockSearchForm stockSearchForm, Model uiModel,
			SessionStatus status) {

		status.setComplete();

		return "stock/stockList";
	}

	@RequestMapping(value = "/search/{page}")
	public String searchPageAction(@Valid StockSearchForm stockSearchForm,
			BindingResult result, @PathVariable("page") int page, Model uiModel) {
		// 自动注入出错的场合，不进行后续的处理直接迁移到库存查询画面。
		if (result.hasErrors()) {
			LOGGER.debug("invalid request.");
			return "stock/stockList";
		}
		StockInfoEntity stockInfoEntity = new StockInfoEntity();
		BeanUtils.copyProperties(stockSearchForm, stockInfoEntity);
		// 服务逻辑的执行
		ServiceResult<Void> serviceResult = stockService
				.searchStockInfoService(stockInfoEntity, page);
		// 从服务的执行结果取得库存信息实体列表
		List<StockInfoEntity> stockList = (List<StockInfoEntity>) serviceResult
				.getAttribute("stocks");
		// 符合查询条件的库存信息为0件的场合，指定信息。
		if (stockList.size() == 0) {
			LOGGER.debug("the length of stockList is zero");
			uiModel.addAttribute("message", messages.getMessage("I00004"));
		}

		uiModel.addAttribute("stocks", stockList);

		// 从服务的执行结果取得Pagination列表
		List<Pagination> pageList = (List<Pagination>) serviceResult
				.getAttribute("pages");
		uiModel.addAttribute("pages", pageList);
		uiModel.addAttribute("curPage", page);

		// 配置总页数
		if (pageList != null && pageList.size() > 0) {
			uiModel.addAttribute("pageCnt", pageList.get(pageList.size() - 1)
					.getIndex());
		}
		return "stock/stockList";
	}
	
	@RequestMapping(value = "/search")
	public String searchAction(@Valid StockSearchForm stockSearchForm, 
	BindingResult result, Model uiModel) {
	
	    if(result.hasErrors()) {
	        LOGGER.debug("invalid request.");
	        return "stock/stockList";
	    }
	
	    StockInfoEntity stockInfoEntity = new StockInfoEntity();
	    BeanUtils.copyProperties(stockSearchForm, stockInfoEntity);
	
	    ServiceResult<Void> serviceResult 
	= stockService.searchStockInfoService(stockInfoEntity, 1);
	
	    List<StockInfoEntity> stockList
	= (List<StockInfoEntity>) serviceResult.getAttribute("stocks");
	    if (stockList.size() == 0) {
	        LOGGER.debug("the length of stockList is zero");
	        uiModel.addAttribute("message", messages.getMessage("I00004"));
	    }
	    uiModel.addAttribute("stocks", stockList);
	
	    List<Pagination> pageList = (List<Pagination>) serviceResult.getAttribute("pages");
	    uiModel.addAttribute("pages", pageList);
	    uiModel.addAttribute("curPage", 1);
	    if (pageList != null && pageList.size() > 0) {
	        uiModel.addAttribute("pageCnt", pageList.get(pageList.size() - 1).getIndex());            
	    }
	    return "stock/stockList";
	}


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	public static Logger getLogger() {
		return LOGGER;
	}

}
