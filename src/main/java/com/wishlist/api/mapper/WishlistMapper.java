package com.wishlist.api.mapper;

import com.wishlist.api.document.Wishlist;
import com.wishlist.api.dto.WishlistDTO;
import org.mapstruct.Mapper;

@Mapper
public interface WishlistMapper {

    Wishlist buildWishlist(WishlistDTO wishlistDTO);

    WishlistDTO buildWishlistDTO(Wishlist wishlist);
}
