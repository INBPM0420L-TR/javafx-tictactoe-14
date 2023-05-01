package tictactoe.model;

import lombok.Builder;
import lombok.Value;

import java.util.Arrays;
import java.util.Optional;

@Value
@Builder(toBuilder = true)
public class TicTacToeState {
    public static final int SIZE = 3;

    Value[][] board;
    Value nextPlayer;

    public TicTacToeState() {
        nextPlayer = Value.BLUE;
        board = new Value[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            Arrays.fill(board[row], Value.EMPTY);
        }
    }

    public Optional<Value> getWinner() {
        boolean hasEmpty = false;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == Value.EMPTY) {
                    hasEmpty = true;
                    continue;
                }

                try {
                    if (board[row][col] == board[row][col + 1]
                            && board[row][col] == board[row][col + 2]) {
                        return Optional.of(board[row][col]);
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }

                try {
                    if (board[row][col] == board[row + 1][col]
                            && board[row][col] == board[row + 2][col]) {
                        return Optional.of(board[row][col]);
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }

                try {
                    if (board[row][col] == board[row + 1][col + 1]
                            && board[row][col] == board[row + 2][col + 2]) {
                        return Optional.of(board[row][col]);
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }

                try {
                    if (board[row][col] == board[row + 1][col - 1]
                            && board[row][col] == board[row + 2][col - 2]) {
                        return Optional.of(board[row][col]);
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return hasEmpty ? Optional.empty() : Optional.of(Value.EMPTY);
    }

    public boolean canMove(
            final int row,
            final int col) {
        try {
            return getWinner().isEmpty() && board[row][col] == Value.EMPTY;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public TicTacToeState doMove(
            final int row,
            final int col) {

        final var newBoard = new Value[SIZE][];
        for (int i = 0; i < SIZE; i++) {
            newBoard[i] = Arrays.copyOf(board[i], SIZE);
        }
        newBoard[row][col] = nextPlayer;
        return toBuilder()
                .board(newBoard)
                .nextPlayer(switch (nextPlayer) {
                    case BLUE -> Value.RED;
                    case RED -> Value.BLUE;
                    default -> throw new IllegalStateException();
                })
                .build();
    }

    public enum Value {
        EMPTY, RED, BLUE
    }
}
