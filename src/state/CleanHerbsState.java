package state;

import core.Core;
import core.State;
import org.osbot.rs07.input.mouse.MouseDestination;
import org.osbot.rs07.input.mouse.MoveMouseEvent;
import org.osbot.rs07.script.MethodProvider;

public final class CleanHerbsState extends State {

    private int[] pattern;

    public CleanHerbsState(Core parent) {
        super(parent);
        this.pattern = parent.getCleanPattern();
    }

    @Override
    public final boolean shouldExecute() {
        if (this.parent.bank.isOpen()) {
            return false;
        }

        if (!this.parent.inventory.contains(this.parent.getCleanHerbName())) {
            return false;
        }

        return true;
    }

    @Override
    public final boolean onLoop() throws InterruptedException {
        int n;
        int n2 = n = 0;
        do {
            if (n2 >= 28) {
                new WaitForCleaningCondition(this).sleep();
                return true;
            }

            if (this.parent.inventory.getItemInSlot(this.pattern[n]) != null && this.parent.inventory.getItemInSlot(this.pattern[n]).getName().equals(this.parent.getCleanHerbName())) {
                MouseDestination mouseDestination = this.parent.inventory.getMouseDestination(this.pattern[n]);
                MoveMouseEvent moveMouseEvent = new MoveMouseEvent(mouseDestination);
                if (mouseDestination != null) {
                    this.parent.execute(moveMouseEvent);
                    MethodProvider.sleep((long) MethodProvider.random(0, this.parent.b() / 2));
                    this.parent.mouse.click(false);
                }

                if (this.parent.b() > 0) {
                    MethodProvider.sleep((long) MethodProvider.random(0, this.parent.b() / 2));
                }
            }

            n2 = ++n;
        } while (true);
    }

    @Override
    public final String getTextualState() {
        return "Cleaning " + this.parent.getCleanHerbName();
    }

}

