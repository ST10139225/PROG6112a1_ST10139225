package com.mycompany.prog6111a1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import jdk.nashorn.api.tree.BreakTree;

/**
 *
 * @author Koketso Baholo
 */
public class Products implements ActionListener {
//This is the file which containts all the succesfully captured Products
//Keeps track of product number
  private static int ProductNumber =0;
//Keep track of single record of product details
  String Record = "";
      private static int Number=0;
      int NumberOfProducts =0;
          float AveragePrice =0;
          float TotalPrice =0;

 public static void setProductNumber(){
     ProductNumber = Number++;
 }
 public static int getProductNumber(){
     return ProductNumber;
 }
  
//Method to search for a product 
  public void SearchProduct() {
    String file ="detailsOfProducts.txt";

    String PCode = JOptionPane.showInputDialog(null, "Enter code of the product");
    boolean RecordFound = false;

    try (Scanner scanlines = new Scanner(new FileReader(file))) {
      while (scanlines.hasNextLine() && RecordFound == false) {
        Record = scanlines.nextLine();
        if (Record.startsWith("*") && scanlines.hasNextLine()) {
          scanlines.nextLine();
        } else {
          if (Record.startsWith(PCode)) {
            RecordFound = true;
          }
        }

      }
      if (RecordFound == true) {
        String[] values = Record.split(",");

        JOptionPane.showMessageDialog(null,
            "******************************" + "\nProduct search results" + "\n******************************"
                + "\nProduct Code: " + values[0] + "\nProduct Name: " + values[1] + "\nProduct Warrenty: " + values[2]
                + "\nProduct Category: " + values[3] + "\nProduct Price: " + values[4] + "\nProduct Stock Levels: "
                + values[5] + "\nProduct Supplier: " + values[6] + "\n******************************");
        ProductModificationMenu();
        } 
        else {////If the product is not found                                                                     ///// //////////
        int menu = Integer.parseInt(JOptionPane.showInputDialog(null,                                        ////           
            "The Product can not be located. Invalid Product. \nEnter (1) to launch menu or any other key to exit"));////
                                                                                                                             ////
          if (menu == 1) {
            Menu();
          } else {
            ExitApplication();
          }                                                                                                                   ///
          ///////////end                                                                                                  ///////
      }
      scanlines.close();

    } // End of while

    catch (IOException e) {
    } // End of try-catch

  }

  String Details = "";

  String Category = "";
  String Warrenty = "";

  public void SaveProduct() {
          setProductNumber();

    String[] ProductS = new String[7];
    // To apply a warrenty according to integer entered
    if (ProductWarrenty.getText().equalsIgnoreCase("1")) {
      Warrenty = "Six-months";
    } else {
      Warrenty = "Two years";
    }
    //////////
    ProductS[0] = ProductCode.getText();
    ProductS[1] = ProductName.getText();
    ProductS[2] = Category;
    ProductS[3] = Warrenty;
    ProductS[4] = ProductPrice.getText();
    ProductS[5] = ProductLevel.getText();
    ProductS[6] = ProductSupplier.getText();
    int valid = 0 ;
    for (String nullSearch : ProductS) {
      valid=0;
      if (nullSearch.isEmpty() | nullSearch.isBlank()) {
        ExitApplication();
      } else {
        valid=1;
        Details = ProductS[0] + "," + ProductS[1] + "," + ProductS[2] + "," + ProductS[3] + "," + ProductS[4] + ","
            + ProductS[5] + "," + ProductS[6];
        

      }
    }
    if( valid==1){
      storeDetails(Details);

    }else{        JOptionPane.showMessageDialog(null, "Please make sure that you have filled all the fields");
}

    // try {Products.add(Details);}catch(NullPointerException |
    // IndexOutOfBoundsException e){System.out.println("Did it");}
    ProductName.setText("");
    ProductCode.setText("");
    ProductWarrenty.setText("");
    ProductPrice.setText("");
    ProductLevel.setText("");
    ProductSupplier.setText("");
  }









 ////////Method for updating the product
private static Scanner RecordString; 
  public void UpdateProduct( ) {
    String ProductCode = JOptionPane.showInputDialog(null, "Please enter the product code");
    String[] Args = { "Update Product Warrenty", "Update Product Price", "Update Product Stock Level" };
    ArrayList<String> newFileAr = new ArrayList<>() ;

  ///Editing file
  String temp ="Temp.txt";
  String original ="detailsOfProducts.txt";
  File NewOrginal = new File(temp);
  File originalFile = new File(original);
      boolean exists =false;
  String[] Values = new String[72];
  try {
    FileWriter FileWr = new FileWriter(NewOrginal,true);
    BufferedWriter BuffWr = new BufferedWriter(FileWr);
    PrintWriter PrintWr = new PrintWriter(BuffWr);
    boolean RecordFound = false;
  

    RecordString = new Scanner(new File(original));
    while (RecordString.hasNextLine() && RecordFound == false) {
    String line = RecordString.nextLine();
    Values = line.split(",");
         
      
          if (line.startsWith(ProductCode)) {
             
            int option = JOptionPane.showOptionDialog(null, "Options", "Please select", JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, Args, 0);
    System.out.println(option);
    if (option == 0) {
      Values[3]= JOptionPane.showInputDialog(null, "Please enter new Warrenty for product: "+ Values[1]+"\nOld Warrenty: "+Values[3]);
    }
    if (option == 1) {
      Values[4]= JOptionPane.showInputDialog(null, "Please enter new Price for product: "+ Values[1]+"\nOld Price: "+Values[4]);

    }
    if (option == 2) {
      Values[5]= JOptionPane.showInputDialog(null, "Please enter new Stock Level for product: "+ Values[1]+"\nOld Stock level: "+Values[5]);
  }
      String newRecord = Values[0]+","+Values[1]+","+Values[2]+","+Values[3]+","+Values[4]+","+Values[5]+","+Values[6];
      PrintWr.println(newRecord);
    }else{
          PrintWr.println(line);

        }
}

  
RecordString.close();
PrintWr.flush();
PrintWr.close();

deletefiles(originalFile); 


if(NewOrginal.isFile()){
    System.out.println("File "+NewOrginal.isFile());
    File j = new File("detailsOfProducts.txt");
    NewOrginal.renameTo(j);
        System.out.println("File renamed"+NewOrginal.isFile());

    
       // File k = new File("ds");

    //originalFile.renameTo(k);
}    
      

  

} catch(Exception e){}



}
///////End of update()


  

private void Switchfilenames(File file1, File file2){
  Path k = Paths.get(file1.getPath());
  
  try {
    Files.move(k, k.resolveSibling(file2.getPath()));
    System.out.println("Glory to Christ");
  } catch (IOException e) {
    System.out.println("Unsuccessful file operation");

        }}
    

  



/////Deleting the product
private static Scanner RecordStringd; 

  public void DeleteProduct() {
  
  String ProductCode = JOptionPane.showInputDialog(null, "Please enter the product code");
  String[] Args={"Yes, DELETE!","No dont delete!"};

  ///Editing file
  String temp ="Temp.txt";
  String original ="detailsOfProducts.txt";
  File NewOrginal = new File(temp);
  File originalFile = new File(original);
      boolean exists =false;
  String[] Values = new String[72];
  try {
    FileWriter FileWr = new FileWriter(NewOrginal,true);
    BufferedWriter BuffWr = new BufferedWriter(FileWr);
    PrintWriter PrintWr = new PrintWriter(BuffWr);
    boolean RecordFound = false;
  

    RecordString = new Scanner(new File(original));
    while (RecordString.hasNextLine() && RecordFound == false) {
    String line = RecordString.nextLine();
    Values = line.split(",");
         
                
          if (line.startsWith(ProductCode)) {
              int option = JOptionPane.showOptionDialog(null, "Options", "Are you SURE", JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, Args, 0);
              if(option==0){
            String newRecord = "-";
      PrintWr.println(newRecord);}
              else{
              ExitApplication();
              }
    }else{
          PrintWr.println(line);

        }
}

  
RecordString.close();
PrintWr.flush();
PrintWr.close();

deletefiles(originalFile); 

  
if(NewOrginal.isFile()){
        File j = new File("detailsOfProducts.txt");
            NewOrginal.renameTo(j);

    System.out.println("File "+NewOrginal.isFile());
        System.out.println("File renamed"+NewOrginal.isFile());

    
       // File k = new File("ds");

    //originalFile.renameTo(k);
}    
      

  

} catch(Exception e){}


  
  
  }



  
///////End of deleteProduct()

   
  
  

private void deletefiles(File g){
    if(g.exists()){
        g.delete();
    if(g.exists()){
        g.deleteOnExit() ;
    }
    }
        }












  // Menu that pops up once a product is found
  public void ProductModificationMenu() {

    String[] Args = { "Update Product", "Delete Product", "Exit Application" };
    int option = JOptionPane.showOptionDialog(null, "Options", "Please select", JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, Args, 0);
    System.out.println(option);
    if (option == 0) {
      UpdateProduct();
    }
    if (option == 1) {
      DeleteProduct();
    }
    if (option == 2) {
      GenerateReport();
    }
    if (option == -1) {
      ExitApplication();
    }
  }

  private static Scanner RecordStrxing;
  public void GenerateReport() {
      String[] Record; 
      try {
          String original ="detailsOfProducts.txt";
          File originalFile = new File(original);
          
          
          
          
          RecordStrxing = new Scanner(originalFile);
          
          while (RecordStrxing.hasNextLine()) {
              Record = RecordStrxing.nextLine().split(",");
              for(String record: Record){
                  System.out.println(record);
              }
          }
          NumberOfProducts = getProductNumber();
        AveragePrice=TotalPrice/NumberOfProducts;
          System.out.println("Number Of Products: "+NumberOfProducts);
          System.out.println("Average Price: "+AveragePrice);
          System.out.println("Total Price: "+TotalPrice);
      }  catch (FileNotFoundException ex) {}
      RecordStrxing.close();
  }
public void ClearEdits() {
    ProductName.setText("");
    ProductCode.setText("");
    ProductWarrenty.setText("");
    ProductPrice.setText("");
    ProductLevel.setText("");
    ProductSupplier.setText("");
    Catgroup.clearSelection();
  }

  public void Menu() {
    int opt = Integer.parseInt(JOptionPane.showInputDialog(null,
        "(1) Capture a new product\n(2) Search for a product\n(3) Update a product\n(4) Delete a product\n(5)Print Report\n(6)Exit Application"));
    if (opt == 1) {
      CaptureProduct();
    }
    if (opt == 2) {
      SearchProduct();
    }
    if (opt == 3) {
      UpdateProduct();
    }
    if (opt == 4) {
      DeleteProduct();
    }
    if (opt == 5) {
      GenerateReport();
    }
    if (opt == 6) {
      ExitApplication();
    }

  }

  public void ExitApplication() {
    
  }

  public void storeDetails(String Details) {
    String file ="detailsOfProducts.txt";


    String[][] Products = new String[7][1];
    Products[ProductNumber][0] = Details;
    try {
      BufferedWriter addDets = new BufferedWriter(new FileWriter(file, true));
      try (PrintWriter printwr = new PrintWriter(addDets)) {
        printwr.println(ProductNumber + "***************        " + "Product Number: " + ProductNumber
            + "          *****************");
        printwr.println(Products[ProductNumber][0]);
        printwr.println("**************************************************************");
        printwr.flush();
        printwr.close();

      }
    } // end of if
    catch (IOException e) {
    } // End of try-catch

    JOptionPane.showMessageDialog(null, "Product details have been successfully saved.");

  }

  // This Section for the components.
  JTextField ProductCode = new JTextField();

  JTextField ProductName = new JTextField();
  JTextField ProductWarrenty = new JTextField();
  JTextField ProductPrice = new JTextField();
  JTextField ProductLevel = new JTextField();
  JTextField ProductSupplier = new JTextField();

  JRadioButton DesktopComputer = new JRadioButton("Desktop Computer");
  JRadioButton Laptop = new JRadioButton("Laptop");
  JRadioButton Tablet = new JRadioButton("Tablet");
  JRadioButton Printer = new JRadioButton("Printer");
  JRadioButton GamingConsole = new JRadioButton("Gaming Console");

  ///// Labels:
  JLabel LabelName = new JLabel("Name");
  JLabel LabelWarrenty = new JLabel("Warrenty");
  JLabel LabelPrice = new JLabel("Price");
  JLabel LabelLevel = new JLabel("Level");
  JLabel LabelSupplier = new JLabel("Supplier");
  JLabel LabelCategory = new JLabel("Category");

  ButtonGroup Catgroup = new ButtonGroup();

  JButton Enter = new JButton("Capture new Product");
  JButton Search = new JButton("Menu");

  JFrame CaptureProduct = new JFrame();

  public void CaptureProduct() {

    Catgroup.add(DesktopComputer);
    Catgroup.add(Laptop);
    Catgroup.add(Tablet);
    Catgroup.add(Printer);
    Catgroup.add(GamingConsole);
    DesktopComputer.addActionListener(this);
    Laptop.addActionListener(this);
    Tablet.addActionListener(this);
    Printer.addActionListener(this);
    GamingConsole.addActionListener(this);

    JLabel LabelCode = new JLabel("Code");
    JLabel Labelmsg = new JLabel("Make full screen");
    LabelCode.setBounds(40, 100, 250, 40);
    Labelmsg.setBounds(40, 60, 250, 40);

    ProductCode.setBounds(100, 100, 250, 40);

    LabelName.setBounds(40, 150, 250, 40);

    ProductName.setBounds(100, 150, 250, 40);

    LabelCategory.setBounds(40, 200, 100, 40);

    LabelWarrenty.setBounds(40, 300, 250, 40);

    ProductWarrenty.setBounds(100, 300, 250, 40);

    LabelPrice.setBounds(40, 350, 250, 40);

    ProductPrice.setBounds(100, 350, 250, 40);

    LabelLevel.setBounds(40, 400, 250, 40);

    ProductLevel.setBounds(100, 400, 250, 40);

    LabelSupplier.setBounds(40, 450, 250, 40);

    ProductSupplier.setBounds(100, 450, 250, 40);

    Enter.setBounds(500, 400, 180, 50);
    Enter.addActionListener(this);

    Search.setBounds(500, 350, 120, 50);
    Search.addActionListener(this);
    JPanel cats = new JPanel();
    cats.setBounds(110, 200, 300, 70);
    cats.add(DesktopComputer);
    cats.add(Laptop);
    cats.add(Tablet);
    cats.add(Printer);
    cats.add(GamingConsole);

    CaptureProduct.setSize(700, 1000);
    CaptureProduct.setVisible(true);
    CaptureProduct.setLayout(null);
    CaptureProduct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    CaptureProduct.add(ProductCode);
    CaptureProduct.add(LabelCode);
    CaptureProduct.add(ProductName);
    CaptureProduct.add(LabelName);
    CaptureProduct.add(LabelCategory);
    CaptureProduct.add(Labelmsg);
    CaptureProduct.add(cats);
    CaptureProduct.add(ProductWarrenty);
    CaptureProduct.add(LabelWarrenty);
    CaptureProduct.add(ProductPrice);
    CaptureProduct.add(LabelPrice);
    CaptureProduct.add(ProductLevel);
    CaptureProduct.add(LabelLevel);
    CaptureProduct.add(ProductSupplier);
    CaptureProduct.add(LabelSupplier);
    CaptureProduct.add(Enter);
    CaptureProduct.add(Search);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == Enter) {
      SaveProduct();
    }
    if (e.getSource() == Search) {
      Menu();

    }

    if (e.getSource() == DesktopComputer) {
      Category = "Desktop Computer";

    } else if (e.getSource() == Laptop) {
      Category = "Laptop";
    } else if (e.getSource() == Tablet) {
      Category = "Tablet";
    } else if (e.getSource() == GamingConsole) {
      Category = "Gaming Console";
    } else if (e.getSource() == Printer) {
      Category = "Printer";
    } else if (Category.isEmpty() | Category.isBlank()) {
      JOptionPane.showMessageDialog(null, "Please choose a category");
    }
  }
}
