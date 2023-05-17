package BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BusinessLogic.validators.EmailValidator;
import BusinessLogic.validators.AgeValidator;
import BusinessLogic.validators.Validator;
import DAO.ClientDAO;
import Model.Clients;

import javax.swing.*;


public class ClientBLL {

	private List<Validator<Clients>> validators;
	private static ClientDAO clientDAO;

	public ClientBLL() {
		validators = new ArrayList<Validator<Clients>>();
		validators.add(new EmailValidator());
		validators.add(new AgeValidator());
		clientDAO = new ClientDAO();
	}

	public Clients findClientById(int id) {
		Clients client = clientDAO.findById(id);
		if (client == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return client;
	}

	public List<Clients> findAllClients() {
	 List<Clients> client = clientDAO.findAll();
		if (client == null) {
			throw new NoSuchElementException("");
		}
		return client;
	}

	public void insert(Clients client) {
		Clients st = clientDAO.insert(client);
	}
	public Clients delete(String id, String field) {
		return clientDAO.delete(id, field);
	}

	public Clients update ( ArrayList<String> values, String whereClause, String value){
		return clientDAO.update( values, whereClause, value);
	}

	public  <T> void fillTable(JTable table1,List<Clients> list){
		clientDAO.fillTable(table1, list);

	}
}
