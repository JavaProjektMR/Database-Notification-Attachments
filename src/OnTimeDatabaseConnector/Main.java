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
        
       /* Database.displayTable("user");
        System.out.println("-----------");
        String[] add = new String[2];
        add[0] = "Street";
        add[1] = "City";
        Date date = new Date(2021-1900,2-1,21,15,0,0);
        int[] par = new int[1];
        par[0] = 0;
        Event event = new Event("Treffen mit 4", add, date, "30min", par, 3, 0, 3);
        //public Event(String title, String[] address, Date date, String duration, int[] participantIDs, int notification, int priority, int ownerID)
        Database.storeNewEvent(event);
        
        System.out.println("-----------");
        Database.displayTable("user");*/
        
        
        
        
        
        /*
        
        Database.displayTable("event");
        System.out.println("-----------");
        
        System.out.println("-----------");
        Database.displayTable("event");*/
        
        NotifikationService.sendInvite("Marlon", "marlon.ringel@gmx.de");
        
        /*
        Thread notifikationService = new Thread(new NotifikationService());
        notifikationService.start();
        new UI().setVisible(true);*/
        
    }
}
