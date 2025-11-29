package cl.duoc.levelup.controller;

import cl.duoc.levelup.model.Orden;
import cl.duoc.levelup.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public List<Orden> getAll() {
        return ordenService.getAllOrdenes();
    }

    @PostMapping
    public Orden create(@RequestBody Orden orden) {
        return ordenService.guardarOrden(orden);
    }
}