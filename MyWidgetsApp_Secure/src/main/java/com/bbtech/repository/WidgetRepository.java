package com.bbtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbtech.model.Widget;

public interface WidgetRepository extends JpaRepository<Widget, Long> {

}
