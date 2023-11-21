package cn.iocoder.yudao.module.budget.controller.admin.Budget;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.budget.controller.admin.Budget.vo.*;
import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;
import cn.iocoder.yudao.module.budget.convert.Budget.UserBudgetConvert;
import cn.iocoder.yudao.module.budget.service.Budget.UserBudgetService;

@Tag(name = "管理后台 - 预算")
@RestController
@RequestMapping("/budget/user-budget")
@Validated
public class UserBudgetController {

    @Resource
    private UserBudgetService userBudgetService;

    @PostMapping("/create")
    @Operation(summary = "创建预算")
    @PreAuthorize("@ss.hasPermission('budget:user-budget:create')")
    public CommonResult<Integer> createUserBudget(@Valid @RequestBody UserBudgetCreateReqVO createReqVO) {
        return success(userBudgetService.createUserBudget(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预算")
    @PreAuthorize("@ss.hasPermission('budget:user-budget:update')")
    public CommonResult<Boolean> updateUserBudget(@Valid @RequestBody UserBudgetUpdateReqVO updateReqVO) {
        userBudgetService.updateUserBudget(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预算")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('budget:user-budget:delete')")
    public CommonResult<Boolean> deleteUserBudget(@RequestParam("id") Integer id) {
        userBudgetService.deleteUserBudget(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预算")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('budget:user-budget:query')")
    public CommonResult<UserBudgetRespVO> getUserBudget(@RequestParam("id") Integer id) {
        UserBudgetDO userBudget = userBudgetService.getUserBudget(id);
        return success(UserBudgetConvert.INSTANCE.convert(userBudget));
    }

    @GetMapping("/list")
    @Operation(summary = "获得预算列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('budget:user-budget:query')")
    public CommonResult<List<UserBudgetRespVO>> getUserBudgetList(@RequestParam("ids") Collection<Integer> ids) {
        List<UserBudgetDO> list = userBudgetService.getUserBudgetList(ids);
        return success(UserBudgetConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预算分页")
    @PreAuthorize("@ss.hasPermission('budget:user-budget:query')")
    public CommonResult<PageResult<UserBudgetRespVO>> getUserBudgetPage(@Valid UserBudgetPageReqVO pageVO) {
        PageResult<UserBudgetDO> pageResult = userBudgetService.getUserBudgetPage(pageVO);
        return success(UserBudgetConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预算 Excel")
    @PreAuthorize("@ss.hasPermission('budget:user-budget:export')")
    @OperateLog(type = EXPORT)
    public void exportUserBudgetExcel(@Valid UserBudgetExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<UserBudgetDO> list = userBudgetService.getUserBudgetList(exportReqVO);
        // 导出 Excel
        List<UserBudgetExcelVO> datas = UserBudgetConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "预算.xls", "数据", UserBudgetExcelVO.class, datas);
    }

}
