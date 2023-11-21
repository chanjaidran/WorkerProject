package com.worker.workerprojects.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.worker.workerprojects.Entity.Worker;
import com.worker.workerprojects.Exceptions.WorkerAlreadyFoundException;
import com.worker.workerprojects.Exceptions.WorkerIdNotFoundException;
import com.worker.workerprojects.Exceptions.WorkernotFoundException;
import com.worker.workerprojects.Repository.Workerrepository;
import com.worker.workerprojects.Service.Workerservice;

@RestController
public class Workercontroller {

	@Autowired
	Workerrepository workerrepository;
	
	@Autowired
	Workerservice workerservice;
	
	@GetMapping("/hi")
	public String hello()
	{
		return "Hi Jaichandran Welcome back";
	}
	
	@GetMapping("/woerkerid/{id}")
	public Optional<Worker> byWorkerid(@PathVariable("id") int id)
	{
		Optional<Worker> getId=workerservice.getWorkerbyid(id);
		if (getId.isPresent()) {
			return getId;
		}
		else {
			throw new WorkerIdNotFoundException(id +" Not Fund");
		}
		
		
	}
	
	@GetMapping("/wokerslist")
	public List<Worker> getAllworkers()
	{
		return workerservice.getAllworkers();
	}
	
	/*@PostMapping("/createworker")
	public Worker createWorker(@RequestBody Worker worker)
	{
		return workerservice.createWorker(worker);
		
	}*/
	
	
	@PostMapping("/createworker")
	public ResponseEntity<Worker> createWorker(@RequestBody Worker worker)
	{
		Optional<Worker> w=workerservice.getWorkerbyid(worker.getId());
		if (w.isPresent()) {
			throw new WorkerAlreadyFoundException();
		}
		else {
			return new ResponseEntity<Worker>(workerservice.createWorker(worker),HttpStatus.CREATED);
		}
	}
	
	
	@DeleteMapping("/delete")
	public void deleteWorker(Worker worker)
	{
		workerservice.delete(worker);
	}
	
	/*@DeleteMapping("/deletebyid/{id}")
	public void deletebyid(@PathVariable("id") int id)
	{
		workerservice.deletebyworkerID(id);
	}*/
	
	@DeleteMapping("/deletebyid/{id}")
	public void deletebyid(@PathVariable("id") int id)
	{
		Optional<Worker> worker=workerservice.getWorkerbyid(id);
		if (worker.isPresent()) {
			workerservice.deletebyworkerID(id);
		} else {

			throw new WorkerIdNotFoundException("Id not found");
		}
	
	}
	
	
	
	@PutMapping("/update/{id}")
	public void updateworkerdetails(@RequestBody Worker worker,@PathVariable ("id") int id)
	{
		//Worker workers=new Worker();
		Worker workers=workerservice.getWorkerbyid(id).orElseThrow(()->new WorkernotFoundException());
		workers.setId(worker.getId());
		workers.setName(worker.getName());
		workers.setExp(worker.getExp());
		workers.setRole(worker.getRole());
		workers.setSalary(worker.getSalary());
		workerservice.updateWorker(workers);
	}
	
	@GetMapping("/byname/{name}")
	public List<Worker> getByName(@PathVariable("name") String name)
	{
		return workerservice.findByName(name);
	}
	
	
	@GetMapping("/bynameandrole/{name}/{role}")
	public List<Worker> getbyNameandRole(@PathVariable("name") String name,@PathVariable("role") String role )
	{
		return workerservice.findByNameAndRole(name, role);
	}
	
	
	@GetMapping("/bycaseignore/{name}")
	public List<Worker> getByNameIgnoreCase(@PathVariable("name") String name)
	{
		return workerservice.findByNameIgnoreCase(name);
	}
	
	@GetMapping("/sortbyname")
	public List<Worker> sortByName()
	{
		return workerservice.sortByName();
	}
	
	@GetMapping("/{field}")
	public List<Worker> sortByEntity(@PathVariable("field") String field)
	{
		return workerservice.sortByEntityField(field);
	}
	
	
	@GetMapping("/pagination/{offset}/{pagesize}")
    public Page<Worker> workerWithPagination(@PathVariable("offset") int offset,@PathVariable("pagesize") int pagesize) {
		
		return workerservice.workerWithPagination(offset, pagesize);
	}
	
	@GetMapping("/highsalary")
	public long highsalary()
	{
		return workerservice.getAllworkers().stream().collect(Collectors.maxBy(Comparator.comparing(Worker::getSalary))).get().getSalary();
		
		
	}
	
	@GetMapping("/salray/highs")
	public List<Worker> salaryWith()
	{
		return workerservice.getAllworkers().stream().filter(sal->sal.getSalary()>30000).collect(Collectors.toList());
	}
	
	
	
	@GetMapping("/salary")
	public List<Worker> getBysalary()
	{
		return workerrepository.higgsalary();
	}
	
}
