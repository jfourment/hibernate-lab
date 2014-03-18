package org.testhibernate.customid;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGenerator implements IdentifierGenerator {

	private static final String QUERY_CALL_STORE_PROC = "call generateId(?,?,?)";
	
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		
        Long result = null;
        try {
        	Connection connection = session.connection();
            CallableStatement callableStmt = connection. prepareCall(QUERY_CALL_STORE_PROC);
            callableStmt.registerOutParameter(1, java.sql.Types.BIGINT);
            callableStmt.setInt(2, ((MyObject) object).getField1());
            callableStmt.setInt(3, ((MyObject) object).getField2());
            callableStmt.executeQuery();
            result = callableStmt.getLong(1);
            connection.close();
        } catch (SQLException sqlException) {
            throw new HibernateException(sqlException);
        }
        return result;
	}

}
