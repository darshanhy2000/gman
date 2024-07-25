package gman;

public enum Direction {
    N, E, S, W;

    public Direction turnLeft() {
        switch (this) {
            case N: return W;
            case E: return N;
            case S: return E;
            case W: return S;
            default: throw new IllegalArgumentException("Invalid direction");
        }
    }

    public Direction turnRight() {
        switch (this) {
            case N: return E;
            case E: return S;
            case S: return W;
            case W: return N;
            default: throw new IllegalArgumentException("Invalid direction");
        }
    }
}
