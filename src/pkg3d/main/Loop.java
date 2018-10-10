package pkg3d.main;

/**
 *
 * @author Alex
 */
public abstract class Loop {
    
    private boolean runFlag = false;

    /**
     * Begin the game loop
     *
     * @param delta time between logic updates (in seconds)
     */
    public void run(double delta) {
        runFlag = true;

        startup();
        // convert the time to seconds
        double nextTime = (double) System.nanoTime() / 1000000000.0;
        double maxTimeDiff = 0.5;
        int skippedFrames = 1;
        int maxSkippedFrames = 5;
        while (runFlag) {
            // convert the time to seconds
            double currTime = (double) System.nanoTime() / 1000000000.0;
            if ((currTime - nextTime) > maxTimeDiff) {
                nextTime = currTime;
                
            }
            
            if (currTime >= nextTime) {
                
                // assign the time for the next update
                nextTime += delta;
                update();
                if ((currTime < nextTime) || (skippedFrames > maxSkippedFrames)) {
                    render();
                    skippedFrames = 1;
                } else {
                    skippedFrames++;
                }
            } else {
                // calculate the time to sleep
                int sleepTime = (int) (1000.0 * (nextTime - currTime));
                // sanity check
                if (sleepTime > 0) {
                    // sleep until the next update
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        // do nothing
                    }
                }
            }
        }
        shutdown();
    }

    public void stop() {
        runFlag = false;
    }

    public abstract void startup();
    public abstract void shutdown();
    public abstract void update();
    public abstract void render();
}

