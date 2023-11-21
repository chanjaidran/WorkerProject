package com.worker.workerprojects.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "worker_table")
@Data
@AllArgsConstructor
@ToString
public class Worker 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	int exp;
	String role;
	long salary;
	public Worker() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
	
		

}
