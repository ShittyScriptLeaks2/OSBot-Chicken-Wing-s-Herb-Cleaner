package state;

import core.Core;
import core.State;

public final class LogoutState extends State {

    public LogoutState(Core parent) {
        super(parent);
    }

    @Override
    public final boolean shouldExecute() {
        if (!this.parent.bank.isOpen()) {
            return false;
        }

        if (this.parent.bank.contains(this.parent.getCleanHerbName())) {
            return false;
        }

        return true;
    }

    @Override
    public final boolean onLoop() {
        this.parent.getLogoutTab().logOut();
        new WaitForLogoutCondition(this).sleep();
        parent.log("Ran out of Items, logging out..");
        parent.stop();
        return true;
    }

    @Override
    public final String getTextualState() {
        return "Logging out";
    }

}

