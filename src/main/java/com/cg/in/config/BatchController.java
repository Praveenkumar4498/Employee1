package com.cg.in.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;

@RestController
@RequestMapping("/batch")
public class BatchController {
	
	
	 @Autowired
	 @Qualifier("h2ToCsvJob")
	 private Job h2ToCsvJob;
	 
	
	 @Autowired
	 @Qualifier("csvToH2Job")
	 private Job csvToH2Job;
	   

	    @Autowired
	    private JobLauncher jobLauncher;

	   

	   
	    @GetMapping("/startCsvtoh2Job")
	    public BatchStatus startCsvToH2Job() throws Exception {
	        JobParameters jobParameters = new JobParametersBuilder()
	                .addLong("time", System.currentTimeMillis())
	                .toJobParameters();

	       
	        JobExecution jobExecution = jobLauncher.run(csvToH2Job, jobParameters);
	       
	        return jobExecution.getStatus();
	    }
	    
	    @GetMapping("/startH2ToCsvJob")
	    public BatchStatus startH2ToCsvJob() throws Exception {
	        JobParameters jobParameters = new JobParametersBuilder()
	                .addLong("time", System.currentTimeMillis())
	                .toJobParameters();

	        JobExecution jobExecution = jobLauncher.run(h2ToCsvJob, jobParameters);
	        
	       
	        return jobExecution.getStatus();
	    }


}
