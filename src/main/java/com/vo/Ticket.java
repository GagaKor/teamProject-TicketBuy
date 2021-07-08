package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	private int ticketId;
	private int schId;
	private int placeId;
	private int seatId;
	private int ticketPrice;
}
