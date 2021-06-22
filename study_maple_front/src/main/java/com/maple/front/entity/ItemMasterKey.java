package com.maple.front.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemMasterKey implements Serializable{

	private Integer item_seq;
	
	private String item_type;
	
	public ItemMasterKey(Integer item_seq, String item_type) {
		this.item_seq = item_seq;
		this.item_type = item_type;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		ItemMasterKey key = (ItemMasterKey)obj;
		
		return Objects.equals(item_seq, key.item_seq) &&
				Objects.equals(item_type, key.item_type);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(item_seq,item_type);
	}
}
