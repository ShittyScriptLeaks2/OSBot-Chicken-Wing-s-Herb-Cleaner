package core;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import state.*;
import ui.MainFrame;
import util.ExperienceTracker;
import util.PriceAPI;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Core extends Script {
    private DankHerb selectedHerb;
    private int profitPerClean;
    private String cleanHerbName;
    private int herbsCleaned;
    private int cleanHerbPrice;
    private int[] cleanPattern;
    private int grimyHerbPrice;
    private List<State> states;
    private int cleanDelay;
    private long startTimeMillis;
    private ExperienceTracker expTracker;
    private String grimyHerbName;
    private String textualState;
    private MainFrame mainFrame;
    private long runtimeMillis;

    public Core() {
        cleanPattern = new int[]{0, 1, 4, 5, 8, 9, 12, 13, 16, 17, 20, 21, 24, 25, 2, 3, 6, 7, 10, 11, 14, 15, 18, 19, 22, 23, 26, 27};
        textualState = "";
        states = new ArrayList<>();
        herbsCleaned = 0;
        cleanDelay = 0;
    }

    private static String formatElapsedTime(long elapsed) {
        long days = TimeUnit.MILLISECONDS.toDays(elapsed);
        long hours = TimeUnit.MILLISECONDS.toHours(elapsed) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(elapsed));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsed) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(elapsed));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsed) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsed));

        if (days != 0) {
            return "" + days + ":" + hours + ":" + minutes + ":" + seconds;
        }

        return "" + hours + "hrs : " + minutes + "mins : " + seconds + "s";
    }

    public int getHerbsCleaned() {
        return this.herbsCleaned;
    }

    public void setHerbsCleaned(int count) {
        this.herbsCleaned = count;
    }

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }

    public final String getCleanHerbName() {
        return this.cleanHerbName;
    }

    @Override
    public int onLoop() throws InterruptedException {
        State state = null;
        for (State cur : this.states) {
            if (cur.shouldExecute()) {
                state = cur;
                break;
            }
        }

        if (state == null) {
            return 100;
        }

        this.textualState = state.getTextualState();
        state.onLoop();
        return Core.random(200, 400);
    }

    public final int getCleanDelay() {
        return this.cleanDelay;
    }

    private void initializeUI() {
        this.mainFrame = new MainFrame();
        this.mainFrame.init();
        this.mainFrame.addWindowListener(new InstantCloseListener(this));
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setVisible(true);
    }

    @Override
    public void onExit() {
    }

    public final int[] getCleanPattern() {
        return this.cleanPattern;
    }

    @Override
    public void onPaint(Graphics2D graphics2D) {
        this.runtimeMillis = System.currentTimeMillis() - this.startTimeMillis;
        graphics2D.setColor(new Color(0, 0, 100, 120));
        graphics2D.fillRect(25, 240, 350, 82);

        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(25, 240, 3 * this.expTracker.getPercentageToNextLevel(Skill.HERBLORE), 9);

        graphics2D.setColor(Color.WHITE);
        graphics2D.drawRect(25, 240, 350, 9);
        graphics2D.drawRect(25, 240, 350, 82);

        int cleanedPerHour = (int) (3600000.0 / (double) (System.currentTimeMillis() - this.startTimeMillis) * (double) this.herbsCleaned);
        String profitsPerHour = String.valueOf(cleanedPerHour * this.profitPerClean).replaceAll("(\\d)(?=(\\d{3})+$)", "$1,");

        graphics2D.setFont(new Font("Monospaced", 0, 12));
        graphics2D.drawString("Herbs cleaned: " + this.herbsCleaned + " | (" + cleanedPerHour + ")", 28, 260);
        graphics2D.drawString("Current State: " + this.textualState, 28, 270);
        graphics2D.drawString("Time running: " + Core.formatElapsedTime(this.runtimeMillis), 28, 280);
        graphics2D.drawString("XP Gained: " + this.expTracker.getExperienceGained(Skill.HERBLORE) + (" | (" + this.expTracker.getLevelsGained(Skill.HERBLORE) + ")"), 28, 290);
        graphics2D.drawString("XP/hr: " + this.expTracker.gainedPerHour(Skill.HERBLORE) + " | Profit/hr: " + profitsPerHour, 28, 300);
        graphics2D.drawString("Time till next level: " + Core.formatElapsedTime(this.expTracker.timeUntilNextLevel(Skill.HERBLORE)), 28, 310);

        graphics2D.drawLine((int) this.getMouse().getPosition().getX(), (int) this.getMouse().getPosition().getY() + 10, (int) this.getMouse().getPosition().getX(), (int) this.getMouse().getPosition().getY() - 10);
        graphics2D.drawLine((int) this.getMouse().getPosition().getX() + 10, (int) this.getMouse().getPosition().getY(), (int) this.getMouse().getPosition().getX() - 10, (int) this.getMouse().getPosition().getY());

        graphics2D.setColor(Color.RED);
        graphics2D.drawString("by Chicken Wing      v1.0", 70, 320);
    }

    @Override
    public void onStart() {
        if (!this.widgets.get(548, 61).isVisible()) {
            this.widgets.get(548, 48).interact();
        }

        getBot().addMouseListener(new RetardMouseListener(this));
        this.expTracker = new ExperienceTracker(this);
        expTracker.startTracking(Skill.HERBLORE);
        EventQueue.invokeLater(this::initializeUI);

        this.cleanPattern = this.mainFrame.getCleanPattern();
        this.selectedHerb = this.mainFrame.getSelectedHerb();
        log(selectedHerb + " " + this.selectedHerb.getGrimyName());
        this.cleanHerbName = this.selectedHerb.getCleanName();
        this.grimyHerbName = this.selectedHerb.getGrimyName();
        this.cleanDelay = this.mainFrame.getCleanDelay();
        log("Delay set: " + this.cleanDelay + "ms");

        String textualPattern = "";
        for (int i = 0; i < cleanPattern.length; i++) {
            textualPattern += i;
            textualPattern += " ";
            textualPattern += cleanPattern[i];
        }
        log("Pattern: " + textualPattern);

        this.startTimeMillis = System.currentTimeMillis();

        Collections.addAll(states,
                new OpenBankState(this),
                new CloseBankState(this),
                new DepositItemsState(this),
                new CleanHerbsState(this),
                new WithdrawItemsState(this),
                new LogoutState(this)
        );

        this.mainFrame.dispose();

        try {
            this.cleanHerbPrice = PriceAPI.getSellingPrice(this.selectedHerb.getCleanId());
            this.grimyHerbPrice = PriceAPI.getBuyingPrice(this.selectedHerb.getGrimyId());
        } catch (IOException e1) {
            e1.printStackTrace();
            this.cleanHerbPrice = 0;
        }

        this.profitPerClean = this.cleanHerbPrice - this.grimyHerbPrice;
        new HerbsCleanedItemTracker(this).start();
    }

    public final String getGrimyHerbName() {
        return this.grimyHerbName;
    }
}

