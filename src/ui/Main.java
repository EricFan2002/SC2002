package ui;

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
import ui.AccountView.ChangePasswordView;
import ui.AccountView.LoginView;
import ui.CampModificationView.CreateCampView;
import ui.CampListView.CampListViewStaff;
import ui.CampListView.CampListViewStudent;
import ui.LandingView.StaffMainView;
import ui.LandingView.StudentMainView;
import ui.windows.*;

import java.io.IOException;
import java.util.Arrays;

public class Main {
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

            String[] titleLines = {
                    "███    ██ ████████ ██    ██          ██████  █████  ███    ███ ███████ ",
                    "████   ██    ██    ██    ██         ██      ██   ██ ████  ████ ██      ",
                    "██ ██  ██    ██    ██    ██         ██      ███████ ██ ████ ██ ███████ ",
                    "██  ██ ██    ██    ██    ██         ██      ██   ██ ██  ██  ██      ██ ",
                    "██   ████    ██     ██████           ██████ ██   ██ ██      ██ ███████ "
            };

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
                // Move down
                for (int vPos = midVerticalPos - bounceHeight; vPos <= midVerticalPos; vPos++) {
                    animationScreen.clear();
                    for (int line = 0; line < titleLines.length; line++) {
                        textGraphics.putString(midHorizontalPos, vPos + line, titleLines[line]);
                    }
                    animationScreen.refresh();
                    Thread.sleep(100);
                }
            }

            // Continue animation to bottom
            for (int vPos = midVerticalPos; vPos <= screenHeight; vPos++) {
                animationScreen.clear();
                for (int line = 0; line < titleLines.length; line++) {
                    textGraphics.putString(midHorizontalPos, vPos + line, titleLines[line]);
                }
                animationScreen.refresh();
                Thread.sleep(5);
            }

            animationScreen.close();

            // After the animation, switch to the application screen
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

            // Rest of your application initialization code...
            Window LoginView = new LoginView(20, 54, 1, 2, 3, 6);
            Window studentMainView = new StudentMainView(0, 5, 3);
            Window staffMainView = new StaffMainView(0, 4, 3);
            Window changePasswordView = new ChangePasswordView(0, 1, 2);
            Window CampListViewStaff = new CampListViewStaff(2);
            Window CampListViewStudent = new CampListViewStudent(1);
            Window createCampView = new CreateCampView(5);
            WindowsManager windows = new WindowsManager(appScreen, 0, 0);

            windows.addWindow(LoginView);
            windows.addWindow(studentMainView);
            windows.addWindow(staffMainView);
            windows.addWindow(changePasswordView);
            windows.addWindow(CampListViewStaff);
            windows.addWindow(CampListViewStudent);
            windows.addWindow(createCampView);

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
                // This isn't really needed as we are overwriting everything below anyway, but
                // just for demonstrative purpose
                textGraphics.fillRectangle(labelBoxTopLeft, labelBoxSize, ' ');

                /*
                 * Draw horizontal lines, first upper then lower
                 */
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(labelBoxSize.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);

                /*
                 * Manually do the edges and (since it's only one) the vertical lines, first on
                 * the left then on the right
                 */
                textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
                textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
                textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
                textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(2),
                        Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

                /*
                 * Finally put the text inside the box
                 */
                textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), sizeLabel);

                /*
                 * Ok, we are done and can display the change. Let's also be nice and allow the
                 * OS to schedule other
                 * threads so we don't clog up the core completely.
                 */
                windows.draw(0, 0);
                appScreen.refresh();
                Thread.yield();

                /*
                 * Every time we call refresh, the whole terminal is NOT re-drawn. Instead, the
                 * Screen will compare the
                 * back and front buffers and figure out only the parts that have changed and
                 * only update those. This is
                 * why in the code drawing the size information box above, we write it out every
                 * time we loop but it's
                 * actually not sent to the terminal except for the first time because the
                 * Screen knows the content is
                 * already there and has not changed. Because of this, you should never use the
                 * underlying Terminal object
                 * when working with a Screen because that will cause modifications that the
                 * Screen won't know about.
                 */
            }

            appScreen.close();

            Terminal animeTerminal = defaultTerminalFactory.createTerminal();
            animationScreen = new TerminalScreen(animeTerminal);
            animationScreen.startScreen();
            animationScreen.setCursorPosition(null);

            titleLines = new String[] {
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⠴⠒⠤⣄⡀⠀⠀⠀⠀⢠⣾⠉⠉⠉⠉⠑⠒⠦⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠋⠀⠀⠀⠀⠀⠉⠲⡄⠀⢠⠏⡏⠀⠀⠀⠀⠀⠀⠀⠀⠉⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⢤⣤⣀⡀⠀⠀⠀⠀⠀⢰⡏⠀⠀⠀⠀⠀⠀⠀⠀⠘⢆⢸⠀⡇⠀⠀⠀⢀⣀⠀⠀⠀⠀⠀⢸⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠏⠀⠀⠀⠀⠈⠙⠢⣄⠀⠀⣿⠀⠀⠀⢰⣿⣷⣆⠀⠀⠀⠘⣾⠀⠇⠀⠀⠀⢾⣿⣿⣦⠀⠀⠀⠀⢻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⢀⣀⡤⣄⠀⠀⠀⣼⡇⠀⠀⠀⢀⣀⠀⠀⠀⠈⠳⣴⢿⡄⠀⠀⢸⡿⣿⢹⠀⠀⠀⠀⣿⠀⡆⠀⠀⠀⣼⠻⣇⣸⠀⠀⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⢀⡴⠚⠉⠀⠀⠈⠳⡄⠀⣿⡇⠀⠀⠀⣿⣿⡷⡄⠀⠀⠀⢹⣆⢧⠀⠀⠀⠙⠿⠋⠀⠀⠀⠀⣿⠀⡇⠀⠀⠀⠙⠛⠉⠁⠀⠀⠀⢠⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⢠⠟⠀⠀⠀⠀⢀⣤⣾⣷⣀⡇⢣⠀⠀⠀⢻⣟⣄⣷⠀⠀⠀⠈⣿⣾⣆⠀⠀⠀⠀⠀⠀⠀⠀⣸⢿⢰⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⣠⡏⠀⠀⠀⣼⡟⣿⣿⡿⠛⠉⢻⣞⣧⠀⠀⠀⠉⠛⠁⠀⠀⠀⠀⡿⣿⣿⣦⣀⠀⠀⠀⠀⣠⣾⡏⢸⣠⣧⣤⣄⣤⣤⣤⣤⣤⣴⣾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⣿⠀⠀⠀⢸⠋⣿⠛⠁⠀⠀⠀⠀⠻⣯⣷⣄⠀⠀⠀⠀⠀⠀⢀⣼⠁⠘⠿⣿⣿⣻⣿⣿⣿⣿⠏⠀⣾⣿⣿⣿⣿⣿⣿⣿⡿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⡟⣇⠀⠀⠈⢿⣏⢧⣴⣶⡆⠀⠀⠀⢿⣿⣿⢳⢦⣤⣤⣤⣶⣿⠟⠀⠀⠀⠀⠉⠉⠛⠋⢩⡤⠖⠒⠛⠛⡿⢁⣾⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⣷⠀⠀⠀⠀⢀⣤⠤⠤⣀⡀⠀",
                    "⣇⠘⣆⠀⠀⠀⠙⠻⠿⠛⠃⠀⠀⠀⣸⡙⠻⢿⣿⣿⣿⣿⠿⠋⠀⣀⡤⠤⠒⠚⠳⣄⢠⣿⠁⠀⠀⠀⢠⠇⡏⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠀⠀⢀⡴⢫⡇⠀⠀⠀⠈⠙",
                    "⠘⣶⣿⣷⣄⡀⠀⠀⠀⠀⠀⠀⣀⣼⣿⠇⠀⠀⠀⣀⣀⣀⠀⢀⣼⣿⣦⡀⠀⠀⠀⠈⢻⡏⠀⠀⠀⠀⡞⠀⡇⢸⠀⠀⠀⠀⢰⣾⣶⣶⣶⣶⣶⡏⠀⠀⡼⠀⡞⠀⠀⠀⠀⠀⢸",
                    "⠀⠈⠻⣿⣿⣿⣶⢶⡶⡶⣶⣾⣿⡿⠋⣠⠴⠚⠉⠁⠀⠉⠙⠺⡿⢿⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⣸⠃⢰⠀⢸⠀⠀⠀⠀⠘⠛⠛⠿⠿⢿⡏⠀⠀⢰⠃⢠⠇⠀⠀⠀⠀⠀⠌",
                    "⠀⠀⠀⠈⠙⠻⠿⠼⠽⠿⠿⠟⠋⢰⡟⠁⠀⠀⢀⣤⣄⡀⠀⠀⠹⡆⠙⢿⣿⣿⣦⡀⠀⠀⠀⠀⢰⡇⠀⢸⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⡞⠀⡜⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡾⢳⡀⠀⠀⠈⢿⡿⠇⠀⠀⣼⠃⠀⠀⠙⢿⣿⣿⣷⠀⠀⠀⠈⡇⠀⢸⠀⡄⠀⠀⠀⠀⢰⣶⣤⣤⣤⣼⠃⠀⢰⠃⢰⠃⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢷⠀⢣⠀⠀⠀⠀⠀⠀⠀⠈⠋⠉⠲⣄⠀⠀⠙⢿⠸⡄⠀⠀⠀⢳⠀⢸⠀⡇⠀⠀⠀⠀⠸⣿⣿⣿⣿⣃⠀⠀⣞⣠⣾⣤⣀⣀⠀⠀⡄⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣆⠈⣇⠀⠀⠀⠀⣠⣤⣀⠀⠀⠀⠘⣆⠀⠀⠸⡄⢳⠀⠀⠀⠸⡆⢸⠀⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠘⠻⢿⣿⣿⣿⣿⡿⠞⠁⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡆⠘⡄⠀⠀⠀⢻⣿⣿⠆⠀⠀⠀⢸⠀⠀⠀⣇⠘⡆⠀⢀⣀⣧⢸⢀⣿⣶⣤⣤⣤⣀⣀⣀⠀⢀⡏⠀⢀⣴⠟⠁⠀⠀⠈⢳⡀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡄⠹⡄⠀⠀⠈⠉⠁⠀⠀⠀⣠⡾⠀⠀⠀⢹⢀⣿⣿⣿⡿⠃⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⢰⣯⡏⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⠀⠹⡄⠀⠀⠀⢀⣠⣴⣾⣿⠃⠀⠀⠀⠘⠿⠟⠛⠛⠁⠀⠀⠀⠉⠉⠉⠛⠛⠛⠿⠟⠁⠀⠀⢾⣿⣷⣄⡀⠀⠀⢀⡼⠃⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢧⠀⢳⣴⣶⣿⣿⣿⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣿⣿⣿⡿⠟⠁⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣦⣿⣿⡿⠟⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⣀⠀⠀⠀⠀⠀⠀⠀"
            };

            textGraphics = animationScreen.newTextGraphics();
            screenWidth = animationScreen.getTerminalSize().getColumns();
            screenHeight = animationScreen.getTerminalSize().getRows();
            longestLineLength = Arrays.stream(titleLines).mapToInt(String::length).max().getAsInt();
            titleHeight = titleLines.length;
            midHorizontalPos = (screenWidth - longestLineLength) / 2;
            midVerticalPos = (screenHeight - titleHeight) / 2;
            bounceHeight = 2; // Height of the bounce
            numBounces = 1; // Number of times the title bounces

            // Animation from top to middle
            for (int vPos = -titleHeight; vPos <= midVerticalPos; vPos++) {
                animationScreen.clear();
                for (int line = 0; line < titleLines.length; line++) {
                    textGraphics.setForegroundColor(redColor); // Set the text color to red
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
                    animationScreen.refresh();
                    Thread.sleep(50);
                }
                // Move down
                for (int vPos = midVerticalPos - bounceHeight; vPos <= midVerticalPos; vPos++) {
                    animationScreen.clear();
                    for (int line = 0; line < titleLines.length; line++) {
                        textGraphics.putString(midHorizontalPos, vPos + line, titleLines[line]);
                    }
                    animationScreen.refresh();
                    Thread.sleep(50);
                }
            }

            // Continue animation to bottom
            for (int vPos = midVerticalPos; vPos <= screenHeight; vPos++) {
                animationScreen.clear();
                for (int line = 0; line < titleLines.length; line++) {
                    textGraphics.putString(midHorizontalPos, vPos + line, titleLines[line]);
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
