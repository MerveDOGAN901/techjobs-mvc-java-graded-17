package org.launchcode.techjobsmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;
import java.util.ArrayList;
import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import java.util.HashMap;
/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping ("/results")
    public String listJobsByColumnAndValue(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        String title;

        if ("all".equalsIgnoreCase(searchType)) {
            jobs = JobData.findByValue(searchTerm);
            title = "Jobs containing: " + searchTerm + " (in all categories)";
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            title = "Jobs with " + searchType + ": " + searchTerm;
        }

        model.addAttribute("title", title);
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        return "list-jobs";
    }

}