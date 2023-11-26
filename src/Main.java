import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import entity.RepositoryCollection;
import ui.account.ChangePasswordView;
import ui.account.LoginView;
import ui.camp.listview.CampListViewStaff;
import ui.camp.listview.CampListViewStudent;
import ui.landingview.StaffMainView;
import ui.landingview.StudentMainView;
import ui.windows.*;

import java.io.IOException;
import java.util.Arrays;

import static consts.Config.titleLines;
import static consts.Config.titleLinesGoodBye;
/**
 * The main class representing the entry point of the UI application.
 * Manages terminal screens, displays animated title sequences, and handles user interface components.
 */
public class Main {
    /**
     * The main method responsible for initializing the application and managing user interface components.
     * @param args The command line arguments (unused in this context).
     */
    public static void main(String[] args) {
        RepositoryCollection.load();

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Screen animationScreen = null;
        Screen appScreen = null;

        TextColor greenColor = TextColor.ANSI.GREEN;
        TextColor redColor = TextColor.ANSI.RED_BRIGHT;

        try {
            Terminal terminal = defaultTerminalFactory.createTerminal();
            animationScreen = new TerminalScreen(terminal);
            animationScreen.startScreen();
            animationScreen.setCursorPosition(null);

            TextGraphics textGraphics = animationScreen.newTextGraphics();
            int screenWidth = animationScreen.getTerminalSize().getColumns();
            int screenHeight = animationScreen.getTerminalSize().getRows();
            int longestLineLength = Arrays.stream(titleLines).mapToInt(String::length).max().getAsInt();
            int titleHeight = titleLines.length;
            int midHorizontalPos = (screenWidth - longestLineLength) / 2;
            int midVerticalPos = (screenHeight - titleHeight) / 2;
            int bounceHeight = 8; // Height of the bounce
            int numBounces = 3; // Number of times the title bounces

            // Animation from top to middle
            for (int vPos = -titleHeight; vPos <= midVerticalPos; vPos++) {
                animationScreen.clear();
                for (int line = 0; line < titleLines.length; line++) {
                    textGraphics.setForegroundColor(greenColor); // Set the text color to green
                    textGraphics.putString(midHorizontalPos, vPos + line, titleLines[line]);
                }
                animationScreen.refresh();
                Thread.sleep(5);
            }

            // Bounce effect
            for (int bounce = 0; bounce < numBounces; bounce++) {
                // Move up
                for (int vPos = midVerticalPos; vPos > midVerticalPos - bounceHeight; vPos--) {
                    animationScreen.clear();
                    for (int line = 0; line < titleLines.length; line++) {
                        textGraphics.putString(midHorizontalPos, vPos + line, titleLines[line]);
                    }
                    bounceHeight /= 2;
                    animationScreen.refresh();
                    Thread.sleep(100);
                }
                for (int vPos = midVerticalPos - bounceHeight; vPos <= midVerticalPos; vPos++) {
                    animationScreen.clear();
                    for (int line = 0; line < titleLines.length; line++) {
                        textGraphics.putString(midHorizontalPos, vPos + line, titleLines[line]);
                    }
                    animationScreen.refresh();
                    Thread.sleep(100);
                }
            }

            for (int vPos = midVerticalPos; vPos <= screenHeight; vPos++) {
                animationScreen.clear();
                for (int line = 0; line < titleLines.length; line++) {
                    textGraphics.putString(midHorizontalPos, vPos + line, titleLines[line]);
                }
                animationScreen.refresh();
                Thread.sleep(5);
            }

            animationScreen.close();

            Terminal appTerminal = defaultTerminalFactory.createTerminal();
            appScreen = new TerminalScreen(appTerminal);
            appScreen.startScreen();
            appScreen.setCursorPosition(null);

            TerminalSize terminalSize = appScreen.getTerminalSize();
            for (int column = 0; column < terminalSize.getColumns(); column++) {
                for (int row = 0; row < terminalSize.getRows(); row++) {
                    appScreen.setCharacter(column, row, new TextCharacter(
                            ' ',
                            TextColor.ANSI.DEFAULT,
                            TextColor.ANSI.DEFAULT));
                }
            }

            Window LoginView = new LoginView(20, 54, 1, 2, 3);
            Window studentMainView = new StudentMainView(0, 5, 3);
            Window staffMainView = new StaffMainView(0, 4, 3);
            Window changePasswordView = new ChangePasswordView(0, 1, 2);
            Window CampListViewStaff = new CampListViewStaff(2);
            Window CampListViewStudent = new CampListViewStudent(1);
            WindowsManager windows = new WindowsManager(appScreen, 0, 0);

            windows.addWindow(LoginView);
            windows.addWindow(studentMainView);
            windows.addWindow(staffMainView);
            windows.addWindow(changePasswordView);
            windows.addWindow(CampListViewStaff);
            windows.addWindow(CampListViewStudent);

            appScreen.refresh();

            // Main application loop
            while (true) {
                KeyStroke keyStroke = appScreen.pollInput();
                if (keyStroke != null
                        && (keyStroke.getKeyType() == KeyType.Escape || keyStroke.getKeyType() == KeyType.EOF)) {
                    break;
                }
                if (keyStroke != null)
                    windows.keyStroke(keyStroke);

                TerminalSize newSize = appScreen.doResizeIfNecessary();
                if (newSize != null) {
                    appScreen.clear();
                    terminalSize = newSize;
                }

                String sizeLabel = "Terminal Size: " + terminalSize;
                TerminalPosition labelBoxTopLeft = new TerminalPosition(1, 1);
                TerminalSize labelBoxSize = new TerminalSize(sizeLabel.length() + 2, 3);
                TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft
                        .withRelativeColumn(labelBoxSize.getColumns() - 1);
                textGraphics = appScreen.newTextGraphics();
                textGraphics.fillRectangle(labelBoxTopLeft, labelBoxSize, ' ');

                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(labelBoxSize.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);

                textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
                textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
                textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
                textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(2),
                        Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

                textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), sizeLabel);

                windows.draw(0, 0);
                appScreen.refresh();
                Thread.yield();

            }

            appScreen.close();

            Terminal animeTerminal = defaultTerminalFactory.createTerminal();
            animationScreen = new TerminalScreen(animeTerminal);
            animationScreen.startScreen();
            animationScreen.setCursorPosition(null);

            textGraphics = animationScreen.newTextGraphics();
            screenWidth = animationScreen.getTerminalSize().getColumns();
            screenHeight = animationScreen.getTerminalSize().getRows();
            longestLineLength = Arrays.stream(titleLinesGoodBye).mapToInt(String::length).max().getAsInt();
            titleHeight = titleLinesGoodBye.length;
            midHorizontalPos = (screenWidth - longestLineLength) / 2;
            midVerticalPos = (screenHeight - titleHeight) / 2;
            bounceHeight = 2; // Height of the bounce
            numBounces = 1; // Number of times the title bounces

            for (int vPos = -titleHeight; vPos <= midVerticalPos; vPos++) {
                animationScreen.clear();
                for (int line = 0; line < titleLinesGoodBye.length; line++) {
                    textGraphics.setForegroundColor(redColor); // Set the text color to red
                    textGraphics.putString(midHorizontalPos, vPos + line, titleLinesGoodBye[line]);
                }
                animationScreen.refresh();
                Thread.sleep(5);
            }

            // Bounce effect
            for (int bounce = 0; bounce < numBounces; bounce++) {
                // Move up
                for (int vPos = midVerticalPos; vPos > midVerticalPos - bounceHeight; vPos--) {
                    animationScreen.clear();
                    for (int line = 0; line < titleLinesGoodBye.length; line++) {
                        textGraphics.putString(midHorizontalPos, vPos + line, titleLinesGoodBye[line]);
                    }
                    animationScreen.refresh();
                    Thread.sleep(50);
                }
                // Move down
                for (int vPos = midVerticalPos - bounceHeight; vPos <= midVerticalPos; vPos++) {
                    animationScreen.clear();
                    for (int line = 0; line < titleLinesGoodBye.length; line++) {
                        textGraphics.putString(midHorizontalPos, vPos + line, titleLinesGoodBye[line]);
                    }
                    animationScreen.refresh();
                    Thread.sleep(50);
                }
            }

            // Continue animation to bottom
            for (int vPos = midVerticalPos; vPos <= screenHeight; vPos++) {
                animationScreen.clear();
                for (int line = 0; line < titleLinesGoodBye.length; line++) {
                    textGraphics.putString(midHorizontalPos, vPos + line, titleLinesGoodBye[line]);
                }
                animationScreen.refresh();
                Thread.sleep(5);
            }

            animationScreen.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (animationScreen != null) {
                    animationScreen.close();
                }
                if (appScreen != null) {
                    RepositoryCollection.save();
                    appScreen.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
