package org.osama.demo.service;

import com.sun.xml.internal.ws.util.CompletedFuture;
import org.osama.demo.model.BankAccount;
import org.osama.demo.model.Wallet;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;


public interface WalletService {
    public CompletableFuture<Map<String, Wallet>>  getWallet();
    public CompletableFuture<Map<String, BankAccount>> getBankAccount();
    public CompletableFuture<String> withdrawWallet(double amount);
    public void setWallet();
}
