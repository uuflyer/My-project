package com.myProject.Services;

import com.myProject.DAO.EquipDao;
import com.myProject.entity.Equip;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomNumber {

    private static EquipDao equipDao;

    public RandomNumber(EquipDao equipDao) {
        RandomNumber.equipDao = equipDao;
    }

    public static Integer getRandomNumber() {
        StringBuilder randomEquipId = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            randomEquipId.append(random.nextInt(10));
        }

        Integer EId = Integer.parseInt(randomEquipId.toString());
        Equip euipIdInDb = equipDao.selectEquipById(EId);

        if (euipIdInDb != null) {
            getRandomNumber();
        }
        return EId;
    }
}
