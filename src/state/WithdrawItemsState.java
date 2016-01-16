package state;

import core.Core;
import core.State;
import org.osbot.rs07.script.MethodProvider;

public final class WithdrawItemsState extends State {

    public WithdrawItemsState(Core ddd2) {
        super(ddd2);
    }

    @Override
    public final boolean onLoop() throws InterruptedException {
        this.parent.bank.withdrawAll(this.parent.getCleanHerbName());
        MethodProvider.sleep((long) MethodProvider.random(10, 50));

        new WithdrawGrimyHerbsCondition(this).sleep();
        return true;
    }

    @Override
    public final String getTextualState() {
        return new StringBuilder().insert(0, "Withdrawing ").append(this.parent.getCleanHerbName()).toString();
    }

    @Override
    public final boolean shouldExecute() {
        if (!this.parent.bank.isOpen()) {
            return false;
        }

        if (!this.parent.bank.contains(this.parent.getCleanHerbName())) {
            return false;
        }

        if (this.parent.inventory.contains(this.parent.getCleanHerbName())) {
            return false;
        }

        return true;
    }

}

