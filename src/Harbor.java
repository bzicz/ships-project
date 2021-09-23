import com.google.gson.annotations.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Harbor extends GameObject {
    private Boat[] docks;
    @Expose(serialize = true)
    private java.util.List<Container> inputContainers;
    @Expose(serialize = true)
    private java.util.List<Container> outputContainers;
    @Expose(serialize = true)
    private int petrol;
    private Point dockPoint;

    public Harbor(int x, int y, int docksLimit, int dockX, int dockY) {
        super(x, y);
        docks = new Boat[docksLimit];
        inputContainers = new ArrayList<>();
        outputContainers = Randomiser.getContainers(10000);
        dockPoint = new Point(dockX, dockY);
    }

    @Override
    public void render(Graphics g) {
        Renderer.renderHarbor(g, this);
    }

    @Override
    public void update(double dt) {
        for (Container container : inputContainers) {
            container.update(dt);
        }
        for (Container container : outputContainers) {
            container.update(dt);
        }
        updateDocks();
        moveContainersFromInputToOutput();
    }

    public boolean tryDock(Boat boat) {
        for (int i = 0; i < docks.length; i++) {
            if (docks[i] == null) {
                boat.setX(-1);
                boat.setY(-1);
                docks[i] = boat;
                moveContainersFromBoatToInput(boat);
                moveContainersFromOutputToBoat(boat);
                return true;
            }
        }
        return false;
    }

    public Boat[] getDocks() {
        return docks;
    }

    public void setDocks(Boat[] docks) {
        this.docks = docks;
    }

    public List<Container> getInputContainers() {
        return inputContainers;
    }

    public void setInputContainers(List<Container> inputContainers) {
        this.inputContainers = inputContainers;
    }

    public List<Container> getOutputContainers() {
        return outputContainers;
    }

    public void setOutputContainers(List<Container> outputContainers) {
        this.outputContainers = outputContainers;
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

    private void updateDocks() {
        for (int i = 0; i < docks.length; i++) {
            Boat boat = docks[i];
            if (boat != null && outputContainers.size() > 0) {
                boat.setPetrolCurrent(boat.getPetrolMax());
                boat.setX(getDockPoint().getX());
                boat.setY(getDockPoint().getY());
                boat.setDockPoint(null);
                docks[i] = null;
            }
        }
    }

    private void moveContainersFromInputToOutput() {
        for (Container container : inputContainers) {
            outputContainers.add(container);
        }
        inputContainers.clear();
    }

    private void moveContainersFromBoatToInput(Boat boat) {
        for (Container container : boat.getContainers()) {
            inputContainers.add(container);
        }
        boat.getContainers().clear();
    }

    private void moveContainersFromOutputToBoat(Boat boat) {
        for (int i = outputContainers.size() - 1; i >= 0; i--) {
            if (boat.getContainers().size() < boat.getCapacity()) {
                boat.getContainers().add(outputContainers.get(i));
                outputContainers.remove(i);
            }
        }
    }
}
