package com.app.lms.entity;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String managerName;
	private String email;
	@JsonIgnore
	@OneToMany(mappedBy = "manager",cascade = CascadeType.PERSIST, orphanRemoval = false)
	//@OnDelete(action = OnDeleteAction.SET_NULL)
	private List<Employee>employee;
}
