package com.dr.mapper.manager;

import com.dr.dto.manager.ManagerSessionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ManagerMapper {

   Optional<ManagerSessionDTO> managerLogin(@Param("managerEmail") String managerEmail, @Param("managerPw") String managerPw);

}
