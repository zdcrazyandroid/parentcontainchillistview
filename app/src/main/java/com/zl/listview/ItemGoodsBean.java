package com.zl.listview;

import java.util.ArrayList;
import java.util.List;

/**
 * 货物信息
 * 
 * @author xieyw
 * 
 */
public class ItemGoodsBean {
	
	private String goodsname;
	private int goodssum;
	private long id;
	private double price;
	private double totalmoney;
	private String goodspicurl;

	private String beanName = "";// 商品名称
	private String beanId = "";// 商品Id
	private int beanCount = 0;// 商品数量
	private String beanPrice = "";// 商品价钱
	private String beanPic = "";// 商品价钱
	private int beanLack = 0; // 缺少的个数
	private List<ItemPriceInBean> beanPriceLists = new ArrayList<ItemPriceInBean>();// 价格区间数组

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	public int getBeanCount() {
		return beanCount;
	}

	public void setBeanCount(int beanCount) {
		this.beanCount = beanCount;
	}

	public String getBeanPrice() {
		return beanPrice;
	}

	public void setBeanPrice(String beanPrice) {
		this.beanPrice = beanPrice;
	}

	public List<ItemPriceInBean> getBeanPriceLists() {
		return beanPriceLists;
	}

	public void setBeanPriceLists(List<ItemPriceInBean> beanPriceLists) {
		this.beanPriceLists = beanPriceLists;
	}

	public String getBeanPic() {
		return beanPic;
	}

	public void setBeanPic(String beanPic) {
		this.beanPic = beanPic;
	}

	public int getBeanLack() {
		return beanLack;
	}

	public void setBeanLack(int beanLack) {
		this.beanLack = beanLack;
	}
	
	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public int getGoodssum() {
		return goodssum;
	}

	public void setGoodssum(int goodssum) {
		this.goodssum = goodssum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}
	
	public String getGoodspicurl() {
		return goodspicurl;
	}

	public void setGoodspicurl(String goodspicurl) {
		this.goodspicurl = goodspicurl;
	}

	@Override
	public String toString() {
		return "ItemGoodsBean [goodsname=" + goodsname + ", goodssum="
				+ goodssum + ", id=" + id + ", price=" + price
				+ ", totalmoney=" + totalmoney + ", beanName=" + beanName
				+ ", beanId=" + beanId + ", beanCount=" + beanCount
				+ ", beanPrice=" + beanPrice + ", beanPic=" + beanPic
				+ ", beanLack=" + beanLack + ", beanPriceLists="
				+ beanPriceLists + "]";
	}
    

	
}
