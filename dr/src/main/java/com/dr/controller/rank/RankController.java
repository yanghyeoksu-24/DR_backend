package com.dr.controller.rank;

import com.dr.dto.myPage.PointCheckDTO;
import com.dr.dto.rank.RankDTO;
import com.dr.mapper.rank.RankMapper;
import com.dr.service.rank.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rank")
public class RankController {

    private final RankService rankService;
    private final RankMapper rankMapper;

    // 사용자 랭킹 페이지
    @GetMapping("/userRank")
    public String getUserRank(@SessionAttribute(value = "userNumber", required = false) Long userNumber, Model model, HttpSession session) {

        // RankService를 사용하여 특정 사용자의 랭킹 데이터를 가져옴
        List<RankDTO> rankList = rankService.getRankList(userNumber);
        model.addAttribute("rankList", rankList);

        // 전체 50위 랭킹 데이터도 가져옴
        List<RankDTO> fiftyRankList = rankService.getFiftyRankList();
        model.addAttribute("fiftyRankList", fiftyRankList);

        return "rank/userRank";
    }


    @Scheduled(cron = "0 30 8 * * *")  // UTC 기준으로 08:05
    public void givePointsToTop5() {
        System.out.println("시작조차 안되는거니?");
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
