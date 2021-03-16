package com.operation.wrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.operation.entities.Position;
import com.operation.entities.Satelite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SateliteWrapper {
	private List<Satelite> satelites;

	public double[] getDistances(){

        double [] distances = new double[satelites.size()];
        for(int i = 0; i < satelites.size(); i ++){
            distances[i] = satelites.get(i).getDistance();
        }
        return  distances;
    }

    public double[][] getPositions(){
        double [][] positions = new double[satelites.size()][];
        String[] points;
        for(int i = 0; i < satelites.size(); i ++){
            if(satelites.get(i).getPosition() != null) {
                points = satelites.get(i).getPosition().toString().split(",");
                positions[i] = Arrays.stream(points)
                        .map(Double::valueOf)
                        .mapToDouble(Double::doubleValue)
                        .toArray();
            }
        }
        return positions;
    }

    public void setPositions(double[][] pointsList){
        Position position;
        for(int i = 0; i < pointsList.length; i++){
            position = new Position(pointsList[i]);
            satelites.get(i).setPosition(position);
        }
    }

    public List<List<String>> getMessages(){
        List<List<String>> messages = new ArrayList<List<String>>();
        for(Satelite s : satelites){
            messages.add(s.getMessage());
        }
        return  messages;
    }

    public List<Satelite> getSatelites() {
        return satelites;
    }
	public void setSatelites(List<Satelite> satelites) {
		this.satelites = satelites;
	}
}
