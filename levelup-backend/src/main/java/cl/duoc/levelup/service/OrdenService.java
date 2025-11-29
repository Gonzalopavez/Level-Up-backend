package cl.duoc.levelup.service;

import cl.duoc.levelup.model.Orden;
import cl.duoc.levelup.model.DetalleOrden;
import cl.duoc.levelup.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public List<Orden> getAllOrdenes() {
        return ordenRepository.findAll();
    }

    public Orden guardarOrden(Orden orden) {
        // Necesitamos vincular los hijos (detalles) con el padre (orden)
        // antes de guardar, para que Java entienda la relación.
        if (orden.getItems() != null) {
            for (DetalleOrden detalle : orden.getItems()) {
                detalle.setOrden(orden); // Le decimos al detalle: "Esta es tu orden padre"
            }
        }
        return ordenRepository.save(orden);
    }
}