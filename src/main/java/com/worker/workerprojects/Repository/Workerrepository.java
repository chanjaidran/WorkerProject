package com.worker.workerprojects.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.worker.workerprojects.Entity.Worker;

@Repository
public interface Workerrepository extends JpaRepository<Worker,Integer>
{
	List<Worker> findByName(String name);
	
	List<Worker> findByNameAndRole(String name,String role);
	
	List<Worker> findByNameIgnoreCase(String name);
	
	
	/*@Query("select u from users u order by u.age")
	List<Users> sortByAge();
	
	@Query(value = "select * from users u order by u.profession desc",nativeQuery = true)
	List<Users> sortByAgeDesc();*/
	
	@Query(value = "select * from worker_table w order by w.name",nativeQuery = true)
	List<Worker> sortByName();
	
	@Query(value = "select * from worker_table where salary>30000 limit 3 ",nativeQuery = true)
	List<Worker> higgsalary();
	
}
