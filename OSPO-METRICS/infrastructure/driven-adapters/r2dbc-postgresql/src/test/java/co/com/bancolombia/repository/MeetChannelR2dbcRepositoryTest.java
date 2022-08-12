package co.com.bancolombia.repository;

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

class MeetChannelR2dbcRepositoryTest {

    @Mock
    MeetChannelR2dbcRepository meetChannelRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMeetChannelAll() {
        MeetChannelEntity meetChannel = MeetChannelEntity.builder()
                .messageError("")
                .id(1)
                .name("name")
                .eventname("eventname")
                .about("about")
                .location("location")
                .members(152)
                .build();

        List<MeetChannelEntity> meetChannelList = new ArrayList<>();
        meetChannelList.add(meetChannel);
        Flux<MeetChannelEntity> meetChannelFlux = meetChannelRepository.getMeetChannelAll();

        when(meetChannelRepository.getMeetChannelAll()).thenReturn(Flux.just(meetChannel));

        assertNotNull(meetChannelRepository.getMeetChannelAll());
    }

    @Test
    void getMeetChannelById() {

            MeetChannelEntity meetChannel = MeetChannelEntity.builder()
                    .messageError("")
                    .id(1)
                    .name("name")
                    .eventname("eventname")
                    .about("about")
                    .location("location")
                    .members(152)
                    .build();

            List<MeetChannelEntity> meetChannelList = new ArrayList<>();
            meetChannelList.add(meetChannel);
            Flux<MeetChannelEntity> meetChannelFlux = Flux.fromIterable(meetChannelList);

            when(meetChannelRepository.getMeetChannelById(1)).thenReturn(meetChannelFlux);
            assertNotNull(meetChannelRepository.getMeetChannelById(1));
    }

    @Test
    void createMeetChannel() {
        MeetChannelEntity meetChannel = MeetChannelEntity.builder()
                .messageError("")
                .id(1)
                .name("name")
                .eventname("eventname")
                .about("about")
                .location("location")
                .members(152)
                .build();

        Mono<MeetChannelEntity> meetChannelFlux = Mono.just(meetChannel);

        when(meetChannelRepository.createMeetChannel(1, "name", "eventname", "about", "location", 152L)).thenReturn(meetChannelFlux);
        assertNotNull(meetChannelRepository.createMeetChannel(1, "name", "eventname", "about", "location", 152L));
    }
}