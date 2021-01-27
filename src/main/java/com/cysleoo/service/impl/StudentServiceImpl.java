package com.cysleoo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cysleoo.domain.entity.College;
import com.cysleoo.domain.entity.Student;
import com.cysleoo.function.StudentRepository;
import com.cysleoo.service.CollegeService;
import com.cysleoo.service.StudentService;

/**
 * @author ssliu
 * @date 2021-01-14
 */
@Service
public class StudentServiceImpl implements StudentService {
  @Autowired
  StudentRepository studentRepository;
  @Autowired
  CollegeService collegeService;

  @Override
  public void save(Student student1) {
  }

  @Override
  @Transactional
  public void findAndChange(int id) {
    // Student student = studentRepository.findById(id).get();
    Student student = studentRepository.findByScore(id);
    student.setName("changed");
  }


  @Override
  @Transactional(rollbackFor = Exception.class)
  public void modifyStudentAndCollege()  {
    Optional<Student> byId = studentRepository.findById(1);
    if (byId.isPresent()) {
      Student student = byId.get();
      student.setName("xxx");
      studentRepository.save(student);

      College college = new College();
      college.setName("河北工程大学");
      collegeService.add(college);

      throw new RuntimeException();
    }
  }

  @Override
  @Transactional
  public void saveAndChange(Student student) {
    // 1.save
    Student save = studentRepository.save(student);

    // 2.modify
    save.setName("changed");
  }
}
