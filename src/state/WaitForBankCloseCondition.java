package state;

import org.osbot.rs07.utility.ConditionalSleep;

public final class WaitForBankCloseCondition extends ConditionalSleep {

    private CloseBankState parent;

    public WaitForBankCloseCondition(CloseBankState parent) {
        super(3000);
        this.parent = parent;
    }

    public final boolean condition() {
        if (this.parent.parent.bank.isOpen()) {
            return false;
        }

        return true;
    }
}

