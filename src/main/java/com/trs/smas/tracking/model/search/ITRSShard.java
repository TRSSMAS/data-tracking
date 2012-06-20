/**
 * Title:		TRS SMAS
 * Copyright:	Copyright(c) 2011,TRS. All rights reserved.
 * Company:		北京拓尔思信息技术股份有限公司(www.trs.com.cn)
 */
package com.trs.smas.tracking.model.search;

import com.trs.client.TRSException;

/**
 * TRS DB Server分片
 * 
 * @author 北京拓尔思信息技术股份有限公司
 * @since huangshengbo @ Mar 20, 2012
 */
public interface ITRSShard {

	/**
	 * 获取TRS DB Server连接地址
	 * 
	 * @return TRS DB Server连接地址
	 * @since huangshengbo @ Mar 22, 2012
	 */
	public String getHost();

	/**
	 * 获取TRS DB Server连接端口
	 * 
	 * @return TRS DB Server连接端口
	 * @since huangshengbo @ Mar 22, 2012
	 */
	public String getPort();

	/**
	 * 获取TRS DB Server连接用户名
	 * 
	 * @return TRS DB Server连接用户名
	 * @since huangshengbo @ Mar 22, 2012
	 */
	public String getUserName();

	/**
	 * 获取TRS DB Server连接密码
	 * 
	 * @return TRS DB Server连接密码
	 * @since huangshengbo @ Mar 22, 2012
	 */
	public String getPassword();

	/**
	 * 获取TRS DB Server连接数据库名
	 * 
	 * @return TRS DB Server连接数据库名
	 * @since huangshengbo @ Mar 22, 2012
	 */
	public String getDatabase();

	/**
	 * 按指定条件检索，返回第一个实体
	 * @param trsl 检索条件
	 * @param entityBuilder 实体构造器
	 * @return
	 */
	public <T> T findFirst(String trsl,ITRSEntityBuilder<T> entityBuilder) throws TRSException;

}
