package com.piisw.jpa.tasks;

import com.piisw.jpa.entities.Event;
import com.piisw.jpa.entities.Follower;
import com.piisw.jpa.entities.Comment;
import com.piisw.jpa.entities.RequestEvent; 
import com.piisw.jpa.services.ServerService;
import com.piisw.jpa.repositories.EventRepository;
import com.piisw.jpa.repositories.ServerRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;

@DataJpaTest
class Task6 {

    @MockBean
    private ServerService serverService;  

    @MockBean
    private ServerRepository serverRepositoryMock;

    @MockBean
    private EventRepository eventRepositoryMock;

    @Test
    void shouldReturnEventDetailsForFollower() {
        // given
        String userId = "user123"; 
        
        
        RequestEvent event = new RequestEvent();  

        event.setTime(java.time.LocalDateTime.now());
        event.setAnalysisRequired(false);

        
        Follower follower = new Follower();
        follower.setUserId(userId);
        follower.setSubscriptionDate(LocalDateTime.now());
        event.setFollowers(List.of(follower));

        Comment comment = new Comment();
        comment.setContent("Sample Comment");
        event.setComments(List.of(comment));

        
        when(eventRepositoryMock.findByFollowers_UserId(userId)).thenReturn(List.of(event));

 
        when(serverService.getEventsForFollower(userId)).thenReturn(List.of(event));

        // when
        List<Event> events = serverService.getEventsForFollower(userId);

        // then
        assertThat(events, Matchers.hasSize(1)); 
        Event fetchedEvent = events.get(0);
        assertThat(fetchedEvent.getTime(), Matchers.notNullValue());
        assertThat(fetchedEvent.isAnalysisRequired(), Matchers.is(false)); 
    }
}
