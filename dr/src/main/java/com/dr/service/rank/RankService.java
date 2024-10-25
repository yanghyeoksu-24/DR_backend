package com.dr.service.rank;

import com.dr.dto.rank.RankDTO;
import com.dr.mapper.rank.RankMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RankService {

    // RankMapper 의존성 주입
    @Autowired
    private RankMapper rankMapper;

    // 랭킹 리스트를 가져오는 메서드
    public List<RankDTO> getRankList(Long userNumber) {
        // RankMapper를 사용하여 DB에서 랭킹 데이터를 가져옴
        return rankMapper.getRankList(userNumber);
    }

    // 50위까지의 랭킹 데이터를 가져오는 메서드
    public List<RankDTO> getFiftyRankList() {
        return rankMapper.fiftyRankList();
    }


}