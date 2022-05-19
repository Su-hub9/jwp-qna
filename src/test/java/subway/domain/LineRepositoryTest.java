package subway.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class LineRepositoryTest {
    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private StationRepository stationRepository;

    @Test
    void saveWithLine() {
        final Station station = new Station("잠실역");
        station.setLine(lineRepository.save(new Line("2호선")));
        final Station actual = stationRepository.save(station);
        stationRepository.flush();
    }

    @Test
    void findByNameWithLine() {
        final Station station = stationRepository.findByName("교대역");
        assertThat(station.getLine().getName()).isEqualTo("3호선");
    }

    @Test
    void updateWithLine() {
        final Station station = stationRepository.findByName("교대역");
        station.setLine(lineRepository.save(new Line("2호선")));
        stationRepository.flush();
    }

    @Test
    void removeLine() {
        final Station station = stationRepository.findByName("교대역");
        station.setLine(null);
        stationRepository.flush();
    }

    @Test
    void findByName() {
        final Line line = lineRepository.findByName("3호선");
        assertThat(line.getStations()).hasSize(1);
    }

    @Test
    void save() {
        final Line line = new Line("2호선");
        line.addStation(stationRepository.save(new Station("잠실역")));
        lineRepository.save(line);
        lineRepository.flush();
    }
}