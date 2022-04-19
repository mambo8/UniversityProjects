/**
 * @author Guilherme Maurício
 *         The class for the participants where the methods for them are stored
 */
public class Archeologist {
    private int position;
    private int merit;
    private boolean license = true;

    /**
     * Constructor
     * 
     * @param archName Name of the archeologist
     */
    public Archeologist(String archName) {
        position = -1;
        this.merit = 0;
    }

    /**
     * 
     * @return the Archeologist's merit
     */
    public int getMerit() {
        return merit;
    }

    /**
     * 
     * @param merit is added to the Archeologist
     */
    public void addArchMerit(int merit) {
        this.merit += merit;
    }

    /**
     * 
     * @return whether the Archeologist still has it's license or not
     */
    public boolean hasLicense() {
        return license;
    }

    /**
     * 
     * @param move the amount of tiles the Archeologist will move
     * @return the new position of the Archeologist
     */
    public int digArch(int move) {
        position = position + move;
        return position;
    }

    // Makes the Archeologist lose it's license
    public void loseLicense() {
        this.license = false;
    }
}