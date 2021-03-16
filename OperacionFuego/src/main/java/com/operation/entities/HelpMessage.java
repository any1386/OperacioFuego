package com.operation.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelpMessage extends Spacecraft {

	String message;

	public HelpMessage(Position position, String message) {
		this.position = position;

		this.message = message;
	}
}
