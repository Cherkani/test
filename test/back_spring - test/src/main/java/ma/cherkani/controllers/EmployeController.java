package ma.cherkani.controllers;

import ma.cherkani.entities.Employe;
import ma.cherkani.entities.Service;
import ma.cherkani.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employes")
public class EmployeController {

    @Autowired
    private EmployeService service;

    @GetMapping
    public List<Employe> findAllEmployes(){
        return service.findAll();
    }

    @PostMapping
    public Employe createEmploye(@RequestBody Employe Employe){
        Employe.setId((long) 0);
        return service.create(Employe);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Employe Employe=service.findById((long) id);
        if (Employe==null) {
            return new ResponseEntity<Object>("la Employe avec id : "+id+"est introuvable", HttpStatus.BAD_REQUEST);
        }else {
            return ResponseEntity.ok(Employe);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmploye(@PathVariable int id,@RequestBody Employe newEmploye){
        Employe Employe=service.findById((long) id);
        if (Employe ==null) {
            return new ResponseEntity<Object>("la Employe avec id : "+id+"est introuvable", HttpStatus.BAD_REQUEST);
        }else {
            newEmploye.setId((long) id);
            return ResponseEntity.ok(service.update(newEmploye));
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmploye(@PathVariable int id) {
        Employe Employe = service.findById((long) id);
        if (Employe == null) {
            return new ResponseEntity<Object>("la Employe avec id : " + id + "est introuvable", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(service.delete(Employe));
        }
    }
    @GetMapping("/chef")
    public List<Employe> findEmployeByChefId(@RequestBody Employe employe) {
        return service.findEmployeByChefId(employe);
    }

    @GetMapping("/byserviceId")
    public List<Employe> findEmployeByServiceId(Long id) {
        return service.findEmployeByServiceId(id);
    }

}
