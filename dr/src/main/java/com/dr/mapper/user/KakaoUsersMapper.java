package com.dr.mapper.user;

import com.dr.dto.user.KakaoUsersDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KakaoUsersMapper {

    // providerId를 기준으로 사용자 정보를 조회합니다.
    KakaoUsersDTO findByProviderId(String providerId);

    // 새로운 사용자를 삽입합니다.
    void insertUser(KakaoUsersDTO user);

    // 기존 사용자 정보를 업데이트합니다.
    void updateUser(KakaoUsersDTO user);

}
