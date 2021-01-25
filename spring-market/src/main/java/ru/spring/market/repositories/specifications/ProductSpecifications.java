package ru.spring.market.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.spring.market.model.Product;

public class ProductSpecifications {
    private static Specification<Product> priceGreaterOrEqualsThan(int minPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private static Specification<Product> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), maxPrice);
    }

    private static Specification<Product> titleLike(String titlePart) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), String.format("%%%s%%", titlePart));
    }

    public static Specification<Product> build(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);
        if (params.containsKey("min_price") && !params.getFirst("min_price").isBlank()) {
            spec = spec.and((ProductSpecifications.
                    priceGreaterOrEqualsThan(Integer.parseInt(params.getFirst("min_price")))));
        }

        if (params.containsKey("max_price") && !params.getFirst("max_price").isBlank()) {
            spec = spec.and((ProductSpecifications.
                    priceGreaterOrEqualsThan(Integer.parseInt(params.getFirst("max_price")))));
        }

        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and((ProductSpecifications.
                    priceGreaterOrEqualsThan(Integer.parseInt(params.getFirst("title")))));
        }

        return spec;
    }
}
