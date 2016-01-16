package state;

import core.Core;
import core.State;

public final class DepositItemsState extends State {

    public DepositItemsState(Core parent) {
        super(parent);
    }

    @Override
    public final boolean onLoop() {
        this.parent.widgets.get(12, 27).interact("Deposit inventory");
        new DepositGrimyHerbsCondition(this).sleep();
        return true;
    }

    @Override
    public final String getTextualState() {
        return "Depositing " + this.parent.getGrimyHerbName();
    }

    @Override
    public final boolean shouldExecute() {
        if (!this.parent.bank.isOpen()) {
            return false;
        }

        if (!this.parent.inventory.contains(this.parent.getGrimyHerbName())) {
            return false;
        }

        return true;
    }

}

