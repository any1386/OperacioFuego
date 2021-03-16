package com.operation.entities;

import java.util.List;

public class Satelite extends Spacecraft {

    private List<String> message;
    double distance;
	
	public List<String> getMessage() {
		return message;
	}
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setPosition(Position position) {
		this.position = position;
		
	}
	public Object getPosition() {
		return position;
	}


}
