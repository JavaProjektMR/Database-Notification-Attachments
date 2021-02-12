/**
 * Create by Marlon Ringel, 1314614
 * Java WiSe 2020/21 Project
 * 
 */
package OnTimeDatabaseConnector;
import java.sql.*;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * Provides methodes to connect to the database.
 */
public class Database {
    
    
    /**
     * Connects to the database and returns a Statement object 
     * to execute sql querys in the database
     * @return
     * @throws SQLException 
     */
    private static Statement getStatement() throws SQLException /*Done*/
    {
        Connection temp = DriverManager.getConnection("jdbc:mysql://localhost:3306/ontimedatabase", "root", "G0s1");
        Statement newStatment = temp.createStatement();
        return newStatment;
    }
    
    /**
     * Returns the number(int) of entrys a table has.
     * @param tableName
     * @return
     * @throws SQLException 
     */
    private static int getRowCount(String tableName) throws SQLException /*Done*/
    {
        Statement state = getStatement();
        String query = "select count(*) from " + tableName;
        ResultSet rC = state.executeQuery(query);
        int rowCount = 0;
        while(rC.next())
        {
            rowCount = rC.getInt("count(*)");
        }
        return rowCount;
    }
    
    /**
     * Prints the contants of a table to system out.
     * @param tableName 
     */
    public static void displayTable(String tableName)  /*Done*/
    {
        try {
            String query = "select * from "+tableName;
            Statement state = getStatement();
            ResultSet table = state.executeQuery(query);
            while(table.next())
            {
                if(tableName.equals("event"))
                {
                    System.out.println(table.getInt("ID")+" "+table.getInt("ownerID")+" "+table.getString("title")+" "+table.getString("street")
                            +" "+table.getString("city")+" "+ table.getDate("date")+" "+table.getString("duration")+" "+table.getInt("notification")+" "+table.getString("participantsID"));
                }
                else if(tableName.equals("user"))
                {
                    System.out.println(table.getInt("ID")+" "+table.getString("username")+" "+table.getString("email")+" "+table.getString("password")+" "+table.getString("friends")+" "+table.getInt("admin"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Converts a Java.Util.Date object into a date string.
     * @param date
     * @return 
     */
    public static String convertDate(Date date) /*Done*/
    {
        String datestring = "";
        if(date.getMonth()<9&&date.getDate()<10)
        {
            datestring = (date.getYear()+1900)+"-0"+(date.getMonth()+1)+"-0"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
        }
        else if(date.getMonth()>8&&date.getDate()<10)
        {
            datestring = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-0"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
        }
        else if(date.getMonth()<9&&date.getDate()>9)
        {
            datestring = (date.getYear()+1900)+"-0"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
        }
        else if(date.getMonth()>8&&date.getDate()>9) 
        {
            datestring = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
        }
        return datestring;
    }
    
    /**
     * Converts a date string into java.util.date object.
     * @param datestring
     * @return 
     */
    public static Date convertDate(String datestring) /*Done*/
    {
        String[] temp = datestring.split(" ");
        String[] date1 = temp[0].split("-");
        String[] time = temp[1].split(":");
        Date date = new Date(Integer.parseInt(date1[0])-1900,Integer.parseInt(date1[1])-1,Integer.parseInt(date1[2]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(time[2]));
        return date;
    }
    
    /**
     * Returns two date objects. They contain the beginning(mon) 
     * and end(sun) date of the current week
     * @return 
     */
    public static Date[] determineWeek() /*Done*/
    {
        Date now = new Date();
        int day = now.getDay();
        Date[] weekStartEnd = new Date[2];
        weekStartEnd[0] = new Date();
        weekStartEnd[1] = new Date();       
        
        switch(day)
        {
            case 0:
            {
                weekStartEnd[0].setDate(weekStartEnd[0].getDate()-6);
                break;
            }
            case 1:
            {
                weekStartEnd[1].setDate(weekStartEnd[1].getDate()+6);
                break;
            }
            case 2:
            {
                weekStartEnd[0].setDate(weekStartEnd[0].getDate()-1);
                weekStartEnd[1].setDate(weekStartEnd[1].getDate()+5);
                break;
            }
            case 3:
            {
                weekStartEnd[0].setDate(weekStartEnd[0].getDate()-2);
                weekStartEnd[1].setDate(weekStartEnd[1].getDate()+4);
                break; 
            }
            case 4:
            {
                weekStartEnd[0].setDate(weekStartEnd[0].getDate()-3);
                weekStartEnd[1].setDate(weekStartEnd[1].getDate()+3);
                break;
            }
            case 5:
            {
                weekStartEnd[0].setDate(weekStartEnd[0].getDate()-4);
                weekStartEnd[1].setDate(weekStartEnd[1].getDate()+2);
                break; 
            }
            case 6:
            {
                weekStartEnd[0].setDate(weekStartEnd[0].getDate()-5);
                weekStartEnd[1].setDate(weekStartEnd[1].getDate()+1);
                break; 
            }
        }
        weekStartEnd[0].setHours(0);
        weekStartEnd[0].setMinutes(0);
        weekStartEnd[0].setSeconds(0);
        weekStartEnd[1].setHours(23);
        weekStartEnd[1].setMinutes(59);
        weekStartEnd[1].setSeconds(59);
        return weekStartEnd;
        
    }
    /*__________________________________________________*/
    /*Account management*/
    
    /**
     * Checks if the given user data is stored inside the database. If they exit, 
     * false is returned, if not true. 
     * @param username
     * @param email
     * @return 
     */
    public static Boolean verifyNewAccount(String username, String email) /*Done*/ /*Done*/
    {
        String encryptedUsername = Encryption.encrypt(username);
        String encryptedEmail = Encryption.encrypt(email);
        Boolean verify = true;
        try {
            String query1 = "select count(*) from user"
                    +" where username='"+encryptedUsername+"'";
            String query2 = "select count(*) from user"
                    +" where email='"+encryptedEmail+"'";
            
            Statement state = getStatement();
            ResultSet Rs1 = state.executeQuery(query1);
            if(Rs1.next())
            {
                if(Rs1.getInt("count(*)")==1)                
                {
                    verify = false;
                }
            }
            ResultSet Rs2 = state.executeQuery(query2);
            if(Rs2.next())
            {
                if(Rs2.getInt("Count(*)")==1)                
                {
                    verify = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return verify;
    }
    
    /**
     * Stores a new user account in the database. 
     * Make sure that the given data not alredy exists.
     * @param newUser 
     */
    public static void storeNewAccount(User newUser) /*Done*/ /*Done*/
    {
        User encryptedNewUser = Encryption.encryptUser(newUser);
        try {
            String store = "Insert into user "
                    + " (username, email, password, friends, admin)"
                    + " values ('"+ encryptedNewUser.getUsername() +"','"+encryptedNewUser.getEmail()+"','"+encryptedNewUser.getPassword()+"', '0', '0') ";
            Statement state = getStatement();
            state.execute(store);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Stores a new administrator account in the database. 
     * Make sure that the given data not alredy exists
     * @param newUser 
     */   
    public static void storeNewAccountAdmin(User newUser) /*Done*/ /*Done*/
    {
        User encryptedNewUser = Encryption.encryptUser(newUser);
        try {
            String store = "Insert into user "
                    + " (username, email, password, friends, admin)"
                    + " values ('"+ encryptedNewUser.getUsername() +"','"+encryptedNewUser.getEmail()+"','"+encryptedNewUser.getPassword()+"', '0', '1') ";
            Statement state = getStatement();
            state.execute(store);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Loads a user identified by his username from the database and returns it
     * as an user object.
     * @param username
     * @return 
     */
    public static User loadUser(String username) /*Done*/ /*Done*/
    {
        String encryptedUsername = Encryption.encrypt(username);
        try {
            Statement state = getStatement();
            ResultSet loadUser = state.executeQuery("select * from user where username='"+encryptedUsername+"'");
            User loadedUser = new User();
            if(loadUser.next())
            {
                loadedUser = new User(loadUser.getInt("ID"), loadUser.getString("username"), loadUser.getString("email"), loadUser.getString("password"), loadUser.getString("friends"), loadUser.getInt("admin"));
            }
            if(loadedUser.getUsername()!=null)
            {
                return Encryption.decryptUser(loadedUser);
            }
            return loadedUser;
            //return Encryption.decryptUser(loadedUser);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new User();
    }
    /**
     * Loads a user identified by his id from the database and returns it
     * as an user object.
     * @param userid
     * @return 
     */
    public static User loadUser(int userid) /*Done*/ /*Done*/
    {
        try {
            Statement state = getStatement();
            ResultSet loadUser = state.executeQuery("select * from user where ID='"+userid+"'");
            User loadedUser = new User();
            if(loadUser.next())
            {
                loadedUser = new User(loadUser.getInt("ID"), loadUser.getString("username"), loadUser.getString("email"), loadUser.getString("password"), loadUser.getString("friends"), loadUser.getInt("admin"));
            }
            if(loadedUser.getUsername()!=null)
            {
                return Encryption.decryptUser(loadedUser);
            }
            return loadedUser;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new User();
    }
    
    /**
     * Loads a user identified by his email address from the database and returns it
     * as an user object.
     * @param useremail
     * @return 
     */
    public static User loadUserViaEmail(String useremail) /*Done*/ /*Done*/
    {
        String encryptedEmail = Encryption.encrypt(useremail);
        try {
            Statement state = getStatement();
            ResultSet loadUser = state.executeQuery("select * from user where email='"+encryptedEmail+"'");
            User loadedUser = new User();
            if(loadUser.next())
            {
                loadedUser = new User(loadUser.getInt("ID"), loadUser.getString("username"), loadUser.getString("email"), loadUser.getString("password"), loadUser.getString("friends"), loadUser.getInt("admin"));
            }
            if(loadedUser.getUsername()!=null)
            {
                return Encryption.decryptUser(loadedUser);
            }
            return loadedUser;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new User();
    }
    
    /**
     * Loads all users from the database and returns them as a user object array.
     * @return 
     */
    public static User[] loadAllUsers() /*Done*/ /*Done*/
    {
        try {
            int rows = getRowCount("user");
            User[] allUsers = new User[rows];
            Statement state = getStatement();
            String query = "select * from user";
            ResultSet loadUser = state.executeQuery(query);
            for(int i = 0;loadUser.next();i++)
            {
                allUsers[i] = new User(loadUser.getInt("ID"), loadUser.getString("username"), loadUser.getString("email"), loadUser.getString("password"), loadUser.getString("friends"), loadUser.getInt("admin"));
                allUsers[i] = Encryption.decryptUser(allUsers[i]);
            }
            return allUsers;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new User[0];
    }
    
    /**
     * Takes a user and stores changes(if they exist) in the database.
     * @param updatedUser 
     */
    public static void updateUser(User updatedUser) /*Done*/ /*Done*/
    {
        User encryptedUpdatedUser = Encryption.encryptUser(updatedUser);
        try {
            Statement state = getStatement();
            String contacts = "";
            for(int i = 0;i<updatedUser.getContactIDs().length;i++)
            {
                if(contacts.equals(""))
                {
                    contacts = String.valueOf(updatedUser.getContactIDs()[i]);
                }
                else
                {
                    contacts = contacts +","+ updatedUser.getContactIDs()[i];
                }
            }
            int admin = 0;
            if(updatedUser.getIsAdmin())
            {
                admin = 1;
            }    
            String query = "update user"
                    + " set username='"+encryptedUpdatedUser.getUsername()+"', email='"+encryptedUpdatedUser.getEmail()+"', password='"+encryptedUpdatedUser.getPassword()+"',friends='"+contacts+"',admin="+admin
                    +" where ID="+encryptedUpdatedUser.getId();
            state.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Takes the login information and compares them to the database. 
     * If the exist true is returned, else false. The "login" attrebute can
     * contain either a email address or a username.
     * @param login
     * @param password
     * @return 
     */
    public static boolean verifyLogin(String login, String password) /*Done*/ /*Done*/
    {
        Boolean logginDataVerified = false;
        int loginVerified = 0;
        String encryptedLogin = Encryption.encrypt(login);
        try {
            String query1 = "select count(*) from user "
                    + "where username='"+encryptedLogin+"'";
            String query2 = "select count(*) from user "
                    + "where email='"+encryptedLogin+"'";
            Statement state = getStatement();
            ResultSet Rs1 = state.executeQuery(query1);
            if(Rs1.next())
            {
                if(Rs1.getInt("Count(*)")==1)
                {
                    loginVerified = 1;
                }
            }
            Rs1 = state.executeQuery(query2);
            if(Rs1.next())
            {
                if(Rs1.getInt("Count(*)")==1)
                {
                    loginVerified = 2;
                }
            }
            User user = new User();
            switch(loginVerified)
            {
                case 1:
                {
                    user = loadUser(login);
                    if(user.getPassword().equals(password))
                    {
                        logginDataVerified = true;
                        break;
                    }
                }
                case 2:
                {
                    user = loadUserViaEmail(login);
                    if(user.getPassword().equals(password))
                    {
                        logginDataVerified = true;
                        break;
                    }
                }
                default: break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logginDataVerified;
    }
    /*Contacts*/
    
    /**
     * Adds a contact to the users(loggedin) account. The changes get stored in the
     * database.
     * @param loggedin
     * @param contact 
     */
    public static void addContact(User loggedin, User contact) /*Done*/ /*Done*/
    {
        if(loggedin.getContactIDs()[0] != 0)
        {
        int[] temp = new int[loggedin.getContactIDs().length+1];
        for(int i = 0;i<loggedin.getContactIDs().length;i++)
        {
            temp[i] = loggedin.getContactIDs()[i];
        }
        temp[temp.length-1] = contact.getId();
        loggedin.setContactIDs(temp);
        }
        else
        {
            int[] temp2 = new int[1];
            temp2[0] = contact.getId();
            loggedin.setContactIDs(temp2);
        }
        updateUser(loggedin);
    }
    
    /**
     * Deletes a contact from the users(loggedin) account.
     * The changes get stored in the database.
     * @param loggedin
     * @param unfriend 
     */
    public static void deleteContact(User loggedin, User unfriend) /*Done*/ /*Done*/
    {
        if(loggedin.getContactIDs().length==1)
        {
            int[] temp = new int[1];
            temp[0] = 0;
            loggedin.setContactIDs(temp);
            
        }
        else
        {
            int[] temp2 = new int[loggedin.getContactIDs().length-1];
            for(int i = 0, j = 0;i<temp2.length;i++, j++)
            {
                if(loggedin.getContactIDs()[j]==unfriend.getId())
                {
                    i--;
                }
                else
                {
                    temp2[i] = loggedin.getContactIDs()[j];
                }
            }
            loggedin.setContactIDs(temp2);
            
        }
        Database.updateUser(loggedin);
    }
    
    /**
     * Loads the accounts of all contacts of a user. The accounts get returned as a 
     * user object array.
     * @param loggedin
     * @return 
     */
    public static User[] loadContacts(User loggedin) /*Done*/ /*Done*/
    {
        User[] friends = new User[0];
        if(loggedin.getContactIDs()[0] != 0)
        {
            friends = new User[loggedin.getContactIDs().length];
            for(int i = 0;i<loggedin.getContactIDs().length;i++)
            {
                friends[i] = loadUser(loggedin.getContactIDs()[i]);
                friends[i] = Encryption.decryptUser(friends[i]);
            }
        }
        return friends;
    }

/*______________________________________________________*/
/* Event management*/

    /**
     * Sorts and returns Event object array by date. If revwerse is true the 
     * array is sorted in reversed order.
     * @param events
     * @param reverse
     * @return 
     */
    public static Event[] sortEvents(Event[] events, Boolean reverse) /*Done*//*Needs Work, Sort in SQL*/
    {
        long[] dates = new long[events.length];
        for(int i = 0;i<dates.length;i++)
        {
            dates[i] = events[i].getDate().getTime();
        }
        Arrays.sort(dates);
        if(reverse)
        {
            
        }
        else
        {
            ArrayUtils.reverse(dates);
        }
        Event[] sortedEvents = new Event[events.length];
        Boolean check = true;
        int[] check2 = new int[dates.length];
        for(int i = 0;i<check2.length;i++)
        {
            check2[i] = -1;
        }
        for(int i = 0;i<dates.length;i++)
        {
            Date temp = new Date(dates[i]);
            for(int j = 0;j<events.length;j++)
            {
                
                if(temp.equals(events[j].getDate()))
                {
                    check = true;
                    for(int k = 0;k<check2.length;k++)
                    {
                        if(check2[k] == j)
                        {
                            check = false;
                        }
                    }
                    if(check)
                    {
                        sortedEvents[i] = events[j];
                        check2[i] = j;
                    }
                }
            }
        }
        return sortedEvents;
    }
    
    /**
     * Takes a event object and stores its contants in the database.
     * @param newEvent 
     */
    public static void storeNewEvent(Event newEvent) /*Done*/ /*Done*/
    {
        Event encryptedNewEvent = Encryption.encryptEvent(newEvent);
        String participants = "";
        for(int i = 0;i<encryptedNewEvent.getParticipantIDs().length;i++)
            {
                if(participants.equals(""))
                {
                    participants = Integer.toString(encryptedNewEvent.getParticipantIDs()[i]);
                }
                else
                {
                    participants = participants + "," + Integer.toString(encryptedNewEvent.getParticipantIDs()[i]);
                }
            }
        String query = "Insert into event "
                + " (ownerID, title, street, city, date, duration, notification, participantsID, priority) "
                + " values ("+encryptedNewEvent.getOwnerID()+", '"+encryptedNewEvent.getTitle()+"','"+encryptedNewEvent.getAddress()[0]
                +"','"+encryptedNewEvent.getAddress()[1]+"','"+convertDate(encryptedNewEvent.getDate())+"','"+encryptedNewEvent.getDuration()+"',"
                +encryptedNewEvent.getNotification()+",'"+participants+"',"+encryptedNewEvent.getPriority()+") ";
        try
        {
            Statement state = getStatement();
            state.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Loads all events from the database and returns them as a Event object array.
     * @return 
     */
    public static Event[] loadAllEvents() /*Done*/ /*Done*/
    {
        Event[] allEvents = new Event[0];
        try {
            
            String query = "select count(*) from event";
            Statement state = getStatement();
            ResultSet Rs1 = state.executeQuery(query);
            int rowCount = 0;
            if(Rs1.next())
            {
                rowCount = Rs1.getInt("count(*)");
            }
            allEvents = new Event[rowCount];
               
            query = "select * from event";
            ResultSet Rs2 = state.executeQuery(query);
            
            for(int i = 0;Rs2.next();i++)
            {
                String[] address = new String[2];
                address[0] = Rs2.getString("street");
                address[1] = Rs2.getString("city");
                String[] members = Rs2.getString("participantsID").split(",");
                int[] participants = new int[members.length];
                for(int j = 0;j<members.length;j++)
                {
                    participants[j] = Integer.parseInt(members[j]);
                }
                
                allEvents[i] = new Event(Rs2.getInt("ID"),Rs2.getString("title"),address,convertDate(Rs2.getString("date")),Rs2.getString("duration"),participants,Rs2.getInt("notification"),Rs2.getInt("priority"),Rs2.getInt("ownerID"));
                if(allEvents[i].getTitle()!=null)
                {
                    allEvents[i] = Encryption.decryptEvent(allEvents[i]);
                }        
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allEvents;
    }
    
    
    /**
     * Loads the events of a user from the database and returns them as 
     * a Event object array.
     * @param userID
     * @return 
     */
    public static Event[] loadUserEvents(int userID) /*Done*/ /*Done*/
    {
        
        String query = "select count(*) from event where ownerID="+userID;
        int rowCount = 0;
        Event[] allEvents = new Event[0];
        try 
        {    
            Statement state = getStatement();
            ResultSet rC = state.executeQuery(query);
            while(rC.next())
            {
                rowCount = rC.getInt("count(*)");
            }
            
            query = "select * from event where ownerID="+userID;
            allEvents = new Event[rowCount];
            
            rC = state.executeQuery(query);
            for(int i = 0;rC.next();i++)
            {
                String[] address = new String[2];
                address[0] = rC.getString("street");
                address[1] = rC.getString("city");
                String[] members = rC.getString("participantsID").split(",");
                int[] participants = new int[members.length];
                for(int j = 0;j<members.length;j++)
                {
                    participants[j] = Integer.parseInt(members[j]);
                }
                
                allEvents[i] = new Event(rC.getInt("ID"),rC.getString("title"),address,convertDate(rC.getString("date")),rC.getString("duration"),participants,rC.getInt("notification"),rC.getInt("priority"),rC.getInt("ownerID"));
                if(allEvents[i].getTitle()!=null)
                {
                    allEvents[i] = Encryption.decryptEvent(allEvents[i]);
                }        
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allEvents;
    }
    
    /**
     * Load all events of a user which take place in the current week.
     * The events get returned as a Event object array.
     * @param userID
     * @return 
     */
    public static Event[] loadUserEventsThisWeek(int userID) /*Done*/ /*Done*/
    {
        Event[] allEvents = loadUserEvents(userID);
        Date[] weekStartEnd = determineWeek();
        System.out.println("-----------");
        System.out.println(weekStartEnd[0]+" , "+weekStartEnd[1]);
        System.out.println("-----------");
        int k=0;
        for(int i = 0;i<allEvents.length;i++)
        {
            if((allEvents[i].getDate().equals(weekStartEnd[0])||allEvents[i].getDate().after(weekStartEnd[0]))&&(allEvents[i].getDate().equals(weekStartEnd[1])||allEvents[i].getDate().before(weekStartEnd[1])))
                k++;
        }
        Event[] weekEvents = new Event[k];
        for(int i = 0,j = 0;i<allEvents.length;i++)
        {
            if((allEvents[i].getDate().equals(weekStartEnd[0])||allEvents[i].getDate().after(weekStartEnd[0]))&&(allEvents[i].getDate().equals(weekStartEnd[1])||allEvents[i].getDate().before(weekStartEnd[1])))
            {
                weekEvents[j] = allEvents[i];
                ++j;
            }
        }
        return weekEvents;
    }

    /**
     * Deletes a Event from the database.
     * @param event 
     */
    public static void deleteEvent(Event event) /*Done*/ /*Done*/
    {
        try {
            String query = "delete from event where ID="+event.getId();
            Statement state = getStatement();
            state.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Deletes all events which are older than one hour.
     */
    public static void deleteOldEvents() /*Done*/ /*Done*/
    {
        Date now = new Date();
        now.setHours(now.getHours()-1);
        Event[] allEvents = loadAllEvents();
        for(int i = 0;i<allEvents.length;i++)
        {
            if(allEvents[i].getDate().equals(now)||allEvents[i].getDate().before(now))
            {
                deleteEvent(allEvents[i]);
            }
        }
    }
    
    /**
     * Takes a event and stores its changes(if they exist) in the database.
     * @param editedEvent 
     */
    public static void UpdateEvent(Event editedEvent) /*Done*/ /*Done*/
    {
        Event encyptedEditedEvent = Encryption.encryptEvent(editedEvent);
        String participants = "";
        for(int i = 0;i<editedEvent.getParticipantIDs().length;i++)
        {
            if(participants.equals(""))
            {
                participants = Integer.toString(editedEvent.getParticipantIDs()[i]);
            }
            else
            {
                participants = participants +","+ Integer.toString(editedEvent.getParticipantIDs()[i]);
            }
        }
        String query = " update event "
                + " set title='"+encyptedEditedEvent.getTitle()
                +"', street='"+encyptedEditedEvent.getAddress()[0]
                +"', city='"+encyptedEditedEvent.getAddress()[0]
                +"', date='"+convertDate(encyptedEditedEvent.getDate())
                +"', duration='"+encyptedEditedEvent.getDuration()
                +"', notification="+encyptedEditedEvent.getNotification()
                +", participantsID='"+participants
                +"', priority="+encyptedEditedEvent.getPriority()
                +", ownerID="+encyptedEditedEvent.getOwnerID()
                +" where ID="+encyptedEditedEvent.getId()+"";
        try {    
            Statement state = getStatement();
            state.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    /*______________________________________________________*/
    /*Notification Service*/
    
    /**
     * Converts a Event object into a Notification object. 
     * Also loads additional data from the database.
     * Returns a notification object.
     * @param event
     * @return 
     */
    public static Notification eventToNotification(Event event) /*Done*/ /*Done*/ /*Done*/
     {
        User owner = loadUser(event.getOwnerID());
        String username = owner.getUsername();
        String useremail = owner.getEmail();
        String memberemail = "";
        String[] participants;
        if(event.getParticipantIDs().length==1&&event.getParticipantIDs()[0]==0)
        {
            participants = new String[1];
            participants[0] = "none";
            memberemail = "none";
        }
        else
        {    
        User participant = new User();
        participants = new String[event.getParticipantIDs().length];
        
            for(int i = 0;i<event.getParticipantIDs().length;i++)
            {
                participant = loadUser(event.getParticipantIDs()[i]);
                if(memberemail.equals(""))
                {
                    memberemail = participant.getEmail();
                    participants[i] = participant.getUsername();
                }
                else 
                {
                    memberemail = memberemail +","+ participant.getEmail();
                    participants[i] = participant.getUsername();
                }
            }
        }
        Notification note = new Notification(username,useremail,memberemail,event.getTitle(),participants,event.getAddress(),event.getNotification(),event.getDate(),event.getDuration(),event.getPriority());
        return note;
    }

    
    /**
     * Loads all events from the database and converts them to Notification objects.
     * Returns a Notification object array.
     * @return 
     */
    public static Notification[] loadNotifications() /*Done*/ /*Done*/
    {
        Event[] allEvents = loadAllEvents();
        Notification[] notifications = new Notification[allEvents.length];
        for(int i = 0;i<notifications.length;i++)
        {
            notifications[i] = eventToNotification(allEvents[i]);
        }
        return notifications;
    }
    
    /**
     * Loads the login information for the notification email host and
     * returns them as a string array.
     * @return 
     */
    public static String[] getLogin() /*Done*/
    {
        String[] login = new String[4];
        try {
            
            String query = "select * from maildata"
                    + " where ID=1 ";
            
            Statement state = getStatement();
            ResultSet Rs = state.executeQuery(query);
            if(Rs.next())
            {
                login[0] = Rs.getString("email");
                login[1] = Rs.getString("password");
                login[2] = Rs.getString("host");
                login[3] = Rs.getString("port");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return login;
    }
}
