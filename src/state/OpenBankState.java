package state;

import core.Core;
import core.State;
import org.osbot.rs07.api.model.Entity;

public final class OpenBankState extends State {

    public OpenBankState(Core ddd2) {
        super(ddd2);
    }

    @Override
    public final boolean shouldExecute() {
        if (this.parent.bank.isOpen()) {
            return false;
        }

        if (this.parent.inventory.contains(this.parent.getCleanHerbName())) {
            return false;
        }

        return true;
    }

    @Override
    public final String getTextualState() {
        return "Opening Bank";
    }

    @Override
    public final boolean onLoop() {
        try {
            Entity entity = this.parent.objects.closest("Bank booth");
            if (entity == null || !entity.isVisible()) {
                entity = this.parent.objects.closest("Grand Exchange booth");
            }

            if (entity == null || !entity.isVisible()) {
                entity = this.parent.objects.closest("Bank chest");
            }

            if (entity != null) {
                entity.interact("Bank");
            }

            new WaitForBankOpenCondition(this).sleep();
            return true;
        } catch (NullPointerException v0) {
            this.parent.log("RELOG");
        }

        return true;
    }
}

