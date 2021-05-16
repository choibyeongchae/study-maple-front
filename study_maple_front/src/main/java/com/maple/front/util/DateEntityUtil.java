package com.maple.front.util;

import java.time.Instant;

import javax.persistence.Column;

public class DateEntityUtil {
	
	@Column(name = "reg_date")
	private Instant createDate;
	
	@Column(name = "upd_date")
	private Instant updateDate;
	
}
