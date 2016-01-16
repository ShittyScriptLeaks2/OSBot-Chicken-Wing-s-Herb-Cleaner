package state;

import org.osbot.rs07.utility.ConditionalSleep;

public final class WaitForCleaningCondition extends ConditionalSleep {

    private CleanHerbsState parent;

    public WaitForCleaningCondition(CleanHerbsState parent) {
        super(2000);
        this.parent = parent;

    }

    public final boolean condition() {
        if (this.parent.parent.inventory.contains(this.parent.parent.getCleanHerbName())) {
            return false;
        }

        return true;
    }
}

