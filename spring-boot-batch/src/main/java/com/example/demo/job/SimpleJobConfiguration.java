package com.example.demo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration

public class SimpleJobConfiguration {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private  JobBuilderFactory jobBuilderFactory;

    @Autowired
    private  StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job simpleJob1() {
        return jobBuilderFactory.get("simpleJob1").start(step1()).build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").tasklet(((stepContribution, chunkContext) ->  {
            System.out.println("step1");
            return RepeatStatus.FINISHED;
        })).build();
    }


    @Bean
    public Job simpleJob2() {
        return jobBuilderFactory.get("simpleJob2")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(simpleJob2Step())
                .end()
                .build();
    }

    @Bean Step simpleJob2Step() {
        return stepBuilderFactory.get("simpleJob2")
                .<String, String>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new InterceptingJobExecution();
    }

    @Bean
    public ListItemReader<String> reader() {
        List<String> list = new ArrayList<>();
        list.add("String 1");

        return new ListItemReader<>(list);
    }

    @Bean
    public StringProcessor processor() {
        return new StringProcessor();
    }

    @Bean
    public ItemWriter<String> writer() {
        ItemWriter<String> writer = new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) throws Exception {
                /*
                    writer 처리
                */
            }
        };

        return writer;
    }


    public class StringProcessor implements ItemProcessor<String, String> {
        @Override
        public String process(final String string) throws Exception {
            String text = "processed " + string;

            /*
                process 처리
            */

            return text;
        }
    }

    public class InterceptingJobExecution implements JobExecutionListener {

        @Override
        public void beforeJob(JobExecution jobExecution) {
            log.info("beforeJob");
        }

        @Override
        public void afterJob(JobExecution jobExecution) {
            log.info("afterJob");
        }
    }
}
