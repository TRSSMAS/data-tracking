/**
 * Title:		TRS SMAS
 * Copyright:	Copyright(c) 2011,TRS. All rights reserved.
 * Company:		北京拓尔思信息技术股份有限公司(www.trs.com.cn)
 */
package com.trs.smas.tracking.model.search;

import com.trs.client.TRSException;
import com.trs.client.TRSResultSet;

/**
 * TRS实体构造接口,根据TRS DB Server检索结果集构造实体对象  @param <T> 实体类型
 *
 * @author 北京拓尔思信息技术股份有限公司
 * @since huangshengbo @ Mar 21, 2012
 */
public interface ITRSEntityBuilder<T> {

	/**
	 * 根据当前检索结果,构造实体对象
	 * @param current 当前检索结果集
	 * @return 实体对象,构造结果
	 * @throws TRSException 构造失败时
	 * @since huangshengbo @ Mar 21, 2012
	 */
	public T build(final TRSResultSet current) throws TRSException;
	
//	/**
//	 * 设置读取结果集的可配置项,如概览字段、细览字段
//	 * @param resultSet 
//	 * @throws TRSException 设置失败时
//	 * @since huangshengbo @ Mar 21, 2012
//	 */
//	public void applyExtendOptions(TRSResultSet resultSet) throws TRSException;
	
}
