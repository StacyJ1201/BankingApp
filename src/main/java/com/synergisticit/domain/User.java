package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
public class User{
	
	@NotNull
	@Id   
	private Long userId;
	
	private String username;
	
	
	private String password;
	
	@Email
	private String email;

	@JsonBackReference
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
		joinColumns= {@JoinColumn(name="user_id")},
		inverseJoinColumns= {@JoinColumn(name="role_id")}
	)
	List<Role> roles = new ArrayList<>();

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private Customer customer;
}
