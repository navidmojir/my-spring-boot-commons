package ir.mojir.spring_boot_commons.enums;

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
	
	
	
}	
