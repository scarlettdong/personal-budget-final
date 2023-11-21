package cn.iocoder.yudao.module.budget.controller.admin.expense;

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

import cn.iocoder.yudao.module.budget.controller.admin.expense.vo.*;
import cn.iocoder.yudao.module.budget.dal.dataobject.expense.UserExpenseDO;
import cn.iocoder.yudao.module.budget.convert.expense.UserExpenseConvert;
import cn.iocoder.yudao.module.budget.service.expense.UserExpenseService;

@Tag(name = "管理后台 - 支出")
@RestController
@RequestMapping("/budget/user-expense")
@Validated
public class UserExpenseController {

    @Resource
    private UserExpenseService userExpenseService;

    @PostMapping("/create")
    @Operation(summary = "创建支出")
    @PreAuthorize("@ss.hasPermission('budget:user-expense:create')")
    public CommonResult<Integer> createUserExpense(@Valid @RequestBody UserExpenseCreateReqVO createReqVO) {
        return success(userExpenseService.createUserExpense(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新支出")
    @PreAuthorize("@ss.hasPermission('budget:user-expense:update')")
    public CommonResult<Boolean> updateUserExpense(@Valid @RequestBody UserExpenseUpdateReqVO updateReqVO) {
        userExpenseService.updateUserExpense(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除支出")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('budget:user-expense:delete')")
    public CommonResult<Boolean> deleteUserExpense(@RequestParam("id") Integer id) {
        userExpenseService.deleteUserExpense(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得支出")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('budget:user-expense:query')")
    public CommonResult<UserExpenseRespVO> getUserExpense(@RequestParam("id") Integer id) {
        UserExpenseDO userExpense = userExpenseService.getUserExpense(id);
        return success(UserExpenseConvert.INSTANCE.convert(userExpense));
    }

    @GetMapping("/list")
    @Operation(summary = "获得支出列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('budget:user-expense:query')")
    public CommonResult<List<UserExpenseRespVO>> getUserExpenseList(@RequestParam("ids") Collection<Integer> ids) {
        List<UserExpenseDO> list = userExpenseService.getUserExpenseList(ids);
        return success(UserExpenseConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得支出分页")
    @PreAuthorize("@ss.hasPermission('budget:user-expense:query')")
    public CommonResult<PageResult<UserExpenseRespVO>> getUserExpensePage(@Valid UserExpensePageReqVO pageVO) {
        PageResult<UserExpenseDO> pageResult = userExpenseService.getUserExpensePage(pageVO);
        return success(UserExpenseConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出支出 Excel")
    @PreAuthorize("@ss.hasPermission('budget:user-expense:export')")
    @OperateLog(type = EXPORT)
    public void exportUserExpenseExcel(@Valid UserExpenseExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<UserExpenseDO> list = userExpenseService.getUserExpenseList(exportReqVO);
        // 导出 Excel
        List<UserExpenseExcelVO> datas = UserExpenseConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "支出.xls", "数据", UserExpenseExcelVO.class, datas);
    }

}
