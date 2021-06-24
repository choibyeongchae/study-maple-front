package com.maple.front.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
@Table(name="member_information")
@EqualsAndHashCode(callSuper=true)
@Builder
@Data
@SequenceGenerator(name = "member_seq", sequenceName = "member_seq", initialValue = 1, allocationSize = 1)
public class Member extends DateEntityUtil{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "member_seq")
	@ApiModelProperty(value = "회원시퀀스")
	@Column(name = "mbr_no")
	private Integer mbr_no;
	
	@ApiModelProperty(value = "이메일")
	@Column(name="mbr_email")
	private String mbr_email;
	
	@ApiModelProperty(value = "회원명")
	@Column(name="mbr_name")
	private String mbr_name;
	
	@ApiModelProperty(value = "password")
	@Column(name="mbr_pass")
	private String mbr_pass;
	
	@ApiModelProperty(value = "휴대폰번호")
	@Column(name = "mbr_phone")
	private String mbr_phone;
	
	@ApiModelProperty(value = "생년월일")
	@Column(name = "mbr_born")
	private String mbr_born;
	
	@ApiModelProperty(value = "주소")
	@Column(name = "mbr_adress")
	private String mbr_adress;
	
	@ApiModelProperty(value = "회원권한")
	@Column(name="mbr_role")// user role
	private String mbr_role;
	
	@ApiModelProperty(value = "회원우편번호")
	@Column(name = "mbr_zip")
	private String mbr_zip;
	
	
	public List<String> getRoleList() {
		List<String> roleList = new ArrayList<String>();
		roleList.add("ROLE_USER");
		return roleList;
	}

}
