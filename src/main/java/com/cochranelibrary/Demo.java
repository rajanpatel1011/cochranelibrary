package com.cochranelibrary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.cochranelibrary.model.Cochranelibrary;


public class Demo {

	
	public static void main(String[] args) {
		System.out.println("Please select Topic From the listAllergy & intolerance " + 
				"\n " + 
				"Blood disorders " + 
				"\n " + 
				"Cancer " + 
				"\n " + 
				"Child health " + 
				"\n " + 
				"Complementary & alternative medicine " + 
				"\n " + 
				"Consumer & communication strategies " + 
				"\n " + 
				"Dentistry & oral health " + 
				"\n " + 
				"Developmental, psychosocial & learning problems " + 
				"\n " + 
				"Diagnosis " + 
				"\n " + 
				"Ear, nose & throat " + 
				"\n " + 
				"Effective practice & health systems " + 
				"\n " + 
				"Endocrine & metabolic " + 
				"\n " + 
				"Eyes & vision " + 
				"\n " + 
				"Gastroenterology & hepatology " + 
				"\n " + 
				"Genetic disorders " + 
				"\n" + 
				"Gynaecology " + 
				"\n " + 
				"Health & safety at work " + 
				"\n " + 
				"Health professional education " + 
				"\n " + 
				"Heart & circulation " + 
				"\n " + 
				"Infectious disease " + 
				"\n " + 
				"Kidney disease " + 
				"\n " + 
				"Lungs & airways " + 
				"\n " + 
				"Mental health " + 
				"\n " + 
				"Methodology " + 
				"\n " + 
				"Neonatal care " + 
				"\n " + 
				"Neurology " + 
				"\n " + 
				"Orthopaedics & trauma " + 
				"\n " + 
				"Pain & anaesthesia " + 
				"\n " + 
				"Pregnancy & childbirth " + 
				"\n " + 
				"Public health " + 
				"\n " + 
				"Rheumatology " + 
				"\n " + 
				"Skin disorders " + 
				"\n " + 
				"Tobacco, drugs & alcohol " + 
				"\n " + 
				"Urology " +
				"\n "+ 
				"Wounds\n \n");
		
		
		System.out.println("----:::Enter the given topic name and make file with its review :::-----");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n \n \n ");
	//	String topic = "Allergy & intolerance";
		String topic = scanner.nextLine();
		System.out.println("........................  Reviews for Topic:" + topic+"..........................."); //For Log Console
		String encodeURL = URLEncoder.encode(topic);
		String fileName = "reviews.txt";
		
	
		String url = "https://www.cochranelibrary.com/search?p_p_id=scolarissearchresultsportlet_WAR_scolarissearchresults&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_scolarissearchresultsportlet_WAR_scolarissearchresults_searchText=*&_scolarissearchresultsportlet_WAR_scolarissearchresults_searchType=basic&_scolarissearchresultsportlet_WAR_scolarissearchresults_facetQueryField=topic_id&_scolarissearchresultsportlet_WAR_scolarissearchresults_searchBy=6&_scolarissearchresultsportlet_WAR_scolarissearchresults_facetDisplayName="+encodeURL+"&_scolarissearchresultsportlet_WAR_scolarissearchresults_facetQueryTerm=z1506030924307755598196034641807&_scolarissearchresultsportlet_WAR_scolarissearchresults_facetCategory=Topics";
  			
		try {
		
			URI uri = new URI(url);
		HttpGet requestGet = new HttpGet(uri);
		requestGet.setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 Firefox/26.0 Chrome/68.0.3440.106 Safari/537.36");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.client.protocol.ResponseProcessCookies", "fatal");
		
		HttpClient client = HttpClientBuilder.create()
	            .setDefaultRequestConfig(RequestConfig.custom()
	                    .setCookieSpec(CookieSpecs.STANDARD).build()).build();
		
		
		HttpResponse response = client.execute(requestGet);
		HttpEntity entity = response.getEntity();
		
        String responseString = EntityUtils.toString(entity, "UTF-8");
        
		List<Cochranelibrary> model = new ArrayList<>();
		
        List<String> URLs = new ArrayList<>();
        List<String> Titles = new ArrayList<>();
        List<String> Authors = new ArrayList<>();
        List<String> Dates = new ArrayList<>();
               
        Matcher m = Pattern.compile(
                Pattern.quote("<h3 class=\"result-title\"> <a target=\"_blank\" href=")
                + "(.*?)"
                + Pattern.quote("</a> </h3> <div class=\"search-result-authors\">")
       ).matcher(responseString);
        
        Matcher auth = Pattern.compile(
                Pattern.quote("<div class=\"search-result-authors\"> <div>")
                + "(.*?)"
                + Pattern.quote("</div> </div> <div class=\"search-result-metadata-block\">")
       ).matcher(responseString);
        
        Matcher datePattern = Pattern.compile(
                Pattern.quote("<div class=\"search-result-date\"> <div>")
                + "(.*?)"	
                + Pattern.quote("</div> </div> </div> <div class=\"search-result-metadata-item\">")
       ).matcher(responseString);
      
        while(m.find()){
            String match = m.group(1).substring(1); 
            URLs.add("https://www.cochranelibrary.com"+match.split("\">",-1)[0]);
            Titles.add(match.split("\">",-1)[1]);            
        }
        while(auth.find()) {
        	String a_match = auth.group(1);
        	Authors.add(a_match); 	
        }
        while(datePattern.find()) {
        	String d_match = datePattern.group(1);
        	Dates.add(d_match);  
        }    
       
        
        
        for (int i=0;i<URLs.size();i++) {
         
        	 Cochranelibrary cochranelibrary = new Cochranelibrary();
         	cochranelibrary.setUrl(URLs.get(i));
        	cochranelibrary.setTopic(topic);
        	cochranelibrary.setTitle(Titles.get(i));
        	cochranelibrary.setAuthor(Authors.get(i));
        	cochranelibrary.setDate(Dates.get(i));
        	
        	 model.add(cochranelibrary);
        	
        	 collectReviewsToFile(fileName, model);
       
        }
         
        
        System.out.println(
				  "HTTP Status :" + response.getStatusLine().getStatusCode());
        
        
		}
		catch (Exception e) {
			System.out.println(e);
		}		
		System.out.println("Reviews will be in the text  : -> : reviews.txt");	
		System.out.println( new Date().toString());	
	}
//File 
	public static void collectReviewsToFile(String fileName, List<Cochranelibrary> model) { 
		
		try {
			BufferedWriter out = new BufferedWriter( 
			new FileWriter(fileName)); 
			 out.write("--------------------------------------------"+model.get(0).getTopic()+"---------------------------------------");
			 out.write(new Date().toString());
			  for(Cochranelibrary c:model) {
				  out.newLine();
				  out.newLine();
		    
				  out.write(c.getUrl()+"|"+c.getTopic()+"|"+c.getTitle()
					+"|"+  c.getAuthor() +"|"+ c.getDate());
		      }
			out.close();
		} 
		catch (IOException e) { 
			System.out.println(" IOException :: " + e); 
		} 
	
	}
}