package co.com.bancolombia.model.meetchannelevent.gateways;

import co.com.bancolombia.model.meetchannelevent.MeetChannelEventEntity;
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

class MeetChannelEventRepositoryTest {

    @Mock
    MeetChannelEventRepository meetChannelEventRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMeetChannelEventAll() {
        MeetChannelEventEntity meetChannelEventEntity = MeetChannelEventEntity.builder()
                .id(2)
                .meetchannelid(2)
                .meetschannelname("Elixir Colombia")
                .name("Migration")
                .detail("Detail of the event")
                .date("Data of the event")
                .urlyoutube("url of the event")
                .urltwitch("url twitch of the event")
                .assistants(120)
                .state("state of the event")
                .build();

        List<MeetChannelEventEntity> meetChannelEventEntityList = new ArrayList<>();
        meetChannelEventEntityList.add(meetChannelEventEntity);
        Flux<MeetChannelEventEntity> meetChannelEventEntityFlux = Flux.fromIterable(meetChannelEventEntityList);

        when(meetChannelEventRepository.getMeetChannelEventAll()).thenReturn(meetChannelEventEntityFlux);

        assertNotNull(meetChannelEventRepository.getMeetChannelEventAll());
        assertEquals("MeetChannelEventEntity", meetChannelEventEntityFlux, meetChannelEventRepository.getMeetChannelEventAll());
    }

    @Test
    void getMeetChannelEventById() {
        MeetChannelEventEntity meetChannelEntity = MeetChannelEventEntity.builder()
                .id(2)
                .meetchannelid(2)
                .meetschannelname("Elixir Colombia")
                .name("Migration")
                .detail("Detail of the event")
                .date("Data of the event")
                .urlyoutube("url of the event")
                .urltwitch("url twitch of the event")
                .assistants(120)
                .state("state of the event")
                .build();


        List<MeetChannelEventEntity> lstEntity = new ArrayList<>();
        lstEntity.add(meetChannelEntity);
        Flux<MeetChannelEventEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(meetChannelEventRepository.getMeetChannelEventById(2)).thenReturn(entityFlux);

        assertNotNull(meetChannelEventRepository.getMeetChannelEventById(2));
        assertEquals("MeetChannelEventEntity", entityFlux, meetChannelEventRepository.getMeetChannelEventById(2));
    }

    @Test
    void createMeetChannelEvent() {
        MeetChannelEventEntity meetChannelEntity = MeetChannelEventEntity.builder()
                .id(2)
                .meetchannelid(2)
                .meetschannelname("Elixir Colombia")
                .name("Migration")
                .detail("Detail of the event")
                .date("Data of the event")
                .urlyoutube("url of the event")
                .urltwitch("url twitch of the event")
                .assistants(120)
                .state("state of the event")
                .build();

        Mono<MeetChannelEventEntity> entityFlux = Mono.just(meetChannelEntity);

        when(meetChannelEventRepository.createMeetChannelEvent(meetChannelEntity)).thenReturn(entityFlux);

        assertNotNull(meetChannelEventRepository.createMeetChannelEvent(meetChannelEntity));
        assertEquals("MeetChannelEventEntity", entityFlux, meetChannelEventRepository.createMeetChannelEvent(meetChannelEntity));
    }
}