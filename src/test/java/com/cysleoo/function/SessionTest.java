package com.cysleoo.function;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cysleoo.domain.entity.Student;
import com.cysleoo.service.impl.StudentServiceImpl;

/**
 * 针对查找出的结果修改后会影响数据库的问题，对Session类进行测试
 * @author ssliu
 * @date 2021-01-21
 */
@SpringBootTest
public class SessionTest {
  @Autowired
  StudentServiceImpl studentService;

  /**
   * 测试find的结果修改后会不会影响数据库
   * 分为以下几个实验
   * 1.findAndChange：加@Transactional
   *    结果：影响库中数据
   *    解释：外部方法有事务，即外部有session，根据事务的传播原理，findById也是在该事务中的，所以find之后是@persisent状态，对其的修改会影响库中数据
   * 2.findAndChange：不加@Transactional
   *    结果：不影响库中数据
   *    解释：findById是有事务的，findById方法结束之后，事务已经结束，session close，entity已经是detached状态,所以后续对entity的修改不会影响数据库
   */
  @Test
  void testFindAndChange() {
    studentService.findAndChange(1);
  }

  /**
   * 测试save之后，修改数据会不会影响数据库
   * 1.saveAndChange： 不加@transactional
   *    结果：不影响
   *    解释：
   * 2.saveAndChange：加@transactional
   *    结果：影响
   *    解释：
   */
  @Test
  void testSaveAndChange() {
    Student student = new Student();
    student.setName("raw name");

    studentService.saveAndChange(student);
  }

}
