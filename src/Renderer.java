import java.awt.*;

public class Renderer {
    private final static int BOAT_SIZE = 10;
    private final static int CONTAINER_SIZE = 2;
    private final static int HARBOR_SIZE = 40;
    private final static int SHIPYARD_SIZE = 60;

    public static void renderBoat(Graphics g, Boat boat)
    {
        g.drawOval((int)boat.getX(), (int)boat.getY(), BOAT_SIZE, BOAT_SIZE);
        g.drawString(boat.getContainers().size() + "", (int)boat.getX() - 3, (int)boat.getY() - 3);
    }

    public static void renderHarbor(Graphics g, Harbor harbor) {
        g.drawRect((int)harbor.getX(), (int)harbor.getY(),HARBOR_SIZE,HARBOR_SIZE);
        g.fillOval((int)harbor.getDockPoint().getX(), (int)harbor.getDockPoint().getY(),3,3);
    }

    public static void renderShipyard(Graphics g, Shipyard shipyard) {
        g.drawRect((int)shipyard.getX(),(int)shipyard.getY(),SHIPYARD_SIZE,SHIPYARD_SIZE);
        g.fillOval((int)shipyard.getDockPoint().getX(), (int)shipyard.getDockPoint().getY(),3,3);
    }




}
