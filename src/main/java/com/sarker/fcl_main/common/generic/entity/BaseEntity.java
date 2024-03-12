package com.sarker.fcl_main.common.generic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

// By using @MappedSuperclass, you are explicitly indicating that
// the class is not meant to be mapped to its own table.
// Instead, its fields should be considered as part of its subclasses.
@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active", columnDefinition="bit default 1", nullable = false)
    private Boolean isActive = true;

}


