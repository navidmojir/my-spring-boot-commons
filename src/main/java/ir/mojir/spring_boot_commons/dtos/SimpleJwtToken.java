package ir.mojir.spring_boot_commons.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleJwtToken {
	@JsonProperty("preferred_username")
	private String preferredUsername;
	
	@JsonProperty("given_name")
	private String givenName;
	
	@JsonProperty("family_name")
	private String familyName;
	
	private String tokenStr;
	
	public String getPreferredUsername() {
		return preferredUsername;
	}
	public void setPreferredUsername(String preferredUsername) {
		this.preferredUsername = preferredUsername;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getTokenStr() {
		return tokenStr;
	}
	public void setTokenStr(String tokenStr) {
		this.tokenStr = tokenStr;
	}
	
	
	
}
