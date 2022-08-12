package co.com.bancolombia.model.meetchannel.gateways;

import co.com.bancolombia.model.meetchannel.MeetChannelEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class MeetChannelRepositoryTest {

    @Mock
    MeetChannelRepository meetChannelRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMeetChannelAll() {
        MeetChannelEntity meetChannelEntity = MeetChannelEntity.builder()
                .id(2)
                .name("Elixir Colombia")
                .eventname("Migration")
                .about("")
                .location("")
                .members(0)
                .build();

        List<MeetChannelEntity> meetChannelEntityList = new ArrayList<>();
        meetChannelEntityList.add(meetChannelEntity);
        Flux<MeetChannelEntity> meetChannelEntityFlux = Flux.fromIterable(meetChannelEntityList);

        when(meetChannelRepository.getMeetChannelAll()).thenReturn(meetChannelEntityFlux);

        assertNotNull(meetChannelRepository.getMeetChannelAll());
        assertEquals("getMeetChannelAll", meetChannelEntityFlux, meetChannelRepository.getMeetChannelAll());
    }

    @Test
    void getMeetChannelById() {
        MeetChannelEntity meetChannelEntity = MeetChannelEntity.builder()
                .id(2)
                .name("Elixir Colombia")
                .eventname("Migration")
                .about("")
                .location("")
                .members(0)
                .build();

        List<MeetChannelEntity> meetChannelEntityList = new ArrayList<>();
        meetChannelEntityList.add(meetChannelEntity);
        Flux<MeetChannelEntity> meetChannelEntityFlux = Flux.fromIterable(meetChannelEntityList);

        when(meetChannelRepository.getMeetChannelById(2)).thenReturn(meetChannelEntityFlux);

        assertNotNull(meetChannelRepository.getMeetChannelById(2));
        assertEquals("getMeetChannelById", meetChannelEntityFlux, meetChannelRepository.getMeetChannelById(2));
    }

    @Test
    void createMeetChannel() {
        MeetChannelEntity meetChannelEntity = MeetChannelEntity.builder()
                .id(2)
                .name("Elixir Colombia")
                .eventname("Migration")
                .about("")
                .location("")
                .members(0)
                .build();

        Mono<MeetChannelEntity> meetChannelEntityFlux = Mono.just(meetChannelEntity);

        when(meetChannelRepository.createMeetChannel(meetChannelEntity)).thenReturn(meetChannelEntityFlux);

        assertNotNull(meetChannelRepository.createMeetChannel(meetChannelEntity));
        assertEquals("createMeetChannel", meetChannelEntityFlux, meetChannelRepository.createMeetChannel(meetChannelEntity));


    }
}