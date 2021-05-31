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
@Table(name="BoardIke")
@Builder
@Data
@SequenceGenerator(name = "history_seq", sequenceName = "history_seq", initialValue = 1, allocationSize = 1)
public class BoardIke extends DateEntityUtil implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "history_seq")
	@ApiModelProperty(value = "공감시퀀스")
	@Column(name = "history_seq")
	private Integer history_seq;
	
	@ApiModelProperty(value = "게시판시퀀스")
	@Column(name = "boader_seq")
	private Integer boader_seq;
	
	@ApiModelProperty(value = "회원시퀀스")
	@Column(name="mbr_no")
	private Integer mbr_no;
	
	@ApiModelProperty(value = "회원이메일")
	@Column(name="mbr_email")
	private String mbr_email;
	
	@ApiModelProperty(value = "공감 등록일자")
	@Column(name="regdate")
	private Date regdate;
	
	@ApiModelProperty(value = "공감 수정일자")
	@Column(name="upddate")
	private Date upddate;
}
