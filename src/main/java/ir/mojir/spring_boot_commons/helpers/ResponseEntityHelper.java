package ir.mojir.spring_boot_commons.helpers;

import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class ResponseEntityHelper {
	
	public static <T> ResponseEntity<Stream<T>> makeResponseEntity(Page<T> result) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-TOTAL-COUNT", String.valueOf(result.getTotalElements()));
		
		return ResponseEntity.ok().headers(headers).body(result.get());
	}
}
