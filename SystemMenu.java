import java.util.Scanner;
public class SystemMenu {

    public static void main(String[] args) {
        String info = "Press 1 to add a warehouse.\n" +
                "Press 2 to add a book to a warehouse\n" +
                "Press 3 to remove a warehouse\n" +
                "Press 4 to remove a book from a warehouse\n" +
                "Press 5 to search by warehouse\n" +
                "Press 6 to search book in warehouse\n" +
                "Press 7 to search book\n" +
                "Press 8 to print the whole system\n" +
                "Press 0 to exit\n" +
                ">> ";
        System.out.print(info);
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        sc.nextLine();

        ST warehouse_system = new ST();
        while (ans != 0) {
            if (ans == 1) {
                System.out.print("Enter warehouse ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter city: ");
                String city = sc.nextLine();
                warehouse_system.insertWarehouse(id,city);
            } else if (ans == 2) {
                System.out.print("Enter warehouse ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter book's ISBN: ");
                int isbn = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter book's copies: ");
                int copies = sc.nextInt();
                sc.nextLine();
                warehouse_system.insertBookAtWarehouse(id,isbn,copies);
            } else if (ans == 3){
                System.out.print("Enter warehouse ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                warehouse_system.removeWarehouse(id);
            } else if (ans == 4) {
                System.out.print("Enter warehouse ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter book's ISBN: ");
                int isbn = sc.nextInt();
                sc.nextLine();
                warehouse_system.removeBook(id,isbn);

            } else if (ans == 5) {
                System.out.print("Enter warehouse ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                warehouse_system.searchByWarehouse(id);
            } else if (ans == 6) {
                System.out.print("Enter warehouse ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter book's ISBN: ");
                int isbn = sc.nextInt();
                sc.nextLine();
                warehouse_system.searchBookInWarehouse(id,isbn);
            } else if (ans == 7) {
                System.out.print("Enter book's ISBN: ");
                int isbn = sc.nextInt();
                sc.nextLine();
                warehouse_system.searchBook(isbn);
            } else if (ans == 8){
                warehouse_system.printΤree(System.out);
            }



            System.out.print(info);
            ans = sc.nextInt();
        }



    }
}
