package br.com.itau.pix.validator;

import br.com.itau.pix.enumerators.KeyTypesEnum;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PixKeyValidator {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+[1-9][0-9]{0,2}\\d{2}\\d{9}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{11}$");
    private static final Pattern CNPJ_PATTERN = Pattern.compile("^\\d{14}$");
    private static final Pattern RANDOM_PATTERN = Pattern.compile("^[a-zA-Z0-9]{36}$");


    public boolean isValidKey(String keyType ,String keyValue) {
        //TODO alterar para retornar somente se a chave eh valida ou nao, nao preciso determinar a chave

        return true;
    }

    private void validateKey(KeyTypesEnum keyType, String keyValue) {
        boolean isValid = true;

        switch (keyType) {
            case CPF:
                isValid = isCpfValid(keyValue);
                break;
            case CNPJ:
                isValid = isCnpjValid(keyValue);
                break;
            default:
                break;
        }
    }

    private KeyTypesEnum keyTypeDetermination(String keyValue) {
        if (PHONE_PATTERN.matcher(keyValue).matches()) {
            return KeyTypesEnum.PHONE;
        } else if (EMAIL_PATTERN.matcher(keyValue).matches()) {
            return KeyTypesEnum.EMAIL;
        } else if (CPF_PATTERN.matcher(keyValue).matches()) {
            return KeyTypesEnum.CPF;
        } else if (CNPJ_PATTERN.matcher(keyValue).matches()) {
            return KeyTypesEnum.CNPJ;
        } else if (RANDOM_PATTERN.matcher(keyValue).matches()) {
            return KeyTypesEnum.RANDOM;
        } else {
            throw new IllegalArgumentException("Valor de chave nao corresponde a nenhum tipo conhecido.");
        }
    }


    private boolean isCpfValid(String cpf) {
        if (cpf == null || cpf.length() != 11) return false;

        int[] weights = {10, 9, 8, 7, 6, 5, 4, 3, 2};

        int firstDigit = calculateDigit(cpf.substring(0, 9), weights);
        int secondDigit = calculateDigit(cpf.substring(0, 9) + firstDigit, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2});

        return cpf.equals(cpf.substring(0, 9) + firstDigit + secondDigit);
    }

    private static boolean isCnpjValid(String cnpj) {
        if (cnpj == null || cnpj.length() != 14) return false;

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int firstDigit = calculateDigit(cnpj.substring(0, 12), weights1);
        int secondDigit = calculateDigit(cnpj.substring(0, 12) + firstDigit, weights2);

        return cnpj.equals(cnpj.substring(0, 12) + firstDigit + secondDigit);
    }

    private static int calculateDigit(String str, int[] weights) {
        int sum = 0;
        for (int i = str.length() - 1, digit; i >= 0; i--) {
            digit = Integer.parseInt(str.substring(i, i + 1));
            sum += digit * weights[weights.length - str.length() + i];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }
}
