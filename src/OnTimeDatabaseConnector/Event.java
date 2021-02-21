/**
 * Create by Marlon Ringel, 1314614
 * Java WiSe 2020/21 Project
 * 
 */
package OnTimeDatabaseConnector;
/**
 * Imports the java.util.date class to store the event date as a date object.
 */

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


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
     * Contains the path strings to the attachment files. 
     * If no attachemnt exists it's a string array whit lenght == 0;
     * For excemple: String[] attachments = new String[0];
     *
     */
    private String[] attachments;
 
    /**
     * Empty constructor
     */
    Event() {
        
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
        this.attachments = new String[0];
    }
    
    /**
     * Constructor to use upon event creation when 
     * the ID isn't assigned by the database 
     * yet and attachments exist.
     * @param title
     * @param address
     * @param date
     * @param duration
     * @param participantIDs
     * @param notification
     * @param priority
     * @param ownerID
     * @param attachments 
     */
    public Event(String title, String[] address, Date date, String duration, int[] participantIDs, int notification, int priority, int ownerID, String[] attachments) {
        this.id = 0;
        this.title = title;
        this.address = address;
        this.date = date;
        this.duration = duration;
        this.participantIDs = participantIDs;
        this.notification = notification;
        this.priority = priority;
        this.ownerID = ownerID;
        this.attachments = attachments;
        this.attachments = new String[0];
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
     * Construcktor to load(or copy) a event from the database 
     * and the event contains attachments
     * @param id
     * @param title
     * @param address
     * @param date
     * @param duration
     * @param participantIDs
     * @param notification
     * @param priority
     * @param ownerID
     * @param attachments 
     */
    public Event(int id, String title, String[] address, Date date, String duration, int[] participantIDs, int notification, int priority, int ownerID, String[] attachments) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.date = date;
        this.duration = duration;
        this.participantIDs = participantIDs;
        this.notification = notification;
        this.priority = priority;
        this.ownerID = ownerID;
        this.attachments = attachments;
    }
    
    /**
     * Prints all contants of a Event object to system out.
     */
    public void print()  {
        String attachment = "";
        for(int i = 0;i<this.attachments.length;i++)
        {
            if(attachment.equals(""))
            {
                attachment = this.attachments[i];
            }
            else
            {
                attachment = attachment + "\n" + this.attachments[i];
            }
        }
        System.out.println(this.id+" "+this.title+" "+this.address[0]+" "+this.address[1]+" "+this.date+" "+this.duration+" "+this.participantIDs[0]+" "+this.notification+" "+this.priority+" "+this.ownerID+" "+attachment);
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
     * Returns the value of the attachments attribute.
     * @return 
     */
    public String[] getAttachments() {
        return this.attachments;
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
    
    /**
     * Stores the given input into the attachments attribute.
     * @param attachments 
     */
    public void setAttachments(String[] attachments) {
        this.attachments = attachments;
    }
    
    
    /**
     * Adds the path of an attachment to the event object.
     * @param path 
     */
    public void addAttachmentToEvent(String path) /*Done*/
    {
        String[] temp = null;
        if(this.attachments.length == 0)
        {
            temp = new String[1];
            temp[0] = path;
        }
        else
        {
            temp = new String[this.attachments.length+1];
            for(int i = 0;i<this.attachments.length;i++)
            {
                temp[i] = this.attachments[i];
            }
            temp[temp.length-1] = path;
        }
        this.attachments = temp;
    }
    /**
     * Deletes the path of an Attachment from the event object.
     * Also deletes the attachment file.
     * @param path 
     */
    public void deleteAttachmentFromEvent(String path) /*Done*/
    {
        String[] temp = new String[this.attachments.length-1];        
        for(int i = 0, j = 0;i<temp.length;i++,j++)
        {
            
            if(this.attachments[j].equals(path))
            {
                --i;
            }
            else
            {
                temp[i] = this.attachments[j];
            }
        }
        this.setAttachments(temp);
        deleteFile(path);
    }
    
    /**
     * Copys the attachment file to the attachments folder inside the programm.
     * Returns the path of the attachment. If no file was picked by the user null
     * is returned.
     * Returns null if error.
     * @return 
     */
    public static String addNewAttachment() /*Done*/
    {
        String dest = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(fileChooser);
        String source = null;
        String name = null;
        if (result == JFileChooser.APPROVE_OPTION) 
        {
            File selectedFile = fileChooser.getSelectedFile();
            source = selectedFile.getAbsolutePath();
            name = selectedFile.getName();
            dest = getApplicationPath()+"\\Attachments";
            new File(dest).mkdirs();
            File file = new File(dest + "\\" + name);
            String newName = "";

            for(int i = 1;file.exists();i++)
            {
                newName = "";
                String[] temp = name.split("\\.");
                for(int j = 0;j<temp.length-1;j++)
                {
                    if(newName.equals(""))
                    {
                        newName = temp[j];
                    }
                    else
                    {
                        newName = newName + "." + temp[j];
                    }
                    newName = newName + "(" + i + ")." + temp[temp.length-1];
                }
                file = new File(dest + "\\" + newName);
            }
            if(!newName.equals(""))
            {
                dest = dest + "\\" + newName;
            }
            else
            {
                dest = dest + "\\" + name;
            }
            
            try {
                Files.copy(Paths.get(source), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dest;
        
        
        
    }
    /**
     * Copys the attachment file to the path choosen by the user.
     * @param source 
     */
    public static void exportAttachment(String source) /*Done*/
    {
        String dest = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) 
        {
            File selectedFile = fileChooser.getSelectedFile();
            dest = selectedFile.getAbsolutePath();
            dest = dest +"\\"+ new File(source).getName();
            
            try {
                Files.copy(Paths.get(source), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            
        }
      }
    /**
     * Takes a path and deletes the corresponding file.
     * @param path 
     */
    public static void deleteFile(String path) /*Done*/
    {
        File file = new File(path);
        file.delete();
    }
    /**
     * Returns the path where the application is stored.
     * @return 
     */
    public static String getApplicationPath() /*Done*/
    {
        String path = null;
        
        try {
            path = new File(".").getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return path;
    }
    /**
     * Takes a path string and opens the correponding file 
     * in the corresponding programm if one exists.
     * @param path 
     */
    public static void displayAttachment(String path) /*Done*/
    {
        if (Desktop.isDesktopSupported()) {
            try {
                File attachment = new File(path);
                Desktop.getDesktop().open(attachment);
            } 
            catch (IOException ex) {
            }
        }
    }
}
