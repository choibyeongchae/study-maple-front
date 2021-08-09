package com.maple.front.util;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class DateEntityUtil {
	
	@Column(name = "regdate")
	@CreatedDate
	private Instant createDate;
	
	@Column(name = "upddate")
	@LastModifiedDate
	private Instant updateDate;
	
}
