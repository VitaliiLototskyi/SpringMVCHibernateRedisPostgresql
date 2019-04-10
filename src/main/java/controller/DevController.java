package controller;

import model.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import services.DeveloperService;


@Controller
public class DevController {
    @Autowired
    public DeveloperService developerService;

    @GetMapping(value = "/")
    public String index() {
        return "/index";
    }

    @GetMapping(value = "/actionWithDB")
    public String actionWithDB(@ModelAttribute("mvc-dispatcher") Developer developer, ModelMap modelMap) {
        for (Developer d : developer.generateDevs(50)) {
            modelMap.addAttribute("developer", d);
            developerService.saveDeveloper(d);
        }
        modelMap.addAttribute("devList", developerService.findAllDevelopers());
        return "resultDB";
    }

    @GetMapping(value = "/showAll")
    public String showAll(@ModelAttribute("mvc-dispatcher") Developer developer, ModelMap modelMap) {
        modelMap.addAttribute("devList", developerService.findAllDevelopers());

        return "resultDB";
    }

    @GetMapping(value = "/redisSave")
    public String redisSave(@ModelAttribute("mvc-dispatcher") Developer developer, ModelMap modelMap) {
        for (Developer d : developer.generateDevs(50)) {
            modelMap.addAttribute("developer", d);
            developerService.saveDeveloperToRedis(d);
        }
        modelMap.addAttribute("devListFromRedis", developerService.getAllFromRedis("developer"));
        return "resultRedis";
    }

    @GetMapping(value = "/showAllFromRedis")
    public String showAllFromRedis(@ModelAttribute("mvc-dispatcher") Developer developer, ModelMap modelMap) {
        //show only one record with key: developer
        modelMap.addAttribute("devListFromRedis", developerService.getAllFromRedis("developer"));

        return "resultRedis";
    }


}
