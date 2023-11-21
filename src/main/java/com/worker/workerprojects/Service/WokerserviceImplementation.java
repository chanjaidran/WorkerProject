package com.worker.workerprojects.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.worker.workerprojects.Entity.Worker;
import com.worker.workerprojects.Repository.Workerrepository;

@Service
public class WokerserviceImplementation implements Workerservice{

	@Autowired
	Workerrepository workerrepository;
	
	

	@Override
	public Worker createWorker(Worker worker) {
		
		if (worker.getExp()>2) {
			worker.setRole("SE");
			worker.setSalary(50000);
		}
		else {
			worker.setRole("ASE");
			worker.setSalary(25000);
		}
		
		Worker w=workerrepository.save(worker);
		return w;
	}

	@Override
	public Optional<Worker> getWorkerbyid(int id) {
		
		return workerrepository.findById(id);
	}

	@Override
	public void delete(Worker worker) {
		
		workerrepository.delete(worker);
		
	}

	@Override
	public void deletebyworkerID(int id) {
	
		workerrepository.deleteById(id);
		
	}

	@Override
	public void updateWorker(Worker worker) {
		
		workerrepository.save(worker);
		
	}

	@Override
	public List<Worker> findByName(String name) {
		
		return workerrepository.findByName(name);
	}

	@Override
	public List<Worker> findByNameAndRole(String name, String role) {
		
		return workerrepository.findByNameAndRole(name, role);
	}

	@Override
	public List<Worker> findByNameIgnoreCase(String name) {
		
		return workerrepository.findByNameIgnoreCase(name);
	}

	@Override
	public List<Worker> sortByName() {
		
		return workerrepository.sortByName();
	}

	@Override
	public List<Worker> sortByEntityField(String field) {
		
		//return workerrepository.findAll(Sort.by(field));
		return workerrepository.findAll(Sort.by(Sort.Direction.ASC,field));
	}

	@Override
	public Page<Worker> workerWithPagination(int offset, int pagesize) {
		
		
		return workerrepository.findAll(PageRequest.of(offset, pagesize));
		//public Page<Worker> workerWithPagination(int offset, int pagesize,String field)
		//Page<Worker> products=repo.findAll(PageRequest.of(offset, pagesize).withSort(Sort.by(field)));
	}

	@Override
	public List<Worker> getAllworkers() {
		
		return workerrepository.findAll();
	}
	
	
	public String hello()
	{
		return "hello";
	}

}
