package com.dr.service.manager;


import com.dr.dto.manager.*;
import com.dr.mapper.manager.ManagerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j // 로그 기능 추가
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerMapper managerMapper;

    @Value("C:/upload/")
    private String fileDir;

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

    // 상품 추가 페이지 이동
    public ManagerRegisterDTO updateShow(String productName) {
        return managerMapper.updateShow(productName);
    }

    // 상품 추가
    public void productUpdate(ManagerRegisterDTO managerRegisterDTO) {
        managerMapper.productUpdate(managerRegisterDTO);
    }

    // 상품과 사진 등록
    @Transactional
    public void registerProductAndPhoto(ManagerRegisterDTO managerRegisterDTO, MultipartFile file) {
        // 상품 등록
        log.info("상품 등록 시작: {}", managerRegisterDTO);
        managerMapper.registerProduct(managerRegisterDTO);
        log.info("상품 등록 완료: {}", managerRegisterDTO);

        // 상품 번호 설정 (DB에 저장 후 할당된 번호 사용)
        int productNumber = managerRegisterDTO.getProductNumber();
        managerRegisterDTO.setProductNumber(productNumber);

        // 파일 정보 설정
        String originalFilename = file.getOriginalFilename();
        String filePath = "C:/upload/" + originalFilename;
        String fileSize = "1000"; // 고정값 설정
        Date photoUploadDate = new Date(); // 현재 날짜

        // 파일 저장
        log.info("파일 저장 시작: 원본 파일 이름: {}, 파일 경로: {}", originalFilename, filePath);
        try {
            saveFile(file, (long) productNumber, originalFilename, filePath, fileSize, photoUploadDate);
            log.info("파일 저장 완료: {}", originalFilename);
        } catch (Exception e) {
            log.error("파일 저장 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }

    private void saveFile(MultipartFile file, Long productNumber, String originalFilename, String filePath, String fileSize, Date photoUploadDate) {
        // 파일 저장 경로 확인 및 폴더 생성
        File uploadDir = new File("C:/upload");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // 폴더가 존재하지 않으면 생성
            log.info("업로드 디렉토리가 생성되었습니다: {}", uploadDir.getAbsolutePath());
        }

        // 파일 저장 로직
        try {
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile); // 선택한 파일을 해당 경로에 저장
            log.info("파일이 성공적으로 저장되었습니다: {}", filePath); // 성공 로그 추가
        } catch (IOException e) {
            log.error("파일 저장 중 오류가 발생했습니다: {}", e.getMessage()); // 오류 메시지 로그
            log.error("오류 세부정보: ", e); // 예외의 전체 스택 추적을 로그에 기록
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }

        // 사진 정보 설정
        ManagerPhotoDTO managerPhotoDTO = new ManagerPhotoDTO();
        managerPhotoDTO.setProductNumber(Math.toIntExact(productNumber));
        managerPhotoDTO.setPhotoOriginal(originalFilename);
        managerPhotoDTO.setPhotoLocal(originalFilename); // 로컬 파일 이름은 원본 파일 이름
        managerPhotoDTO.setPhotoSize(fileSize);
        managerPhotoDTO.setPhotoUpload(String.valueOf(photoUploadDate)); // Date 객체를 직접 사용

        // 사진 등록
        managerMapper.registerPhoto(managerPhotoDTO);
    }




}