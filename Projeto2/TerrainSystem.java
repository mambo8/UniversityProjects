/**
 * @author Guilherme Mauricio
 * @author Joao Neves
 *         Class where the Terrain and Team Methods are used
 */
public class TerrainSystem {
    private Terrain terrain;
    private Team[] team;
    private int archNum;
    private int teamsNr;

    /**
     * Constructor
     * 
     * @param tValue
     * @param teamsNr
     */
    public TerrainSystem(int[][] tValue, int teamsNr) {
        terrain = new Terrain(tValue);
        team = new Team[teamsNr];
        this.teamsNr = teamsNr;
        archNum = 0;
    }

    /**
     * Creates new Team
     * 
     * @param teamName
     * @param archNames
     */
    public void addTeam(String teamName, String[] archNames) {
        team[archNum++] = new Team(teamName, archNames);
    }

    /**
     * @return the value of the terrain
     */
    public int getTerrainSysValue() {
        return terrain.getTerrainValue();
    }

    /**
     * @return the state of the terrain
     */
    public String getTerrainSysState() {
        return terrain.getTerrainState();
    }

    /**
     * @return the Iterator for each Team
     */
    public TerrainIterator iterator() {
        return new TerrainIterator(newTeam(), team.length);
    }

    /**
     * @return returns the same team
     */
    private Team[] newTeam() {
        Team[] team1 = new Team[team.length];
        for (int i = 0; i < team1.length; i++) {
            team1[i] = team[i];
        }
        return team1;
    }

    /**
     * @param teamName
     * @return the team's name
     */
    public Team callTeam(String teamName) {
        for (int i = 0; i < team.length; i++) {
            if (teamName.equals(team[i].getTeamName())) {
                return team[i];
            }
        }
        return null;
    }

    /**
     * @param team
     * @return the output of the "classificacao" method
     */
    public String TeamStats(Team team) {
        String part1 = team.getTeamName() + ": " + team.sumTeamMerit() + " pts; ";
        String part2 = team.disqualifiedArch() + " descl.; ";
        String part3 = team.notDisqualifiedArch() + " com lic.";
        return part1 + part2 + part3;
    }

    /**
     * @param teamName
     * @return wheter a team with the name read by the scanner exists or not
     */
    public boolean doesTeamExist(String teamName) {
        boolean exists = false;
        for (int i = 0; i < team.length; i++) {
            if (!team[i].isDisqualified() && teamName.equals(team[i].getTeamName())) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * @param positionX
     * @param positionY
     * @return wheter or not the Archeologists position will be out of bounds
     */
    private boolean isOutOfBounds(int positionX, int positionY) {
        boolean outX = positionX >= terrain.getTerrainLengthX();
        boolean outY = positionY >= terrain.getTerrainLengthY();
        boolean out0 = positionX < 0 || positionY < 0;
        if (outX || outY || out0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to give and remove points from the Archeologists when digging
     * and also remove points from the terrain's tiles, or disqualify the
     * Archeologist's who are out of bounds
     * 
     * @param digX
     * @param digY
     * @param teamName
     */
    public void digTerrainSys(int digX, int digY, String teamName) {
        Team team1 = callTeam(teamName);
        int digPosX = team1.getCurrentArchX() + digX;
        int digPosY = team1.getCurrentArchY() + digY;
        if (isOutOfBounds(digPosX, digPosY)) {
            team1.disqualifyArch();
            if (team1.isDisqualified()) {
                System.out.println(team1.getTeamName() + " foi expulsa");
            } else {
                team1.changeOrder();
            }
        } else {
            int positionX = team1.digTeamX(digX);
            int positionY = team1.digTeamY(digY);
            int treasure = terrain.digTile(positionX, positionY);
            team1.addArchTeamMerit(treasure);
            if (treasure < 0) {
                team1.addArchPenalty();
            }
            team1.changeOrder();
        }
    }

    /**
     * @return true if all teams have lost their licenses
     */
    public boolean allLostLicense() {
        int teamsDisqualified = 0;
        for (int i = 0; i < teamsNr; i++) {
            if (team[i].isDisqualified()) {
                teamsDisqualified++;
            }
        }
        return teamsDisqualified == team.length;
    }

    /**
     * @return true if all the Treasures where found, false if not
     */
    public boolean allTreasuresFound() {
        return terrain.getTerrainValue() == 0;
    }

}
