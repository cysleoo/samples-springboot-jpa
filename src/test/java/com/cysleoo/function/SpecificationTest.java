package com.cysleoo.function;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import com.cysleoo.domain.entity.Student;

/**
 * Specification test
 */
@SpringBootTest
class SpecificationTest {

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  EntityManager entityManager;

  @BeforeEach
  void setUp() {
    // studentRepository.save(new Student(null,"lili",1,2, LocalDateTime.now(),LocalDateTime.now()));
  }

  @AfterEach
  void tearDown() {

  }

  /**
   * what can root do?
   *  0. 获取要操作的字段
   *  1. in
   *  2. null / not null
   */
  @Test
  void testRoot() {
    // get

    // in
    Specification<Student> idInSpec = (root, query, builder) -> {
      Path<Object> id = root.get("id");
      return id.in(List.of(1, 2, 3));
    };

     // not null
     Specification<Student> scoreIsNotNullSpec = (root, query, builder) -> {
      Path<Object> score = root.get("score");
       return score.isNotNull();
    };

     // is null
     Specification<Student> scoreIsNullSpec = (root, query, builder) -> {
      Path<Object> score = root.get("score");
       return score.isNull();
    };

    // List<Student> all = studentRepository.findAll(idIn);
    // List<Student> all = studentRepository.findAll(scoreIsNullSpec);
    // List<Student> all = studentRepository.findAll(scoreIsNullSpec);
    // System.out.println(all);

    // 组合
    List<Student> all = studentRepository.findAll(idInSpec.and(scoreIsNullSpec));
    System.out.println(all);
  }

  /**
   * what can query do?
   * 1. order by
   */
  @Test
  void testQuery() {

    Specification<Student> orderSpec =  (root,query, builder)->{
      Path<Object> collegeId = root.get("collegeId");
      Path<Object> id = root.get("id");

      // multiselect在spring data jpa中无效
      // query = query.multiselect(collegeId);

      // 1.order by single
      // query.orderBy(builder.desc(id));

      // 2.order by list
      query.orderBy(builder.desc(id), builder.asc(collegeId));

      Predicate equalPre = builder.equal(id, 1);

      // 执行查询
      return query.where(equalPre).getRestriction();
    };

    List all = studentRepository.findAll(orderSpec);
    System.out.println(all);
  }

  /**
   * what can builder do?
   * 1. 连接、比较、排序
   * 2. 动态查询：可以解决参数为null的问题
   *
   */
  @Test
  void testBuilder() {

    List<Integer> ids = new ArrayList<>();
    String name  = "li";

    Specification<Student> builderSpec =  (root,query,builder) ->{
      Path<Object> collegeId = root.get("collegeId");
      Path<Object> id = root.get("id");
      Path<String> namePath = root.get("name");
      Path<LocalDateTime> createTime = root.get("createTime");

      // 1. 比较、连接、排序
      Predicate resultPre = builder.lessThan(createTime, LocalDateTime.now());
      query.orderBy(builder.asc(id),builder.desc(namePath));

      // 2.动态查询
      if (!ObjectUtils.isEmpty(ids)) {
        Predicate inPre = id.in(ids);
        resultPre = builder.and(inPre, resultPre);
      }
      if (!ObjectUtils.isEmpty(name)) {
        Predicate namePre = builder.like(namePath, "%" + name + "%");
        resultPre = builder.and(namePre, resultPre);
      }

      return query.where(resultPre).getRestriction();
    };

    List all = studentRepository.findAll(builderSpec);
    System.out.println(all);
  }

}
