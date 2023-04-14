package util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DocumentIdGenerator {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int ID_LENGTH = 2;
    private static final Random RANDOM = new Random();
    private static final Set<String> GENERATED_IDS = new HashSet<>();

    public static String generateDocumentId() {
        String id;
        do {
            StringBuilder sb = new StringBuilder(ID_LENGTH);
            for (int i = 0; i < ID_LENGTH; i++) {
                sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
            }
            id = sb.toString();
        } while (GENERATED_IDS.contains(id));
        GENERATED_IDS.add(id);
        return id;
    }
}
