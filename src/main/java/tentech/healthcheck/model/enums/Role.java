package tentech.healthcheck.model.enums;

public enum Role {
    ADMIN,
    USER;
    @Override
    public String getAuthority() {
        return name();
    }
}
