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
@Table(name="main_board")
@Builder
@Data
@SequenceGenerator(name = "boader_seq", sequenceName = "boader_seq", initialValue = 1, allocationSize = 1)
public class MainBoard extends DateEntityUtil implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "boader_seq")
	@ApiModelProperty(value = "게시판시퀀스")
	@Column(name = "boader_seq")
	private Integer boader_seq;
	
	@ApiModelProperty(value = "회원시퀀스")
	@Column(name="mbr_no")
	private Integer mbr_no;
	
	@ApiModelProperty(value = "회원이메일")
	@Column(name="mbr_email")
	private String mbr_email;
	
	@ApiModelProperty(value = "게시판 타이틀")
	@Column(name="boader_title")
	private String boader_title;
	
	@ApiModelProperty(value = "게시판 내용")
	@Column(name="boader_contents")
	private String boader_contents;
	
	@ApiModelProperty(value = "게시판 타입")
	@Column(name="boader_type")
	private Date boader_type;
	
	@ApiModelProperty(value = "게시판 조회수")
	@Column(name="boader_viewcnt")
	private String boader_viewcnt;
	
	
}
