package ir.mojir.spring_boot_commons.helpers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.mime.MediaType;
import org.springframework.web.multipart.MultipartFile;

import ir.mojir.spring_boot_commons.enums.MimeTypeEnum;

public class FileValidator {

	public static class ValidationResult {
		private boolean valid;

		private List<String> messages = new ArrayList<>();

		public boolean isValid() {
			return valid;
		}

		public List<String> getMessages() {
			return messages;
		}

	}

	public static class ValidationCriteria {
		private int maxSize;

		private List<MimeTypeEnum> validMimeTypes = new ArrayList<>();

		public void setMaxSizeInBytes(int maxSizeInBytes) {
			this.maxSize = maxSizeInBytes;
		}

		public void setMaxSizeInMegaBytes(int maxSizeInMegaBytes) {
			this.maxSize = maxSizeInMegaBytes * 1024 * 1024;
		}

		public void addValidMimeType(MimeTypeEnum mime) {
			this.validMimeTypes.add(mime);
		}

	}

	public static ValidationResult validate(MultipartFile file, ValidationCriteria validationCriteria)
			throws IOException {
		return validate(file.getInputStream(), file.getSize(), file.getOriginalFilename(), validationCriteria);
	}
	
	public static ValidationResult validate(byte[] file, String fileName, ValidationCriteria validationCriteria) throws IOException {
		return validate(new ByteArrayInputStream(file), file.length, fileName, validationCriteria);
	}
	
	private static ValidationResult validate(InputStream fileInputStream, long fileSize, String fileOriginalName, 
			ValidationCriteria validationCriteria) throws IOException {
		ValidationResult result = new ValidationResult();

		result.valid = true;

		if (fileSize > validationCriteria.maxSize) {
			result.valid = false;
			result.messages.add(String.format("Maximum size of the file is %d kilobytes. Given file size is %d",
					validationCriteria.maxSize / (1024) , fileSize / (1024)));
		}
		
		MediaType mimeType = detectMimeType(fileInputStream, fileOriginalName);
		
		if (!isMimeTypeSupported(mimeType.toString(), validationCriteria.validMimeTypes)) {
			result.valid = false;
			result.messages.add(String.format("File content type is invalid. Detected type is: %s. Valid types are %s.",
					mimeType, validationCriteria.validMimeTypes));
		}

		return result;
	}

	private static boolean isMimeTypeSupported(String mimeType, List<MimeTypeEnum> validMimeTypes) {
		if(mimeType.contains(";"))
			mimeType = mimeType.split(";")[0];
		
		for (MimeTypeEnum validType : validMimeTypes) {
			if (mimeType.equals(validType.getMime()))
				return true;
		}
		return false;
	}

	private static MediaType detectMimeType(InputStream fileInputStream, String fileOriginalName) throws IOException {
		TikaConfig config = TikaConfig.getDefaultConfig();
		Detector detector = config.getDetector();

		TikaInputStream stream = TikaInputStream.get(fileInputStream);

		Metadata metadata = new Metadata();
		metadata.add(TikaCoreProperties.RESOURCE_NAME_KEY, fileOriginalName);
		return detector.detect(stream, metadata);
	}
}
