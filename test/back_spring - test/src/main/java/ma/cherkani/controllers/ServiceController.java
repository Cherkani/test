package ma.cherkani.controllers;

import ma.cherkani.entities.Service;
import ma.cherkani.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    @Autowired
    private ServiceService service;

    @GetMapping
    public List<Service> findAllServices(){
        return service.findAll();
    }

    @PostMapping
    public Service createService(@RequestBody Service Service){
        Service.setId((long) 0);
        return service.create(Service);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Service Service=service.findById((long) id);
        if (Service==null) {
            return new ResponseEntity<Object>("la Service avec id : "+id+"est introuvable", HttpStatus.BAD_REQUEST);
        }else {
            return ResponseEntity.ok(Service);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateService(@PathVariable int id,@RequestBody Service newService){
        Service Service=service.findById((long) id);
        if (Service ==null) {
            return new ResponseEntity<Object>("la Service avec id : "+id+"est introuvable", HttpStatus.BAD_REQUEST);
        }else {
            newService.setId((long) id);
            return ResponseEntity.ok(service.update(newService));
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable int id) {
        Service Service = service.findById((long) id);
        if (Service == null) {
            return new ResponseEntity<Object>("la Service avec id : " + id + "est introuvable", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(service.delete(Service));
        }
    }}
