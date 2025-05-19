package org.example;

import org.example.entities.Airplane;
import org.example.entities.Flight;
import org.example.entities.Passenger;
import org.example.entities.Reserve;
import org.example.facade.AirplaneFacade;
import org.example.facade.FlightFacade;
import org.example.facade.PassengerFacade;
import org.example.facade.ReserveFacade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    private PassengerFacade passengerFacade;
    private AirplaneFacade airplaneFacade;
    private FlightFacade flightFacade;
    private ReserveFacade reserveFacade;

    private Scanner scanner;
    private DateTimeFormatter dtf;

    public App() {
        this.passengerFacade = new PassengerFacade();
        this.airplaneFacade = new AirplaneFacade();
        this.flightFacade = new FlightFacade(airplaneFacade.getRepository());
        this.reserveFacade = new ReserveFacade(passengerFacade.getRepository(), flightFacade.getRepository());

        this.scanner = new Scanner(System.in);
        this.dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public void run() {
        int option;
        do {
            showMenu();
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1 -> cadastrarPassageiro();
                case 2 -> listarPassageiros();
                case 3 -> cadastrarAviao();
                case 4 -> listarAvioes();
                case 5 -> cadastrarVoo();
                case 6 -> listarVoos();
                case 7 -> reservarPassagem();
                case 8 -> listarReservas();
                case 9 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 9);
    }

    private void showMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1 - Cadastrar passageiro");
        System.out.println("2 - Listar passageiros");
        System.out.println("3 - Cadastrar avião");
        System.out.println("4 - Listar aviões");
        System.out.println("5 - Cadastrar voo");
        System.out.println("6 - Listar voos");
        System.out.println("7 - Reservar passagem");
        System.out.println("8 - Listar reservas");
        System.out.println("9 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarPassageiro() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (passengerFacade.cadastrarPassageiro(nome, cpf, email)) {
            System.out.println("Passageiro cadastrado com sucesso.");
        } else {
            System.out.println("Erro: CPF ou email inválidos.");
        }
    }

    private void listarPassageiros() {
        System.out.println("\nLista de Passageiros:");
        passengerFacade.list().forEach(p ->
                System.out.printf("ID: %d - Nome: %s - CPF: %s - Email: %s%n", p.getId(), p.getNome(), p.getCpf(), p.getEmail()));
    }

    private void cadastrarAviao() {
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Capacidade: ");
        int capacidade = Integer.parseInt(scanner.nextLine());
        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine();

        if (airplaneFacade.registerPlane(modelo, capacidade, fabricante)) {
            System.out.println("Avião cadastrado com sucesso.");
        } else {
            System.out.println("Erro: Capacidade deve ser maior que zero.");
        }
    }

    private void listarAvioes() {
        System.out.println("\nLista de Aviões:");
        airplaneFacade.list().forEach(a ->
                System.out.printf("ID: %d - Modelo: %s - Capacidade: %d - Fabricante: %s%n", a.getId(), a.getModel(), a.getCapacity(), a.getManufacturer()));
    }

    private void cadastrarVoo() {
        System.out.print("Origem: ");
        String origem = scanner.nextLine();
        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Data e Hora (yyyy-MM-dd HH:mm): ");
        String dataHoraStr = scanner.nextLine();

        LocalDateTime dataHora;
        try {
            dataHora = LocalDateTime.parse(dataHoraStr, dtf);
        } catch (Exception e) {
            System.out.println("Formato de data/hora inválido.");
            return;
        }

        System.out.print("ID do Avião: ");
        int airplaneId = Integer.parseInt(scanner.nextLine());

        if (flightFacade.registerFlight(origem, destino, dataHora, airplaneId)) {
            System.out.println("Voo cadastrado com sucesso.");
        } else {
            System.out.println("Erro: Avião não encontrado ou dados inválidos.");
        }
    }

    private void listarVoos() {
        System.out.println("\nLista de Voos:");
        flightFacade.list().forEach(f -> {
            System.out.printf("ID: %d - Origem: %s - Destino: %s - Data/Hora: %s - Avião: %s%n",
                    f.getId(), f.getOrigin(), f.getDestination(), f.getDateTime().format(dtf), f.getAirplane().getModel());
        });
    }

    private void reservarPassagem() {
        System.out.print("ID do Passageiro: ");
        int passengerId = Integer.parseInt(scanner.nextLine());
        System.out.print("ID do Voo: ");
        int flightId = Integer.parseInt(scanner.nextLine());

        if (reserveFacade.makeReservation(passengerId, flightId)) {
            System.out.println("Reserva realizada com sucesso.");
        } else {
            System.out.println("Erro: Passageiro ou voo não encontrado, ou voo lotado.");
        }
    }

    private void listarReservas() {
        System.out.println("\nLista de Reservas:");
        reserveFacade.listAll().forEach(r -> {
            System.out.printf("Reserva ID: %d - Passageiro: %s - Voo ID: %d - Data Reserva: %s%n",
                    r.getId(), r.getPassenger().getNome(), r.getFlight().getId(), r.getDateReserve().format(dtf));
        });
    }

    public static void main(String[] args) {
        new App().run();
    }
}