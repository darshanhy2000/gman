package gman;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GMan {
    private static final int GRID_SIZE = 6;
    private static final int TURN_COST = 5;
    private static final int MOVE_COST = 10;

    private Point position;
    private Direction direction;
    private int power;

    public GMan(Point position, Direction direction, int power) {
        this.position = position;
        this.direction = direction;
        this.power = power;
    }

    public int calculateRemainingPower(Point destination) {
        Set<State> visited = new HashSet<>();
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(position, direction, power));

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            Point currentPos = currentState.position;
            Direction currentDir = currentState.direction;
            int currentPower = currentState.power;

            if (currentPos.equals(destination)) {
                return currentPower;
            }

            State visitedState = new State(currentPos, currentDir, currentPower);
            if (visited.contains(visitedState)) {
                continue;
            }
            visited.add(visitedState);

            // Move forward
            Point nextPos = moveForward(currentPos, currentDir);
            if (isValid(nextPos) && currentPower >= MOVE_COST) {
                queue.add(new State(nextPos, currentDir, currentPower - MOVE_COST));
            }

            // Turn left
            Direction leftDir = currentDir.turnLeft();
            if (currentPower >= TURN_COST) {
                queue.add(new State(currentPos, leftDir, currentPower - TURN_COST));
            }

            // Turn right
            Direction rightDir = currentDir.turnRight();
            if (currentPower >= TURN_COST) {
                queue.add(new State(currentPos, rightDir, currentPower - TURN_COST));
            }
        }

        return -1; // If no path is found
    }

    private Point moveForward(Point position, Direction direction) {
        switch (direction) {
            case N: return new Point(position.x, position.y + 1);
            case E: return new Point(position.x + 1, position.y);
            case S: return new Point(position.x, position.y - 1);
            case W: return new Point(position.x - 1, position.y);
            default: throw new IllegalArgumentException("Invalid direction");
        }
    }

    private boolean isValid(Point position) {
        return position.x >= 0 && position.x < GRID_SIZE && position.y >= 0 && position.y < GRID_SIZE;
    }

    private static class State {
        Point position;
        Direction direction;
        int power;

        State(Point position, Direction direction, int power) {
            this.position = position;
            this.direction = direction;
            this.power = power;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return position.equals(state.position) && direction == state.direction && power == state.power;
        }

        @Override
        public int hashCode() {
            int result = position.hashCode();
            result = 31 * result + direction.hashCode();
            result = 31 * result + power;
            return result;
        }
    }
}
