package com.myProject.Services;

import com.myProject.DAO.EquipDao;
import com.myProject.entity.Equip;
import com.myProject.entity.EquipListResult;
import com.myProject.entity.EquipResult;
import com.myProject.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipService {
    private final EquipDao equipDao;

    @Inject
    public EquipService(EquipDao equipDao) {
        this.equipDao = equipDao;
    }


    public EquipListResult getEquips(Integer page, Integer pageSize, Integer userId) {
        try {

            List<Equip> EquipsInDb = equipDao.getUserEquips(page, pageSize, userId);

            int count = equipDao.count(userId);

            int totalPage = count % pageSize == 0 ? (count / pageSize) : (count / pageSize) + 1;

            return EquipListResult.success(EquipsInDb, page, totalPage, count);
        } catch (Exception e) {
//            throw new RuntimeException(e);
            return EquipListResult.failure("系统异常");
        }

    }


    public EquipResult insertEquip(Equip equip) {
        try {
            //随机生成8位数字的设备ID
            equip.setEId(RandomNumber.getRandomNumber());
            if (checkIsNull(equip)) {
                return EquipResult.failure("参数不能为空");
            }

            return EquipResult.success("添加成功", equipDao.insertEquip(equip));
        } catch (Exception e) {
            return EquipResult.failure(e);
        }

    }

    private boolean checkIsNull(Equip equip) {
        return equip.getEName() == null || equip.getCategory() == null || equip.getDescription() == null || equip.getProtocol() == null;
    }

    public EquipResult updateEquip(Integer EId, Equip targetEquip) {

        Equip equipInDB = equipDao.selectEquipById(EId);
        if (equipInDB == null) {
            return EquipResult.failure("设备信息不存在");
        }

        if (equipInDB.getUserId() != targetEquip.getUserId()) {
            return EquipResult.failure("无法修改别人的设备信息");
        }
        try {
            targetEquip.setEId(EId);
            return EquipResult.success("修改成功", equipDao.updateEquip(targetEquip));
        } catch (Exception e) {
            return EquipResult.failure(e);
        }

    }

    public EquipListResult deleteEquip(String eids, User user) {
        String[] equipIds = eids.split(",");
        Equip equipInDb = equipDao.selectEquipById(Integer.valueOf(equipIds[0]));
        if (equipInDb.getUserId() != user.getId()) {
            return EquipListResult.failure("无法删除别人的设备");
        }
        List<Integer> list = new ArrayList<>();
        for (String id : equipIds) {
            list.add(Integer.valueOf(id));

        }

        try {

            if (equipDao.deleteEquip(list) != list.size()) {
                return EquipListResult.failure("删除失败");
            }
            return EquipListResult.successExec("删除成功");
        } catch (Exception e) {
            return EquipListResult.failure(e);
        }
    }

    public EquipResult getEquipById(Integer EId) {
        try {
            return EquipResult.success("操作成功", equipDao.selectEquipById(EId));
        } catch (Exception e) {
            return EquipResult.failure(e);
        }
    }
}



