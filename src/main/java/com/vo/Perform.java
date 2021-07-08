package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perform {
	private int performId;
	private int categoryCode;
	private String performName;
	private String performDetail;
	private String performImg;
	private String detailImg;
	private String runTime;
	
}
