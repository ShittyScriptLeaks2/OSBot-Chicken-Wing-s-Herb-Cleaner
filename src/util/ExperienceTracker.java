package util;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

public final class ExperienceTracker {
    private final EnumMap<Skill, ExperienceTrackerNode> nodes;
    private Script script;

    public ExperienceTracker(Script script) {
        this.script = script;
        this.nodes = new EnumMap<>(Skill.class);
    }

    private static double a(long l, long l2) {
        return 3600000.0 / (double) (System.currentTimeMillis() - l2) * (double) l;
    }

    static Script a(ExperienceTracker ddd2) {
        return ddd2.script;
    }

    private int getExperienceForLevel(int index) {
        return this.script.skills.getExperienceForLevel(index);
    }

    public final void startTracking(Skill... skills) {
        for (Skill skill : skills) {
            this.nodes.put(skill, new ExperienceTrackerNode(this, skill));
        }
    }

    private List<Skill> getTrackedSkills() {
        Iterator<Skill> iterator = this.nodes.keySet().iterator();
        ArrayList<Skill> arrayList = new ArrayList<>();
        while (iterator.hasNext()) {
            Skill skill = iterator.next();
            if (gained(skill) > 0) {
                arrayList.add(skill);
            }
        }
        return arrayList;
    }

    public final int a(Skill skill) {
        ExperienceTracker ddd2 = this;
        int n = ddd2.script.skills.getStatic(skill);
        int n2 = n + 1;
        int n4 = ddd2.getExperienceForLevel(Math.max(n, n2)) - ddd2.getExperienceForLevel(Math.min(n, n2));
        return (int) ((double) (ddd2.script.skills.getExperience(skill) - this.getExperienceForLevel(n)) / (double) n4 * 100.0);
    }

    private ExperienceTrackerNode getNode(Skill skill) {
        ExperienceTrackerNode cc = this.nodes.get(skill);
        if (cc != null) {
            return cc;
        }

        throw new UnsupportedOperationException("You're not tracking this skill!");
    }

    public final int gained(Skill skill) {
        return this.script.skills.getExperience(skill) - this.getNode(skill).getExperience();
    }

    private int g(Skill skill) {
        return this.script.skills.experienceToLevel(skill);
    }

    private long a(Skill skill, int n) {
        if (this.gained(skill) <= 0) {
            return 0;
        }
        int n2 = n;
        ExperienceTracker ddd2 = this;
        return (long) ((double) (ddd2.getExperienceForLevel(n2) - ddd2.script.skills.getExperience(skill)) * 3600000.0 / (double) this.gainedPerHour(skill));
    }

    private int b(Skill skill, int n) {
        return this.getExperienceForLevel(n) - this.script.skills.getExperience(skill);
    }

    public final int c(Skill skill) {
        return this.script.skills.getStatic(skill) - this.getNode(skill).getStaticLevel();
    }

    public final long d(Skill skill) {
        if (this.gained(skill) <= 0) {
            return 0;
        }

        return (long) ((double) script.skills.experienceToLevel(skill) * 3600000.0 / (double) this.gainedPerHour(skill));
    }

    private EnumMap<Skill, ExperienceTrackerNode> getNodes() {
        return this.nodes;
    }

    private int a(int n, int n2) {
        return this.getExperienceForLevel(Math.max(n, n2)) - this.getExperienceForLevel(Math.min(n, n2));
    }

    private long getTotalExperienceGained() {
        Iterator<Skill> iterator = nodes.keySet().iterator();
        ArrayList<Skill> tracking = new ArrayList<>();

        while (iterator.hasNext()) {
            Skill skill = iterator.next();
            if (gained(skill) > 0) {
                tracking.add(skill);
            }
        }

        long l = 0;
        iterator = tracking.iterator();
        while (iterator.hasNext()) {
            l += this.gained(iterator.next());
        }

        return l;
    }

    public final int gainedPerHour(Skill skill) {
        long startTime = this.getNode(skill).getStartTime();
        long gained = this.gained(skill);
        return (int) (3600000.0 / (double) (System.currentTimeMillis() - startTime) * (double) gained);
    }

    private boolean isTracked(Skill skill) {
        if (this.gained(skill) <= 0) {
            return false;
        }
        return true;
    }

    private long getTrackedTime(Skill skill) {
        return System.currentTimeMillis() - ExperienceTrackerNode.getStartTimeBridge(this.getNode(skill));
    }

}

