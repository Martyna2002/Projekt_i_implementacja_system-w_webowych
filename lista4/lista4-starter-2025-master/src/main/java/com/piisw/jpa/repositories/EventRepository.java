package com.piisw.jpa.repositories;

import com.piisw.jpa.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;


import java.util.List;
import java.time.LocalDateTime;





public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findByTimeBetweenAndAnalysisRequired(
        LocalDateTime start,
        LocalDateTime end,
        boolean analysisRequired,
        Pageable pageable
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Event e WHERE e.time < :time")
    void deleteAllByTimeBefore(@Param("time") LocalDateTime time);

    @Query("SELECT new com.piisw.jpa.repositories.ServerStatistic(e.server, COUNT(e)) " +
           "FROM Event e " +
           "GROUP BY e.server")
    List<ServerStatistic> countEventsByServer();

    @EntityGraph(value = "event-with-comments-followers", type = EntityGraph.EntityGraphType.FETCH)
    List<Event> findByFollowers_UserId(String userId);


}
