package info.siddiq.calendarevents.controller;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.siddiq.calendarevents.business.CalendarEventBo;
import info.siddiq.calendarevents.model.CalendarEvent;

/**
 * The CalendarEventController class is a RESTful web service controller. The
 * <code>@RestController</code> annotation informs Spring that each
 * <code>@RequestMapping</code> method returns a <code>@ResponseBody</code>
 * which, by default, contains a ResponseEntity converted into JSON with an
 * associated HTTP status code.
 * 
 * @author M Siddiq
 */

@RestController
public class CalendarEventController {

	/**
     * The Logger for this class.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
	/**
     * The CalendarEventBo business service.
     */
	@Autowired
	CalendarEventBo calendarEventBo;
	
	/**
     * Web service endpoint to FETCH all CalendarEvent entities. The service returns
     * the collection of CalendarEvent entities as JSON.
     * 
     * @return A ResponseEntity containing a Collection of CalendarEvent objects.
     */
    @RequestMapping(value = "/events",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CalendarEvent>> getAllCalEvents() 
    {
    	logger.info(">> getAllCalEvents");	
    	Collection<CalendarEvent> allCalEvents = calendarEventBo.findAll();
    	logger.info("<< getAllCalEvents");
    
    	return new ResponseEntity<Collection<CalendarEvent>>(allCalEvents,
                HttpStatus.OK);
    }
    
    
    /**
     * Web service endpoint to FETCH all CalendarEvent entity by day/week/month followed by
     * 
     * id 1-31 for DAY of the month, 
     * 1-5 for WEEK of the month and 
     * 1-12 for MONTH of the year.
     * 
     * @param dwm A String URL path variable containing the string "day", "week" or "month".
     * @param id A Long URL path variable containing the CalendarEvent primary key
     *        identifier.
     * @return A ResponseEntity containing a single CalendarEvent object, if found,
     *         and a HTTP status code as described in the method comment.
     */
    
    @RequestMapping(value = "/events/{dayWeekMonth}/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CalendarEvent>> getCalEventByDWM(
    		@PathVariable("dayWeekMonth") String dayWeekMonth,
    		@PathVariable("id") int id) {
    	
    	logger.info(">> getCalEventByDWM");
    	Collection<CalendarEvent> allCalEvents = 
    			calendarEventBo.findCalEventsByDayWeekMonth(dayWeekMonth, id);
    	logger.info("<< getCalEventByDWM");
    	
        return new ResponseEntity<Collection<CalendarEvent>>(allCalEvents,
                HttpStatus.OK);
    }
    
    /**
     * Web service endpoint to FETCH a single CalendarEvent entity by primary key
     * identifier. If found, the CalendarEvent is returned as JSON with HTTP status 200.
     * If not found, the service returns an empty response body with HTTP status
     * 404.
     * 
     * @param id A Long URL path variable containing the CalendarEvent primary key
     *        identifier.
     * @return A ResponseEntity containing a single CalendarEvent object, if found,
     *         and a HTTP status code as described in the method comment.
     */
    
    @RequestMapping(value = "/events/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalendarEvent> getCalEvent(
    		@PathVariable("id") Long id) {
    	
    	logger.info(">> getCalEvent");
    	CalendarEvent calEvent = calendarEventBo.findOne(id);
    	
    	 if (calEvent == null) {
             return new ResponseEntity<CalendarEvent>(HttpStatus.NOT_FOUND);
         }
       
    	 logger.info("<< getCalEvent");
    	 return new ResponseEntity<CalendarEvent>(calEvent, HttpStatus.OK);
    }
       

    /**
     * Web service endpoint to CREATE a single CalendarEvent entity. The HTTP request
     * body is expected to contain a CalendarEvent object in JSON format. The
     * CalendarEvent is persisted in the data repository.
     * 
     * If created successfully, the persisted CalendarEvent is returned as JSON with
     * HTTP status 201. If not created successfully, the service returns 
     * an empty response body with HTTP status 500.
     * 
     * @param  The CalendarEvent object to be created.
     * @return A ResponseEntity containing a single CalendarEvent object, if created
     *         successfully, and a HTTP status code as described in the method
     *         comment.
     */
    @RequestMapping(value = "/events",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalendarEvent> createCalEvent(
    		@RequestBody CalendarEvent calendarEvent) {
    	
    	logger.info(">> createCalEvent");
    	CalendarEvent createdCalEvent = calendarEventBo.create(calendarEvent);
    	logger.info("<< createCalEvent");
    	
        return new ResponseEntity<CalendarEvent>(createdCalEvent, HttpStatus.CREATED);
    }
    
    
    /**
     * Web service endpoint to update a single CalendarEvent entity. The HTTP request
     * body is expected to contain a CalendarEvent object in JSON format. The
     * CalendarEvent is updated in the data repository.
     * 
     * If updated successfully, the persisted CalendarEvent is returned as JSON with
     * HTTP status 200. If not found, the service returns an empty response body and 
     * HTTP status 404.
     * 
     * If not updated successfully, the service returns an empty response body
     * with HTTP status 500.
     * 
     * @param  The CalendarEvent object to be updated.
     * @return A ResponseEntity containing a single CalendarEvent object, if updated
     *         successfully, and a HTTP status code as described in the method
     *         comment.
     */
     
    @RequestMapping(value = "/events/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalendarEvent> updateCalEvent(
    		@RequestBody CalendarEvent calendarEvent) {
    	
    	logger.info(">> updateCalEvent");
    	CalendarEvent updatedCalEvent = calendarEventBo.update(calendarEvent);
    	if(updatedCalEvent == null) {
    		return new ResponseEntity<CalendarEvent>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	logger.info("<< updateCalEvent");
    	
        return new ResponseEntity<CalendarEvent>(updatedCalEvent, HttpStatus.OK);
    }
    
    
    /**
     * Web service endpoint to DELETE a single CalendarEvent entity.
     * 
     * If deleted successfully, the service returns an empty response body with
     * HTTP status 204. If not deleted successfully, the service returns an empty
     * response body with HTTP status 500.
     * 
     * @param id A Long URL path variable containing the CalendarEvent primary key
     *        identifier.
     * @return A ResponseEntity with an empty response body and a HTTP status
     *         code as described in the method comment.
     */
    
    @RequestMapping(value = "/events/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<CalendarEvent> deleteCalEvent(@PathVariable("id") Long id) 
    {	
    	logger.info(">> deleteCalEvent");
    	calendarEventBo.delete(id);
    	logger.info("<< deleteCalEvent");
    	
        return new ResponseEntity<CalendarEvent>(HttpStatus.NO_CONTENT);
    }

}


