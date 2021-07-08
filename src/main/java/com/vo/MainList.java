package com.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainList {	
	private int performId;
	private int placeId;
	private String performImg;
	private String performName;
	private String categoryName;
	private String placeName;
	private String seasonDate;
}
