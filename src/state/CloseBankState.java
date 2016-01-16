package state;

import core.Core;
import core.State;
import org.osbot.rs07.script.MethodProvider;

public final class CloseBankState extends State {

    private static boolean escapeExit = true;

    public CloseBankState(Core parent) {
        super(parent);
    }

    @Override
    public boolean shouldExecute() {
        if (!this.parent.bank.isOpen()) {
            return false;
        }

        if (!this.parent.inventory.contains(this.parent.getCleanHerbName())) {
            return false;
        }

        return true;
    }

    @Override
    public String getTextualState() {
        return "Closing Bank";
    }

    @Override
    public boolean onLoop() throws InterruptedException {
        if (escapeExit) {
            this.parent.getKeyboard().typeKey('\u001b');
            new WaitForBankCloseCondition(this).sleep();
        }

        if (this.parent.bank.isOpen()) {
            escapeExit = false;
            this.parent.widgets.get(12, 3, 11).interact("Close");
            new WaitForBankCloseCondition2(this).sleep();
        }

        MethodProvider.sleep((long) MethodProvider.random(10, 50));
        return true;
    }

}

