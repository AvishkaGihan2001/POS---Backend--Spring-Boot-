package com.ijse.pos.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private List<Long> itemIDs;
    private List<Integer> quantities;
    private String customerName;
}
