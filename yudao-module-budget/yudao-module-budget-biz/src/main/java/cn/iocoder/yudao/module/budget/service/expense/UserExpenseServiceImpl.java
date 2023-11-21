package cn.iocoder.yudao.module.budget.service.expense;

import cn.iocoder.yudao.module.budget.convert.Budget.UserBudgetConvert;
import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;
import cn.iocoder.yudao.module.budget.dal.mysql.Budget.UserBudgetMapper;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.budget.controller.admin.expense.vo.*;
import cn.iocoder.yudao.module.budget.dal.dataobject.expense.UserExpenseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.budget.convert.expense.UserExpenseConvert;
import cn.iocoder.yudao.module.budget.dal.mysql.expense.UserExpenseMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.budget.enums.ErrorCodeConstants.*;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;

/**
 * 支出 Service 实现类
 *
 * @author budget
 */
@Service
@Validated
public class UserExpenseServiceImpl implements UserExpenseService {

    @Resource
    private UserExpenseMapper userExpenseMapper;

    @Resource
    private UserBudgetMapper userBudgetMapper;
    @Transactional
    @Override
    public Integer createUserExpense(UserExpenseCreateReqVO createReqVO) {
        UserBudgetDO userBudget = userBudgetMapper.selectById(createReqVO.getBudgetId());
        BigDecimal spentAmount = userBudget.getSpentAmount().add(createReqVO.getExpenseAmount());
        userBudget.setSpentAmount(spentAmount);
        userBudgetMapper.updateById(userBudget);
        // 插入
        UserExpenseDO userExpense = UserExpenseConvert.INSTANCE.convert(createReqVO);
        userExpenseMapper.insert(userExpense);
        // 返回
        return userExpense.getId();
    }

    @Override
    public void updateUserExpense(UserExpenseUpdateReqVO updateReqVO) {
        // 校验存在
        validateUserExpenseExists(updateReqVO.getId());
        // 更新
        UserExpenseDO updateObj = UserExpenseConvert.INSTANCE.convert(updateReqVO);
        userExpenseMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserExpense(Integer id) {
        // 校验存在
        validateUserExpenseExists(id);
        // 删除
        userExpenseMapper.deleteById(id);
    }

    private void validateUserExpenseExists(Integer id) {
        if (userExpenseMapper.selectById(id) == null) {
            throw exception(USER_EXPENSE_NOT_EXISTS);
        }
    }

    @Override
    public UserExpenseDO getUserExpense(Integer id) {
        return userExpenseMapper.selectById(id);
    }

    @Override
    public List<UserExpenseDO> getUserExpenseList(Collection<Integer> ids) {
        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }
        return userExpenseMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<UserExpenseDO> getUserExpensePage(UserExpensePageReqVO pageReqVO) {
        return userExpenseMapper.selectPage(pageReqVO);
    }

    @Override
    public List<UserExpenseDO> getUserExpenseList(UserExpenseExportReqVO exportReqVO) {
        return userExpenseMapper.selectList(exportReqVO);
    }

}
