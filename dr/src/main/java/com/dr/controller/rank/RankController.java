package com.dr.controller.rank;

import com.dr.dto.rank.RankDTO;
import com.dr.service.rank.RankService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rank")
public class RankController {

    private final RankService rankService;

    // 사용자 랭킹 페이지
    @GetMapping("/userRank")
    public String getUserRank(@RequestParam("userNumber") Long userNumber, Model model) {
        // RankService를 사용하여 특정 사용자의 랭킹 데이터를 가져옴
        List<RankDTO> rankList = rankService.getRankList(userNumber);
        model.addAttribute("rankList", rankList);

        List<RankDTO> fiftyRankList = rankService.getFiftyRankList();
        model.addAttribute("fiftyRankList", fiftyRankList);

        return "rank/userRank";

    }

}