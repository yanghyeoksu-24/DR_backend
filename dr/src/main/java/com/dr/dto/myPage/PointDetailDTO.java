package com.dr.dto.myPage;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PointDetailDTO {
    private int pointNumber;
    private String pointNote;
    private int pointGet;
    private LocalDateTime pointDate;
    private int totalPoints;

    //DATE타입 String 타입으로 변환 후, 메서드로 타임리프에 사용하기!
    public String getFormattedPointDate() {
        if (pointDate != null) {
            Date date = Date.from(pointDate.atZone(ZoneId.systemDefault()).toInstant());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.format(date);
        }
        return "";
    }
}
