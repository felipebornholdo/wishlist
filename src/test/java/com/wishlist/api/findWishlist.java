package com.wishlist.api;

import com.wishlist.api.config.ProductConfig;
import com.wishlist.api.dto.WishlistDTO;
import com.wishlist.api.repository.WishlistRepository;
import com.wishlist.api.service.WishlistService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(Cucumber.class)
public class findWishlist {

    @Autowired
    private WishlistRepository repository;
    private ProductConfig productConfig;

    private WishlistService service;
    private WishlistDTO wishlistDTO;
    private Boolean productExists;

    @Given("Client wishlist service is initialized")
    public void clientWishlistServiceIsInitialized() {
        service = new WishlistService(repository, productConfig);
    }

    @When("I find wishlist by client {string}")
    public void iFindWishlistByClient(String clientId) {
        wishlistDTO = service.findWishlistByClientId(clientId);
    }

    @Then("The wishlist returned")
    public void theWishlistReturned() {
        Assert.assertNotNull(wishlistDTO);
    }

    @When("I find product at wishlist by client {string} and product {string}")
    public void iFindProductAtWishlistByClientAndProduct(String clientId, String productId) {
        productExists = service.productExists(clientId, productId);
    }

    @Then("The product exists")
    public void theProductExists() {
        Assert.assertTrue(productExists);
    }
}
