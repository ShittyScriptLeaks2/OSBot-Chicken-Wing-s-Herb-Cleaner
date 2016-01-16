package util;

import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.Script;

import java.util.Map;
import java.util.TreeMap;

public abstract class ItemTracker extends Thread {

    private final Map<String, Integer> previousItems;
    private final Map<String, Integer> currentItems;
    private final Script script;

    public ItemTracker(Script script) {
        this.script = script;
        this.previousItems = new TreeMap<>();
        this.currentItems = new TreeMap<>();
    }

    public abstract int onTick();

    public final int getChangedAmount(String string) {
        if (!this.currentItems.containsKey(string)) {
            if (!this.previousItems.containsKey(string)) {
                return 0;
            }

            return 0 - this.previousItems.get(string);
        }

        if (this.previousItems.containsKey(string)) {
            int prev = this.previousItems.get(string);
            return this.currentItems.get(string) - prev;
        }

        return this.currentItems.get(string);
    }

    @Override
    public void run() {
        if (this.script == null) {
            return;
        }

        while (this.script.getBot().getScriptExecutor().isRunning()) {
            Inventory inventory = this.script.getInventory();
            if (inventory != null) {
                this.previousItems.clear();
                this.previousItems.putAll(currentItems);
                this.currentItems.clear();
                Item[] items = inventory.getItems();
                for (Item item : items) {
                    if (item != null) {
                        String name = item.getName();
                        if (name != null && !this.currentItems.containsKey(name)) {
                            this.currentItems.put(name, (int) inventory.getAmount(name));
                        }
                    }
                }
            }

            try {
                this.onTick();
                Thread.sleep(100);
            } catch (InterruptedException v3) {
                v3.printStackTrace();
            }
        }
    }

}

