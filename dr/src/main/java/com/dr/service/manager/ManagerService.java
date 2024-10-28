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

    public ManagerSessionDTO managerLogin(String managerEmail, String managerPw) {
        return managerMapper.managerLogin(managerEmail, managerPw)
                .orElse(null); // 존재하지 않는 경우 null 반환
    }

    public List<ManagerDTO> managerInfo(){
        return managerMapper.managerInfo();
    }

    public DashBoardDTO dashBoardInfo(){
        return managerMapper.dashBoardInfo();
    }

    public List<ManagerUserDTO> manageUser(){
        return managerMapper.manageUser();
    }

    public boolean userOut(Integer userNumber) {
        return managerMapper.userOut(userNumber);
    }

    public boolean userPause(Integer userNumber) {
        return managerMapper.userPause(userNumber);
    }

    public List<ManagerBoardDTO> showBoard(){
        return managerMapper.showBoard();
    }

    public boolean boardDelete(Integer boardNumber) {
        return managerMapper.boardDelete(boardNumber);
    }

    public List<ManagerRecipeDTO> showRecipe(){
        return managerMapper.showRecipe();
    }

    public boolean recipeDelete(Integer recipeNumber) {
        return managerMapper.recipeDelete(recipeNumber);
    }

    public List<ManagerCommentDTO> showReply(){
        return managerMapper.showReply();
    }

    public boolean replyDelete(Integer replyNumber) {
        return managerMapper.replyDelete(replyNumber);
    }

    public List<ManagerPointDTO> showPoint(){
        return managerMapper.showPoint();
    }

    public boolean pointDelete(Integer pointNumber) {
        return managerMapper.pointDelete(pointNumber);
    }

    public boolean takePoint(Integer PointNumber) {
        return managerMapper.takePoint(PointNumber);
    }

    public List<ManagerReportDTO> showReport(){
        return managerMapper.showReport();
    }

    public boolean reportDelete(Integer sirenNumber) {
        return managerMapper.reportDelete(sirenNumber);
    }

    public List<ManagerProductDTO> showProduct(){
        return managerMapper.showProduct();
    }

    public boolean productDelete(String productName) {
        return managerMapper.productDelete(productName);
    }


}