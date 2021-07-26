package com.mazbah.springBatchExample.config

import com.mazbah.springBatchExample.model.User
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.LineMapper
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource

@Configuration
@EnableBatchProcessing
class SpringBatchConfig {

    @Bean
    fun job(
        jobBuilderFactory: JobBuilderFactory,
        stepBuilderFactory: StepBuilderFactory,
        itemReader: ItemReader<User>,
        itemProcessor: ItemProcessor<User, User>,
        itemWriter: ItemWriter<User>,
    ): Job {
        val step: Step = stepBuilderFactory["ETL-file-load"]
            .chunk<User, User>(100)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build()
        return jobBuilderFactory["ETL-Load"]
            .incrementer(RunIdIncrementer())
            .start(step)
            .build()
    }

    @Bean
    fun itemReader(): FlatFileItemReader<User> {
        val flatFileItemReader: FlatFileItemReader<User> = FlatFileItemReader<User>()
        flatFileItemReader.setResource(FileSystemResource("src/main/resources/users.csv"))
        flatFileItemReader.setName("CSV-Reader")
        flatFileItemReader.setLinesToSkip(1)
        flatFileItemReader.setLineMapper(lineMapper())
        return flatFileItemReader
    }

    @Bean
    fun lineMapper(): LineMapper<User> {
        val defaultLineMapper: DefaultLineMapper<User> = DefaultLineMapper<User>()
        val lineTokenizer = DelimitedLineTokenizer()
        lineTokenizer.setDelimiter(",")
        lineTokenizer.setStrict(false)
        lineTokenizer.setNames("id", "name", "dept", "salary")

        val fieldSetMapper: BeanWrapperFieldSetMapper<User> = BeanWrapperFieldSetMapper<User>()
        fieldSetMapper.setTargetType(User::class.java)
        defaultLineMapper.setLineTokenizer(lineTokenizer)
        defaultLineMapper.setFieldSetMapper(fieldSetMapper)
        return defaultLineMapper
    }
}