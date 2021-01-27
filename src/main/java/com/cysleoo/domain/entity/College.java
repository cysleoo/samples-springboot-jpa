package com.cysleoo.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ssliu
 * @date 2021-01-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "college")
public class College {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private Integer level;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

}
