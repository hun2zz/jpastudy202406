package com.spring.jpastudy.chap01.entity;

import jdk.jfr.Category;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString(exclude = "nickName")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_product")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long id; // PK


    @Setter
    @Column(name = "prod_nm", length = 30, nullable = false)
    private String name; // 상품명

    @Column(name = "prod_price")
    private int price; // 상품 가격

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category; //상품 카테고리


    @CreationTimestamp // INSERT 시에 자동으로 서버시간 저장
    @Column(updatable = false) // 수정 불간
    private LocalDateTime createdAt; // 상품 등록시간

    @UpdateTimestamp // update문 실행시 자동으로 시간이 저장
    private LocalDateTime updatedAt; // 상품 수정 시간


    //데이터베이스에서는 저장 안하고 클래스 내부에서만 사용할 필드
    @Transient
    private String nickName;

    public enum Category {
        FOOD, FASHION, ELECTRONIC
    }


    @PrePersist
    public void prePersist() {
        if (this.price == 0) {
            this.price = 10000;

        }
        if (this.category == null) {
            this.category = Category.FOOD;
        }
    }
}
