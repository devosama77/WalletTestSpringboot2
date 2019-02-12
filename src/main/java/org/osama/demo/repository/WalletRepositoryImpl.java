package org.osama.demo.repository;

import org.osama.demo.model.BankAccount;
import org.osama.demo.model.Wallet;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class WalletRepositoryImpl implements WalletRepository {
    // Concurrent Hash map
    public static Map<String, Wallet> userWallet=new ConcurrentHashMap<>();
    public static Map<String,BankAccount> userBankAccount=new ConcurrentHashMap<>();

    Wallet wallet=new Wallet();
    BankAccount bankAccount=new BankAccount();

// init new wallet
    @Override
    public void setWallet() {
        wallet.setBalance(50);
        wallet.setUserId("11");
        wallet.setCurrency("$");
        wallet.setLastUpdated(new Date());
        userWallet.put("osama",wallet);
        bankAccount.setBalance(0);
        bankAccount.setCurrency("$");
        bankAccount.setLastUpdated(new Date());
        userBankAccount.put("osama",bankAccount);

    }

    @Override
    public Map<String, Wallet> getWallets() {
        return userWallet;
    }
    @Override
    public Map<String,BankAccount> getBankAccount(){
        return userBankAccount;
    }

    @Override
    public Map<String,Wallet> withdrawFromWallet(double amount) {
        double balance = wallet.getBalance();
        balance-=amount;
        wallet.setBalance(balance);
        userWallet.put("osama",wallet);
        return userWallet;
    }

    @Override
    public Map<String,BankAccount> addToBankAccount(double amount) {
        double balance = bankAccount.getBalance();
        balance+=amount;
        bankAccount.setBalance(balance);
        return userBankAccount;
    }


}
