package inshurer.model;

import inshurer.view.PolisController;
import inshurer.view.RateERGOController;

public class Polis {
    private String territory;
    private String option;
    private String coast;
    private String condition_franchisecoast;
    private String no_condition_franchisecoast;
    private String payment;
    PolisController polisController;


    public Polis(String territory, String option, String coast, String condition_franchisecoast, String no_condition_franchisecoast, String payment) {
        this.territory = territory;
        this.option = option;
        this.coast = coast;
        this.condition_franchisecoast = condition_franchisecoast;
        this.no_condition_franchisecoast = no_condition_franchisecoast;
        this.payment = payment;

        polisController.initPolis(territory, option, coast, condition_franchisecoast, no_condition_franchisecoast, payment);

    }
}


