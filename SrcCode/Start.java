package SrcCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import SrcCode.ShoppingCart.QuantityExceededException;
import SrcCode.SubCategories.ProductNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Start {

    public static String[] AccountChoice() {
        String[] dataArr = null;
        String response;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Do you have an account? Y/N");
            response = input.nextLine();
            if (response.equalsIgnoreCase("y")) {
                Login account = new Login();
                dataArr = account.getUserData();
            } else if (response.equalsIgnoreCase("n")) {
                System.out.println("Register an account");
                Register account = new Register();
                System.out.println("Now you can Login");
                Login acc = new Login();
                dataArr = acc.getUserData();
            } else {
                System.out.println("Invalid response. Please enter 'y' or 'n'.");
            }
        } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));
        return dataArr;
    }

    public static void CustomerShoppingSystem() throws IOException {
        Scanner input = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        int exists = 0;
        Product product = Product.product(); //has object of the product you choose
        for (int i = 0; i < cart.getItems().size(); i++) {
            if (cart.getItems().get(i).equals(product)) {
                exists = 1;
                break;
            }
        }
        System.out.println("Add to cart or remove from cart: (Enter add or remove)");
        if (input.next().equalsIgnoreCase("add")) {
            if (exists == 0) //not in cart
            {
                cart.addItem(product);
            } else //in cart already
            {
                try {
                    cart.Increase(product);
                } catch (QuantityExceededException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else if (input.next().equalsIgnoreCase("remove")) {
            if (exists == 0)//not incart
            {
                cart.removeItem(product);
            } else //in cart already
            {
                cart.Decrease(product);
            }
        }
        String mychoice;
        do {

            System.out.println("Continue shopping or access shopping cart? (Enter continue or cart)");
            mychoice = input.next();
            if (mychoice.equalsIgnoreCase("continue")) {
                try {
                    Product.DisplayProducts();
                } catch (FileNotFoundException e) {

                    e.printStackTrace();
                } catch (ProductNotFoundException e) {

                    e.printStackTrace();
                }
            } else if (mychoice.equalsIgnoreCase("cart")) {
                cart.displayCart();
                System.out.println(cart.getTotalPriceBeforeDiscount());
                System.out.println("Do you want to clear cart, get checkout, cancel an order or remove item from cart? (Enter: clear, checkout,cancel, remove)");
                String choice = input.next();
                do {

                    if (choice.equalsIgnoreCase("clear")) {
                        cart.clearCart();
                    } else if (choice.equalsIgnoreCase("checkout")) {
                        Order order = new Order(cart);
                        //order.Confirmation(cart);
                        cart.checkout();
                        double amount = 0;
                        System.out.println("Do you have a discount code? (Enter Y/N)");
                        String n = input.next();
                        do {

                            if (n.equalsIgnoreCase("Y")) {
                                cart.applyDiscountCode();
                                amount = cart.getTotalPriceAfterDiscount();
                            } else if (n.equalsIgnoreCase("N")) {
                                amount = cart.getTotalPriceBeforeDiscount();
                            }

                        } while (!n.equalsIgnoreCase("Y") && !n.equalsIgnoreCase("N"));
                        System.out.println("Do you want to pay by credit card, gift card or cash on delivery? (Enter credit,giftcard or cash): ");
                        String d = input.next();
                        do {

                            order.pay(d);
                        } while (!d.equalsIgnoreCase("credit") && !d.equalsIgnoreCase("giftcard") && !d.equalsIgnoreCase("cash"));
                        //order.Confirmation(cart);

                    } else if (choice.equalsIgnoreCase("remove")) {
                        System.out.println("Choose product to remove");
                        String k = input.next();
                        int found = 0;
                        do {

                            for (int i = 0; i < cart.getItems().size(); i++) {
                                if (k.equalsIgnoreCase(cart.getItems().get(i).getName())) {
                                    cart.Decrease(cart.getItems().get(i));
                                    found = 1;
                                    break;
                                }
                            }
                            if (found == 0) {
                                System.out.println("Item is not in cart, retry: ");
                            }
                        } while (found == 0);
                    } else if (choice.equalsIgnoreCase("cancel")) {
                        System.out.println("Do you want to cancel an order? Y/N");
                        if (input.next().equalsIgnoreCase("Y")) {
                            System.out.println("Enter Date: (MM/DD/YYYY) ");
                            String strDate = input.next();
                            LocalDate orderDate = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("M/d/u"));
                            Order order = new Order(cart, orderDate);
                            order.CancelOrder();
                        } else if (input.next().equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    while (!input.next().equalsIgnoreCase("Y") && !input.next().equalsIgnoreCase("N"));
                } while (!choice.equalsIgnoreCase("clear") && !choice.equalsIgnoreCase("checkout") && !input.next().equalsIgnoreCase("cancel") && !input.next().equalsIgnoreCase("remove"));

            }
        } while (!mychoice.equalsIgnoreCase("continue") && !mychoice.equalsIgnoreCase("cart"));
        CustomerShoppingSystem();
    }

    public static void main(String[] args) {

        String[] arr = AccountChoice();
        Scanner input = new Scanner(System.in);
        //now user is logged in
        String type = Login.typeOfUser();
        //check admin or customer
        
        if (type.equalsIgnoreCase("customer")) {
            String userInput;
            System.out.println("Shopping or enter profile (Enter shopping or profile)");
            do {
                userInput = input.next(); // Read the input once and store it in a variable
                if (userInput.equalsIgnoreCase("Shopping")) {
                    try {
                        Product.DisplayProducts();
                        CustomerShoppingSystem(); // Assuming this is a method call
                    } catch (ProductNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                } else if (userInput.equalsIgnoreCase("Profile")) {
                    User customer = new Customer(arr[0], arr[2], arr[3], arr[4], arr[5], arr[6]); //Polymorphism
                    customer.ProfileInterface();
                }
            } while (!userInput.equalsIgnoreCase("Shopping") && !userInput.equalsIgnoreCase("Profile"));

        } else if (type.equalsIgnoreCase("admin")) // THIS IS DONE FOR ADMIN
        {
            String product, subcat, choice;
            //We chose a specific product
            Admin admin = new Admin(arr[0], arr[2], arr[3], arr[4], arr[5], arr[6]);
            do {
                System.out.println("Do you want to add items for sale, remove items from sale or add discount code? (Enter add or remove or discount)");
                choice = input.next();

                if (choice.equalsIgnoreCase("add")) {
                    System.out.println("Enter product subcategory: ");
                    subcat = input.next();
                    System.out.println("Enter product name: ");
                    product = input.next();
                    try {
                        admin.addProduct(product, subcat);
                    } catch (FileNotFoundException e) {

                        e.printStackTrace();
                    }
                } else if (choice.equalsIgnoreCase("remove")) {
                    System.out.println("Enter product subcategory: ");
                    subcat = input.next();
                    System.out.println("Enter product name: ");
                    product = input.next();
                    admin.removeProduct(product, subcat);

                } else if (choice.equalsIgnoreCase("discount")) {
                    do {
                        System.out.println("Enter discount code: ");
                        String code = input.next();

                        System.out.println("Enter percentage: ");
                        int percent = input.nextInt();
                        admin.addDiscountCode(code, percent);

                    } while (!input.next().equalsIgnoreCase("add"));
                }
            } while (!choice.equalsIgnoreCase("add") && !choice.equalsIgnoreCase("remove") && !choice.equalsIgnoreCase("discount"));
            ////////////////////////////////////////////////// 
            System.out.println("do you want to visit your profile (Enter Y or N)");
            choice = input.next();
            do {

                if (choice.equalsIgnoreCase("Y")) {

                    admin.ProfileInterface();

                } else if (choice.equalsIgnoreCase("N")) {
                    System.out.println("thank you for visiting the site");
                    System.exit(1);
                }
            } while (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("Y"));

        }
    }
}
