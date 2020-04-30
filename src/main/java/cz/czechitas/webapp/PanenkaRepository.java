package cz.czechitas.webapp;

import java.util.*;

public interface PanenkaRepository {

    List<Panenka> findAll();

    Panenka save(Panenka zaznamKUlozeni);

    void deleteById(Long id);
}
