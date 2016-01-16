package util;

import org.osbot.rs07.api.Keyboard;
import org.osbot.rs07.script.Script;

import java.util.ArrayList;
import java.util.Random;

public final class SmartKeyboard {

    private Random rand = new Random();
    private int charsPerMinute;
    private int msPerChar;
    private int maxMS;
    private String validTypoKeys = "abcdefghijklmnopqrstuvwxyz";
    private String[][] replacements = new String[][]{
            {"q", "s", "z"},
            {"v", "g", "n"},
            {"x", "d", "v"},
            {"s", "e", "f", "c"},
            {"w", "d", "r"},
            {"d", "r", "g", "v"},
            {"f", "t", "h", "b"},
            {"g", "y", "j", "n"},
            {"u", "k", "o"},
            {"h", "m", "k", "u"},
            {"j", ",", "l", "i"},
            {"k", ".", ";", "o"},
            {"n", "j", ","},
            {"b", "h", "m"},
            {"i", "l", "p"},
            {"o", ";", "["},
            {"w", "ui", "s"},
            {"e", "f", "t", "4"},
            {"w", "ui", "d", "x"},
            {"r", "g", "y", "5"},
            {"y", "j", "i", "8"},
            {"c", "f", "b"},
            {"q", "s", "e"},
            {"z", "s", "c", "d"},
            {"t", "h", "u", "j"},
            {"ui", "s", "x", "\\"}
    };

    private Keyboard keyboard;
    private int wpm;
    private int minMS;
    private int charsPerSecond;
    private int typoMultiplier = 100000;

    private SmartKeyboard(Script script, int wpm) {
        this.keyboard = script.getKeyboard();
        this.wpm = wpm;
        this.charsPerMinute = wpm * 6;
        this.charsPerSecond = this.charsPerMinute / 60;
        this.msPerChar = 1_000 / this.charsPerSecond;
        this.minMS = this.msPerChar - this.wpm / 2 + this.rand.nextInt(15);
        this.maxMS = this.msPerChar + this.wpm / 2 + this.rand.nextInt(15);
    }

    public static int rand(int min, int max, int mean) {
        int n;
        int std = (max - mean) / 3;
        Random r = new Random();

        do {
            double val = r.nextGaussian() * std + mean;
            n = (int) Math.round(val);
        } while (n < min || n > max);

        return n;
    }

    private boolean pressEnter() {
        this.keyboard.typeKey('\r');
        this.keyboard.typeKey('\n');
        return true;
    }

    private int getTypoChance() {
        return typoMultiplier / (wpm * 10) + this.rand.nextInt(6);
    }

    private boolean typeInteger(int sleepTime, boolean enter) throws InterruptedException {
        this.keyboard.typeKey(Character.forDigit(sleepTime, 10));
        sleepTime = SmartKeyboard.rand(this.minMS, this.maxMS, this.msPerChar);
        Script.sleep((long) sleepTime);

        if (enter) {
            this.pressEnter();
        }

        return true;
    }

    private String getReplacement(String string) {
        string = string.toLowerCase();
        int n = validTypoKeys.indexOf(string.charAt(0));
        String[] replacements = this.replacements[n];
        return replacements[rand.nextInt(replacements.length)];
    }

    private boolean typeCharacter(String string, boolean enter) throws InterruptedException {
        this.keyboard.typeKey(string.toCharArray()[0]);
        int sleepTime = SmartKeyboard.rand(this.minMS, this.maxMS, this.msPerChar);
        Script.sleep((long) sleepTime);

        if (enter) {
            this.pressEnter();
        }

        return true;
    }

    private boolean typeString(String string, boolean pressEnter, boolean entersTypos) throws InterruptedException {
        int typoChance = getTypoChance();
        ArrayList<MistypedKey> mistypedKeys = new ArrayList<>();

        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (entersTypos && this.rand.nextInt(2) == 1) {
                for (MistypedKey mk : mistypedKeys) {
                    if (mk.index != i - 1) {
                        continue;
                    }

                    this.keyboard.typeKey('\b');
                    Script.sleep(SmartKeyboard.rand(minMS, this.maxMS, this.msPerChar));

                    keyboard.typeKey(mk.c);
                    Script.sleep(SmartKeyboard.rand(this.minMS, this.maxMS, this.msPerChar));
                }
            }

            if (entersTypos && this.rand.nextInt(typoChance) == (typoChance / 2)) {
                mistypedKeys.add(new MistypedKey(i, c));
                c = getReplacement(String.valueOf(c)).toCharArray()[0];
            }

            this.keyboard.typeKey(c);
            Script.sleep(SmartKeyboard.rand(this.minMS, this.maxMS, this.msPerChar));
        }

        if (pressEnter) {
            this.pressEnter();
        }

        return true;
    }

}

