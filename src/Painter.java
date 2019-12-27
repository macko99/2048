import java.awt.*;

class Painter {

    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 64;
    private static final int TILES_MARGIN = 16;
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
    }

    private void drawSquare(Graphics g, Square square, int x, int y) {
        int value = square.getValue();
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);

        g.setColor(square.getColor());
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);

        g.setColor(square.getNumberColor());
        int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
        Font font = new Font(FONT_NAME, Font.BOLD, size);
        g.setFont(font);
        String s = String.valueOf(value);
        final FontMetrics fm = game.getFontMetrics(font);
        final int w = fm.stringWidth(s);
        final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];
        if (value != 0)
            g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);

        if (!game.getStatus().equals(GameStatus.RUNNING)) {
            g.setColor(new Color(255, 255, 255, 30));
            g.fillRect(0, 0, game.getWidth(), game.getHeight());
            g.setColor(new Color(78, 139, 202));
            g.setFont(new Font(FONT_NAME, Font.BOLD, 48));
            if (game.getStatus().equals(GameStatus.WON)) {
                g.drawString("Wygrałeś!", 68, 150);
            }
            if (game.getStatus().equals(GameStatus.LOST)) {
                g.drawString("Koniec gry", 50, 130);
                g.drawString("Przegrałeś!", 64, 200);
            }
        }

        g.setFont(new Font(FONT_NAME, Font.PLAIN, 18));
        g.drawString("Score: " + game.getScore(), 200, 365);
    }

    private static int offsetCoors(int arg) {
        return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
    }
}
