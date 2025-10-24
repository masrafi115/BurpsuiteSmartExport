import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.proxy.ProxyHttpRequestResponse;
import burp.api.montoya.ui.contextmenu.ContextMenuItemsProvider;

import java.util.List;
import java.awt.Component;
import java.io.FileWriter;
import java.security.SecureRandom;

import burp.api.montoya.ui.contextmenu.ContextMenuEvent;
import javax.swing.JMenuItem;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Extension implements BurpExtension {
    @Override
    public void initialize(MontoyaApi montoyaApi) {
        montoyaApi.extension().setName("Smart Export");

        montoyaApi.userInterface().registerContextMenuItemsProvider(new MyContextMenuItemsProvider(montoyaApi));
    }
}

class MyContextMenuItemsProvider implements ContextMenuItemsProvider {
    private MontoyaApi api;
    private static final SecureRandom RANDOM = new SecureRandom();

    public MyContextMenuItemsProvider(MontoyaApi api) {
        this.api = api;
    }

    @Override
    public List<Component> provideMenuItems(ContextMenuEvent event) {
        JMenuItem menuItem = new JMenuItem("Save .txt");

        menuItem.addActionListener(l -> {
            try {
                String userHome = System.getProperty("user.home");
                Path exportDir;

                if (System.getProperty("os.name").toLowerCase().contains("win")) {
                    exportDir = Paths.get(userHome, "BurpSmartExport");
                } else {
                    exportDir = Paths.get(userHome, "BurpSmartExport");
                }

                Files.createDirectories(exportDir);

                String suffix = randomSuffix(5);
                String filename = "full_history_" + suffix + ".txt";
                Path outputFile = exportDir.resolve(filename);

                try (FileWriter writer = new FileWriter(outputFile.toFile(), true)) {
                    List<ProxyHttpRequestResponse> proxy_history = api.proxy().history();

                    for (ProxyHttpRequestResponse requestResponse : proxy_history) {
                        String request = requestResponse.finalRequest().toString();
                        String response = requestResponse.response().toString();

                        writer.write("---Request---\n");
                        writer.write(request);
                        writer.write("\n");
                        writer.write("---Response---\n");
                        writer.write(response);
                        writer.write("\n\n");
                    }
                }

                api.logging().logToOutput("History saved to: " + outputFile.toAbsolutePath());

            } catch (Exception e) {
                api.logging().logToError("Exception while saving history: " + e.getMessage());
            }
        });

        return List.of(menuItem);
    }
    
    private static String randomSuffix(int length) {
        final String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int idx = RANDOM.nextInt(letters.length());
            sb.append(letters.charAt(idx));
        }
        return sb.toString();
    }

}