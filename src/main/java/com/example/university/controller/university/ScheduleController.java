package com.example.university.controller.university;

import com.example.university.service.university.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping
    public String schedulePage(Model model) {
        model.addAttribute("schedules", scheduleService.getAllSchedule());
        return "/university/schedule";
    }
}
