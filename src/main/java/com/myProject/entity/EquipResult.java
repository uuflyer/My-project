package com.myProject.entity;

public class EquipResult extends Result<Equip> {
    public EquipResult(ResultStatus status, String msg, Equip data) {
        super(status, msg, data);
    }

    public static EquipResult success(String msg) {
        return new EquipResult(ResultStatus.OK, msg, null);
    }

    public static EquipResult success(String msg, Equip data) {
        return new EquipResult(ResultStatus.OK, msg, data);
    }

    public static EquipResult failure(String msg) {
        return new EquipResult(ResultStatus.FAIL, msg, null);
    }

    public static EquipResult failure(Exception e) {
        return new EquipResult(ResultStatus.FAIL, e.getMessage(), null);
    }
}
