/**
 * Created by lawzoom on 5/22/17.
 */
import java.awt.image.BufferedImage;

public class AnFrame {

    private BufferedImage frame;
    private int duration;

    public AnFrame(BufferedImage frame, int duration) {
        this.frame = frame;
        this.duration = duration;
    }

    public BufferedImage getFrame() {
        return frame;
    }

    public void setFrame(BufferedImage frame) {
        this.frame = frame;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
