package state;

import org.osbot.rs07.utility.ConditionalSleep;

public final class WaitForBankCloseCondition2 extends ConditionalSleep {

    private final CloseBankState parent;

    public WaitForBankCloseCondition2(CloseBankState parent) {
        super(3000);
        this.parent = parent;
    }

    public boolean condition() {
        if (this.parent.parent.bank.isOpen()) {
            return false;
        }

        return true;
    }

}

