package composite.ox.game.grid;

public class Coords {
    private int [] coords;

    Coords(int x, int y) {
        this.coords[0] = x;
        this.coords[1] = y;
    }

    Coords(int [] coords) {
        this.coords = coords.clone();
    }

    public int getX() {
        return this.coords[0];
    }

    public int getY() {
        return this.coords[1];
    }

    public int getZ() {
        return 0;
    }

    public Coords add(int ... offset) {
        return new Coords(this.coords[0] + offset[0], this.coords[1] + offset[1]);
    }

    public Coords add(Direction dir) {
        return new Coords(dir.applyOffsetTo(this.coords));
    }
}
