package ru.lesson7.spring.data.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.lesson7.spring.data.model.Product;
import ru.lesson7.spring.data.repositories.ProductRepository;

import java.util.List;

/*
* Создать сущность «Товар» (id, название, стоимость) и соответствующую таблицу в БД. Заполнить таблицу тестовыми данными (20 записей).
Сделать RestController позволяющий выполнять следующий набор операции над этой сущностью:
    получение товара по id [ GET .../app/products/{id} ]
    получение всех товаров [ GET .../app/products ]
    создание нового товара [ POST .../app/products ]
    удаление товара по id.[ GET .../app/products/delete/{id} ]
(Замечание: пока делаем немного не по правилам REST API, эта тема будет разбираться на следующих занятиях,
* поэтому удаление выполняется через http-метод GET, а не DELETE)
* К запросу всех товаров добавьте возможность фильтрации по минимальной и максимальной цене
* (в трех вариантах: товары дороже min цены, товары дешевле max цены, или товары, цена которых находится в пределах min-max).

*
* */

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/{id}")
    public Product getProductByID(@RequestParam Long id) {
        return productRepository.findById(id).get();
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false, name = "min_price") Integer minPrice,
                                        @RequestParam(required = false, name = "max_price") Integer maxPrice) {

        if (minPrice != null & maxPrice != null)  {
           return productRepository.findAllByPriceBetween(minPrice, maxPrice);
        }

        if (minPrice != null) {
            return productRepository.findAllByPriceIsGreaterThanEqual(minPrice);
        }

        if (maxPrice != null) {
            return productRepository.findAllByPriceIsLessThanEqual(maxPrice);
        }

        return productRepository.findAll();
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("delete/{id}")
    public boolean deleteProductById(@RequestParam Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
