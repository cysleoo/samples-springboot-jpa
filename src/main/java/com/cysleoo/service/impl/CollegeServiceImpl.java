package com.cysleoo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cysleoo.domain.entity.College;
import com.cysleoo.function.CollegeRepository;
import com.cysleoo.service.CollegeService;

/**
 * @author ssliu
 * @date 2021-01-21
 */
@Service
public class CollegeServiceImpl implements CollegeService {
  @Autowired
  CollegeRepository collegeRepository;


  @Override
  // @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void add(College college) {
    collegeRepository.save(college);
  }
}
