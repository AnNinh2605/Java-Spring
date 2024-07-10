package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "jobs")
public class Jobs {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "employer_id")
    private Long employerId;

    @Column(name = "title")
    private String title;

    @Column(name = "slug")
    private String slug;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "gender")
    private String gender;

    @Column(name = "description")
    private String description;

    @Column(name = "skill")
    private String skill;

    @Column(name = "attribute")
    private Integer attribute;

    @Column(name = "level")
    private Integer level;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "salary_unit")
    private String salaryUnit;

    @Column(name = "work_time")
    private Integer workTime;

    @Column(name = "probation_time")
    private Integer probationTime;

    @Column(name = "benefit")
    private String benefit;

    @Column(name = "resume_requirements")
    private String resumeRequirements;

    @Column(name = "fields")
    private String fields;

    @Column(name = "provinces")
    private String provinces;

    @Column(name = "total_views")
    private String totalViews;

    @Column(name = "total_applied")
    private String totalApplied;

    @Column(name = "is_premium")
    private String isPremium;

    @Column(name = "is_search_allowed")
    private String isSearchAllowed;

    @Column(name = "status")
    private String status;

    @Column(name = "tags")
    private String tags;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_address")
    private String contactAddress;
}
