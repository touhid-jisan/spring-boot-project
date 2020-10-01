package io.touhidjisan.springbootprojectcoronavirustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.touhidjisan.springbootprojectcoronavirustracker.model.LocationStats;
import io.touhidjisan.springbootprojectcoronavirustracker.services.CoronaVirusDataService;

@Controller
public class HomeController {
	
	CoronaVirusDataService coronaVirusDataService;
	
	@Autowired
	public HomeController(CoronaVirusDataService theCoronaVirusDataService) {
		coronaVirusDataService = theCoronaVirusDataService;
	}

	@GetMapping("/")
	public String home(Model model) {
		
		List<LocationStats> allStats  = coronaVirusDataService.getAllStats();
		int totalCase = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalCaseDiffFromPrevDay = allStats.stream().mapToInt(stat -> stat.getDiffPrevDayCases()).sum();
		model.addAttribute("allStats", allStats);
		model.addAttribute("totalCase", totalCase);
		model.addAttribute("totalCaseDiffFromPrevDay", totalCaseDiffFromPrevDay);
		return "home";
	}
}
