/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superkey.keychain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ico
 */
public class KeyEntryTest {
    private KeyEntry entryA, entryEmpty;
    
    public KeyEntryTest() {
    }
    
    @Before
    public void setUp() {
        entryA = new KeyEntry();
        entryA.setApplicationName("appx");
        entryA.setUsername("xx");
        entryA.setPassword("secret@@@");
        
        entryEmpty = new KeyEntry();
    }
    
    @After
    public void tearDown() {
        entryA = null;
    }

   
    @Test( expected = IllegalArgumentException.class)
    public void testSetApplicationNameWithNull() {
        entryA.setApplicationName( null);
    }

    @Test
    public void testKey() {
        // the key is the application name
        assertEquals("failed to get existing key field", entryA.getApplicationName(), "appx");
    }

    @Test
    public void testFormatAsCsv() {
        String expects = "appx" + KeyEntry.FIELDS_DELIMITER + "xx" + KeyEntry.FIELDS_DELIMITER + "secret@@@";
        assertEquals("failed to format entry to delimited string", entryA.formatAsCsv(), expects);
                   
    }

    @Test
    public void testToString() {
        StringBuilder expects = new StringBuilder();
        expects.append( "appx" );expects.append("\t");
        expects.append( "xx" );expects.append("\t");
        expects.append( "secret@@@" );
        assertEquals("failed toString", entryA.toString(), expects.toString());
    }

    @Test
    public void testParse() {
        String dados = "appx;xx;secret@@@";
        KeyEntry entryB = new KeyEntry();
        entryB = entryA.parse(dados);
        assertEquals("application name diferente", "appx", entryB.getApplicationName());
        assertEquals("password diferente", "secret@@@", entryB.getPassword());
        assertEquals("username diferente", "xx", entryB.getUsername());
    }
    
    @Test
    public void testGetPassword() {
        assertEquals("password diferente", entryA.getPassword(), "secret@@@");
    }
    
    @Test
    public void testGetUsername() {
        assertEquals("username diferente", entryA.getUsername(), "xx");
    }
    
    @Test
    public void testGetApplicationName() {
        assertEquals("application name diferente", entryA.getApplicationName(), "appx");
    }
    
    @Test
    public void testSetApplicationName() {
        entryEmpty.setApplicationName("appEmpty");
        assertEquals("appEmpty", entryEmpty.getApplicationName());
    }
    
    @Test
    public void testSetUsername() {
        entryEmpty.setUsername("usernameEmpty");
        assertEquals("usernameEmpty", entryEmpty.getUsername());
    }
    
    @Test
    public void testSetPassword() {
        entryEmpty.setPassword("passwordEmpty");
        assertEquals("passwordEmpty", entryEmpty.getPassword());
    }
    
}
