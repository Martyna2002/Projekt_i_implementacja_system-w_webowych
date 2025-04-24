package com.piisw.jpa.tasks;

import com.piisw.jpa.entities.Server;
import com.piisw.jpa.repositories.ServerRepository;
import com.piisw.jpa.repositories.EventRepository;
import com.piisw.jpa.services.ServerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
class Task5 {

    @MockBean 
    private ServerRepository serverRepositoryMock;

    @MockBean 
    private EventRepository eventRepositoryMock;

    @Autowired
    private ServerService serverService; 

    @Test
    void shouldReturnMockServer() throws Exception {
        // given
        String serverName = "dummyName";
        String mockServerName = "Alex";
        String mockServerIp = "noIp";
        Server dummyServer = new Server(mockServerName, mockServerIp);
        whenSerachingForNameReturn(serverName, dummyServer);

        // when
        Optional<Server> result = serverService.findByName(serverName);

        // then
        assertThat(result.isPresent(), Matchers.is(true));
        assertThat(result.get().getName(), Matchers.is(mockServerName));
        assertThat(result.get().getIp(), Matchers.is(mockServerIp));
    }

    private void whenSerachingForNameReturn(String serverName, Server dummyServer) {
        
        when(serverRepositoryMock.findByNameAndIsActiveTrue(serverName)).thenReturn(Optional.of(dummyServer));
    }

    @TestConfiguration
    static class ServerServiceTestConfig {

        @Bean
        public ServerService serverService(ServerRepository serverRepository, EventRepository eventRepository) {
            return new ServerService(serverRepository, eventRepository);
        }
    }
}
