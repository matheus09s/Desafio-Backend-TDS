package tds.company.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tds.company.api.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
}
