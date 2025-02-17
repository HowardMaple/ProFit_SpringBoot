package com.ProFit.model.dao.usersCRUD;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.ProFit.model.bean.usersBean.Employer_profile;
import com.ProFit.model.bean.usersBean.Users;
import com.ProFit.model.dto.usersDTO.CompanyStatistics;
import java.util.List;

public interface EmpPfRepository extends JpaRepository<Employer_profile, Integer>, JpaSpecificationExecutor<Employer_profile> {

	@Query("SELECT e FROM Employer_profile e WHERE e.user.userEmail LIKE %?1%OR e.companyName LIKE %?2%")
	Page<Employer_profile> findByUserEmailOrCompanyNameContaining(String userEmail, String companyName,
			Pageable pageable);

	Employer_profile findByUserId(int userId);

	@Query("SELECT new com.ProFit.model.dto.usersDTO.CompanyStatistics(e.companyCategory, COUNT(e)) FROM Employer_profile e GROUP BY e.companyCategory")
	List<CompanyStatistics> countByCompanyCategory();

//	@Query("SELECT e FROM Employer_profile e WHERE e.companyAddress LIKE %?1%AND e.companyCategory = ?2")
//	Page<Employer_profile> findByCompanyCategoryAndCompanyAddress(String address, String category, Pageable pageable);

}
