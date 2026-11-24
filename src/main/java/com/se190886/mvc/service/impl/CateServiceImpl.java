package com.se190886.mvc.service.impl;

import com.se190886.mvc.entity.Category;
import com.se190886.mvc.repository.CateRepository;
import com.se190886.mvc.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CateServiceImpl implements CateService {
    @Autowired
    private CateRepository cateRepository;

    @Override
    public List<Category> findAll() {
        return cateRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return cateRepository.findById(id).orElse(null);
    }
}
