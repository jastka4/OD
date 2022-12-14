package org.jastka4.utils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final List<Item> items;

    public Menu(final List<Item> items) {
        this.items = items;
    }


    public void handleMenu() throws IOException, InterruptedException {
        int input = 1;
        while (input != items.size()) {
            clearScreen();
            printMenu(items);
            try {
                input = SCANNER.nextInt();
                if (input > items.size() + 1 || input == 0) {
                    throw new IllegalStateException("Unexpected value: " + items.size());
                }
                for (Item item : items) {
                    if (input == item.number && !(item.command instanceof Item.DoNothingCommand)) {
                        clearScreen();
                        execute(item.command, item.waitForConfirmation);
                    }
                }
            } catch (IllegalStateException ex) {
                System.out.println("Please enter an integer value between 1 and " + items.size());
                SCANNER.next();
            }
        }
    }

    public void printMenu(List<Item> items) {
        for (Item item : items) {
            System.out.println(item.text);
        }
        System.out.print("Choose your option : ");
    }

    private static void execute(Item.Command command, boolean waitForConfirmation) throws IOException, InterruptedException {
        if (waitForConfirmation) {
            command.execute();
            System.out.print("\nChoose 0 to get back: ");
            SCANNER.next();
        } else {
            command.execute();
        }
    }

    private void clearScreen() throws IOException, InterruptedException {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static class Item {
        private final int number;
        private final String text;
        private final Command command;
        private boolean waitForConfirmation = false;

        public Item(final int number, final String text, final Command command) {
            this.number = number;
            this.text = text;
            this.command = command;
        }

        public Item(int number, String text, Command command, boolean waitForConfirmation) {
            this.number = number;
            this.text = text;
            this.command = command;
            this.waitForConfirmation = waitForConfirmation;
        }

        public interface Command {
            void execute() throws IOException, InterruptedException;
        }

        public static class DoNothingCommand implements Command {
            @Override
            public void execute() {
            }
        }
    }
}
