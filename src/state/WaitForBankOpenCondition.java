package state;

import org.osbot.rs07.utility.ConditionalSleep;

public final class WaitForBankOpenCondition extends ConditionalSleep {

    private OpenBankState parent;

    public WaitForBankOpenCondition(OpenBankState parent) {
        super(3000);
        this.parent = parent;

    }

    public final boolean condition() {
        return this.parent.parent.bank.isOpen();
    }
}

