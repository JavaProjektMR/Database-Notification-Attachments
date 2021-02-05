/*-------------------------------------------------------------------------*/


                     //Testclasse nicht beachten//


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
        /*
        Database.displayTable("user");
        System.out.println("-----------");
       
        System.out.println("-----------");
        Database.displayTable("user");
        */
        
       
        
        Database.displayTable("event");
        System.out.println("-----------");
        /*String[] address = new String[2];
        address[0] = "Bordell";
        address[1] = "bahnhofsvirtel";
        Date date = new Date(2023-1900,7-1,2,22,12,59);
        int[] participantIDs = new int[3];
        participantIDs[0] = 2;
        participantIDs[1] = 3;
        participantIDs[2] = 4;
        Event test = new Event("Treffen mit Karma", address, date, "2Stunden", participantIDs, 2, 2, 1);
        Database.storeNewEvent(test);*/
        String[] login = Database.getLogin();
        for(int i= 0;i<login.length;i++)
        {
            System.out.println(login[i]);
        }
        Notification[] notes = Database.loadNotifications();
        printNotiArray(notes);
        System.out.println("-----------");
        Database.displayTable("event");
    }
}
