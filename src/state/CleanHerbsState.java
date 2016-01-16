package state;

import core.Core;
import core.State;
import org.osbot.rs07.input.mouse.MouseDestination;
import org.osbot.rs07.input.mouse.MoveMouseEvent;
import org.osbot.rs07.script.MethodProvider;

public final class CleanHerbsState extends State {

    private final int[] pattern;

    public CleanHerbsState(Core parent) {
        super(parent);
        this.pattern = parent.getCleanPattern();
    }

    @Override
    public boolean shouldExecute() {
        if (this.parent.bank.isOpen()) {
            return false;
        }

        if (!this.parent.inventory.contains(this.parent.getCleanHerbName())) {
            return false;
        }

        return true;
    }

    @Override
    public boolean onLoop() throws InterruptedException {
        for (int i = 0; i < 28; i++) {
            if (this.parent.inventory.getItemInSlot(this.pattern[i]) != null && this.parent.inventory.getItemInSlot(this.pattern[i]).getName().equals(this.parent.getCleanHerbName())) {
                MouseDestination mouseDestination = this.parent.inventory.getMouseDestination(this.pattern[i]);
                MoveMouseEvent moveMouseEvent = new MoveMouseEvent(mouseDestination);
                if (mouseDestination != null) {
                    this.parent.execute(moveMouseEvent);
                    MethodProvider.sleep((long) MethodProvider.random(0, this.parent.getCleanDelay() / 2));
                    this.parent.mouse.click(false);
                }

                if (this.parent.getCleanDelay() > 0) {
                    MethodProvider.sleep((long) MethodProvider.random(0, this.parent.getCleanDelay() / 2));
                }
            }
        }

        new WaitForCleaningCondition(this).sleep();
        return true;
    }

    @Override
    public String getTextualState() {
        return "Cleaning " + this.parent.getCleanHerbName();
    }

}

