package io.netsphere.springdemo.controller;

import io.netsphere.springdemo.entity.Customer;
import io.netsphere.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // @RequestMapping(path="/list", method= RequestMethod.GET) can be replaced by @GetMapping and @PostMapping in 4.3
    @GetMapping("/list")
    public String listCustomers(Model model) {

        // controller -> service -> dao -> database
        List<Customer> customers = customerService.getCustomers();

        // add customers to spring mvc model
        model.addAttribute("customers", customers);

        return "customer-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        //  create new model attribute to bind form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        // save customer with service
        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

        // get customer from database
        Customer customer = customerService.getCustomer(id);

        // set customer as model attribute
        model.addAttribute(customer);

        // pre-populate form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id) {

        // delete customer
        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomer(@RequestParam("theSearchName") String name, Model model) {

        // search customer
        List<Customer> customers = customerService.searchCustomer(name);

        // add customers to model
        model.addAttribute("customers", customers);

        return "customer-list";
    }
}