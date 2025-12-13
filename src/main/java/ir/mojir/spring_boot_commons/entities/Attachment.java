package ir.mojir.spring_boot_commons.entities;

import java.util.Date;

import ir.mojir.spring_boot_commons.enums.MimeTypeEnum;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String type;
	
	private String fileName;
	
	@Basic(fetch = FetchType.LAZY)
    @Lob
	private byte[] bytes;
	
	@Enumerated(EnumType.STRING)
    private MimeTypeEnum mimeType;
	
	private long size;
	
	private String description;
	
	private Date createdAt;
	
	private String uploaderUserId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public MimeTypeEnum getMimeType() {
		return mimeType;
	}

	public void setMimeType(MimeTypeEnum mimeType) {
		this.mimeType = mimeType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUploaderUserId() {
		return uploaderUserId;
	}

	public void setUploaderUserId(String uploaderUserId) {
		this.uploaderUserId = uploaderUserId;
	}
	
	
	
	
}
