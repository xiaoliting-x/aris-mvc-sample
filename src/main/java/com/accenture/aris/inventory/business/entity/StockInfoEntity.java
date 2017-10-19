package com.accenture.aris.inventory.business.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class StockInfoEntity extends StockEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2301987269735797899L;

	private String dvdEanCode;
	private String dvdTitle;
	private String dvdPackageImgPath;
	private String warehouseName;
	private String warehouseAddress;
	private String warehouseTel;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public String getDvdPackageImgPath() {
		return dvdPackageImgPath;
	}

	public void setDvdPackageImgPath(String dvdPackageImgPath) {
		this.dvdPackageImgPath = dvdPackageImgPath;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public String getWarehouseTel() {
		return warehouseTel;
	}

	public void setWarehouseTel(String warehouseTel) {
		this.warehouseTel = warehouseTel;
	}
	

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
