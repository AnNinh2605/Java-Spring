package vn.unigap.api.repository;

import org.springframework.beans.factory.annotation.Value;

public interface GetEmployerByIdProjection {
    Long getId();
    String getEmail();
    String getName();
    @Value("#{target.province}")
    String getProvinceId();
    String getDescription();
}
