package com.maple.front.entity;

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
@Table(name="member")
@Builder
@Data
@SequenceGenerator(name = "member_seq", sequenceName = "member_seq", initialValue = 1, allocationSize = 1)
public class Member extends DateEntityUtil{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "member_seq")
	@ApiModelProperty(value = "회원시퀀스")
	@Column(name = "mbrno")
	private Integer mbrno;
	
	@ApiModelProperty(value = "회원명")
	@Column(name="mbr_nm")
	private String mbrnm;
	
	@ApiModelProperty(value = "이메일")
	@Column(name="email")
	private String email;
	
	@ApiModelProperty(value = "password")
	@Column(name="password")
	private String password;
	
	@ApiModelProperty(value = "휴대폰번호")
	@Column(name = "phone")
	private String phone;
	
	@ApiModelProperty(value = "생년월일")
	@Column(name = "birth")
	private String birth;
	
	@ApiModelProperty(value = "회원포인트")
	@Column(name = "mbr_point")
	private Integer mbrpoint;
	
	@ApiModelProperty(value = "주소")
	@Column(name = "address")
	private String address;
	
	@ApiModelProperty(value = "role")
	@Column(name="roles")
	private String roles;
}
