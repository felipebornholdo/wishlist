package com.wishlist.api.mapper;

import org.mapstruct.factory.Mappers;

public class MapperConstants {

    private MapperConstants() {
    }

    public static final WishlistMapper wishlistMapper = Mappers.getMapper(WishlistMapper.class);
}
