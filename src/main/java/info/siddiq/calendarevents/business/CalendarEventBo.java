package info.siddiq.calendarevents.business;

import java.util.Collection;

import info.siddiq.calendarevents.model.CalendarEvent;

public interface CalendarEventBo {

	Collection<CalendarEvent> findAll();
	
	Collection<CalendarEvent> findCalEventsByDayWeekMonth(String dayWeekMonth, int id);
	
	CalendarEvent findOne(Long id);
	
	CalendarEvent create(CalendarEvent calendarEvent);
	
	CalendarEvent update(CalendarEvent calendarEvent);
	
	void delete(Long id);
	
}
