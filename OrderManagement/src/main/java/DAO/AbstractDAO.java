package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import BusinessLogic.ClientBLL;
import Connection.ConnectionFactory;
import Model.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	public T findById(int idProducts) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query= new String();
		for(Field f: type.getDeclaredFields()){
			 query = createSelectQuery(f.getName());
			break;
		}

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, idProducts);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	private String createFindAllQuery(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());

		return sb.toString();
	}
	public List<T> findAll() {
		List<T> myList= new ArrayList<>();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createFindAllQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();


			myList=(createObjects(resultSet));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

		return myList;
	}

	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T)ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	private String createInsertQuery(String table, ArrayList<String> values) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(table);
		sb.append(" VALUES");
		sb.append("(");
		for (String v : values) {
			sb.append("'").append(v).append("'").append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return sb.toString();
	}

	public T  insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<String> vals = new ArrayList<>();

		for (Field f : type.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				vals.add(f.get(t).toString());

			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		String query = createInsertQuery(type.getSimpleName(), vals);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}


	private String createDelteQuery(String value, String field){
		StringBuilder sb = new StringBuilder();

		sb.append("DELETE ");
		sb.append("FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " = "+ value);
		return sb.toString();

	}

	public T delete(String value, String field){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createDelteQuery(value, field);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			 statement.executeUpdate();


		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	private String createUpdateQuery(String table, ArrayList<String> values, String whereClause, String value) {
		StringBuilder sb = new StringBuilder();
		Field[] fields = type.getDeclaredFields();

		sb.append("UPDATE ");
		sb.append(table);
		sb.append(" SET ");

		int aux=0;

		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].getName().equals("id")) {
				if(!values.get(i).equals("empty")) {
					fields[i].setAccessible(true);
					sb.append(fields[i].getName()).append("='").append(values.get(i)).append("'");

						sb.append(", ");

				}
			}
		}
		sb.deleteCharAt(sb.length() - 2);

		sb.append(" WHERE ");
		sb.append(whereClause + "="  + "'" + value + "'");

		return sb.toString();
	}

	public T update( ArrayList<String> values, String whereClause, String value) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		String query = createUpdateQuery(type.getSimpleName(), values, whereClause, value);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;

	}

	public JTable fillTable(JTable table1, List<T> listOfObjects ){

		String[][] matrix = new String[listOfObjects.size()][listOfObjects.size()];

		Vector columns = new Vector<>();
		ArrayList<String> values = new ArrayList<>();
		Field[] fields = type.getDeclaredFields();

		for(Field f: type.getDeclaredFields()){
			columns.add(f.getName());
		}

		Vector<Vector<Object>> data = new Vector<>();

		for (T obj : listOfObjects) {
			Vector<Object> rowData = new Vector(fields.length);
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				try {
					rowData.addElement(fields[i].get(obj));
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
			data.addElement(rowData);
		}
		DefaultTableModel model = new DefaultTableModel(data, columns);
        table1.setModel(model);
		return table1;
	}


}
