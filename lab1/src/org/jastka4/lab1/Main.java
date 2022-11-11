package org.jastka4.lab1;

import org.jastka4.utils.Menu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final List<Field> FIELDS = Arrays.asList(
            new Field(3),
            new Field(7),
            new Field(11)
    );
    private static final List<Encryption> ENCRYPTION_LAB_6 = Arrays.asList(
            new Encryption(new Field(3), new int[]{2, 1, 1}, new int[]{1, 0}),
            new Encryption(new Field(2), new int[]{1, 1, 0, 0, 1}, new int[]{1, 0})
    );
    private static final Encryption ENCRYPTION_LAB_7 = new Encryption(
            new Field(2), new int[]{1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1}, new int[]{1, 0}
    );

    public static void main(String[] args) throws IOException, InterruptedException {
        Menu menu = buildMenu();
        menu.handleMenu();
    }

    private static Menu buildMenu() {
        final List<Menu.Item> items = new ArrayList<>();
        final Menu menu = new Menu(items);

        final List<Menu.Item> items1 = new ArrayList<>();
        items1.add(new Menu.Item(1, "1: GF(3)", () -> zad1(FIELDS.get(0)), true));
        items1.add(new Menu.Item(2, "2: GF(7)", () -> zad1(FIELDS.get(1)), true));
        items1.add(new Menu.Item(3, "3: GF(11)", () -> zad1(FIELDS.get(2)), true));
        items1.add(new Menu.Item(4, "4: Powrot", new Menu.Item.DoNothingCommand()));

        final List<Menu.Item> items2 = new ArrayList<>();
        items2.add(new Menu.Item(1, "1: GF(3)", () -> zad2(FIELDS.get(0)), true));
        items2.add(new Menu.Item(2, "2: GF(7)", () -> zad2(FIELDS.get(1)), true));
        items2.add(new Menu.Item(3, "3: GF(11)", () -> zad2(FIELDS.get(2)), true));
        items2.add(new Menu.Item(4, "4: Powrot", new Menu.Item.DoNothingCommand()));

        final List<Menu.Item> items3 = new ArrayList<>();
        items3.add(new Menu.Item(1, "1: GF(3)", () -> zad3(FIELDS.get(0)), true));
        items3.add(new Menu.Item(2, "2: GF(7)", () -> zad3(FIELDS.get(1)), true));
        items3.add(new Menu.Item(3, "3: GF(11)", () -> zad3(FIELDS.get(2)), true));
        items3.add(new Menu.Item(4, "4: Powrot", new Menu.Item.DoNothingCommand()));

        final List<Menu.Item> items4 = new ArrayList<>();
        items4.add(new Menu.Item(1, "1: GF(3)", () -> zad4(FIELDS.get(0)), true));
        items4.add(new Menu.Item(2, "2: GF(7)", () -> zad4(FIELDS.get(1)), true));
        items4.add(new Menu.Item(3, "3: GF(11)", () -> zad4(FIELDS.get(2)), true));
        items4.add(new Menu.Item(4, "4: Powrot", new Menu.Item.DoNothingCommand()));

        final List<Menu.Item> items5 = new ArrayList<>();
        items5.add(new Menu.Item(1, "1: GF(3)", () -> zad5(FIELDS.get(0)), true));
        items5.add(new Menu.Item(2, "2: GF(7)", () -> zad5(FIELDS.get(1)), true));
        items5.add(new Menu.Item(3, "3: GF(11)", () -> zad5(FIELDS.get(2)), true));
        items5.add(new Menu.Item(4, "4: Powrot", new Menu.Item.DoNothingCommand()));

        final List<Menu.Item> items6 = new ArrayList<>();
        items6.add(new Menu.Item(1, "1: GF(2), x^4+x+1, {1, 0}", () -> zad6(ENCRYPTION_LAB_6.get(0)), true));
        items6.add(new Menu.Item(2, "2: GF(3), x^2+x+2, {1, 0}", () -> zad6(ENCRYPTION_LAB_6.get(1)), true));
        items6.add(new Menu.Item(3, "3: Powrot", new Menu.Item.DoNothingCommand()));

        final List<Menu.Item> items7 = new ArrayList<>();
        items7.add(new Menu.Item(1, "1: GF(2), x^10+x^3+1, {1, 0}", () -> zad7(ENCRYPTION_LAB_7), true));
        items7.add(new Menu.Item(2, "2: Powrot", new Menu.Item.DoNothingCommand()));

        final Menu menuZad1 = new Menu(items1);
        final Menu menuZad2 = new Menu(items2);
        final Menu menuZad3 = new Menu(items3);
        final Menu menuZad4 = new Menu(items4);
        final Menu menuZad5 = new Menu(items5);
        final Menu menuZad6 = new Menu(items6);
        final Menu menuZad7 = new Menu(items7);

        items.add(new Menu.Item(1, "1: Zadanie 1", menuZad1::handleMenu));
        items.add(new Menu.Item(2, "2: Zadanie 2", menuZad2::handleMenu));
        items.add(new Menu.Item(3, "3: Zadanie 3", menuZad3::handleMenu));
        items.add(new Menu.Item(4, "4: Zadanie 4", menuZad4::handleMenu));
        items.add(new Menu.Item(5, "5: Zadanie 5", menuZad5::handleMenu));
        items.add(new Menu.Item(6, "6: Zadanie 6", menuZad6::handleMenu));
        items.add(new Menu.Item(7, "7: Zadanie 7", menuZad7::handleMenu));
        items.add(new Menu.Item(8, "8: Wyjscie", () -> System.exit(0)));

        return menu;
    }

    private static void zad1(Field field) {
        System.out.println("G(" + field.getP() + ")");

        System.out.println("Tabliczka dodawania:");
        for (List<Integer> row : field.getAdditionTable()) {
            System.out.println(row);
        }

        System.out.println();

        System.out.println("Tabliczka mnozenia:");
        for (List<Integer> row : field.getMultiplicationTable()) {
            System.out.println(row);
        }
    }

    private static void zad2(Field field) {
        List<Integer> additionReverse = new ArrayList<>();

        for (List<Integer> integers : field.getAdditionTable()) {
            for (int j = 0; j < integers.size(); j++) {
                if (0 == integers.get(j)) {
                    additionReverse.add(j);
                }
            }
        }

        System.out.println(additionReverse);
    }

    private static void zad3(Field field) {
        List<Integer> multiplicationReverse = new ArrayList<>();

        for (List<Integer> integers : field.getMultiplicationTable()) {
            for (int j = 0; j < integers.size(); j++) {
                if (1 == integers.get(j)) {
                    multiplicationReverse.add(j);
                }
            }
        }

        System.out.println(multiplicationReverse);
    }

    private static void zad4(Field field) {
        List<Integer> multiplicativeOrder = Field.getMultiplicativeOrder(field.getP());

        System.out.println(multiplicativeOrder);
    }

    private static void zad5(Field field) {
        List<Integer> primitiveElements = Field.getPrimitiveElements(field.getP());

        System.out.println(primitiveElements);
    }

    private static void zad6(Encryption encryption) {
        int[] periodicSequence = encryption.getPeriodicSequence();

        int cycleCount = encryption.getCycleCount(periodicSequence);
        boolean primitive = cycleCount == encryption.getT();

        System.out.println(periodicSequence);
        System.out.println(Arrays.toString(encryption.getStartingSequence()));
        System.out.println("Okres:" + cycleCount);
        System.out.println("Czy prymitywny: " + primitive);
    }

    private static void zad7(Encryption encryption) {
        int[] periodicSequence = encryption.getPeriodicSequence();

        String encrypted = new String(encryption.xorWithKey("testingAbc".getBytes(StandardCharsets.UTF_8),
                Arrays.toString(periodicSequence).getBytes(StandardCharsets.UTF_8)));
        String decrypted = new String(encryption.xorWithKey(encrypted.getBytes(StandardCharsets.UTF_8),
                Arrays.toString(periodicSequence).getBytes(StandardCharsets.UTF_8)));

        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}
