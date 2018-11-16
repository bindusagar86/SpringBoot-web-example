package com.bbtech.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	
	@NotEmpty
	@Column(name="firstname")
	private String firstName;
	
	@NotEmpty
	@Column(name="lastname")
	private String lastName;
	
	@NotEmpty @Email
	private String email;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	
	@NotEmpty @NotNull
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(
			name="users_widgets",
			joinColumns=@JoinColumn(
					name="user_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(
					name="widget_id", referencedColumnName="id")
	)
	private Set<Widget> widgets;
	
}
