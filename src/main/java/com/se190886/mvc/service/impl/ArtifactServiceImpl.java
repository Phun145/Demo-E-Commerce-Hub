package com.se190886.mvc.service.impl;

import com.se190886.mvc.dto.ArtifactDto;
import com.se190886.mvc.dto.StatusOption;
import com.se190886.mvc.entity.Artifact;
import com.se190886.mvc.entity.Category;
import com.se190886.mvc.repository.ArtifactRepository;
import com.se190886.mvc.repository.CateRepository;
import com.se190886.mvc.service.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtifactServiceImpl implements ArtifactService {
    @Autowired
    private ArtifactRepository artifactRepository;
    @Autowired
    private CateRepository cateRepository;

    @Override
    public List<StatusOption> getAllStatus() {
        return List.of(
                new StatusOption("AC", "Active"),
                new StatusOption("IN", "Inactive"),
                new StatusOption("DR", "Draft")
        );
    }

    @Override
    public String getStatusDescription(String status) {
        if (status == null) return "";
        List<StatusOption> list = getAllStatus();

        for (StatusOption statusDes : list) {
            if (statusDes.status().equalsIgnoreCase(status)) {
                return statusDes.statusDes();
            }
        }
        return status;
    }

    @Override
    public List<ArtifactDto> findAll() {
        return artifactRepository.findAllByOrderByAcquisitionDateDesc()
                .stream().map(x -> toArtifactDto(x)).toList();
    }

    @Override
    public List<ArtifactDto> search(String keyword) {
        if(keyword == null || keyword.isEmpty())
            return findAll();

        return artifactRepository.findAllByArtifactNameContainingIgnoreCaseOrderByAcquisitionDateAsc(keyword)
                .stream().map(x -> toArtifactDto(x)).toList();
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        Artifact  artifact= artifactRepository.findById(id).orElse(null);
        if (artifact == null) {
            return false;
        }

        if ("AC".equalsIgnoreCase(artifact.getStatus())) {
            return false;
        }

        artifactRepository.delete(artifact);
        return true;
    }

    @Override
    public ArtifactDto findById(Integer id) {
        Artifact artifact = artifactRepository.findById(id).orElse(null);
        if (artifact == null) {return null;}
        return toArtifactDto(artifact);
    }

    @Override
    @Transactional
    public void create(ArtifactDto artifactDto) {
        Artifact artifact = new Artifact();

        artifact.setArtifactCode(artifactDto.getArtifactCode());
        artifact.setArtifactName(artifactDto.getArtifactName());
        if (artifactDto.getCateId()!= null) {
            Category category = cateRepository.findById(artifactDto.getCateId()).orElse(null);
            artifact.setCategory(category);
        } else if (artifactDto.getCategory() != null) {
            artifact.setCategory(artifactDto.getCategory());
        }
        artifact.setEstimatedValue(artifactDto.getEstimatedValue());
        artifact.setAgeYears(artifactDto.getAgeYears());
        artifact.setAcquisitionDate(artifactDto.getAcquisitionDate());
        artifact.setStatus(artifactDto.getStatus());

        artifactRepository.save(artifact);
    }


    private ArtifactDto toArtifactDto(Artifact artifact){
        ArtifactDto artifactDto = new ArtifactDto();

        artifactDto.setId(artifact.getId());
        artifactDto.setArtifactCode(artifact.getArtifactCode());
        artifactDto.setArtifactName(artifact.getArtifactName());
        artifactDto.setCateId(artifact.getCategory().getId());
        artifactDto.setCategory(artifact.getCategory());
        artifactDto.setEstimatedValue(artifact.getEstimatedValue());
        artifactDto.setAgeYears(artifact.getAgeYears());
        artifactDto.setAcquisitionDate(artifact.getAcquisitionDate());
        artifactDto.setStatus(artifact.getStatus());
        artifactDto.setStatusDes(getStatusDescription(artifact.getStatus()));

        return artifactDto;
    }
}
