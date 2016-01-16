/*
 * Decompiled with CFR 0_110.
 */
package core;

public enum DankHerb {

    a("Guam leaf", 249, 199),
    d("Marrentill", 251, 201),
    j("Tarromin", 253, 203),
    g("Harralander", 255, 205),
    m("Ranarr weed", 257, 207),
    q("Toadflax", 2998, 3049),
    r("Irit leaf", 259, 209),
    s("Avantoe", 261, 211),
    o("Kwuarm", 263, 213),
    n("Snapdragon", 3000, 3051),
    f("Cadantine", 265, 215),
    p("Lantadyme", 2481, 2485),
    k("Dwarf weed", 267, 217),
    l("Torstol", 269, 219);

    private final int cleanId;
    private final int grimyId;
    private String grimyName;
    private String cleanName;

    private DankHerb(String name, int grimyId, int cleanId) {
        this.cleanName = name;
        this.grimyName = "Grimy " + name.toLowerCase();
        this.grimyId = grimyId;
        this.cleanId = cleanId;
    }

    public final int getGrimyId() {
        return this.grimyId;
    }

    public final int getCleanId() {
        return this.cleanId;
    }

    public final String getCleanName() {
        return this.cleanName;
    }

    @Override
    public final String toString() {
        return this.grimyName;
    }

    public final String getGrimyName() {
        return this.grimyName;
    }

}

