package com.task.transaction.dto;

import com.task.transaction.dto.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private ResponseStatus status;
    private Object data;
}
