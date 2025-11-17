package com.course.kafka;

import com.course.kafka.producer.ProducerFireAndForget;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void Menu01(){
        var producer = new ProducerFireAndForget();
        producer.sendMessages("CORSO_FAF", 5000);
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
            System.out.println("-".repeat(30));
            System.out.println("0. Esci");
            System.out.println("-".repeat(30));
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("Scegli un'opzione: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //System.out.println("\n");
                    Menu01();
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