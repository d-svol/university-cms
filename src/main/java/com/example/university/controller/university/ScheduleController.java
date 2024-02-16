package com.example.university.controller.university;

import com.example.university.dto.ScheduleDTO;
import com.example.university.model.university.Schedule;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.GroupRepository;
import com.example.university.repository.ProfessorRepository;
import com.example.university.service.university.ScheduleService;
import com.example.university.service.user.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;
    private final ProfessorService professorService;

    @GetMapping
    public String schedulePage(Model model) {
        model.addAttribute("scheduleDTO", new ScheduleDTO());
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("professors", professorService.getAllProfessorDTOs());
        model.addAttribute("schedulesDTO", scheduleService.getAllScheduleDTO());
        return "university/schedule";
    }

    @PostMapping("delete")
    public String deleteUser(Long scheduleId, RedirectAttributes redirectAttributes) {
        scheduleService.deleteScheduleById(scheduleId);
        redirectAttributes.addFlashAttribute("successMessage", "Schedule successfully delete.");
        return "redirect:/schedule";
    }

    @PostMapping("/edit")
    public String editSchedule(@ModelAttribute("scheduleDTO") ScheduleDTO scheduleDTO, RedirectAttributes redirectAttributes) {
        scheduleService.updateSchedule(scheduleDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Schedule successfully edit.");
        return "redirect:/schedule";
    }

    @PostMapping("/add")
    public String addSchedule(@ModelAttribute("schedule") Schedule schedule, RedirectAttributes redirectAttributes) {
        scheduleService.addSchedule(schedule);
        redirectAttributes.addFlashAttribute("successMessage", "Schedule successfully add.");
        return "redirect:/schedule";
    }
}
