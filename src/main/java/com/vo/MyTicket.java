package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyTicket {
	private int reservationNo;
	private Perform perform;
	private String categoryName;
	private Sch sch;
	private Place place;
}
