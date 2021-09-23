import com.google.gson.annotations.*;

import java.awt.*;
import java.time.*;
import java.util.*;
import java.util.List;

public class Game extends GameObject{

    @Expose(serialize = true)
    private Date moment;
    @Expose(serialize = true)
    private List<Harbor> harbors;
    @Expose(serialize = true)
    private List<Shipyard> shipyards;
    @Expose(serialize = true)
    private List<Shipowner> shipowners;


    public Game (){
        super(0,0);
        harbors = new ArrayList<>();
        shipyards = new ArrayList<>();
        shipowners = new ArrayList<>();
        shipyards.add(new Shipyard(this,0,400, 2,4,62,398));
        harbors.add(new Harbor(0,0,10,42,42));
        harbors.add(new Harbor(600,0,10,642,42));
        shipowners.add(new Shipowner(this, 0,0, 1000000, "Lucek"));
        moment = new Date();

    }

    @Override
    public void render(Graphics g) {
        for (Harbor harbor : harbors){
            harbor.render(g);
        }
        for (Shipyard shipyard : shipyards){
            shipyard.render(g);
        }
        for (Shipowner shipowner : shipowners){
            shipowner.render(g);
        }

    }

    public void update(double dt) {

        for (Harbor harbor : harbors) {
            harbor.update(dt);
        }
        for (Shipyard shipyard : shipyards) {
            shipyard.update(dt);
        }
        for (Shipowner shipowner : shipowners){
            shipowner.update(dt);
        }

    }


    public List<Harbor> getHarbors() {
        return harbors;
    }

    public List<Shipyard> getShipyards() {
        return shipyards;
    }

    public List<Shipowner> getShipowners() {
        return shipowners;
    }

    public void setCurrentMoment() {
        moment = new Date();
    }
}

