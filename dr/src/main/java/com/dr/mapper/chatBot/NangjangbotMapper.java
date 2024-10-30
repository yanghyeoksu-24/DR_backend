package com.dr.mapper.chatBot;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NangjangbotMapper {
    void createNangjangbot(Long userNumber);
}
