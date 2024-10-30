package com.dr.service.chatBot;

import com.dr.mapper.chatBot.NangjangbotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //메서드나 클래스에 트랜잭션을 적용하여 데이터베이스 작업의 원자성을 보장하는 데 사용
// 트랜잭션은 여러 데이터베이스 작업을 하나의 단위로 묶어 처리하며,
// 이 중 하나라도 실패하면 전체 작업이 취소되어 데이터 무결성을 유지
@RequiredArgsConstructor //final 필드의 생성자를 자동생성
public class NangjangbotService {
    private NangjangbotMapper nangjangbotMapper;
}
