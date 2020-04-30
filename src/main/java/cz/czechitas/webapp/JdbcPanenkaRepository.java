package cz.czechitas.webapp;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class JdbcPanenkaRepository implements PanenkaRepository {

    private RowMapper<Panenka> prevodnik;
    private JdbcTemplate odesilacDotazu;

    public JdbcPanenkaRepository() {
        try {
            MariaDbDataSource konfiguraceDatabaze = new MariaDbDataSource();
            konfiguraceDatabaze.setUserName("student");
            konfiguraceDatabaze.setPassword("password");
            konfiguraceDatabaze.setUrl("jdbc:mariadb://localhost:3306/SkladPanenek");

            odesilacDotazu = new JdbcTemplate(konfiguraceDatabaze);
            prevodnik = BeanPropertyRowMapper.newInstance(Panenka.class);

        } catch (SQLException sqle) {
            throw new DataSourceLookupFailureException("Chyba připojení do databáze");
        }
    }

    @Override
    public List<Panenka> findAll() {
        return odesilacDotazu.query("SELECT ID, Jmeno, Vrsek, Spodek, CasVzniku FROM Panenky ORDER BY CasVzniku DESC", prevodnik);
    }

    //Panenka zaznamKUlozeni = new Panenka("Xenie" + (int) (Math.random() * 100), "javagirl_top05.png", "javagirl_bottom05.png");
    @Override
    public Panenka save(Panenka zaznamKUlozeni) {
        // když panenka už existuje a má ID:
        if (zaznamKUlozeni.getId() != null) {
            throw new IllegalArgumentException("Panenka.ID musí být null. Panenku lze do databáze jen přidat, nikoliv měnit.");
        }
        // panenka ještě neexistuje:
        Panenka novyZaznam = clone(zaznamKUlozeni);
        GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
        String sql = "INSERT INTO Panenky (Jmeno, Vrsek, Spodek, CasVzniku) " +
            "VALUES (?, ?, ?, ?)";
        odesilacDotazu.update((Connection con) -> {
                PreparedStatement prikaz = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prikaz.setString(1, novyZaznam.getJmeno());
                prikaz.setString(2, novyZaznam.getVrsek());
                prikaz.setString(3, novyZaznam.getSpodek());
                prikaz.setTimestamp(4, new Timestamp(novyZaznam.getCasVzniku().toEpochMilli()));
                return prikaz;
            },
            drzakNaVygenerovanyKlic);
        novyZaznam.setId(drzakNaVygenerovanyKlic.getKey().longValue());
        System.out.println("\nPřidali jsme novou panenku:\n  " + novyZaznam);

        return null;
    }

    @Override
    public void deleteById(Long id) {
        odesilacDotazu.update(
            "DELETE FROM Panenky WHERE ID = ?",
            id);
        System.out.println("\nA nyni uz je z databaze zase odstranena.");

    }

    public Panenka findById(Long id) {
        return odesilacDotazu.queryForObject("SELECT ID, Jmeno, Vrsek, Spodek, CasVzniku FROM Panenky WHERE ID = ?", prevodnik, id);
    }

    private static Panenka clone(Panenka puvodniObjekt) {
        return new Panenka(puvodniObjekt.getId(), puvodniObjekt.getJmeno(), puvodniObjekt.getVrsek(), puvodniObjekt.getSpodek(), puvodniObjekt.getCasVzniku());
    }


}



