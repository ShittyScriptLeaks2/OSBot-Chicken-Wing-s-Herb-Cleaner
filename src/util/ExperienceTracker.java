package util;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import java.util.EnumMap;

public final class ExperienceTracker {

    private final Script script;
    private final EnumMap<Skill, ExperienceTrackerNode> nodes;

    public ExperienceTracker(Script script) {
        this.script = script;
        this.nodes = new EnumMap<>(Skill.class);
    }

    public Script getScript() {
        return this.script;
    }

    private int getExperienceForLevel(int index) {
        return this.script.skills.getExperienceForLevel(index);
    }

    public final void startTracking(Skill... skills) {
        for (Skill skill : skills) {
            this.nodes.put(skill, new ExperienceTrackerNode(this, skill));
        }
    }

    public final int getPercentageToNextLevel(Skill skill) {
        int currentLevel = script.skills.getStatic(skill);
        int nextLevel = currentLevel + 1;
        int levelExpDiff = getExperienceForLevel(Math.max(currentLevel, nextLevel)) - getExperienceForLevel(Math.min(currentLevel, nextLevel));
        return (int) ((double) (script.skills.getExperience(skill) - this.getExperienceForLevel(currentLevel)) / (double) levelExpDiff * 100.0);
    }

    private ExperienceTrackerNode getNode(Skill skill) {
        ExperienceTrackerNode cc = this.nodes.get(skill);
        if (cc != null) {
            return cc;
        }

        throw new UnsupportedOperationException("You're not tracking this skill!");
    }

    public final int getExperienceGained(Skill skill) {
        return this.script.skills.getExperience(skill) - this.getNode(skill).getExperience();
    }

    public final int getLevelsGained(Skill skill) {
        return this.script.skills.getStatic(skill) - this.getNode(skill).getStaticLevel();
    }

    public final long timeUntilNextLevel(Skill skill) {
        if (this.getExperienceGained(skill) <= 0) {
            return 0;
        }

        return (long) ((double) script.skills.experienceToLevel(skill) * 3600000.0 / (double) this.gainedPerHour(skill));
    }

    public final int gainedPerHour(Skill skill) {
        long startTime = this.getNode(skill).getStartTime();
        long gained = this.getExperienceGained(skill);
        return (int) (3600000.0 / (double) (System.currentTimeMillis() - startTime) * (double) gained);
    }

}

