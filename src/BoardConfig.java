
public class BoardConfig {

    private int[] config = new int[9];

    public BoardConfig(int[] map){
        for(int i =0; i < map.length; i++){
            config[i] = (map[i]);
        }
    }

    public int[] getConfig() {
        return config;
    }
}
