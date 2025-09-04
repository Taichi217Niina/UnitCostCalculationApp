package com.unitcostcalculation.costcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unitcostcalculation.costcalculator.dto.UnitCostDto;
import com.unitcostcalculation.costcalculator.form.UnitCostForm;
import com.unitcostcalculation.costcalculator.service.CalculateService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 単価計算用Controllerクラス.
 * 
 * @author niinataichi
 *
 */
@Controller
@RequestMapping("/calculator")

public class CalculateController {

	// 年収の閾値を定数として定義
	private static final int ANNUAL_INCOME_LOW = 3_300_000;
	private static final int ANNUAL_INCOME_SOMEWHAT_LOW = 3_800_000;
	private static final int ANNUAL_INCOME_AVERAGE = 4_500_000;
	private static final int ANNUAL_INCOME_SOMEWHAT_HIGH = 5_200_000;

	@Autowired
	private CalculateService calculateService;

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
		// 単価計算処理を呼び出す
		UnitCostDto unitCostResult = calculateService.doCalculate(form);

		// 年収平均メッセージの設定
		String message = getMessageForAnnualIncome(unitCostResult.getAnualIncome());
		model.addAttribute("message", message);
		model.addAttribute("unitCost", unitCostResult);
		return "calculated";
	}

	// 年収に対するメッセージを取得するヘルパーメソッド
	private String getMessageForAnnualIncome(int annualIncome) {
		if (annualIncome <= ANNUAL_INCOME_LOW && annualIncome >= 0) {
			return "平均年収より低い年収です。";
		} else if (annualIncome <= ANNUAL_INCOME_SOMEWHAT_LOW && annualIncome > ANNUAL_INCOME_LOW) {
			return "平均年収よりやや低い年収です。";
		} else if (annualIncome <= ANNUAL_INCOME_AVERAGE && annualIncome > ANNUAL_INCOME_SOMEWHAT_LOW) {
			return "平均年収です。";
		} else if (annualIncome <= ANNUAL_INCOME_SOMEWHAT_HIGH && annualIncome > ANNUAL_INCOME_AVERAGE) {
			return "平均年収よりやや高い年収です。";
		} else {
			return "平均年収より高い年収です。";
		}
	}

}
