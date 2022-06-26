package com.kakaopay.pointsearchapi;

import com.kakaopay.pointsearchapi.api.dto.ActionHistoryDTO;
import com.kakaopay.pointsearchapi.api.dto.SearchRequestDTO;
import com.kakaopay.pointsearchapi.api.repo.ActionLogRepository;
import com.kakaopay.pointsearchapi.api.vo.ActionLogVo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class PointSearchRepoTests {

    @Autowired
    private ActionLogRepository actionLogRepository;

    @Test
    @DisplayName("포인트 내역 정보 범위 조회가 잘 되는지 확인")
    public void searchHistory() {
        // given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        SearchRequestDTO requestDTO = new SearchRequestDTO();
        requestDTO.setBarcode("7788340850");
        requestDTO.setAccessType("history");
        requestDTO.setStartedAt(LocalDateTime.parse("2022-06-24T10:20:33",formatter));
        requestDTO.setEndedAt(LocalDateTime.parse("2022-06-26T20:00:00",formatter));
        // when
        List<ActionLogVo> logVo = actionLogRepository.findAllByBarcodeAndApprovedAtBetween(requestDTO.getBarcode(), requestDTO.getStartedAt(), requestDTO.getEndedAt());
        // then
        Assertions.assertThat(logVo).isNotNull();
        Assertions.assertThat(logVo).isInstanceOf(List.class);
    }

}
