package info.siddiq.calendarevents.batch;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import info.siddiq.calendarevents.repository.CalendarEventRepository;

@Component
public class CalEventReminder {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CalendarEventRepository calendarEventRepository;

	@Scheduled(cron = "0,30 * * * * *") //Checking reminders every 30 seconds
	public void sendCalEventReminder() 
	{
		logger.info(">> sendCalEventReminder. ");
		Collection<Long> idLIst = calendarEventRepository.findCalendarEventsforReminder();
		logger.info("There are {} events to start", idLIst.size());
		
		for (Long id: idLIst) {
			calendarEventRepository.updateCalendarEventsforReminder(id);
			logger.info("<< sent Calendar Event Reminder for id " + id);
		}			
	}

}
