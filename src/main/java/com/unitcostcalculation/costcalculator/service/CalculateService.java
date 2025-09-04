package com.unitcostcalculation.costcalculator.service;

import com.unitcostcalculation.costcalculator.dto.UnitCostDto;
import com.unitcostcalculation.costcalculator.form.UnitCostForm;

/**
 * 単価計算用Serviceインターフェース.
 * 
 * @author niinataichi
 */
public interface CalculateService {
	/**
	 * 入力情報を元に単価計算及び年収算出
	 * 
	 * @param form 入力情報
	 * @return unitCost 計算結果を返却
	 */
	UnitCostDto doCalculate(UnitCostForm form);
}
