package com.operation.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import com.operation.controller.IntelligentService;
import com.operation.controller.LocationException;
import com.operation.entities.HelpMessage;
import com.operation.entities.Position;
import com.operation.entities.Spacecraft;
import com.operation.services.exception.MessageException;
import com.operation.wrapper.SateliteWrapper;

@Service
public class IntelligentServiceImpl implements IntelligentService {

    @Autowired
    LocationService locationService;

    @Autowired
    MessageService messageService;

    @Autowired
    private Environment environment;

    @Override
    public Spacecraft getSpacecraft(RequestEntity requestEntity) throws MessageException, LocationException {

        SateliteWrapper sateliteWrapper = (SateliteWrapper)requestEntity.getBody();
        if(sateliteWrapper.getMessages().size() < 2)
            throw new MessageException("Nùmero de mensajes insuficientes");
        String message = messageService.getMessage(sateliteWrapper.getMessages());

        uploadPositions(sateliteWrapper);
        if( (sateliteWrapper.getPositions().length < 2) || (sateliteWrapper.getDistances().length < 2) )
            throw new LocationException("Nùmero posicion o distancias insuficientes");
        double [] points = locationService.getLocation( sateliteWrapper.getDistances(),
        		sateliteWrapper.getPositions());
        Position position = new Position(points);

        return new HelpMessage(position, message);
    }

    public void uploadPositions(SateliteWrapper sateliteWrapper){

        if(sateliteWrapper.getPositions()[0] == null) {
            int numberSat = Integer.parseInt(environment.getProperty("satellites.numbers"));
            double[][] pointsList = new double[numberSat][];
            String[] satellitePos;
            for (int i = 0; i < sateliteWrapper.getSatelites().size(); i++) {
                satellitePos = environment.getProperty("satellites." + i + ".position").split(",");
                pointsList[i] = Arrays.stream(satellitePos)
                        .map(Double::valueOf)
                        .mapToDouble(Double::doubleValue)
                        .toArray();
            }
            sateliteWrapper.setPositions(pointsList);
        }
    }

}

