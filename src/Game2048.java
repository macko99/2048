import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Game2048 extends JPanel {
    private final Square[][] squares = new Square[4][4];
    private final Painter painter;
    private int score;
    private GameStatus status = GameStatus.RUNNING;

    Game2048() {
        painter = new Painter(this);
        setPreferredSize(new Dimension(350, 420));
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (noMove())
                    status = GameStatus.LOST;
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    resetGame();
                if (status.equals(GameStatus.RUNNING)) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            play(Direction.LEFT);
                            break;
                        case KeyEvent.VK_RIGHT:
                            play(Direction.RIGHT);
                            break;
                        case KeyEvent.VK_DOWN:
                            play(Direction.DOWN);
                            break;
                        case KeyEvent.VK_UP:
                            play(Direction.UP);
                            break;
                    }
                }
                if (!status.equals(GameStatus.WON) && noMove()) {
                    status = GameStatus.LOST;
                }
                repaint();
            }
        });
        resetGame();
    }

    private void resetGame() {
        score = 0;
        status = GameStatus.RUNNING;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squares[i][j] = new Square();
            }
        }
        addSquare();
        addSquare();
    }

    private void play(Direction direction) {
        boolean needAddTile = false;

        for (int i = 0; i < 4; i++) {
            Square[] line = getLine(direction, i);
            Square[] merged = mergeLine(moveLine(line));
            if (!Arrays.equals(line, merged)) {
                setLine(direction, i, merged);
                if (!needAddTile)
                    needAddTile = true;
            }
        }
        if (needAddTile)
            addSquare();
    }

    private Square[] getLine(Direction direction, int i) {
        Square[] line = new Square[4];
        for (int j = 0; j < 4; j++) {
            switch (direction) {
                case UP:
                    line[j] = squares[i][j];
                    break;
                case DOWN:
                    line[j] = squares[i][3 - j];
                    break;
                case LEFT:
                    line[j] = squares[j][i];
                    break;
                case RIGHT:
                    line[j] = squares[3 - j][i];
                    break;
            }
        }
        return line;
    }

    private void setLine(Direction direction, int i, Square[] merged) {
        for (int j = 0; j < 4; j++) {
            switch (direction) {
                case UP:
                    squares[i][j] = merged[j];
                    break;
                case DOWN:
                    squares[i][3 - j] = merged[j];
                    break;
                case LEFT:
                    squares[j][i] = merged[j];
                    break;
                case RIGHT:
                    squares[3 - j][i] = merged[j];
                    break;
            }
        }
    }

    private void addSquare() {
        List<Square> list = availableSpace();
        if (!list.isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            list.get(index).setValue(Math.random() < 0.9 ? 2 : 4);
        }
    }

    private List<Square> availableSpace() {
        List<Square> list = new ArrayList<>(16);
        for (Square[] line : squares) {
            for (Square square : line) {
                if (square.isEmpty()) {
                    list.add(square);
                }
            }
        }
        return list;
    }

    private boolean noMove() {
        if (availableSpace().size() != 0) {
            return false;
        }
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                Square square = squares[x][y];
                if ((x < 3 && square.getValue() == squares[x + 1][y].getValue()) || ((y < 3) && square.getValue() == squares[x][y + 1].getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    private Square[] moveLine(Square[] oldLine) {
        LinkedList<Square> hasValue = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            if (!oldLine[i].isEmpty()) {
                hasValue.addLast(oldLine[i]);
            }
        }
        if (hasValue.size() == 0) {
            return oldLine;
        }
        return LineValidator(hasValue);
    }

    private Square[] mergeLine(Square[] oldLine) {
        LinkedList<Square> newLine = new LinkedList<>();
        for (int i = 0; i < 4 && !oldLine[i].isEmpty(); i++) {
            if (i < 3 && oldLine[i].getValue() == oldLine[i + 1].getValue()) {
                score += oldLine[i].getValue() * 2;
                i++;
                newLine.add(new Square(oldLine[i].getValue() * 2));
                if (oldLine[i].getValue() * 2 == 16) {
                    status = GameStatus.WON;
                }
            } else {
                newLine.add(oldLine[i]);
            }
        }
        if (newLine.size() == 0) {
            return oldLine;
        }
        return LineValidator(newLine);
    }

    private Square[] LineValidator(LinkedList<Square> line) {
        while (line.size() != 4) {
            line.add(new Square());
        }
        return line.toArray(new Square[4]);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        painter.paint(g, squares);
    }

    int getScore() {
        return this.score;
    }

    GameStatus getStatus() {
        return this.status;
    }

}