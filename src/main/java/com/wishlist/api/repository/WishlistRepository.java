package com.wishlist.api.repository;

import com.wishlist.api.document.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    Optional<Wishlist> findByClientId(String clientId);
    Boolean existsByClientIdAndProductIds(String clientId, String productId);
}
