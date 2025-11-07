package com.example.bankmanagement.Controller;

import com.example.bankmanagement.Api.ApiResponse;
import com.example.bankmanagement.Model.BankManagementSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bankmanagement")
public class BankManagementController {
    ArrayList<BankManagementSystem>customers=new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<BankManagementSystem> getCustomers(){
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse AddCustomer(@RequestBody BankManagementSystem customer){
        customers.add(customer);
        return new ApiResponse("customer was added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse UpdateCustomer(@PathVariable int index,@RequestBody BankManagementSystem customer){
        customers.set(index, customer);

        return new ApiResponse("the customer have index: ("+index+") was updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse DeleteCustomer(@PathVariable int index){
        customers.remove(index);

        return new ApiResponse("the customer was deleted successfully");
    }


    @PutMapping("/deposit/{id}/{username}")
    public ApiResponse DepositMoney(@PathVariable int id ,@PathVariable String username,@RequestBody double amount){
        for (BankManagementSystem b:customers){
            if(b.getId()==id && b.getUsername().equals(username)){
                b.setBalance(b.getBalance()+amount);
                return new ApiResponse("your current balance after deposit: "+b.getBalance());
            }
        }
        return new ApiResponse("invalid id or username");
    }

    @PutMapping("/withdraw/{id}/{username}")
    public ApiResponse WithdrawMoney(@PathVariable int id,@PathVariable String username,@RequestBody int amount){
        for (BankManagementSystem b:customers){
            if (b.getId()==id && b.getUsername().equals(username)){
                if (b.getBalance()>=amount){
                    b.setBalance(b.getBalance()-amount);
                    return new ApiResponse("your current balance after withdraw: "+b.getBalance());
                }
                else
                    return new ApiResponse("your current balance is less than the amount want to withdraw");
            }
        }
        return new ApiResponse("invalid id or username");
    }
}
