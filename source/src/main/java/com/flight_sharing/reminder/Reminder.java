package com.flight_sharing.reminder;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Reminder {

	public static void start() throws SchedulerException, InterruptedException {
		// collect the next day flight list
		JobDetail jobA = JobBuilder.newJob(FlightList.class).withIdentity("getFlights", "group1").build();
		//
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("getFlightsTrigger", "group1")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(12).repeatForever()).build();

		// send email to the pilot and passenger
		JobDetail jobB = JobBuilder.newJob(SendEmail.class).withIdentity("sendEmail", "group1").build();

		Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("sendEmailTrigger", "group1")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).repeatForever()).build();

		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobA, trigger);
		// pause 5s
		Thread.sleep(5000);
		scheduler.scheduleJob(jobB, trigger2);
	}
}
