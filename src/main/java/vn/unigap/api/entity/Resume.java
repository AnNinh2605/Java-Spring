package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "resume")
public class Resume {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "seeker_id")
    private Long seekerId;

    @Column(name = "career_obj")
    private String careerObj;

    @Column(name = "title")
    private String title;

    @Column(name = "slug")
    private String slug;

    @Column(name = "position")
    private Integer position;

    @Column(name = "position_current")
    private String positionCurrent;

    @Column(name = "wanting_job")
    private String wantingJob;

    @Column(name = "skill")
    private String skill;

    @Column(name = "interesting")
    private String interesting;

    @Column(name = "speial_skill")
    private String speialSkill;

    @Column(name = "level")
    private Integer level;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "computer_level")
    private String computerLevel;

    @Column(name = "language")
    private String language;

    @Column(name = "language_level")
    private String languageLevel;

    @Column(name = "current_salary")
    private String currentSalary;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "salary_min")
    private String salaryMin;

    @Column(name = "salary_unit")
    private String salaryUnit;

    @Column(name = "work_time")
    private Integer workTime;

    @Column(name = "fields")
    private String fields;

    @Column(name = "provinces")
    private String provinces;

    @Column(name = "total_views")
    private String totalViews;

    @Column(name = "is_ready_move")
    private String isReadyMove;

    @Column(name = "is_search_allowed")
    private String isSearchAllowed;

    @Column(name = "is_finished")
    private String isFinished;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "actived_at")
    private LocalDateTime activedAt;

    @Column(name = "feedback_at")
    private String feedbackAt;

    @Column(name = "feedback_content")
    private String feedbackContent;

    @Column(name = "feedback_reason")
    private String feedbackReason;

    @Column(name = "edit_status")
    private String editStatus;

    @Column(name = "c_resume_id")
    private String cResumeId;

    @Column(name = "requirement_job")
    private String requirementJob;

    @Column(name = "resume_kind")
    private Integer resumeKind;

    @Column(name = "resume_file")
    private String resumeFile;

    @Column(name = "status_yes")
    private String statusYes;

    @Column(name = "source")
    private String source;

    @Column(name = "data_search")
    private String dataSearch;

    @Column(name = "create_source")
    private Integer createSource;

    @Column(name = "user_delete")
    private String userDelete;

    @Column(name = "view_order")
    private String viewOrder;

    @Column(name = "employer_view")
    private String employerView;

    @Column(name = "updated_elastic_at")
    private String updatedElasticAt;

    @Column(name = "finished_date")
    private String finishedDate;

    @Column(name = "created_by_username")
    private String createdByUsername;

    @Column(name = "public_date")
    private String publicDate;
}
