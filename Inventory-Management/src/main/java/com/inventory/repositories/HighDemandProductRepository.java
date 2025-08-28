package com.inventory.repositories;


import com.inventory.models.HighDemandProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HighDemandProductRepository extends JpaRepository<HighDemandProduct, Integer> {

    public Optional<HighDemandProduct> findByProductId(Integer productId);
    public List<HighDemandProduct> findAllByProductIdIn(List<Integer> productIds);

}
