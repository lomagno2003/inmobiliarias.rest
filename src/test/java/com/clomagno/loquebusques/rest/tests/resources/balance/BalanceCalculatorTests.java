package com.clomagno.loquebusques.rest.tests.resources.balance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		TestCase_BalanceCalculator_WithLiabilities_InteresesChange.class,
		TestCase_BalanceCalculator_WithLiabilities_OneMonth.class,
		TestCase_BalanceCalculator_WithLiabilities_TwoMonths.class,
		TestCase_BalanceCalculator_WithoutLiabilities_OneMonth.class,
		TestCase_BalanceCalculator_WithoutLiabilitiesCrossingYear.class,
		TestCase_BalanceCalculator_WithoutLiabilitiesMultipleMonths.class })
public class BalanceCalculatorTests {

}
