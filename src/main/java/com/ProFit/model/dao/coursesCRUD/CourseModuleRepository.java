package com.ProFit.model.dao.coursesCRUD;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProFit.model.bean.coursesBean.CourseModuleBean;

public interface CourseModuleRepository extends JpaRepository<CourseModuleBean, Integer> {

}
