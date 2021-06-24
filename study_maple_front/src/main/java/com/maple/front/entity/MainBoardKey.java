package com.maple.front.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MainBoardKey implements Serializable{

	private Integer boader_seq;
	
	private Integer mbr_no;
	
	private String mbr_email;
	
	public MainBoardKey(Integer boader_seq, Integer mbr_no, String mbr_email) {
		this.boader_seq = boader_seq;
		this.mbr_no = mbr_no;
		this.mbr_email = mbr_email;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		MainBoardKey key = (MainBoardKey)obj;
		
		return Objects.equals(boader_seq, key.boader_seq) &&
				Objects.equals(mbr_no, key.mbr_no) &&
				Objects.equals(mbr_email, key.mbr_email);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(boader_seq,mbr_no,mbr_email);
	}
}
