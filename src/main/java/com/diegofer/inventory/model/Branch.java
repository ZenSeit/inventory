package com.diegofer.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.processing.Generated;

@Table("Branch")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Branch{

    @Id
    @Generated(value = "uuid")
    private String id;
    private String name;
    private String location;

}
