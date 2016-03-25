package composite.ox.game.grid;

public enum Direction {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    private static int[][] offsets = {
            {0, 1}
    };

    public int[] getOffset()
    {
        return offsets[this.ordinal()];
    }
}
