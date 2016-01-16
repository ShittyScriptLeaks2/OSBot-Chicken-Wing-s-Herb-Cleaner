/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.osbot.rs07.api.Bank
 *  org.osbot.rs07.utility.ConditionalSleep
 */
package state;

import org.osbot.rs07.utility.ConditionalSleep;

public final class WaitForBankCloseCondition2 extends ConditionalSleep {

    private CloseBankState parent;

    public WaitForBankCloseCondition2(CloseBankState parent) {
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

