package ir.mojir.spring_boot_commons.helpers;

import java.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ir.mojir.spring_boot_commons.dtos.SimpleJwtToken;

public class SimpleJwtDecoder {
	public static SimpleJwtToken decode(String input) throws JsonMappingException, JsonProcessingException {
		SimpleJwtToken result = new SimpleJwtToken();
		if(Validations.isBlank(input))
			return result;
		String jwtTokenStr = input.replaceAll("Bearer ", "");
		String[] tokenParts = jwtTokenStr.split("\\.");
		String jsonStr = new String(Base64.getUrlDecoder().decode(tokenParts[1]));
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		result = objectMapper.readValue(jsonStr, SimpleJwtToken.class);
		result.setTokenStr(jwtTokenStr);
		return result;
	}
}
