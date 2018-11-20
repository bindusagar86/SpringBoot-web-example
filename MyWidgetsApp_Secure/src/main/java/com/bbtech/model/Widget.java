package com.bbtech.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Widget {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@NotEmpty
	private String name;
	@NotEmpty
	private String description;
	
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createdDate=LocalDateTime.now();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Widget [id=" + id + ", name=" + name + ", description=" + description + ", createdDate=" + createdDate
				+ ", user=" + user + "]";
	}

	
}
