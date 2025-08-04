package conveyor;

import org.springframework.stereotype.Component;

@Component
public class ConveyorState {
    private boolean running = true;

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = false;
    }
}
