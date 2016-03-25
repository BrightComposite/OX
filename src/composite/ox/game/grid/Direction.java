package composite.ox.game.grid;

enum Direction {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    private static int[][] offsets = {
            { 0,  1},
            { 1,  1},
            { 1,  0},
            { 1, -1},
            { 0, -1},
            {-1, -1},
            {-1,  0},
            {-1,  1}
    };

    private static Direction[][] pairs = {
            {NORTH,      SOUTH},
            {NORTH_EAST, SOUTH_WEST},
            {EAST,       WEST},
            {SOUTH_EAST, NORTH_WEST}
    };

    public static Direction[][] getPairs()
    {
        return pairs;
    }

    public int[] getOffset()
    {
        return offsets[this.ordinal()];
    }

    public int[] applyOffsetTo(int ... coords)
    {
        return new int[]{coords[0] + offsets[this.ordinal()][0], coords[1] + offsets[this.ordinal()][1]};
    }

    public Coords applyOffsetTo(Coords coords)
    {
        return coords.add(offsets[this.ordinal()]);
    }

    public Direction getCounterDirection()
    {
        return Direction.values()[(this.ordinal() + 4) % 8];
    }
}
