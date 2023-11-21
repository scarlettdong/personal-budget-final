package cn.iocoder.yudao.module.budget.dal.mysql.expense;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.budget.dal.dataobject.expense.UserExpenseDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.budget.controller.admin.expense.vo.*;

/**
 * 支出 Mapper
 *
 * @author budget
 */
@Mapper
public interface UserExpenseMapper extends BaseMapperX<UserExpenseDO> {

    default PageResult<UserExpenseDO> selectPage(UserExpensePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserExpenseDO>()
                .eqIfPresent(UserExpenseDO::getBudgetId, reqVO.getBudgetId())
                .eqIfPresent(UserExpenseDO::getItem, reqVO.getItem())
                .eqIfPresent(UserExpenseDO::getExpenseAmount, reqVO.getExpenseAmount())
                .betweenIfPresent(UserExpenseDO::getExpenseDate, reqVO.getExpenseDate())
                .orderByDesc(UserExpenseDO::getId));
    }

    default List<UserExpenseDO> selectList(UserExpenseExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<UserExpenseDO>()
                .eqIfPresent(UserExpenseDO::getBudgetId, reqVO.getBudgetId())
                .eqIfPresent(UserExpenseDO::getItem, reqVO.getItem())
                .eqIfPresent(UserExpenseDO::getExpenseAmount, reqVO.getExpenseAmount())
                .betweenIfPresent(UserExpenseDO::getExpenseDate, reqVO.getExpenseDate())
                .orderByDesc(UserExpenseDO::getId));
    }

}
