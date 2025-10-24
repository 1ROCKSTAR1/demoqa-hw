package data;

public enum Forms {

    Elements("Elements"),
    Afw("Alerts, Frame & Windows");

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
