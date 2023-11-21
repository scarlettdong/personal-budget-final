package cn.iocoder.yudao.module.budget.service;

import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;

import java.util.List;

public interface ObserverService {
    public List<UserBudgetDO> getCategoryAndAmount();

    public List<UserBudgetDO> getCateryDate();
}
