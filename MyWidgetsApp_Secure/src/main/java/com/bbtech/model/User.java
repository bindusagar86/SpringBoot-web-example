package com.bbtech.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
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

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
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
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<Widget> widgets=new LinkedHashSet<>();
	
	protected void setInternalWidgets(Set<Widget> widgets) {
		this.widgets=widgets;
	}
	
	protected Set<Widget> getInternalWidgets(){
		if(this.widgets == null)
			this.widgets=new HashSet<>();
		return this.widgets;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setWidgets(Set<Widget> widgets) {
		this.widgets=widgets;
	}
	public List<Widget> getWidgets() {
		List<Widget> sortedWidget=new ArrayList<Widget>(getInternalWidgets());
		PropertyComparator.sort(sortedWidget, new MutableSortDefinition("createdDate", false, false));
		return Collections.unmodifiableList(sortedWidget);
	}
	
	public void addWidget(Widget widget) {
		getInternalWidgets().add(widget);
		widget.setUser(this);
		
	}
	
	
}
