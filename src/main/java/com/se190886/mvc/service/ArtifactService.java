package com.se190886.mvc.service;

import com.se190886.mvc.dto.ArtifactDto;
import com.se190886.mvc.dto.StatusOption;

import java.util.List;

public interface ArtifactService {

    List<StatusOption> getAllStatus();

    String getStatusDescription(String status);

    List<ArtifactDto> findAll();

    List<ArtifactDto> search(String keyword);

    boolean delete(Integer id);

    ArtifactDto findById(Integer id);

    void create(ArtifactDto artifactDto);
}
