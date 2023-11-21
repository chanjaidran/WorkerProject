package com.worker.workerprojects.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.worker.workerprojects.Entity.Worker;

public interface Workerservice {
	
	List<Worker> getAllworkers();
	
	Worker createWorker(Worker worker);
	
	Optional<Worker> getWorkerbyid(int id);
	
	void delete(Worker worker);

	void deletebyworkerID(int id);
	
	void updateWorker(Worker worker);
	
	
	
	
	
	List<Worker> findByName(String name);
	
	List<Worker> findByNameAndRole(String name,String role);
	
	List<Worker> findByNameIgnoreCase(String name);
	
	
	
	List<Worker> sortByName();
	
	List<Worker> sortByEntityField(String field);
	
	Page<Worker> workerWithPagination(int offset,int pagesize);

}
