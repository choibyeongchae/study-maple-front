package com.maple.front.util;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class DateEntityUtil {
	
	@Column(name = "regdate")
	private Instant regdate;
	
	@Column(name = "upddate")
	private Instant upddate;
	
}
