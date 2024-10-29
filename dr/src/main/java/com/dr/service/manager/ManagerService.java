package com.dr.service.manager;

import com.dr.dto.manager.*;
import com.dr.mapper.manager.ManagerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerMapper managerMapper;

    // 로그인
    public ManagerSessionDTO managerLogin(String managerEmail, String managerPw) {
        return managerMapper.managerLogin(managerEmail, managerPw)
                .orElse(null); // 존재하지 않는 경우 null 반환
    }

    // 관리자 정보
    public List<ManagerDTO> managerInfo(){
        return managerMapper.managerInfo();
    }

    // 대시보드
    public DashBoardDTO dashBoardInfo(){
        return managerMapper.dashBoardInfo();
    }

    // 회원관리
    public List<ManagerUserDTO> manageUser(){
        return managerMapper.manageUser();
    }

    // 회원탈퇴
    public boolean userOut(Integer userNumber) {
        return managerMapper.userOut(userNumber);
    }

    // 회원 정지
    public boolean userPause(Integer userNumber) {
        return managerMapper.userPause(userNumber);
    }

    // 회원 검색
    public ManagerUserDTO userSearch(int userNumber) {
        return managerMapper.userSearch(userNumber);
    }

    // 게시판
    public List<ManagerBoardDTO> showBoard(){
        return managerMapper.showBoard();
    }

    // 게시판 삭제
    public boolean boardDelete(Integer boardNumber) {
        return managerMapper.boardDelete(boardNumber);
    }

    // 게시판 검색
    public ManagerBoardDTO boardSearch(int boardNumber) {
        return managerMapper.boardSearch(boardNumber);
    }

    // 레시피
    public List<ManagerRecipeDTO> showRecipe(){
        return managerMapper.showRecipe();
    }

    // 레시피 삭제
    public boolean recipeDelete(Integer recipeNumber) {
        return managerMapper.recipeDelete(recipeNumber);
    }

    // 레시피 검색
    public ManagerRecipeDTO recipeSearch(int recipeNumber) {
        return managerMapper.recipeSearch(recipeNumber);
    }

    // 댓글
    public List<ManagerCommentDTO> showReply(){
        return managerMapper.showReply();
    }

    // 댓글 삭제
    public boolean replyDelete(Integer replyNumber) {
        return managerMapper.replyDelete(replyNumber);
    }

    // 댓글 검색
    public ManagerCommentDTO replySearch(int replyNumber) {
        return managerMapper.replySearch(replyNumber);
    }

    // 포인트
    public List<ManagerPointDTO> showPoint(){
        return managerMapper.showPoint();
    }

    // 포인트 삭제 (목록)
    public boolean pointDelete(Integer pointNumber) {
        return managerMapper.pointDelete(pointNumber);
    }

    // 포인트 회수
    public boolean takePoint(Integer PointNumber) {
        return managerMapper.takePoint(PointNumber);
    }

    // 포인트 검색
    public List<ManagerPointDTO> pointSearch(String userNickName) {
        return managerMapper.pointSearch(userNickName);
    }

    // 신고
    public List<ManagerReportDTO> showReport(){
        return managerMapper.showReport();
    }

    // 신고 삭제
    public boolean reportDelete(Integer sirenNumber) {
        return managerMapper.reportDelete(sirenNumber);
    }

    // 상품
    public List<ManagerProductDTO> showProduct(){
        return managerMapper.showProduct();
    }

    // 상품 삭제
    public boolean productDelete(String productName) {
        return managerMapper.productDelete(productName);
    }

    // 상품 등록

    // 상품 수정


}