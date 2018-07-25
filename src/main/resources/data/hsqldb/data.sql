INSERT INTO CalendarUser (name, username, password)
VALUES ('Muhammad Siddiq', 'msiddiq', 'msiddiq');


INSERT INTO CalendarEvent (userid, title, eventDateTime, 
location, attendees, reminderTime) 
VALUES (1, 'My 1st calendar event', 
       CURRENT_TIMESTAMP + interval '2' minute, --my current event time      
       'Atlanta, GA', 'M Siddiq; Albert; Robert' 
       , CURRENT_TIMESTAMP + interval '1' minute --set reminder 1 minute before
);


INSERT INTO CalendarEvent (userid, title, eventDateTime, 
location, attendees, reminderTime) 
VALUES (1, 'My 2nd calendar event', 
       CURRENT_TIMESTAMP + interval '3' minute,   
       'Atlanta, GA', 'M Siddiq; Robinson Crusso; Michael Bubble' 
       , CURRENT_TIMESTAMP + interval '2' minute
);
