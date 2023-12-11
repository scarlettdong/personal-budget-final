package cn.iocoder.yudao.module.budget.service.impl;

import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;
import cn.iocoder.yudao.module.budget.dal.mysql.ObserverMapper;
import cn.iocoder.yudao.module.budget.service.ObserverService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ObserverServiceimpl implements ObserverService {
    @Resource
    private ObserverMapper observerMapper;
    @Override
    public List<UserBudgetDO> getCategoryAndAmount() {
        return observerMapper.getCategoryAndAmount();
    }

    @Override
    public List<UserBudgetDO> getCateryDate() {
        return observerMapper.getDateCategory();
    }
}
