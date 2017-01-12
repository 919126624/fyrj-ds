package com.fyrj.cims.api.dto;

import java.util.Date;

import com.fyrj.framework.common.model.BaseEntitiy;

public class Goods extends BaseEntitiy{
	
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
