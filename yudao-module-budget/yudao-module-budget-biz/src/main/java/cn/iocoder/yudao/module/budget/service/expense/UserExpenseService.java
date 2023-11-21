package cn.iocoder.yudao.module.budget.service.expense;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.budget.controller.admin.expense.vo.*;
import cn.iocoder.yudao.module.budget.dal.dataobject.expense.UserExpenseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 支出 Service 接口
 *
 * @author budget
 */
public interface UserExpenseService {

    /**
     * 创建支出
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createUserExpense(@Valid UserExpenseCreateReqVO createReqVO);

    /**
     * 更新支出
     *
     * @param updateReqVO 更新信息
     */
    void updateUserExpense(@Valid UserExpenseUpdateReqVO updateReqVO);

    /**
     * 删除支出
     *
     * @param id 编号
     */
    void deleteUserExpense(Integer id);

    /**
     * 获得支出
     *
     * @param id 编号
     * @return 支出
     */
    UserExpenseDO getUserExpense(Integer id);

    /**
     * 获得支出列表
     *
     * @param ids 编号
     * @return 支出列表
     */
    List<UserExpenseDO> getUserExpenseList(Collection<Integer> ids);

    /**
     * 获得支出分页
     *
     * @param pageReqVO 分页查询
     * @return 支出分页
     */
    PageResult<UserExpenseDO> getUserExpensePage(UserExpensePageReqVO pageReqVO);

    /**
     * 获得支出列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 支出列表
     */
    List<UserExpenseDO> getUserExpenseList(UserExpenseExportReqVO exportReqVO);

}
