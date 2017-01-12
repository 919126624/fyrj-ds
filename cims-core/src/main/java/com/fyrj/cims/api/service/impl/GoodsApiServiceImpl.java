package com.fyrj.cims.api.service.impl;

import java.util.Date;
import java.util.UUID;

import com.fyrj.cims.api.dto.Goods;
import com.fyrj.cims.api.service.GoodsApiService;

public class GoodsApiServiceImpl implements GoodsApiService {

	@Override
	public Goods findGoods(int type, String id) {
		Goods goods = new Goods();
		goods.setId(UUID.randomUUID().toString());
		goods.setName("小呀小苹果！");
		goods.setCreateTime(new Date());
		return goods;
	}

}
