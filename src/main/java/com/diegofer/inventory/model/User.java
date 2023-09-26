package com.diegofer.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("User")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    private String id;
    private String name;
    private String password;
    private String email;
    private String role;
    private String branchId;

}
