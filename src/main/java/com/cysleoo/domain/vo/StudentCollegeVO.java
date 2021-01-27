package com.cysleoo.domain.vo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ssliu
 * @date 2021-01-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentCollegeVO  {
  @Id
  private Integer id;
  private String name;
  private Integer collegeId;
  private Integer score;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

  private String collegeName;
}
