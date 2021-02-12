/**
 * Create by Marlon Ringel, 1314614
 * Java WiSe 2020/21 Project
 * 
 */
package OnTimeDatabaseConnector;
/**
 * Imports the java.util.date class to store the event date as a date object.
 */
import java.util.Date;


/**
 * This class is a container to store event data.
 * Also its provieds seter and geter methodes and constructors.
 * 
 */
public class Event {
    
    /**
     * Contains the ID the event has in the database.
     * This will be assigned by the database upon event creation.
     */
    private int id; 
    /**
     * Contains the title of the event.
     */
    private String title;
    /**
     * Contains the street and house number of an event and also the city. 
     */
    private String[] address;
    /**
     * Contains the date on which the event will start.
     */
    private Date date;
    /**
     * Contains the duration of an event.
     */
    private String duration;
    /**
     * Contains the account ID'S of the participants of the event.
     */
    private int[] participantIDs;
    
    /**
     * Contains the notification delay indicator. 
     * 1 = one week
     * 2 = 3 days
     * 3 = one hour 
     * 4 = ten minutes
     * every other number triggers the defoult case, which means no delay.
     */
    private int notification;
    /**
     * Contains the priority indecator of the event corresponding to the notification.
     * 1 = low  
     * 2 = medium
     * 3 = high
     * every other number triggers the defoult case, which means priority  = none. 
     */
    private int priority;
    /**
     * Contains the ID of the user account who created the event.
     */
    private int ownerID;
 
    /**
     * Empty constructor
     */
    Event()
    {
        
    }
    
    /**
     * Constructor to use upon event creation when 
     * the ID isn't assigned by the database yet.
     * @param title
     * @param address
     * @param date
     * @param duration
     * @param participantIDs
     * @param notification
     * @param priority
     * @param ownerID 
     */
    public Event(String title, String[] address, Date date, String duration, int[] participantIDs, int notification, int priority, int ownerID) {
        this.id = 0;
        this.title = title;
        this.address = address;
        this.date = date;
        this.duration = duration;
        this.participantIDs = participantIDs;
        this.notification = notification;
        this.priority = priority;
        this.ownerID = ownerID;
    }
    
    /**
     * Construcktor to use to load(or copy) a event from the database.
     * @param id
     * @param title
     * @param address
     * @param date
     * @param duration
     * @param participantIDs
     * @param notification
     * @param priority
     * @param ownerID 
     */
    public Event(int id, String title, String[] address, Date date, String duration, int[] participantIDs, int notification, int priority, int ownerID) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.date = date;
        this.duration = duration;
        this.participantIDs = participantIDs;
        this.notification = notification;
        this.priority = priority;
        this.ownerID = ownerID;
    }
    
    /**
     * Prints all contants of a Event object to system out.
     */
    public void print()
    {
        System.out.println(this.id+" "+this.title+" "+this.address[0]+" "+this.address[1]+" "+this.date+" "+this.duration+" "+this.participantIDs[0]+" "+this.notification+" "+this.priority+" "+this.ownerID);
    }
    
    /**
     * Returns the value of the ID attribute.
     * @return 
     */
    public int getId() {
        return id;
    }
    
    /**
     * Returns the value of the title attribute.
     * @return 
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Returns the value of the address attribute.
     * @return 
     */
    public String[] getAddress() {
        return address;
    }
    
    /**
     * Returns the value of the date attribute.
     * @return 
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * Returns the value of the duration attribute.
     * @return 
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Returns the value of the participantIDs attribute.
     * @return 
     */
    public int[] getParticipantIDs() {
        return participantIDs;
    }

    /**
     * Returns the value of the notification attribute.
     * @return 
     */
    public int getNotification() {
        return notification;
    }

    /**
     * Returns the value of the priority attribute.
     * @return 
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns the value of the ownerID attribute.
     * @return 
     */
    public int getOwnerID() {
        return ownerID;
    }
    
    /**
     * Stores the given input into the id attribute.
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Stores the given input into the title attribute.
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Stores the given input into the address attribute.
     * @param address 
     */
    public void setAddress(String[] address) {
        this.address = address;
    }

    /**
     * Stores the given input into the date attribute.
     * @param date 
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Stores the given input into the duration attribute.
     * @param duration 
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Stores the given input into the participantIDs attribute.
     * @param participantIDs 
     */
    public void setParticipantIDs(int[] participantIDs) {
        this.participantIDs = participantIDs;
    }

    /**
     * Stores the given input into the notification attribute.
     * @param notification 
     */
    public void setNotification(int notification) {
        this.notification = notification;
    }

    /**
     * Stores the given input into the priority attribute.
     * @param priority 
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Stores the given input into the ownerID attribute.
     * @param ownerID 
     */
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}
