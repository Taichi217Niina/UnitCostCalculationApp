package com.unitcostcalculation.costcalculator.service;

import org.springframework.stereotype.Service;

import com.unitcostcalculation.costcalculator.common.CalculateConstant;
import com.unitcostcalculation.costcalculator.dto.UnitCostDto;
import com.unitcostcalculation.costcalculator.form.UnitCostForm;

@Service
public class CalculateServiceImpl implements CalculateService {

  @Override
  public UnitCostDto doCalculate(UnitCostForm form) {
    int totalCost;
    int annualIncome;

    // 単価の合計値と年収を算出
    double percentage = form.getPercentage();
    totalCost = (int) (form.getUnitCost() * (percentage / CalculateConstant.PERCENT));
    annualIncome = totalCost * CalculateConstant.TWELVE_MONTHS;

    // 計算結果をDTOに格納
    UnitCostDto unitCost = new UnitCostDto();
    unitCost.setTotalCost(totalCost);
    unitCost.setAnualIncome(annualIncome);
    return unitCost;
  }
}
