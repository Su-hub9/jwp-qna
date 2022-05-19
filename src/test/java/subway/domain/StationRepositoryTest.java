package subway.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class StationRepositoryTest {
    @Autowired
    private StationRepository stationRepository;

    @Test
    void save() {
        final Station expected = new Station("잠실역");
        final Station actual = stationRepository.save(expected);
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getName()).isEqualTo("잠실역");
    }

    @Test
    void findByName() {
        stationRepository.save(new Station("잠실역"));
        final Station actual = stationRepository.findByName("잠실역");
        assertThat(actual).isNotNull();
    }

    @Test
    void identity() {
        final Station station1 = stationRepository.save(new Station("잠실역"));
        final Station station2 = stationRepository.findByName("잠실역");
        assertThat(station1 == station2).isTrue();
        assertThat(station1).isSameAs(station2);
    }

    @Test
    void update() {
        final Station station1 = stationRepository.save(new Station("잠실역"));
        station1.changeName("몽촌토성역");
        final Station station2 = stationRepository.findByName("몽촌토성역");
        assertThat(station2).isNotNull();
    }

    @Test
    void update2() {
        final Station station1 = stationRepository.save(new Station("잠실역"));
        station1.changeName("몽촌토성역");
        final Optional<Station> station2 = stationRepository.findById(station1.getId());
        assertThat(station2.get().getName()).isEqualTo("몽촌토성역");
    }
}