/**
 * @author Guilherme Maurício Classe Sistema usada para unir os métodos da
 *         classe Terreno e Arqueólogo para comunicar com a classe Main
 */
public class TerrainSystem {
    private Terrain terrain1;
    private Archeologist archeologist1;
    private Archeologist archeologist2;

    /**
     * Constructor
     * 
     * @param archName1
     * @param archName2
     * @param tValue
     */
    public TerrainSystem(String archName1, String archName2, int[] tValue) {
        this.archeologist1 = new Archeologist(archName1);
        this.archeologist2 = new Archeologist(archName2);
        this.terrain1 = new Terrain(tValue);
    }

    /**
     * 
     * @return getTerrainValue for terrain1
     */
    public int getTerrainSysValue() {
        return terrain1.getTerrainValue();
    }

    /**
     * 
     * @return getTerrainState for terrain1
     */
    public String getTerrainSysState() {
        return terrain1.getTerrainState();
    }

    /**
     * 
     * @return getMerit for archeologist1
     */
    public int getArchMerit1() {
        return archeologist1.getMerit();
    }

    /**
     * 
     * @return getMerit for archeologist2
     */
    public int getArchMerit2() {
        return archeologist2.getMerit();
    }

    /**
     * 
     * @return hasLicense for archeologist1
     */
    public boolean hasLicenseSys1() {
        return archeologist1.hasLicense();
    }

    /**
     * 
     * @return hasLicense for archeologist2
     */
    public boolean hasLicenseSys2() {
        return archeologist2.hasLicense();
    }

    /**
     * digTile for archeologist1 if the jump is valid, loseLicence if not
     * @param jump
     */
    public void digTileSys1(int jump) {
        int position = archeologist1.digArch(jump);
        if (position >= terrain1.length() || position < 0) {
            archeologist1.loseLicense();
        } else {
            archeologist1.addArchMerit(terrain1.digTile(position));
        }
    }

    /**
     * digTile for archeologist2 if the jump is valid, loseLicence if not
     * @param jump
     */
    public void digTileSys2(int jump) {
        int position = archeologist2.digArch(jump);
        if (position >= terrain1.length() || position < 0) {
            archeologist2.loseLicense();
        } else {
            archeologist2.addArchMerit(terrain1.digTile(position));
        }
    }

    /**
     * 
     * @return if both lost their licenses or not
     */
    public boolean bothLostLicense() {
        return !archeologist1.hasLicense() && !archeologist2.hasLicense();
    }

    /**
     * 
     * @return if there's any treasure left or not
     */
    public boolean stillHadTreasure() {
        return terrain1.getTerrainValue() != 0;
    }

}
