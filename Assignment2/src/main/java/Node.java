class Node {
    // Defined through Mobility Models
    private double x;
    private double y;
    private int endTime = 0;
    private double angle;
    private double speed;

    Node() {
        this.x = Math.random() * Network.MAX_X;
        this.y = Math.random() * Network.MAX_X;
    }

    /**
     * Update the position of the node given time `t`
     *
     * @param t
     */
    void RandomDirection(int t) {
        // IF is paused THEN nothing is required
        if (t > endTime && t < endTime + Network.PAUSE_TIME) {
            return;
        }

        // IF needs new THEN randomly set Network
        if (t >= endTime + Network.PAUSE_TIME) {
            this.angle = Math.random() * 2 * Math.PI;
            this.speed = Math.random() * (Network.MAX_V + Network.MIN_V) + Network.MIN_V;
            this.endTime = t + (int) (Math.random() * (Network.MAX_TIME_SPAN - 1)) + 1;
        }

        // IF is moving THEN update location
        this.x += Math.cos(this.angle) * this.speed;
        this.y += Math.sin(this.angle) * this.speed;

        // Wrap around so that nodes remain within the bounds of the 2D torus
        this.x = (this.x + Network.MAX_X) % Network.MAX_X;
        this.y = (this.y + Network.MAX_X) % Network.MAX_X;
    }

    boolean canCommunicateWith(Node n) {
        double d = Math.sqrt(Math.pow(this.x + n.x, 2) + Math.pow(this.y + n.y ,2));
        return d <= Network.R;
    }
}
