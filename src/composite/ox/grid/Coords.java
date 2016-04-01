package composite.ox.grid;

public class Coords {
    private final int x;
    private final int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coords(int ... coords) {
        this.x = coords[0];
        this.y = coords.length > 1 ? coords[1] : 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return 0;
    }

    public Coords add(int ... offset) {
        return new Coords(this.x + offset[0], this.y + (offset.length > 1 ? offset[1] : 0));
    }

    public Coords add(Direction dir) {
        return new Coords(dir.applyOffsetTo(this.x, this.y));
    }
}
