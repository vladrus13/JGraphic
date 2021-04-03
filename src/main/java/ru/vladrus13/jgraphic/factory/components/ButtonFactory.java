package ru.vladrus13.jgraphic.factory.components;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Background;
import ru.vladrus13.jgraphic.basic.components.ClassicButton;
import ru.vladrus13.jgraphic.basic.components.Text;

public class ButtonFactory {
    public Background background = null;
    public Background chooseBackground = null;

    public ButtonFactory setBackground(Background background) {
        this.background = background;
        return this;
    }

    public ButtonFactory setChooseBackground(Background chooseBackground) {
        this.chooseBackground = chooseBackground;
        return this;
    }

    public ClassicButton getInstance(String name, Text text, Frame parent) {
        ClassicButton button = new ClassicButton(name, parent);
        Background temp = background.copy();
        temp.setParent(button);
        button.setBackground(temp);
        temp = chooseBackground.copy();
        temp.setParent(button);
        button.setBackgroundChoose(temp);
        button.setText(text);
        return button;
    }
}
