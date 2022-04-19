import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author Guilherme Mauricio
 * @author Joao Neves
 */
public class Main {
    public static final String ERROR_TEAM = "Equipa invalida";
    public static final String ERROR_JUMP = "Salto invalido";
    public static final String ERROR_COMMAND = "Comando invalido";
    public static final String NO_TEAMS_LEFT = "Todas as equipas foram expulsas.";
    public static final String TREASURES_NOT_FOUND = "Ainda havia tesouros por descobrir...";
    public static final String NO_TREASURES_LEFT = "Todos os tesouros foram descobertos!";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sr = new Scanner(System.in);
        Scanner fr = new Scanner(new FileReader("teams.txt"));
        int x = sr.nextInt();
        int y = sr.nextInt();
        int[][] tValue = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                tValue[i][j] = sr.nextInt();
            }
            sr.nextLine();
        }
        int numTeams = sr.nextInt();
        TerrainSystem terrainSystem = new TerrainSystem(tValue, numTeams);
        int i = 0;
        String[] teamNames = new String[20];
        String[][] archNames = new String[20][];
        while (fr.hasNextLine()) {
            int teamArchs = fr.nextInt();
            fr.nextLine();
            teamNames[i] = fr.nextLine();
            archNames[i] = new String[teamArchs];
            for (int j = 0; j < teamArchs; j++) {
                archNames[i][j] = fr.nextLine();
            }
            i++;
        }
        for (int k = 0; k < numTeams; k++) {
            int teamNum = sr.nextInt();
            terrainSystem.addTeam(teamNames[teamNum - 1], archNames[teamNum - 1]);
        }
        fr.close();
        processCommands(sr, terrainSystem);
    }

    private static void processCommands(Scanner sr, TerrainSystem terrainSystem) {
        boolean working = true;
        while (working) {
            switch (sr.next()) {
                case "riqueza":
                    System.out.println("Riqueza enterrada: " + processTerrainValue(terrainSystem));
                    break;
                case "terreno":
                    System.out.print(processTerrainState(terrainSystem));
                    break;
                case "classificacao":
                    processTeamStats(terrainSystem);
                    break;
                case "estrela":
                    System.out.println(processTeamStar(sr, terrainSystem));
                    break;
                case "escavacao":
                    processDig(sr, terrainSystem);
                    break;
                case "sair":
                    System.out.println(processExit(terrainSystem));
                    working = false;
                    sr.close();
                    break;
                default:
                    sr.nextLine();
                    System.out.println(ERROR_COMMAND);
                    break;
            }
        }
    }

    private static int processTerrainValue(TerrainSystem terrainSystem) {
        return terrainSystem.getTerrainSysValue();
    }

    private static String processTerrainState(TerrainSystem terrainSystem) {
        return terrainSystem.getTerrainSysState();
    }

    private static void processTeamStats(TerrainSystem terrainSystem) {
        int teams = 0;
        TerrainIterator it = terrainSystem.iterator();
        while (it.hasNextTeam()) {
            Team team1 = it.nextTeam();
            if (!team1.isDisqualified()) {
                teams++;
                System.out.println(terrainSystem.TeamStats(team1));
            }
        }
        if (teams == 0) {
            System.out.println(NO_TEAMS_LEFT);
        }
    }

    private static String processTeamStar(Scanner sr, TerrainSystem terrainSystem) {
        String teamName = sr.nextLine().trim();
        if (terrainSystem.doesTeamExist(teamName)) {
            return "Estrela de " + teamName + ": " + terrainSystem.callTeam(teamName).getStar();
        } else {
            return ERROR_TEAM;
        }
    }

    private static void processDig(Scanner sr, TerrainSystem terrainSystem) {
        int digX = sr.nextInt();
        int digY = sr.nextInt();
        String teamName = sr.nextLine().trim();
        if (digX == 0 && digY == 0) {
            System.out.println(ERROR_JUMP);
        } else {
            if (terrainSystem.doesTeamExist(teamName)) {
                terrainSystem.digTerrainSys(digX, digY, teamName);
            } else {
                System.out.println(ERROR_TEAM);
            }
        }

    }

    private static String processExit(TerrainSystem terrainSystem) {
        if (terrainSystem.allLostLicense()) {
            return NO_TEAMS_LEFT;
        } else {
            if (terrainSystem.allTreasuresFound()) {
                return NO_TREASURES_LEFT;
            } else {
                return TREASURES_NOT_FOUND;
            }
        }
    }
}