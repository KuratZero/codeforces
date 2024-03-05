package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Notice;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NewNoticePage extends Page {

    private final NoticeService noticeService;

    public NewNoticePage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/newNotice")
    public String newNoticeGet(Model model, HttpSession session) {
        if(checkIsBlocked(session)) {
            setMessage(session, "You are disabled.");
            return "redirect:/";
        }
        if(getUser(session) == null) {
            setMessage(session, "You must be logged in");
            return "redirect:/";
        }
        model.addAttribute("noticeForm", new Notice());
        return "NewNoticePage";
    }

    @PostMapping("/newNotice")
    public String newNoticePost(@Valid @ModelAttribute("noticeForm") Notice notice,
                                BindingResult bindingResult,
                                HttpSession session) {
        if(checkIsBlocked(session)) {
            setMessage(session, "You are disabled.");
            return "redirect:/";
        }
        if(getUser(session) == null) {
            setMessage(session, "You must be logged in");
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            return "NewNoticePage";
        }
        noticeService.createNotice(notice);
        setMessage(session, "Notice published");
        return "redirect:/";
    }
}
