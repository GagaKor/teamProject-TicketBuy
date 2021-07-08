package com.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail {
	private Perform perform;
	private String categoryName;
	private List<Actor> actorList;
	private Place place;
	private String seasonDate;
	private int price;
	private List<Sch> schList;
	private List<List<Seat>> seatList;
	private List<Integer> remainSeat;
}
