package state;

import org.osbot.rs07.utility.ConditionalSleep;

public final class WithdrawGrimyHerbsCondition extends ConditionalSleep {

    private final WithdrawItemsState parent;

    WithdrawGrimyHerbsCondition(WithdrawItemsState parent) {
        super(2000);
        this.parent = parent;
    }

    public final boolean condition() {
        return this.parent.parent.inventory.contains(this.parent.parent.getCleanHerbName());
    }

}

