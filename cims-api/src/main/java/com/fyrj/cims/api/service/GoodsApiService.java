package com.fyrj.cims.api.service;

import com.fyrj.cims.api.dto.Goods;

public interface GoodsApiService {
	
	/***
	 * dubbo测试，获取商品
	 * @param type
	 * @param id
	 * @return
	 */
	Goods findGoods(int type,String id);
}
