import com.google.gson.annotations.*;

import java.awt.*;

public class Container extends GameObject {

   private Harbor sourceHarbor;
   private Harbor destinationHarbor;
    @Expose(serialize = true)
   private ContentType contentType;

    public Container(int x, int y, ContentType contentType) {
        super(x, y);
        this.contentType = contentType;
    }


    @Override
    public void render(Graphics g) {

    }

    @Override
    public void update(double dt) {

    }

    public Harbor getSourceHarbor() {
        return sourceHarbor;
    }

    public void setSourceHarbor(Harbor sourceHarbor) {
        this.sourceHarbor = sourceHarbor;
    }

    public Harbor getDestinationHarbor() {
        return destinationHarbor;
    }

    public void setDestinationHarbor(Harbor destinationHarbor) {
        this.destinationHarbor = destinationHarbor;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
