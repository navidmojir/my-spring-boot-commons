package ir.mojir.spring_boot_commons.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import ir.mojir.spring_boot_commons.entities.Attachment;
import ir.mojir.spring_boot_commons.enums.MimeTypeEnum;
import ir.mojir.spring_boot_commons.exceptions.EntityNotFoundException;
import ir.mojir.spring_boot_commons.exceptions.InternalErrorException;
import ir.mojir.spring_boot_commons.exceptions.InvalidInputException;
import ir.mojir.spring_boot_commons.interfaces.AttachmentHolder;
import ir.mojir.spring_boot_commons.repositories.AttachmentRepo;

@Component
public class AttachmentHandler {

	private static Logger logger = LoggerFactory.getLogger(AttachmentHandler.class);
	
	private MimeTypeEnum[] attachmentAcceptedMimeTypes = new MimeTypeEnum[] {
			MimeTypeEnum.IMAGE_PNG,
			MimeTypeEnum.IMAGE_JPG,
			MimeTypeEnum.IMAGE_JPEG,
			MimeTypeEnum.APPLICATION_ZIP,
			MimeTypeEnum.APPLICATION_RAR,
			MimeTypeEnum.APPLICATION_PDF,
			MimeTypeEnum.APPLICATION_DOC,
			MimeTypeEnum.APPLICATION_DOCX
		};
	private int maxAttachmentSizeInMegaBytes = 5;
	
	
	@Autowired
	private AttachmentRepo attachmentRepo;
	
	public <T extends AttachmentHolder> void uploadAttachment(T entity, MultipartFile file, String currentUserId, String type, String description) {
        logger.info("Uploading attachment with filename {} for entity with id {}", file.getOriginalFilename(), entity.getEntityId());
        byte[] fileBytes = getFileBytes(file);
        MimeTypeEnum detectedMimeType = validateAttachmentFile(file);
        Attachment attachment = new Attachment();
        attachment.setBytes(fileBytes);
        attachment.setCreatedAt(new Date());
        attachment.setFileName(file.getOriginalFilename());
        attachment.setUploaderUserId(currentUserId);
        attachment.setMimeType(detectedMimeType);
        attachment.setSize(file.getSize());
        attachment.setType(type);
        attachment.setDescription(description);
        attachmentRepo.save(attachment);
        
        if(entity.getAttachments() == null)
        	entity.setAttachments(new ArrayList<>());
        
        entity.getAttachments().add(attachment);
        	
    }

    public <T extends AttachmentHolder> Attachment downloadAttachment(T entity, long attachmentId) {
        logger.info("downloading attachment with id {} from entity with id {}", attachmentId, entity.getEntityId());
        for(Attachment attachment: entity.getAttachments()) {
        	if(attachment.getId() == attachmentId)
        		return attachment;
        }
        throw new EntityNotFoundException("Attachment with id " + attachmentId + " was not found", null);
    }

    public <T extends AttachmentHolder> List<Attachment> getAllAttachments(T entity) {
        logger.info("Getting attachments of entity with id {}", entity.getEntityId());
        return entity.getAttachments();
    }

    public <T extends AttachmentHolder> void removeAttachment(T entity, long attachmentId) {
        logger.info("deleting attachment with id {} from entity {}", attachmentId, entity.getEntityId());
        Attachment attachment = findAttachmentById(attachmentId);
        for(int i = 0; i < entity.getAttachments().size(); i++) {
        	if(entity.getAttachments().get(i).getId() == attachmentId)
        		entity.getAttachments().remove(i);
        }
        attachmentRepo.delete(attachment);
    }

    private Attachment findAttachmentById(long attachmentId) {
        Optional<Attachment> optAttachment = attachmentRepo.findById(attachmentId);
        if(optAttachment.isEmpty())
            throw new EntityNotFoundException(attachmentId, null);
        Attachment attachment = optAttachment.get();
        return attachment;
    }
    
    private byte[] getFileBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (Exception e) {
            throw new InternalErrorException("An exception occured while getting bytes of the uploaded file", e);
        }
    }
    
    private MimeTypeEnum validateAttachmentFile(MultipartFile file) {
        FileValidator.ValidationCriteria criteria = new FileValidator.ValidationCriteria();

        criteria.setMaxSizeInMegaBytes(maxAttachmentSizeInMegaBytes);
        for(MimeTypeEnum mime: attachmentAcceptedMimeTypes) {
            criteria.addValidMimeType(mime);
        }
        FileValidator.ValidationResult result;
        try {
            result = FileValidator.validate(file, criteria);
        } catch (IOException e) {
            throw new InternalErrorException("Failed to open file for validation", e);
        }
        if(!result.isValid())
            throw new InvalidInputException("uploaded file is invalid. reason = " + result.getMessages());
        return result.getDetectedMimeType();
    }

	public void setAttachmentAcceptedMimeTypes(MimeTypeEnum[] attachmentAcceptedMimeTypes) {
		this.attachmentAcceptedMimeTypes = attachmentAcceptedMimeTypes;
	}

	public void setMaxAttachmentSizeInMegaBytes(int maxAttachmentSizeInMegaBytes) {
		this.maxAttachmentSizeInMegaBytes = maxAttachmentSizeInMegaBytes;
	}
    
    
}
