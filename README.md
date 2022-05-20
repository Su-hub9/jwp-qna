## JPA

### 1단계 - 엔티티 매핑
#### 요구사항
* 아래의 DDL(Data Definition Language)을 보고 유추하여 엔티티 클래스와 리포지토리 클래스 작성
* @DataJpaTest를 사용하여 학습 테스트
```roomsql
create table answer
(
    id          bigint generated by default as identity,
    contents    clob,
    created_at  timestamp not null,
    deleted     boolean   not null,
    question_id bigint,
    updated_at  timestamp,
    writer_id   bigint,
    primary key (id)
)
```
```roomsql
create table delete_history
(
    id            bigint generated by default as identity,
    content_id    bigint,
    content_type  varchar(255),
    create_date   timestamp,
    deleted_by_id bigint,
    primary key (id)
)
```
```roomsql
create table question
(
    id         bigint generated by default as identity,
    contents   clob,
    created_at timestamp    not null,
    deleted    boolean      not null,
    title      varchar(100) not null,
    updated_at timestamp,
    writer_id  bigint,
    primary key (id)
)
```
```roomsql
create table user
(
    id         bigint generated by default as identity,
    created_at timestamp   not null,
    email      varchar(50),
    name       varchar(20) not null,
    password   varchar(20) not null,
    updated_at timestamp,
    user_id    varchar(20) not null,
    primary key (id)
)

alter table user
    add constraint UK_a3imlf41l37utmxiquukk8ajc unique (user_id)
```

#### 구현 기능
- [x] 엔티티 매핑
  - [x] Answer
  - [x] DeleteHistory
  - [x] Question
  - [x] User
- [x] 중복되는 속성을 분리하는 BaseEntity 생성
- [x] 도메인 테스트 작성
  - [x] Answer
  - [x] Question
  - [x] User
- [x] 리포지토리 테스트 작성
  - [x] Answer
  - [x] DeleteHistory
  - [x] Question
  - [x] User

---

### 2단계 - 연관 관계 매핑
#### 요구사항
* 객체의 참조와 테이블의 외래 키를 매핑해서 객체에서는 참조를 사용하고 테이블에서는 외래 키를 사용할 수 있도록 한다.
* 아래의 DDL을 보고 유추한다.
```roomsql
alter table answer
    add constraint fk_answer_to_question
        foreign key (question_id)
            references question

alter table answer
    add constraint fk_answer_writer
        foreign key (writer_id)
            references user

alter table delete_history
    add constraint fk_delete_history_to_user
        foreign key (deleted_by_id)
            references user

alter table question
    add constraint fk_question_writer
        foreign key (writer_id)
            references user
```

#### 구현 기능
- [x] 연관 관계 매핑
  - [x] Answer - Question
  - [x] Answer - User
  - [x] DeleteHistory - User
  - [x] Question - User
- [x] 리포지토리 테스트 작성
  - [x] Answer - Question
  - [x] Answer - User
  - [x] DeleteHistory - User
  - [x] Question - User
- [x] 도메인 테스트 작성
  - [x] Answer - Question
  - [x] Answer - User
  - [x] DeleteHistory - User
  - [x] Question - User