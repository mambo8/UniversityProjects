/**
 * @author Guilherme Mauricio
 * @author Joao Neves
 *         The class of the Teams of Archeologists
 */
public class Team {
    private int disqualifiedNum;
    private Archeologist[] archeologist;
    private String teamName;
    private int archDigRotation;

    /**
     * Constructor
     * 
     * @param teamName
     * @param archNames
     * @pre teamName <= 40 && teamName >= 1
     */
    public Team(String teamName, String[] archNames) {
        archeologist = new Archeologist[archNames.length];
        archDigRotation = 0;
        this.teamName = teamName;
        addArchs(archNames);
        disqualifiedNum = 0;
    }

    /**
     * Creates new Archeologist's
     * 
     * @param archNames
     */
    public void addArchs(String[] archNames) {
        for (int i = 0; i < archeologist.length; i++) {
            archeologist[i] = new Archeologist(archNames[i]);
        }
    }

    // Changes the order of the Archeologist
    public void changeOrder() {
        do {
            archDigRotation++;
            if (archDigRotation >= archeologist.length) {
                archDigRotation = 0;
            }
        } while (!archeologist[archDigRotation].hasLicense());
    }

    /**
     * Adds Merit to the Archeologist
     * 
     * @param merit
     */
    public void addArchTeamMerit(int merit) {
        archeologist[getCurrentArchNum()].addArchMerit(merit);
    }

    /**
     * @return the current Archeologist in rotation
     */
    public int getCurrentArchNum() {
        return archDigRotation;
    }

    /**
     * @return team's Name
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param move
     * @return X after the move
     */
    public int digTeamX(int move) {
        return archeologist[archDigRotation].digArchX(move);
    }

    /**
     * @param move
     * @return Y after the move
     */
    public int digTeamY(int move) {
        return archeologist[archDigRotation].digArchY(move);
    }

    /**
     * @param archeologist1
     * @param i
     * @return if the archeologist doesn't exist and has its license
     */
    private boolean archNull(Archeologist archeologist1, int i) {
        return archeologist1 == null && archeologist[i].hasLicense();
    }

    /**
     * @param archeologist1
     * @param i
     * @return if the archeologist exists and has its license
     */
    private boolean archNotNull(Archeologist archeologist1, int i) {
        return archeologist1 != null && archeologist[i].hasLicense();
    }

    /**
     * @param archeologist1
     * @param i
     * @return if the Archeologist has more merit than the other
     */
    private boolean hasMoreMerit(Archeologist archeologist1, int i) {
        return archeologist1.getMerit() < archeologist[i].getMerit();
    }

    /**
     * @param archeologist1
     * @param i
     * @return if the Archeologist has the same merit as the other
     */
    private boolean hasSameMerit(Archeologist archeologist1, int i) {
        return archeologist1.getMerit() == archeologist[i].getMerit();
    }

    /**
     * @param archeologist1
     * @param i
     * @return if the Archeologist's have the same number of Penalties
     */
    private boolean hasSamePenalties(Archeologist archeologist1, int i) {
        return archeologist1.getPenalties() == archeologist[i].getPenalties();
    }

    /**
     * @param archeologist1
     * @param i
     * @return the name order
     */
    private boolean checkNameOrder(Archeologist archeologist1, int i) {
        return (archeologist[i].getArchName().compareTo(archeologist1.getArchName()) < 0);
    }

    /**
     * @param archeologist1
     * @param i
     * @return checks if the Archeologist has the same penalties and checks the name
     *         order
     */
    private boolean checkNameAndSamePenalties(Archeologist archeologist1, int i) {
        return hasSamePenalties(archeologist1, i) && (checkNameOrder(archeologist1, i));
    }

    /**
     * @return the Star Archeologist
     */
    public String getStar() {
        Archeologist archeologist1 = null;
        for (int i = 0; i < archeologist.length; i++) {
            if (archNull(archeologist1, i)) {
                archeologist1 = archeologist[i];
            } else if (archNotNull(archeologist1, i)) {
                if (hasMoreMerit(archeologist1, i)) {
                    archeologist1 = archeologist[i];
                } else if (hasSameMerit(archeologist1, i)) {
                    if (archeologist[i].getPenalties() < archeologist1.getPenalties()) {
                        archeologist1 = archeologist[i];
                    } else if (checkNameAndSamePenalties(archeologist1, i)) {
                        archeologist1 = archeologist[i];
                    }
                }
            }
        }
        return archeologist1.getArchName();
    }

    /**
     * @return the team's Merit
     */
    public int sumTeamMerit() {
        int sum = 0;
        for (int i = 0; i < archeologist.length; i++) {
            sum += archeologist[i].getMerit();
        }
        return sum;
    }

    // adds penalty to the current Archeologist
    public void addArchPenalty() {
        archeologist[archDigRotation].addPenalty();
    }

    // Disqualifies the current Archeologist
    public void disqualifyArch() {
        archeologist[archDigRotation].loseLicense();
        disqualifiedNum++;
    }

    /**
     * @return the number of disqualified Archeologists
     */
    public int disqualifiedArch() {
        return disqualifiedNum;
    }

    /**
     * @return the number of not disqualified Archeologists
     */
    public int notDisqualifiedArch() {
        return archeologist.length - disqualifiedNum;
    }

    /**
     * @return if the Team has been disqualified or not
     */
    public boolean isDisqualified() {
        return archeologist.length == disqualifiedNum;
    }

    /**
     * @return current Archeologist's X
     */
    public int getCurrentArchX() {
        return archeologist[archDigRotation].getCurrentX();
    }

    /**
     * @return current Archeologist's Y
     */
    public int getCurrentArchY() {
        return archeologist[archDigRotation].getCurrentY();
    }

}