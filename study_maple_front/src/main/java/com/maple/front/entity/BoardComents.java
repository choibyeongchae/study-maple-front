package com.maple.front.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.maple.front.util.DateEntityUtil;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="BoardComents")
@Builder
@Data
@SequenceGenerator(name = "coment_seq", sequenceName = "coment_seq", initialValue = 1, allocationSize = 1)
public class BoardComents extends DateEntityUtil implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "coment_seq")
	@ApiModelProperty(value = "댓글 시퀀스")
	@Column(name = "coment_seq")
	private Integer coment_seq;
	
	@ApiModelProperty(value = "게시판시퀀스")
	@Column(name = "boader_seq")
	private Integer boader_seq;
	
	@ApiModelProperty(value = "회원시퀀스")
	@Column(name="mbr_no")
	private Integer mbr_no;
	
	@ApiModelProperty(value = "회원이메일")
	@Column(name="mbr_email")
	private String mbr_email;
	
	@ApiModelProperty(value = "댓글내용")
	@Column(name="com_contents")
	private String com_contents;
	
}
