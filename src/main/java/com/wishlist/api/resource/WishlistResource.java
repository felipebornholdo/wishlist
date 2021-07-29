package com.wishlist.api.resource;

import com.wishlist.api.dto.ProductWishedDTO;
import com.wishlist.api.dto.WishlistDTO;
import com.wishlist.api.service.WishlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/wishlists")
@RequiredArgsConstructor
@Tag(name = "Wishlists")
public class WishlistResource {

    private final WishlistService wishlistService;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Add product to wishlist", responses = {@ApiResponse(responseCode = "201")})
    public void addProduct(@Valid @RequestBody ProductWishedDTO product) {
        wishlistService.addProduct(product);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get wishlist by client",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = WishlistDTO.class)))})
    public WishlistDTO findWishlistByClientId(@PathVariable String clientId) {
        return wishlistService.findWishlistByClientId(clientId);
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Check product exists at wishlist",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))})
    public Boolean productExists(@PathVariable String productId, @RequestParam String clientId) {
        return wishlistService.productExists(clientId, productId);
    }

    @DeleteMapping("/client/{clientId}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Remove product from client wishlist", responses = {@ApiResponse(responseCode = "204")})
    public void removeProduct(@PathVariable String clientId, @RequestParam String productId) {
        wishlistService.removeProduct(clientId, productId);
    }
}
