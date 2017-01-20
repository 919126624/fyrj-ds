package com.fyrj.system.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RedisTestObj implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private Date createDate;
	private BigDecimal price;
	private double sum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	public RedisTestObj() {
		// TODO Auto-generated constructor stub
	}

	public RedisTestObj(String id, Date createDate, BigDecimal price, double sum) {
		this.id = id;
		this.createDate = createDate;
		this.price = price;
		this.sum = sum;
	}
	
}
