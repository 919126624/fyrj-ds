package com.fyrj.framework.mybatis;

/***
 * mysql分页
 * @author caiying
 * @date 2016-5-10 上午10:13:10
 * @version 1.0.0
 */
public class MysqlDialect extends Dialect{
	public boolean supportsLimitOffset(){
		return true;
	}
	
    public boolean supportsLimit() {   
    	
        return true;   
    }
	@Override
	public String getLimitString(String sql, int offset,
			String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {   
        	return sql + " limit "+offsetPlaceholder+","+limitPlaceholder; 
        } else {   
            return sql + " limit "+limitPlaceholder;
        }  
	}

}
