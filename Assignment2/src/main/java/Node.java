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
        return this.distance(n) <= Network.R;
    }

    double distance(Node n) {
        return this.distance(this.x, this.y, n.x, n.y);
    }

    // Following algorithm from: (https://blog.demofox.org/2017/10/01/calculating-the-distance-between-points-in-wrap-around-toroidal-space/)
    double distance(double n1_x, double n1_y, double n2_x, double n2_y) {
        double dx = Math.abs(n1_x  - n2_x);
        double dy = Math.abs(n1_y  - n2_y);

        if (dx > Network.MAX_X / 2) {
            dx = Network.MAX_X - dx;
        }

        if (dy > Network.MAX_X / 2) {
            dy = Network.MAX_X - dy;
        }

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy,2));
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
