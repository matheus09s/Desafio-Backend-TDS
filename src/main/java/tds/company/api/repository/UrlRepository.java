package tds.company.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tds.company.api.entity.UrlEntity;
@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, String> {
}
