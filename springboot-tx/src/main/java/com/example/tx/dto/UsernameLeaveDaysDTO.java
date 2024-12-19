package com.example.tx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsernameLeaveDaysDTO {
    private String username;
    private Integer totalLeaveDays;
}
