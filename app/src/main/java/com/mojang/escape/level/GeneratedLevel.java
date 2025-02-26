package com.mojang.escape.level;

import com.mojang.escape.Game;

public class GeneratedLevel extends Level {
    
	public GeneratedLevel() {
	}

    public static Level loadLevel(Game game) {
        Level level = new GeneratedLevel();

        int w = 30;
        int h = 30;

        int[] pixels = new int[w*h];

        level.xSpawn = w / 2;
        level.ySpawn = h / 2;
        level.ceilTex = -1;
        level.floorTex = -1;

    // TODO: in order for generated level not change when we return to it, we need to give it name

        level.init(game, "generated_1", w, h, pixels);

        return level;
    }

    private void generateLevel() {
        System.out.println("hello");
    }

}