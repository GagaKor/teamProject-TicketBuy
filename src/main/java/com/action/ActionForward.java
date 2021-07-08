package com.action;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionForward {
	private boolean redirect;
	private String url;
	
	
}
