package com.task.transaction.service;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.OfferDto;
import com.task.transaction.dto.RequestDto;

public interface OfferService {

    CommonResponse addOffer(OfferDto offerDto);
}
