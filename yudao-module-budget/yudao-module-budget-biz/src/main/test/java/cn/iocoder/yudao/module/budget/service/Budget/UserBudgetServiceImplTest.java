package cn.iocoder.yudao.module.budget.service.Budget;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.budget.controller.admin.Budget.vo.UserBudgetCreateReqVO;
import cn.iocoder.yudao.module.budget.controller.admin.Budget.vo.UserBudgetExportReqVO;
import cn.iocoder.yudao.module.budget.controller.admin.Budget.vo.UserBudgetPageReqVO;
import cn.iocoder.yudao.module.budget.controller.admin.Budget.vo.UserBudgetUpdateReqVO;
import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;
import cn.iocoder.yudao.module.budget.dal.mysql.Budget.UserBudgetMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserBudgetServiceImplTest {

    @Mock
    private UserBudgetMapper mockUserBudgetMapper;

    @InjectMocks
    private UserBudgetServiceImpl userBudgetServiceImplUnderTest;



    @Test
    void testUpdateUserBudget_UserBudgetMapperSelectByIdReturnsNull() {
        // Setup
        final UserBudgetUpdateReqVO updateReqVO = new UserBudgetUpdateReqVO();
        updateReqVO.setSpentAmount(new BigDecimal("0.00"));
        updateReqVO.setId(0);

        when(mockUserBudgetMapper.selectById(0)).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> userBudgetServiceImplUnderTest.updateUserBudget(updateReqVO))
            .isInstanceOf(ServiceException.class);
    }

    @Test
    void testDeleteUserBudget() {
        // Setup
        when(mockUserBudgetMapper.selectById(0)).thenReturn(UserBudgetDO.builder()
            .id(0)
            .build());

        // Run the test
        userBudgetServiceImplUnderTest.deleteUserBudget(0);

        // Verify the results
        verify(mockUserBudgetMapper).deleteById(0);
    }

    @Test
    void testDeleteUserBudget_UserBudgetMapperSelectByIdReturnsNull() {
        // Setup
        when(mockUserBudgetMapper.selectById(0)).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> userBudgetServiceImplUnderTest.deleteUserBudget(0))
            .isInstanceOf(ServiceException.class);
    }

    @Test
    void testGetUserBudget() {
        // Setup
        final UserBudgetDO expectedResult = UserBudgetDO.builder()
            .id(0)
            .build();
        when(mockUserBudgetMapper.selectById(0)).thenReturn(UserBudgetDO.builder()
            .id(0)
            .build());

        // Run the test
        final UserBudgetDO result = userBudgetServiceImplUnderTest.getUserBudget(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testGetUserBudgetPage() {
        // Setup
        final UserBudgetPageReqVO pageReqVO = new UserBudgetPageReqVO();
        pageReqVO.setCategory("category");
        pageReqVO.setAmount(new BigDecimal("0.00"));
        pageReqVO.setSpentAmount(new BigDecimal("0.00"));
        pageReqVO.setBudgetDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});

        final PageResult<UserBudgetDO> expectedResult = new PageResult<>(Arrays.asList(UserBudgetDO.builder()
            .id(0)
            .build()), 0L);

        // Configure UserBudgetMapper.selectPage(...).
        final PageResult<UserBudgetDO> userBudgetDOPageResult = new PageResult<>(Arrays.asList(UserBudgetDO.builder()
            .id(0)
            .build()), 0L);
        final UserBudgetPageReqVO reqVO = new UserBudgetPageReqVO();
        reqVO.setCategory("category");
        reqVO.setAmount(new BigDecimal("0.00"));
        reqVO.setSpentAmount(new BigDecimal("0.00"));
        reqVO.setBudgetDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});
        when(mockUserBudgetMapper.selectPage(reqVO)).thenReturn(userBudgetDOPageResult);

        // Run the test
        final PageResult<UserBudgetDO> result = userBudgetServiceImplUnderTest.getUserBudgetPage(pageReqVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetUserBudgetPage_UserBudgetMapperReturnsNoItem() {
        // Setup
        final UserBudgetPageReqVO pageReqVO = new UserBudgetPageReqVO();
        pageReqVO.setCategory("category");
        pageReqVO.setAmount(new BigDecimal("0.00"));
        pageReqVO.setSpentAmount(new BigDecimal("0.00"));
        pageReqVO.setBudgetDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});

        // Configure UserBudgetMapper.selectPage(...).
        final UserBudgetPageReqVO reqVO = new UserBudgetPageReqVO();
        reqVO.setCategory("category");
        reqVO.setAmount(new BigDecimal("0.00"));
        reqVO.setSpentAmount(new BigDecimal("0.00"));
        reqVO.setBudgetDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});
        when(mockUserBudgetMapper.selectPage(reqVO)).thenReturn(PageResult.empty());

        // Run the test
        final PageResult<UserBudgetDO> result = userBudgetServiceImplUnderTest.getUserBudgetPage(pageReqVO);

        // Verify the results
        assertThat(result).isEqualTo(PageResult.empty());
    }

    @Test
    void testGetUserBudgetList2() {
        // Setup
        final UserBudgetExportReqVO exportReqVO = new UserBudgetExportReqVO();
        exportReqVO.setCategory("category");
        exportReqVO.setAmount(new BigDecimal("0.00"));
        exportReqVO.setSpentAmount(new BigDecimal("0.00"));
        exportReqVO.setBudgetDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});

        final List<UserBudgetDO> expectedResult = Arrays.asList(UserBudgetDO.builder()
            .id(0)
            .build());

        // Configure UserBudgetMapper.selectList(...).
        final List<UserBudgetDO> userBudgetDOS = Arrays.asList(UserBudgetDO.builder()
            .id(0)
            .build());
        final UserBudgetExportReqVO reqVO = new UserBudgetExportReqVO();
        reqVO.setCategory("category");
        reqVO.setAmount(new BigDecimal("0.00"));
        reqVO.setSpentAmount(new BigDecimal("0.00"));
        reqVO.setBudgetDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});
        when(mockUserBudgetMapper.selectList(reqVO)).thenReturn(userBudgetDOS);

        // Run the test
        final List<UserBudgetDO> result = userBudgetServiceImplUnderTest.getUserBudgetList(exportReqVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetUserBudgetList2_UserBudgetMapperReturnsNoItems() {
        // Setup
        final UserBudgetExportReqVO exportReqVO = new UserBudgetExportReqVO();
        exportReqVO.setCategory("category");
        exportReqVO.setAmount(new BigDecimal("0.00"));
        exportReqVO.setSpentAmount(new BigDecimal("0.00"));
        exportReqVO.setBudgetDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});

        // Configure UserBudgetMapper.selectList(...).
        final UserBudgetExportReqVO reqVO = new UserBudgetExportReqVO();
        reqVO.setCategory("category");
        reqVO.setAmount(new BigDecimal("0.00"));
        reqVO.setSpentAmount(new BigDecimal("0.00"));
        reqVO.setBudgetDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});
        when(mockUserBudgetMapper.selectList(reqVO)).thenReturn(Collections.emptyList());

        // Run the test
        final List<UserBudgetDO> result = userBudgetServiceImplUnderTest.getUserBudgetList(exportReqVO);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
