package io.Jasper.FirstMavenProject.coronavirustracker.controllers;

import io.Jasper.FirstMavenProject.coronavirustracker.models.LocationStats;
import io.Jasper.FirstMavenProject.coronavirustracker.services.CoronaVirusDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: JasperRui
 * Date: 2022-02-01
 * Time: 18:17
 * Description:
 */

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataServices coronaVirusDataServices;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStatus = coronaVirusDataServices.getAllStatus();
        int totalReportedCases = allStatus.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStatus.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStatus", allStatus);
        model.addAttribute("totalReportedCases", totalReportedCases);
        return "home";
    }
}
