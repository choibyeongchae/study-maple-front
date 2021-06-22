package com.maple.front.util;

import java.time.Instant;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateEntityUtil {
	
	@Column(name = "regdate")
	private Instant regdate;
	
	@Column(name = "upddate")
	private Instant upddate;
	
}
