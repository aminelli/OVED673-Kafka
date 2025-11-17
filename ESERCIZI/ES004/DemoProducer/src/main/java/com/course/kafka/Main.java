package com.course.kafka;

import com.course.kafka.producer.ProducerAckOne;
import com.course.kafka.producer.ProducerFireAndForget;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void Menu01(Scanner scanner){
        var producer = new ProducerFireAndForget();
        producer.sendMessages("CORSO_FAF", 1000000, 1, (short)1);
        System.out.println("\n\nPremere un tasto per continuare...");
        scanner.nextLine();
    }

    public static void Menu02(Scanner scanner){
        var producer = new ProducerAckOne();
        producer.sendMessagesSync("CORSO_FAF", 5000, 3, (short)3);
        System.out.println("\n\nPremere un tasto per continuare...");
        scanner.nextLine();
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            // Print menu
            clearScreen();

            System.out.println("-".repeat(30));
            System.out.println("MENU PRINCIPALE");
            System.out.println("-".repeat(30));
            System.out.println("1. Producer - Caso 1 - Fire And Forget (ack 0)");
            System.out.println("2. Producer - Caso 2 - Ack 1 Sync)");
            System.out.println("-".repeat(30));
            System.out.println("0. Esci");
            System.out.println("-".repeat(30));
            System.out.println("Scegli un'opzione: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //System.out.println("\n");
                    Menu01(scanner);
                    break;
                case 2:
                    //System.out.println("\n");
                    Menu02(scanner);
                    break;
                case 0:
                    System.out.println("Ciao");
                    break;
                default:
                    System.out.println("Opzione non valida !!!");
            }

        } while (choice != 0);

        scanner.close();
    }
}