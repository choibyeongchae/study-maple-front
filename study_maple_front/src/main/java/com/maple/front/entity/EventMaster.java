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
@Table(name="event_master")
@Builder
@Data
@SequenceGenerator(name = "even_op", sequenceName = "even_op", initialValue = 1, allocationSize = 1)
public class EventMaster extends DateEntityUtil implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "even_op")
	@ApiModelProperty(value = "이벤트오픈")
	@Column(name = "even_op")
	private Integer even_op;
	
	@ApiModelProperty(value = "이벤트상태코드")
	@Column(name="even_code")
	private String even_code;
	
	@ApiModelProperty(value = "이벤트명")
	@Column(name="even_name")
	private String even_name;
	
	@ApiModelProperty(value = "이벤트 시작기간")
	@Column(name="even_stardate")
	private Date even_stardate;
	
	@ApiModelProperty(value = "이벤트 종료기간")
	@Column(name="even_enddate")
	private Date even_enddate;
	
	@ApiModelProperty(value = "이벤트 타이틀")
	@Column(name="even_title")
	private String even_title;
	
	@ApiModelProperty(value = "이벤트 내용")
	@Column(name="even_contents")
	private String even_contents;
	
	@ApiModelProperty(value = "이벤트 댓글")
	@Column(name="even_coment")
	private String even_coment;
	
	@ApiModelProperty(value = "이벤트 조회수")
	@Column(name="even_viewcnt")
	private String even_viewcnt;
	
 
}
