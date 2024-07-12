package vn.unigap.api.dto.out;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.repository.EmployerProjection;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginationResponse {
    int page;
    int pageSize;
    long totalElements;
    long totalPages;
    List<EmployerProjection> data;
}
