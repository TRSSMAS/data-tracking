/**
 * Title:		TRS SMAS
 * Copyright:	Copyright(c) 2011,TRS. All rights reserved.
 * Company:		北京拓尔思信息技术股份有限公司(www.trs.com.cn)
 */
package com.trs.smas.tracking.model.search.impl;

import org.apache.log4j.Logger;

import com.trs.client.TRSConnection;
import com.trs.client.TRSException;
import com.trs.client.TRSResultSet;
import com.trs.dev4.jdk16.utils.StringHelper;
import com.trs.smas.tracking.model.search.ITRSEntityBuilder;
import com.trs.smas.tracking.model.search.ITRSShard;

/**
 * {@link ITRSShard}实现
 * 
 * @author 北京拓尔思信息技术股份有限公司
 * @since huangshengbo @ Mar 21, 2012
 */
public class TRSShardImpl implements ITRSShard {
	
	private static final Logger LOG = Logger.getLogger(TRSShardImpl.class);
	
	/**
	 * 连接地址
	 */
	private String host;

	/**
	 * 连接端口
	 */
	private String port;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 数据库名
	 */
	private String database;
	
	public TRSShardImpl(){
	}
	
	public TRSShardImpl(String host,String port,String userName,String password,String database){
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.database = database;
	}
	
	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	/* (non-Javadoc)
	 * @see com.trs.smas.model.search.ITRSShard#getHost()
	 */
	public String getHost() {
		return this.host;
	}
	
	/* (non-Javadoc)
	 * @see com.trs.smas.model.search.ITRSShard#getPort()
	 */
	public String getPort() {
		return this.port;
	}

	/* (non-Javadoc)
	 * @see com.trs.smas.model.search.ITRSShard#getUserName()
	 */
	public String getUserName() {
		return this.userName;
	}

	/* (non-Javadoc)
	 * @see com.trs.smas.model.search.ITRSShard#getPassword()
	 */
	public String getPassword() {
		return this.password;
	}
	

	/* (non-Javadoc)
	 * @see com.trs.smas.model.search.ITRSShard#getDatabase()
	 */
	public String getDatabase() {
		return this.database;
	}
	
	/**
	 * 获取TRS DB Server连接
	 * 
	 * @return TRS DB Server连接
	 * @throws TRSException
	 * @since huangshengbo @ Mar 21, 2012
	 */
	protected TRSConnection connect() throws TRSException{
		TRSConnection connection = new TRSConnection();
		connection.connect(host, port, userName, password);
		return connection;
	}

	/**
	 * 关闭TRS DB Server结果集和连接
	 * 
	 * @param result
	 * @param connection
	 * @since huangshengbo @ Mar 21, 2012
	 */
	protected void close(TRSResultSet result,TRSConnection connection){
		if (result != null) {
			result.close();
			result = null;
		}
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}

	@Override
	public <T> T findFirst(String trsl,ITRSEntityBuilder<T> entityBuilder) throws TRSException{
		if (StringHelper.isEmpty(trsl)) {
			return null;
		}
		
		TRSConnection connection = null;
		TRSResultSet result = null;
		try {
			connection = connect();
			result = connection.executeSelect(database, trsl, false);

			if (result.getRecordCount() <= 0) {
				return null;
			}
			result.moveFirst();
			return entityBuilder.build(result);
		} catch (TRSException e) {
			LOG.error("fail to get entity by trsl:[" + trsl + "]", e);
			throw e;
		} finally {
			close(result, connection);
		}
	}
}
