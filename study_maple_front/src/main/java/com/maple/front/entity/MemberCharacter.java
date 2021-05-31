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
@Table(name="MemberCharacter")
@Builder
@Data
@SequenceGenerator(name = "caharacter_name", sequenceName = "caharacter_name", initialValue = 1, allocationSize = 1)
public class MemberCharacter extends DateEntityUtil implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "caharacter_name")
	@ApiModelProperty(value = "캐릭터명")
	@Column(name = "caharacter_name")
	private String caharacter_name;
	
	@ApiModelProperty(value = "회원시퀀스")
	@Column(name="mbr_no")
	private Integer mbr_no;
	
	@ApiModelProperty(value = "회원이메일")
	@Column(name="mbr_email")
	private String mbr_email;
	
	@ApiModelProperty(value = "캐릭터레벨")
	@Column(name="cha_level")
	private Integer cha_level;
	
	@ApiModelProperty(value = "캐릭터직업")
	@Column(name="cha_job")
	private String cha_job;
	
	@ApiModelProperty(value = "캐릭터서버")
	@Column(name="cha_server")
	private String cha_server;
	
	@ApiModelProperty(value = "캐릭터 등록일자")
	@Column(name="regdate")
	private Date regdate;
	
	@ApiModelProperty(value = "캐릭터 수정일자")
	@Column(name="upddate")
	private Date upddate;
}
