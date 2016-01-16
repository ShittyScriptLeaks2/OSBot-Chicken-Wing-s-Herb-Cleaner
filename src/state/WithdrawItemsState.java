package state;

import core.Core;
import core.State;
import org.osbot.rs07.script.MethodProvider;

public final class WithdrawItemsState extends State {

    public WithdrawItemsState(Core parent) {
        super(parent);
    }

    @Override
    public boolean onLoop() throws InterruptedException {
        this.parent.bank.withdrawAll(this.parent.getCleanHerbName());
        MethodProvider.sleep((long) MethodProvider.random(10, 50));

        new WithdrawHerbsCondition(this).sleep();
        return true;
    }

    @Override
    public String getTextualState() {
        return "Withdrawing " + this.parent.getCleanHerbName();
    }

    @Override
    public boolean shouldExecute() {
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

