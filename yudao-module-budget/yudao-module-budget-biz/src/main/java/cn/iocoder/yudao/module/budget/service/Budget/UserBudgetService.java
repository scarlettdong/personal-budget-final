package cn.iocoder.yudao.module.budget.service.Budget;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.budget.controller.admin.Budget.vo.*;
import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预算 Service 接口
 *
 * @author budget
 */
public interface UserBudgetService {

    /**
     * 创建预算
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createUserBudget(@Valid UserBudgetCreateReqVO createReqVO);

    /**
     * 更新预算
     *
     * @param updateReqVO 更新信息
     */
    void updateUserBudget(@Valid UserBudgetUpdateReqVO updateReqVO);

    /**
     * 删除预算
     *
     * @param id 编号
     */
    void deleteUserBudget(Integer id);

    /**
     * 获得预算
     *
     * @param id 编号
     * @return 预算
     */
    UserBudgetDO getUserBudget(Integer id);

    /**
     * 获得预算列表
     *
     * @param ids 编号
     * @return 预算列表
     */
    List<UserBudgetDO> getUserBudgetList(Collection<Integer> ids);

    /**
     * 获得预算分页
     *
     * @param pageReqVO 分页查询
     * @return 预算分页
     */
    PageResult<UserBudgetDO> getUserBudgetPage(UserBudgetPageReqVO pageReqVO);

    /**
     * 获得预算列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 预算列表
     */
    List<UserBudgetDO> getUserBudgetList(UserBudgetExportReqVO exportReqVO);

}
