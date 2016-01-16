package ui;

import java.util.EventObject;

public final class PatternEventObject extends EventObject {

    private int[] a;

    public PatternEventObject(Object object, int[] arrn) {
        super(object);
        this.a = arrn;
    }

    public final int[] a() {
        return this.a;
    }

}

