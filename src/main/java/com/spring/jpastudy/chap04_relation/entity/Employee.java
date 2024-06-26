package com.spring.jpastudy.chap04_relation.entity;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString(exclude = "department")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id; // 사원 번호


    @Column(name = "emp_name", nullable = false)
    private String name;

    //단방향 매핑 = 데이터베이스처럼 한 쪽에 상대방의 Pk를 Fk로 갖는 형태
    // Eager : 연관된 데이터를 항상 JOIN을 통해 같이 가져옴
    // LAZY : 해당 엔터티 데이터만 가져오고, 필요한 경우 연관엔터티를 가져옴

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department department;


    public void changeDepartment(Department department) {
        this.department = department;
        department.getEmployees().add(this);
    }
}
