package com.dr.mapper.rank;

import com.dr.dto.rank.RankDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RankMapperTest {

    @Autowired
    private RankMapper rankMapper;

    @BeforeEach
    void setUp() {

    }


    @Test
    void testFiftyRankList() {
        List<RankDTO> ranks = rankMapper.fiftyRankList();
        assertNotNull(ranks);  // 반환된 리스트가 null이 아님을 확인
        assertFalse(ranks.isEmpty());  // 리스트가 비어있지 않음을 확인
        assertTrue(ranks.size() <= 50);  // 리스트의 크기가 50 이하인지 확인
    }
}