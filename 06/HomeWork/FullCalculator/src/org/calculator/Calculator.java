package org.calculator;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {

			// Calculator's text banner
			textBanner();

			// User input operation
			String operation = inputOperation(scanner);

			// calculator cycle
			while (!("exit".equals(operation))) {
				Long longA = inputFirstNumber(scanner);
				Long longB = inputSecondNumber(scanner, operation);
				calculateOperation(longA, longB, operation);
				operation = inputOperation(scanner);
			}

		}

		System.out.println("Всего доброго!");
	}

	private static void calculateOperation(Long longA, Long longB, String operation) {
		// TODO Auto-generated method stub
		String FORMAT = "#0.0000000000";

		switch (operation) {
		case "+":
			System.out.println("сумма: " + longA + " + " + longB + " = " + operationAdd(longA, longB));
			break;

		case "-":
			System.out.println("разность: " + longA + " - " + longB + " = " + operationMinus(longA, longB));
			break;

		case "*":
			System.out.println("умножение: " + longA + " * " + longB + " = " + operationMultiply(longA, longB));
			break;

		case "/":
			System.out.println("деление: " + longA + " / " + longB + " = " + operationDivideDouble(longA, longB));
			break;

		case "div":
			System.out.println("целочисленное деление: " + longA + " div " + longB + " = " + operationDivideLong(longA, longB));
			break;

		case "mod":
			System.out.println("деление по модулю: " + longA + " mod " + longB + " = " + operationMod(longA, longB));
			break;

		case "^":
			// возведение в степень
			Long degreeLongA = 1L;
			Double degreeDoubleA = null;

			if (longB > 0) {
				for (int i = 0; i < longB; i++) {
					degreeLongA *= longA;
				}
				System.out.println("степень - " + longA + " ^ " + longB + " = " + degreeLongA);
			}

			else if (longB < 0) {
				for (int i = 0; i < Math.abs(longB); i++) {
					degreeLongA *= longA;
				}
				degreeDoubleA = (double) (1 / (double) degreeLongA);
				String formatDegreeDoubleA = new DecimalFormat(FORMAT).format(degreeDoubleA);
				System.out.println("степень - " + longA + " ^ " + longB + " = " + formatDegreeDoubleA);

			} else {
				System.out.println("степень - " + longA + " ^ " + longB + " = " + degreeLongA);
			}

			break;

		default:
			// System.out.println("Просто выход по умолчанию из switch" );
			break;
		}

	}

	private static double operationDivideDouble(Long longA, Long longB) {
		return (double) longA / longB;
	}

	private static long operationMultiply(Long longA, Long longB) {
		return longA * longB;
	}

	private static long operationMinus(Long longA, Long longB) {
		return longA - longB;
	}

	private static long operationAdd(Long longA, Long longB) {
		return longA + longB;
	}

	private static long operationDivideLong(Long longA, Long longB) {
		return longA / longB;
	}

	private static long operationMod(Long longA, Long longB) {
		return longA % longB;
	}

	private static Long inputFirstNumber(Scanner scanner) {
		System.out.println("Введите 1е число: ");
		while (!scanner.hasNextLong()) {
			System.out.println("Неверный ввод, введите число:");
			scanner.next();
		}
		Long longA = scanner.nextLong();
		return longA;
	}

	private static Long inputSecondNumber(Scanner scanner, String operation) {
		System.out.println("Введите 2е число: ");
		Long longB;
		do {
			while (!scanner.hasNextLong()) {
				System.out.println("Неверный ввод, введите число:");
				scanner.next();
			}
			longB = scanner.nextLong();
			if (longB == 0 && ("/".equals(operation) || "mod".equals(operation) || "div".equals(operation))) {
				System.out.println("На ноль делить нельзя! Введите другое число:");
			} else if (longB == 0 && "^".equals(operation)) {
				break;
			}

		} while (longB == 0);

		return longB;
	}

	private static String inputOperation(Scanner scanner) {
		System.out.println("Пожалуйста, введите операцию:");
		while (!(scanner.hasNext("[+-/*^]{1}|div|mod|exit"))) {
			System.out.println("Неверный выбор операции. Повторите:");
			scanner.next();
		}

		String operation = scanner.next();
		return operation;
	}

	private static void textBanner() {
		System.out.println("Калькулятор нескольких операций для двух чисел.");
		System.out.println("Для расчета введите операцию: + - / * ^ div mod");
		System.out.println("Для выхода введите: exit");
	}
}
