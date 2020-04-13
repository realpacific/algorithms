package dynamic;

import java.util.Arrays;

/**
 * Implement the"paint fill" function that one might see on many image editing programs.
 * That is, given a screen (represented by a two-dimensional array of colors), a point, and a new color,
 * fill in the surrounding area until the color changes from the original color
 */
public class PaintFill {

    enum Color {Black, White, Red, SBlue, Green}

    public static void main(String[] args) {
        Color[][] screen = new Color[10][10];

        fill(screen);

        for (int row = 0; row < screen.length; row++) {
            for (int col = 0; col < screen[row].length; col++) {
                if (col > 2 && col < 8 && row > 2 && row < 6) {
                    screen[row][col] = Color.SBlue;
                }
            }
        }

        printScreen(screen);
        paintFill(screen, 3, 5, Color.Green);
        printScreen(screen);


        printScreen(screen);
        paintFill(screen, 0, 0, Color.Black);
        printScreen(screen);
    }

    private static void fill(Color[][] screen) {
        for (Color[] colors : screen) {
            Arrays.fill(colors, Color.White);
        }
    }

    private static void printScreen(Color[][] screen) {
        System.out.println("________");
        for (Color[] colors : screen) {
            for (Color color : colors) {
                System.out.print(color + " ");
            }
            System.out.println();
        }
    }

    /**
     * Only reads the color to be replaced i.e screen[row][col]
     */
    private static void paintFill(Color[][] screen, int row, int col, Color newColor) {
        paintFill(screen, row, col, screen[row][col], newColor);
    }

    /**
     * Mutates the screen with newColor by replacing the old color
     */
    private static void paintFill(Color[][] screen, int row, int col, Color colorToBeReplaced, Color newColor) {
        if (col < 0 || col >= screen[0].length || row < 0 || row >= screen.length) {
            return;
        }
        // Do nothing if the color is different than the [colorToBeReplaced]
        if (screen[row][col] != colorToBeReplaced) {
            return;
        }
        // Change color
        screen[row][col] = newColor;
        // Go left, right, top, down
        paintFill(screen, row + 1, col, colorToBeReplaced, newColor);
        paintFill(screen, row - 1, col, colorToBeReplaced, newColor);
        paintFill(screen, row, col + 1, colorToBeReplaced, newColor);
        paintFill(screen, row, col - 1, colorToBeReplaced, newColor);
    }


}
