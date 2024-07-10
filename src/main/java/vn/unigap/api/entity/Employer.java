package vn.unigap.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "employer")
public class Employer {
    @Id
    @Column
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "address")
    private String address;

    @Column(name = "province")
    private Integer province;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "is_map_shown")
    private Integer isMapShown;

    @Column(name = "scale")
    private Integer scale;

    @Column(name = "identification")
    private String identification;

    @Column(name = "logo")
    private String logo;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "logined_at")
    private LocalDateTime loginedAt;

    @Column(name = "logined_ip")
    private String loginedIp;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_address")
    private String contactAddress;

    @Column(name = "contact_type")
    private String contactType;

    @Column(name = "is_premium")
    private Integer isPremium;

    @Column(name = "is_newsletter_registered")
    private Integer isNewsletterRegistered;

    @Column(name = "is_mail_notify_received")
    private Integer isMailNotifyReceived;

    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "meta_description")
    private String metaDescription;

    @Column(name = "meta_keywords")
    private String metaKeywords;

    @Column(name = "canonical_url")
    private String canonicalUrl;

    @Column(name = "number_of_employer")
    private String numberOfEmployer;

    @Column(name = "old_age")
    private String oldAge;

    @Column(name = "founded_year")
    private String foundedYear;

    @Column(name = "is_view_resume")
    private String isViewResume;

    @Column(name = "data_search")
    private String dataSearch;
}
