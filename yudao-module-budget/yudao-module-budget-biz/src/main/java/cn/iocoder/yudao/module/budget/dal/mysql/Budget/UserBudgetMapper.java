package cn.iocoder.yudao.module.budget.dal.mysql.Budget;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.budget.controller.admin.Budget.vo.*;

/**
 * 预算 Mapper
 *
 * @author budget
 */
@Mapper
public interface UserBudgetMapper extends BaseMapperX<UserBudgetDO> {

    default PageResult<UserBudgetDO> selectPage(UserBudgetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserBudgetDO>()
                .eqIfPresent(UserBudgetDO::getCategory, reqVO.getCategory())
                .eqIfPresent(UserBudgetDO::getAmount, reqVO.getAmount())
                .eqIfPresent(UserBudgetDO::getSpentAmount, reqVO.getSpentAmount())
                .betweenIfPresent(UserBudgetDO::getBudgetDate, reqVO.getBudgetDate())
                .orderByDesc(UserBudgetDO::getId));
    }

    default List<UserBudgetDO> selectList(UserBudgetExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<UserBudgetDO>()
                .eqIfPresent(UserBudgetDO::getCategory, reqVO.getCategory())
                .eqIfPresent(UserBudgetDO::getAmount, reqVO.getAmount())
                .eqIfPresent(UserBudgetDO::getSpentAmount, reqVO.getSpentAmount())
                .betweenIfPresent(UserBudgetDO::getBudgetDate, reqVO.getBudgetDate())
                .orderByDesc(UserBudgetDO::getId));
    }

}
