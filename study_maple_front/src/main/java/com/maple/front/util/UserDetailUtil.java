package com.maple.front.util;

import com.maple.front.config.PrincipalDetails;

public class UserDetailUtil {

	PrincipalDetails principalDetails = null;
	
	public void setPrincipalDetails(PrincipalDetails principalDetails) {
		this.principalDetails = principalDetails;
	}
	
	public PrincipalDetails getPrincipalDetails() {
		return this.principalDetails;
	}
	
}
