package com.se190886.mvc.repository;

import com.se190886.mvc.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CateRepository extends JpaRepository<Category, Integer> {

}
