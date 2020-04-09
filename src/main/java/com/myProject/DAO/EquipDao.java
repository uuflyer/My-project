package com.myProject.DAO;

import com.myProject.entity.Equip;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EquipDao {
    private final SqlSession sqlSession;

    @Inject
    public EquipDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Map<String, Object> asMap(Object... args) {
        Map<String, Object> result = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            result.put(args[i].toString(), args[i + 1]);
        }
        return result;
    }

    public List<Equip> getUserEquips(Integer page, Integer pageSize, Integer userId) {
        Map<String, Object> parameters = asMap(
                "user_id", userId,
                "offset", (page - 1) * pageSize,
                "limit", pageSize);


        return sqlSession.selectList("selectEquips", parameters);
    }

    public int count(Integer userId) {
        return sqlSession.selectOne("count", asMap("user_id", userId));
    }

    public Equip selectEquipById(Integer eid) {
        return sqlSession.selectOne("selectEquipById", eid);
    }

    public int deleteEquip(List<Integer> eids) {
        return sqlSession.delete("deleteEquip", eids);
    }

    public Equip insertEquip(Equip equip) {
        sqlSession.insert("insertEquip", equip);
        return selectEquipById(equip.getEId());
    }

    public Equip updateEquip(Equip targetEquip) {
        sqlSession.update("updateEquip", targetEquip);
        return selectEquipById(targetEquip.getEId());
    }
}
