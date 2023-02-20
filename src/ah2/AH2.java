package AH2;

//Desarrollador de Aplicaciones para Android y JAVA
//Ingeniero: Javier Alexander Girón Donis
//Fecha: 17/02/23
//Nombre: Gerson David Lemus Laguna
//Carnet: 2022-099897


import java.util.Random;
import java.util.Scanner;

public class AH2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);   
        
        // Menú principal
        String menu = "======MENU PRINCIPAL=======";
        String op1 = "1. Iniciar Juego";
        String op2 = "2. Retomar Juego";            
        String op4 = "4. Salir";
        System.out.println(menu);
        System.out.println(op1);
        System.out.println(op2);
        System.out.println(op4);
        
        int opcion = input.nextInt();
        input.nextLine();
        switch (opcion) {
            case 1:
                // Iniciar juego
                int[][] tablero = new int[8][8];
                char[][] jugador = new char[8][8];
                char[][] trampas = new char[8][8];
                
                // Trampas
                Random random = new Random();
                for (int i = 0; i < trampas.length; i++) {
                    int numTrampas = random.nextInt(3) + 2; 
                    for (int j = 0; j < numTrampas; j++) {
                        int randomCol = random.nextInt(8); 
                        while (trampas[i][randomCol] == '#') {
                            randomCol = random.nextInt(8);
                        }
                        trampas[i][randomCol] = '#'; 
                    }
                }

                // Inicializar valores
                int posicion = 0;
                int valor = 64;        
                boolean cambio = true;
                for (int i = 0; i < tablero.length; i++) {
                    if (cambio) {
                        for (int j = tablero[i].length - 1; j >= 0; j--) {
                            tablero[i][j] = valor;
                            valor--;
                        }
                        cambio = false;
                    } else {
                        for (int j = 0; j < tablero[i].length; j++) {
                            tablero[i][j] = valor;
                            valor--;   
                        }
                        cambio = true;
                    }
                }

                // Posición inicial del jugador
                trampas[7][7] = ' ';    
                jugador[7][7] = '@';
                
                // Juego
                while (posicion <= 64) {
                    System.out.println("-------------------------------------------------");
                    for (int i = 0; i < tablero.length; i++) {
                        for (int j = 0; j < tablero[i].length; j++) {
                            if (tablero[i][j] < 9) {
                                System.out.print("|    "+ tablero [i][j]);
                            } else {
                                System.out.print("|   "+ tablero [i][j]);
                            }
                        }
                        System.out.println("|        ");
                        for (int j = 0; j < trampas.length; j++) {
                            System.out.print("|   "+jugador[i][j] + trampas[i][j]);
                            System.out.print(" ");  
                            System.out.print("");
                        }
                        System.out.println("|");
                        System.out.println("-------------------------------------------------");
                    }
                    for (int i = 0; i < trampas.length; i++) {
                        for (int j = 0; j < jugador[i].length; j++) {
                            jugador [i][j] = ' ';
                        }
                    }
                    System.out.println(" Deseas tirar el dado con R o salir al menu con P");
                    String seleccion = input.nextLine();
                
        switch (seleccion){
        case "r":        
        int dado = (int) (Math.random() * (6 - 2) + 2);
            System.out.println(" Tu dado salio con " + dado );
            posicion += dado;
            
            if (posicion >= 59) {
                jugador[0][7 - (posicion - 57)] = '@';
            } else if (posicion >= 49) {
                jugador[1][7 - (posicion - 49)] = '@';
            } else if (posicion >= 41) {
                jugador[2][7 - (posicion - 41)] = '@';
            } else if (posicion >= 33) {
                jugador[3][7 - (posicion - 33)] = '@';
            } else if (posicion == 25) {
                jugador[4][7] = '@';
            } else if (posicion == 24) {
                jugador[4][7] = '@';
            } else if (posicion > 25) {
                jugador[4][7 - (posicion - 25)] = '@';
            } else if (posicion >= 16) {
                jugador[5][7 - (posicion - 16)] = '@';
            } else if (posicion >= 8) {
                jugador[6][posicion - 8] = '@';
            } else {
                jugador[7][posicion] = '@';
            }
            
            // Checkear trampas
            int row = 7 - (posicion / 8);
            int col = posicion % 8;
            if (trampas[row][col] == '#') {
                System.out.println("Te encontraste una trampa!");
                
                jugador[7][7] = '@';
            } else {
                jugador[row][col] = '@';
            }
            
            break;
        case "p":
            // Regresar al menu
            System.out.println("Regresando al menú principal...");
            return;
        default:
            System.out.println("Opción no válida.");
            break;
    }
}

System.out.println("Felicidades, ¡ganaste el juego!");

input.close();    

        }
    }
}