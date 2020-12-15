package com.example.afterservice.dto;

import com.example.afterservice.entity.Edition;
import lombok.Data;

@Data
public class EditionDto extends Edition {
    private String username;
}
