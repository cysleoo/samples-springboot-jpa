package com.cysleoo.function;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cysleoo.domain.entity.College;

/**
 * @author ssliu
 * @date 2021-01-16
 */
@Repository
public interface CollegeRepository extends JpaRepository<College,Integer> {

  @Query(value ="select c from College c where c.id = ?1")
  College findById(int id);
}
