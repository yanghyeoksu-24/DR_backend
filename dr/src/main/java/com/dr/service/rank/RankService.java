package com.dr.service.rank;

import com.dr.dto.rank.RankDTO;
import com.dr.dto.myPage.PointCheckDTO;
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

    // 특정 사용자의 랭킹 리스트를 가져오는 메서드
    public List<RankDTO> getRankList(Long userNumber) {
        return rankMapper.getRankList(userNumber);
    }

    // 50위까지의 랭킹 데이터를 가져오는 메서드
    public List<RankDTO> getFiftyRankList() {
        return rankMapper.fiftyRankList();
    }

    // 월초 1등부터 5등까지 사용자에게 200포인트를 지급하는 메서드
    public void givePointsToTop5() {
        // 1등부터 5등까지의 사용자 조회
        List<RankDTO> top5RankList = rankMapper.Top5Rank();

        // 각 사용자에게 200 포인트 적립
        for (RankDTO user : top5RankList) {
            PointCheckDTO pointCheckDTO = new PointCheckDTO();
            pointCheckDTO.setUserNumber((long) user.getUserNumber());
            pointCheckDTO.setPointGet(200);
            pointCheckDTO.setPointNote("랭킹");

            // 포인트 적립
            rankMapper.insertPoint(pointCheckDTO);
        }
    }
}
