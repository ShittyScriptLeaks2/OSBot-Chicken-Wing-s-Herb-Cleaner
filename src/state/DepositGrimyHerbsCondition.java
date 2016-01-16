package state;

import org.osbot.rs07.utility.ConditionalSleep;

public final class DepositGrimyHerbsCondition extends ConditionalSleep {

    private final DepositItemsState parent;

    public DepositGrimyHerbsCondition(DepositItemsState parent) {
        super(2000);
        this.parent = parent;
    }

    public boolean condition() {
        if (this.parent.parent.inventory.contains(this.parent.parent.getGrimyHerbName())) {
            return false;
        }

        return true;
    }
}

