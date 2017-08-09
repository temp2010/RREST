package com.udistrital.rrest.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RRestController {

    @RequestMapping(value = "/")
    public String welcome() {
        return "Service R statistics REST";
    }

    @RequestMapping(value = "/{a}/{b}", method = RequestMethod.GET, produces = "application/json")
    public String message(@PathVariable String a, @PathVariable String b) {
        try {
            RConnection c = new RConnection();
            REXP s = c.eval(a + "+" + b);
            REXP r = c.eval(a + "-" + b);
            REXP m = c.eval(a + "*" + b);
            REXP d = c.eval(a + "/" + b);
            
            return "{\"Suma\": " + s.asString() + ", \"Resta\":" + r.asString() + ", \"Multiplicación\":" + m.asString() + ", \"División\": " + d.asString() + "}";
        } catch (RserveException | REXPMismatchException ex) {
            Logger.getLogger(RRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
