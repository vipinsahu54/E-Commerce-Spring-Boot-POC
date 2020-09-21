package com.ecommerce.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.site.model.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long>{

}
