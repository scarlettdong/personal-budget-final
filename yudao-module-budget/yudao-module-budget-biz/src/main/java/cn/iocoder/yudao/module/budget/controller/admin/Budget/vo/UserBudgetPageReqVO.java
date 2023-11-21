package cn.iocoder.yudao.module.budget.controller.admin.Budget.vo;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "管理后台 - 预算分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserBudgetPageReqVO extends PageParam {

    @Schema(description = "预算类别")
    private String category;

    @Schema(description = "预算金额")
    private BigDecimal amount;

    @Schema(description = "预算金额")
    private BigDecimal spentAmount;

    @Schema(description = "预算的日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] budgetDate;

}
