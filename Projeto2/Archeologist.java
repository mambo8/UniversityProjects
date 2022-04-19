/**
 * @author Guilherme Mauricio
 * @author Joao Neves
 *         The class of the participants where the methods for them are stored
 */
public class Archeologist {
    private int positionX;
    private int positionY;
    private String archName;
    private int merit;
    private int penaltyCount;
    private boolean license = true;

    /**
     * Constructor
     * 
     * @param archName Name of the archeologist
     * @pre archName <= 40 && archName >= 1
     */
    public Archeologist(String archName) {
        this.archName = archName;
        positionX = -1;
        positionY = -1;
        this.merit = 0;
        penaltyCount = 0;
    }

    /**
     * @return the Archeologist's name
     */
    public String getArchName() {
        return archName;
    }

    /**
     * @return the Archeologist's merit
     */
    public int getMerit() {
        return merit;
    }

    /**
     * Adds merit to the Archeologist
     * 
     * @param merit
     */
    public void addArchMerit(int merit) {
        this.merit += merit;
    }

    /**
     * @return wheter or not the Archeologist has its license
     */
    public boolean hasLicense() {
        return license;
    }

    /**
     * @param move
     * @return the archeologist's current X
     */
    public int digArchX(int move) {
        positionX = positionX + move;
        return positionX;
    }

    /**
     * @param move
     * @return the archeologist's current Y
     */
    public int digArchY(int move) {
        positionY = positionY + move;
        return positionY;
    }

    // makes the Archeologist lose his license
    public void loseLicense() {
        license = false;
        merit = 0;
    }

    /**
     * @return the penalty count of the Archeologist
     */
    public int getPenalties() {
        return penaltyCount;
    }

    // adds a penalty to the Archeologist's penalty count
    public void addPenalty() {
        penaltyCount++;
    }

    /**
     * @return the Archeologist's X
     */
    public int getCurrentX() {
        return positionX;
    }

    /**
     * @return the Archeologist's Y
     */
    public int getCurrentY() {
        return positionY;
    }

}