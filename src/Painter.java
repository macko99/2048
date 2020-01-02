import java.awt.*;

class Painter {

    private static final Color BG_COLOR = new Color(187, 173, 160);
    private static final String FONT_NAME = "Arial";
    private static final int SQUARE_SIZE = 64;
    private static final int SQUARE_SPACING = 16;
    private final Game2048 game;

    Painter(Game2048 game) {
        this.game = game;
    }

    void paint(Graphics g, Square[][] squares) {
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, game.getSize().width, game.getSize().height);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                drawSquare(g, squares[x][y], x, y);
            }
        }
        drawScore(g);
        if (!game.getStatus().equals(GameStatus.RUNNING))
            drawStatusScreen(g);
    }

    private void drawStatusScreen(Graphics g) {
        g.setColor(new Color(86, 80, 80, 134));
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        g.setColor(new Color(238, 228, 218));
        g.setFont(new Font(FONT_NAME, Font.BOLD, 46));
        if (game.getStatus().equals(GameStatus.WON)) {
            g.drawString("Wygrałeś!", 60, 190);
        } else if (game.getStatus().equals(GameStatus.LOST)) {
            g.drawString("Przegrałeś!", 50, 190);
        }
        g.setFont(new Font(FONT_NAME, Font.PLAIN, 20));
        g.drawString("ponownie: ESC", 20, 360);
        drawScore(g);
    }

    private void drawScore(Graphics g) {
        g.setColor(new Color(238, 228, 218));
        g.setFont(new Font(FONT_NAME, Font.PLAIN, 20));
        g.drawString("Wynik: " + game.getScore(), 200, 360);
    }

    private void drawSquare(Graphics g, Square square, int x, int y) {
        int value = square.getValue();
        String stringValue = String.valueOf(value);
        int xPosition = squareCoordinates(x);
        int yPosition = squareCoordinates(y);

        g.setColor(square.getColor());
        g.fillRoundRect(xPosition, yPosition, SQUARE_SIZE, SQUARE_SIZE, 15, 15);

        g.setColor(square.getNumberFontColor());
        Font font = new Font(FONT_NAME, Font.BOLD, square.getNumberFontSize());
        g.setFont(font);
        int stringWidth = game.getFontMetrics(font).stringWidth(stringValue);
        int stringHeight = game.getFontMetrics(font).getHeight();
        if (value != 0) {
            g.drawString(stringValue, xPosition + (SQUARE_SIZE - stringWidth) / 2, yPosition + SQUARE_SIZE - (SQUARE_SIZE - stringHeight) / 2 - 8);
        }
    }

    private static int squareCoordinates(int arg) {
        return arg * (SQUARE_SPACING + SQUARE_SIZE) + SQUARE_SPACING;
    }
}
