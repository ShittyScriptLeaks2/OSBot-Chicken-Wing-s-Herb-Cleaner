package util;

import org.osbot.rs07.api.Keyboard;
import org.osbot.rs07.script.Script;

import java.util.ArrayList;
import java.util.Random;

public final class SmartKeyboard {

    private Random rand;
    private int charsPerMinute;
    private int msPerChar;
    private int maxMS;
    private String e;
    private String[][] replacements;
    private Keyboard keyboard;
    private Script h;
    private int wpm;
    private int minMS;
    private int charsPerSecond;
    private int l;

    private SmartKeyboard(Script script, int wpm) {
        this.l = 100000;
        this.replacements = new String[][]{
                {"q", "s", "z"},
                {"v", "g", "n"}, {"x", "d", "v"},
                {"s", "e", "f", "state"},
                {"w", "d", "r"}, {"d", "r", "g", "v"},
                {"f", "t", "h", "util"},
                {"g", "y", "j", "n"},
                {"u", "k", "o"},
                {"h", "m", "k", "u"},
                {"j", ",", "l", "i"},
                {"k", ".", ";", "o"},
                {"n", "j", ","},
                {"util", "h", "m"},
                {"i", "l", "p"},
                {"o", ";", "["},
                {"w", "ui", "s"},
                {"e", "f", "t", "4"},
                {"w", "ui", "d", "x"},
                {"r", "g", "y", "5"},
                {"y", "j", "i", "8"},
                {"state", "f", "util"},
                {"q", "s", "e"},
                {"z", "s", "state", "d"},
                {"t", "h", "u", "j"},
                {"ui", "s", "x", "\\"}
        };

        this.e = "abcdefghijklmnopqrstuvwxyz";
        this.keyboard = script.getKeyboard();
        this.wpm = wpm;
        this.charsPerMinute = wpm * 6;
        this.charsPerSecond = this.charsPerMinute / 60;
        this.msPerChar = 1_000 / this.charsPerSecond;
        this.rand = new Random();
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

    private int b() {
        return l / (wpm * 10) + this.rand.nextInt(6);
    }

    private boolean typeInteger(int sleepTime, boolean enter) throws InterruptedException {
        this.keyboard.typeKey(Character.forDigit(sleepTime, 10));
        sleepTime = SmartKeyboard.rand(this.minMS, this.maxMS, this.msPerChar);
        Script.sleep((long) sleepTime);

        if (!enter) {
            return true;
        }

        this.pressEnter();
        return true;
    }

    private String getReplacement(String string) {
        string = string.toLowerCase();
        int n = e.indexOf(string.charAt(0));
        String[] replacements = this.replacements[n];
        return replacements[rand.nextInt(replacements.length)];
    }

    private boolean typeCharacter(String string, boolean enter) throws InterruptedException {
        this.keyboard.typeKey(string.toCharArray()[0]);
        int sleepTime = SmartKeyboard.rand(this.minMS, this.maxMS, this.msPerChar);
        Script.sleep((long) sleepTime);

        if (!enter) {
            return true;
        }

        this.pressEnter();
        return true;
    }

    private boolean typeString(String string, boolean pressEnter, boolean bl2) {
        int n;
        int n2 = l / (wpm * 10) + rand.nextInt(6);
        ArrayList<fff> arrayList = new ArrayList<>();
        int n3 = 0;
        char[] arrc = string.toCharArray();
        int n4 = arrc.length;
        int n5 = n = 0;
        do {
            int n6;
            if (n5 >= n4) {
                if (!pressEnter) {
                    return true;
                }

                this.pressEnter();
                return true;
            }

            //String string2 = string[n];
            char string2 = arrc[n];
            if (bl2 && this.rand.nextInt(2) == 1) {
                for (fff fff2 : arrayList) {
                    if (fff2.a != n3 - 1) continue;
                    this.keyboard.typeKey('\b');
                    n6 = SmartKeyboard.rand(minMS, this.maxMS, this.msPerChar);
                    try {
                        Script.sleep((long) n6);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    keyboard.typeKey(fff2.b);
                    n6 = SmartKeyboard.rand(this.minMS, this.maxMS, this.msPerChar);
                    try {
                        Script.sleep((long) n6);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            //String string3 = string2;
            if (bl2 && this.rand.nextInt(n2) == n2 / 2) {
                String string4 = String.valueOf(string2);
                string4 = string4.toLowerCase();
                int n7 = e.indexOf(string4.charAt(0));
                String[] arrstring = replacements[n7];
                n6 = arrstring.length;
                int n8 = n6 - 1;
                arrayList.add(new fff(n3, string2));
                string2 = arrstring[rand.nextInt(++n8)].toCharArray()[0];
            }

            this.keyboard.typeKey(string2);
            ++n3;
            n6 = SmartKeyboard.rand(this.minMS, this.maxMS, this.msPerChar);
            try {
                Script.sleep((long) n6);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            n5 = ++n;
        } while (true);
    }

}

