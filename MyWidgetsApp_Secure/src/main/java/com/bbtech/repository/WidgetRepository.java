package com.bbtech.repository;

import org.springframework.data.repository.CrudRepository;

import com.bbtech.model.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Long> {

}
