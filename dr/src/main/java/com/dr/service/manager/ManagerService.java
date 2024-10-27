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


}