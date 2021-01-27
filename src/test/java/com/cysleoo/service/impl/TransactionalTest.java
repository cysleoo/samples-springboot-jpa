package com.cysleoo.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cysleoo.function.CollegeRepository;

@SpringBootTest
class TransactionalTest {
  @Autowired
  StudentServiceImpl studentService;
  @Autowired
  CollegeRepository collegeRepository;

  /**
   * 测试事务的优先级
   * @throws Exception
   */
  @Test
  void testOrder() throws Exception {
    studentService.modifyStudentAndCollege();
  }

}
