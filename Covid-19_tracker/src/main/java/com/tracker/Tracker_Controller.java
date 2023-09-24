package com.tracker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tracker.Services.Locationservice;
import com.tracker.entity.LocationEntity;

@Controller
public class Tracker_Controller {
	
	
		@Autowired
		private Locationservice locservice;


		public Tracker_Controller() {
			System.out.println("controller started");
		}
		
		@GetMapping("/")
		
		public String homepage(Model m) {
		List<LocationEntity> l=locservice.getdata();
		int totalDeathsReported=l.stream().mapToInt(stat->stat.getLatesttotal()).sum();
		m.addAttribute("total",totalDeathsReported);
		m.addAttribute("data",l);
		return "index";
		}
		
		@GetMapping("/viewchart")
		public String viewchart() {
			
			return "ViewChart";
		}
		@PostMapping("/compare")
		public String compre( @RequestParam("out")String data ,Model m ){
			if(data.isEmpty()){
				return "redirect:/viewchart";
			}
			else {
			String ar[]=data.split(",");
			List<Integer> l = new ArrayList<Integer>();
			for(String i:ar) {
			String[] s=i.split("-");
			int num=Integer.parseInt(s[0]);
			l.add(num);
			}
			List<LocationEntity> list=	locservice.getdatabyid(l);
	
			m.addAttribute("data", list);
			return "compare";
			}
		}
}
