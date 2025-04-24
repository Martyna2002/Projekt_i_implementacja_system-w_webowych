package com.piisw.jpa.repositories;

import com.piisw.jpa.entities.Event;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface EventBaseRepository<T extends Event> extends JpaRepository<T, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.analysisRequired = true WHERE e.duration > :minDuration")
    int updateAnalysisRequiredForEvents(@Param("minDuration") int minDuration);
}
