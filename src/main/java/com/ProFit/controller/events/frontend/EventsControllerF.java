package com.ProFit.controller.events.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ProFit.model.bean.eventsBean.EventHostBean;
import com.ProFit.model.bean.eventsBean.EventOrderBean;
import com.ProFit.model.bean.eventsBean.EventsBean;
import com.ProFit.model.bean.usersBean.Users;
import com.ProFit.model.dto.coursesDTO.CoursesDTO;
import com.ProFit.model.dto.eventsDTO.EventHostDTO;
import com.ProFit.model.dto.eventsDTO.EventOrderDTO;
import com.ProFit.model.dto.eventsDTO.EventsDTO;
import com.ProFit.model.dto.majorsDTO.MajorDTO;
import com.ProFit.model.dto.usersDTO.UsersDTO;
import com.ProFit.service.eventService.IEventHostService;
import com.ProFit.service.eventService.IEventOrderService;
import com.ProFit.service.eventService.IEventsService;
import com.ProFit.service.majorService.IMajorService;
import com.ProFit.service.userService.IUserService;
import com.ProFit.service.userService.UserService;

import jakarta.servlet.http.HttpSession;

import com.ProFit.service.majorService.IMajorCategoryService;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/f/events")
public class EventsControllerF {

    @Autowired
    private IEventsService eventsService;
    
    @Autowired
    private IEventHostService eventHostService;
    
    @Autowired
    private IEventOrderService eventOrderService;
    
    @Autowired
    private IUserService userService;

    @Autowired
    private IMajorService majorService;

    @Autowired
    private IMajorCategoryService majorCategoryService;

    // 主頁面，列出所有活動
    @GetMapping
    public String listEvents() {
        return "eventsVIEW/frontend/eventsF";
    }

    // 新增活動
    @GetMapping("/new")
    public String newEvent(Model model) {
        EventsDTO event = new EventsDTO();
//        List<MajorDTO> majors = majorService.findAllMajors();
//        model.addAttribute("majors", majors);
        model.addAttribute("event", event);
        return "eventsVIEW/frontend/eventsFormF";
    }

//    // 編輯活動
//    @GetMapping("/edit")
//    public String editEvent(@RequestParam String eventId, Model model) {
//        EventsBean eventBean = eventsService.selectEventById(eventId);
//        EventsDTO event = eventsService.convertToDTO(eventBean);
//        List<MajorDTO> majors = majorService.findAllMajors();
//        model.addAttribute("majors", majors);
//        model.addAttribute("event", event);
//        return "eventsVIEW/frontend/eventsFormF";
//    }

    // 檢視活動
    @GetMapping("/view")
    public String viewEvent(@RequestParam String eventId, HttpSession session, Model model) {
    	UsersDTO currentUser = (UsersDTO) session.getAttribute("CurrentUser");
        EventsBean eventBean = eventsService.selectEventById(eventId);
        EventsDTO event = eventsService.convertToDTO(eventBean);
       
        List<EventHostBean> eventHostList = eventHostService.selectByEvent(eventId);
        List<EventHostDTO> eventHost = eventHostList.stream().map(eventHostService::convertToDTO)
        		.collect(Collectors.toList());
        
        model.addAttribute("event", event);
        model.addAttribute("eventHosts", eventHost);
        return "eventsVIEW/frontend/eventsInfoF";
    }

    // 搜尋活動
    @GetMapping("/search")
    @ResponseBody
    public List<EventsDTO> searchEvents(@RequestParam(required = false) String eventName,
            @RequestParam(required = false) Integer eventStatus,
            @RequestParam(required = false) Integer eventCategory,
            @RequestParam(required = false) Integer eventMajor) {
        List<EventsBean> eventsList;

        if (eventName != null && !eventName.isEmpty()) {
            eventsList = eventsService.selectEventByName(eventName);
        } else if (eventStatus != null) {
            eventsList = eventsService.selectEventByStatus(eventStatus);
        } else if (eventCategory != null) {
            eventsList = eventsService.selectEventByCategory(eventCategory);
        } else if (eventMajor != null) {
            eventsList = eventsService.selectEventByMajor(eventMajor);
        } else {
            eventsList = eventsService.selectAllEvents();
        }

        List<EventsDTO> events = eventsList.stream().map(eventsService::convertToDTO).collect(Collectors.toList());
        return events;
    }

//    // 刪除活動
//    @GetMapping("/delete")
//    public String deleteEvent(@RequestParam String eventId) {
//        eventsService.deleteEvent(eventId);
//        return "redirect:/f/events";
//    }

    // 儲存活動
    @PostMapping("/save")
    public ResponseEntity<String> saveEvent(@RequestBody EventsDTO eventsDTO) {
        EventsBean newEvent = eventsService.convertToBean(eventsDTO);
        Users host = userService.getUserInfoByID(eventsDTO.getHostId());
        EventsBean event = eventsService.selectEventById(eventsService.saveEvent(newEvent));
        eventHostService.saveEventHost(new EventHostBean(event, host));
        
        return ResponseEntity.ok("/ProFit/f/events");
    }
    
}
