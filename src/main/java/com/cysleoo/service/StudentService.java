package com.cysleoo.service;

import com.cysleoo.domain.entity.Student;

/**
 * @author ssliu
 * @date 2021-01-14
 */
public interface StudentService {

  void save(Student student);

  void findAndChange(int id);

  void modifyStudentAndCollege() throws Exception;

  void saveAndChange(Student student);
}
