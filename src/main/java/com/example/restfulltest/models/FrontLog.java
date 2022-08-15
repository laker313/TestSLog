package com.example.restfulltest.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "log")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FrontLog implements Serializable {
    @Column(name = "log_id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @NotNull
    @Column(name = "log_message",nullable = false)
    String message;
    @NotNull
    @Column(name = "log_type",nullable = false)
    String type;
    @NotNull
    @Column(name = "log_level",nullable = false)
    String level;
    @NotNull
    @Column(name = "log_time",nullable = false)
    Timestamp timestamp;
    @JsonCreator
    public FrontLog(@JsonProperty("message")String message, @JsonProperty("type")String type,
                    @JsonProperty("level")String level,  @JsonProperty("time")Long timestamp) throws Exception {
        this.message = message;
        this.type = type;
        this.level = level;
        this.timestamp = new Timestamp(timestamp);
    }

    @Override
    public String toString() {
        return "FrontLog{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", level='" + level + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
