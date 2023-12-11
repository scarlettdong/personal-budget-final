package cn.iocoder.yudao.module.budget.enums;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 *
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {
    ErrorCode USER_BUDGET_NOT_EXISTS = new ErrorCode(1-010-000-001, "预算不存在");

    ErrorCode USER_EXPENSE_NOT_EXISTS = new ErrorCode(1-010-000-002, "支出不存在");
}
