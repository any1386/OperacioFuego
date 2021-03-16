package com.operation.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {

	double x;
	double y;

	public Position(double[] coordinates) {
		this.x = coordinates[0];
		this.y = coordinates[1];
	}
}
