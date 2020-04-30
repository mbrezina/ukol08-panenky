package cz.czechitas.webapp;

import java.io.*;
import java.util.*;
import org.springframework.core.io.*;
import org.springframework.core.io.support.*;

public class ObrazkyRepository {

    private List<String> vrsky;
    private List<String> spodky;

    public ObrazkyRepository() {
        vrsky = najdiSoubory("javagirl_top");
        spodky = najdiSoubory("javagirl_bottom");
    }

    public List<String> findAllVrsky() {
        return vrsky;
    }

    public List<String> findAllSpodky() {
        return spodky;
    }

    private List<String> najdiSoubory(String prefixJmenaSouboru) {
        try {
            ResourcePatternResolver prohledavacSlozek = new PathMatchingResourcePatternResolver();
            List<Resource> soubory = Arrays.asList(prohledavacSlozek.getResources("classpath:/static/images/" + prefixJmenaSouboru + "*"));

            List<String> paths = new ArrayList<>(soubory.size());
            for (Resource soubor : soubory) {
                String jmenoSouboru = soubor.getFilename();
                paths.add(jmenoSouboru);
            }
            return paths;
        } catch (IOException ex) {
            throw new RuntimeException("Nepodařilo se prohledat složku s obrázky", ex);
        }
    }

}
