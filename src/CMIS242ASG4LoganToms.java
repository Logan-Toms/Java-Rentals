import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * 
 * @Author Logan Toms
 * @Date 05/09/2023
 * @Description: This Java program models a Media library with EBook, MusicCD, and MovieDVD objects
 */

 /*
  * The Media class is an abstract class that represents a generic media item
  * It provides common properties and methods that all media items have in common
  */
 abstract class Media {
    // Attributes common to all Media objects
    private String mediaID;
    private String title;
    private String yearPublished;
    private boolean isRented;

    /*
     * Constructs a Media object with the specified media ID, title, year published, and rental status
     * 
     * @param mediaID the unique identifier of the media item
     * @param title the title of the media item
     * @param yearPublished the year the media item was published
     * @param isRented the rental status of the media item
     */
    public Media(String mediaID, String title, String yearPublished, boolean isRented) {
        this.mediaID = mediaID;
        this.title = title;
        this.yearPublished = yearPublished;
        this.isRented = isRented;
    }

    // Abstract method to calculate the rental fee for a Media object
    public abstract double calculateRentalFee();

    // Getters for Media attributes
    public String getId() {
        return mediaID;
    }

    public String getTitle() {
        return title;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public boolean getRentStatus() {
        return isRented;
    }
}

/*
 * EBook class, which extends Media
 */
class EBook extends Media {
    private int numberOfChapters;

    /*
     * Constructs an EBook object with the specified media ID, title, year published, number of chapters, and rental status
     * 
     * @param mediaId the ID of the EBook
     * @param title the title of the EBook
     * @param yearPublished the year the EBook was published
     * @param numberOfChapters the number of chapters in the EBook
     * @param isRented whether the EBook is currently rented or not
     */
    public EBook(String mediaId, String title, String yearPublished, int numberOfChapters, boolean isRented) {
        super(mediaId, title, yearPublished, isRented);
        this.numberOfChapters = numberOfChapters;
    }

    @Override
    public double calculateRentalFee() {
        double rentalFee = 1.50 + numberOfChapters * 0.10;
        if (Integer.parseInt(getYearPublished()) > 2015) {
            rentalFee += 1.00;
        }
        return rentalFee;
    }

    // Getters and setters for EBook attributes
    public int getNumberOfChapters() {
        return numberOfChapters;
    }

    public void setNumberOfChapters(int numberOfChapters) {
        this.numberOfChapters = numberOfChapters;
    }
}

/*
 * MusicCD class, which extends Media
 */
class MusicCD extends Media {
    private int lengthInMinutes;

    /*
     * Creates a new instance of a MusicCD object with the specified parameters.
     * 
     * @param mediaId the unique identifier of the MusicCD
     * @param title the title of the MusicCD
     * @param yearPublished the year the MusicCD was published
     * @param lengthInMinutes the length of the MusicCD in minutes
     * @param isRented the rental status of the MusicCD
     */
    public MusicCD(String mediaId, String title, String yearPublished, int lengthInMinutes, boolean isRented) {
        super(mediaId, title, yearPublished, isRented);
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    public double calculateRentalFee() {
       double rentalFee = 1.50 + lengthInMinutes * 0.045;
        if (Integer.parseInt(getYearPublished()) > 2014) {
        rentalFee += 2.00;
        }
        return rentalFee;
    } public int getLengthInMinutes() {
    return lengthInMinutes;
    }

    // Setters for MusicCD attributes
    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }
}

/*
 * MovieDVD class inheriting from Media
 */
class MovieDVD extends Media {
    private int sizeInMegabytes;

    /*
     * Constructs a MovieDVD object with the given media ID, title, year published, size in megabytes and rental status.
     * 
     * @param mediaId the ID of the DVD
     * @param title the title of the DVD
     * @param yearPublished the year the DVD was published
     * @param sizeInMegabytes the size of the DVD in megabytes
     * @param isRented the rental status of the DVD
     */
    public MovieDVD(String mediaId, String title, String yearPublished, int sizeInMegabytes, boolean isRented) {
        super(mediaId, title, yearPublished, isRented);
        this.sizeInMegabytes = sizeInMegabytes;
    }

    @Override
    public double calculateRentalFee() {
        double rentalFee = 3.25 + 1.50;
        if (Integer.parseInt(getYearPublished()) > 2019) {
            rentalFee = 5.00;
        }
        return rentalFee;
    }

    // Getters and setters for MovieDVD attributes
    public int getSizeInMegabytes() {
        return sizeInMegabytes;
    }

    public void setSizeInMegabytes(int sizeInMegabytes) {
        this.sizeInMegabytes = sizeInMegabytes;
    }
}

/*
 * MediaManager class to handle Media objects
 */
class MediaManager {
    // Stores a list of Media objects in the ArrayList created in the main () method
    private List<String[]> mediaLibrary;


    // Constructor for MediaManager objects
    public MediaManager() {
        // Create an ArrayList to store Media objects
        mediaLibrary = new ArrayList<>();
    }    

    // Stores the number of Media objects in the ArrayList
    public void addMediaToArray(Media media) {
        String[] mediaInfo = new String[7];
        
        mediaInfo[0] = media.getId();
        mediaInfo[1] = media.getRentStatus() ? "1" : "0";
        mediaInfo[3] = media.getTitle();
        mediaInfo[4] = media.getYearPublished();
        mediaInfo[6] = String.valueOf(media.calculateRentalFee());
    
        if (media instanceof EBook) {
            mediaInfo[2] = "E";
            mediaInfo[5] = String.valueOf(((EBook) media).getNumberOfChapters());
        } else if (media instanceof MusicCD) {
            mediaInfo[2] = "C";
            mediaInfo[5] = String.valueOf(((MusicCD) media).getLengthInMinutes());
        } else if (media instanceof MovieDVD) {
            mediaInfo[2] = "D";
            mediaInfo[5] = "1";
        }
    
        mediaLibrary.add(mediaInfo);
    }
    
    /*
     * This method adds a new Media object to the ArrayList
     */
    public void addMedia() {
        Scanner scanner = new Scanner(System.in);

        // Get Media ID
        System.out.print("Enter Media ID (5 digits): ");
        String mediaId = scanner.next();
        while (!mediaId.matches("\\d{5}")) {
            System.out.println("Invalid input. Please enter exactly 5 digits.");
            System.out.print("Enter Media ID (5 digits): ");
            mediaId = scanner.next();
            
        }

        // Get Rent Status
        System.out.print("Enter Rent Status (0 for not rented, 1 for rented): ");
        int rentStatus = scanner.nextInt();
        while (rentStatus != 0 && rentStatus != 1) {
            System.out.println("Invalid input. Please enter 0 for not rented or 1 for rented.");
            System.out.print("Enter Rent Status (0 for not rented, 1 for rented): ");
            rentStatus = scanner.nextInt();
        }
        boolean isRented = rentStatus == 1;

        // Get Media Model
        System.out.print("Enter Media Model (E for Ebook, C for CD, D for DVD): ");
        String mediaModel = scanner.next().toUpperCase();
        while (!mediaModel.matches("[ECD]")) {
            System.out.println("Invalid input. Please enter E for Ebook, C for CD, or D for DVD.");
            System.out.print("Enter Media Model (E for Ebook, C for CD, D for DVD): ");
            mediaModel = scanner.next().toUpperCase();
        }

        // Get Media Title
        scanner.nextLine(); // To consume the remaining newline character
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        //  Get Year Published
        System.out.print("Enter Year Published (4 digits): ");
        String yearPublished = scanner.next();
        while (!yearPublished.matches("\\d{4}")) {
            System.out.println("Invalid input. Please enter exactly 4 digits.");
            System.out.print("Enter Year Published (4 digits): ");
            yearPublished = scanner.next();
        }

        // Get Scope Media
        int scopeMedia;
        if (mediaModel == "D") { // If DVD, default scope is 1
            scopeMedia = 1;
        } else { // If Ebook or CD, get scope from user
            System.out.print("Enter Scope Media: ");
            scopeMedia = scanner.nextInt();
        }
        
        Media media;
        switch (mediaModel) {
            case "E":
                media = new EBook(mediaId, title, yearPublished, scopeMedia, isRented);
                break;
            case "C":
                media = new MusicCD(mediaId, title, yearPublished, scopeMedia, isRented);
                break;
            case "D":
                media = new MovieDVD(mediaId, title, yearPublished, 1, isRented);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + mediaModel);
        }

        addMediaToArray(media);
        System.out.print("\nMedia added succssfully.\n");

    } 

    public void removeMedia() {
        Scanner scanner = new Scanner(System.in);
    
        // Get Media ID
        System.out.print("\nEnter Media ID (5 digits): ");
        String mediaId = scanner.next();
        while (!mediaId.matches("\\d{5}")) {
            System.out.println("Invalid input. Please enter exactly 5 digits.");
            System.out.print("Enter Media ID (5 digits): ");
            mediaId = scanner.next();
        }
    
        // Find Media object in ArrayList and remove it
        boolean mediaFound = false;
        int indexToRemove = -1;
        for (int i = 0; i < mediaLibrary.size(); i++) {
            String[] mediaInfo = mediaLibrary.get(i);
            if (mediaInfo[0].equals(mediaId)) {
                mediaFound = true;
                indexToRemove = i;
                break;
            }
        }
    
        if (mediaFound) {
            mediaLibrary.remove(indexToRemove);
            System.out.println("\nMedia removed successfully.");
        } else {
            System.out.println("\nMedia not found.");
        }
    }

    public void rentMedia() {
        Scanner scanner = new Scanner(System.in);
    
        // Get Media ID
        System.out.print("Enter Media ID (5 digits): ");
        String mediaId = scanner.next();
        while (!mediaId.matches("\\d{5}")) {
            System.out.println("Invalid input. Please enter exactly 5 digits.");
            System.out.print("Enter Media ID (5 digits): ");
            mediaId = scanner.next();
        }
    
        // Find Media object in ArrayList and change the rental status
        boolean mediaFound = false;
        for (String[] mediaInfo : mediaLibrary) {
            if (mediaInfo[0].equals(mediaId)) {
                mediaFound = true;
                if (mediaInfo[1].equals("0")) { // If the media is not rented
                    mediaInfo[1] = "1"; // Change the status to rented
                    System.out.println("Media rental status changed to 'Rented'.");
                } else {
                    System.out.println("This media is already rented.");
                }
                break;
            }
        }
    
        if (!mediaFound) {
            System.out.println("Media not found.");
        }
    }

    public void modifyMedia() {
        Scanner scanner = new Scanner(System.in);
    
        // Get Media ID
        System.out.print("Enter Media ID (5 digits): ");
        String mediaId = scanner.next();
        while (!mediaId.matches("\\d{5}")) {
            System.out.println("Invalid input. Please enter exactly 5 digits.");
            System.out.print("Enter Media ID (5 digits): ");
            mediaId = scanner.next();
        }
    
        // Find Media object in ArrayList
        boolean mediaFound = false;
        int indexToModify = -1;
        for (int i = 0; i < mediaLibrary.size(); i++) {
            String[] mediaInfo = mediaLibrary.get(i);
            if (mediaInfo[0].equals(mediaId)) {
                mediaFound = true;
                indexToModify = i;
                break;
            }
        }
    
        if (!mediaFound) {
            System.out.println("Media not found.");
            return;
        }
    
        String[] mediaInfo = mediaLibrary.get(indexToModify);
        
        if (mediaInfo[2].equals("E")) { 
            // Get Scope Media
            System.out.print("Enter number of chapters: ");
            int scopeMedia = scanner.nextInt();
            mediaInfo[5] = String.valueOf(scopeMedia);
        } else if (mediaInfo[2].equals("C")){
            System.out.print("Enter length in minutes: ");
            int scopeMedia = scanner.nextInt();
            mediaInfo[5] = String.valueOf(scopeMedia);
        }        
        
        // Get Year Published
        System.out.print("Enter Year Published (4 digits): ");
        String yearPublished = scanner.next();
        while (!yearPublished.matches("\\d{4}")) {
            System.out.println("Invalid input. Please enter exactly 4 digits.");
            System.out.print("Enter Year Published (4 digits): ");
            yearPublished = scanner.next();
        }
        mediaInfo[4] = yearPublished;

        // Update rental fee
        mediaInfo[6] = String.valueOf(calculateRentalFeeForInfo(mediaInfo));
        // Dispay updated rental fee
        System.out.println("Rental fee updated to: " + mediaInfo[6]);
    
        // Save changes to the array
        mediaLibrary.set(indexToModify, mediaInfo);
        System.out.println("Media information updated.");
    }    
    
    // Helper method to create Media objects from the ArrayList
    private Media createMediaFromInfo(String[] mediaInfo) {
        String mediaId = mediaInfo[0];
        boolean isRented = mediaInfo[1].equals("1");
        String title = mediaInfo[3];
        String yearPublished = mediaInfo[4];
    
        if (mediaInfo[2].equals("E")) {
            int numberOfChapters = Integer.parseInt(mediaInfo[5]);
            return new EBook(mediaId, title, yearPublished, numberOfChapters, isRented);
        } else if (mediaInfo[2].equals("C")) {
            int lengthInMinutes = Integer.parseInt(mediaInfo[5]);
            return new MusicCD(mediaId, title, yearPublished, lengthInMinutes, isRented);
        } else if (mediaInfo[2].equals("D")) {
            int sizeInMegabytes = Integer.parseInt(mediaInfo[5]);
            return new MovieDVD(mediaId, title, yearPublished, sizeInMegabytes, isRented);
        }
    
        return null;
    }
    
    // Helper method to calculate rental fee for Media objects
    private double calculateRentalFeeForInfo(String[] mediaInfo) {
        Media media = createMediaFromInfo(mediaInfo);
        return media.calculateRentalFee();
    }
    
    private void displayMediaInfo(String[] mediaInfo) {
        String rentStatus = mediaInfo[1].equals("1") ? "Rented   " : "Available";
        String mediaType = "";
        String scope = mediaInfo[5];
    
        if (mediaInfo[2].equals("E")) {
            mediaType = "EBook";
        } else if (mediaInfo[2].equals("C")) {
            mediaType = "CD";
        } else if (mediaInfo[2].equals("D")) {
            mediaType = "DVD";
        }
    
        System.out.printf("%-7s %-7s %-7s %-30s %-7s %-8s %.2f%n",
                mediaInfo[0],
                rentStatus,
                mediaType,
                mediaInfo[3],
                mediaInfo[4],
                scope,
                Double.parseDouble(mediaInfo[6])
        );
    }
    
    public void findMedia() {
        Scanner scanner = new Scanner(System.in);
    
        // Get Media ID
        System.out.print("Enter Media ID (5 digits): ");
        String mediaId = scanner.next();
        while (!mediaId.matches("\\d{5}")) {
            System.out.println("Invalid input. Please enter exactly 5 digits.");
            System.out.print("Enter Media ID (5 digits): ");
            mediaId = scanner.next();
        }
    
        // Find Media object in ArrayList
        boolean mediaFound = false;
        System.out.println("\nID      Rent    Type    Title                           Pub     Scope     Calc");
        System.out.println("--------------------------------------------------------------------------------");
        for (String[] mediaInfo : mediaLibrary) {
            if (mediaInfo[0].equals(mediaId)) {
                mediaFound = true;
                displayMediaInfo(mediaInfo);
                break;
            }
        }
    
        if (!mediaFound) {
            System.out.println("Media not found.");
        }
    }

    public void displayOneMedia() {
        Scanner scanner = new Scanner(System.in);

        // Get Media ID
        System.out.print("Enter Media ID (5 digits): ");
        String mediaId = scanner.next();
        while (!mediaId.matches("\\d{5}")) {
            System.out.println("Invalid input. Please enter exactly 5 digits.");
            System.out.print("Enter Media ID (5 digits): ");
            mediaId = scanner.next();
        }

        // Find Media object in ArrayList
        boolean mediaFound = false;
        System.out.println("\nID      Rent    Type    Title                           Pub     Scope     Calc");
        System.out.println("--------------------------------------------------------------------------------");
        for (String[] mediaInfo : mediaLibrary) {
            if (mediaInfo[0].equals(mediaId)) {
                mediaFound = true;
                displayMediaInfo(mediaInfo);
                break;
            }
        }

        if (!mediaFound) {
            System.out.println("Media not found.");
        }
    }
    
    public void displayAllMedia() {
        System.out.println("\nID      Rent    Type    Title                           Pub     Scope     Calc");
        System.out.println("--------------------------------------------------------------------------------");
        for (String[] mediaInfo : mediaLibrary) {
            displayMediaInfo(mediaInfo);
        }
    }

    public void displayMediaByType() {
        Scanner scanner = new Scanner(System.in);
        
        // Get Media Type
        System.out.print("Enter Media Type (E for Ebook, C for CD, D for DVD): ");
        char mediaType = scanner.next().toUpperCase().charAt(0);
        while (mediaType != 'E' && mediaType != 'C' && mediaType != 'D') {
            System.out.println("Invalid input. Please enter E for Ebook, C for CD, or D for DVD.");
            System.out.print("Enter Media Type (E for Ebook, C for CD, D for DVD): ");
            mediaType = scanner.next().toUpperCase().charAt(0);
        }
    
        System.out.println("\nID      Rent    Type    Title                           Pub     Scope     Calc");
        System.out.println("--------------------------------------------------------------------------------");
        for (String[] mediaInfo : mediaLibrary) {
            if (mediaInfo[2].charAt(0) == mediaType) {
                displayMediaInfo(mediaInfo);
            }
        }
    }
    
    
}

public class CMIS242ASG4LoganToms {
    /*
     * Reads media information from a text file and creates an ArrayList of Media objects.
     * 
     * @param fileName the name of the text file to read
     * @return an ArrayList of Media objects read from the file
     */
    public static ArrayList<Media> loadMediaLibraryFromFile(String fileName) {
        ArrayList<Media> mediaLibrary = new ArrayList<>();
    
        try { // read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get("PRJ4Rentals.txt")); // **** Modify this line to use the fileName parameter ****
            
            // parse each line and create a Media object based on its model
            for (String line : lines) {
                String[] parts = line.split("\\, ");
                String mediaId = parts[0];
                boolean isRented = parts[1].equals("true");
                char mediaModel = parts[2].charAt(0);
                String title = parts[3];
                String yearPublished = parts[4];
    
                switch (mediaModel) {
                    case 'E':
                        int numberOfChapters = Integer.parseInt(parts[5]);
                        mediaLibrary.add(new EBook(mediaId, title, yearPublished, numberOfChapters, isRented));
                        break;
                    case 'C':
                        int lengthInMinutes = Integer.parseInt(parts[5]);
                        mediaLibrary.add(new MusicCD(mediaId, title, yearPublished, lengthInMinutes, isRented));
                        break;
                    case 'D':
                        int sizeInMegabytes = Integer.parseInt(parts[5]);
                        mediaLibrary.add(new MovieDVD(mediaId, title, yearPublished, sizeInMegabytes, isRented));
                        break;
                    default:
                        System.out.println("Invalid media model in the file. Skipping...");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    
        return mediaLibrary;
    }
    
    /*
     * Main method
     * The main () is considered as a driver, that is, only the minimum number of code lines are in it
     * The main () method should call the methods of the MediaManager class to perform the required operations
     */
    public static void main(String[] args) {
        // Creates a Scanner object to read user input
        try (Scanner scanner = new Scanner(System.in)) {
            MediaManager manager = new MediaManager();
            ArrayList<Media> loadedMedia = loadMediaLibraryFromFile("media_data.txt");

            for (Media media : loadedMedia) {
                manager.addMediaToArray(media);
            }

            manager.displayAllMedia();

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Add Media");
                System.out.println("2. Find Media");
                System.out.println("3. Remove Media");
                System.out.println("4. Rent Media");
                System.out.println("5. Modify Media");
                System.out.println("6. Display One Media");
                System.out.println("7. Display All Media of One Type");
                System.out.println("8. Display Whole Library");
                System.out.println("9. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                /*
                 * The switch statement is used to call methods of the MediaManager class
                 * The switch should not be considered process coding – no conditionals – but calls to methods in the program. 
                 */
                switch (choice) {
                    case 1:
                        // Add media
                        manager.addMedia();
                        break;
                    case 2:
                        // Find media
                        manager.findMedia();
                        break;
                    case 3:
                        // Remove media
                        manager.removeMedia();
                        break;
                    case 4:
                        // Rent media
                        manager.rentMedia();
                        break;
                    case 5:
                        // Modify media
                        manager.modifyMedia();
                        break;
                    case 6:
                        // Display one media
                        manager.displayOneMedia();
                        break;
                    case 7:
                        // Display all media of one type
                        manager.displayMediaByType();
                        break;
                    case 8:
                        // Display whole library
                        manager.displayAllMedia();
                        break;
                    case 9:
                        // Exit
                        System.out.println("Exiting...");
                        manager.displayAllMedia();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
} 