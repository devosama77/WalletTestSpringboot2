package org.osama.demo.service;

import org.osama.demo.model.BankAccount;
import org.osama.demo.model.Wallet;
import org.osama.demo.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public void setWallet() {
         walletRepository.setWallet();
    }

    // get wallet
    @Async
    @Override
    public CompletableFuture<Map<String, Wallet>>  getWallet() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, Wallet> userWallets = walletRepository.getWallets();
        return CompletableFuture.completedFuture(userWallets);
    }

    // get bank Account
    @Override
    public CompletableFuture<Map<String, BankAccount>> getBankAccount() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, BankAccount> bankAccount = walletRepository.getBankAccount();
        return CompletableFuture.completedFuture(bankAccount);
    }

    // withdraw from wallet and add to bank account in one Transaction
    @Async
    @Override
    public CompletableFuture<String> withdrawWallet(double amount) {
        Map<String, Wallet> stringWalletMap=null;
        Map<String,BankAccount> bankAccountMap=null;
        String response="";
        try {
            // 2 second .
            Thread.sleep(2000L);
            stringWalletMap= walletRepository.withdrawFromWallet(amount);
            bankAccountMap=walletRepository.addToBankAccount(amount);
            response="User: Osama"+" Last Upadate : "+ bankAccountMap.get("osama")
                    .getLastUpdated()
                    +"\n Wallet Amount : "+stringWalletMap.get("osama")
                    .getBalance()+stringWalletMap
                    .get("osama").getCurrency()
                    +"\n Bank Amount : "+bankAccountMap.get("osama")
                    .getBalance()+bankAccountMap.get("osama")
                        .getCurrency();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(response);
    }


}
