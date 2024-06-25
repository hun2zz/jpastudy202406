package com.spring.jpastudy.chap01.repository;

import com.spring.jpastudy.chap01.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository  productRepository;

    @Test
    @DisplayName("상품을 데이터베이스에 저장한다")
    void saveTest() {
        //given
        Product product = Product.builder().name("정장").price(120000).category(Product.Category.FASHION).build();

        //when
        Product saved = productRepository.save(product);
        //then
        assertNotNull(saved);
    }


}