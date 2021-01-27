package com.cysleoo.function;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cysleoo.domain.entity.Student;
import com.cysleoo.domain.vo.NameOnly;
import com.cysleoo.domain.vo.StudentVO;

/**
 * 简单派生测试
 */
@SpringBootTest
class SimpleRepositoryTest {

  @Autowired
  StudentRepository studentRepository;

  @BeforeEach
  void setUp() {
    // studentRepository.save(new Student(null,"lili",1,2, LocalDateTime.now(),LocalDateTime.now()));
  }

  @AfterEach
  void tearDown() {

  }


  @Test
  void findSlice() {
    NameOnly lili = studentRepository.findByName("lili");
    System.out.println(lili);
  }

  @Test
  void findVO() {
    StudentVO lili = studentRepository.findVOByName("lili");
    System.out.println(lili);
  }

  @Test
  void findT() {
    Student lili = studentRepository.findTByName("lili", Student.class);
    System.out.println(lili);
    NameOnly lili1 = studentRepository.findTByName("lili", NameOnly.class);
    System.out.println(lili1);
    StudentVO lili2 = studentRepository.findTByName("lili", StudentVO.class);
    System.out.println(lili2);
  }


}
