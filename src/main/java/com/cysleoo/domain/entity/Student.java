package com.cysleoo.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.cysleoo.domain.vo.NameOnly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ssliu
 * @date 2021-01-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
@DynamicUpdate
public class Student implements NameOnly {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private Integer collegeId;
  private Integer score;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

}
