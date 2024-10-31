package com.dr.service.myPage;

import com.dr.mapper.myPage.MyPageMapper; // MyPageMapper 임포트
import com.dr.dto.myPage.UserCheckDTO; // UserCheckDTO 임포트
import com.dr.dto.myPage.PointRecordDTO; // PointRecordDTO 임포트
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceService {

    private final MyPageMapper myPageMapper; // MyPageMapper 주입

    // 출석 체크를 기록하는 메서드
    public void checkAttendance(UserCheckDTO userCheckDTO) {
        Long userNumber = userCheckDTO.getUserNumber(); // DTO에서 userNumber 가져오기
        String date = userCheckDTO.getDate(); // DTO에서 date 가져오기

        // 1. 출석 체크 기록 삽입
        myPageMapper.insertAttendanceCheck(userNumber, date); // 출석 체크 기록 삽입

        // 2. 월간 출석 체크 횟수 조회
        int attendanceCount = checkMonthlyAttendance(userNumber); // 사용자 번호로 월간 출석 횟수 조회

        // 3. 출석 체크 후 포인트 기록
        if (attendanceCount >= 30) {
            // 30일 출석 시 200포인트 적립
            insertPointRecord(new PointRecordDTO(userNumber, 200, "개근"));
        } else {
            // 기본 10포인트 적립
            insertPointRecord(new PointRecordDTO(userNumber, 10, "출석체크"));
        }
    }

    // 포인트 기록을 추가하는 메서드
    public void insertPointRecord(PointRecordDTO pointRecordDTO) {
        myPageMapper.insertPointRecord(pointRecordDTO);
    }

    // 월간 출석 체크 카운트 조회 메서드
    public int checkMonthlyAttendance(Long userNumber) {
        return myPageMapper.checkMonthlyAttendance(userNumber);
    }
}
