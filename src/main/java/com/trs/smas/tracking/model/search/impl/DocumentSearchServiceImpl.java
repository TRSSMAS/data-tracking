package com.trs.smas.tracking.model.search.impl;

import java.util.ArrayList;
import java.util.List;

import com.trs.client.TRSException;
import com.trs.client.TRSResultSet;
import com.trs.smas.tracking.bo.Document;
import com.trs.smas.tracking.model.search.IDocumentSearchService;
import com.trs.smas.tracking.model.search.ITRSEntityBuilder;
import com.trs.smas.tracking.model.search.ITRSShard;
import com.trs.smas.tracking.system.Const;

public class DocumentSearchServiceImpl implements IDocumentSearchService {

	private static final ITRSEntityBuilder<Document> documentBuilder = new ITRSEntityBuilder<Document>(){

		@Override
		public Document build(TRSResultSet current) throws TRSException {
			Document document = new Document();
			document.setSId(current.getString(Const.DOCUMENT_FIELD_SID));
			document.setTitle(current.getString(Const.DOCUMENT_FIELD_TITLE));
			document.setURL(current.getString(Const.DOCUMENT_FIELD_URL));
			document.setGroupName(current.getString(Const.DOCUMENT_FIELD_GROUPNAME));
			document.setSiteName(current.getString(Const.DOCUMENT_FIELD_SITENAME));
			return document;
		}
	};
	
	private List<ITRSShard> shards;
	
	public DocumentSearchServiceImpl(){
		this.shards = new ArrayList<ITRSShard>();
	}
	
	public void setShards(List<ITRSShard> shards){
		for(ITRSShard shard : shards){
			this.shards.add(shard);
		}
	}
	
	public IDocumentSearchService addShard(ITRSShard shard) {
		if(shard != null){
			this.shards.add(shard);
		}
		return this;
	}
	
	@Override
	public Document findFirst(String trsl)	throws TRSException {
		for(ITRSShard shard : this.shards){
			Document document = shard.findFirst(trsl, documentBuilder);
			if(document != null){
				return document;
			}
		}
		return null;
	}

}
