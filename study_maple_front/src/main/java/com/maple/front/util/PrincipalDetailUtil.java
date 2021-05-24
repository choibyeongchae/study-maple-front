package com.maple.front.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.maple.front.config.PrincipalDetails;

import lombok.Data;

@Data
public class PrincipalDetailUtil {

	@Autowired
	public static PrincipalDetails principalDetails;
}
