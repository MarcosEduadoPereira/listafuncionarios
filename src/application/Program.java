package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();

        // Pergunta quantos funcionários serão registrados
        System.out.print("How many employees will be registred? ");
        int n = sc.nextInt();

        // Registro dos funcionários
        for (int i = 0; i < n; i++) {
            System.out.println();
            System.out.println("Employee #" + (i + 1) + ":");
            System.out.print("Id: ");
            Integer id = sc.nextInt();
            // Verifica se o ID já está em uso
            while (hasId(list, id)) {
                System.out.println("Id already taken! Try again: ");
                id = sc.nextInt();
            }
            System.out.print("Name: ");
            sc.nextLine(); // Limpa o buffer
            String name = sc.nextLine();
            System.out.print("Salary: ");
            Double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, salary);
            list.add(emp);
        }

        // Solicita o ID do funcionário que terá o aumento de salário
        System.out.println();
        System.out.print("Enter the employee id that will have salary increase: ");
        int idSala = sc.nextInt();
        Integer pos = position(list, idSala);
        if (pos == null) {
            System.out.println("This id does not exist");
        } else {
            System.out.print("Enter the porcentage: ");
            double porcent = sc.nextDouble();
            list.get(pos).increaseSalary(porcent);
        }

        // Exibe a lista de funcionários
        System.out.println();
        System.out.println("List of employees: ");
        for (Employee emp : list) {
            System.out.println(emp);
        }

        sc.close();
    }

    // Método que retorna a posição do funcionário na lista dado o ID
    public static Integer position(List<Employee> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return null;
    }

    // Método que verifica se um ID já está em uso
    public static boolean hasId(List<Employee> list, int id) {
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }
}