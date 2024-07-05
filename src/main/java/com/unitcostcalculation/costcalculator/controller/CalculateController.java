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
		int totalCost;
		double percentage = form.getPercentage();
		totalCost = (int) (form.getUnitCost() * (percentage / CalculateConstant.PERCENT));
		int anualIncome;
		anualIncome = totalCost * CalculateConstant.TWELVE_MONTHS;

		UnitCostDto unitCost = new UnitCostDto();
		unitCost.setTotalCost(totalCost);
		unitCost.setAnualIncome(anualIncome);

		model.addAttribute("unitCost", unitCost);
		return "calculated";
	}
	

}
