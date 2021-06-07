package com.maple.front.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

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
@Table(name="item_master")
@Builder
@Data
@SequenceGenerator(name = "item_seq", sequenceName = "item_seq", initialValue = 1, allocationSize = 1)
public class ItemMaster  extends DateEntityUtil implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "item_seq")
	@ApiModelProperty(value = "아이템 시퀀스")
	@Column(name = "item_seq")
	private Integer item_seq;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "item_type")
	@ApiModelProperty(value = "아이템 구분")
	@Column(name="item_type")
	private String item_type;
	
	@ApiModelProperty(value = "아이템명")
	@Column(name="item_name")
	private String item_name;
	
	@ApiModelProperty(value = "아이템설명")
	@Column(name="item_desc")
	private String item_desc;
	
	@ApiModelProperty(value = "착용레벨")
	@Column(name="item_takelevel")
	private String item_takelevel;
	
	@ApiModelProperty(value = "착용직업")
	@Column(name="item_job")
	private String item_job;
	
	@ApiModelProperty(value = "아이템 힘")
	@Column(name="item_str")
	private String item_str;
	
	@ApiModelProperty(value = "아이템 덱스")
	@Column(name="item_dex")
	private String item_dex;
	
	@ApiModelProperty(value = "아이템 인트")
	@Column(name="item_int")
	private String item_int;
	
	@ApiModelProperty(value = "아이템 럭")
	@Column(name="item_luk")
	private String item_luk;
	
	@ApiModelProperty(value = "아이템 스타포스")
	@Column(name="item_strapos")
	private String item_strapos;
	
	@ApiModelProperty(value = "아이템 장비분류")
	@Column(name="item_ficat")
	private String item_ficat;
	
	@ApiModelProperty(value = "아이템 공격력")
	@Column(name="item_attack")
	private String item_attack;
	
	@ApiModelProperty(value = "아이템 마력")
	@Column(name="item_mattack")
	private String item_mattack;
	
	@ApiModelProperty(value = "아이템 강화수치")
	@Column(name="item_upgarde")
	private String item_upgarde;
}
