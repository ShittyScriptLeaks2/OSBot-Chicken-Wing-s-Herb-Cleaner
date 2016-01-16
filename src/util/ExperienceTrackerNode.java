package util;

import org.osbot.rs07.api.ui.Skill;

public final class ExperienceTrackerNode {

    private final int experience;
    private final long startTime;
    private final int staticLevel;

    public ExperienceTrackerNode(ExperienceTracker parent, Skill skill) {
        this.experience = parent.getScript().skills.getExperience(skill);
        this.staticLevel = parent.getScript().skills.getStatic(skill);
        this.startTime = System.currentTimeMillis();
    }

    public static long getStartTimeBridge(ExperienceTrackerNode etn) {
        return etn.startTime;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final int getExperience() {
        return this.experience;
    }

    public final int getStaticLevel() {
        return this.staticLevel;
    }

}

