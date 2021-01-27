package com.cysleoo.function;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cysleoo.domain.entity.Student;

/**
 * @author ssliu
 * @date 2021-01-19
 */
@SpringBootTest
public class SaveTest {
  @Autowired
  StudentRepository studentRepository;

  /**
   * 测试@DynamicUpdate：仅更新修改过的字段
   */
  @Test
  @Transactional
  void testDynamicUpdate() {
    Optional<Student> byId = studentRepository.findById(1);
    if (byId.isPresent()) {
      Student student = byId.get();
      String name = student.getName();
      student.setName(name+"123");
      studentRepository.save(student);
      // studentRepository.findById(1);


      // studentRepository.saveQuery(student.getName(),student.getId());
      // studentRepository.flush();
    }
  }




}
