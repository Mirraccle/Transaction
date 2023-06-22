package com.task.transaction.service;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.RequestDto;

public interface RequestService {

    CommonResponse addRequest(RequestDto requestDto);
}
