package com.worldoffice.worldoffice.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.worldoffice.worldoffice.dto.ProductosDTO;
import com.worldoffice.worldoffice.entity.Productos;
import com.worldoffice.worldoffice.job.ProductosProcessor;
import com.worldoffice.worldoffice.job.ProductosWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader<ProductosDTO> reader() {
		return new FlatFileItemReaderBuilder<ProductosDTO>()
				.name("ProductosItemReader")
				.resource(new FileSystemResource("C:\\Productos.csv"))
				.delimited()
				.names(new String[] {"nombre", "marca", "precio", "stock", "estado", "descuento"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<ProductosDTO>() {{
					setTargetType(ProductosDTO.class);
				}})
				.linesToSkip(1)
				.build();
	}
	
	@Bean
	public ItemProcessor<ProductosDTO, Productos> proccesor() {
		return new ProductosProcessor();
	}
	
	@Bean
	public ItemWriter<Productos> writer() {
		return new ProductosWriter();
	}
	
	@Bean
	public Step step() {
		return stepBuilderFactory
				.get("step")
				.<ProductosDTO, Productos> chunk(10)
				.reader(reader())
				.processor(proccesor())
				.writer(writer())
				.build();
	}
	
	@Bean
	public Job importarProductosJob() {
		return jobBuilderFactory
				.get("importarProductosJob")
				.incrementer(new RunIdIncrementer())
				.flow(step())
				.end()
				.build();
	}

}
