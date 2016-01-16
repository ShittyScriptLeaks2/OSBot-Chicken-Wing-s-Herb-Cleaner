/*
 * Decompiled with CFR 0_110.
 */
package core;

public abstract class State {

    public Core parent;

    public State(Core parent) {
        this.parent = parent;
    }

    public abstract boolean onLoop() throws InterruptedException;

    public String getTextualState() {
        return "";
    }

    public abstract boolean shouldExecute();

}

