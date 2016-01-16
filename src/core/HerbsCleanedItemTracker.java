package core;

import org.osbot.rs07.script.Script;
import util.ItemTracker;

public final class HerbsCleanedItemTracker extends ItemTracker {

    private Core parent;

    public HerbsCleanedItemTracker(Core parent, Script script) {
        super(script);
        this.parent = parent;
    }

    @Override
    public final int onTick() {
        if (getChangedAmount(Core.getGrimyHerbName(parent)) <= 0) {
            return 100;
        }

        Core.setAndGetHerbsCleaned(parent, Core.getHerbsCleaned(parent) + getChangedAmount(Core.getGrimyHerbName(parent)));
        return 100;
    }

}

