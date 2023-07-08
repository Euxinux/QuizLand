package com.Dijkstra.QuizLand;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Setter
public class Audit{
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
    private void prePersist(){
        this.createdOn = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.updatedOn = LocalDateTime.now();
    }
}
