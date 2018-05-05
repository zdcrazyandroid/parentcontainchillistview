package com.zl.listview;

import java.util.ArrayList;
import java.util.List;



public class PurOrderBean {

	
	private String ddaddress;
	private String ddarea;
	private String ddcity;
	private int ddhouseId;
	private String ddhouseName;
	private String ddoperator;
	private String ddoperatorphone;
	private String ddprovince;
	private double purchasemoney;
	private int id;
	private String beanOrderId = "";// 订单号
	private String beanCreate_Time = "";// 创建时间
	private String beanContactName = "";// 送达人
	private String beanContactPhone = "";// 送达电话
	private String beanWareHoseAddress = "";// 送达地点
	private String beanTotalFinance = "";// 总金额
	private float beanTemporaryTotalFinance;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getBeanTemporaryTotalFinance() {
		return beanTemporaryTotalFinance;
	}

	public void setBeanTemporaryTotalFinance(float beanTemporaryTotalFinance) {
		this.beanTemporaryTotalFinance = beanTemporaryTotalFinance;
	}

	private List<ItemGoodsBean> orderDetailVoList = new ArrayList<ItemGoodsBean>();//商品数组

	public String getBeanOrderId() {
		return beanOrderId;
	}

	public void setBeanOrderId(String beanOrderId) {
		this.beanOrderId = beanOrderId;
	}

	public String getBeanCreate_Time() {
		return beanCreate_Time;
	}

	public void setBeanCreate_Time(String beanCreate_Time) {
		this.beanCreate_Time = beanCreate_Time;
	}

	public String getBeanContactName() {
		return beanContactName;
	}

	public void setBeanContactName(String beanContactName) {
		this.beanContactName = beanContactName;
	}

	public String getBeanContactPhone() {
		return beanContactPhone;
	}

	public void setBeanContactPhone(String beanContactPhone) {
		this.beanContactPhone = beanContactPhone;
	}

	public String getBeanWareHoseAddress() {
		return beanWareHoseAddress;
	}

	public void setBeanWareHoseAddress(String beanWareHoseAddress) {
		this.beanWareHoseAddress = beanWareHoseAddress;
	}

	public String getBeanTotalFinance() {
		return beanTotalFinance;
	}

	public void setBeanTotalFinance(String beanTotalFinance) {
		this.beanTotalFinance = beanTotalFinance;
	}

	public List<ItemGoodsBean> getBeanGoodsList() {
		return orderDetailVoList;
	}

	public void setBeanGoodsList(List<ItemGoodsBean> orderDetailVoList) {
		this.orderDetailVoList = orderDetailVoList;
	}
	
	public String getDdaddress() {
		return ddaddress;
	}

	public void setDdaddress(String ddaddress) {
		this.ddaddress = ddaddress;
	}

	public String getDdarea() {
		return ddarea;
	}

	public void setDdarea(String ddarea) {
		this.ddarea = ddarea;
	}

	public String getDdcity() {
		return ddcity;
	}

	public void setDdcity(String ddcity) {
		this.ddcity = ddcity;
	}

	public int getDdhouseId() {
		return ddhouseId;
	}

	public void setDdhouseId(int ddhouseId) {
		this.ddhouseId = ddhouseId;
	}

	public String getDdhouseName() {
		return ddhouseName;
	}

	public void setDdhouseName(String ddhouseName) {
		this.ddhouseName = ddhouseName;
	}

	public String getDdoperator() {
		return ddoperator;
	}

	public void setDdoperator(String ddoperator) {
		this.ddoperator = ddoperator;
	}

	public String getDdoperatorphone() {
		return ddoperatorphone;
	}

	public void setDdoperatorphone(String ddoperatorphone) {
		this.ddoperatorphone = ddoperatorphone;
	}

	public String getDdprovince() {
		return ddprovince;
	}

	public void setDdprovince(String ddprovince) {
		this.ddprovince = ddprovince;
	}

	public double getPurchasemoney() {
		return purchasemoney;
	}

	public void setPurchasemoney(double purchasemoney) {
		this.purchasemoney = purchasemoney;
	}

	@Override
	public String toString() {
		return "PurOrderBean [ddaddress=" + ddaddress + ", ddarea=" + ddarea
				+ ", ddcity=" + ddcity + ", ddhouseId=" + ddhouseId
				+ ", ddhouseName=" + ddhouseName + ", ddoperator=" + ddoperator
				+ ", ddoperatorphone=" + ddoperatorphone + ", ddprovince="
				+ ddprovince + ", purchasemoney=" + purchasemoney
				+ ", beanOrderId=" + beanOrderId + ", beanCreate_Time="
				+ beanCreate_Time + ", beanContactName=" + beanContactName
				+ ", beanContactPhone=" + beanContactPhone
				+ ", beanWareHoseAddress=" + beanWareHoseAddress
				+ ", beanTotalFinance=" + beanTotalFinance
				+ ", beanTemporaryTotalFinance=" + beanTemporaryTotalFinance
				+ ", orderDetailVoList=" + orderDetailVoList + "]";
	}


	
	

}
