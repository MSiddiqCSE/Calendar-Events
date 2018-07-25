package info.siddiq.calendarevents.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CalendarEvent
{
	@Id
	@GeneratedValue
	private Long id;
	
	private Long userid; //who created the event	
	
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date eventDateTime;
	
	private String location;
	
	private String attendees;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date reminderTime;
	
	private boolean isReminderSent;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAttendees() {
		return attendees;
	}
	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}
	public Date getReminderTime() {
		return reminderTime;
	}
	public void setReminderTime(Date reminderTime) {
		this.reminderTime = reminderTime;
	}
	public boolean isReminderSent() {
		return isReminderSent;
	}
	public void setReminderSent(boolean isReminderSent) {
		this.isReminderSent = isReminderSent;
	}

}
