package com.ProFit.service.courseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ProFit.model.bean.coursesBean.CourseBean;
import com.ProFit.model.dao.coursesCRUD.CoursesRepository;
import com.ProFit.model.dao.coursesCRUD.IHcourseDao;
import com.ProFit.model.dto.coursesDTO.CoursesDTO;

@Service
@Transactional
public class CoursesService implements IcourseService {

	@Autowired
	private IHcourseDao hcourseDao;

	@Autowired
	private CoursesRepository courseRepo;

	@Override
	public CourseBean insertCourse(CourseBean course) {
		return hcourseDao.insertCourse(course);
	}

	@Override
	public boolean deleteCourseById(String courseId) {
		return hcourseDao.deleteCourseById(courseId);
	}

	@Override
	public boolean updateCourseById(CourseBean newCourse) {
		return hcourseDao.updateCourseById(newCourse);
	}

	@Override
	public CoursesDTO searchOneCourseById(String courseId) {
		// CourseBean singleCourseById = hcourseDao.searchOneCourseById(courseId);
		Optional<CourseBean> optional = courseRepo.findById(courseId);

		if (optional.isPresent()) {
			// 使用 DTO 包裝 CourseBean 的數據
			CoursesDTO coursesDTO = new CoursesDTO(optional.get());
			return coursesDTO;
		}
		return null;

	}

	@Override
	public List<CourseBean> searchCourses() {
		return hcourseDao.searchCourses();
	}

	@Override
	public List<CoursesDTO> searchCourses(String courseName, String userName, String status, Integer userId,
			Integer category) {

		List<CourseBean> searchCourses = hcourseDao.searchCourses(courseName, userName, status, userId, category);

		// 將 CourseBean 轉換為 CoursesDTO
		List<CoursesDTO> searchCoursesDTO = searchCourses.stream()
				.map(CoursesDTO::new).collect(Collectors.toList()); // 使用 DTO 的構造函數

		return searchCoursesDTO;
	}

	// 查詢以分頁形式顯示．一頁十筆
	public Page<CoursesDTO> findMsgByPage(Integer pageNumber) {
		Pageable pgb = PageRequest.of(pageNumber - 1, 10, Sort.Direction.DESC, "added");
		Page<CourseBean> coursePage = courseRepo.findAll(pgb);
		Page<CoursesDTO> dtoPage = coursePage.map(CoursesDTO::new); // 使用方法引用進行映射
		return dtoPage;
	}

	@Override
	@Cacheable
	public Page<CoursesDTO> searchCoursesPage(String courseName, String userName, String status, Integer userId,
			Integer category, String sort, String sortBy, Integer pageNumber, Integer pageSize) {

		Sort.Direction direction = Sort.Direction.ASC; // 默認為升序

		if (sort != null && sort.equalsIgnoreCase("DESC")) {
			direction = Sort.Direction.DESC; // 如果是 "DESC" 則設置為降序
		}

		PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, Sort.by(direction, sortBy));

		Page<CourseBean> searchCoursesPage = courseRepo.searchCoursesPage(courseName, userName, status, userId,
				category, pageRequest);

		Page<CoursesDTO> courseDTOPage = searchCoursesPage.map(CoursesDTO::new);

		return courseDTOPage;
	}

	@Override
	public List<Object[]> getCourseCountByCategoryName() {

		List<Object[]> courseCountByCategoryName = courseRepo.getCourseCountByCategoryName();

		return courseCountByCategoryName;
	}

}
