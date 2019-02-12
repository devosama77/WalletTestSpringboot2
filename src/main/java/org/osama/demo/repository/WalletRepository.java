package org.osama.demo.repository;

import org.osama.demo.model.BankAccount;
import org.osama.demo.model.Wallet;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface WalletRepository {
   public  void setWallet();
   public Map<String, Wallet>  getWallets();
   public Map<String,Wallet> withdrawFromWallet(double amount);
   public Map<String, BankAccount>  addToBankAccount(double amount);
   public Map<String,BankAccount> getBankAccount();
}
