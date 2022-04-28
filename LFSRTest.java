/**
 * Name: Alan Wu
 * Pennkey: wualan
 * Execution: java LFSRTest
 *
 * Description: Class that tests the methods for LFSR
**/

import static org.junit.Assert.*;
import org.junit.*;

public class LFSRTest {
    // dummy test
    @Test
    public void dummyTest() {
        assertTrue(true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullSeed() {
        // should throw exception when seed is null
        LFSR broken = new LFSR(null, 5);
    }
    
    @Test
    public void testConstructorNormal() {
        LFSR test = new LFSR("1010101", 2);
        boolean expected = true;
        boolean actual = test.toString().equals("1010101") && 
            test.getTapPosition() == 2; // False if seed and tap weren't set up right
        assertEquals("Normal constructor test", expected, actual);
    }
    
    @Test
    public void testRandomConstructorNomral() {
        LFSR test = new LFSR(5, 3);
        boolean legalVals = true;
        boolean expected = true;
        // Checks over ever character in the seed to see if they're legal
        for (int i = 0; i < test.toString().length(); i++) {
            if (test.toString().charAt(i) != '1' && test.toString().charAt(i) !=
                '0') {
                legalVals = false;
                break;
            }
        }
        // False if tap wasn't set up right or there were illegal values in the seed
        boolean actual = test.getTapPosition() == 3 && legalVals;
        assertEquals("Normal random constructor test", expected, actual);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRandomConstructorNegativeSeed() {
        // Throw an illegal arg exception for a negative seed length
        LFSR test = new LFSR(-2, 1);
    }
    
    @Test
    public void testGetBitNormal() {
        // Tests getBit()
        LFSR test = new LFSR("10101", 1);
        int expected = 1;
        int actual = test.nextBit();
        assertEquals("Next bit for 10101 with tap 1", expected, actual);
    }
    
    @Test
    public void testGetNextBitEdge() {
        // Edge case for getBit() when tap and least significant are the same bit
        LFSR test = new LFSR("10001010110", 10);
        int expected = 0;
        int actual = test.nextBit();
        assertEquals("Next bit for 10001010110 with tap 10", expected, actual);
    }
    
    
    
}