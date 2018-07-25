package info.siddiq.calendarevents.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import info.siddiq.calendarevents.model.CalendarEvent;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long>
{
	 @Query("from CalendarEvent ce where to_char(ce.eventDateTime, 'DD') = :day")
	 Collection<CalendarEvent> findCalendarEventsByDay(@Param("day") String day);
	 
	 @Query("from CalendarEvent ce where to_char(ce.eventDateTime, 'W') = :week")
	 Collection<CalendarEvent> findCalendarEventsByWeek(@Param("week") String week);
	 
	 @Query("from CalendarEvent ce where to_char(ce.eventDateTime, 'MM') = :month")
	 Collection<CalendarEvent> findCalendarEventsByMonth(@Param("month") String month);
	 
	 @Query("select ce.id from CalendarEvent ce "
	 		+ " where ce.isReminderSent = false and ce.reminderTime < CURRENT_TIMESTAMP")
	 Collection<Long> findCalendarEventsforReminder();
	 
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query("update CalendarEvent ce set ce.isReminderSent = true where ce.id IN (:id)")
	 void updateCalendarEventsforReminder(@Param("id") Long id);
}
