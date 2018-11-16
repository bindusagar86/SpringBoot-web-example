package com.bbtech.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
	

}
