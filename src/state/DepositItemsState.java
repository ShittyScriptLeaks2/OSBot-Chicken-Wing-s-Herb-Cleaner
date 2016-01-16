package state;

import core.Core;
import core.State;

public final class DepositItemsState extends State {

    public DepositItemsState(Core parent) {
        super(parent);
    }

    @Override
    public boolean onLoop() {
        this.parent.widgets.get(12, 27).interact("Deposit inventory");
        new DepositGrimyHerbsCondition(this).sleep();
        return true;
    }

    @Override
    public String getTextualState() {
        return "Depositing " + this.parent.getGrimyHerbName();
    }

    @Override
    public boolean shouldExecute() {
        if (!this.parent.bank.isOpen()) {
            return false;
        }

        if (!this.parent.inventory.contains(this.parent.getGrimyHerbName())) {
            return false;
        }

        return true;
    }

}

