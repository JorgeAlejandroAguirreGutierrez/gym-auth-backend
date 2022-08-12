package co.com.bancolombia.repository;

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

class MeetChannelEventR2dbcRepositoryTest {

    @Mock
    MeetChannelEventR2dbcRepository meetChannelRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMeetChannelEventAll() {
        MeetChannelEventEntity meetChannelEvent = MeetChannelEventEntity.builder()
                .id(1)
                .meetchannelid(1)
                .meetschannelname("meetchannelname")
                .name("name")
                .detail("detail")
                .date("date")
                .urlyoutube("urlyoutube")
                .urltwitch("urltwitch")
                .assistants(1)
                .state("state")
                .build();

        List<MeetChannelEventEntity> meetChannelEventList = new ArrayList<>();
        meetChannelEventList.add(meetChannelEvent);
        Flux<MeetChannelEventEntity> meetChannelEventFlux = meetChannelRepository.getMeetChannelEventAll();

        when(meetChannelRepository.getMeetChannelEventAll()).thenReturn(Flux.just(meetChannelEvent));

        assertNotNull(meetChannelRepository.getMeetChannelEventAll());
    }

    @Test
    void getMeetChannelEventById() {

        MeetChannelEventEntity meetChannelEntity = MeetChannelEventEntity.builder()
                .messageError("")
                .id(1)
                .meetchannelid(1)
                .meetschannelname("meetchannelname")
                .name("name")
                .detail("detail")
                .date("date")
                .urlyoutube("urlyoutube")
                .urltwitch("urltwitch")
                .assistants(1)
                .state("state")
                .build();

        List<MeetChannelEventEntity> lstEntity = new ArrayList<MeetChannelEventEntity>();
        lstEntity.add(meetChannelEntity);
        Flux<MeetChannelEventEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(meetChannelRepository.getMeetChannelEventById(1)).thenReturn(entityFlux);

        assertNotNull(meetChannelRepository.getMeetChannelEventById(1));
    }

    @Test
    void createMeetChannelEvent() {

            MeetChannelEventEntity meetChannelEntity = MeetChannelEventEntity.builder()
                    .messageError("")
                    .id(1)
                    .meetchannelid(1)
                    .meetschannelname("meetchannelname")
                    .name("name")
                    .detail("detail")
                    .date("date")
                    .urlyoutube("urlyoutube")
                    .urltwitch("urltwitch")
                    .assistants(1)
                    .state("state")
                    .build();

            Mono<MeetChannelEventEntity> entityMono = Mono.just(meetChannelEntity);

            when(meetChannelRepository.createMeetChannelEvent(1, 1, "name", "detail", "date", "urlyoutube", "urltwitch", 1, 1)).thenReturn(entityMono);
            assertNotNull(meetChannelRepository.createMeetChannelEvent(1, 1, "name", "detail", "date", "urlyoutube", "urltwitch", 1, 1));
    }

}