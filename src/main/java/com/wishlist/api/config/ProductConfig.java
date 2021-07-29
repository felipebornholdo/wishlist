package com.wishlist.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ProductConfig {

    @Value("${product.max-amount}")
    private Integer productMaxAmount;
}
