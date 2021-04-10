package com.worldoffice.worldoffice.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.worldoffice.worldoffice.util.Util;

@Configuration
@EnableScheduling
public class SchedulingConfiguration extends Util {
	
	private static final Logger LOG = LogManager.getLogger(SchedulingConfiguration.class);

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Scheduled(fixedRate = 60000)
	public void scheduleFixedRateTask() {
		try {
			FileSystemResource fileCSV = new FileSystemResource("C:\\Productos.csv");
			
			if(fileCSV.exists()) {
				JobParameters jobParameters  = new JobParametersBuilder()
						.addString("Job ejecutado el: ", convertDate(System.currentTimeMillis(), "dd/MM/yyyy hh:mm:ss"))
						.toJobParameters();
				
				jobLauncher.run(job, jobParameters);
			} else {
				LOG.warn("Error al ejecutar en JobBatch asegurate que el archivo C:\\Productos.csv exista");
			}
		} catch (Exception e) {
			LOG.warn("No existe ningún job para ejecutar");
		}
	}

}
