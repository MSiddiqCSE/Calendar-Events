# How to use/run Calendar-Events program
CRUD operations on Calendar events and send reminder.

User/pass: msiddiq/msiddiq

Sample commands (Using curl):- 
=============================

1. RUN the app:-
---------------

    i. Using maven plugin:- upnzip the source folder and go to the directory where pom.xml is, then run:-    

        mvn spring-boot:run
    
    ii. Using jar file from the above location:-

       java -jar target/calendar-events-1.0.0.jar 
    
    

2. Getting calendar events:-
---------------------------

i.  Getting all events:-
    curl -u msiddiq:msiddiq http://localhost:8080/events/


ii. Getting calendar event by its ID:-
    curl -u msiddiq:msiddiq http://localhost:8080/events/1


iii. Getting by day(1-31)/week(1-5)/month(1-12) :-

    curl -u msiddiq:msiddiq http://localhost:8080/events/day/14

    curl -u msiddiq:msiddiq http://localhost:8080/events/week/2

    curl -u msiddiq:msiddiq http://localhost:8080/events/month/6


3. Create /POST an event:-

    curl -u msiddiq:msiddiq -H "Content-Type: application/json" -X POST -d '{"userid":1,"title":"My NEW calendar event","eventDateTime":1497436376095,"location":"Atlanta, GA","attendees":"M Siddiq; Albert; Robert","reminderTime":1497436316095,"reminderSent":true}' http://localhost:8080/events


4. Update /PUT an event:-

    curl -u msiddiq:msiddiq -H "Content-Type: application/json" -X PUT -d '{"id":1,"userid":1,"title":"My UPDATED Event1","eventDateTime":1497436376095,"location":"Atlanta, GA","attendees":"M Siddiq; Albert; Robert","reminderTime":1497436326095,"reminderSent":true}' http://localhost:8080/events/1

   
5. Delete an event:-

    curl -u msiddiq:msiddiq -X DELETE http://localhost:8080/events/1


8. Automatically check and send reminder ( every 30s in this program)

