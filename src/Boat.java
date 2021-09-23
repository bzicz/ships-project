import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Boat extends GameObject {

    private Harbor harbor;
    private int capacity;
    private int petrolCurrent;
    private int petrolMax;
    private double condition;
    private java.util.List<Container> containers;
    private Game game;
    private Point dockPoint;
    private int steps;
    private double dx, dy;
    private Shipowner owner;
    private Shipyard shipyard;

    public Boat(Game game, Shipowner owner, int x, int y, int capacity, int petrolCurrent, int petrolMax) {
        super(x, y);
        this.owner = owner;
        this.capacity = capacity;
        this.petrolCurrent = petrolCurrent;
        this.petrolMax = petrolMax;
        harbor = null;
        condition = 100;
        containers = new ArrayList<>();
        this.game = game;
    }

    @Override
    public void render(Graphics g) {

        if (isAlive()) {
            Renderer.renderBoat(g, this);
        }
    }

    @Override
    public void update(double dt) {
        if (isAlive()) {
        if(trySail()){
            petrolCurrent -= 1;
            condition -= 0.05;
        }
        }
    }


    private boolean trySail() {
        if (dockPoint == null) {
            generateDockPoint(false);
        }else if (condition <= 20){
            generateDockPoint(true);
        }

        int nx = (int)Math.abs(getX() - dockPoint.getX());
        int ny = (int)Math.abs(getY() - dockPoint.getY());


        if (nx < 3 && ny < 3){
            if (harbor != null && harbor.tryDock(this)) {
                owner.setCapital(owner.getCapital() + Randomiser.getMoney());
            } else if (shipyard != null && shipyard.tryDock(this)){
                owner.setCapital(owner.getCapital() - Randomiser.getMoney());
            }
        }else {

            setX(getX() + dx);
            setY(getY() + dy);
            return true;
        }
        return false;
    }

    private void generateDockPoint(boolean toShipyard) {
        if (!toShipyard){
            harbor = Randomiser.getHarbor(game.getHarbors());
            shipyard = null;
            dockPoint = harbor.getDockPoint();
        } else {
            shipyard = Randomiser.getShipyard(game.getShipyards());
            harbor = null;
            dockPoint = shipyard.getDockPoint();
        }
        generatedxdy();
    }
    private void generatedxdy(){
        double nx = dockPoint.getX() - getX();
        double ny = dockPoint.getY() - getY();
        if (Math.abs(nx) > Math.abs(ny)) {
            steps = (int) Math.abs(nx);
        } else {
            steps = (int) Math.abs(ny);
        }
        dx = nx / steps;
        dy = ny / steps;
    }
    public boolean isAlive() {
        if(condition <= 0 || petrolCurrent <= 0){
            return false;
        }
        return getX() != -1 && getY() != -1;
    }

    // if condition <20 go to shipyard & repair


    public Harbor getHarbor() {
        return harbor;
    }

    public void setHarbor(Harbor harbor) {
        this.harbor = harbor;
        setX(shipyard.getDockPoint().getX());
        setY(shipyard.getDockPoint().getY());
        shipyard = null;
        dockPoint = harbor.getDockPoint();

        generatedxdy();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPetrolCurrent() {
        return petrolCurrent;
    }

    public void setPetrolCurrent(int petrolCurrent) {
        this.petrolCurrent = petrolCurrent;
    }

    public int getPetrolMax() {
        return petrolMax;
    }

    public void setPetrolMax(int petrolMax) {
        this.petrolMax = petrolMax;
    }

    public double getCondition() {
        return condition;
    }

    public void setCondition(double condition) {
        this.condition = condition;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public void setDockPoint(Point dockPoint) {
        this.dockPoint = dockPoint;
    }
}

