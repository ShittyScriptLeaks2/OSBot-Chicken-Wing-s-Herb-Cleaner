package ui;

import java.util.EventObject;

public final class PatternEventObject extends EventObject {

    private final int[] cleanPattern;

    public PatternEventObject(Object object, int[] pattern) {
        super(object);
        this.cleanPattern = pattern;
    }

    public int[] getCleanPattern() {
        return this.cleanPattern;
    }

}

