package com.diegofer.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private int inventoryStock;
    private String category;
    @Column("branch_id")
    private String branchId;

    public Product addStock(int stock){
        this.inventoryStock = this.inventoryStock + stock;
        return this;
    }
}
