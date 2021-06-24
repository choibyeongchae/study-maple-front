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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="member_ character")
@EqualsAndHashCode(callSuper=true)
@Builder
@Data
public class MemberCharacter extends DateEntityUtil implements Serializable{

	@Id
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
	
}
