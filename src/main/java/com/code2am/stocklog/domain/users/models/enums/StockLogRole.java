package com.code2am.stocklog.domain.users.models.enums;

public enum StockLogRole {
    USER("USER"),
    ADMIN("ADMIN");

    private String role;

    StockLogRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "OhgiraffersRole{" +
                "role='" + role + '\'' +
                '}';
    }

}
