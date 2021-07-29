package com.wishlist.api.document;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Value
@With
@Jacksonized
@Builder
@Document
public class Wishlist {

    @Id
    String id;
    String clientId;
    List<String> productIds;
}