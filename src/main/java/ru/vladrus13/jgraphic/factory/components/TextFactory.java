package ru.vladrus13.jgraphic.factory.components;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Text;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;

import java.awt.*;

public class TextFactory {
    public String nameFont;
    public Size fontSize;
    public Color color;
    public Text.TextAlign textAlign;

    public TextFactory setNameFont(String nameFont) {
        this.nameFont = nameFont;
        return this;
    }

    public TextFactory setFontSize(Size fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public TextFactory setColor(Color color) {
        this.color = color;
        return this;
    }

    public TextFactory setTextAlign(Text.TextAlign textAlign) {
        this.textAlign = textAlign;
        return this;
    }

    public Text getInstance(String nameButton, String text, Frame parent) throws GameException {
        return new Text(nameButton, text, nameFont, fontSize.copy(), color, textAlign, parent);
    }
}
