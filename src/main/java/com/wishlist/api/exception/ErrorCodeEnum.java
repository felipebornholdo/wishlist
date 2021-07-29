package com.wishlist.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    ERROR_WISHLIST_MAX_AMOUNT_PRODUCT("error.wishlist.max-amount.product");

    private final String messageKey;
}
