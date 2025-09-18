package tentech.healthcheck.model.enums;

public enum Role {
    ADMIN,
    USER;

    public String getAuthority() {
        return name();
    }
}
