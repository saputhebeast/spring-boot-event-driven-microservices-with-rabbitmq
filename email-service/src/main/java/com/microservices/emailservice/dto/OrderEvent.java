package com.microservices.emailservice.dto;

import com.microservices.emailservice.dto.type.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private Status status;
    private String message;
    private Order order;
}
