package com.course.kafka;

import com.course.kafka.consumer.ConsumerGeneric;
import com.course.kafka.producer.*;
import net.datafaker.providers.base.Bool;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Optional;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void Menu01(Scanner scanner, Boolean isProducer){
        String topicName = "CORSO_FIRE_AND_FORGET";
        if (isProducer) {
            var producer = new ProducerFireAndForget();
            producer.sendMessages(topicName, 10000, 1, (short)1);
        } else {
           var consumer = new ConsumerGeneric();
           consumer.loadRecords(topicName, StringDeserializer.class, StringDeserializer.class);
        }


        System.out.println("\n\nPremere un tasto per continuare...");
        scanner.nextLine();
    }

    public static void Menu02(Scanner scanner, Boolean isProducer){
        String topicName = "CORSO_ACK_01_SYNC";
        if (isProducer) {
            var producer = new ProducerAckOne();
            producer.sendMessagesSync(topicName, 1000, 3, (short) 3);
        } else  {
            var consumer = new ConsumerGeneric();
            consumer.loadRecords(topicName, StringDeserializer.class, StringDeserializer.class);
        }
        System.out.println("\n\nPremere un tasto per continuare...");
        scanner.nextLine();
    }

    public static void Menu03(Scanner scanner) throws InterruptedException {
        var producer = new ProducerAckOne();
        producer.sendMessagesAsync("CORSO_ACK_01_ASYNC", 1000, 3, (short)3);
        System.out.println("\n\nPremere un tasto per continuare...");
        scanner.nextLine();
    }


    public static void Menu04(Scanner scanner ){
        var producer = new ProducerAckAll();
        producer.sendMessagesSync("CORSO_ACK_ALL_SYNC", 1000, 3, (short)3);
        System.out.println("\n\nPremere un tasto per continuare...");


        scanner.nextLine();
    }

    public static void Menu05(Scanner scanner) throws InterruptedException {
        var producer = new ProducerAckAll();
        producer.sendMessagesAsync("CORSO_ACK_ALL_ASYNC", 1000, 3, (short)3);
        System.out.println("\n\nPremere un tasto per continuare...");
        scanner.nextLine();
    }

    public static void Menu06(Scanner scanner) throws InterruptedException {
        var producer = new ProducerTestPartitioner();
        producer.sendMessages("CORSO_CST_PART_02", 10000, 3, (short)3);
        System.out.println("\n\nPremere un tasto per continuare...");
        scanner.nextLine();
    }

    public static void Menu07(Scanner scanner) throws InterruptedException {
        var producer = new ProducerCustomer();
        producer.sendCustomersBinary("CUSTOMER_BIN", 100, 3, (short)3);
        System.out.println("\n\nPremere un tasto per continuare...");
        scanner.nextLine();
    }

    public static void Menu08(Scanner scanner) throws InterruptedException {
        var producer = new ProducerCustomer();
        producer.sendCustomersJson("CUSTOMER_JSON", 100, 3, (short)3);
        System.out.println("\n\nPremere un tasto per continuare...");
        scanner.nextLine();
    }


    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            // Print menu
            clearScreen();

            System.out.println("-".repeat(30));
            System.out.println("MENU PRINCIPALE");
            System.out.println("-".repeat(30));
            System.out.println("1. Producer - Fire And Forget (ack 0)");
            System.out.println("2. Producer - Ack 1 Sync");
            System.out.println("3. Producer - Ack 1 Async");
            System.out.println("4. Producer - Ack All Sync");
            System.out.println("5. Producer - Ack All Async");
            System.out.println("6. Producer - Custom Partitioner");
            System.out.println("7. Producer - Customer Binary");
            System.out.println("8. Producer - Customer Json");
            System.out.println("-".repeat(30));
            System.out.println("21. Consumer - Fire And Forget");
            System.out.println("22. Consumer - Ack 1 Sync");
            System.out.println("23. Consumer - Ack 1 Async");
            System.out.println("24. Consumer - Ack All Sync");
            System.out.println("25. Consumer - Ack All Async");
            System.out.println("26. Consumer - Custom Partitioner");
            System.out.println("27. Consumer - Customer Binary");
            System.out.println("28. Consumer - Customer Json");

            System.out.println("-".repeat(30));
            System.out.println("0. Esci");
            System.out.println("-".repeat(30));
            System.out.println("Scegli un'opzione: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Menu01(scanner, true);
                    break;
                case 2:
                    Menu02(scanner, true);
                    break;
                case 3:
                    Menu03(scanner);
                    break;
                case 4:
                    Menu04(scanner);
                    break;
                case 5:
                    Menu05(scanner);
                    break;
                case 6:
                    Menu06(scanner);
                    break;
                case 7:
                    Menu07(scanner);
                    break;
                case 8:
                    Menu08(scanner);
                    break;


                case 21:
                    Menu01(scanner, false);
                    break;
                case 22:
                    Menu02(scanner, false);
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