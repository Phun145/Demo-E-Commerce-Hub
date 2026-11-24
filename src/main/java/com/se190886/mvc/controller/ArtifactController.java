package com.se190886.mvc.controller;

import com.se190886.mvc.dto.ArtifactDto;
import com.se190886.mvc.dto.StatusOption;
import com.se190886.mvc.entity.Category;
import com.se190886.mvc.repository.CateRepository;
import com.se190886.mvc.service.ArtifactService;
import com.se190886.mvc.service.CateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ArtifactController {
    @Autowired
    private ArtifactService artifactService;

    @Autowired
    private CateService cateService;

    @GetMapping("/")
    public String search(@RequestParam(required = false) String keyword, Model model) {
        List<ArtifactDto> list = artifactService.search(keyword);
        model.addAttribute("artifactList", list);
        model.addAttribute("keyword", keyword);

        if (list == null || list.isEmpty()) {
            model.addAttribute("notFound", "No records found");
        }

        return "list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        boolean deleted = artifactService.delete(id);

        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete an active artifact.");
        }

        return "redirect:/";
    }

    @GetMapping("/view/{id}")
    public String viewDevice(@PathVariable Integer id, Model model) {
        ArtifactDto artifactDto = artifactService.findById(id);
        model.addAttribute("artifact", artifactDto);
        return "view";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        if (!model.containsAttribute("artifact")) {
            model.addAttribute("artifact", new ArtifactDto());
        }
        return "add";
    }

    @PostMapping("/add")
    public String saveDevice(@Valid @ModelAttribute("device") ArtifactDto artifactDto,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {


        artifactService.create(artifactDto);
        redirectAttributes.addFlashAttribute("successMessage", "Created new artifact successfully");

        return "redirect:/add";
    }

    @ModelAttribute("statusList")
    public List<StatusOption> getAllStatus() {
        return artifactService.getAllStatus();
    }

    @ModelAttribute("cateList")
    public List<Category> getAllCategory() {
        return cateService.findAll();
    }

}
