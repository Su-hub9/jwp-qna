package subway.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany // (1) 일대다(1:N) 관계라는 매핑 정보
    @JoinColumn(name = "member_id") // (2) favorite 의 외래 키
    private List<Favorite> favorites = new ArrayList<>(); // (3) 사용자와 즐겨찾기는 단방향 관계다.
}
