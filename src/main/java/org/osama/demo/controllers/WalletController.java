package org.osama.demo.controllers;


import org.osama.demo.model.BankAccount;
import org.osama.demo.repository.WalletRepository;
import org.osama.demo.model.Wallet;
import org.osama.demo.repository.WalletRepositoryImpl;
import org.osama.demo.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class WalletController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    WalletService walletService;

//  No Embedded database in this example  && init data In DemoApplication

//    get only wallet for user osama
   @GetMapping( value = "/wallets",produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Wallet> getWalletBalance() throws ExecutionException, InterruptedException {
       CompletableFuture<Map<String, Wallet>> mapCompletableFuture = walletService.getWallet();
       CompletableFuture.allOf(mapCompletableFuture).join();
       Map<String, Wallet> stringWalletMap = mapCompletableFuture.get();
       return stringWalletMap;
    }


//    get bank account for user osama
   @GetMapping( value = "/bank",produces = MediaType.APPLICATION_JSON_VALUE)
     public Map<String,BankAccount> getBankAccount() throws ExecutionException, InterruptedException {
         CompletableFuture<Map<String, BankAccount>> bankAccount = walletService.getBankAccount();
         CompletableFuture.allOf(bankAccount).join();
         Map<String, BankAccount> bankAccountMap = bankAccount.get();
         return bankAccountMap;
     }


//    withdraw from wallet and and add to bank account ..
    @PutMapping( value = "/wallets/{amount}",produces = MediaType.TEXT_PLAIN_VALUE)
    public String WithdrawWallet(@PathVariable("amount") String amount)
            throws ExecutionException, InterruptedException {

       double amountDouble= Double.parseDouble(amount);

       CompletableFuture<String> mapCompletableFuture =
               walletService.withdrawWallet(amountDouble);

       // Wait until they are all done
        CompletableFuture.allOf(mapCompletableFuture).join();
        String response = mapCompletableFuture.get();
        return response;
    }
    

}
