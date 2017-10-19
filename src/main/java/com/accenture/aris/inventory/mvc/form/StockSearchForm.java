package com.accenture.aris.inventory.mvc.form;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.accenture.aris.core.support.validator.ValidationConstraints;

public class StockSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -395160474187506157L;
	
	@Pattern(regexp = ValidationConstraints.HALF_WIDTH_ALPHABET_DIGIT)
	private String dvdEanCode;
	private String dvdTitle;
	
	@Pattern(regexp = ValidationConstraints.HALF_WIDTH_ALPHABET_DIGIT)
	private String warehouseId;
	private String warehouseName;
	
	
	public String getDvdEanCode() {
		return dvdEanCode;
	}
	public void setDvdEanCode(String dvdEanCode) {
		this.dvdEanCode = dvdEanCode;
	}
	public String getDvdTitle() {
		return dvdTitle;
	}
	public void setDvdTitle(String dvdTitle) {
		this.dvdTitle = dvdTitle;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	@Override
	public String toString(){
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
