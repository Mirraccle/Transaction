package com.task.transaction.repository;

import com.task.transaction.domain.Place;
import com.task.transaction.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {

    List<Request> findAllByPlaceId(Long placeId);

    List<Request> findAllByProductId(String productId);

}
