package com.myscaler.urlshortner.repositories;

import com.myscaler.urlshortner.models.UrlAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlAccessLogRepository extends JpaRepository<UrlAccessLog, Long> {
}
