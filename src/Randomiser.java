import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Randomiser {

    public static Boat getBoat(Game game, Shipowner owner){
        return new Boat(game, owner, 0,0,getInteger(50, 100), 0, getInteger(500,1000));
    }
    public static Harbor getHarbor(List<Harbor> harbors){
        return get(harbors);
    }

    public static Shipyard getShipyard(List<Shipyard> shipyards){
        return get(shipyards);
    }
    private static <T> T get(List<T> objects){
        int index = getInteger(0, objects.size());
        return objects.get(index);
    }

    public static boolean chance(double percent) {
        return (Math.random() * 100) <= percent;
    }

    public static Container getContainer() {
        ContentType contentType = ContentType.values()[getInteger(0, 2)];
        return new Container(0, 0, contentType);
    }

    public static List<Container> getContainers(int n) {
        List<Container> containers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            containers.add(getContainer());
        }
        return containers;
    }

    public static int getMoney() {
        return getInteger(50000, 100000);
    }

    private static int getInteger(int from, int to){
        return ThreadLocalRandom.current().nextInt(from, to);
    }

}
