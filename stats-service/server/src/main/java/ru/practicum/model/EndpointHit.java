package ru.practicum.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EndpointHit {

    private Integer id;
    private String app;
    private String uri;
    private String ip;
    private LocalDateTime timestamp;
}
