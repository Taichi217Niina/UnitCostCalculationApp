package com.unitcostcalculation.costcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unitcostcalculation.costcalculator.common.CalculateConstant;
import com.unitcostcalculation.costcalculator.dto.UnitCostDto;
import com.unitcostcalculation.costcalculator.form.UnitCostForm;

/**
 * 単価計算用Controllerクラス.
 * 
 * @author niinataichi
 *
 */
@Controller
@RequestMapping("/caluculater")
public class CalculateController {
	
	@GetMapping
	public String unitCostInputList(Model model) {
		model.addAttribute("unitCost", new UnitCostForm());	
		return "calculate";
	}
	
	@GetMapping("/result")
	public String unitCostResult() {
		return "calculated";
	}

	@PostMapping("/result")
	public String unitCostInputInfo(Model model, UnitCostForm form) {
		// 入力情報を元に単価計算算出
		int totalCost;
		double percentage = form.getPercentage();
		totalCost = (int) (form.getUnitCost() * (percentage / CalculateConstant.PERCENT));
		int anualIncome;
		anualIncome = totalCost * CalculateConstant.TWELVE_MONTHS;

		// 計算結果をDTOに格納
		UnitCostDto unitCost = new UnitCostDto();
		unitCost.setTotalCost(totalCost);
		unitCost.setAnualIncome(anualIncome);

		// 年収平均メッセージ
		if (anualIncome <= 3500000 && anualIncome >= 0) {
			model.addAttribute("message", "平均年収より低い年収です。");
		} else if (anualIncome <= 3900000 && anualIncome > 3500000) {
			model.addAttribute("message", "平均年収よりやや低い年収です。");
		} else if (anualIncome <= 4500000 && anualIncome > 3900000) {
			model.addAttribute("message", "平均年収よりやや高い年収です。");
		} else if (anualIncome > 4500000) {
			model.addAttribute("message", "平均年収より高い年収です。");
		}
		model.addAttribute("unitCost", unitCost);
		return "calculated";
	}
	

}
