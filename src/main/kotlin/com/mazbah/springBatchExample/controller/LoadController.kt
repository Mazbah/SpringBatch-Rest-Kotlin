package com.mazbah.springBatchExample.controller

import org.springframework.batch.core.*
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException
import org.springframework.batch.core.repository.JobRestartException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoadController(private var job: Job, private var jobLauncher: JobLauncher) {

    @GetMapping("/load")
    @Throws(JobParametersInvalidException::class,
        JobExecutionAlreadyRunningException::class,
        JobRestartException::class,
        JobInstanceAlreadyCompleteException::class)

    fun load(): BatchStatus {
        val maps: MutableMap<String, JobParameter> = HashMap()
        maps["time"] = JobParameter(System.currentTimeMillis())
        val parameters = JobParameters(maps)
        val jobExecution = jobLauncher.run(job, parameters)
        println("JobExecution: " + jobExecution.status)
        println("Batch is Running...")
        while (jobExecution.isRunning) {
            println("...")
        }
        return jobExecution.status
    }
}
