package io.touhidjisan.springbootprojectcoronavirustracker.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.touhidjisan.springbootprojectcoronavirustracker.model.LocationStats;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStats> allStats = new ArrayList<>();
    
    
    public List<LocationStats> getAllStats() {
		return allStats;
	}

	public void setAllStats(List<LocationStats> allStats) {
		this.allStats = allStats;
	}
	
	



	@PostConstruct
    @Scheduled(cron="* 10 * * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
    	
    	List<LocationStats> newStats = new ArrayList<>();
    	
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader in = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();

            int latestTotalCases = Integer.parseInt(record.get(record.size()-1));
            int prevDayTotalCases = Integer.parseInt(record.get(record.size()-2));
            
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            locationStat.setLatestTotalCases(latestTotalCases);
            locationStat.setDiffPrevDayCases(latestTotalCases-prevDayTotalCases);
            
            newStats.add(locationStat);
        }
        
        this.allStats= newStats;
        

    }

}
