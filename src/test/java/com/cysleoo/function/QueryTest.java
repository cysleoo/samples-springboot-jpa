package com.cysleoo.function;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

import com.cysleoo.domain.entity.Student;
import com.google.gson.Gson;

/**
 * @Query test
 * @author ssliu
 * @date 2021-01-18
 */
@SpringBootTest
public class QueryTest {
  @Autowired
  StudentRepository studentRepository;
  @Autowired
  CollegeRepository collegeRepository;

  private Gson gson = new Gson();

  /**
   * 测试自定义返回结果
   */
  @Test
  void testVO() {
    System.out.println(studentRepository.findVO());
  }

  /**
   * 测试模糊查询：null的情况
   */
  @Test
  void testLikeNull() {
    String name = "123";

    // not work
    // System.out.println(studentRepository.queryByName(null));

    // work
    if (ObjectUtils.isEmpty(name)) {
      name = "";
    }
    System.out.println(studentRepository.queryByName(""));

  }

  /**
   * 测试null的处理
   *    用： ? is null or 来处理null值
   *    1. 可以识别""和null
   *    2. list为空无法识别
   */
  @Test
  void testNull() {
    System.out.println(studentRepository.queryByNameNull(null));
    System.out.println(studentRepository.queryByNameNull(""));

    // list is null
    System.out.println(studentRepository.queryByIdIn(null));
    // list size = 0
    System.out.println(studentRepository.queryByIdIn(new ArrayList<>()));
  }


  /**
   * 测试分页查询
   */
  @Test
  void testPage() {
    PageRequest namePage = PageRequest.of(0, 20, Sort.by("name").descending());
    Page<Student> students = studentRepository.queryAll(namePage);
    System.out.println(gson.toJson(students));
  }



}
