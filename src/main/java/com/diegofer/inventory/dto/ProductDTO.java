package com.diegofer.inventory.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private String id;
    private String name;
    private String description;
    private int inventoryStock;
    private String category;
    private String branchId;

}
