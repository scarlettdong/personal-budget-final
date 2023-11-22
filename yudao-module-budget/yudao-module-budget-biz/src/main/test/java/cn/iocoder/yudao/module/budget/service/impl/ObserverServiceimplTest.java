package cn.iocoder.yudao.module.budget.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;
import cn.iocoder.yudao.module.budget.dal.mysql.ObserverMapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ObserverServiceimplTest {

    @Mock
    private ObserverMapper mockObserverMapper;

    @InjectMocks
    private ObserverServiceimpl observerServiceimplUnderTest;

    @Test
    void testGetCategoryAndAmount() {
        // Setup
        final List<UserBudgetDO> expectedResult = Arrays.asList(UserBudgetDO.builder().build());

        // Configure ObserverMapper.getCategoryAndAmount(...).
        final List<UserBudgetDO> userBudgetDOS = Arrays.asList(UserBudgetDO.builder().build());
        when(mockObserverMapper.getCategoryAndAmount()).thenReturn(userBudgetDOS);

        // Run the test
        final List<UserBudgetDO> result = observerServiceimplUnderTest.getCategoryAndAmount();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCategoryAndAmount_ObserverMapperReturnsNoItems() {
        // Setup
        when(mockObserverMapper.getCategoryAndAmount()).thenReturn(Collections.emptyList());

        // Run the test
        final List<UserBudgetDO> result = observerServiceimplUnderTest.getCategoryAndAmount();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetCateryDate() {
        // Setup
        final List<UserBudgetDO> expectedResult = Arrays.asList(UserBudgetDO.builder().build());

        // Configure ObserverMapper.getDateCategory(...).
        final List<UserBudgetDO> userBudgetDOS = Arrays.asList(UserBudgetDO.builder().build());
        when(mockObserverMapper.getDateCategory()).thenReturn(userBudgetDOS);

        // Run the test
        final List<UserBudgetDO> result = observerServiceimplUnderTest.getCateryDate();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCateryDate_ObserverMapperReturnsNoItems() {
        // Setup
        when(mockObserverMapper.getDateCategory()).thenReturn(Collections.emptyList());

        // Run the test
        final List<UserBudgetDO> result = observerServiceimplUnderTest.getCateryDate();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
