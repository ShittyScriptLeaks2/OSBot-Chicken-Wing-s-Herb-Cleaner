package core;

import util.ItemTracker;

public final class HerbsCleanedItemTracker extends ItemTracker {

    private final Core parent;

    public HerbsCleanedItemTracker(Core parent) {
        super(parent);
        this.parent = parent;
    }

    @Override
    public int onTick() {
        if (getChangedAmount(parent.getGrimyHerbName()) <= 0) {
            return 100;
        }

        parent.setHerbsCleaned(parent.getHerbsCleaned() + getChangedAmount(parent.getGrimyHerbName()));
        return 100;
    }

}

