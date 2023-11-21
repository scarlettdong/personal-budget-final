package cn.iocoder.yudao.module.budget.convert.expense;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.budget.controller.admin.expense.vo.*;
import cn.iocoder.yudao.module.budget.dal.dataobject.expense.UserExpenseDO;

/**
 * 支出 Convert
 *
 * @author budget
 */
@Mapper
public interface UserExpenseConvert {

    UserExpenseConvert INSTANCE = Mappers.getMapper(UserExpenseConvert.class);

    UserExpenseDO convert(UserExpenseCreateReqVO bean);

    UserExpenseDO convert(UserExpenseUpdateReqVO bean);

    UserExpenseRespVO convert(UserExpenseDO bean);

    List<UserExpenseRespVO> convertList(List<UserExpenseDO> list);

    PageResult<UserExpenseRespVO> convertPage(PageResult<UserExpenseDO> page);

    List<UserExpenseExcelVO> convertList02(List<UserExpenseDO> list);

}
