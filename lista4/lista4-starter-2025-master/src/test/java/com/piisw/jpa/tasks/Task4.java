package com.piisw.jpa.tasks;

import com.piisw.jpa.repositories.EventRepository;
import com.piisw.jpa.repositories.ServerStatistic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@DataJpaTest
class Task4 {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void shouldCountEventsByServer() throws Exception {
        // given ensured by script
        long expectedServer_1 = 15;
        long expectedServer_2 = 14;
        long expectedServer_3 = 11;

        // when
        //List<ServerStatistic> result = new ArrayList<>();// replace by repository method call
        List<ServerStatistic> result = eventRepository.countEventsByServer();


 
        // then

        assertThat(result, hasSize(3));

        
        Map<Long, Long> map = result.stream()
                .collect(Collectors.toMap(stat -> stat.getServer().getId(), ServerStatistic::getCount)); // Fix method references
                
        assertThat(map.get(1L), is(expectedServer_1));
        assertThat(map.get(2L), is(expectedServer_2));
        assertThat(map.get(3L), is(expectedServer_3));
    }

}
