package app.ui.widgets;

import com.googlecode.lanterna.input.KeyStroke;

/**
 * Interface for text input widgets.
 */
public interface ITextInput {
    /**
     * Handles a key stroke.
     * @param keyStroke The key stroke to handle.
     */
    public void keyStroke(KeyStroke keyStroke);
}
