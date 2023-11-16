package ui;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import entity.RepositoryCollection;
import ui.windows.Window;
import ui.windows.WindowsManager;

import java.io.IOException;

/**
 * The third tutorial, introducing the Screen interface
 * 
 * @author Martin
 */
public class Main {
    public static void main(String[] args) {
        RepositoryCollection.load();

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Screen screen = null;

        try {
            Terminal terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.startScreen();
            screen.setCursorPosition(null);

            TerminalSize terminalSize = screen.getTerminalSize();
            for (int column = 0; column < terminalSize.getColumns(); column++) {
                for (int row = 0; row < terminalSize.getRows(); row++) {
                    screen.setCharacter(column, row, new TextCharacter(
                            ' ',
                            TextColor.ANSI.DEFAULT,
                            TextColor.ANSI.DEFAULT));
                }
            }
            Window LoginView = new LoginView(60, 60, 1, 2,1);
            Window studentMainView = new StudentMainView(0, 4, 3);
            Window staffMainView = new StaffMainView(0, 4, 3);
            Window changePasswordView = new ChangePasswordView(4, 1);
            Window CampListView = new CampListView(0, 0, 0);
            Window createCampView = new CreateCampView(3);
            Window campViewer = new CampViewer(40, 40, 3);
            WindowsManager windows = new WindowsManager(screen, 0, 0);

            windows.addWindow(LoginView);
            windows.addWindow(studentMainView);
            windows.addWindow(staffMainView);
            windows.addWindow(changePasswordView);
            windows.addWindow(CampListView);
            windows.addWindow(createCampView);
            windows.addWindow(campViewer);

            screen.refresh();

            while (true) {
                KeyStroke keyStroke = screen.pollInput();
                if (keyStroke != null
                        && (keyStroke.getKeyType() == KeyType.Escape || keyStroke.getKeyType() == KeyType.EOF)) {
                    break;
                }
                if (keyStroke != null)
                    windows.keyStroke(keyStroke);

                TerminalSize newSize = screen.doResizeIfNecessary();
                if (newSize != null) {
                    screen.clear();
                    terminalSize = newSize;
                }

                String sizeLabel = "Terminal Size: " + terminalSize;
                TerminalPosition labelBoxTopLeft = new TerminalPosition(1, 1);
                TerminalSize labelBoxSize = new TerminalSize(sizeLabel.length() + 2, 3);
                TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft
                        .withRelativeColumn(labelBoxSize.getColumns() - 1);
                TextGraphics textGraphics = screen.newTextGraphics();
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
                screen.refresh();
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (screen != null) {
                try {
                    /*
                     * The close() call here will restore the terminal by exiting from private mode
                     * which was done in
                     * the call to startScreen(), and also restore things like echo mode and intr
                     */
                    screen.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}