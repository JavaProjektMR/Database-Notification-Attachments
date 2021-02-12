/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTimeDatabaseConnector;

import java.util.Base64;

/**
 * The type Encryption.
 */
public class Encryption {

    /**
     * Encrypt event event.
     *
     * @param event the event
     *
     * The Fields that are encrypted are the ones of type String.
     * Namely: eventAddress, eventDuration and eventTitle.
     *
     * @return the event
     */
    public static Event encryptEvent(Event event){
        Event outputEvent = event;


        String[] adresses = new String[event.getAddress().length];
        for(int i=0;i<adresses.length;i++){
            adresses[i] = encrypt(event.getAddress()[i]);
        }

        outputEvent.setAddress(adresses);
        outputEvent.setTitle(encrypt(event.getTitle()));
        outputEvent.setDuration(encrypt(event.getDuration()));

        return outputEvent;
    }

    /**
     * Decrypt event event.
     *
     * @param event the event
     *
     * The Fields that are decrypted are the ones of type String.
     * Namely: eventAddress, eventDuration and eventTitle.
     *
     * @return the event
     */
    public static Event decryptEvent(Event event){
        Event outputEvent = event;

        String[] adresses = new String[event.getAddress().length];
        for(int i=0;i<adresses.length;i++){
            adresses[i] = decrypt(event.getAddress()[i]);
        }

        outputEvent.setAddress(adresses);
        outputEvent.setTitle(decrypt(event.getTitle()));
        outputEvent.setDuration(decrypt(event.getDuration()));

        return outputEvent;
    }

    /**
     * Encrypt user user.
     *
     * @param user the user
     *
     * The Fields that are encrypted are userName, userPassword and userEmail
     *
     * @return the user
     */
    public static User encryptUser(User user){

        User outputUser = user;

        outputUser.setUsername(encrypt(user.getUsername()));
        outputUser.setPassword(encrypt(user.getPassword()));
        outputUser.setEmail(encrypt(user.getEmail()));

        return outputUser;
    }

    /**
     * Decrypt user user.
     *
     * @param user the user
     *
     * The Fields that are decrypted are userName, userPassword and userEmail
     *
     * @return the user
     */
    public static User decryptUser(User user){

        User outputUser = user;

        outputUser.setUsername(decrypt(user.getUsername()));
        outputUser.setPassword(decrypt(user.getPassword()));
        outputUser.setEmail(decrypt(user.getEmail()));

        return outputUser;
    }



    public static String encrypt(String password){
        //encoding
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static String decrypt(String encryptedPassword){
        //decoding
        String decryptedPassword = new String(Base64.getMimeDecoder().decode(encryptedPassword));
        return (decryptedPassword);
    }

}