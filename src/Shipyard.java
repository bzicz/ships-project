import com.google.gson.annotations.*;

import java.awt.*;

public class Shipyard extends GameObject {

    @Expose(serialize = true)
    private Boat[] constructionDocks;
    @Expose(serialize = true)
    private Boat[] repairDocks;
    @Expose(serialize = true)
    private int petrol;
    private Point dockPoint;
    private Game game;

    public Shipyard(Game game, int x, int y, int constructionDocksLimit, int repairDocksLimit, int dockX, int dockY) {
        super(x, y);
        this.game = game;
        constructionDocks = new Boat[constructionDocksLimit];
        repairDocks = new Boat[repairDocksLimit];
        dockPoint = new Point(dockX,dockY);

    }

    @Override
    public void render(Graphics g) {
    Renderer.renderShipyard(g,this);
    }

    @Override
    public void update(double dt) {
        updateConstructionDocks();
        updateRepairDocks();
    }

    public int calculateBoatCost(int capacity, int petrolMax){
        if(getConstructionDockCount() == 0){
            return Integer.MAX_VALUE;
        }
        return capacity*1000 + petrolMax*100;
    }

    public void orderBoat(Boat boat){

            boat.setCondition(0);
            boat.setX(-1);
            boat.setY(-1);
            addBoatToConstruction(boat);
    }
    private void updateConstructionDocks(){
        for (int i = 0; i < constructionDocks.length; i++){
            Boat boat = constructionDocks[i];
            if (boat != null) {

                if (boat.getCondition() < 100) {
                    boat.setCondition(boat.getCondition() + 100);

                } else {
                    boat.setX((int) dockPoint.getX());
                    boat.setY((int) dockPoint.getY());
                    boat.setPetrolCurrent(boat.getPetrolMax());
                    constructionDocks[i] = null;
                }
            }
        }
    }
    private int getConstructionDockCount(){
        int count = 0;
        for (Boat boat : constructionDocks){
            if (boat == null) {
                count++;
            }
        }
        return count;
    }
    private void addBoatToConstruction(Boat boat){
        for (int i = 0; i < constructionDocks.length; i++){
            if (constructionDocks[i] == null){
                constructionDocks[i] = boat;
                break;
            }
        }
    }
    private void updateRepairDocks(){
        for (int i = 0; i < repairDocks.length; i++){
            Boat boat = repairDocks[i];
            if (boat != null) {
                if (Randomiser.chance(1)) {
                    boat.setPetrolCurrent(0);
                    boat.setCondition(0);
                    repairDocks[i] = null;

                }else {

                    if (boat.getCondition() < 100) {
                        boat.setCondition(boat.getCondition() + 1);


                    } else {
                        Harbor harbor = Randomiser.getHarbor(game.getHarbors());
                        boat.setHarbor(harbor);

                        boat.setPetrolCurrent(boat.getPetrolMax());
                        repairDocks[i] = null;
                    }
                }
            }
        }
    }

    private int getRepairDockCount(){
        int count = 0;
        for (Boat boat : repairDocks){
            if (boat == null) {
                count++;
            }
        }
        return count;
    }

    private void addBoatToRepair(Boat boat){
        for (int i = 0; i < repairDocks.length; i++){
            if (repairDocks[i] == null){
                repairDocks[i] = boat;
                break;
            }
        }
    }
    public boolean tryDock(Boat boat){
        for (int i = 0; i < repairDocks.length; i++) {
            if (repairDocks[i] == null) {
                boat.setX(-1);
                boat.setY(-1);
                repairDocks[i] = boat;
                return true;
                }
            }
        return false;
    }
    public Boat[] getConstructionDocks() {
        return constructionDocks;
    }

    public void setConstructionDocks(Boat[] constructionDocks) {
        this.constructionDocks = constructionDocks;
    }

    public Boat[] getRepairDocks() {
        return repairDocks;
    }

    public void setRepairDocks(Boat[] repairDocks) {
        this.repairDocks = repairDocks;
    }

    public int getPetrol() {
        return petrol;
    }

    public void setPetrol(int petrol) {
        this.petrol = petrol;
    }

    public Point getDockPoint() {
        return dockPoint;
    }

    public void setDockPoint(Point dockPoint) {
        this.dockPoint = dockPoint;
    }
}
