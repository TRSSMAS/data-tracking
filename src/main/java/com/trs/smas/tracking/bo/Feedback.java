package com.trs.smas.tracking.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.trs.dev4.jdk16.model.IEntity;

@Entity
@Table(name="`FEEDBACK`")
public class Feedback implements IEntity {

	/**
	 * generated serial version id
	 */
	private static final long serialVersionUID = -6666416584942722333L;

	@Id
	@GeneratedValue(generator="id_generator")
	@GenericGenerator(name = "id_generator", strategy = "native")
	@Column(name="`ID`")
	private int id;
	
	@Column(name="`DOCUMENTID`")
	private String documentId;
	
	@Column(name="`DESCRIPTION`",columnDefinition="TEXT")
	private String description;
	
	@Column(name="`CREATEDTIME`")
	private long createdTime;
	
	@Column(name="`LASTMODIFIEDTIME`")
	private long lastModifiedTime;

	@Column(name="`CREATEDUSER`")
	private String createdUser;
	
	@Column(name="`LASTMODIFIEDUSER`")
	private String lastModifiedUser;
	
	@Override
	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public long getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(long createdTime){
		this.createdTime = createdTime;
	}

	@Override
	public long getLastModifiedTime() {
		return lastModifiedTime;
	}
	
	public void setLastModifiedTime(long lastModifiedTime){
		this.lastModifiedTime = lastModifiedTime;
	}

	@Override
	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser){
		this.createdUser = createdUser;
	}
	
	@Override
	public String getLastModifiedUser() {
		return lastModifiedUser;
	}
	
	public void setLastModifiedUser(String lastModifiedUser){
		this.lastModifiedUser = lastModifiedUser;
	}

}
