package com.se190886.mvc.repository;

import com.se190886.mvc.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtifactRepository extends JpaRepository<Artifact, Integer> {
    List<Artifact> findAllByOrderByAcquisitionDateDesc();

    List<Artifact> findAllByArtifactNameContainingIgnoreCaseOrderByAcquisitionDateAsc(String keyword);
}
