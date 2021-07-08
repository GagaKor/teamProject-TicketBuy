package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
	private int seatId;
	private String seatName;
	private int placeId;
	private int schId;
	private int issue; //0=예매가능/1=예매불가능
}
