package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
	private int actorId;
	private int performId;
	private String name;
	private String part;
	private int actorCode;
	
}
