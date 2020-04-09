package com.myProject.entity;

import java.util.List;

public class EquipListResult extends Result<List<Equip>> {
    private int totalPage;
    private int page;
    private int totalEquipNumber;

    public EquipListResult(ResultStatus status, String msg, List<Equip> data, int page, int totalPage, int totalEquipNumber) {
        super(status, msg, data);
        this.page = page;
        this.totalPage = totalPage;
        this.totalEquipNumber = totalEquipNumber;
    }

    public static EquipListResult successExec(String msg) {
        return new EquipListResult(ResultStatus.OK, msg, null, 0, 0, 0);
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalEquipNumber() {
        return totalEquipNumber;
    }

    public int getPage() {
        return page;
    }

    public static EquipListResult success(List<Equip> data, int page, int totalPage, int totalEquipNumber) {
        return new EquipListResult(ResultStatus.OK, "获取成功", data, page, totalPage, totalEquipNumber);
    }


    public static EquipListResult failure(String msg) {
        return new EquipListResult(ResultStatus.FAIL, msg, null, 0, 0, 0);
    }

    public static EquipListResult failure(Exception e) {
        return new EquipListResult(ResultStatus.FAIL, e.getMessage(), null, 0, 0, 0);
    }


}
