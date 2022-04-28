/**
 * Name: Alan Wu
 * Pennkey: wualan
 * Execution: java LFSR
 *
 * Description: Class that creates the LFSR object and its related methods
**/

public class LFSR {
    /* 
     * Constructor: Creates new instance of LFSR object
     */
    private String seed = "";
    private int tapPosition;
    private int[] register;
        
    public LFSR(String seed, int tapPosition) {
        // Error checks
        if (seed == null) {
            // Check for seed being empty
            throw new IllegalArgumentException("Seed is empty"); 
        } else {
            boolean invalidChar = false; // Presence of invalid character
            
            // Iterates over every char in seed and checks if it isn't a 0/1
            for (int i = 0; i < seed.length(); i++) {
                if (seed.charAt(i) != '0' && seed.charAt(i) != '1') {
                    invalidChar = true;
                    break;
                }
            } if (invalidChar) {
                // Checks for invalid character
                throw new IllegalArgumentException("Seed is invalid");
            } else if (tapPosition < 0 || tapPosition >= seed.length()) {
                // Checks if tapPosition is a valid position
                throw new IllegalArgumentException("Invald tap position");
                }
            }
         
        this.seed = seed; // Original binary seed
        this.tapPosition = tapPosition; // Tap position
        
        // Initializes array of shift register
        int[] temp = new int[seed.length()];
        this.register = temp;
        for (int i = 0; i < seed.length(); i++) {
            this.register[i] = this.seed.charAt(i) - 48;
        }
    }
    
    /*
     * Constructor: Creates LSFR object with random seed of length seedLength
     */ 
    public LFSR(int seedLength, int tapPosition) {
        // Error checks
        if (seedLength < 0) {
            // Checks if seedLength is valid
            throw new IllegalArgumentException("Seed length is invalid");
        } else if (tapPosition < 0 || tapPosition >= seedLength) {
            // Checks if tapPosition is valid
            throw new IllegalArgumentException("Tap position is invalid");
        }
        
        // Creates random seed with length seedLength and shift register
        int[] temp = new int[seedLength];
        this.register = temp;       
        for (int i = 0; i < seedLength; i++) {
            int random = (int) (Math.random() * 2);
            this.seed += random;
            this.register[i] = random;
        }
        
        this.tapPosition = tapPosition; // Tap position
    }
    /*
     * Input: None
     * Output: String form of called LFSR object
     * Description: Returns the current bit sequence as a string of 0's and 1's
     */
    public String toString() {
        String s = "";
        // Builds returned string
        for (int i = 0; i < this.seed.length(); i++) {
            s += this.register[i];
        }
        return s;
    }
    
    /*
     * Input: None
     * Output: int tapPosition
     * Description: int value of the tap position of called LFSR object
     */ 
    public int getTapPosition() {
        return this.tapPosition;
    }
    
    /* 
     * Input: None
     * Output: int nextBit
     * Description: Return the least significant/right most digit in the register
     */ 
    public int nextBit() {
        // New least significant bit
        int newLeast = this.register[0] ^ 
            this.register[this.seed.length() - 1 - this.tapPosition]; 
        
        // Updates register
        for (int i = 0; i < this.seed.length() - 1; i++) {
            this.register[i] = this.register[i + 1];
        }
        
        // Sets new least signficant bit
        this.register[this.seed.length() - 1] = newLeast;
        
        return this.register[this.seed.length() - 1];
    }
    
    public static void main(String[] args) {
        LFSR lfsr = new LFSR("01101000010", 8);
        for (int i = 0; i < 10; i++) {
            int bit = lfsr.nextBit();
            System.out.println(lfsr.toString() + " " + bit);
        }
    }
}
    