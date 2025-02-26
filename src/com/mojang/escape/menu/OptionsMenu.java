package com.mojang.escape.menu;

import com.mojang.escape.Art;
import com.mojang.escape.Game;
import com.mojang.escape.Sound;
import com.mojang.escape.gui.Bitmap;

public class OptionsMenu extends Menu {

    private String[] options = { "Sound", "Back" };
    private int selected = 0;

    private float changeVolumeDiff = .1f;
    private Menu prevMenu;

    public OptionsMenu(Menu prevMenu) {
        this.prevMenu = prevMenu;
    }

    public void render(Bitmap target) {
        target.fill(0, 0, 160, 120, 0);
        target.draw(Art.logo, 0, 8, 0, 0, 160, 36, Art.getCol(0xffffff));

        float currentVolume = Game.getVolume();

        for (int i = 0; i < options.length; i++) {
            String msg = options[i];
            int col = 0x909090;
            if (selected == i) {
                msg = "-> " + msg;
                col = 0xffff80;
            }
            if (i == 0) { // Sound
                msg = msg + " " + currentVolume;
            }
            target.draw(msg, 40, 60 + i * 10, Art.getCol(col));
        }
    }

    private void changeVolume(boolean decrease) {
        float diff = decrease ? -changeVolumeDiff : changeVolumeDiff;

        Game.setVolume((float) Math.round((Game.getVolume() + diff) * 10) / 10);
    }

    public void tick(Game game, boolean up, boolean down, boolean left, boolean right, boolean use) {
        if (up || down) Sound.click2.play();
        if (up) selected--;
        if (down) selected++;
        if (selected < 0) selected = 0;
        if (selected >= options.length) selected = options.length - 1;

        switch (selected) {
            case 0:
                if (left || right) {
                    changeVolume(left);
                    Sound.click2.play();
                }
            case 1:
                if (use) {
                    Sound.click1.play();
                    game.setMenu(this.prevMenu);
                }
        }

    }
}
