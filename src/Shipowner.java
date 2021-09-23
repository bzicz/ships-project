import com.google.gson.annotations.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Shipowner extends GameObject {

    @Expose(serialize = true)
    private int capital;
    @Expose(serialize = true)
    private String name;
    @Expose(serialize = true)
    private java.util.List<Boat> boats;
    private Game game;

    public Shipowner(Game game, int x, int y, int capital, String name) {
        super(x, y);
        this.capital = capital;
        this.name = name;
        boats = new ArrayList<>();
        this.game = game;
    }

    @Override
    public void render(Graphics g) {
    for (Boat boat : boats){
        boat.render(g);
    }
    }

    @Override
    public void update(double dt) {
        for (Boat boat : boats){
            boat.update(dt);
        }
        tryOrderBoat();

    }
    private void tryOrderBoat(){
        Shipyard shipyard = Randomiser.getShipyard(game.getShipyards());
        Boat boat = Randomiser.getBoat(game, this);
        int cost = shipyard.calculateBoatCost(boat.getCapacity(), boat.getPetrolMax());
        if (Randomiser.chance(1) && cost <= capital){
            shipyard.orderBoat(boat);
            boats.add(boat);
            capital -= cost;
        }
    }
    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Boat> getBoats() {
        return boats;
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }
}
