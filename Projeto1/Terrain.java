/**
 * @author Guilherme Maurício
 *         The class for the Terrain of the Contest
 */
public class Terrain {
    private int[] tile;
    private static final int PEN = -10;
    private int[] digCount;

    /**
     * 
     * @param position
     * @return if the tile has been dug or not
     */
    private boolean dug(int position) {
        return digCount[position] > 0;
    }

    /**
     * 
     * @return the length of the terrain
     */
    public int length() {
        return tile.length;
    }

    /**
     * Constructor
     * 
     * @param tile
     */
    public Terrain(int[] tile) {
        this.tile = tile;
        this.digCount = new int[tile.length];
        for (int i = 0; i < digCount.length; i++) {
            digCount[i] = 0;
        }
    }

    /**
     * 
     * @return the sum of the terrain's richness
     */
    public int getTerrainValue() {
        int sum = 0;
        for (int tileSquare : tile) {
            sum += tileSquare;
        }
        return sum;
    }

    /**
     * '*' - if has treasure, '-' if not
     * 
     * @return the state of the terrain
     */
    public String getTerrainState() {
        String terrainState = "";
        for (int tValue : tile) {
            if (tValue > 0) {
                terrainState += "*";
            } else {
                terrainState += "-";
            }
        }
        return terrainState;
    }

    /**
     * 
     * @param position
     * @return product of the times the tile has been dug and 10
     */
    private int penalty(int position) {
        return digCount[position] * PEN;
    }

    /**
     * 
     * @param position
     * @return digs tile if it has not been dug yet, and returns either the treasure
     *         or the penalty
     */
    public int digTile(int position) {
        if (dug(position)) {
            int merit = penalty(position);
            digCount[position]++;
            return merit;
        } else {
            digCount[position]++;
            int hasTreasure = tile[position];
            tile[position] = 0;
            return hasTreasure;
        }
    }
}