package data;

public enum Forms {

    Eng("en"),
    Esp("es");

    private final String displayName;

    Forms(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
