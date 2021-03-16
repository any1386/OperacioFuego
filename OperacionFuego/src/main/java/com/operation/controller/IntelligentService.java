package com.operation.controller;

import org.springframework.http.RequestEntity;

import com.operation.entities.Spacecraft;
import com.operation.services.exception.MessageException;

public interface IntelligentService {
    public Spacecraft getSpacecraft(RequestEntity requestEntity) throws MessageException, LocationException;

}
