package rmi_client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.Scanner;
import rmi_interface.interface1;

public class Rmi_client {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        interface1 obj1 = null;
        try {
            Registry regi = LocateRegistry.getRegistry("localhost", 1099);
            obj1 = (interface1) regi.lookup("//localhost:1099/checking_Account");

        } catch (RemoteException ex) {
            System.out.println(ex);
        } catch (NotBoundException ex) {
            System.out.println(ex);
        }

        int choice;
        boolean getout = false;
        String b_Name[] = new String[10];
        String name, pass, e_m, coun, Respons = "";
        int pric;
        int Price[] = new int[10];
        for (int i = 0; i < 2; i++) {
            b_Name[i] = "B " + i;
            Price[i] = 10 * (i + 1);
        }
        while (getout != true) {
            System.out.println("");
            System.out.println("\t MAIN CHOICES");
            System.out.print("\t 1- SEARCH OF BOOKS \n\t 2- LISTS OF BOOKS \n\t 3-CANCEL \n");
            System.out.print("\t PLEASE ENTER YOUR CHOICE HERE :  ");            
            choice = input.nextInt();
            System.out.println("");
            switch (choice) {
                case 1:
                    String s_Book;
                    System.out.print("\t PLEASE ENTER THE NAME OF BOOK : ");                    
                    s_Book = input.next();
                    int c = 0,
                     k = -1,
                     choice1;
                    for (int i = 0; i < 2; i++) {
                        if (s_Book.equals(b_Name[i])) {
                            c = 1;
                            k = i;
                        }
                    }
                    if (c == 1) {
                        System.out.print("\t " + b_Name[k] + "\t" + Price[k]);
                        System.out.println("\n\n");
                        System.out.println("\t 1- ADD TO CART");
                        System.out.println("\t 2- BACK");
                        System.out.print("\t : ");
                        choice1 = input.nextInt();
                        if (choice1 == 1) {
                            try {
                                System.out.print("\t NAEM: ");
                                name = input.next();
                                System.out.print("\t EMAIL:  ");
                                e_m = input.next();
                                System.out.print("\t PASSWORD: ");
                                pass = input.next();
                                System.out.print("\t COUNTRY: ");
                                coun = input.next();
                                pric = Price[k];
                                Respons = obj1.chek(name, e_m, pass, coun, pric);
                            } catch (RemoteException ex) {
                                System.err.println(ex);
                            }
                            if (Respons.equals("DONE")) {
                                System.out.println("\t YOUR OPERATION IS ADD.. THANKS FOR USING OUR SERVECES ");
                            } else if (Respons.equals("YOU DON`T HAVE ENOUGH MONEY")) {
                                System.out.println("\t YOU DON`T HAVE ENOUGH MONEY.. PLEASE RECHARGE YOUR ACCOUNT AND TRY AGAIN");
                            } else if (Respons.equals("NOT FOUND")) {
                                System.out.println("\t YOU DON`T HAVE ACCOUNT ");
                            }
                        } else if (choice1 == 2) {
                            break;
                        }
                    } else {
                        System.err.println("\t NOT FOUND ... PLEASE TRY AGAIN");
                    }
                    break;

                case 2:
                    int num;
                    System.out.print("\t ALL BOOKS \n");
                    System.out.println("\t NUMBER \t NAME \t PRICE");
                    for (int i = 0; i < 2; i++) {
                        System.out.println("\t " + (i + 1) + "\t\t " + b_Name[i] + "\t" + Price[i]);
                    }
                    System.out.println("\n");
                    System.out.println("\t 1- SELECT BOOK TO CART");
                    System.out.println("\t 2- BACK");
                    System.out.print("\t PLEASE ENTER YOUR CHOICE HERE :  ");
                    choice1 = input.nextInt();
                    if (choice1 == 1) {
                        try {
                            System.out.print("\t ENTER NUMBER OF THE BOOK :  ");
                            
                            num = input.nextInt();
                            System.out.print("\t NAEM: ");
                            name = input.next();
                            System.out.print("\t EMAIL:  ");
                            e_m = input.next();
                            System.out.print("\t PASSWORD: ");
                            pass = input.next();
                            System.out.print("\t COUNTRY: ");
                            coun = input.next();
                            pric = Price[num - 1];
                            Respons = obj1.chek(name, e_m, pass, coun, pric);
                        } catch (RemoteException ex) {
                            System.err.println(ex);
                        }
                        if (Respons.equals("DONE")) {
                            System.out.println("\t YOUR OPERATION IS ADD.. THANKS FOR USING OUR SERVECES ");
                        } else if (Respons.equals("YOU DON`T HAVE ENOUGH MONEY")) {
                            System.out.println("\t YOU DON`T HAVE ENOUGH MONEY.. PLEASE RECHARGE YOUR ACCOUNT AND TRY AGAIN");
                        } else if (Respons.equals("NOT FOUND")) {
                            System.out.println("\t YOU DON`T HAVE ACCOUNT ");
                        }
                    } else if (choice1 == 2) {
                        break;
                    }

                    break;
                case 3:
                    System.out.println("\t GOOD BEY");
                    getout = true;
                    break;
                default:
                    System.out.println("\tPLEASE ENTER FROM CHOICES");
            }
        }

    }

}
