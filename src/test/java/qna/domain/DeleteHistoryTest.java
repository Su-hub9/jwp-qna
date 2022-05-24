package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoryTest {
    @DisplayName("DeleteHistory 도메인 생성")
    @Test
    void test_new() {
        //given & when
        DeleteHistory deleteHistory = DeleteHistory.ofQuestion(1L, UserTest.JAVAJIGI);
        //then
        assertThat(deleteHistory).isNotNull();
    }
}