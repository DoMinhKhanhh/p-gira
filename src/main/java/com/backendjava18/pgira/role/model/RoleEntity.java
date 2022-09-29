package com.backendjava18.pgira.role.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleEntity {
    @UtilityClass
    public static class Role{
        public static final String TABLE_NAME = "G_ROLE";
        public static final String NAME = "G_NAME";
        public static final String DESCRIPTION = "G_DESCRIPTION";
        public static final String CODE = "G_CODE";
    }
}
