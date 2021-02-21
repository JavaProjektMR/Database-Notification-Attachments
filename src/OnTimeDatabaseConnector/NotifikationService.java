  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTimeDatabaseConnector;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *This class implements the interface "Runnable" in order to get multithreading funktionality. 
 * It contains the code which should be executed in a parallel thread.
 */
public class NotifikationService implements Runnable
{
    /**
     * If set to false code execution stops.
     */
    protected static boolean exit = true;
    
    /**
     * Contains the Date information of the last minute.
     */
    private static Date last;
    
    /**
     * The constructor initiates the class veriables.
     * exit has to be true in order to run the code.
     * last has to contain the date information of one minute befor the programm start.
     */
    NotifikationService()
    {
        exit = true;
        last = getNow();
        last.setMinutes(last.getMinutes()-1);
    }
    
    /**
     * Returns a date object containing the current date and time. 
     * The precision of the date object is reduced to only be precise to the minute. 
     * Secounds an microseconds are allways stored as zero. 
     * @return 
     */
    public static Date getNow()
    {
        Date temp = new Date();
        Date now = new Date(temp.getYear(), temp.getMonth(), temp.getDate(), temp.getHours(), temp.getMinutes());
        return now;
    }
    
    /**
     * Creates an email subject string from an given notification object and returns it.
     * @param notification
     * @return 
     */
    public static String createSubject(Notification notification)
    {
        String subject;
        switch(notification.getDelay())
        {
            case 1:
            {
                subject = "You're appointment \"" +  notification.getEventName() + "\" is in one week";
                break;
            }
            case 2:
            {
                subject = "You're appointment \"" +  notification.getEventName() + "\" is in tree days";
                break;
            }
            case 3:
            {
                subject = "You're appointment \"" +  notification.getEventName() + "\" is in one hour";
                break;
            }
            case 4:
            {
                subject = "You're appointment \"" +  notification.getEventName() + "\" is in ten minutes";
                break;
            }
            default:
            {
                subject = "You're appointment \"" +  notification.getEventName() + "\" is now";
                break;
            }
        }
        return subject;
    }
    
    /**
     * Creates an email text string from an given notification object and returns it.
     * @param notification
     * @return 
     */
    public static String createText(Notification notification)
    {
        String address = "";
        for(int i = 0;i<notification.getEventAddress().length;i++)
        {
            address = address + notification.getEventAddress()[i] + "\n";
        }
        String members = notification.getUsername() + "\n";
        for(int i = 0;i<notification.getEventMembers().length;i++)
        {
            members = members + notification.getEventMembers()[i] + "\n";
        }
        String priority;
        switch(notification.getPriority())
        {
            case 1:
            {
                priority = "Low\n";
                break;
            }
            case 2:
            {
                priority = "Medium\n";
                break;
            }
            case 3:
            {
                priority = "High\n";
                break;
            }
            default:
            {
                priority = "none\n";
                break;
            }
        }
        String delay;
        switch(notification.getDelay())
        {
            case 1:
            {
                delay = " is in one week.\n";
                break;
            }
            case 2:
            {
                delay = " is in tree days.\n";
                break;
            }
            case 3:
            {
                delay = " is in one hour.\n";
                break;
            }
            case 4:
            {
               delay = " is in ten minutes.\n";
                break;
            }
            default:
            {
                delay = " is now.\n";
                break;
            }
        }
        String text = "Dear User,\nYou're appointment \"" + notification.getEventName() + "\"" + delay + "See details below:\n\n"
                + "The appointment beginns at:\n" + notification.getEventDateStart().toString() 
                + "\n\nThe appointmemt duration is:\n" + notification.getEventDuration()
                + "\n\nThe appointment takes place at:\n"+ address 
                + "\nParticipants of the appointment are:\n"+ members
                + "\nThe priority of the Appointment is:\n" + priority
                + "\n\nKind regards\nYou're onTime Team\n";
        return text;
    }

    /**
     * Compares the dates in the notification objekt array to the the date in "now", 
     * which its receives upon funktion call.
     * If they equal, an email is created from the notification object. Then this email gets send.
     * The reqired data will be provieded by the notification object and the database.
     * @param notifications
     * @param now 
     */
    public static void compareAndSend(Notification[] notifications, Date now)
    {
        for(int i = 0;i<notifications.length;i++)
        {
            if(notifications[i].getSendDate().equals(now))
            {
                Email mail = new Email(createSubject(notifications[i]), createText(notifications[i]), notifications[i].getReceiverAddresse(), notifications[i].getMemberAdresses());
                mail.login();
                try 
                {
                    mail.sendMail();
                } 
                catch (MessagingException ex) 
                {
                    Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (UnsupportedEncodingException ex) 
                {
                    Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Now = " + new Date());
                System.out.println("-----------------------------------------\n");
                System.out.println(mail.getSubject()+"\n\n\n"+mail.getEmailText());
                System.out.println("-----------------------------------------\n");
            }
        }
    }
    
    /**
     * Creates an email containing an welcome text for a specific user and sends it to the given email address.
     * @param username
     * @param userAddress 
     */
    public static void sendWelcome(String username, String userAddress)
    {
        String subject = "Welcome " + username + " to onTime.";
        String text = "Dear " + username + ",\n" 
                +"you're account has been registered.\n"
                +"\n\n You're onTime Team";
        Email mail = new Email(subject, text, userAddress, "none");
        mail.login();
        try 
        {
            mail.sendMail();
        } 
        catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This sends an email which informs about the change of an event. 
     * The email contains all details given by the notification about the event.
     * @param notification 
     */
    public static void sendEventEdit(Notification notification)
    {
        String subject = "You're appointment \"" + notification.getEventName() + "\" has changed.";
        String address = "";
        for(int i = 0;i<notification.getEventAddress().length;i++)
        {
            address = address + notification.getEventAddress()[i] + "\n";
        }
        String members = notification.getUsername() + "\n";
        for(int i = 0;i<notification.getEventMembers().length;i++)
        {
            members = members + notification.getEventMembers()[i] + "\n";
        }
        String priority;
        switch(notification.getPriority())
        {
            case 1:
            {
                priority = "Low\n";
                break;
            }
            case 2:
            {
                priority = "Medium\n";
                break;
            }
            case 3:
            {
                priority = "High\n";
                break;
            }
            default:
            {
                priority = "none\n";
                break;
            }
        }
        String text = "Dear User,\nYou're appointment \"" + notification.getEventName() + "\"" + " has changed.\nSee details below:\n\n"
                + "The appointment beginns at:\n" + notification.getEventDateStart().toString() 
                + "\n\nThe appointmemt ends at:\n" + notification.getEventDuration() 
                + "\n\nThe appointment takes place at:\n"+ address 
                + "\nParticipants of the appointment are:\n"+ members
                + "\nThe priority of the Appointment is:\n" + priority
                + "\n\nKind regards\nYou're onTime Team\n";
        
        Email mail = new Email(subject, text, notification.getReceiverAddresse(), notification.getMemberAdresses());
        mail.login();
        try 
        {
            mail.sendMail();
        }
        catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates and sends an invite email.
     * @param username
     * @param receiverAddress 
     */
    public static void sendInvite(String username, String receiverAddress)
    {
        String subject = "You've been invited to onTime";
        String text = "You've been invited to onTime by "+username
                +". \nPlease register an account to use our service.\n You're onTime team.";
        String memberAdresses = "none";
        
        
        Email mail = new Email(subject, text, receiverAddress, memberAdresses);
        mail.login();
        try {
            mail.sendMail();
        } catch (MessagingException ex) {
            Logger.getLogger(NotifikationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NotifikationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   /**
    * This methode executes the code which runs in a parallel thread.
    * It checks the current time and compares it to the time of the
    * scheduled notification.
    * If the current time equals the time of an notification,
    * an email with the notification text is send to the 
    * corresponding email address(es).
    */
    @Override public void run()
    {
        while(exit)
        {
            Date now = getNow();
            if(now.compareTo(last)>0)
            {
                Database.deleteOldEvents();
                Notification[] all = Database.loadNotifications();
                compareAndSend(all, now);
                last = now;
                
            }
        }
    }
}
