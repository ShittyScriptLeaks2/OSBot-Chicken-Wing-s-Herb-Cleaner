package state;

import core.Core;
import core.State;
import org.osbot.rs07.api.model.Entity;

public final class OpenBankState extends State {

    public OpenBankState(Core ddd2) {
        super(ddd2);
    }

    @Override
    public boolean shouldExecute() {
        if (this.parent.bank.isOpen()) {
            return false;
        }

        if (this.parent.inventory.contains(this.parent.getCleanHerbName())) {
            return false;
        }

        return true;
    }

    @Override
    public String getTextualState() {
        return "Opening Bank";
    }

    @Override
    public boolean onLoop() {
        try {
            Entity booth = this.parent.objects.closest("Bank booth");
            if (booth == null || !booth.isVisible()) {
                booth = this.parent.objects.closest("Grand Exchange booth");
            }

            if (booth == null || !booth.isVisible()) {
                booth = this.parent.objects.closest("Bank chest");
            }

            if (booth != null) {
                booth.interact("Bank");
            }

            new WaitForBankOpenCondition(this).sleep();
        } catch (NullPointerException v0) {
            this.parent.log("RELOG");
        }

        return true;
    }
}

