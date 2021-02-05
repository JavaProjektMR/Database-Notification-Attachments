/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTimeDatabaseConnector;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M.PC
 */
public class Event {
    private int id; 
    private String title;
    private String[] address;
    private Date date;
    private String duration;
    private int[] participantIDs;
    private int notification;
    private int priority;
    private int ownerID;
    
    Event()
    {
        
    }
    
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

    public void print()
    {
        System.out.println(this.id+" "+this.title+" "+this.address[0]+" "+this.address[1]+" "+this.date+" "+this.duration+" "+this.participantIDs[0]+" "+this.notification+" "+this.priority+" "+this.ownerID);
    }
    
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAddress() {
        return address;
    }

    public Date getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public int[] getParticipantIDs() {
        return participantIDs;
    }

    public int getNotification() {
        return notification;
    }

    public int getPriority() {
        return priority;
    }

    public int getOwnerID() {
        return ownerID;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setParticipantIDs(int[] participantIDs) {
        this.participantIDs = participantIDs;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
    /*
   public void printEvent()
   {
       System.out.println(this.name+this.date+this.id);
   }
    
    public static Event[] loadEvents(String username)
    {
        File userEvent = new File("Events_"+username+".txt");
        if(!userEvent.exists())
        {
            try {
                userEvent.createNewFile();
                FileWriter createNew = new FileWriter("Events_"+username+".txt");
                createNew.write("1\n");
                createNew.write("No entries~ ~ § ~ § § § ~ ~3~0\n");
                createNew.close();
            } catch (IOException ex) {
                Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Scanner load;
        Event[] allfakeEvents;
        try {
            load = new Scanner(userEvent);
        String Size = load.nextLine();
        int size = Integer.parseInt(Size);    
        Event[] allEvents = new Event[size];
        for(int i = 0;i<allEvents.length;i++)
        {
            allEvents[i] = new Event();
            
            
            String[] Event = load.nextLine().split("~");
            
            allEvents[i].setName(Event[0]);
            allEvents[i].setDate(Event[1]);
            String time[] = Event[2].split("§");
            allEvents[i].setTime(time);
            String location[] = Event[3].split("§");
            allEvents[i].setLocation(location);
            String[] members = Event[4].split("§");
            allEvents[i].setMembers(members);
            allEvents[i].setPriority(Event[5]);
            allEvents[i].setID(Event[6]);
        }
        load.close();
        return allEvents;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allfakeEvents = new Event[1];
    }
    
    public static void storeEvents(Event[] allEvents, String username)
    {
        System.out.println("Start Store");
        File userEvent = new File("Events_"+username+".txt");
        if(!userEvent.exists())
        {
            try {
                userEvent.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try 
        {
            FileWriter storeEvents = new FileWriter(userEvent);
            storeEvents.write(allEvents.length+"\n");
            for(int i = 0;i<allEvents.length;i++)
            {
                String[] time = allEvents[i].getTime();
                String[] location = allEvents[i].getLoacation();
                String[] members = allEvents[i].getMembers();
                String allMembers = "";
                for(int j = 0; j<members.length;j++)
                {
                    if(j==0)
                        allMembers = members[0];
                    else
                        allMembers = allMembers +"§"+ members[j];
                }
                
                storeEvents.write(allEvents[i].getName()+"~"
                        +allEvents[i].date+"~"
                        +time[0]+"§"+time[1]+"~"
                        +location[0]+"§"+location[1]+"§"
                        +location[2]+"§"+location[3]+"~"
                        +allMembers+"~"
                        +allEvents[i].getPriority() +"~"
                        +(i+1) + "\n");
            }
            storeEvents.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean checkDefaultEvent(String username)
    {
        Event[] allEvents = loadEvents(username);
        if(allEvents.length == 1)
        {
            for(int i = 0;i<allEvents.length;i++)
            {
                if(Integer.parseInt(allEvents[i].getID())==0)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void addEvent(Event newEvent, String username)
    {
        if(checkDefaultEvent(username))
        {
            Event[] allEvents = new Event[1];
            allEvents[0] = newEvent;
            storeEvents(allEvents, username);
        }
        else
        {
            Event[] allEvents = loadEvents(username);
            Event[] allEventsPlus = new Event[allEvents.length+1];
            for(int i = 0;i<allEvents.length;i++)
            {
                allEventsPlus[i] = allEvents[i];
            }
            allEventsPlus[allEvents.length] = newEvent;
            storeEvents(allEventsPlus, username); 
        }
    }
    
    public static void deletEvent(String ID, String username)
    {
        Event[] allEvents = loadEvents(username);
        if(allEvents.length == 1)
        {
            File delete = new File("Events_"+username+".txt");
            delete.delete();
            Event[] resetEvent = loadEvents(username);
        }
        else
        {
            Event[] allEventsMinus = new Event[allEvents.length-1];
            for(int i = 0, j = 0;i<allEvents.length;i++,j++)
            {
                if(allEvents[i].getID().equals(ID))
                {
                    j--;
                }
                else
                {
                    allEventsMinus[j] = allEvents[i];
                }
            }
            storeEvents(allEventsMinus, username);
            
        }
    }*/

    
}
