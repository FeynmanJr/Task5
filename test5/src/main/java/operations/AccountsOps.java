package operations;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import model.Account;

public class AccountsOps {
	static Configuration configuration;
	static Session session;

	static {
		configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Account.class);
		session = configuration.buildSessionFactory().openSession();
	}

	public enum columns {
        ID("id"),
        CUSTOMER_NO("customerNo"),
        PASSWORD("password"),
        NAME("name"),
        EMAIL("email"),
        CREATION_DATE("creationDate");

		private String columnName;

		columns(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}
	}

	public static boolean getAccounts() {
		List<Account> custResult = session.createQuery("FROM Account", Account.class).list();

		if (custResult != null) {
			for (int i = 0; i < custResult.size(); i++) {
				if (i != custResult.size() - 1)
					System.out.println(custResult.get(i).toString() + ", ");
				else
					System.out.println(custResult.get(i).toString());
			}
			return true;
		}
		return false;
	}

	public static boolean deleteAccountWithValue(LinkedHashMap<String, Object> params) {
		System.out.println("WORKİNG");
		Object obj2 = params.get("column");
		Object obj3 = params.get("value");
		if (/*obj2 instanceof String &&*/ obj3 instanceof String) {
			String column = (String) obj2;
			String value = (String) obj3;
			session.beginTransaction();
			Query<Account> query = session.createQuery("FROM Account WHERE " + column + " = :" + column + "",
					Account.class);
			query.setParameter(column, value);
			List<Account> custResultRemove = query.list();
			if (custResultRemove != null) {
				for (Account res : custResultRemove)
					session.remove(res);
				session.getTransaction().commit();
				return true;
			} else
			System.out.println(column + " bilgisi " + value + " olan bir kayıt bulunamadı.");
		} else
			System.out.println("Objelerden birisi null...");
		return false;
	}

	public static boolean insertAccounts(LinkedHashMap<String, Object> params) {

		Object obj1 = params.get("Accounts");
		if (obj1 instanceof Account) {
			Account newAccount = (Account) obj1;

			session.beginTransaction();
			session.persist(newAccount);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}
}
