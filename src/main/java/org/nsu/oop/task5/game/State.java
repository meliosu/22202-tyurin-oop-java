package org.nsu.oop.task5.game;

import org.nsu.oop.task5.game.exceptions.IllegalMoveException;
import org.nsu.oop.task5.game.exceptions.IllegalWallException;
import org.nsu.oop.task5.util.Player;
import org.nsu.oop.task5.util.Position;
import org.nsu.oop.task5.util.WallType;

import java.util.ArrayList;
import java.util.HashSet;

public class State {
    private Player currentPlayer;

    private Position firstPlayerPos;
    private Position secondPlayerPos;

    private int firstPlayerWalls;
    private int secondPlayerWalls;

    private WallType[][] walls;

    public State() {
        reset();
    }

    public void reset() {
        currentPlayer = Player.First;
        firstPlayerPos = new Position(4, 0);
        secondPlayerPos = new Position(4, 8);
        firstPlayerWalls = 10;
        secondPlayerWalls = 10;
        walls = new WallType[8][8];
    }

    public void placeWall(WallType wallType, int x, int y) throws IllegalWallException {
        if (x < 0 || y < 0 || x > 7 || y > 7) {
            throw new IllegalWallException("Can't place walls on border!");
        }

        if (!enoughWalls()) {
            throw new IllegalWallException("No walls left!");
        }

        if (walls[x][y] != null) {
            throw new IllegalWallException("Already has a wall");
        }

        if (hasCollisions(wallType, x, y)) {
            throw new IllegalWallException("Collides!");
        }

        walls[x][y] = wallType;

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

    private boolean hasCollisions(WallType wallType, int x, int y) {
        boolean hasCollisions = false;
        switch (wallType) {
            case Horizontal: {
                if (x != 0) hasCollisions |= walls[x - 1][y] == WallType.Horizontal;
                if (x != 7) hasCollisions |= walls[x + 1][y] == WallType.Horizontal;
                break;
            }

            case Vertical: {
                if (y != 0) hasCollisions |= walls[x][y - 1] == WallType.Vertical;
                if (y != 7) hasCollisions |= walls[x][y + 1] == WallType.Vertical;
                break;
            }
        }

        return hasCollisions;
    }

    public Player winningPlayer() {
        if (firstPlayerPos.y == 8) {
            return Player.First;
        } else if (secondPlayerPos.y == 0) {
            return Player.Second;
        } else {
            return null;
        }
    }

    private boolean isLegalPosition() {
        return isReachable(firstPlayerPos, 8, new HashSet<>()) &&
                isReachable(secondPlayerPos, 0, new HashSet<>());
    }

    private ArrayList<Position> movableCells(Position pos) {
        ArrayList<Position> cells = new ArrayList<>();

        for (Position neighbor : adjacentPositions(pos)) {
            if (!hasWallBetween(pos, neighbor)
                    && !firstPlayerPos.equals(neighbor) && !secondPlayerPos.equals(neighbor)) {
                cells.add(neighbor);
            }
        }

        return cells;
    }

    private ArrayList<Position> adjacentPositions(Position pos) {
        ArrayList<Position> positions = new ArrayList<>();

        if (pos.x != 0) positions.add(new Position(pos.x - 1, pos.y));
        if (pos.y != 0) positions.add(new Position(pos.x, pos.y - 1));
        if (pos.x != 8) positions.add(new Position(pos.x + 1, pos.y));
        if (pos.y != 8) positions.add(new Position(pos.x, pos.y + 1));

        return positions;
    }

    private boolean hasWallBetween(Position first, Position second) {
        if (first.x == second.x) {
            int x = first.x;
            int y = Math.min(first.y, second.y);
            return (x != 0 && walls[x - 1][y] == WallType.Horizontal) || (x != 8 && walls[x][y] == WallType.Horizontal);
        } else {
            int x = Math.min(first.x, second.x);
            int y = first.y;
            return (y != 0 && walls[x][y - 1] == WallType.Vertical) || (y != 8 && walls[x][y] == WallType.Vertical);
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

        for (Position newPos : movableCells(pos)) {
            reachable |= isReachable(newPos, rank, visited);
        }

        return reachable;
    }

    public void move(Player player, Position pos) throws IllegalMoveException {
        if (player != currentPlayer) {
            throw new IllegalMoveException();
        }

        ArrayList<Position> adjacent;

        if (currentPlayer == Player.First) {
            adjacent = movableCells(firstPlayerPos);
        } else {
            adjacent = movableCells(secondPlayerPos);
        }

        if (!adjacent.contains(pos)) {
            throw new IllegalMoveException();
        }

        switch (currentPlayer) {
            case First: {
                firstPlayerPos = pos;
                currentPlayer = Player.Second;
                break;
            }

            case Second: {
                secondPlayerPos = pos;
                currentPlayer = Player.First;
                break;
            }
        }
    }

    public ArrayList<Position> legalMoves() {
        if (currentPlayer == Player.First) {
            return movableCells(firstPlayerPos);
        } else {
            return movableCells(secondPlayerPos);
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getCurrentPlayerWallCount() {
        if (currentPlayer == Player.First) {
            return firstPlayerWalls;
        } else {
            return secondPlayerWalls;
        }
    }
}
