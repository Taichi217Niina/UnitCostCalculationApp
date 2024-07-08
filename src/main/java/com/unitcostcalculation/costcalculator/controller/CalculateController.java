package com.unitcostcalculation.costcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unitcostcalculation.costcalculator.common.CalculateConstant;
import com.unitcostcalculation.costcalculator.dto.UnitCostDto;
import com.unitcostcalculation.costcalculator.form.UnitCostForm;
import com.unitcostcalculation.costcalculator.service.CalculateService;

/**
 * 単価計算用Controllerクラス.
 * 
 * @author niinataichi
 *
 */
@Controller
@RequestMapping("/caluculater")
public class CalculateController {
	
	@GetMapping("/top")
	public String index() {
		return "index";
	}

	@GetMapping
	public String unitCostInputList(Model model) {
		model.addAttribute("unitCostForm", new UnitCostForm());	
		return "calculate";
	}
	
	@GetMapping("/result")
	public String unitCostResult() {
		return "calculated";
	}

	@PostMapping("/result")
	public String unitCostInputInfo(Model model, UnitCostForm form) {
		// 入力情報を元に単価計算算出
		UnitCostDto unitCostResult = CalculateService.doCalculate(form);

		// 年収平均メッセージ
		String message = getMessageForAnnualIncome(unitCostResult.getAnualIncome());
		model.addAttribute("message", message);
		model.addAttribute("unitCost", unitCostResult);
		return "calculated";
	}
	
	// 年収に対するメッセージを取得するヘルパーメソッド
    private String getMessageForAnnualIncome(int annualIncome) {
		if (annualIncome <= 3300000 && annualIncome >= 0) {
			return "平均年収より低い年収です。";
		} else if (annualIncome <= 3800000 && annualIncome > 3300000) {
			return "平均年収よりやや低い年収です。";
		} else if (annualIncome <= 4500000 && annualIncome > 3800000) {
			return "平均年収です。";
		} else if (annualIncome <= 5200000 && annualIncome > 4500000) {
			return "平均年収よりやや高い年収です。";
		} else {
			return "平均年収より高い年収です。";
		}
    	
    }

}
