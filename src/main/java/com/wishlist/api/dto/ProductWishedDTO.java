package com.wishlist.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Value
@With
@Jacksonized
@Builder
public class ProductWishedDTO {

    @NotBlank
    String clientId;
    @NotBlank
    String productId;
}
