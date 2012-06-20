/**
 * Title:		TRS SMAS
 * Copyright:	Copyright(c) 2011,TRS. All rights reserved.
 * Company:		北京拓尔思信息技术股份有限公司(www.trs.com.cn)
 */
package com.trs.smas.tracking.model.search;

import com.trs.client.TRSException;
import com.trs.smas.tracking.bo.Document;

/**
 * TRS DB Server文档检索统计服务接口
 * 
 * @author 北京拓尔思信息技术股份有限公司
 * @since huangshengbo @ Mar 23, 2012
 */
public interface IDocumentSearchService {
	
	/**
	 * 添加检索分片
	 * @param shard
	 * @return
	 */
	public IDocumentSearchService addShard(ITRSShard shard) ;

	/**
	 * 获取满足指定检索条件的实体，仅返回第一条纪录
	 * @param trsl 检索条件
	 * @param entityBuilder 检索实体构造
	 * @return
	 * @throws TRSException 检索失败时
	 */
	public Document findFirst(String trsl) throws TRSException;

}
