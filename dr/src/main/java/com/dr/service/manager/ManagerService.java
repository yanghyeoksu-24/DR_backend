package com.dr.service.manager;

import com.dr.dto.manager.ManagerSessionDTO;
import com.dr.mapper.manager.ManagerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerMapper managerMapper;

    public ManagerSessionDTO managerLogin(String managerEmail, String managerPw) {
        return managerMapper.managerLogin(managerEmail, managerPw)
                .orElse(null); // 존재하지 않는 경우 null 반환
    }

}