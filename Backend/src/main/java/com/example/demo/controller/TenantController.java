package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tenant;
import com.example.demo.repository.TenantRepository;
import com.example.demo.services.MailService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tenants")
public class TenantController {

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private MailService mailService; 

    @GetMapping
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tenant getTenantById(@PathVariable Long id) {
        return tenantRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Tenant createTenant(@RequestBody Tenant tenant) {
    	 mailService.sendSimpleMail(
    	            tenant.getEmail(),
    	            "Welcome to Our Platform!",
    	            "Hi " + tenant.getName() + ",\n\nThank you for registering with us!"
    	        );
        return tenantRepository.save(tenant);
    }

    @PutMapping("/{id}")
    public Tenant updateTenant(@PathVariable Long id, @RequestBody Tenant updatedTenant) {
        return tenantRepository.findById(id).map(tenant -> {
            tenant.setName(updatedTenant.getName());
            tenant.setEmail(updatedTenant.getEmail());
            tenant.setDescription(updatedTenant.getDescription());
            return tenantRepository.save(tenant);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable Long id) {
        tenantRepository.deleteById(id);
    }
}
