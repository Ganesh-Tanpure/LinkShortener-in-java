
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LinkShortener {
    private Map<String, String> shortToLongMap = new HashMap();
    private Map<String, String> longToShortMap = new HashMap();

    public LinkShortener() {
    }

    private String generateShortUrl() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < 6; ++i) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }

    public String shortenUrl(String longUrl) {
        if (this.longToShortMap.containsKey(longUrl)) {
            return (String)this.longToShortMap.get(longUrl);
        } else {
            String shortUrl = this.generateShortUrl();
            if (this.shortToLongMap.containsKey(shortUrl)) {
                return this.shortenUrl(longUrl);
            } else {
                this.shortToLongMap.put(shortUrl, longUrl);
                this.longToShortMap.put(longUrl, shortUrl);
                return shortUrl;
            }
        }
    }

    public String expandUrl(String shortUrl) {
        return (String)this.shortToLongMap.getOrDefault(shortUrl, "Short URL not found");
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            while(true) {
                System.out.println("1. Shorten Link");
                System.out.println("2. Expand Link");
                System.out.println("3. Exit");
                System.out.print("Enter the choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter the URL to shorten: ");
                        String longUrl = scanner.nextLine();
                        String shortUrl = linkShortener.shortenUrl(longUrl);
                        System.out.println("Shortened URL: " + shortUrl);
                        break;
                    case 2:
                        System.out.print("Enter the URL to expand: ");
                        String shortInput = scanner.nextLine();
                        String expandedUrl = linkShortener.expandUrl(shortInput);
                        System.out.println("Expanded URL: " + expandedUrl);
                        break;
                    case 3:
                        System.out.println("Exit");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }
}
