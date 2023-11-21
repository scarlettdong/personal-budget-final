package cn.iocoder.yudao.module.budget.convert.Budget;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.budget.controller.admin.Budget.vo.*;
import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;

/**
 * 预算 Convert
 *
 * @author budget
 */
@Mapper
public interface UserBudgetConvert {

    UserBudgetConvert INSTANCE = Mappers.getMapper(UserBudgetConvert.class);

    UserBudgetDO convert(UserBudgetCreateReqVO bean);

    UserBudgetDO convert(UserBudgetUpdateReqVO bean);

    UserBudgetRespVO convert(UserBudgetDO bean);

    List<UserBudgetRespVO> convertList(List<UserBudgetDO> list);

    PageResult<UserBudgetRespVO> convertPage(PageResult<UserBudgetDO> page);

    List<UserBudgetExcelVO> convertList02(List<UserBudgetDO> list);

}
