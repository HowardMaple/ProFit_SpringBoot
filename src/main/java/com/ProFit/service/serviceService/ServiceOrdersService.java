package com.ProFit.service.serviceService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ProFit.model.bean.servicesBean.ServiceOrderBean;
import com.ProFit.model.dao.servicesCRUD.ServiceOrderRepository;
import com.ProFit.model.dto.servicesDTO.ServiceOrdersDTO;

@Service
public class ServiceOrdersService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepo;

    // 查詢 所有服務訂單 回傳page
    public Page<ServiceOrderBean> findAllByPage(Integer pageNumber) {
        Pageable pgb = PageRequest.of(pageNumber - 1, 10, Sort.Direction.DESC, "added");

        Page<ServiceOrderBean> serviceOrderPage = serviceOrderRepo.findAll(pgb);
        System.out.println(serviceOrderPage);

        // Page<CourseOrderDTO> dtoPage = courseOrderPage.map(CourseOrderDTO::new);

        return serviceOrderPage;
    }

    public List<ServiceOrdersDTO> getAllServiceOrdersAsDTO() {
        List<ServiceOrderBean> serviceOrders = serviceOrderRepo.findAll();
        return serviceOrders.stream()
                .map(ServiceOrdersDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
