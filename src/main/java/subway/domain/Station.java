package subway.domain;

import javax.persistence.*;

@Entity // (1) 엔티티 클래스임을 지정하며 테이블과 매핑된다.
@Table(name = "station") // (2) 엔티티가 매핑될 테이블을 지정하고 생략 시 엔티티 클래스 이름과 같은 테이블로 매핑된다.
public class Station {
    @Id // (3) 직접 매핑해서 사용하는 경우
    @GeneratedValue(strategy = GenerationType.IDENTITY) // (4) PK의 생성 규칙을 나타낸다.
    private Long id;

    @Column(name = "name", nullable = false) // (5) 컬럼의 이름을 이용하여 지정된 필드나 속성을 테이블의 컬럼에 매핑한다.
    private String name;

    @ManyToOne
//    @JoinColumn(name = "line_id")
    private Line line;

    // The entity class must have a no-arg constructor. The entity class may have other constructors as well. - JSR 338
    protected Station() { // (6) 매개 변수가 없는 생성자
    }

    public Station(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void setLine(Line line) {
        this.line = line;
        line.getStations().add(this);
    }

    public Line getLine() {
        return this.line;
    }
}
