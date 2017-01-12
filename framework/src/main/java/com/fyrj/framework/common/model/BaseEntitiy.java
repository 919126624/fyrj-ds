package com.fyrj.framework.common.model;

import java.io.Serializable;

/***
 * 基础实体
 * @author Administrator
 *
 */
public class BaseEntitiy implements Serializable {
	private static final long serialVersionUID = 6113546966303802106L;

	private String id ;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
