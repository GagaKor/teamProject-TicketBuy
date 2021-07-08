package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
	
	private int placeId;
	private String placeName;
	private String placeaddr;
	private String phonNum;
	private int totalSeatNum;
	private int placeRow;
	private int placeCol;
}
