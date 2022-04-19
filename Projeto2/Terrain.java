/**
 * @author Guilherme Mauricio
 * @author Joao Neves
 *         The class for the Terrain
 */
public class Terrain {
    private int[][] tile;
    private static final int PEN = -10;
    private int[][] digCount;

    /**
     * @param xPosition
     * @param yPosition
     * @return if the tile has been dug or not
     */
    public boolean dug(int xPosition, int yPosition) {
        return digCount[xPosition][yPosition] > 0;
    }

    /**
     * Constructor
     * 
     * @param tile
     */
    public Terrain(int[][] tile) {
        this.tile = tile;
        this.digCount = new int[tile.length][tile[0].length];
        for (int i = 0; i < digCount.length; i++) {
            for (int f = 0; f < digCount[0].length; f++) {
                digCount[i][f] = 0;
            }
        }
    }

    /**
     * @return the value of the terrain
     */
    public int getTerrainValue() {
        int sum = 0;
        for (int i = 0; i < tile.length; i++) {
            for (int j = 0; j < tile[0].length; j++) {
                sum = sum + tile[i][j];
            }
        }
        return sum;
    }

    /**
     * @return the state of the terrain
     */
    public String getTerrainState() {
        String terrainState = "";
        for (int i = 0; i < tile.length; i++) {
            for (int j = 0; j < tile[0].length; j++) {
                if (tile[i][j] > 0) {
                    terrainState += "*";
                } else {
                    terrainState += "-";
                }
            }
            terrainState += "\n";
        }
        return terrainState;
    }

    /**
     * @param xPosition
     * @param yPosition
     * @return the penalty vaue for the tile
     */
    private int penalty(int xPosition, int yPosition) {
        return digCount[xPosition][yPosition] * PEN;
    }

    /**
     * @param xPosition
     * @param yPosition
     * @return the treasure or the penalty that will be transferred to the
     *         Archeologist
     */
    public int digTile(int xPosition, int yPosition) {
        if (dug(xPosition, yPosition)) {
            int merit = penalty(xPosition, yPosition);
            digCount[xPosition][yPosition]++;
            return merit;
        } else {
            digCount[xPosition][yPosition]++;
            int hasTreasure = tile[xPosition][yPosition];
            tile[xPosition][yPosition] = 0;
            return hasTreasure;
        }
    }

    /**
     * @return width of the terrain
     */
    public int getTerrainLengthX() {
        return tile.length;
    }

    /**
     * @return length of the terrain
     */
    public int getTerrainLengthY() {
        return tile[0].length;
    }
}
