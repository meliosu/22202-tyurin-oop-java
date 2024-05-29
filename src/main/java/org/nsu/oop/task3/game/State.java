package org.nsu.oop.task3.game;

import java.util.ArrayList;
import java.util.HashSet;

public class State {
    public enum Player {
        First,
        Second,
    }

    public enum Wall {
        Horizontal,
        Vertical,
    }

    private Player currentPlayer;

    private Position firstPlayerPos;
    private Position secondPlayerPos;

    private int firstPlayerWalls;
    private int secondPlayerWalls;

    private final Wall[][] walls;

    public State() {
        currentPlayer = Player.First;

        // maybe need to change positions
        firstPlayerPos = new Position(4, 0);
        secondPlayerPos = new Position(4, 8);

        firstPlayerWalls = 10;
        secondPlayerWalls = 10;

        walls = new Wall[8][8];

        // maybe unnecessary to initialize with null
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                walls[x][y] = null;
            }
        }
    }

    public void placeWall(Wall wall, int x, int y) throws IllegalWallException {
        if (!enoughWalls()) {
            throw new IllegalWallException("No walls left!");
        }

        if (walls[x][y] != null) {
            throw new IllegalWallException();
        }

        if (hasCollisions(wall, x, y)) {
            throw new IllegalWallException();
        }

        walls[x][y] = wall;

        if (!isLegalPosition()) {
            walls[x][y] = null;
            throw new IllegalWallException("Illegal Wall!");
        }

        switch (currentPlayer) {
            case First: {
                firstPlayerWalls--;
                currentPlayer = Player.Second;
                break;
            }
            case Second: {
                secondPlayerWalls--;
                currentPlayer = Player.First;
                break;
            }
        }
    }

    private boolean enoughWalls() {
        return currentPlayer == Player.First && firstPlayerWalls != 0
                || currentPlayer  == Player.Second && secondPlayerWalls != 0;
    }

    private boolean hasCollisions(Wall wall, int x, int y) {
        boolean hasCollisions = false;

        switch (wall) {
            case Horizontal: {
                if (x != 0) {
                    hasCollisions |= walls[x - 1][y] == Wall.Horizontal;
                }

                if (x != 7) {
                    hasCollisions |= walls[x + 1][y] == Wall.Horizontal;
                }

                break;
            }

            case Vertical: {
                if (y != 0) {
                    hasCollisions |= walls[x][y - 1] == Wall.Vertical;
                }

                if (y != 7) {
                    hasCollisions |= walls[x][y + 1] == Wall.Vertical;
                }

                break;
            }
        }

        return hasCollisions;
    }

    private Player winningPlayer() {
        if (firstPlayerPos.y == 8) {
            return Player.First;
        } else if (secondPlayerPos.y == 0) {
            return Player.Second;
        } else {
            return null;
        }
    }

    // bfs for possible paths to victory
    private boolean isLegalPosition() {
        HashSet<Position> visitedCells = new HashSet<>();

        if (currentPlayer == Player.First) {
            return isReachable(firstPlayerPos, 8, visitedCells);
        } else {
            return isReachable(secondPlayerPos, 0, visitedCells);
        }
    }

    private ArrayList<Position> adjacentCells(Position pos) {
        ArrayList<Position> cells = new ArrayList<>();

        for (Position neighbor : adjacentPositions(pos)) {
            if (!hasWallBetween(pos, neighbor)) {
                cells.add(neighbor);
            }
        }

        return cells;
    }

    private ArrayList<Position> adjacentPositions(Position pos) {
        ArrayList<Position> positions = new ArrayList<>();

        if (pos.x != 0) {
            positions.add(new Position(pos.x - 1, pos.y));
        }

        if (pos.y != 0) {
            positions.add(new Position(pos.x, pos.y - 1));
        }

        if (pos.x != 8) {
            positions.add(new Position(pos.x + 1, pos.y));
        }

        if (pos.y != 8) {
            positions.add(new Position(pos.x, pos.y + 1));
        }

        return positions;
    }

    // need to invert
    private boolean hasWallBetween(Position first, Position second) {
        if (first.x == second.x) {
            int x = first.x;
            int y = Math.min(first.y, second.y);
            return (x == 0 || walls[x - 1][y] == Wall.Horizontal) || (x == 8 || walls[x][y] == Wall.Horizontal);
        } else {
            int x = Math.min(first.x, second.x);
            int y = first.y;
            return (y == 0 || walls[x][y - 1] == Wall.Vertical) || (y == 8 || walls[x][y] == Wall.Vertical);
        }
    }

    private boolean isReachable(Position pos, int rank, HashSet<Position> visited) {
        if (pos.y == rank) {
            return true;
        }

        if (visited.contains(pos)) {
            return false;
        }

        visited.add(pos);

        boolean reachable = false;

        for (Position newPos : adjacentCells(pos)) {
            reachable |= isReachable(newPos, rank, visited);
        }

        return reachable;
    }
}
