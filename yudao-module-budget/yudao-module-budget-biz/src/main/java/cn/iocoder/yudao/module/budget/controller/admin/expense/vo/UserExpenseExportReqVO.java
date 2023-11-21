package cn.iocoder.yudao.module.budget.controller.admin.expense.vo;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "管理后台 - 支出 Excel 导出 Request VO，参数和 UserExpensePageReqVO 是一致的")
@Data
public class UserExpenseExportReqVO {

    @Schema(description = "预算id", example = "5455")
    private Integer budgetId;

    @Schema(description = "支出的项目")
    private String item;

    @Schema(description = "支出的金额")
    private BigDecimal expenseAmount;

    @Schema(description = "支出的年月")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] expenseDate;

}
