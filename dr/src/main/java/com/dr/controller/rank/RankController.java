package com.dr.controller.rank;

import com.dr.dto.rank.RankDTO;
import com.dr.service.rank.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rank")
public class RankController {

    private final RankService rankService;

    // 사용자 랭킹 페이지
    @GetMapping("/userRank")
    public String getUserRank(HttpSession session, Model model) {
        // 세션에서 userNumber 가져오고..
        Long userNumber = (Long) session.getAttribute("userNumber");

        // 세션에 userNumber가 없는 경우 로그인 페이지로 리다이렉트
        if (userNumber == null) {
            return "redirect:/user/login";
        }

        // RankService를 사용하여 특정 사용자의 랭킹 데이터를 가져옴
        List<RankDTO> rankList = rankService.getRankList(userNumber);
        model.addAttribute("rankList", rankList);

        // 전체 50위 랭킹 데이터도 가져옴
        List<RankDTO> fiftyRankList = rankService.getFiftyRankList();
        model.addAttribute("fiftyRankList", fiftyRankList);

        return "rank/userRank";
    }
}