/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Fabricio Pretto
 */
public class Validacao
{

    private static final int[] pesoCPF =
    {
        11, 10, 9, 8, 7, 6, 5, 4, 3, 2
    };
    private static final int[] pesoCNPJ =
    {
        6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2
    };

    private static int calcularDigito(String str, int[] peso)
    {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--)
        {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean validarCPF(String cpf)
    {
        if ((cpf == null) || (cpf.trim().length() == 0))
        {
            return true;
        }

        if (cpf.length() != 11)
        {
            return false;
        }
        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean validarCNPJ(String cnpj)
    {
        if ((cnpj == null) || (cnpj.trim().length() == 0))
        {
            return true;
        }

        if (cnpj.length() != 14)
        {
            return false;
        }

        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    public static boolean validarDataDMA(int d, int m, int a)
    {
        boolean correto = true;
        int[] dias =
        {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        if (a < 0 || m < 1 || m > 12)
        {
            correto = false;
        }
        else
        {
            // valida o dia
            if (a % 4 == 0 && (a % 100 != 0 || a % 400 == 0))
            {
                dias[1] = 29;
            }
            if (d < 1 || d > dias[m - 1])
            {
                correto = false;
            }
        }
        return (correto);
    }

    public static boolean validarDataFormatada(String dataComFormato)
    {
        if (dataComFormato.equalsIgnoreCase(""))
        {
            return true;
        }

        String[] data = dataComFormato.split("/");
        try
        {
            return (validarDataDMA(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
        }
        catch (NumberFormatException e)
        {
            return false;
        }

    }

    public static void validarTelefone(JFormattedTextField campo)
    {
        if (campo.getText().trim().length() < 14)
        {
            Formatacao.reformatarTelefone(campo);
        }
    }

    public static boolean validarEmail(String email)
    {
        if ((email == null) || (email.trim().length() == 0))
        {
            return true;
        }

        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validarNumero(String numero)
    {
        double valor;
        if (numero.length() != 0)
        {
            try
            {
                valor = Double.parseDouble(numero);
                return true;
            }
            catch (NumberFormatException ex)
            {
//                JOptionPane.showMessageDialog(null, "Esse Campo só aceita números!", "Informação", JOptionPane.INFORMATION_MESSAGE); 
//                numero.grabFocus();
            }
        }
        return false;
    }
}
