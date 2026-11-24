package com.se190886.mvc.dto;

import com.se190886.mvc.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ArtifactDto {

    private Integer id;

    @NotBlank(message = "Artifact code is required.")
    @Pattern(regexp = "^[A-Z]{3}\\d{3}$", message = "Must start with 3 uppercase letters followed by 3 digits (e.g., ART101).")
    private String artifactCode;

    @NotBlank(message = "Artifact name is required.")
    @Size(max = 200, message = "Max length 200.")
    @Pattern(regexp = "^\\S(.*\\S)?$", message = "No space at both sides.")
    private String artifactName;

    private Integer cateId;

    private Category category;

    @NotNull(message = "Estimated Value is required.")
    @DecimalMin(value = "0.01", message = "Price must be a number greater than 0.")
    private BigDecimal estimatedValue;

    @NotNull(message = "Age is required.")
    @Min(value = 1, message = "Must be a positive integer.")
    private Integer ageYears;

    @NotNull(message = "Acquisition date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate acquisitionDate;


    private String status;

    private String statusDes;


    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public ArtifactDto() {
    }

    public ArtifactDto(Integer id, String artifactCode, String artifactName, Integer cateId, Category category, BigDecimal estimatedValue, Integer ageYears, LocalDate acquisitionDate, String status, String statusDes) {
        this.id = id;
        this.artifactCode = artifactCode;
        this.artifactName = artifactName;
        this.cateId = cateId;
        this.category = category;
        this.estimatedValue = estimatedValue;
        this.ageYears = ageYears;
        this.acquisitionDate = acquisitionDate;
        this.status = status;
        this.statusDes = statusDes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArtifactCode() {
        return artifactCode;
    }

    public void setArtifactCode(String artifactCode) {
        this.artifactCode = artifactCode;
    }

    public String getArtifactName() {
        return artifactName;
    }

    public void setArtifactName(String artifactName) {
        this.artifactName = artifactName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public Integer getAgeYears() {
        return ageYears;
    }

    public void setAgeYears(Integer ageYears) {
        this.ageYears = ageYears;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }
}
