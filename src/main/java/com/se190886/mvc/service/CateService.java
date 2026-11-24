package com.se190886.mvc.service;

import com.se190886.mvc.entity.Category;

import java.util.List;

public interface CateService {
    List<Category> findAll();
    Category findById(Integer id);
}
