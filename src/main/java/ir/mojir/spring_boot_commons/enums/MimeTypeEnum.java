package ir.mojir.spring_boot_commons.enums;

import org.apache.tika.mime.MediaType;

public enum MimeTypeEnum {
	IMAGE_PNG("image/png"),
	IMAGE_JPG("image/jpg"),
	IMAGE_JPEG("image/jpeg"),
	APPLICATION_ZIP("application/zip"),
	APPLICATION_RAR("application/x-rar-compressed"),
	APPLICATION_PDF("application/pdf"),
	APPLICATION_DOC("application/msword"),
	APPLICATION_DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

	MimeTypeEnum(String mime) {
		this.mime = mime;
	}
	
	private String mime;
	
	public String getMime() {
		return this.mime;
	}

	@Override
	public String toString() {
		return this.mime;
	}

	public static MimeTypeEnum of(MediaType mediaType) {
		String mediaTypeStr = mediaType.toString();
		if(mediaTypeStr.contains(";"))
			mediaTypeStr = mediaTypeStr.split(";")[0];
		for(MimeTypeEnum m: MimeTypeEnum.values()) {
			if(m.mime.equals(mediaTypeStr))
				return m;
		}
		return null;
	}
	
	
}	
