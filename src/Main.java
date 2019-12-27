import javax.swing.*;

class Main {

    public static void main(String[] args) {
        JFrame game = new JFrame();
        game.setTitle("2048 - Nowa gra");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(350, 420);
        game.setResizable(false);

        game.add(new Game2048());

        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}