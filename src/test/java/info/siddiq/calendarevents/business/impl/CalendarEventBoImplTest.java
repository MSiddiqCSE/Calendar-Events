package info.siddiq.calendarevents.business.impl;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import info.siddiq.calendarevents.AbstractTest;
import info.siddiq.calendarevents.business.CalendarEventBo;
import info.siddiq.calendarevents.model.CalendarEvent;

@Transactional
public class CalendarEventBoImplTest extends AbstractTest {

    @Autowired
    private CalendarEventBo calendarEventBo;

    @After
    public void tearDown() {
        // clean up after each test method
    }

    @Test
    public void testFindAll() {

    	logger.info(">> testFindAll");	
    	Collection<CalendarEvent> list = calendarEventBo.findAll();

        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertEquals("failure - expected list size", 2, list.size());

        logger.info("<< testFindAll");
    }

    @Test
    public void testFindOne() {

    	logger.info(">> testFindOne");
    	Long id = new Long(1);
    	CalendarEvent entity = calendarEventBo.findOne(id);
    	
        Assert.assertNotNull("failure - expected not null", entity);
        Assert.assertEquals("failure - expected id attribute match", id,
                entity.getId());
        logger.info("<< testFindOne");
    }

    @Test
    public void testFindOneNotFound() {
    	logger.info(">> testFindOneNotFound");
        Long id = Long.MAX_VALUE;
    	CalendarEvent entity = calendarEventBo.findOne(id);
    	
        Assert.assertNull("failure - expected null", entity);
        logger.info("<< testFindOneNotFound");

    }

}
