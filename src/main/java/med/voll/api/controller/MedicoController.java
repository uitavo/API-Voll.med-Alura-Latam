package med.voll.api.controller;

import jakarta.transaction.TransactionScoped;
import jakarta.validation.Valid;
import med.voll.api.models.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroMedico json){
        repository.save(new Medico(json));

    }

    @GetMapping
    public Page<DatosListaMedicos> listar(@PageableDefault(size = 10, sort = {"nombre"} ) Pageable paginacion){
        return repository.findAllByActivoTrue(paginacion).map(DatosListaMedicos::new);
    }

    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizacionMedico datosActualizacionMedico){
        Medico medico = repository.getReferenceById(datosActualizacionMedico.id());
        medico.actualizarInformacion(datosActualizacionMedico);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        medico.eliminar();
    }

}
