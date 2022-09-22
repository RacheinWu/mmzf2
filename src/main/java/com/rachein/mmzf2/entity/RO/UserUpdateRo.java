package com.rachein.mmzf2.entity.RO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author 华南理工大学 吴远健
 * @Date 2022/9/20
 * @Description
 */
@Data
public class UserUpdateRo {
    private String nickname;
    private String school;
    private String status;
    private String  phone;
    private String mingzu;
    private String gender;
    private LocalDateTime birthday;
    private String shenfenmianmao;
    private LocalDateTime graduateDate;
    private String graduateSchool;
    private String firstSchool;
    private String zhunayeDalei;
    private String majority;
}
