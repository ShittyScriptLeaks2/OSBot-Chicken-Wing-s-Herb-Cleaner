package state;

import org.osbot.rs07.utility.ConditionalSleep;

public final class WaitForLogoutCondition extends ConditionalSleep {

    private final LogoutState parent;

    public WaitForLogoutCondition(LogoutState parent) {
        super(5000);
        this.parent = parent;
    }

    public boolean condition() {
        if (this.parent.parent.getClient().isLoggedIn()) {
            return false;
        }

        return true;
    }

}

