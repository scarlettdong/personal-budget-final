package cn.iocoder.yudao.module.budget.controller.admin.Budget.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 预算 Excel VO
 *
 * @author budget
 */
@Data
public class UserBudgetExcelVO {

    @ExcelProperty("预算id")
    private Integer id;

    @ExcelProperty("预算类别")
    private String category;

    @ExcelProperty("预算金额")
    private BigDecimal amount;

    @ExcelProperty("预算金额")
    private BigDecimal spentAmount;

    @ExcelProperty("预算的日期")
    private LocalDate budgetDate;

}
