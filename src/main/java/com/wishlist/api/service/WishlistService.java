package com.wishlist.api.service;

import com.wishlist.api.config.ProductConfig;
import com.wishlist.api.document.Wishlist;
import com.wishlist.api.dto.ProductWishedDTO;
import com.wishlist.api.dto.WishlistDTO;
import com.wishlist.api.helper.MessageHelper;
import com.wishlist.api.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.wishlist.api.exception.ErrorCodeEnum.ERROR_WISHLIST_MAX_AMOUNT_PRODUCT;
import static com.wishlist.api.mapper.MapperConstants.wishlistMapper;
import static java.util.Optional.ofNullable;


@Service
@RequiredArgsConstructor
@Slf4j
public class WishlistService {

    private final WishlistRepository repository;
    private final ProductConfig productConfig;
    private final MessageHelper messageHelper;

    public void addProduct(final ProductWishedDTO product) {
        var wishlist = findWishlistByClientId(product.getClientId());
        var products = new ArrayList<>(ofNullable(wishlist.getProductIds()).orElse(List.of()));
        var productsExisting = findProductExisting(products, product.getProductId());
        products.add(product.getProductId());
        if (productsExisting.isEmpty()) repository.save(wishlistMapper.buildWishlist(wishlist).withProductIds(products));
    }

    public void removeProduct(String clientId, String productId) {
        var wishlist = findWishlistByClientId(clientId);
        var products = new ArrayList<>(ofNullable(wishlist.getProductIds()).orElse(List.of()));
        products.remove(productId);
        repository.save(wishlistMapper.buildWishlist(wishlist).withProductIds(products));
    }

    public Boolean productExists(final String clientId, final String productId) {
        return repository.existsByClientIdAndProductIds(clientId, productId);
    }

    public WishlistDTO findWishlistByClientId(String clientId) {
        var wishlist = repository.findByClientId(clientId);
        return wishlistMapper.buildWishlistDTO(wishlist.orElseGet(() -> createWishlist(clientId)));
    }

    private Wishlist createWishlist(final String clientId) {
        return repository.save(Wishlist.builder().clientId(clientId).build());
    }

    private List<String> findProductExisting(ArrayList<String> products, String productId) {
        if (productConfig.getProductMaxAmount().equals(products.size()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messageHelper.get(ERROR_WISHLIST_MAX_AMOUNT_PRODUCT));
        return products.stream().filter(product -> product.equals(productId)).collect(Collectors.toList());
    }
}
