package com.tracker.Services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tracker.entity.LocationEntity;
import com.tracker.repo.Locations;



@Service
public class Locationservice {
	
	@Autowired
	private Locations loc;
	
	
		
	public Locationservice() {
		System.out.println("service class");
		System.out.println("need internet");
	}
	
	private static String virus_data_url="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	@PostConstruct
	@ Scheduled(cron="* * * 1 * *")
	public void fetchdata() throws IOException, InterruptedException {
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest req=HttpRequest.newBuilder().uri(URI.create(virus_data_url)).build();
		HttpResponse<String> res=client.send(req, HttpResponse.BodyHandlers.ofString());
	
		StringReader r=new StringReader(res.body());
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> record=CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(r);
		List<LocationEntity> newstate=new ArrayList<LocationEntity>();
		for(CSVRecord allrecord:record) {
			if(loc. existsByCountryAndState(allrecord.get("Country/Region"),allrecord.get("Province/State"))){
			Optional<LocationEntity> l=	loc.findByCountryAndState(allrecord.get("Country/Region"),allrecord.get("Province/State"));		
			LocationEntity update=l.get();
			update.setState(allrecord.get("Province/State"));
			update.setCountry(allrecord.get("Country/Region"));
		    int latestCase=Integer.parseInt(allrecord.get(allrecord.size()-1));
			int PrevCase=Integer.parseInt(allrecord.get(allrecord.size()-2));
			update.setLatesttotal(latestCase);
			update.setDifferfromprevay(latestCase-PrevCase);
			loc.save(update);
			}
			else {
			LocationEntity le = new LocationEntity();
			le.setState(allrecord.get("Province/State"));
			le.setCountry(allrecord.get("Country/Region"));
		    int latestCase=Integer.parseInt(allrecord.get(allrecord.size()-1));
			int PrevCase=Integer.parseInt(allrecord.get(allrecord.size()-2));
			le.setLatesttotal(latestCase);
			le.setDifferfromprevay(latestCase-PrevCase);
			System.out.println(le);
			newstate.add(le);
			}
		}
		
		loc.saveAll(newstate);
	}

	public List<LocationEntity> getdata() {
		List<LocationEntity> l=loc.findAll();
		return l;
	}
	
	public List<LocationEntity>  getdatabyid(List<Integer> l) {
		List<LocationEntity> data=new ArrayList<LocationEntity>();
		for(Integer i:l) {
		Optional<LocationEntity> out=	loc.findById(i);
		data.add(out.get());
		
		}
		return data;
	}


}
