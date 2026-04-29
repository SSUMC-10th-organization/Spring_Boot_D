package com.example.umc10jinho.domain.market.dto;

import java.util.List;

public class MarketResDTO {

    public record HomeResponse(
            List<MarketPreview> markets
    ) {
    }

    public record MarketPreview(
            Long marketId,
            String name,
            String location,
            String categoryName,
            String imageUrl
    ) {
    }
}