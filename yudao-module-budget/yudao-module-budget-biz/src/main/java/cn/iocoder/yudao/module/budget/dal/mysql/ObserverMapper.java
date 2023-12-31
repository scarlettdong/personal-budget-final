package cn.iocoder.yudao.module.budget.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;
import com.github.yulichang.method.mp.SelectList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ObserverMapper  {
    @Select("SELECT category, amount, spent_amount FROM budget WHERE deleted=0")
    List<UserBudgetDO> getCategoryAndAmount();

    @Select("SELECT category, amount, spent_amount, budget_date FROM `budget` WHERE deleted=0 ORDER BY budget_date DESC LIMIT 0,5")
    List<UserBudgetDO> getDateCategory();
}
