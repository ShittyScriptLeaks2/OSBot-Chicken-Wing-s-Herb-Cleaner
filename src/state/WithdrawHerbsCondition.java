package state;

import org.osbot.rs07.utility.ConditionalSleep;

public final class WithdrawHerbsCondition extends ConditionalSleep {

    private final WithdrawItemsState parent;

    public WithdrawHerbsCondition(WithdrawItemsState parent) {
        super(2000);
        this.parent = parent;
    }

    public boolean condition() {
        return this.parent.parent.inventory.contains(this.parent.parent.getCleanHerbName());
    }

}

