package com.ijse.pos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Long categoryID;
}
