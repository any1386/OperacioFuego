package com.operation.services;

import org.springframework.stereotype.Service;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

@Service
public class LocationService {

    public double[] getLocation(double [] distance,double[][] positions) {
    	
    	TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distance);
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());

        return  solver.solve().getPoint().toArray();

       
    }

}
