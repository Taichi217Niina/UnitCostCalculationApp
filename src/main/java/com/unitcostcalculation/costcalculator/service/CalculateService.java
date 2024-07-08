package com.unitcostcalculation.costcalculator.service;

import org.springframework.stereotype.Service;

import com.unitcostcalculation.costcalculator.common.CalculateConstant;
import com.unitcostcalculation.costcalculator.dto.UnitCostDto;
import com.unitcostcalculation.costcalculator.form.UnitCostForm;

/**
 * 単価計算用Serviceクラス.
 * 
 * @author niinataichi
 *
 */
@Service
public class CalculateService {
	
	/**
	 * 入力情報を元に単価計算及び年収算出
	 * 
	 * @param form 入力情報
	 * @return unitCost 計算結果を返却
	 */
	public static UnitCostDto doCalculate(UnitCostForm form) {
	
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
