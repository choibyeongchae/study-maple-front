package com.maple.front.util;

import java.time.Instant;

import javax.persistence.Column;

public class DateEntityUtil {
	
	@Column(name = "regdate")
	private Instant regdate;
	
	@Column(name = "upddate")
	private Instant upddate;
	
}
