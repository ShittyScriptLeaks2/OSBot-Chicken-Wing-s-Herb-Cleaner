/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.osbot.rs07.Bot
 *  org.osbot.rs07.api.Mouse
 *  org.osbot.rs07.api.Widgets
 *  org.osbot.rs07.api.ui.RS2Widget
 *  org.osbot.rs07.api.ui.Skill
 *  org.osbot.rs07.script.Script
 */
package core;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import state.*;
import ui.MainFrame;
import util.ExperienceTracker;
import util.PriceAPI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Core extends Script {
    private DankHerb a;
    private int b;
    private String cleanHerbName;
    private int herbsCleaned;
    private int e;
    private int[] cleanPattern;
    private int g;
    private List<State> states;
    private int i;
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
        i = 0;
    }

    static int setAndGetHerbsCleaned(Core core, int count) {
        core.herbsCleaned = count;
        return core.herbsCleaned;
    }

    static int getHerbsCleaned(Core core) {
        return core.herbsCleaned;
    }

    static String getGrimyHerbName(Core core) {
        return core.grimyHerbName;
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

    static MainFrame getMainFrame(Core core) {
        return core.mainFrame;
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

    public final int b() {
        return this.i;
    }

    private void e() {
        this.mainFrame = new MainFrame();
        this.mainFrame.init();
        this.mainFrame.addWindowListener(new InstantCloseListener(this));
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setVisible(true);
    }

    public void onExit() {
    }

    public final int[] getCleanPattern() {
        return this.cleanPattern;
    }

    private void setCleanPattern(int[] pattern) {
        this.cleanPattern = pattern;
    }

    public void onPaint(Graphics2D graphics2D) {
        this.runtimeMillis = System.currentTimeMillis() - this.startTimeMillis;
        Object object = new Color(0, 0, 100, 120);
        Color color = Color.GREEN;
        graphics2D.setColor((Color) object);
        graphics2D.fillRect(25, 240, 350, 82);
        graphics2D.setColor(color);
        graphics2D.fillRect(25, 240, 3 * this.expTracker.a(Skill.HERBLORE), 9);
        graphics2D.setFont(new Font("Monospaced", 0, 12));
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawRect(25, 240, 350, 9);
        graphics2D.drawRect(25, 240, 350, 82);
        int n = (int) (3600000.0 / (double) (System.currentTimeMillis() - this.startTimeMillis) * (double) this.herbsCleaned);
        String profitsPerHour = String.valueOf(n * this.b).replaceAll("(\\d)(?=(\\d{3})+$)", "$1,");
        graphics2D.drawString(new StringBuilder().insert(0, "Herbs cleaned: ").append(this.herbsCleaned).append(" | (").append(n).append(")").toString(), 28, 260);
        graphics2D.drawString(new StringBuilder().insert(0, "Current State: ").append(this.textualState).toString(), 28, 270);
        graphics2D.drawString(new StringBuilder().insert(0, "Time running: ").append(Core.formatElapsedTime(this.runtimeMillis)).toString(), 28, 280);
        graphics2D.drawString(new StringBuilder().insert(0, "XP Gained: ").append(this.expTracker.gained(Skill.HERBLORE)).append(" | (").append(this.expTracker.c(Skill.HERBLORE)).append(")").toString(), 28, 290);
        graphics2D.drawString(new StringBuilder().insert(0, "XP/hr: ").append(this.expTracker.gainedPerHour(Skill.HERBLORE)).append(" | Profit/hr: ").append(profitsPerHour).toString(), 28, 300);
        graphics2D.drawString(new StringBuilder().insert(0, "Time till next level: ").append(Core.formatElapsedTime(this.expTracker.d(Skill.HERBLORE))).toString(), 28, 310);
        graphics2D.drawLine((int) this.getMouse().getPosition().getX(), (int) this.getMouse().getPosition().getY() + 10, (int) this.getMouse().getPosition().getX(), (int) this.getMouse().getPosition().getY() - 10);
        graphics2D.drawLine((int) this.getMouse().getPosition().getX() + 10, (int) this.getMouse().getPosition().getY(), (int) this.getMouse().getPosition().getX() - 10, (int) this.getMouse().getPosition().getY());
        graphics2D.setColor(Color.RED);
        graphics2D.drawString("by Chicken Wing      v1.0", 70, 320);
    }

    public void onStart() {
        if (!this.widgets.get(548, 61).isVisible()) {
            this.widgets.get(548, 48).interact();
        }

        getBot().addMouseListener(new RetardMouseListener(this));
        this.expTracker = new ExperienceTracker(this);
        expTracker.startTracking(new Skill[]{Skill.HERBLORE});
        EventQueue.invokeLater(this::e);

        this.cleanPattern = this.mainFrame.a();
        a = mainFrame.getSelectedHerb();
        log(new StringBuilder().insert(0, a.getCleanName()).append(" ").append(this.a.getGrimyName()).toString());
        cleanHerbName = a.getCleanName();
        grimyHerbName = a.getGrimyName();
        i = mainFrame.b();
        log("Delay set: " + this.i + "ms");

        String textualPattern = "";
        for (int i = 0; i < cleanPattern.length; i++) {
            textualPattern += i;
            textualPattern += " ";
            textualPattern += cleanPattern[i];
        }

        log("Pattern: " + textualPattern);
        startTimeMillis = System.currentTimeMillis();
        Collections.addAll(states, new OpenBankState(this), new CloseBankState(this), new DepositItemsState(this), new CleanHerbsState(this), new WithdrawItemsState(this), new LogoutState(this));
        this.mainFrame.dispose();
        PriceAPI var2_34 = new PriceAPI();
        this.e = var2_34.c(this.a.getGrimyId());
        b = e - g;
        new HerbsCleanedItemTracker(this, this).start();
    }

    public final String getGrimyHerbName() {
        return this.grimyHerbName;
    }
}

