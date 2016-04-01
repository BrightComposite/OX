package composite.ox.grid;

import java.util.ArrayList;

public class Grid<T> {
    private final ArrayList<T> cells;
    private int rank;

    public Grid(int rank) {
        this.cells = new ArrayList<>();
        this.rank = rank;

        while(this.cells.size() < this.rank * this.rank) this.cells.add(null);
    }

    public int getRank() {
        return this.rank;
    }

    public T get(final Coords coords) {
        return this.cells.get(coords.getY() * rank + coords.getX());
    }

    public T get(int x, int y) {
        return this.cells.get(y * rank + x);
    }

    public void set(T element, final Coords coords) {
        this.cells.set(coords.getY() * rank + coords.getX(), element);
    }

    public void set(T element, int x, int y) {
        this.cells.set(y * rank + x, element);
    }

    public void fill(T element) {
        for(int i = 0; i < this.rank * this.rank; ++i)
            this.cells.set(i, element);
    }

    public boolean includes(final Coords coords) {
        return coords.getX() >= 0 && coords.getY() >= 0 && coords.getX() < rank && coords.getY() < rank;
    }
}
