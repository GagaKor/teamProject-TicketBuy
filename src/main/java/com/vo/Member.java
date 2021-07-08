package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int idx;
	private String userid;
	private String userpw;
	private int memberCode;
	private String phone;
}
