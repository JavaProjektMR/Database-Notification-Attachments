/**
 * Create by Marlon Ringel, 1314614
 * Java WiSe 2020/21 Project
 * 
 */
package OnTimeDatabaseConnector;

/**
 * This class is a container to store user data.
 * Also its provieds seter and geter methodes and constructors.
 * 
 */
public class User {
    
    /**
     * Contains the ID the user has in the database.
     * This will be assigned by the database upon user creation.
     */
    private int id;
    
    /**
     * Contains the username of the user. 
     */
    private String username;
    
    /**
     * Contains the email address of the user.
     */
    private String email;
    
    /**
     * Contains the password of the user.
     */
    private String password;
    
    /**
     * Contains the ids of the accounts the user added to his contacts.
     * The default value is 0. This means no contacts.
     */
    private int[] contactIDs;
    
    /**
     * Contains the status of the user accout. 
     * True means it is an admin account.
     * False means it's a user account.
     */
    private boolean isAdmin;

    /**
     * Empty constructor.
     */
    public User() 
    {}
    
    /**
     * Constructor to be used upon account creation.
     * @param username
     * @param email
     * @param password 
     */
    public User(String username, String email, String password)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = false;
        int[] temp = new int[1];
        temp[0] = 0;
        this.contactIDs = temp;
    }

    /**
     * Constructor to be used for loading the event from the database.
     * Also for copying.
     * @param id
     * @param username
     * @param email
     * @param password
     * @param friendIDs
     * @param isadmin 
     */
    public User(int id, String username, String email, String password, String friendIDs, int isadmin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        
        String[] temp1 = friendIDs.split(",");
        int[] temp2 = new int[temp1.length];
        for(int i = 0;i<temp1.length;i++)
        {
            temp2[i] = Integer.parseInt(temp1[i]);
        }
        this.contactIDs = temp2;
        
        switch(isadmin)
        {
            case 0: this.isAdmin = false; break;
            case 1: this.isAdmin = true; break;
            default: this.isAdmin = false; break;
        }
    }

    /**
     * Prints the contants of the user object to system out.
     */
    public void print()
    {
        System.out.println(this.id+" "+this.username+" "+this.email+" "+this.password+" "+this.contactIDs[0]+" "+this.isAdmin);
    }
    
    /**
     * Returns the values of the id attribute.
     * @return 
     */
    public int getId() {
        return id;
    }
    
    /**
     * Returns the values of the v attribute.
     * @return 
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Returns the values of the email attribute.
     * @return 
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Returns the values of the password attribute.
     * @return 
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Returns the values of the contactIDs attribute.
     * @return 
     */
    public int[] getContactIDs() {
        return contactIDs;
    }
    
    /**
     * Returns the values of the isAdmin attribute.
     * @return 
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }
    /**
     * Stores the given input into the id attribute.
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Stores the given input into the username attribute.
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Stores the given input into the email attribute.
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Stores the given input into the password attribute.
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Stores the given input into the contactIDs attribute.
     * @param contactIDs 
     */
    public void setContactIDs(int[] contactIDs) {
        this.contactIDs = contactIDs;
    }

    /**
     * Stores the given input into the isAdmin attribute.
     * @param isAdmin 
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
}




