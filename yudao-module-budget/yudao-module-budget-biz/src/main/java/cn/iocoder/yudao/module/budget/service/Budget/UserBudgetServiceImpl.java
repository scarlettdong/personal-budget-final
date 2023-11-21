package cn.iocoder.yudao.module.budget.service.Budget;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.budget.controller.admin.Budget.vo.*;
import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.budget.convert.Budget.UserBudgetConvert;
import cn.iocoder.yudao.module.budget.dal.mysql.Budget.UserBudgetMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.budget.enums.ErrorCodeConstants.*;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;

/**
 * 预算 Service 实现类
 *
 * @author budget
 */
@Service
@Validated
public class UserBudgetServiceImpl implements UserBudgetService {

    @Resource
    private UserBudgetMapper userBudgetMapper;

    @Override
    public Integer createUserBudget(UserBudgetCreateReqVO createReqVO) {
        // 插入
        UserBudgetDO userBudget = UserBudgetConvert.INSTANCE.convert(createReqVO);
        userBudgetMapper.insert(userBudget);
        // 返回
        return userBudget.getId();
    }

    @Override
    public void updateUserBudget(UserBudgetUpdateReqVO updateReqVO) {
        // 校验存在
        validateUserBudgetExists(updateReqVO.getId());
        // 更新
        UserBudgetDO updateObj = UserBudgetConvert.INSTANCE.convert(updateReqVO);
        userBudgetMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserBudget(Integer id) {
        // 校验存在
        validateUserBudgetExists(id);
        // 删除
        userBudgetMapper.deleteById(id);
    }

    private void validateUserBudgetExists(Integer id) {
        if (userBudgetMapper.selectById(id) == null) {
            throw exception(USER_BUDGET_NOT_EXISTS);
        }
    }

    @Override
    public UserBudgetDO getUserBudget(Integer id) {
        return userBudgetMapper.selectById(id);
    }

    @Override
    public List<UserBudgetDO> getUserBudgetList(Collection<Integer> ids) {
        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }
        return userBudgetMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<UserBudgetDO> getUserBudgetPage(UserBudgetPageReqVO pageReqVO) {
        return userBudgetMapper.selectPage(pageReqVO);
    }

    @Override
    public List<UserBudgetDO> getUserBudgetList(UserBudgetExportReqVO exportReqVO) {
        return userBudgetMapper.selectList(exportReqVO);
    }

}
