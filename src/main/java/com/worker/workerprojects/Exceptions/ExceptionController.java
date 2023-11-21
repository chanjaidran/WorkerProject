package com.worker.workerprojects.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class ExceptionController {
	
	
	@ExceptionHandler(WorkernotFoundException.class)
	public ResponseEntity<Object> workerNotFOundException(WorkernotFoundException e)
	{
		return new ResponseEntity<Object>("Employee Not found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(WorkerAlreadyFoundException.class)
	public ResponseEntity<Object> workerAlreadyExists(WorkerAlreadyFoundException e)
	{
		return new ResponseEntity<Object>("Employee Already Exist",HttpStatus.ALREADY_REPORTED);
	}
	
	@ExceptionHandler(WorkerIdNotFoundException.class)
	public Map<String, String> workerIdNotFoundException(WorkerIdNotFoundException e)
	{
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("Error Message", e.getMessage());
		return errorMap;
	}

}
