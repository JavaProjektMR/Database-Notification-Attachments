/*-------------------------------------------------------------------------*/


                //Testclasse nicht beachten und Löschen//


/*-------------------------------------------------------------------------*/


package OnTimeDatabaseConnector;

import java.util.Date;

/**
 *
 * @author MyUniLaptop
 */
public class Main {
    
    public static void printUserArray(User[] user)
    {
        for(int i = 0; i<user.length;i++)
        {
            user[i].print();
        }
        if(user.length == 0)
        {
            System.out.println("Keine Daten");
        }
    }
    
    public static void printEventArray(Event[] event)
    {
        System.out.println("-----------");
        for(int i = 0;i<event.length;i++)
        {
            event[i].print();
        }
        if(event.length == 0)
        {
            System.out.println("Keine Daten");
        }
        System.out.println("-----------");
    }
    public static void printNotiArray(Notification[] note)
    {
        System.out.println("-----------");
        for(int i = 0;i<note.length;i++)
        {
            note[i].print();
        }
        if(note.length == 0)
        {
            System.out.println("Keine Daten");
        }
        System.out.println("-----------");
    }
    
    public static void main(String[] args)
    {
        
        /*Database.displayTable("user");
        System.out.println("-----------");
        
        
        if(Database.verifyLogin("Maraslon.Ringel@gmx.de", "1234"))
        {
            System.out.println("true");
        }
        else
        {
            System.out.println("false");
        }
        User[] all = Database.loadAllUsers();
        Main.printUserArray(all);
        System.out.println("-----------");
        Database.displayTable("user");*/
        
        
       
        
        Database.displayTable("event");
        System.out.println("-----------");
        /*String[] address = new String[2];
        address[0] = "Street";
        address[1] = "City";
        Date date = new Date(2020-1900,3-1,9,0,0,0);
        int[] members = new int[1];
        members[0] = 0;
        Event event = new Event("26.geburtstag",address,date,"30min",members,0,0,1);
        Database.storeNewEvent(event);*/
        
        Event[] events = Database.loadUserEvents(1);
        events = Database.sortEvents(events, false);
        Main.printEventArray(events);
        Notification[] note = Database.loadNotifications();
        Main.printNotiArray(note);
        System.out.println("-----------");
        Database.displayTable("event");
    }
}
