package info.siddiq.calendarevents.business.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.siddiq.calendarevents.model.CalendarEvent;
import info.siddiq.calendarevents.repository.CalendarEventRepository;
import info.siddiq.calendarevents.business.CalendarEventBo;

@Service
public class CalendarEventBoImpl implements CalendarEventBo
{
	@Autowired
	CalendarEventRepository calendarEventRepository;

	@Override
	public Collection<CalendarEvent> findAll() {
		Collection<CalendarEvent> events = calendarEventRepository.findAll();
		return events;
	}
	
	@Override
	public Collection<CalendarEvent> findCalEventsByDayWeekMonth(String dayWeekMonth, int id) 
	{	
		dayWeekMonth = dayWeekMonth.toLowerCase();
		Collection<CalendarEvent> calendarEventList = null;
		String strId;
		
		switch(dayWeekMonth) {
			case "day":
				strId = String.format("%02d", id);
				calendarEventList = calendarEventRepository.findCalendarEventsByDay(strId);
				break;
			case "week":
				strId = String.format("%1d", id);
				calendarEventList = calendarEventRepository.findCalendarEventsByWeek(strId);
				break;
			case "month":
				strId = String.format("%02d", id);
				calendarEventList = calendarEventRepository.findCalendarEventsByMonth(strId);
				break;
			default: 
				return null;
		}
	
		return calendarEventList;
	}

	@Override
	public CalendarEvent findOne(Long id) {
		CalendarEvent event = calendarEventRepository.findOne(id);
		return event;
	}

	@Override
	public CalendarEvent create(CalendarEvent calendarEvent) {
		if(calendarEvent.getId() != null)
		{
			//Cannot create CalendarEvent with specified id
			return null;
		}
		CalendarEvent event = calendarEventRepository.save(calendarEvent);
		return event;
	}

	@Override
	public CalendarEvent update(CalendarEvent calendarEvent) {
		CalendarEvent persistedCalendarEvent = findOne(calendarEvent.getId());
		if(persistedCalendarEvent == null){
			//cannot update CalendarEvent that hasn't been persisted yet
			return null;
		}
		
		CalendarEvent event = calendarEventRepository.save(calendarEvent);
		return event;
	}

	@Override
	public void delete(Long id) {
		calendarEventRepository.delete(id);		
	}
	
}
