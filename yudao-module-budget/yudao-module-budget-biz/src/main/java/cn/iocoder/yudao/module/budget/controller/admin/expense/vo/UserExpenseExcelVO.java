package cn.iocoder.yudao.module.budget.controller.admin.expense.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 支出 Excel VO
 *
 * @author budget
 */
@Data
public class UserExpenseExcelVO {

    @ExcelProperty("支出id")
    private Integer id;

    @ExcelProperty("预算id")
    private Integer budgetId;

    @ExcelProperty("支出的项目")
    private String item;

    @ExcelProperty("支出的金额")
    private BigDecimal expenseAmount;

    @ExcelProperty("支出的年月")
    private LocalDate expenseDate;

}
