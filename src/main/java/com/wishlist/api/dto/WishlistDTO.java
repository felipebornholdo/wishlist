package com.wishlist.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@With
@Jacksonized
@Builder
public class WishlistDTO {

    String id;
    String clientId;
    List<String> productIds;
}
