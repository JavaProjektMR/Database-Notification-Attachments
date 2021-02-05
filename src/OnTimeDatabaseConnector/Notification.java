/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTimeDatabaseConnector;

import java.util.Date;

/**
 *This class stores the information about an notification. Its only a data struckture.
 * 
 */
public class Notification 
{
    /**
     * Contains the username of the owner of the corresponding event.
     */
    private String username;
    /**
     * Contains the email address to which the email will be send.
     */
    private String receiverAddresse;
    /**
     * Contains the name of the event corresponding to this notification.
     */
    private String eventName;
    /**
     * Contains the email addresses from the CC receivers.
     * This has to contain 1 or more email addresses seperated by commas or the string "none".
     */
    private String memberAdresses;
    /**
     * Contains the names of the participants of the event corresponding to the notification.
     * If there are no paticipants create a string array with lenght zero 
     * exemple: String[] members = new String[0];
     */
    private String[] eventMembers;
    /**
     * Contains the address of the event corresponding to the notification.
     * One string for street and house number, secound string for city.
     */
    private String[] eventAddress;
    /**
     * Contains the notification delay indicator. 
     * 1 = one week
     * 2 = 3 days
     * 3 = one hour 
     * 4 = ten minutes
     * every other number triggers the defoult case, which means no delay.
     */
    private int delay;
    /**
     * Contains the beginning of the event corresponding to the notification.
     */
    private Date eventDateStart;
    /**
     * Contains the duration of the event corresponding to the notification.
     */
    private String eventDuration;
    /**
     * Contais the date information when the notification mail should be sended.
     * This will be created upon cunstructor call from the eventDateStart and delay attributes.
     */
    private Date sendDate;
    /**
     * Contains the priority indecator of the event corresponding to the notification.
     * 1 = low  
     * 2 = medium
     * 3 = high
     * every other number triggers the defoult case, which means priority  = none. 
     */
    private int priority;
    /**
     * Empty constructor
     */
    Notification(){}
    /**
     * This allows to create an notification object by giving the following attributes.
     * It will also calculate the sendDate attribute from the eventDateStart and delay attributes.
     * @param username
     * @param receiverAddresse
     * @param memberAdresses
     * @param eventName
     * @param eventMembers
     * @param eventAddress
     * @param delay
     * @param eventDateStart
     * @param eventDuration
     * @param priority 
     */
    Notification(String username, String receiverAddresse, String memberAdresses, String eventName, String[] eventMembers, String[] eventAddress, int delay, Date eventDateStart, String eventDuration, int priority)
    {
        this.username = username;
        this.receiverAddresse = receiverAddresse;
        this.memberAdresses = memberAdresses;
        this.eventName = eventName;
        this.eventMembers = eventMembers;
        this.eventAddress = eventAddress;
        this.delay = delay;
        this.eventDateStart = eventDateStart;
        this.eventDuration = eventDuration;
        this.priority = priority;
        
        switch(delay)
        {
            case 1:/*1 week*/
            {
                this.sendDate = new Date(eventDateStart.getYear(), eventDateStart.getMonth(), eventDateStart.getDate()-7, eventDateStart.getHours(), eventDateStart.getMinutes());
                break;
            }
            case 2:/*3 days*/
            {
                this.sendDate = new Date(eventDateStart.getYear(), eventDateStart.getMonth(), eventDateStart.getDate()-3, eventDateStart.getHours(), eventDateStart.getMinutes());
                break;
            }
            case 3:/*1 hour*/
            {
                this.sendDate = new Date(eventDateStart.getYear(), eventDateStart.getMonth(), eventDateStart.getDate(), eventDateStart.getHours()-1, eventDateStart.getMinutes());
                break;
            }
            case 4:/*10 Min.*/
            {
                this.sendDate = new Date(eventDateStart.getYear(), eventDateStart.getMonth(), eventDateStart.getDate(), eventDateStart.getHours(), eventDateStart.getMinutes()-10);
                break;
            }
            default: 
            {
                this.sendDate = this.sendDate = new Date(eventDateStart.getYear(), eventDateStart.getMonth(), eventDateStart.getDate(), eventDateStart.getHours(), eventDateStart.getMinutes());
                break;
            }
        }
        
    }
    
    public void print()
    {
        String memEvent = "";
        for(int i = 0;i<this.eventMembers.length;i++)
        {
            if(memEvent.equals(""))
            {
                memEvent = eventMembers[i];
            }
            else
            {
                memEvent = memEvent +"\n"+ eventMembers[i];
            }
        }
        String address = this.eventAddress[0] + "\n" + this.eventAddress[1];
        
        System.out.println(this.username+"\n"
                +this.receiverAddresse+"\n"
                +this.eventName+"\n"
                +this.memberAdresses+"\n"
                +memEvent+"\n"
                +address+"\n"
                +this.delay+"\n"
                +this.eventDateStart.toString()+"\n"
                +this.eventDuration+"\n"
                +this.sendDate+"\n"
                +this.priority+"\n");
    }
    
    /**
     * This is used to change the "username" attribute.
     * @param username 
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    /**
     * This returns the value of the "username" attribute.
     * @return 
     */
    public String getUsername()
    {
        return username;
    }
    /**
     * This is used to change the "receiverAddresse" attribute.
     * @param receiverAddresse 
     */
    public void setReceiverAddresse(String receiverAddresse)
    {
       this.receiverAddresse = receiverAddresse; 
    }
    /**
     * This returns the value of the "receiverAddresse" attribute.
     * @return 
     */
    public String getReceiverAddresse()
    {
        return receiverAddresse;
    }
    /**
     * This is used to change the "memberAdresses" attribute.
     * @param memberAdresses 
     */
    public void setMemberAdresses(String memberAdresses)
    {
        this.memberAdresses = memberAdresses;
    }
    /**
     * This returns the value of the "memberAdresses" attribute.
     * @return 
     */
    public String getMemberAdresses()
    {
        return memberAdresses;
    }
    /**
     * This is used to change the "eventName" attribute.
     * @param eventName 
     */
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }
    /**
     * This returns the value of the "eventName" attribute.
     * @return 
     */
    public String getEventName()
    {
        return eventName;
    }
    /**
     * This is used to change the "eventMembers" attribute.
     * @param eventMembers 
     */
    public void setEventMembers(String[] eventMembers)
    {
        this.eventMembers = eventMembers;
    }
    /**
     * This returns the value of the "eventMembers" attribute.
     * @return 
     */
    public String[] getEventMembers()
    {
        return eventMembers;
    }
    /**
     * This is used to change the "eventAddress" attribute.
     * @param eventAddress 
     */
    public void setEventAddress(String[] eventAddress)
    {
        this.eventAddress = eventAddress;
    }
    /**
     * This returns the value of the "eventAddress" attribute.
     * @return 
     */
    public String[] getEventAddress()
    {
        return eventAddress;
    }
    /**
     * This is used to change the "delay" attribute.
     * @param delay 
     */
    public void setDelay(int delay)
    {
        this.delay = delay;
    }
    /**
     * This returns the value of the "delay" attribute.
     * @return 
     */
    public int getDelay()
    {
        return delay;
    }
    /**
     * This is used to change the "eventDateStart" attribute.
     * @param eventDateStart 
     */
    public void setEventDateStart(Date eventDateStart)
    {
        this.eventDateStart = eventDateStart;
    }
    /**
     * This returns the value of the "eventDateStart" attribute.
     * @return 
     */
    public Date getEventDateStart()
    {
        return eventDateStart;
    }
    /**
     * This is used to change the "eventDateEnd" attribute.
     * @param eventDateEnd 
     */
    public void setEventDuration(String eventDuration)
    {
        this.eventDuration = eventDuration;
    }
    /**
     * This returns the value of the "eventDateEnd" attribute.
     * @return 
     */
    public String getEventDuration()
    {
        return eventDuration;
    }
    /**
     * This is used to change the "sendDate" attribute.
     * @param sendDate 
     */
    public void setSendDate(Date sendDate)
    {
        this.sendDate = sendDate;
    }
    /**
     * This returns the value of the "sendDate" attribute.
     * @return 
     */
    public Date getSendDate()
    {
        return sendDate;
    }
    /**
     * This is used to change the "priority" attribute.
     * @param priority 
     */
    public void setPriority(int priority)
    {
        this.priority = priority;
    }
    /**
     * This returns the value of the "priority" attribute.
     * @return 
     */
    public int getPriority()
    {
        return priority;
    }
}
