/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations;

import accountsJAXB.AccountType;
import accountsJAXB.AccountsType;
import accountsJAXB.ObjectFactory;
import com.chat.common.User;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import view.control.HomeViewController;

/**
 *
 * @author FARES-LAP
 */
public class AccountsJAXBHandler {

    private List<AccountType> accounts;

    public AccountsJAXBHandler() {
        accounts = new ArrayList<>();
    }

    public void saveAccount(User user) {
        try {
            JAXBContext context = JAXBContext.newInstance("accountsJAXB");
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            accountsJAXB.ObjectFactory objectFactory = new ObjectFactory();
            AccountType newAccount = objectFactory.createAccountType();
            newAccount.setAccountId(user.getPhone());
            newAccount.setSaved(true);
            AccountsType accountsType = objectFactory.createAccountsType();

            for (AccountType oldAccount : accounts) {
                oldAccount.setSaved(false);
                accountsType.getAccount().add(oldAccount);
            }
            accountsType.getAccount().add(newAccount);
            JAXBElement<AccountsType> JAXBElement = objectFactory.createAccounts(accountsType);
            marshaller.marshal(JAXBElement, new File("accounts.xml"));
        } catch (JAXBException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<AccountType> loadAccounts() {
        List<AccountType> accountsList = null;
        try {
            JAXBContext context = JAXBContext.newInstance("accountsJAXB");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement JAXBMyAccountsElement = (JAXBElement) unmarshaller.unmarshal(new File("accounts.xml"));
            AccountsType accountsType = (AccountsType) JAXBMyAccountsElement.getValue();
            accountsList = accountsType.getAccount();
        } catch (JAXBException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accountsList;
    }
}
