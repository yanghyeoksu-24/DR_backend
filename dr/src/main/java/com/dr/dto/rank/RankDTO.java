package com.dr.dto.rank;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RankDTO {
    private int rank;              // 사용자 순위
    private String userNickname;   // 사용자 닉네임
    private String photoLocal;     // 사용자 프로필 사진
    private int scoreGet;          // 사용자 환경 기여 점수 합계
}


