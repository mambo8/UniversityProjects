import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sr = new Scanner(System.in);
        String archName1 = sr.nextLine().trim();
        String archName2 = sr.nextLine().trim();
        int numberTiles = sr.nextInt();
        int tValue[] = new int[numberTiles];
        for (int i = 0; i < numberTiles; i++) {
            tValue[i] = sr.nextInt();
        }
        TerrainSystem terrainSystem = new TerrainSystem(archName1, archName2, tValue);
        processCommands(sr, archName1, archName2, terrainSystem);
    }

    /**
     * Method used for the Scanner to read the commands
     * 
     * @param sr
     * @param archName1
     * @param archName2
     * @param terrainSystem
     * @pre sr != 0 && arcName1.length <= 40 && archName1.length > 0 &&
     *      arcName2.length <= 40
     * @pre archName2.length > 0 && terranSystem != null
     */
    private static void processCommands(Scanner sr, String archName1, String archName2, TerrainSystem terrainSystem) {
        boolean working = true;
        while (working) {
            switch (sr.next()) {
            case "riqueza":
                System.out.println("Riqueza enterrada: " + processTerrainValue(terrainSystem));
                break;
            case "terreno":
                System.out.println(processTerrainState(terrainSystem));
                break;
            case "merito":
                processArchMerit(sr, terrainSystem, archName1, archName2);
                break;
            case "escavacao":
                processDig(sr, terrainSystem, archName1, archName2);
                break;
            case "sair":
                processExit(terrainSystem);
                working = false;
                break;
            default:
                sr.nextLine();
                System.out.println("Comando invalido");
                break;
            }
        }
        sr.nextLine();
    }

    /**
     * Method used the input is 'riqueza'
     * @param terrainSystem
     * @return value of the terrain
     */
    private static int processTerrainValue(TerrainSystem terrainSystem) {
        return terrainSystem.getTerrainSysValue();
    }

    /**
     * Method used for the input 'terreno'
     * @param terrainSystem
     * @return the state of the terrain
     */
    private static String processTerrainState(TerrainSystem terrainSystem) {
        return terrainSystem.getTerrainSysState();
    }

    /**
     * Method used to print out the Arceologist's merit when the command 'merito' is used
     * @param sr
     * @param terrainSystem
     * @param archName1
     * @param archName2
     */
    private static void processArchMerit(Scanner sr, TerrainSystem terrainSystem, String archName1, String archName2) {
        String name = sr.nextLine().trim();
        if (name.equals(archName1)) {
            if (!terrainSystem.hasLicenseSys1()) {
                System.out.println("Arqueologo desclassificado");
            } else {
                System.out.println("Merito de " + archName1 + ": " + terrainSystem.getArchMerit1());
            }
        } else if (name.equals(archName2)) {
            if (!terrainSystem.hasLicenseSys2()) {
                System.out.println("Arqueologo desclassificado");
            } else {
                System.out.println("Merito de " + archName2 + ": " + terrainSystem.getArchMerit2());
            }
        } else {
            System.out.println("Arqueologo inexistente");
        }
    }

    /**
     * Method used for the input 'escavacao', will disqualify the Archeologist if the jump is invalid,
     * move it if the jump is valid, and print out a message if the name or jump is invalid
     * @param sr
     * @param terrainSystem
     * @param archName1
     * @param archName2
     */
    private static void processDig(Scanner sr, TerrainSystem terrainSystem, String archName1, String archName2) {
        int jump = sr.nextInt();
        String name = sr.nextLine().trim();
        if (jump != 0) {
            if (name.equals(archName1)) {
                if (!terrainSystem.hasLicenseSys1()) {
                    System.out.println("Arqueologo desclassificado");
                } else {
                    terrainSystem.digTileSys1(jump);
                    if (!terrainSystem.hasLicenseSys1()) {
                        System.out.println(archName1 + " perdeu a licenca de escavacao");
                    }
                }
            } else if (name.equals(archName2)) {
                if (!terrainSystem.hasLicenseSys2()) {
                    System.out.println("Arqueologo desclassificado");
                } else {
                    terrainSystem.digTileSys2(jump);
                    if (!terrainSystem.hasLicenseSys2()) {
                        System.out.println(archName2 + " perdeu a licenca de escavacao");
                    }
                }
            } else {
                System.out.println("Arqueologo inexistente");
            }
        } else {
            System.out.println("Salto invalido");
        }
    }

    /**
     * Method used for the input 'sair'
     * @param terrainSystem
     */
    public static void processExit(TerrainSystem terrainSystem) {
        if (terrainSystem.bothLostLicense()) {
            System.out.println("Correu mal! Foram ambos desclassificados.");
        } else{
            if (terrainSystem.stillHadTreasure()) {
            System.out.println("Ainda havia tesouros por descobrir...");
        } else {
            System.out.println("Todos os tesouros foram descobertos!");
            }
        }
    }
}
