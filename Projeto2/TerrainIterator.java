/**
 * @author Guilherme Mauricio
 * @author Joao Neves
 *         Iterator Class for the method getTeamStats()
 */
public class TerrainIterator {
    private Team[] team;
    private int archNum;
    private int teamNum;

    /**
     * Contructor
     * 
     * @param team1
     * @param teamNum
     */
    public TerrainIterator(Team[] team1, int teamNum) {
        team = team1;
        archNum = 0;
        this.teamNum = teamNum;
        Organise();
    }

    // Advances to the next team
    public Team nextTeam() {
        return team[archNum++];
    }

    /**
     * @return if there is a next team or not
     */
    public boolean hasNextTeam() {
        return archNum < team.length;
    }

    /**
     * @param j
     * @return if the team has more merit archeologists than the next team
     */
    private boolean hasMoreMerit(int j) {
        return team[j].sumTeamMerit() > team[j - 1].sumTeamMerit();
    }

    /**
     * @param j
     * @return if the teams have the same number of merit archeologists
     */
    private boolean hasSameMerit(int j) {
        return team[j].sumTeamMerit() == team[j - 1].sumTeamMerit();
    }

    /**
     * @param j
     * @return if the team has more disqualified archeologists than the next team
     */
    private boolean hasLessDisqualified(int j) {
        return team[j].disqualifiedArch() < team[j - 1].disqualifiedArch();
    }

    /**
     * @param j
     * @return if the teams have the same number of disqualified archeologists
     */
    private boolean hasSameDisqualified(int j) {
        return team[j].disqualifiedArch() == team[j - 1].disqualifiedArch();
    }

    /**
     * @param j
     * @return if the team has more licensed archeologists than the next team
     */
    private boolean hasLessLicensed(int j) {
        return team[j].notDisqualifiedArch() < team[j - 1].notDisqualifiedArch();
    }

    /**
     * @param j
     * @return if the teams have the same number of licensed archeologists
     */
    private boolean hasSameLicensed(int j) {
        return team[j].notDisqualifiedArch() == team[j - 1].notDisqualifiedArch();
    }

    /**
     * @param j
     * @return the teams order through alphabetical order
     */
    private boolean checkNameOrder(int j) {
        return team[j - 1].getTeamName().compareTo(team[j].getTeamName()) > 0;
    }

    /**
     * Method used to order the teams
     * 
     * @param j
     */
    private void checkTeams(int j) {
        Team team1 = team[j - 1];
        team[j - 1] = team[j];
        team[j] = team1;
    }

    // Bubble sort
    public void Organise() {
        for (int i = 1; i < teamNum; i++) {
            for (int j = teamNum - 1; j >= i; j--) {
                if (hasMoreMerit(j)) {
                    checkTeams(j);
                }
            }
        }
        for (int i = 1; i < teamNum; i++) {
            for (int j = teamNum - 1; j >= i; j--) {
                if (hasSameMerit(j)) {
                    if (hasLessDisqualified(j)) {
                        checkTeams(j);
                    }
                }
            }
        }
        for (int i = 1; i < teamNum; i++) {
            for (int j = teamNum - 1; j >= i; j--) {
                if (hasSameMerit(j)) {
                    if (hasSameDisqualified(j)) {
                        if (hasLessLicensed(j)) {
                            checkTeams(j);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < teamNum; i++) {
            for (int j = teamNum - 1; j >= i; j--) {
                if (hasSameMerit(j)) {
                    if (hasSameDisqualified(j)) {
                        if (hasSameLicensed(j)) {
                            if (checkNameOrder(j)) {
                                checkTeams(j);
                            }
                        }
                    }
                }
            }
        }
    }
}
