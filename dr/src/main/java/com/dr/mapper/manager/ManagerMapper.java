package com.dr.mapper.manager;

import com.dr.dto.manager.DashBoardDTO;
import com.dr.dto.manager.ManagerDTO;
import com.dr.dto.manager.ManagerSessionDTO;
import com.dr.dto.manager.ManagerUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;
import java.util.List;

@Mapper
public interface ManagerMapper {

   Optional<ManagerSessionDTO> managerLogin(@Param("managerEmail") String managerEmail, @Param("managerPw") String managerPw);

   DashBoardDTO dashBoardInfo();

   List <ManagerDTO> managerInfo();

   List <ManagerUserDTO> manageUser();

   boolean userOut(Integer userNumber);

   boolean userPause(Integer userNumber);

}
