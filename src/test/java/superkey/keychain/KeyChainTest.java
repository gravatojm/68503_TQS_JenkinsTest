/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superkey.keychain;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author João
 */
public class KeyChainTest {
    
    File userKeychainFile = new File("Keychain.txt");
    private final HashMap<String, KeyEntry> keyEntriesCollection = new HashMap<>();
    KeyChain keyChain;
    
    private KeyEntry entryA;
    
    public KeyChainTest() {
    }
    
    
    @Before
    public void setUp() {    
        entryA = new KeyEntry();
        entryA.setApplicationName("appx");
        entryA.setUsername("xx");
        entryA.setPassword("secret@@@");
        try {
            keyChain = KeyChain.from(userKeychainFile, new CipherTool("#wisper"));
        } catch (IOException ex) {
            Logger.getLogger(KeyChainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of put method, of class KeyChain.
     */
    @Test
    public void testPut() {
      keyChain.put(entryA);
      assertEquals(keyChain.find(entryA.key()),entryA);
    }

    /**
     * Test of find method, of class KeyChain.
     */
    @Test
    public void testFind() {
        keyChain.put(entryA);
        KeyEntry entryB = keyChain.find(entryA.key());
        assertEquals(entryB.getApplicationName(),"appx");
        assertEquals(entryB.getUsername(),"xx");
        assertEquals(entryB.getPassword(),"secret@@@");
    }
    
}
