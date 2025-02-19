package attestation;

import java.util.*;

public class NotebooksSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Notebooks> laptops = new ArrayList<>();
        laptops.add(new Notebooks("HPPavilion", "HP", 8, 512, "Windows 10", "Silver", 15.6));
        laptops.add(new Notebooks("DellInspiron", "Dell", 16, 256, "Ubuntu", "Black", 14.0));
        laptops.add(new Notebooks("LenovoThinkPad", "Lenovo", 12, 1000, "Windows 11", "Gray", 13.3));
        laptops.add(new Notebooks("AcerAspire", "Acer", 8, 1000, "Dos", "Gray", 17.0));
        laptops.add(new Notebooks("AsusROG", "Asus", 16, 2000, "Windows 11", "Black", 15.6));
        laptops.add(new Notebooks("MSIGaming", "MSI", 8, 1000, "Dos", "Red", 17.0));
        laptops.add(new Notebooks("GigabyteAero", "Gigabyte", 12, 512, "Mint", "Brown", 14.0));
        laptops.add(new Notebooks("HPPavilionDv6", "HP", 16, 4000, "Windows 10", "Silver", 16.0));

        List<Notebooks> filteredLaptops = laptops;
        int continueFiltering;
        Map<Integer, Object> filterCriteria;

        do {
            filterCriteria = getFilterCriteria(scanner);
            filteredLaptops = filterLaptops(filteredLaptops, filterCriteria);

            if (!filteredLaptops.isEmpty()) {
                System.out.println("Результаты фильтрации:");
                for (Notebooks laptop : filteredLaptops) {
                    System.out.println(laptop.toString()); // Вместо прямого доступа к полям, используем геттеры
                }
            } else {
                System.out.println("Нет результатов по заданным критериям.");
            }

            System.out.println("Продолжить фильтрацию?");
            System.out.println("1 - Да");
            System.out.println("2 - Нет");
            System.out.print("Ваш выбор: ");
            continueFiltering = scanner.nextInt();
            scanner.nextLine(); // очистим буфер
        } while (continueFiltering == 1);
    }

    private static Map<Integer, Object> getFilterCriteria(Scanner scanner) {
        Map<Integer, Object> filterCriteria = new HashMap<>();

        System.out.println("Выберите критерий фильтрации:");
        System.out.println("1 - Минимальный размер ОЗУ (гигабайты)");
        System.out.println("2 - Минимальный объем накопителя (гигабайты)");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет ноутбука");
        System.out.println("5 - Минимальный размер экрана (дюймы)");

        int filterKey = scanner.nextInt();
        System.out.print("Введите значение критерия: ");
        Object filterValue;
        if (filterKey == 1 || filterKey == 2 || filterKey == 5) {
            filterValue = scanner.nextInt();
        } else {
            scanner.nextLine(); // очистим буфер
            filterValue = scanner.nextLine();
        }

        filterCriteria.put(filterKey, filterValue);

        return filterCriteria;
    }

    private static List<Notebooks> filterLaptops(List<Notebooks> laptops, Map<Integer, Object> filterCriteria) {
        List<Notebooks> filteredLaptops = new ArrayList<>();

        for (Notebooks laptop : laptops) {
            if (laptop.matchesFilterCriteria(filterCriteria)) {
                filteredLaptops.add(laptop);
            }
        }

        return filteredLaptops;
    }
}