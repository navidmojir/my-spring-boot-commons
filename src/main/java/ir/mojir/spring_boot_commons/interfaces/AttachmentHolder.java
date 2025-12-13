package ir.mojir.spring_boot_commons.interfaces;

import java.util.List;

import ir.mojir.spring_boot_commons.entities.Attachment;

public interface AttachmentHolder {
	String getEntityId();
	
	List<Attachment> getAttachments();
	
	void setAttachments(List<Attachment> attachments);
}
