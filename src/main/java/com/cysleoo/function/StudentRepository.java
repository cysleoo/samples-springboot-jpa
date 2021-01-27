package com.cysleoo.function;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cysleoo.domain.entity.Student;
import com.cysleoo.domain.vo.NameOnly;
import com.cysleoo.domain.vo.StudentCollegeVO;
import com.cysleoo.domain.vo.StudentVO;

/**
 * jpa 使用方法测试
 * @author ssliu
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>,
    JpaSpecificationExecutor {

  // 基本使用

  /**
   * 基于接口的投影调用
   * @param name
   * @return
   */
  NameOnly findByName(String name);

  /**
   * 基于类的投影
   * @param name
   * @return
   */
  StudentVO findVOByName(String name);

  /**
   * 动态投影
   * @param name
   * @param type
   * @param <T>
   * @return
   */
  <T> T findTByName(String name, Class<T> type);


  /**
   * 派生：模糊匹配
   * @param name
   * @return
   */
  List<Student> findByNameContaining(String name);

  /**
   * test for transactional
   * @param id
   * @return
   */
 // @Transactional(readOnly = true)
 Student findByScore(int score);

  // @Query

  /**
   * 返回自定义数据
   *  1.nativeQuery = false
   *  2.VO需要有@Entity，@Id
   *
   * @return
   */
  @Query(value = "select new StudentCollegeVO(a.id,a.name,a.collegeId,a.score,a.updateTime,a.createTime, b.name) from Student a left join College b on a.collegeId = b.id ")
  List<StudentCollegeVO> findVO();

  /**
   * 模糊查询
   * @param name
   * @return
   */
  @Query("select a from Student a where a.name like %?1%")
  List<Student> queryByName(String name);
  /**
   * 模糊查询:处理null
   * @param name
   * @return
   */
  @Query("select a from Student a where (?1 is null or a.name like %?1%)")
  List<Student> queryByNameNull(String name);

  /**
   * 处理null：list为null
   *
   * @param idList
   * @return
   */
  @Query("select a from Student a where (?1 is null or a.id in ?1)")
  List<Student> queryByIdIn(List<Integer> idList);


  /**
   * 分页查询
   * @param pageable
   * @return
   */
  @Query("select a from Student  a")
  Page<Student> queryAll(Pageable pageable);

  /**
   * 更新
   * @param name
   * @param id
   * @return
   */
  @Modifying
  @Query("update Student s set s.name = ?1 where s.id = ?2")
  void saveQuery(String name, int id);

}

