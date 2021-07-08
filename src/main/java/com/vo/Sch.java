package com.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sch {
	private int schId;
	private Date schDate;
	private int performId;
	private int placeId;
	private Date startDate;
	private Date endDate;
	
}
