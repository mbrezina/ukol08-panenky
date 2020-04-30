package cz.czechitas.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZoneId;
import java.util.List;

@Controller
public class HlavniController {

    private PanenkaRepository panenkaRepository;
    private ObrazkyRepository obrazkyRepository;

    public HlavniController() {
        panenkaRepository = new JdbcPanenkaRepository();
        obrazkyRepository = new ObrazkyRepository();
    }

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping("/seznam.html")
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("seznam");
        List<Panenka> vsechnyPanenky = panenkaRepository.findAll();
        drzakNaDataAJmenoSablony.addObject("seznamPanenek", vsechnyPanenky);
        drzakNaDataAJmenoSablony.addObject("casovaZona", ZoneId.systemDefault());
        return drzakNaDataAJmenoSablony;
    }

    @RequestMapping(value = "/nova.html", method = RequestMethod.GET)
    public ModelAndView zobrazNova() {
        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("nova");
        drzakNaDataAJmenoSablony.addObject("seznamVrsku", obrazkyRepository.findAllVrsky());
        drzakNaDataAJmenoSablony.addObject("seznamSpodku", obrazkyRepository.findAllSpodky());
        return drzakNaDataAJmenoSablony;
    }

    @RequestMapping(value = "/nova.html", method = RequestMethod.POST)
    public ModelAndView zpracujNova(NovaForm vyplnenyFormular) {
        Panenka novaPanenka = new Panenka(vyplnenyFormular.getJmeno(), vyplnenyFormular.getVrsek(), vyplnenyFormular.getSpodek());
        if (novaPanenka.getVrsek() == null) {
            novaPanenka.setVrsek("");
        }
        if (novaPanenka.getSpodek() == null) {
            novaPanenka.setSpodek("");
        }
        panenkaRepository.save(novaPanenka);
        return new ModelAndView("redirect:/seznam.html");
    }

    
    @RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.DELETE)
    public ModelAndView zpracujSmazat(@PathVariable Long id) {
        panenkaRepository.deleteById(id);
        return new ModelAndView("redirect:/seznam.html");
    }

}
