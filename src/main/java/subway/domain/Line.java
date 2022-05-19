package subway.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "line")
    private List<Station> stations = new ArrayList<>();

    protected Line() {
    }

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void addStation(final Station station) {
        this.stations.add(station);
        station.setLine(this);
    }
}
