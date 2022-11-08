package org.jastka4.lab2;

import org.jastka4.utils.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final List<FieldExtension> FIELD_EXTENSIONS = Arrays.asList(
            new FieldExtension(2),
            new FieldExtension(3),
            new FieldExtension(4)
    );

    public static void main(String[] args) throws IOException, InterruptedException {
        Menu menu = buildMenu();
        menu.handleMenu();
    }

    private static Menu buildMenu() {
        final List<Menu.Item> items = new ArrayList<>();
        final Menu menu = new Menu(items);

        final List<Menu.Item> items1 = new ArrayList<>();
        items1.add(new Menu.Item(1, "1: GF(2^2)", () -> zad1(FIELD_EXTENSIONS.get(0))));
        items1.add(new Menu.Item(2, "2: GF(2^3)", () -> zad1(FIELD_EXTENSIONS.get(1))));
        items1.add(new Menu.Item(3, "3: GF(2^4)", () -> zad1(FIELD_EXTENSIONS.get(2))));
        items1.add(new Menu.Item(4, "4: Powrot", new Menu.Item.DoNothingCommand()));

        final List<Menu.Item> items2 = new ArrayList<>();
        items2.add(new Menu.Item(1, "1: GF(2^2)", () -> zad2(FIELD_EXTENSIONS.get(0))));
        items2.add(new Menu.Item(2, "2: GF(2^3)", () -> zad2(FIELD_EXTENSIONS.get(1))));
        items2.add(new Menu.Item(3, "3: GF(2^4)", () -> zad2(FIELD_EXTENSIONS.get(2))));
        items2.add(new Menu.Item(4, "4: Powrot", new Menu.Item.DoNothingCommand()));

        final List<Menu.Item> items3 = new ArrayList<>();
        items3.add(new Menu.Item(1, "1: GF(2^2)", () -> zad3(FIELD_EXTENSIONS.get(0))));
        items3.add(new Menu.Item(2, "2: GF(2^3)", () -> zad3(FIELD_EXTENSIONS.get(1))));
        items3.add(new Menu.Item(3, "3: GF(2^4)", () -> zad3(FIELD_EXTENSIONS.get(2))));
        items3.add(new Menu.Item(4, "4: Powrot", new Menu.Item.DoNothingCommand()));

        final List<Menu.Item> items4 = new ArrayList<>();
        items4.add(new Menu.Item(1, "1: GF(2^4)", () -> zad4(FIELD_EXTENSIONS.get(2))));
        items4.add(new Menu.Item(2, "2: Powrot", new Menu.Item.DoNothingCommand()));

        final Menu menuZad1 = new Menu(items1);
        final Menu menuZad2 = new Menu(items2);
        final Menu menuZad3 = new Menu(items3);
        final Menu menuZad4 = new Menu(items4);

        items.add(new Menu.Item(1, "1: Zadanie 1", menuZad1::handleMenu));
        items.add(new Menu.Item(2, "2: Zadanie 2", menuZad2::handleMenu));
        items.add(new Menu.Item(3, "3: Zadanie 3", menuZad3::handleMenu));
        items.add(new Menu.Item(4, "4: Zadanie 4", menuZad4::handleMenu));
        items.add(new Menu.Item(8, "8: Wyjscie", () -> System.exit(0)));

        return menu;
    }

    private static void zad1(FieldExtension fieldExtension) {
        System.out.println("G(2^" + fieldExtension.getM() + ")");
        System.out.println("Tabliczka mnozenia:");

        for (List<String> row : fieldExtension.multiplicationMatrix()) {
            System.out.println(row);
        }
    }

    private static void zad2(FieldExtension fieldExtension) {
        int x = 2, y = 1;

        System.out.println("G(2^" + fieldExtension.getM() + ")");

        System.out.println("Dodawanie:");
        System.out.println("x = 2, y = 1");
        System.out.println(fieldExtension.S(x, y));

        System.out.println("Mnozenie:");
        System.out.println("x = 2, y = 1");
        System.out.println(fieldExtension.P(x, y));
    }

    private static void zad3(FieldExtension fieldExtension) {
        System.out.println("G(2^" + fieldExtension.getM() + ")");

        System.out.println("Tabliczka dodawania:");
        for (List<Integer> row : fieldExtension.zechAdditionMatrix()) {
            System.out.println(row);
        }

        System.out.println("Tabliczka mnozenia:");
        for (List<Integer> row : fieldExtension.zechMultiplicationMatrix()) {
            System.out.println(row);
        }
    }

    private static void zad4(FieldExtension fieldExtension) {
        int x1 = 8, x2 = 15, x3 = 14, x4 = 12;
//        int x1 = 4, x2 = 7, x3 = 13, x4 = 10;
//        int x1 = 2, x2 = 3, x3 = 5, x4 = 9;
        System.out.println(fieldExtension.getPolynomial(x1, x2, x3, x4));
    }
}
