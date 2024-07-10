package vn.unigap.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vn.unigap.api.entity.Employer;

@Repository
public interface EmployerRepositoryPage extends PagingAndSortingRepository<Employer, Long> {
    Page<EmployerProjection> findAllEmployerByOrderByName(Pageable pageable);
}
